
package acme.features.lecturer.lectureCourseAggregation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.controllers.AbstractController;
import acme.roles.Lecturer;

@Controller
public class LectureCourseAggregationController extends AbstractController<Lecturer, LectureCourseAggregation> {

	@Autowired
	protected LectureCourseAggregationCreateService	createService;
	@Autowired
	protected LectureCourseAggregationDeleteService	deleteService;
	@Autowired
	protected LectureCourseAggregationListService	listService;
	@Autowired
	protected LectureCourseAggregationShowService	showService;


	@PostConstruct
	public void initialise() {
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}
}
