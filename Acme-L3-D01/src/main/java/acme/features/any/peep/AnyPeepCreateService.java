
package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peep.Peep;
import acme.framework.components.accounts.Any;
import acme.framework.components.accounts.UserAccount;
import acme.framework.components.models.Tuple;
import acme.framework.controllers.HttpMethod;
import acme.framework.helpers.MomentHelper;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractService;

@Service
public class AnyPeepCreateService extends AbstractService<Any, Peep> {

	@Autowired
	protected AnyPeepRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}
	@Override
	public void load() {
		final Peep object = new Peep();
		final Date moment = MomentHelper.getCurrentMoment();
		object.setInstantiation(moment);
		if (super.getRequest().getPrincipal().isAuthenticated()) {
			String username;
			int id;
			UserAccount ua;
			id = super.getRequest().getPrincipal().getAccountId();
			ua = this.repository.findUserAccountById(id);
			username = ua.getIdentity().getFullName();
			object.setNick(username);
		}
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Peep object) {
		assert object != null;
		final Date moment = MomentHelper.getCurrentMoment();
		super.bind(object, "title", "nick", "message", "mail", "link");
		object.setInstantiation(moment);
		if (object.getNick().isEmpty())
			if (!super.getRequest().getPrincipal().isAnonymous()) {
				final int accountId = super.getRequest().getPrincipal().getAccountId();
				final UserAccount account = this.repository.findUserAccountById(accountId);
				final String nick = account.getIdentity().getFullName();
				object.setNick(nick);
			}
	}

	@Override
	public void validate(final Peep object) {
		assert object != null;
	}

	@Override
	public void perform(final Peep object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Peep object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "instantiation", "title", "nick", "message", "mail", "link");
		super.getResponse().setData(tuple);
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals(HttpMethod.POST))
			PrincipalHelper.handleUpdate();
	}

}
