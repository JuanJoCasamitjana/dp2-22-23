
package acme.testing.student.enrolment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.enrolment.Enrolment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface StudentEnrolmentRepositoryTest extends AbstractRepository {

	@Query("SELECT e FROM Enrolment e WHERE e.student.userAccount.username=:string")
	Collection<Enrolment> findAllEnrolmentsByStudentUserName(String string);

}
