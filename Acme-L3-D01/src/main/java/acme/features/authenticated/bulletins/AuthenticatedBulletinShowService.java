
package acme.features.authenticated.bulletins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedBulletinShowService extends AbstractService<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
		;
	}

	@Override
	public void authorise() {
		final boolean auth = super.getRequest().getPrincipal().isAuthenticated();
		super.getResponse().setAuthorised(auth);
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Bulletin b = this.repository.findBulletinById(id);
		super.getBuffer().setData(b);
	}

	@Override
	public void unbind(final Bulletin object) {
		final Tuple tuple;
		tuple = this.unbind(object, "title", "message", "optionalLink", "isCritical", "instantiationMomment");
		super.getResponse().setData(tuple);
	}

}
