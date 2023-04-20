
package acme.features.lecturer.lectureCourseAggregation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.lecture.Lecture;
import acme.entities.lecture.LectureCourseAggregation;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LectureCourseAggregationRepository extends AbstractRepository {

	@Query("SELECT lc.lecturer FROM Lecture lc WHERE lc.id = :lectureId")
	Lecturer findLecturerOfLecture(int lectureId);
	@Query("SELECT lc FROM Lecture lc WHERE lc.id = :lectureId")
	Lecture findLectureById(int lectureId);
	@Query("SELECT c FROM Course c WHERE c.inDraft = true AND c.lecturer.id = :lecturerId")
	Collection<Course> findAvailableCoursesToAddLecture(int lecturerId);
	@Query("SELECT lca FROM LectureCourseAggregation lca WHERE lca.id = :id")
	LectureCourseAggregation findOneAggregationById(int id);
	@Query("SELECT lca FROM LectureCourseAggregation lca WHERE lca.lecture.lecturer.id = :id")
	Collection<LectureCourseAggregation> findAllAggregationOfLecturer(int id);
	@Query("SELECT l FROM Lecture l WHERE l.lecturer.id = :lecturerId")
	Collection<Lecture> findAllLecturesOfLecturer(int lecturerId);
	@Query("SELECT c FROM Course c WHERE c.id = :courseId")
	Course findCourseById(int courseId);
}
