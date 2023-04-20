
package acme.features.lecturer.lectureCourseAggregation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.lecture.Lecture;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LectureCourseAggregationCreateService extends AbstractService<Lecturer, LectureCourseAggregation> {

	@Autowired
	protected LectureCourseAggregationRepository repository;


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
		final LectureCourseAggregation aggregation = new LectureCourseAggregation();
		super.getBuffer().setData(aggregation);
	}
	@Override
	public void bind(final LectureCourseAggregation object) {
		assert object != null;
		final int courseId = super.getRequest().getData("course", int.class);
		final int lectureId = super.getRequest().getData("lecture", int.class);
		final Lecture lecture = this.repository.findLectureById(lectureId);
		final Course course = this.repository.findCourseById(courseId);
		object.setCourse(course);
		object.setLecture(lecture);
	}

	@Override
	public void validate(final LectureCourseAggregation object) {
		assert object != null;
		/***
		 * if (object.getCourse() == null)
		 * super.state(false, "course", "course must be selected");
		 * else
		 * super.state(object.getCourse().isInDraft(), "*", "not.in.draft");
		 * if (object.getLecture() == null)
		 * super.state(false, "lecture", "lecture must be selected");
		 ***/

	}
	@Override
	public void perform(final LectureCourseAggregation object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final LectureCourseAggregation object) {
		assert object != null;
		final int lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		final Collection<Course> courses = this.repository.findAvailableCoursesToAddLecture(lecturerId);
		final Collection<Lecture> lectures = this.repository.findAllLecturesOfLecturer(lecturerId);
		final SelectChoices coursesChoices = SelectChoices.from(courses, "code", courses.stream().findAny().get());
		final SelectChoices lectureChoices = SelectChoices.from(lectures, "title", lectures.stream().findAny().get());
		final Tuple tuple = super.unbind(object, "course", "lecture");
		tuple.put("courses", coursesChoices);
		tuple.put("lectures", lectureChoices);
		super.getResponse().setData(tuple);
	}
}
