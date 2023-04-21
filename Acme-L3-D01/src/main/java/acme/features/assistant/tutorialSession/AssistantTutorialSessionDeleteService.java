
package acme.features.assistant.tutorialSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.Nature;
import acme.entities.courses.LectureType;
import acme.entities.tutorial.Tutorial;
import acme.entities.tutorial.TutorialSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantTutoiralSessionDeleteService extends AbstractService<Assistant, TutorialSession> {


	@Autowired
	protected AssistantTutorialSessionRepository repository;



	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int sessionId;
		Tutorial tutorial;

		sessionId = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findOneTutorialByTutorialSessionId(sessionId);
		status = tutorial != null && tutorial.isDraftMode() && super.getRequest().getPrincipal().hasRole(tutorial.getAssistant());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TutorialSession object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTutorialSessionById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final TutorialSession object) {
		assert object != null;

		super.bind(object, "title", "abstract$", "nature", "startPeriod", "endPeriod", "furtherInformationLink");
	}

	@Override
	public void validate(final TutorialSession object) {
		assert object != null;
	}

	@Override
	public void perform(final TutorialSession object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final TutorialSession object) {
		assert object != null;

		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(Nature.class, object.getNature());

		tuple = super.unbind(object, "title", "abstract$", "nature", "startDate", "endPeriod", "furtherInformationLink");
		tuple.put("tutorialId", super.getRequest().getData("tutorialId", int.class));
		tuple.put("draftMode", object.getTutorial().isDraftMode());
		tuple.put("types", choices);


		super.getResponse().setData(tuple);
	}

}
