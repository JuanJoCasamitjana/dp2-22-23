
package acme.features.authenticated.bulletins;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.accounts.Authenticated;
import acme.framework.controllers.AbstractController;

@Controller
public class AuthenticatedBulletinController extends AbstractController<Authenticated, Bulletin> {

	@Autowired
	protected AuthenticatedBulletinListService		listService;
	@Autowired
	protected AuthenticatedBulletinShowService		showService;
	@Autowired
	protected AuthenticatedBulletinCreateService	createService;


	@PostConstruct
	public void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
	}
}
