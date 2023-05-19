
package acme.features.authenticated.bulletins;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository

public interface AuthenticatedBulletinRepository extends AbstractRepository {

	@Query("Select b FROM Bulletin b")
	Collection<Bulletin> findAllBulletins();

	@Query("Select b FROM Bulletin b WHERE b.id = :id")
	Bulletin findBulletinById(int id);

}
