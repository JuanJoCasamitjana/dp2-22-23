
package acme.features.student.enrolment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.enrolment.Enrolment;
import acme.framework.controllers.AbstractController;
import acme.roles.Student;

@Controller
public class StudentEnrolmentController extends AbstractController<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentListService		listService;

	@Autowired
	protected StudentEnrolmentShowService		showService;

	@Autowired
	protected StudentEnrolmentCreateService		createService;

	@Autowired
	protected StudentEnrolmentUpdateService		updateService;

	@Autowired
	protected StudentEnrolmentDeleteService		deleteService;

	@Autowired
	protected StudentEnrolmentFinaliseService	finaliseService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
		super.addCustomCommand("finalise", "update", this.finaliseService);
		super.addBasicCommand("create", this.createService);

	}
}
