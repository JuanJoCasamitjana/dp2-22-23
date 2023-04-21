
package acme.features.student.activities;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.activity.Activity;
import acme.framework.controllers.AbstractController;
import acme.roles.Student;

@Controller
public class StudentActivityController extends AbstractController<Student, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityListService	listService;
	@Autowired
	protected StudentActivityShowService	showService;
	@Autowired
	protected StudentActivityCreateService	createService;

	@Autowired
	protected StudentActivityUpdateService	updateService;
	//
	//	@Autowired
	//	protected StudentActivitiesDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		//		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("create", this.createService);

	}
}
