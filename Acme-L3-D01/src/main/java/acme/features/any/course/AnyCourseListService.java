
package acme.features.any.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyCourseListService extends AbstractService<Any, Course> {

	@Autowired
	protected AnyCourseRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final Collection<Course> courses = this.repository.findPublishedCourses();
		super.getBuffer().setData(courses);
	}
	@Override
	public void unbind(final Course course) {
		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "retailPrice");
		super.getResponse().setData(tuple);
	}
}
