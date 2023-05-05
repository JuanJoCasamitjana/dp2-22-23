
package acme.features.company.practicumSession;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionCreateService extends AbstractService<Company, PracticumSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("practicumId", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean rolOk;
		int practicumId;
		Practicum practicum;
		Collection<PracticumSession> sesiones;
		boolean hasAddendum;

		practicumId = super.getRequest().getData("practicumId", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		sesiones = this.repository.findAddendumSessionByPracticumId(practicumId);

		rolOk = super.getRequest().getPrincipal().hasRole(practicum.getCompany());
		hasAddendum = !practicum.isPublished() || practicum.isPublished() && sesiones.isEmpty();
		status = practicum != null && rolOk && hasAddendum;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		PracticumSession object;
		int practicumId;
		Practicum practicum;

		practicumId = super.getRequest().getData("practicumId", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		object = new PracticumSession();
		object.setTitle("");
		object.setAbstractMessage("");
		object.setPeriodStart(new Date());
		object.setPeriodEnd(new Date());
		object.setTotalTime(0.0);
		object.setOptionalLink("");
		object.setPracticum(practicum);
		object.setAddendum(practicum.isPublished());

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final PracticumSession object) {
		assert object != null;

		super.bind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");
	}

	@Override
	public void validate(final PracticumSession object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("title")) {
			PracticumSession p;
			p = this.repository.findOnePracticumSessionByTitle(object.getTitle());
			super.state(p == null || p.equals(object), "title", "company.practicum-session.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("confirm") && object.getPracticum().isPublished()) {

			boolean confirmed;

			confirmed = super.getRequest().getData("confirm", boolean.class);

			super.state(confirmed, "confirm", "company.practicum-session.form.error.not-confirmed");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			Date ahora;
			double diferencia;
			long periodStart;
			long diff;

			diferencia = 0.0;
			ahora = new Date();
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
		Tuple tuple;
		int practicumId;
		boolean isAddendum;

		practicumId = super.getRequest().getData("practicumId", int.class);
		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "totalTime", "optionalLink", "addendum");
		isAddendum = object.isAddendum();

		tuple.put("practicumId", practicumId);
		tuple.put("isAddendum", isAddendum);

		super.getResponse().setData(tuple);
	}
}
