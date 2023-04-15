
package acme.features.any.courses;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyCoursesRepository extends AbstractRepository {

	@Query("SELECT c FROM Course c WHERE c.id = :id and c.inDraft = false")
	Course findOneCourseById(int id);
	@Query("SELECT c FROM Course c WHERE c.inDraft = false")
	Collection<Course> findAllCourses();
}
