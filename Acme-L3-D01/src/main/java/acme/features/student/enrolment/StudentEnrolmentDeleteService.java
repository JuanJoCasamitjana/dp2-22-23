
package acme.features.student.enrolment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.enrolment.Enrolment;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentDeleteService extends AbstractService<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final Integer eId = super.getRequest().getData("id", int.class);
		final Enrolment e = this.repository.findEnrolmentById(eId);
		final boolean auth = super.getRequest().getPrincipal().getActiveRoleId() == e.getStudent().getId();
		super.getResponse().setAuthorised(auth && e.isDraft());
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Enrolment enrolment = this.repository.findEnrolmentById(id);

		super.getBuffer().setData(enrolment);
	}

	@Override
	public void bind(final Enrolment object) {
		assert object != null;

		super.bind(object, "motivation", "goals", "code");
	}

	@Override
	public void validate(final Enrolment object) {
		assert object != null;
	}

	@Override
	public void perform(final Enrolment object) {
		assert object != null;

		final Collection<Activity> activities = this.repository.findAllActivitiesByEnrolmentId(object.getId());
		this.repository.deleteAll(activities);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;

		Tuple tuple;
		tuple = super.unbind(object, "motivation", "goals", "code", "draft");

		super.getResponse().setData(tuple);
	}
}
