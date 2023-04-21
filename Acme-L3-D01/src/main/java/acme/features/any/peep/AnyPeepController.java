
package acme.features.any.peep;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.peep.Peep;
import acme.framework.components.accounts.Any;
import acme.framework.controllers.AbstractController;

@Controller
public class AnyPeepController extends AbstractController<Any, Peep> {

	@Autowired
	protected AnyPeepShowService	showService;
	@Autowired
	protected AnyPeepCreateService	createService;
	@Autowired
	protected AnyPeepListService	listService;


	@PostConstruct
	public void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("show", this.showService);
	}
}
