
package acme.features.any.peep;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.peep.Peep;
import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyPeepRepository extends AbstractRepository {

	@Query("SELECT p FROM Peep p")
	Collection<Peep> findAllpeeps();

	@Query("SELECT p FROM Peep p WHERE p.id = :id")
	Peep findOnePeepById(int id);

	@Query("SELECT ua FROM UserAccount ua WHERE ua.id = :accountId")
	UserAccount findUserAccountById(int accountId);

}
