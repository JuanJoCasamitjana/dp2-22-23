
package acme.features.student.activities;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentActivityListService extends AbstractService<Student, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		final boolean auth = super.getRequest().getPrincipal().getActiveRole() == Student.class;
		super.getResponse().setAuthorised(auth);
	}

	@Override
	public void load() {
		Collection<Activity> activities;

		final int id = super.getRequest().getPrincipal().getActiveRoleId();
		activities = this.repository.findAllActivitiesByStudent(id);
		super.getBuffer().setData(activities);
	}

	@Override
	public void unbind(final Activity object) {

		Tuple tuple;

		tuple = super.unbind(object, "title", "text", "type");

		super.getResponse().setData(tuple);
	}

}
