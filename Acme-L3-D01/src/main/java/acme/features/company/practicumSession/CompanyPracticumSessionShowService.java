
package acme.features.company.practicumSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicum.PracticumSession;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionShowService extends AbstractService<Company, PracticumSession> {

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
		int practicumSessionId;
		PracticumSession practicumSession;
		boolean rolOk;

		practicumSessionId = super.getRequest().getData("id", int.class);
		practicumSession = this.repository.findOnePracticumSessionById(practicumSessionId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = practicumSession != null && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		PracticumSession objects;
		int sessionId;

		sessionId = super.getRequest().getData("id", int.class);
		objects = this.repository.findOnePracticumSessionById(sessionId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");

		super.getResponse().setData(tuple);
	}

}
