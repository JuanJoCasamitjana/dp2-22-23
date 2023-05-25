
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.entities.audit.Audit;
import acme.entities.audit.AuditingRecord;
import acme.entities.course.Course;
import acme.entities.enrolment.Enrolment;
import acme.entities.lecture.Lecture;
import acme.entities.lecture.LectureCourseAggregation;
import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.entities.tutorial.Tutorial;
import acme.entities.tutorial.TutorialSession;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;
import acme.system.configuration.SystemConfiguration;

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
	@Query("SELECT c FROM Course c WHERE c.code = :code")
	Course findCourseByCode(String code);
	@Query("SELECT sc FROM SystemConfiguration sc")
	SystemConfiguration getSystemConfiguration();
	@Query("SELECT lca FROM LectureCourseAggregation lca WHERE lca.course.id = :id")
	Collection<LectureCourseAggregation> findAllAggregationsOfCourseById(int id);
	@Query("SELECT a FROM Audit a WHERE a.course.id = :id")
	Collection<Audit> findAuditsOfCourse(int id);
	@Query("SELECT ar FROM AuditingRecord ar WHERE ar.audit.id = :id")
	Collection<AuditingRecord> findAllRecordsOfRecord(int id);
	@Query("SELECT e FROM Enrolment e WHERE e.course.id = :id")
	Collection<Enrolment> findEnrolmentsOfCourse(int id);
	@Query("SELECT p FROM Practicum p WHERE p.course.id = :id")
	Collection<Practicum> findPracticumsOfCourse(int id);
	@Query("SELECT s FROM PracticumSession s WHERE s.practicum.id = :id")
	Collection<PracticumSession> findAllSessionsOfPracticum(int id);
	@Query("SELECT t FROM Tutorial t WHERE t.course.id = :id")
	Collection<Tutorial> findTutorialsOfCourse(int id);
	@Query("SELECT s FROM TutorialSession s WHERE s.tutorial.id = :id")
	Collection<TutorialSession> findAllSessionsOfTutorial(int id);
	@Query("SELECT a FROM Activity a WHERE a.enrolment.id = :id")
	Collection<Activity> findActivitiesOfEnrolment(int id);
}
