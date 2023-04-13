
package acme.features.any.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Anonymous;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.accounts.DefaultUserIdentity;
import acme.framework.components.accounts.UserAccount;
import acme.framework.components.models.Tuple;
import acme.framework.controllers.HttpMethod;
import acme.framework.helpers.UserIdentityHelper;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class AnonymousCompanyCreateService extends AbstractService<Anonymous, Company> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousCompanyRepository repository;

	// AbstractService interface ----------------------------------------------


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
		UserAccount userAccount;
		DefaultUserIdentity identity;
		Authenticated authenticatedRole;

		userAccount = new UserAccount();
		userAccount.setEnabled(true);
		identity = UserIdentityHelper.createBlankIdentity();
		userAccount.setIdentity(identity);
		authenticatedRole = new Authenticated();
		userAccount.addRole(authenticatedRole);
		authenticatedRole.setUserAccount(userAccount);

		super.getBuffer().setData(userAccount);
	}

	@Override
	public void bind(final Company object) {
		String[] properties;

		properties = UserIdentityHelper.computeProperties("username", "password");
		super.bind(object, properties);
	}

	@Override
	public void validate(final Company object) {
		assert object != null;

		boolean isDuplicated, isAccepted, isMatching;
		String password, confirmation;
		int passwordLength;

		isDuplicated = this.repository.findOneCompanyByName(object.getName()) != null;
		super.state(!isDuplicated, "name", "anonymous.user-account.form.error.duplicated");

		passwordLength = super.getRequest().getData("password", String.class).length();
		super.state(passwordLength >= 5 && passwordLength <= 60, "password", "acme.validation.length", 6, 60);

		isAccepted = this.getRequest().getData("accept", boolean.class);
		super.state(isAccepted, "accept", "anonymous.user-account.form.error.must-accept");

		password = this.getRequest().getData("password", String.class);
		confirmation = this.getRequest().getData("confirmation", String.class);
		isMatching = password.equals(confirmation);
		super.state(isMatching, "confirmation", "anonymous.user-account.form.error.confirmation-no-match");
	}

	//	@Override
	//	public void perform(final Company object) {
	//		assert object != null;
	//
	//		this.repository.save(object);
	//		for (final AbstractRole role : object.getRoles())
	//			if (!role.isVirtual())
	//				this.repository.save(role);
	//	}

	@Override
	public void unbind(final Company object) {
		assert object != null;

		Tuple record;
		String[] properties;

		properties = UserIdentityHelper.computeProperties("username");
		record = super.unbind(object, properties);

		if (super.getRequest().getMethod().equals(HttpMethod.POST)) {
			record.put("password", super.getRequest().getData("password", String.class));
			record.put("confirmation", super.getRequest().getData("confirmation", String.class));
			record.put("accept", super.getRequest().getData("accept", boolean.class));
		} else {
			record.put("password", "");
			record.put("confirmation", "");
			record.put("accept", "false");
		}

		super.getResponse().setData(record);
	}
}
