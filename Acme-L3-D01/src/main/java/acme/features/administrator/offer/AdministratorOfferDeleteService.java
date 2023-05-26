
package acme.features.administrator.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offer.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorOfferDeleteService extends AbstractService<Administrator, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorOfferRepository repository;

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

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Offer object;
		int offerId;

		offerId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneOfferById(offerId);

		super.getBuffer().setData(object);

	}

	@Override
	public void bind(final Offer object) {
		assert object != null;

		Date ahora;

		ahora = new Date();

		super.bind(object, "heading", "summary", "periodStart", "periodEnd", "price");
		object.setInstantiationMomment(ahora);
	}

	@Override
	public void validate(final Offer object) {
		assert object != null;

	}

	@Override
	public void perform(final Offer object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final Offer object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "instantiationMomment", "heading", "summary", "periodStart", "periodEnd", "price");

		super.getResponse().setData(tuple);
	}
}
