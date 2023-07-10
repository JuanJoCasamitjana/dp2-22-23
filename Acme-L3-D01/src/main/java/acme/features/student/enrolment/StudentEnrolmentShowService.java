
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
public class StudentEnrolmentShowService extends AbstractService<Student, Enrolment> {

	//Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	//AbstractService<Authenticated, Consumer> ---------------------------


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
		Enrolment enrolment;
		int id;

		id = super.getRequest().getData("id", int.class);
		enrolment = this.repository.findEnrolmentById(id);

		super.getBuffer().setData(enrolment);
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

		tuple = super.unbind(object, "code", "motivation", "goals", "draft", "holderName", "lowerNibble", "workTime");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());

		super.getResponse().setData(tuple);
	}

}
