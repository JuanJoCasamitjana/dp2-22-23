
package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumDeleteService extends AbstractService<Company, Practicum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean rolOk;
		int practicumId;
		Practicum practicum;

		practicumId = super.getRequest().getData("id", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = practicum != null && !practicum.isPublished() && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practicum objects;
		int practicumId;

		practicumId = super.getRequest().getData("id", int.class);
		objects = this.repository.findOnePracticumById(practicumId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void bind(final Practicum object) {
		assert object != null;

		int courseId;
		Course course;

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);

		super.bind(object, "code", "title", "abstractMessage", "goals", "estimatedTotalTime", "published", "company", "course");

		object.setCourse(course);
	}

	@Override
	public void validate(final Practicum object) {
		assert object != null;
	}

	@Override
	public void perform(final Practicum object) {
		assert object != null;

		Collection<PracticumSession> practicumSession;

		practicumSession = this.repository.findManyPracticumSessionByPracticumId(object.getId());
		this.repository.deleteAll(practicumSession);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final Practicum object) {
		assert object != null;

		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;

		courses = this.repository.findAllCourse();
		choices = SelectChoices.from(courses, "title", object.getCourse());
		tuple = super.unbind(object, "code", "title", "abstractMessage", "goals", "estimatedTotalTime", "published");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
