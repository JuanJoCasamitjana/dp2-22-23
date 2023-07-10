
package acme.features.student.activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentActivitiesDeleteService extends AbstractService<Student, Activity> {

	@Autowired
	protected StudentActivityRepository repository;


	@Override
	public void check() {

		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final Integer actId = super.getRequest().getData("id", int.class);
		final Activity act = this.repository.findActivityById(actId);
		final boolean auth = super.getRequest().getPrincipal().getActiveRoleId() == act.getEnrolment().getStudent().getId();
		super.getResponse().setAuthorised(auth);

	}

	@Override
	public void validate(final Activity object) {

	}
	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Activity act = this.repository.findActivityById(id);

		super.getBuffer().setData(act);
	}

	@Override
	public void bind(final Activity object) {
		super.bind(object, "title", "text", "type", "periodStart", "periodEnd", "link");
	}

	@Override
	public void perform(final Activity object) {
		this.repository.delete(object);

	}

	@Override
	public void unbind(final Activity object) {

		Tuple tuple;
		tuple = super.unbind(object, "title", "text", "type", "periodStart", "periodEnd", "enrolment", "link");

		super.getResponse().setData(tuple);

	}

}
