
package acme.features.student.enrolment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.enrolment.Enrolment;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentListService extends AbstractService<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

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
		Collection<Enrolment> enrolments;

		final int id = super.getRequest().getPrincipal().getAccountId();
		enrolments = this.repository.findAllEnrolmentsByStudentId(id);
		super.getBuffer().setData(enrolments);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "draft", "course.title");

		super.getResponse().setData(tuple);
	}

}
