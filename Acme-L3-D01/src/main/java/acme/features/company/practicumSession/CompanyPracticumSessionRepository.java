
package acme.features.company.practicumSession;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface CompanyPracticumSessionRepository extends AbstractRepository {

	@Query("SELECT p FROM PracticumSession p")
	Collection<PracticumSession> findAllPracticumSession();

	@Query("SELECT p FROM PracticumSession p WHERE p.practicum.id = :id")
	Collection<PracticumSession> findManyPracticumSessionByPracticumId(int id);

	@Query("SELECT p FROM PracticumSession p WHERE p.id = :id")
	PracticumSession findOnePracticumSessionById(int id);

	@Query("SELECT ps FROM PracticumSession ps WHERE ps.title = :title")
	PracticumSession findOnePracticumSessionByTitle(String title);

	@Query("SELECT p FROM Practicum p WHERE p.id = :id")
	Practicum findOnePracticumById(int id);
}
