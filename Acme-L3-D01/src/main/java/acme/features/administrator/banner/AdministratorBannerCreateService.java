
package acme.features.administrator.banner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerCreateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Banner object;

		object = new Banner();
		object.setSlogan("");
		object.setPeriodStart(new Date());
		object.setPeriodEnd(new Date());
		object.setPictureLink("");
		object.setWebDocLink("");

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

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			final Date now = new Date();

			super.state(object.getPeriodStart().after(now), "periodStart", "company.practicum-session.form.error.periodStart");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodEnd"))
			super.state(object.getPeriodEnd().after(object.getPeriodStart()), "periodEnd", "company.practicum-session.form.error.periodEnd");

	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		object.setInstantiationOrUpdate(new Date());
		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		Tuple tuple;

		tuple = super.unbind(object, "slogan", "periodStart", "periodEnd", "pictureLink", "webDocLink");

		super.getResponse().setData(tuple);
	}
}
