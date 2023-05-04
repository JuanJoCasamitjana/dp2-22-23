
package acme.features.student.activities;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.activity.Activity.Type;
import acme.entities.enrolment.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentActivityCreateService extends AbstractService<Student, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Student.class));
	}

	@Override
	public void load() {
		final Activity activity = new Activity();

		super.getBuffer().setData(activity);
	}
	@Override
	public void bind(final Activity object) {
		assert object != null;
		final int enrolmentId = super.getRequest().getData("enrolment", int.class);
		final Enrolment enrolment = this.repository.findEnrolmentById(enrolmentId);
		object.setEnrolment(enrolment);

		super.bind(object, "title", "text", "type", "periodStart", "periodEnd", "link");

	}

	@Override
	public void validate(final Activity object) {
		assert object != null;

	}

	@Override
	public void perform(final Activity object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Activity act) {
		assert act != null;
		Tuple tuple;
		tuple = super.unbind(act, "title", "text", "type", "periodStart", "periodEnd", "link");
		final Collection<Enrolment> enrolments = this.repository.findAllEnrolmentsOfStundetn(super.getRequest().getPrincipal().getActiveRoleId());
		final SelectChoices choices2 = SelectChoices.from(enrolments, "code", act.getEnrolment());
		tuple.put("enrolments", choices2);
		tuple.put("enrolment", choices2.getSelected().getKey());

		final SelectChoices choices = SelectChoices.from(Type.class, act.getType());
		tuple.put("types", choices);
		tuple.put("type", choices.getSelected().getKey());

		super.getResponse().setData(tuple);
	}

}
