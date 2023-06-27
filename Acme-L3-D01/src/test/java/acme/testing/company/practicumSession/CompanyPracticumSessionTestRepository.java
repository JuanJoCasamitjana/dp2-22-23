
package acme.testing.company.practicumSession;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.practicum.Practicum;
import acme.framework.repositories.AbstractRepository;

public interface CompanyPracticumSessionTestRepository extends AbstractRepository {

	@Query("SELECT p FROM Practicum p WHERE p.company.userAccount.username = :username")
	Collection<Practicum> findManyPracticumByCompanyUsername(String username);
}
