
package acme.features.company.practicumSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicum.PracticumSession;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionDeleteService extends AbstractService<Company, PracticumSession> {

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
		int practicumId;

		practicumId = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticumSessionById(practicumId);

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
	}

	@Override
	public void perform(final PracticumSession object) {
		assert object != null;

		this.repository.delete(object);

		double suma;

		suma = this.repository.sumOfPracticumSessionTimeByPracticumId(object.getPracticum().getId()).orElse(0.0);

		object.getPracticum().setEstimatedTotalTime(suma);
		this.repository.save(object.getPracticum());
	}

	@Override
	public void unbind(final PracticumSession object) {
		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "totalTime", "optionalLink", "addendum", "confirmed");

		super.getResponse().setData(tuple);
	}
}
