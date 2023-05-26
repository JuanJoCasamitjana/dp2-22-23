
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseDeleteService extends AbstractService<Lecturer, Course> {

	@Autowired
	protected LecturerCourseRepository repository;


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		boolean status;
		final int roleId = super.getRequest().getPrincipal().getActiveRoleId();
		final int courseId = super.getRequest().getData("id", int.class);
		final int lecturerId = this.repository.findOneCourseById(courseId).getLecturer().getId();
		status = roleId == lecturerId;
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl");
	}
	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Course courses = this.repository.findOneCourseById(id);
		super.getBuffer().setData(courses);
	}
	@Override
	public void validate(final Course object) {
		assert object != null;
		final boolean status = object.isInDraft();
		super.state(status, "*", "lecturer.course.delete.not.in.draft");
	}

	@Override
	public void perform(final Course object) {
		assert object != null;
		final Collection<LectureCourseAggregation> aggregations = this.repository.findAllAggregationsOfCourseById(object.getId());
		for (final LectureCourseAggregation lca : aggregations)
			this.repository.delete(lca);
		//		final Collection<Audit> audits = this.repository.findAuditsOfCourse(object.getId());
		//		for (final Audit a : audits) {
		//			final Collection<AuditingRecord> records = this.repository.findAllRecordsOfRecord(a.getId());
		//			for (final AuditingRecord ar : records)
		//				this.repository.delete(ar);
		//			this.repository.delete(a);
		//		}
		//		final Collection<Enrolment> enrolments = this.repository.findEnrolmentsOfCourse(object.getId());
		//		for (final Enrolment e : enrolments) {
		//			final Collection<Activity> activities = this.repository.findActivitiesOfEnrolment(object.getId());
		//			for (final Activity a : activities)
		//				this.repository.delete(a);
		//			this.repository.delete(e);
		//		}
		//
		//		final Collection<Practicum> practs = this.repository.findPracticumsOfCourse(object.getId());
		//		for (final Practicum p : practs) {
		//			final Collection<PracticumSession> sessions = this.repository.findAllSessionsOfPracticum(p.getId());
		//			for (final PracticumSession s : sessions)
		//				this.repository.delete(s);
		//			this.repository.delete(p);
		//		}
		//		final Collection<Tutorial> tutorials = this.repository.findTutorialsOfCourse(object.getId());
		//		for (final Tutorial t : tutorials) {
		//			final Collection<TutorialSession> sessions = this.repository.findAllSessionsOfTutorial(t.getId());
		//			for (final TutorialSession s : sessions)
		//				this.repository.delete(s);
		//			this.repository.delete(t);
		//		}
		this.repository.delete(object);
	}

	@Override
	public void unbind(final Course course) {
		assert course != null;
		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl", "lecturer", "inDraft");
		final SelectChoices choices = SelectChoices.from(Type.class, course.getTypeOfCourse());
		tuple.put("types", choices);
		super.getResponse().setData(tuple);
	}
}
