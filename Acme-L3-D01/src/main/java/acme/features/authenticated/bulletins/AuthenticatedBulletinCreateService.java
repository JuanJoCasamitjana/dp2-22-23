
package acme.features.authenticated.bulletins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedBulletinCreateService extends AbstractService<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		final boolean auth = super.getRequest().getPrincipal().isAuthenticated();
		super.getResponse().setAuthorised(auth);
	}

	@Override
	public void bind(final Bulletin bulletin) {
		assert bulletin != null;
		super.bind(bulletin, "title", "message", "optionalLink", "instantiationMomment");
	}

	@Override
	public void perform(final Bulletin bulletin) {
		assert bulletin != null;
		final boolean critical = super.getRequest().getData("isCritical", boolean.class);
		bulletin.setCritical(critical);
		this.repository.save(bulletin);
	}
	@Override
	public void validate(final Bulletin object) {
		assert object != null;
		final boolean checked = super.getRequest().getData("check", boolean.class);
		super.state(checked, "check", "Necesita Confirmación Previa");

		//		final Date instantiationMomment = object.getInstantiationMomment();
		//
		//		super.state(instantiationMomment == null, "instantiationMomment", "No puede ser nulo");
		//		final Date hoy = new Date();
		//		final boolean correcta = instantiationMomment.compareTo(hoy) <= 0 ? true : false;
		//
		//		super.state(correcta, "instantiationMomment", "No puede ser una fecha futura");
	}

	@Override
	public void load() {
		final Bulletin bulletin = new Bulletin();
		super.getBuffer().setData(bulletin);
	}

	@Override
	public void unbind(final Bulletin bulletin) {
		final Tuple tuple;
		final boolean check = false;
		tuple = this.unbind(bulletin, "title", "message", "optionalLink", "isCritical", "instantiationMomment");
		tuple.put("check", check);
		super.getResponse().setData(tuple);
	}

}
