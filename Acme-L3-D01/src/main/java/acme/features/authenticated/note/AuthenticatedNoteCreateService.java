
package acme.features.authenticated.note;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.note.Note;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.accounts.UserAccount;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedNoteCreateService extends AbstractService<Authenticated, Note> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedNoteRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().isAuthenticated();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Note object;

		object = new Note();
		object.setTitle("");
		object.setMessage("");
		object.setAuthor("");
		object.setMail("");
		object.setLink("");
		object.setInstantiation(MomentHelper.getBaseMoment());

		super.getBuffer().setData(object);

	}

	@Override
	public void bind(final Note object) {
		assert object != null;

		Date ahora;
		int userId;
		UserAccount userAccount;
		String username;
		String surname;
		String name;
		String res;

		ahora = MomentHelper.getBaseMoment();
		userId = super.getRequest().getPrincipal().getAccountId();
		userAccount = this.repository.findOneUserAccountById(userId);
		username = userAccount.getUsername();
		surname = userAccount.getIdentity().getSurname();
		name = userAccount.getIdentity().getName();
		res = "<" + username + "> - <" + surname + ", " + name + ">";

		super.bind(object, "title", "message", "mail", "link");
		object.setAuthor(res);
		object.setInstantiation(ahora);
	}

	@Override
	public void validate(final Note object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("title")) {
			Note n;
			n = this.repository.findOneNoteByTitle(object.getTitle());
			super.state(n == null || n.equals(object), "title", "authenticated.note.form.error.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("confirm")) {
			boolean confirmed;

			confirmed = super.getRequest().getData("confirm", boolean.class);

			super.state(confirmed, "confirm", "authenticated.note.form.error.not-confirmed");
		}

	}

	@Override
	public void perform(final Note object) {
		assert object != null;

		Date ahora;

		ahora = MomentHelper.getBaseMoment();

		object.setInstantiation(ahora);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Note object) {
		assert object != null;

		Tuple tuple;
		int userId;
		UserAccount userAccount;
		String username;
		String surname;
		String name;
		String res;

		userId = super.getRequest().getPrincipal().getAccountId();
		userAccount = this.repository.findOneUserAccountById(userId);
		username = userAccount.getUsername();
		surname = userAccount.getIdentity().getSurname();
		name = userAccount.getIdentity().getName();
		res = "<" + username + "> - <" + surname + ", " + name + ">";

		tuple = super.unbind(object, "instantiation", "title", "message", "mail", "link");
		tuple.put("author", res);
		super.getResponse().setData(tuple);
	}
}
