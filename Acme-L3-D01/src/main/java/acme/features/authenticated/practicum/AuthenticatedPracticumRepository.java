
package acme.features.authenticated.practicum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.practicum.Practicum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedPracticumRepository extends AbstractRepository {

	@Query("SELECT p FROM Practicum p")
	Collection<Practicum> findAllPracticum();

	@Query("SELECT p FROM Practicum p WHERE p.published = true AND p.course.typeOfCourse = 3")
	Collection<Practicum> findAllPublishedPracticumFromHandOnCourse();

	@Query("SELECT p FROM Practicum p WHERE p.id = :id")
	Practicum findOnePracticumById(int id);

}
