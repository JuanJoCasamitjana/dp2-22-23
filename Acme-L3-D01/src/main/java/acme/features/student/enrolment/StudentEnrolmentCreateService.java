
package acme.features.student.enrolment;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.enrolment.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentCreateService extends AbstractService<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Student.class));
	}

	@Override
	public void load() {

		final Enrolment enrolment = new Enrolment();
		enrolment.setDraft(true);

		final Student student = this.repository.findStudentById(super.getRequest().getPrincipal().getActiveRoleId());
		enrolment.setStudent(student);
		enrolment.setLowerNibble("");
		enrolment.setHolderName("");

		super.getBuffer().setData(enrolment);
	}
	@Override
	public void bind(final Enrolment object) {
		assert object != null;
		final int courseId = super.getRequest().getData("course", int.class);
		final Course course = this.repository.findCourseById(courseId);
		object.setCourse(course);
		super.bind(object, "motivation", "goals", "code");

	}

	@Override
	public void validate(final Enrolment object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("course")) {
			final boolean exceded = this.repository.findAllEnrolmentsOfOneStudentToOneCourseById(object.getCourse().getId(), object.getStudent().getId()) > 0;
			super.state(!exceded, "course", "student.enrolment.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			final boolean duplicatedCode = this.repository.findOneEnrolmentByCode(object.getCode()) > 0;
			super.state(!duplicatedCode, "code", "student.enrolment.form.error.duplicated-code");
		}

	}

	@Override
	public void perform(final Enrolment object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;
		Tuple tuple;

		final Collection<Course> courses = this.repository.findPublishedCourses();
		final Collection<Enrolment> enrolments = this.repository.findAllEnrolments();

		courses.removeAll(enrolments.stream().map(x -> x.getCourse()).collect(Collectors.toList()));

		final SelectChoices choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "motivation", "goals", "draft", "workTime");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());

		super.getResponse().setData(tuple);
	}

}
