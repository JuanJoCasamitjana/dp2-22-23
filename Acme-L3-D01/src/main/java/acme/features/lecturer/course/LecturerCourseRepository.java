
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.lecture.Lecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerCourseRepository extends AbstractRepository {

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findOneCourseById(int id);
	@Query("SELECT c FROM Course c WHERE c.lecturer.id = :lecturerId")
	Collection<Course> findAllCoursesOfLecturer(int lecturerId);
	@Query("SELECT l FROM Lecturer l WHERE l.id = :id")
	Lecturer findLecturerById(int id);
	@Query("SELECT lc.lecture FROM LectureCourseAggregation lc WHERE lc.course.id = :id")
	Collection<Lecture> findAllLecturesOfCourse(int id);
}
