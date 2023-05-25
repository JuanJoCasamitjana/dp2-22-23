
package acme.features.student.activities;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.entities.enrolment.Enrolment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface StudentActivityRepository extends AbstractRepository {

	@Query("SELECT a FROM Activity a WHERE a.enrolment.student.id = :id")
	Collection<Activity> findAllActivitiesByStudent(int id);
	@Query("SELECT a FROM Activity a WHERE a.id = :id")
	Activity findActivityById(int id);
	@Query("Select e FROM Enrolment e WHERE e.student.id = :studentId AND e.draft = FALSE")
	Collection<Enrolment> findAllEnrolmentsOfStundetn(int studentId);
	@Query("Select e FROM Enrolment e WHERE e.id = :id")
	Enrolment findEnrolmentById(int id);
	@Query("Select a FROM Activity a")
	Collection<Activity> findAllActivities();
	@Query("SELECT SUM(a.totalTime) FROM Activity a WHERE a.enrolment.id = :id")
	Optional<Double> sumOfActivityTime(int id);

}
