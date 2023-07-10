
package acme.features.lecturer.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.Type;
import acme.framework.components.datatypes.Money;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;
import acme.system.configuration.SystemConfiguration;

@Service
public class LecturerCourseCreateService extends AbstractService<Lecturer, Course> {

	@Autowired
	protected LecturerCourseRepository repository;


	@Override
	public void check() {
		final boolean status = true;
		super.getResponse().setChecked(status);
	}
	@Override
	public void authorise() {
		final boolean status = true;
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		final int lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		final Lecturer lecturer = this.repository.findLecturerById(lecturerId);
		final Course course = new Course();
		final Money money = new Money();
		money.setAmount(0.00);
		money.setCurrency("EUR");
		course.setLecturer(lecturer);
		course.setAbstractMessage("");
		course.setCode("");
		course.setOptionalUrl("");
		course.setRetailPrice(money);
		course.setTitle("");
		course.setTypeOfCourse(Type.PURELY_THEORETICAL);
		super.getBuffer().setData(course);
	}
	@Override
	public void bind(final Course object) {
		super.bind(object, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl");
	}

	@Override
	public void validate(final Course object) {
		final boolean status = object.isInDraft();
		super.state(status, "*", "lecturer.course.create.not.in.draft");
		final Course coursesWithSameCode = this.repository.findCourseByCode(object.getCode());
		super.state(coursesWithSameCode == null, "code", "lecturer.course.create.code.exists");
		final SystemConfiguration sc = this.repository.getSystemConfiguration();
		final boolean isNotNull = object.getRetailPrice() != null;
		if (isNotNull) {
			super.state(object.getRetailPrice().getAmount() >= 0, "retailPrice", "lecturer.course.create.negative");
			boolean state = false;
			final String currencies[] = sc.getAcceptedCurrencies().split(",");
			for (final String currency : currencies) {
				if (object.getRetailPrice().getCurrency().contains(currency)) {
					state = true;
					break;
				}
				state = false;
			}
			super.state(state, "retailPrice", "lecturer.course.create.not.supported");
		} else {
			final Money money = new Money();
			money.setAmount(0.);
			money.setCurrency(this.repository.getSystemConfiguration().getDefaultSystemCurrency());
			object.setRetailPrice(money);
		}
	}
	@Override
	public void perform(final Course object) {
		this.repository.save(object);
	}

	@Override
	public void unbind(final Course course) {
		Tuple tuple;
		tuple = super.unbind(course, "code", "title", "abstractMessage", "typeOfCourse", "retailPrice", "optionalUrl", "lecturer", "inDraft");
		final SelectChoices choices = SelectChoices.from(Type.class, course.getTypeOfCourse());
		super.getResponse().setData(tuple);
		tuple.put("types", choices);

	}
}
