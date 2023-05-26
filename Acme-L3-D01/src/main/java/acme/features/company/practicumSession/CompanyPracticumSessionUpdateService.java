
package acme.features.company.practicumSession;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicum.PracticumSession;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionUpdateService extends AbstractService<Company, PracticumSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionRepository repository;

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
		int sessionId;
		PracticumSession practicumSession;

		sessionId = super.getRequest().getData("id", int.class);
		practicumSession = this.repository.findOnePracticumSessionById(sessionId);
		rolOk = super.getRequest().getPrincipal().hasRole(practicumSession.getPracticum().getCompany());
		status = practicumSession != null && !practicumSession.getPracticum().isPublished() && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		PracticumSession object;
		int sessionId;

		sessionId = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticumSessionById(sessionId);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final PracticumSession object) {

		super.bind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");
	}

	@Override
	public void validate(final PracticumSession object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			Date ahora;
			double diferencia;
			long periodStart;
			long diff;

			diferencia = 0.0;
			ahora = MomentHelper.getCurrentMoment();
			periodStart = object.getPeriodStart().getTime();
			diff = periodStart - ahora.getTime();
			if (diff > 0)
				diferencia = diff / (1000.0 * 60 * 60);

			super.state(diferencia >= 7.0 * 24, "periodStart", "company.practicum-session.form.error.period-start");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodEnd")) {
			double diferencia;
			long periodStart;
			long periodEnd;
			long diff;

			diferencia = 0.0;
			periodStart = object.getPeriodStart().getTime();
			periodEnd = object.getPeriodEnd().getTime();
			diff = periodEnd - periodStart;
			if (diff > 0)
				diferencia = diff / (1000.0 * 60 * 60);

			super.state(diferencia >= 7 * 24, "periodEnd", "company.practicum-session.form.error.period-end");
		}
	}

	@Override
	public void perform(final PracticumSession object) {
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

		suma = this.repository.sumOfPracticumSessionTimeByPracticumId(object.getPracticum().getId()).orElse(0.0);

		object.getPracticum().setEstimatedTotalTime(suma);
		this.repository.save(object.getPracticum());
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;

		Tuple tuple;
		boolean isPublished;

		isPublished = object.getPracticum().isPublished();

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "totalTime", "optionalLink", "addendum");
		tuple.put("isPublished", isPublished);

		super.getResponse().setData(tuple);
	}
}
