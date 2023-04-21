
package acme.features.company.practicumSession;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

		practicumId = super.getRequest().getData("practicumId", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		rolOk = super.getRequest().getPrincipal().hasRole(practicum.getCompany());
		status = practicum != null && rolOk;

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
		object.setOptionalLink("");
		object.setPracticum(practicum);

		if (practicum.isPublished())
			object.setAddendum(true);
		else
			object.setAddendum(false);

		object.setConfirmed(false);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final PracticumSession object) {
		assert object != null;

		super.bind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink", "confirmed");
	}

	@Override
	public void validate(final PracticumSession object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("title")) {
			PracticumSession existing;

			existing = this.repository.findOnePracticumSessionByTitle(object.getTitle());
			super.state(existing == null, "title", "company.practicum-session.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			final Date now = new Date();
			long milisegundosStart;
			long miliseguntosEnd;
			long diff;
			long diffDias;

			milisegundosStart = now.getTime();
			miliseguntosEnd = object.getPeriodStart().getTime();
			diff = miliseguntosEnd - milisegundosStart;
			diffDias = 0;

			if (diff > 0)
				diffDias = TimeUnit.MILLISECONDS.toDays(diff);

			super.state(diffDias >= 7, "periodStart", "company.practicum-session.form.error.periodStart");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodEnd")) {
			long milisegundosStart;
			long miliseguntosEnd;
			long diff;
			long diffDias;

			milisegundosStart = object.getPeriodStart().getTime();
			miliseguntosEnd = object.getPeriodEnd().getTime();
			diff = miliseguntosEnd - milisegundosStart;
			diffDias = 0;

			if (diff > 0)
				diffDias = TimeUnit.MILLISECONDS.toDays(diff);

			super.state(diffDias >= 7, "periodEnd", "company.practicum-session.form.error.periodEnd");
		}
	}

	@Override
	public void perform(final PracticumSession object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final PracticumSession object) {
		Tuple tuple;
		int practicumId;

		practicumId = super.getRequest().getData("practicumId", int.class);
		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");

		tuple.put("practicumId", practicumId);

		super.getResponse().setData(tuple);
	}
}
