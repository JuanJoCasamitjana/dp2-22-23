
package acme.features.any.courses;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyCoursesListService extends AbstractService<Any, Course> {

	@Autowired
	protected AnyCoursesRepository repository;


	@Override
	public void check() {
		final boolean status = true;
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		//Anyone can see courses
		final boolean status = true;
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		final Collection<Course> courses = this.repository.findAllCourses();
		super.getBuffer().setData(courses);
	}

	@Override
	public void unbind(final Course course) {
		assert course != null;
		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl", "lecturer", "inDraft");
		super.getResponse().setData(tuple);
	}
}
