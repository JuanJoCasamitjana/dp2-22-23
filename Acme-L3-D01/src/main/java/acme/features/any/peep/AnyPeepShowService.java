
package acme.features.any.peep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peep.Peep;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyPeepShowService extends AbstractService<Any, Peep> {

	@Autowired
	protected AnyPeepRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}
	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}
	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Peep object = this.repository.findOnePeepById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Peep object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "instantiation", "title", "nick", "message", "mail", "link");
		super.getResponse().setData(tuple);
	}
}
