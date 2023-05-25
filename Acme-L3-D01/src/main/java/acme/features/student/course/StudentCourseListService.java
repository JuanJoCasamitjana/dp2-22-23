
package acme.features.student.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentCourseListService extends AbstractService<Student, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentCourseRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		final boolean auth = super.getRequest().getPrincipal().getActiveRole() == Student.class;
		super.getResponse().setAuthorised(auth);
	}

	@Override
	public void load() {
		Collection<Course> Courses;

		Courses = this.repository.findAllPublishedCourses();
		super.getBuffer().setData(Courses);
	}

	@Override
	public void unbind(final Course course) {
		assert course != null;

		Tuple tuple;

		tuple = super.unbind(course, "code", "title", "retailPrice");

		super.getResponse().setData(tuple);
	}

}
