
package acme.features.company.practicumSession;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicum.PracticumSession;
import acme.framework.components.models.Tuple;
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

		if (!super.getBuffer().getErrors().hasErrors("title")) {
			PracticumSession existing;

			existing = this.repository.findOnePracticumSessionByTitle(object.getTitle());
			super.state(existing == null || existing.equals(object), "title", "company.practicum-session.form.error.duplicated");
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
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");

		super.getResponse().setData(tuple);
	}
}
