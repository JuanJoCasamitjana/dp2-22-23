
package acme.features.any.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
import acme.framework.components.accounts.Any;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyCourseShowService extends AbstractService<Any, Course> {

	@Autowired
	protected AnyCourseRepository repository;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		int id;
		id = super.getRequest().getData("id", int.class);
		final Course course = this.repository.findCourseById(id);
		super.getResponse().setAuthorised(!course.isInDraft());
	}

	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		final Course course = this.repository.findCourseById(id);
		super.getBuffer().setData(course);
	}

	@Override
	public void unbind(final Course course) {
		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl", "lecturer");
		final SelectChoices choices = SelectChoices.from(Type.class, course.getTypeOfCourse());
		tuple.put("types", choices);
		super.getResponse().setData(tuple);
	}
}
