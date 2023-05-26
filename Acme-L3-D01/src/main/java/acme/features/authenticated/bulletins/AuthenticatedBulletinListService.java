
package acme.features.authenticated.bulletins;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedBulletinListService extends AbstractService<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		final boolean v = super.getRequest().getPrincipal().isAuthenticated();
		super.getResponse().setAuthorised(v);
	}

	@Override
	public void load() {
		Collection<Bulletin> bulletins;
		bulletins = this.repository.findAllBulletins();
		super.getBuffer().setData(bulletins);
	}

	@Override
	public void unbind(final Bulletin object) {
		final Tuple tuple;
		tuple = this.unbind(object, "title");
		super.getResponse().setData(tuple);

	}

}
