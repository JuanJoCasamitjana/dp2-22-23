
package acme.features.authenticated.note;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.note.Note;
import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedNoteRepository extends AbstractRepository {

	@Query("SELECT n FROM Note n")
	Collection<Note> findAllNote();

	@Query("SELECT n FROM Note n WHERE n.id = :id")
	Note findOneNoteById(int id);

	@Query("SELECT n FROM Note n WHERE n.title = :title")
	Note findOneNoteByTitle(String title);

	@Query("SELECT ua FROM UserAccount ua WHERE ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	default Collection<Note> findManyRecentNote() {
		Collection<Note> res;
		Collection<Note> notes;

		res = new HashSet<>();
		notes = this.findAllNote();

		for (final Note n : notes) {
			double diferencia;
			long ahora;
			long instanciacion;
			long diff;

			diferencia = 0.0;
			ahora = new Date().getTime();
			instanciacion = n.getInstantiation().getTime();
			diff = ahora - instanciacion;

			if (diff > 0)
				diferencia = diff / (1000.0 * 60 * 60 * 24);

			if (diferencia <= 30)
				res.add(n);
		}
		return res;
	}
}
