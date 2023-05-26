
package acme.features.student.activities;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.activity.Activity.Type;
import acme.entities.enrolment.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
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
		activity.setPeriodStart(MomentHelper.getCurrentMoment());
		activity.setPeriodEnd(new Date());
		activity.setTotalTime(0.0);
		super.getBuffer().setData(activity);
	}
	@Override
	public void bind(final Activity object) {
		assert object != null;
		final Collection<Enrolment> enrolments = this.repository.findAllEnrolmentsOfStundetn(super.getRequest().getPrincipal().getActiveRoleId());
		if (enrolments.size() != 0) {
			final int enrolmentId = super.getRequest().getData("enrolment_proxy", int.class);
			final Enrolment enrolment = this.repository.findEnrolmentById(enrolmentId);
			object.setEnrolment(enrolment);
		} else
			super.state(false, "enrolment", "No existe ningun enrolment");

		super.bind(object, "title", "text", "type", "periodStart", "periodEnd", "link");

	}

	@Override
	public void validate(final Activity object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("type"))
			super.state(object.getType() != null, "type", "Tipo no puede ser nulo");

		if (!super.getBuffer().getErrors().hasErrors("periodEnd")) {
			final boolean correct = object.getPeriodStart().before(object.getPeriodEnd());
			super.state(correct, "periodStart", "student.activity.error.create");

		}

	}

	@Override
	public void perform(final Activity object) {
		assert object != null;
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
		this.repository.save(object);

		//###############################

		double suma;

		suma = this.repository.sumOfActivityTime(object.getEnrolment().getId()).orElse(0.0);

		object.getEnrolment().setWorkTime(suma);
		this.repository.save(object.getEnrolment());

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
