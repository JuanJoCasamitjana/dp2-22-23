
package acme.features.administrator.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offer.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AdministratorOfferUpdateService extends AbstractService<Administrator, Offer> {

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

		ahora = MomentHelper.getBaseMoment();

		super.bind(object, "heading", "summary", "periodStart", "periodEnd", "price");
		object.setInstantiationMomment(ahora);
	}

	@Override
	public void validate(final Offer object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("heading")) {
			Offer o;
			o = this.repository.findOneOfferByHeading(object.getHeading());
			super.state(o == null || o.equals(object), "heading", "administrator.offer.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("price"))
			super.state(object.getPrice().getAmount() >= 0.0, "price", "administrator.offer.form.error.negative-price");

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			double diferencia;
			long instantiation;
			long periodStart;
			long diff;

			diferencia = 0.0;
			instantiation = object.getInstantiationMomment().getTime();
			periodStart = object.getPeriodStart().getTime();
			diff = periodStart - instantiation;
			if (diff > 0)
				diferencia = diff / (1000.0 * 60 * 60);

			super.state(diferencia >= 1 * 24, "periodStart", "administrator.offer.form.error.period-start");
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

			super.state(diferencia >= 7 * 24, "periodEnd", "administrator.offer.form.error.period-end");
		}
	}

	@Override
	public void perform(final Offer object) {
		assert object != null;

		Date ahora;

		ahora = MomentHelper.getBaseMoment();

		object.setInstantiationMomment(ahora);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Offer object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "instantiationMomment", "heading", "summary", "periodStart", "periodEnd", "price");

		super.getResponse().setData(tuple);
	}
}
