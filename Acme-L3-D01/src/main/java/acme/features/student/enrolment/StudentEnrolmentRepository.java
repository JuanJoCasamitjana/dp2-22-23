
package acme.features.student.enrolment;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.entities.course.Course;
import acme.entities.enrolment.Enrolment;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentEnrolmentRepository extends AbstractRepository {

	@Query("SELECT s FROM Student s")
	List<Student> findAllStudents();

	@Query("SELECT a FROM Activity a WHERE a.enrolment.student.userAccount.id = :id")
	Collection<Activity> findAllActivitiesByStudentId(int id);

	@Query("SELECT a FROM Activity a WHERE a.enrolment.id = :id")
	Collection<Activity> findAllActivitiesByEnrolmentId(int id);

	@Query("SELECT s FROM Student s WHERE s.id = :id")
	Student findStudentById(int id);

	@Query("SELECT a FROM Activity a")
	Collection<Activity> findAllActivities();

	@Query("SELECT a FROM Activity a WHERE a.id = :id")
	Activity findActivityById(int id);

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(int id);

	@Query("SELECT c FROM Course c WHERE c.inDraft = FALSE")
	Collection<Course> findPublishedCourses();

	@Query("SELECT c FROM Course c")
	Collection<Course> findCourses();

	@Query("SELECT e FROM Enrolment e")
	List<Enrolment> findAllEnrolments();

	@Query("SELECT e FROM Enrolment e WHERE e.student.userAccount.id = :id")
	List<Enrolment> findAllEnrolmentsByStudentId(int id);

	@Query("SELECT e FROM Enrolment e WHERE e.id = :id")
	Enrolment findEnrolmentById(int id);

	@Query("SELECT count(e) FROM Enrolment e WHERE e.code = :code")
	int findOneEnrolmentByCode(String code);

	@Query("SELECT count(e) FROM Enrolment e WHERE e.course.id = :courseId AND e.student.id = :studentId")
	int findAllEnrolmentsOfOneStudentToOneCourseById(int courseId, int studentId);

}
