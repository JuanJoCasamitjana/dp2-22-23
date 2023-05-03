
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
		rolOk = super.getRequest().getPrincipal().hasRole(practicumSession.getPracticum().getCompany());
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
		boolean isPublished;
		boolean isAddendum;
		int practicumId;

		practicumId = object.getPracticum().getId();
		isPublished = object != null && object.getPracticum().isPublished();
		isAddendum = object.isAddendum();

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "totalTime", "optionalLink", "addendum", "confirmed");

		tuple.put("practicumId", practicumId);
		tuple.put("isPublished", isPublished);
		tuple.put("isAddendum", isAddendum);

		super.getResponse().setData(tuple);
	}

}
