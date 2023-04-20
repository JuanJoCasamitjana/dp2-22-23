
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.lecture.Lecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerLectureRepository extends AbstractRepository {

	@Query("SELECT lc FROM Lecture lc WHERE lc.id = :id")
	Lecture findOneLectureById(int id);
	@Query("SELECT lc FROM Lecture lc WHERE lc.lecturer.id = :lecturerId")
	Collection<Lecture> findAllLecturesOfLecturer(int lecturerId);
	@Query("SELECT l FROM Lecturer l WHERE l.id = :id")
	Lecturer findLecturerById(int id);
	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(int id);
	@Query("SELECT lca.lecture FROM LectureCourseAggregation lca WHERE lca.course.id = :courseId")
	Collection<Lecture> findAllLecturesOfCourse(int courseId);
}
