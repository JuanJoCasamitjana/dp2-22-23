
package acme.features.lecturer.lectureCourseAggregation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LectureCourseAggregationShowService extends AbstractService<Lecturer, LectureCourseAggregation> {

	@Autowired
	protected LectureCourseAggregationRepository repository;


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		boolean status;
		final int roleId = super.getRequest().getPrincipal().getActiveRoleId();
		final int id = super.getRequest().getData("id", int.class);
		final int lecturerId = this.repository.findOneAggregationById(id).getLecture().getLecturer().getId();
		status = roleId == lecturerId;
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final LectureCourseAggregation aggregation = this.repository.findOneAggregationById(id);
		super.getBuffer().setData(aggregation);
	}

	@Override
	public void unbind(final LectureCourseAggregation object) {
		assert object != null;
		final Collection<Course> courses = this.repository.findAllCourses();
		final SelectChoices coursesChoices = SelectChoices.from(courses, "code", object.getCourse());
		final Tuple tuple = super.unbind(object, "serialVersionUID");
		tuple.put("courses", coursesChoices);
		tuple.put("lecture", object.getLecture().getTitle());
		super.getResponse().setData(tuple);
	}
}