
package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumShowService extends AbstractService<Company, Practicum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean rolOk;
		int practicumId;
		Practicum practicum;

		practicumId = super.getRequest().getData("id", int.class);
		practicum = this.repository.findOnePracticumById(practicumId);
		rolOk = super.getRequest().getPrincipal().hasRole(practicum.getCompany());
		status = practicum != null && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practicum object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticumById(id);

		super.getBuffer().setData(object);

	}

	@Override
	public void unbind(final Practicum object) {
		assert object != null;

		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;
		Collection<PracticumSession> sesiones;
		double diffHoras = 0;
		double total = 0;

		sesiones = this.repository.findManyPracticumSessionByPracticumId(object.getId());

		for (final PracticumSession ps : sesiones) {
			long milisegundosStart;
			long milisegundosEnd;
			long diffMilisegundos;

			milisegundosStart = ps.getPeriodStart().getTime();
			milisegundosEnd = ps.getPeriodEnd().getTime();
			diffMilisegundos = milisegundosEnd - milisegundosStart;
			if (diffMilisegundos > 0) {
				diffHoras = (double) diffMilisegundos / (1000 * 60 * 60);
				total += diffHoras;
			}
		}

		final int horas = (int) total;
		final int minutos = (int) ((total - horas) * 60);
		final double res = Double.parseDouble(horas + "." + minutos);

		courses = this.repository.findManyHandsOnCourse();
		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "title", "abstractMessage", "goals", "published");
		tuple.put("estimatedTotalTime", res);
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
