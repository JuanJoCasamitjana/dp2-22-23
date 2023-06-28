
package acme.features.any.course;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyCourseRepository extends AbstractRepository {

	@Query("SELECT c FROM Course c WHERE c.inDraft = false")
	Collection<Course> findPublishedCourses();

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(int id);

}
