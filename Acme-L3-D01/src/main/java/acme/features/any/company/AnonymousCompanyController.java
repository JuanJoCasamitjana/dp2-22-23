
package acme.features.any.company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.components.accounts.Anonymous;
import acme.framework.controllers.AbstractController;
import acme.roles.Company;

@Controller
public class AnonymousCompanyController extends AbstractController<Anonymous, Company> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousCompanyCreateService createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
	}
}
