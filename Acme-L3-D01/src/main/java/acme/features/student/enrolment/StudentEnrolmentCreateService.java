
package acme.features.student.enrolment;

import java.util.Collection;
import java.util.List;
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

		final int courseId = super.getRequest().getData("course", int.class);
		final Course course = this.repository.findCourseById(courseId);
		object.setCourse(course);
		super.bind(object, "motivation", "goals", "code");

	}

	@Override
	public void validate(final Enrolment object) {

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

		final SelectChoices choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "motivation", "goals", "draft", "workTime");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());

		super.getResponse().setData(tuple);
	}

}
