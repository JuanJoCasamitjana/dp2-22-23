
package acme.features.student.enrolment;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.enrolment.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentFinaliseService extends AbstractService<Student, Enrolment> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final Integer eId = super.getRequest().getData("id", int.class);
		final Enrolment e = this.repository.findEnrolmentById(eId);
		final boolean auth = super.getRequest().getPrincipal().getActiveRoleId() == e.getStudent().getId();
		super.getResponse().setAuthorised(auth);
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Enrolment enrolment = this.repository.findEnrolmentById(id);

		super.getBuffer().setData(enrolment);
	}

	@Override
	public void bind(final Enrolment object) {
		assert object != null;

		final int courseId = super.getRequest().getData("course", int.class);
		final Course course = this.repository.findCourseById(courseId);
		object.setCourse(course);

		super.bind(object, "holderName", "lowerNibble", "creditCard", "cvc", "expiryDate");

	}

	@Override
	public void validate(final Enrolment object) {

		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("holderName")) {
			final String holderName = object.getHolderName();
			final boolean wrongholderName = holderName.isEmpty();

			super.state(!wrongholderName, "holderName", "student.enrolment.form.error.wrong-holder");
		}
		if (!super.getBuffer().getErrors().hasErrors("creditCard")) {
			final String creditCard = object.getCreditCard();
			final boolean wrongCard = creditCard.matches("^\\d{16}$");
			final boolean emptyCard = creditCard.isEmpty();

			super.state(!emptyCard, "creditCard", "student.enrolment.form.error.wrong-holder");
			super.state(wrongCard, "creditCard", "student.enrolment.form.error.wrong-card");
		}
		if (!super.getBuffer().getErrors().hasErrors("cvc")) {
			final String cvc = object.getCvc();
			final boolean wrongcvc = cvc.matches("^\\d{3}$");
			final boolean emptyCvc = cvc.isEmpty();
			super.state(wrongcvc, "cvc", "student.enrolment.form.error.wrong-cvc");
			super.state(!emptyCvc, "cvc", "student.enrolment.form.error.wrong-holder");
		}
		if (!super.getBuffer().getErrors().hasErrors("expiryDate")) {
			final String expiryDate = object.getExpiryDate();

			final boolean wrongExpiryDate = expiryDate.matches("^\\d{2}\\/\\d{2}$");
			final boolean emptyDate = expiryDate.isEmpty();
			super.state(wrongExpiryDate, "expiryDate", "student.enrolment.form.error.wrong-expiry-date");
			super.state(!emptyDate, "expiryDate", "student.enrolment.form.error.wrong-holder");

			final String year = expiryDate.split("/")[1];
			final String month = expiryDate.split("/")[0];
			if (99 >= Integer.parseInt(year) && Integer.parseInt(year) > 0 && 12 >= Integer.parseInt(month) && Integer.parseInt(month) > 0) {
				final LocalDate fecha = LocalDate.of(2000 + Integer.parseInt(year), Integer.parseInt(month), 1);
				final LocalDate actual = LocalDate.now();
				super.state(actual.isBefore(fecha), "expiryDate", "student.enrolment.form.error.wrong-expiry-date-incorrect");
			} else
				super.state(false, "expiryDate", "student.enrolment.form.error.wrong-expiry-date");
		}

	}

	@Override
	public void perform(final Enrolment object) {
		assert object != null;
		object.setDraft(false);
		final String card = super.getRequest().getData("creditCard", String.class);
		if (object.getCreditCard().length() > 4)
			object.setLowerNibble(card.substring(card.length() - 4));
		this.repository.save(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;
		Tuple tuple;

		Collection<Course> courses;
		SelectChoices choices;
		courses = this.repository.findCourses();
		choices = SelectChoices.from(courses, "code", object.getCourse());

		tuple = super.unbind(object, "code", "motivation", "goals", "holderName", "lowerNibble", "draft", "creditCard", "cvc", "expiryDate");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());

		super.getResponse().setData(tuple);
	}

}
