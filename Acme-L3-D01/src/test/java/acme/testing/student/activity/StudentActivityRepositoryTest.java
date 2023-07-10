
package acme.testing.student.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface StudentActivityRepositoryTest extends AbstractRepository {

	@Query("SELECT a FROM Activity a WHERE a.enrolment.student.userAccount.username = :name")
	Collection<Activity> findAllActivitiesByStudentUsername(String name);

}
