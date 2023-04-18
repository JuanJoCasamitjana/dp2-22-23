
package acme.features.company.practicumSession;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionListService extends AbstractService<Company, PracticumSession> {

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
		int practicumId;
		Practicum practicum;

		practicumId = super.getRequest().getData("id", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = practicum != null && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<PracticumSession> objects;
		int practicumId;

		practicumId = super.getRequest().getData("id", int.class);
		objects = this.repository.findManyPracticumSessionByPracticumId(practicumId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractMessage", "periodStart", "periodEnd", "optionalLink");

		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<PracticumSession> object) {
		assert object != null;

		int practicumId;
		Practicum practicum;
		boolean status;
		boolean rolOk;

		practicumId = super.getRequest().getData("id", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = practicum != null && !practicum.isPublished() && rolOk;

		super.getResponse().setGlobal("practicumId", practicumId);
		super.getResponse().setGlobal("status", status);
	}

}
