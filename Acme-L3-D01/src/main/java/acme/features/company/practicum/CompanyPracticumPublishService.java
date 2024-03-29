
package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumPublishService extends AbstractService<Company, Practicum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean rolOk;
		int practicumId;
		Practicum practicum;

		practicumId = super.getRequest().getData("id", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		rolOk = super.getRequest().getPrincipal().hasRole(practicum.getCompany());
		status = practicum != null && !practicum.isPublished() && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practicum object;
		int practicumId;

		practicumId = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticumById(practicumId);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Practicum object) {
		int courseId;
		Course course;

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);

		super.bind(object, "code", "title", "abstractMessage", "goals");
		object.setCourse(course);
	}

	@Override
	public void validate(final Practicum object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Practicum p;
			p = this.repository.findOnePracticumByCode(object.getCode());
			super.state(p == null || p.equals(object), "code", "company.practicum.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("course"))
			super.state(object.getCourse().getTypeOfCourse() == Type.HANDS_ON, "course", "company.practicum.form.error.nothandson");

		if (!super.getBuffer().getErrors().hasErrors("published"))
			super.state(!object.isPublished(), "published", "company.practicum.form.error.published");

		if (!super.getBuffer().getErrors().hasErrors("published")) {
			Collection<PracticumSession> sessions;
			sessions = this.repository.findManyPracticumSessionByPracticumId(object.getId());
			super.state(!sessions.isEmpty(), "published", "company.practicum.form.error.empty");
		}

	}

	@Override
	public void perform(final Practicum object) {
		assert object != null;

		object.setPublished(true);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Practicum object) {
		assert object != null;

		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;

		courses = this.repository.findManyHandsOnPublishedCourse();
		choices = SelectChoices.from(courses, "title", object.getCourse());
		tuple = super.unbind(object, "code", "title", "abstractMessage", "goals", "estimatedTotalTime", "published");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
