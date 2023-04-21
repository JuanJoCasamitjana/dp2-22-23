
package acme.features.student.course;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentCourseRepository extends AbstractRepository {

	@Query("SELECT s FROM Student s")
	List<Student> findAllStudents();

	@Query("SELECT s FROM Student s WHERE s.id = :id")
	Student findStudentById(int id);

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(int id);

	@Query("SELECT c FROM Course c")
	Collection<Course> findCourses();

	@Query("SELECT e FROM Course e")
	List<Course> findAllCourses();

	@Query("SELECT e FROM Course e WHERE e.inDraft = FALSE")
	List<Course> findAllPublishedCourses();

	@Query("SELECT m FROM LectureCourseAggregation m WHERE m.course.id = :id")
	Collection<LectureCourseAggregation> findCourseLectureByCourseId(int id);

}
