
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseListService extends AbstractService<Lecturer, Course> {

	@Autowired
	protected LecturerCourseRepository repository;


	@Override
	public void check() {
		final boolean status = true;
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		final boolean status = true;
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		final int lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		final Collection<Course> courses = this.repository.findAllCoursesOfLecturer(lecturerId);
		super.getBuffer().setData(courses);
	}

	@Override
	public void unbind(final Course course) {
		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl", "lecturer", "inDraft");
		super.getResponse().setData(tuple);
	}
}
