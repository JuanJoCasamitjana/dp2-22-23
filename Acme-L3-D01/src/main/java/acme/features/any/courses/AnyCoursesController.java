
package acme.features.any.courses;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.course.Course;
import acme.framework.components.accounts.Any;
import acme.framework.controllers.AbstractController;

@Controller
public class AnyCoursesController extends AbstractController<Any, Course> {

	@Autowired
	protected AnyCoursesListService	listService;
	@Autowired
	protected AnyCoursesShowService	showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
	}
}
