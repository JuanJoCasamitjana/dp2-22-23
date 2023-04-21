
package acme.features.lecturer.lectureCourseAggregation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
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
		final boolean status = super.getRequest().hasData("lectureId", int.class);
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		final int lectureId = super.getRequest().getData("lectureId", int.class);
		final Lecture lecture = this.repository.findLectureById(lectureId);
		final int roleId = super.getRequest().getPrincipal().getActiveRoleId();
		super.getResponse().setAuthorised(roleId == lecture.getLecturer().getId());
	}
	@Override
	public void load() {
		final LectureCourseAggregation aggregation = new LectureCourseAggregation();
		final int lectureId = super.getRequest().getData("lectureId", int.class);
		final Lecture lecture = this.repository.findLectureById(lectureId);
		aggregation.setLecture(lecture);
		super.getBuffer().setData(aggregation);
	}
	@Override
	public void bind(final LectureCourseAggregation object) {
		assert object != null;
		if (super.getRequest().hasData("course", int.class)) {
			final int courseId = super.getRequest().getData("course", int.class);
			final Course course = this.repository.findCourseById(courseId);
			object.setCourse(course);
		}
		final int lectureId = super.getRequest().getData("lectureId", int.class);
		final Lecture lecture = this.repository.findLectureById(lectureId);
		object.setLecture(lecture);
	}

	@Override
	public void validate(final LectureCourseAggregation object) {
		assert object != null;
		if (object.getCourse() != null && object.getLecture() != null) {
			final int lectureId = object.getLecture().getId();
			final int courseId = object.getCourse().getId();
			final LectureCourseAggregation sameRelation = this.repository.findAggregationByCourseAndLecture(courseId, lectureId);
			super.state(sameRelation == null, "*", "lca.form.already.exists");
		}
	}
	@Override
	public void perform(final LectureCourseAggregation object) {
		assert object != null;
		final Course course = this.repository.findCourseById(object.getCourse().getId());
		final Collection<Lecture> lectures = this.repository.findAllLecturesOfCourse(object.getCourse().getId());
		if (!lectures.isEmpty()) {
			final long theoryLectures = lectures.stream().filter(Lecture::isTheoretical).count();
			final double ratio = theoryLectures * 1.0 / lectures.size();
			if (0. >= ratio && ratio <= 0.40)
				course.setTypeOfCourse(Type.HANDS_ON);
			else if (0.40 < ratio && ratio <= 0.6)
				course.setTypeOfCourse(Type.EQUILIBRATED);
			else if (0.6 < ratio && ratio < 1.)
				course.setTypeOfCourse(Type.THEORETICAL);
			else if (ratio == 1.0)
				course.setTypeOfCourse(Type.PURELY_THEORETICAL);
		}
		this.repository.save(course);
		this.repository.save(object);
	}

	@Override
	public void unbind(final LectureCourseAggregation object) {
		assert object != null;
		final int lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		final Collection<Course> courses = this.repository.findAvailableCoursesToAddLecture(lecturerId);
		final SelectChoices coursesChoices = SelectChoices.from(courses, "code", courses.stream().findAny().get());
		final Tuple tuple = new Tuple();
		tuple.put("lecture", object.getLecture().getTitle());
		tuple.put("courses", coursesChoices);
		tuple.put("lectureId", object.getLecture().getId());
		super.getResponse().setData(tuple);
	}
}
