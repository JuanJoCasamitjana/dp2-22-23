
package acme.features.student.course;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentCourseShowService extends AbstractService<Student, Course> {

	//Internal state ---------------------------------------------------------

	@Autowired
	protected StudentCourseRepository repository;

	//AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Course course;
		int id;

		id = super.getRequest().getData("id", int.class);
		course = this.repository.findCourseById(id);

		final Boolean authorise = !course.isInDraft();
		super.getResponse().setAuthorised(authorise);
	}

	@Override
	public void load() {
		Course course;
		int id;

		id = super.getRequest().getData("id", int.class);
		course = this.repository.findCourseById(id);

		super.getBuffer().setData(course);
	}

	@Override
	public void unbind(final Course course) {
		assert course != null;

		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl", "lecturer", "inDraft");
		final SelectChoices choices = SelectChoices.from(Type.class, course.getTypeOfCourse());
		super.getResponse().setData(tuple);

		final int id = course.getId();
		final Collection<LectureCourseAggregation> mapper = this.repository.findCourseLectureByCourseId(id);
		final List<String> lectures = mapper.stream().map(m -> m.getLecture().getTitle()).collect(Collectors.toList());

		final String lecturer = course.getLecturer().getAlmaMater();
		tuple.put("types", choices);
		tuple.put("lectures", lectures);
		tuple.put("lecturer", lecturer);

		super.getResponse().setData(tuple);
	}

}
