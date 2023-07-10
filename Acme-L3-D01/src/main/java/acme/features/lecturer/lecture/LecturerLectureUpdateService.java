
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
import acme.entities.lecture.Lecture;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureUpdateService extends AbstractService<Lecturer, Lecture> {

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
		assert object != null;

		super.bind(object, "title", "abstractMessage", "learningTime", "body", "theoretical", "optionalUrl");
	}

	@Override
	public void validate(final Lecture object) {
		assert object != null;
		final boolean status = !object.isPublished();
		super.state(status, "*", "lecturer.lecture.update.not.in.draft");
	}
	@Override
	public void perform(final Lecture object) {
		assert object != null;

		this.repository.save(object);
		final Collection<LectureCourseAggregation> lcas = this.repository.findAllAggregationsOfLectureById(object.getId());
		for (final LectureCourseAggregation lca : lcas) {
			final Course c = lca.getCourse();
			final Collection<Lecture> lectures = this.repository.findAllLecturesOfCourse(c.getId());
			if (!lectures.isEmpty()) {
				final long theoryLectures = lectures.stream().filter(Lecture::isTheoretical).count();
				final double ratio = theoryLectures * 1.0 / lectures.size();
				if (0. >= ratio && ratio <= 0.40)
					c.setTypeOfCourse(Type.HANDS_ON);
				else if (0.40 < ratio && ratio <= 0.6)
					c.setTypeOfCourse(Type.EQUILIBRATED);
				else if (0.6 < ratio && ratio < 1.)
					c.setTypeOfCourse(Type.THEORETICAL);
				else if (ratio == 1.0)
					c.setTypeOfCourse(Type.PURELY_THEORETICAL);
			}
			this.repository.save(c);
		}
	}

	@Override
	public void unbind(final Lecture lecture) {
		assert lecture != null;
		Tuple tuple;
		tuple = super.unbind(lecture, "title", "abstractMessage", "learningTime", "body", "theoretical", "optionalUrl", "published");
		final Collection<Course> courses = this.repository.findAllCoursesOfLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		tuple.put("noCourses", courses.isEmpty());
		super.getResponse().setData(tuple);
	}
}
