
package acme.features.administrator.banner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerUpdateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBannerRepository repository;

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
		int bannerId;
		Banner object;

		bannerId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneBannerById(bannerId);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "slogan", "periodStart", "periodEnd", "pictureLink", "webDocLink");

	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("slogan")) {
			Banner b;
			b = this.repository.findOneBannerBySlogan(object.getSlogan());
			super.state(b == null || b.equals(object), "slogan", "administrator.banner.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			Date instantiation;
			Date periodStart;

			instantiation = object.getInstantiation();
			periodStart = object.getPeriodStart();

			super.state(instantiation.before(periodStart), "periodStart", "administrator.banner.form.error.period-start");
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
				diferencia = diff / (1000.0 * 60);

			super.state(diferencia >= 7 * 24 * 60, "periodEnd", "administrator.banner.form.error.period-end");
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		Date ahora;

		ahora = new Date();

		object.setInstantiation(ahora);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "slogan", "instantiation", "periodStart", "periodEnd", "pictureLink", "webDocLink");

		super.getResponse().setData(tuple);
	}

}
