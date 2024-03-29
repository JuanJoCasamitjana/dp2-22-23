
package acme.features.company.practicumSession;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface CompanyPracticumSessionRepository extends AbstractRepository {

	@Query("SELECT p FROM PracticumSession p WHERE p.practicum.id = :id")
	Collection<PracticumSession> findManyPracticumSessionByPracticumId(int id);

	@Query("SELECT p FROM PracticumSession p WHERE p.id = :id")
	PracticumSession findOnePracticumSessionById(int id);

	@Query("SELECT ps FROM PracticumSession ps WHERE ps.title = :title")
	PracticumSession findOnePracticumSessionByTitle(String title);

	@Query("SELECT p FROM Practicum p WHERE p.id = :id")
	Practicum findOnePracticumById(int id);

	@Query("SELECT ps FROM PracticumSession ps WHERE ps.practicum.id = :id AND ps.addendum = true")
	Collection<PracticumSession> findAddendumSessionByPracticumId(int id);

	@Query("SELECT SUM(ps.totalTime) FROM PracticumSession ps WHERE ps.practicum.id = :id")
	Optional<Double> sumOfPracticumSessionTimeByPracticumId(int id);
}
