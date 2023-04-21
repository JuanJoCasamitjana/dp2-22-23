
package acme.features.student.activities;

import java.util.Collection;

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
	@Query("Select e FROM Enrolment e WHERE e.draft=0")
	Collection<Enrolment> findAllEnrolmentsFinished();
	@Query("Select e FROM Enrolment e WHERE e.id = :id")
	Enrolment findEnrolmentById(int id);

}
