
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lecture.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureCreateService extends AbstractService<Lecturer, Lecture> {

	@Autowired
	protected LecturerLectureRepository repository;


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
		final Lecturer lecturer = this.repository.findLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		final Lecture lecture = new Lecture();
		lecture.setLecturer(lecturer);
		lecture.setAbstractMessage("");
		lecture.setBody("");
		lecture.setLearningTime(0.00);
		lecture.setOptionalUrl("");
		lecture.setPublished(false);
		lecture.setTheoretical(false);
		lecture.setTitle("");
		super.getBuffer().setData(lecture);
	}
	@Override
	public void bind(final Lecture object) {
		assert object != null;

		super.bind(object, "title", "abstractMessage", "learningTime", "body", "isTheoretical", "optionalUrl");
	}

	@Override
	public void validate(final Lecture object) {
		assert object != null;
		final boolean status = !object.isPublished();
		super.state(status, "*", "lecturer.Lecture.create.not.in.draft");
	}
	@Override
	public void perform(final Lecture object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Lecture lecture) {
		assert lecture != null;
		Tuple tuple;
		tuple = super.unbind(lecture, "title", "abstractMessage", "learningTime", "body", "isTheoretical", "optionalUrl", "published");
		super.getResponse().setData(tuple);

	}
}
