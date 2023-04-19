
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
		PracticumSession object;

		sessionId = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticumSessionById(sessionId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = object != null && !object.getPracticum().isPublished() && rolOk;

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

		super.bind(object, "code", "title", "abstractMessage", "goals", "estimatedTotalTime", "published");

	}

	@Override
	public void validate(final PracticumSession object) {
		assert object != null;
	}

	@Override
	public void perform(final PracticumSession object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final PracticumSession object) {
		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");

		super.getResponse().setData(tuple);
	}
}
