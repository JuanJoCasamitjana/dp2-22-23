
package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.practicum.Practicum;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumCreateService extends AbstractService<Company, Practicum> {

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

		status = super.getRequest().getPrincipal().hasRole(Company.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practicum object;
		Company company;

		company = this.repository.findOneCompanyById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Practicum();
		object.setTitle("");
		object.setAbstractMessage("");
		object.setGoals("");
		object.setEstimatedTotalTime(0.0);
		object.setPublished(false);
		object.setCompany(company);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Practicum object) {
		assert object != null;

		int courseId;
		Course course;

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);

		super.bind(object, "code", "title", "abstractMessage", "estimatedTotalTime", "goals", "published", "company.name", "course");

		object.setCourse(course);
	}

	@Override
	public void validate(final Practicum object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Practicum existing;

			existing = this.repository.findOnePracticumByCode(object.getCode());
			super.state(existing == null, "code", "company.practicum.form.error.duplicated");
		}
	}

	@Override
	public void perform(final Practicum object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Practicum object) {
		assert object != null;

		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;

		courses = this.repository.findAllCourse();
		choices = SelectChoices.from(courses, "title", object.getCourse());
		tuple = super.unbind(object, "code", "title", "abstractMessage", "goals", "estimatedTotalTime", "published", "company.name");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
