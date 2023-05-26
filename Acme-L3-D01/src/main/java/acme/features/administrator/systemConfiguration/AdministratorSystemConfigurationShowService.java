
package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.system.configuration.SystemConfiguration;

@Service
public class AdministratorSystemConfigurationShowService extends AbstractService<Administrator, SystemConfiguration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

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
		SystemConfiguration object;

		object = this.repository.findCurrentSystemConfiguration();

		super.getBuffer().setData(object);

	}

	@Override
	public void unbind(final SystemConfiguration object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "defaultSystemCurrency", "acceptedCurrencies");

		super.getResponse().setData(tuple);
	}
}
