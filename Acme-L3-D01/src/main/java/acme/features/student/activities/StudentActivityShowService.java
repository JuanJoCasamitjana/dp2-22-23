
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
public class StudentActivityShowService extends AbstractService<Student, Activity> {

	//Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityRepository repository;

	//AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final int id = super.getRequest().getData("id", int.class);
		final Activity act = this.repository.findActivityById(id);
		super.getResponse().setAuthorised(act.getEnrolment().getStudent().getId() == this.getRequest().getPrincipal().getActiveRoleId());

	}

	@Override
	public void load() {
		int activityId;
		Activity activity;

		activityId = super.getRequest().getData("id", int.class);
		activity = this.repository.findActivityById(activityId);

		super.getBuffer().setData(activity);
	}

	@Override
	public void unbind(final Activity act) {
		assert act != null;

		Tuple tuple;
		tuple = super.unbind(act, "title", "text", "periodStart", "periodEnd", "link");
		final Collection<Enrolment> enrolments = this.repository.findAllEnrolmentsOfStundetn(super.getRequest().getPrincipal().getActiveRoleId());

		final SelectChoices choices2 = SelectChoices.from(enrolments, "code", act.getEnrolment());
		tuple.put("enrolments", choices2);
		tuple.put("enrolment", choices2.getSelected().getKey());

		final SelectChoices choices = SelectChoices.from(Type.class, act.getType());
		tuple.put("types", choices);
		tuple.put("type", choices.getSelected().getKey());

		tuple.put("draft", act.getEnrolment().isDraft());
		super.getResponse().setData(tuple);
	}

}
