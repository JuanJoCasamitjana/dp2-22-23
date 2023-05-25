
package acme.features.authenticated.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offer.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedOfferRepository extends AbstractRepository {

	@Query("SELECT o FROM Offer o")
	Collection<Offer> findAllOffer();

	@Query("SELECT o FROM Offer o WHERE o.id = :id")
	Offer findOneOfferById(int id);

}
