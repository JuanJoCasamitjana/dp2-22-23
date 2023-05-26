
package acme.testing.lecturer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.course.Course;
import acme.entities.lecture.Lecture;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.repositories.AbstractRepository;

public interface LecturerTestRepository extends AbstractRepository {

	@Query("SELECT lca FROM LectureCourseAggregation lca WHERE lca.course.lecturer.userAccount.username = :username")
	Collection<LectureCourseAggregation> findAggregationsByLecturerUname(String username);
	@Query("SELECT l FROM Lecture l WHERE l.lecturer.userAccount.username = :username")
	Collection<Lecture> findLecturesByLecturerUname(String username);
	@Query("SELECT c FROM Course c WHERE c.lecturer.userAccount.username = :username")
	Collection<Course> findCoursesByLecturerUname(String username);
}
