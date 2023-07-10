
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
		this.repository.deleteAll(aggregations);
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
