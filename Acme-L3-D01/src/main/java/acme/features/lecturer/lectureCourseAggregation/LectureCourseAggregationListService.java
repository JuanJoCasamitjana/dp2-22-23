
package acme.features.lecturer.lectureCourseAggregation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LectureCourseAggregationListService extends AbstractService<Lecturer, LectureCourseAggregation> {

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
		final int lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		final Collection<LectureCourseAggregation> courses = this.repository.findAllAggregationOfLecturer(lecturerId);
		super.getBuffer().setData(courses);
	}

	@Override
	public void unbind(final LectureCourseAggregation object) {
		final Tuple tuple = super.unbind(object, "serialVersionUID");
		tuple.put("course", object.getCourse().getCode());
		tuple.put("lecture", object.getLecture().getTitle());
		super.getResponse().setData(tuple);
	}
}
