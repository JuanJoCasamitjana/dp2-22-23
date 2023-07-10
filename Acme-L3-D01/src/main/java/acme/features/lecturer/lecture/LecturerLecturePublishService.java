
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
public class LecturerLecturePublishService extends AbstractService<Lecturer, Lecture> {

	@Autowired
	protected LecturerLectureRepository repository;


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		boolean status;
		final int roleId = super.getRequest().getPrincipal().getActiveRoleId();
		final int lectureId = super.getRequest().getData("id", int.class);
		final int lecturerId = this.repository.findOneLectureById(lectureId).getLecturer().getId();
		status = roleId == lecturerId;
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Lecture lectures = this.repository.findOneLectureById(id);
		super.getBuffer().setData(lectures);
	}
	@Override
	public void bind(final Lecture object) {
	}

	@Override
	public void validate(final Lecture object) {
	}
	@Override
	public void perform(final Lecture object) {
		object.setPublished(true);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Lecture lecture) {
		Tuple tuple;
		tuple = super.unbind(lecture, "title", "abstractMessage", "learningTime", "body", "theoretical", "optionalUrl", "published");
		final Collection<Course> courses = this.repository.findAllCoursesOfLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		tuple.put("noCourses", courses.isEmpty());
		super.getResponse().setData(tuple);
	}
}
