
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.lecture.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureListOfCourseService extends AbstractService<Lecturer, Lecture> {

	@Autowired
	protected LecturerLectureRepository repository;


	@Override
	public void check() {
		final boolean status = true;
		super.getRequest().getData("courseId", int.class);
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		final boolean status;
		final Course course = this.repository.findCourseById(super.getRequest().getData("courseId", int.class));
		status = course.getLecturer().getId() == super.getRequest().getPrincipal().getActiveRoleId();
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		final int courseId = super.getRequest().getData("courseId", int.class);
		final Collection<Lecture> lectures = this.repository.findAllLecturesOfCourse(courseId);
		super.getBuffer().setData(lectures);
	}

	@Override
	public void unbind(final Lecture lecture) {
		Tuple tuple;
		tuple = super.unbind(lecture, "title", "abstractMessage", "learningTime", "body", "theoretical", "optionalUrl", "published");
		super.getResponse().setData(tuple);
	}
}
