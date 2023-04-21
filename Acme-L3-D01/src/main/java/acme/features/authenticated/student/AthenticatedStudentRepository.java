
package acme.features.authenticated.student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface AthenticatedStudentRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
	@Query("select l from Student l where l.userAccount.id = :id")
	Student findOneStudentByUserAccountId(int id);
}
