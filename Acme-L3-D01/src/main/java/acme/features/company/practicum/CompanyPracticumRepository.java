
package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface CompanyPracticumRepository extends AbstractRepository {

	@Query("SELECT p FROM Practicum p WHERE p.id = :id")
	Practicum findOnePracticumById(int id);

	@Query("SELECT p FROM Practicum p WHERE p.code = :code")
	Practicum findOnePracticumByCode(String code);

	@Query("SELECT ps FROM PracticumSession ps WHERE ps.practicum.id = :id")
	Collection<PracticumSession> findManyPracticumSessionByPracticumId(int id);

	@Query("SELECT p FROM Practicum p WHERE p.company.id = :id")
	Collection<Practicum> findManyPracticumByCompanyId(int id);

	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findOneCompanyById(int id);

	@Query("SELECT c FROM Course c WHERE c.typeOfCourse = 3")
	Collection<Course> findManyHandsOnCourse();

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findOneCourseById(int id);

}
