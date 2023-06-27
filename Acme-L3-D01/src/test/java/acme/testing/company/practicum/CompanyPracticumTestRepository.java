
package acme.testing.company.practicum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.repositories.AbstractRepository;

public interface CompanyPracticumTestRepository extends AbstractRepository {

	@Query("SELECT p FROM Practicum p WHERE p.company.userAccount.username = :username")
	Collection<Practicum> findManyPracticumByCompanyUsername(String username);

	@Query("SELECT ps FROM PracticumSession ps WHERE ps.practicum.id = :id")
	Collection<PracticumSession> findManyPracticumSessionByPracticumId(int id);
}
