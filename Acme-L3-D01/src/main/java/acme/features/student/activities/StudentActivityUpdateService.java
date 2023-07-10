
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
public class StudentActivityUpdateService extends AbstractService<Student, Activity> {

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
		final Integer aId = super.getRequest().getData("id", int.class);
		final Activity act = this.repository.findActivityById(aId);
		final boolean auth = super.getRequest().getPrincipal().getActiveRoleId() == act.getEnrolment().getStudent().getId();
		final boolean draft = act.getEnrolment().isDraft();
		super.getResponse().setAuthorised(auth && !draft);
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
	public void bind(final Activity object) {

		final int enrolmentId = Integer.valueOf(super.getRequest().getData("enrolment_proxy", String.class));
		final Enrolment enrolment = this.repository.findEnrolmentById(enrolmentId);
		object.setEnrolment(enrolment);

		super.bind(object, "title", "text", "type", "periodStart", "periodEnd", "link");

	}

	@Override
	public void validate(final Activity object) {

		if (!super.getBuffer().getErrors().hasErrors("periodEnd") && !super.getBuffer().getErrors().hasErrors("periodStart")) {
			final boolean correct = object.getPeriodStart().before(object.getPeriodEnd());
			super.state(correct, "periodStart", "student.activity.error.create");

		}

		if (object.getEnrolment() == null) // Validación manual dado que posteriormente
											//  se llama en una fución.
			super.state(true, "enrolment", "May not be null");

	}

	@Override
	public void perform(final Activity object) {
		long periodStart;
		long periodEnd;
		long diff;
		double total;
		int horas;
		int minutos;
		double res;

		total = 0.0;
		periodStart = object.getPeriodStart().getTime();
		periodEnd = object.getPeriodEnd().getTime();
		diff = periodEnd - periodStart;
		if (diff > 0)
			total = diff / (1000.0 * 60 * 60);

		horas = (int) total;
		minutos = (int) ((total - horas) * 60);
		res = Double.parseDouble(horas + "." + minutos);

		object.setTotalTime(res);

		//###############################

		double suma;

		suma = this.repository.sumOfActivityTime(object.getEnrolment().getId()).orElse(0.0);

		object.getEnrolment().setWorkTime(suma);
		this.repository.save(object.getEnrolment());

		this.repository.save(object);
	}

	@Override
	public void unbind(final Activity act) {

		Tuple tuple;
		tuple = super.unbind(act, "title", "text", "type", "periodStart", "periodEnd", "link");
		final Collection<Enrolment> enrolments = this.repository.findAllEnrolmentsOfStundetn(super.getRequest().getPrincipal().getActiveRoleId());
		final SelectChoices choices2 = SelectChoices.from(enrolments, "code", act.getEnrolment());
		tuple.put("enrolments", choices2);
		tuple.put("enrolment", choices2.getSelected().getKey());

		final SelectChoices choices = SelectChoices.from(Type.class, act.getType());
		tuple.put("types", choices);

		if (act.getEnrolment() != null) // Validación manual
			tuple.put("draft", act.getEnrolment().isDraft());

		super.getResponse().setData(tuple);
	}

}
