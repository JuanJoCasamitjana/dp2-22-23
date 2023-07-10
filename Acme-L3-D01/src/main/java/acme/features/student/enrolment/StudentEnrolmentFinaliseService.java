
package acme.features.student.enrolment;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.enrolment.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
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

		final int courseId = super.getRequest().getData("course", int.class);
		final Course course = this.repository.findCourseById(courseId);
		object.setCourse(course);

		super.bind(object, "holderName", "lowerNibble", "creditCard", "cvc", "expiryDate");

	}

	@Override
	public void validate(final Enrolment object) {
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
			final boolean emptyDate = expiryDate.isEmpty() || expiryDate == null;
			super.state(wrongExpiryDate, "expiryDate", "student.enrolment.form.error.wrong-expiry-date");

			if (!emptyDate) {
				final String year = expiryDate.split("/")[1];
				final String month = expiryDate.split("/")[0];
				if (99 >= Integer.parseInt(year) && Integer.parseInt(year) > 0 && 12 >= Integer.parseInt(month) && Integer.parseInt(month) > 0) {
					final LocalDate fecha = LocalDate.of(2000 + Integer.parseInt(year), Integer.parseInt(month), 1);

					Date ahora;
					final Calendar cal1 = Calendar.getInstance();
					final Calendar cal2 = Calendar.getInstance();

					cal1.set(fecha.getYear(), fecha.getMonthValue(), 1);
					ahora = MomentHelper.getCurrentMoment();

					cal2.setTime(ahora);

					final Date fechaValidacion = cal1.getTime();
					final Date fechaSistema = cal2.getTime();

					super.state(fechaSistema.before(fechaValidacion), "expiryDate", "student.enrolment.form.error.wrong-expiry-date-incorrect");
				} else
					super.state(false, "expiryDate", "student.enrolment.form.error.wrong-expiry-date");
			}
			super.state(!emptyDate, "expiryDate", "student.enrolment.form.error.wrong-holder");
		}

	}

	@Override
	public void perform(final Enrolment object) {
		object.setDraft(false);
		final String card = super.getRequest().getData("creditCard", String.class);
		if (object.getCreditCard().length() > 4)
			object.setLowerNibble(card.substring(card.length() - 4));
		this.repository.save(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		Tuple tuple;

		//Todos los cursos publicados
		final Collection<Course> courses = this.repository.findPublishedCourses();
		//El studen logueado
		final Student student = this.repository.findStudentById(super.getRequest().getPrincipal().getActiveRoleId());
		//Todos los cursos que tienen un enrolment del alumno logeado
		final List<Course> coursesWithEnrolment = this.repository.findByStudent(student).stream().map(x -> x.getCourse()).collect(Collectors.toList());
		//Todos los cursos - los cursos que tienen un enrolment(student)
		courses.removeAll(coursesWithEnrolment);
		courses.add(object.getCourse());
		final SelectChoices choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "motivation", "goals", "holderName", "lowerNibble", "draft", "workTime", "creditCard", "cvc", "expiryDate");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());

		super.getResponse().setData(tuple);
	}

}
