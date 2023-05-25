
package acme.features.company.companyDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface CompanyDashboardRepository extends AbstractRepository {

	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findOneCompanyById(int id);

	@Query("SELECT COUNT(p) FROM Practicum p WHERE p.company.id = :id")
	Integer totalNumberOfPracticum(int id);

	@Query("SELECT avg(p.estimatedTotalTime) FROM Practicum p WHERE p.company.id = :id")
	Double averagePeriodLengthOfPracticum(int id);

	@Query("SELECT stddev(p.estimatedTotalTime) FROM Practicum p WHERE p.company.id = :id")
	Double deviationPeriodLengthOfPracticum(int id);

	@Query("SELECT min(p.estimatedTotalTime) FROM Practicum p WHERE p.company.id = :id")
	Double minimumPeriodLengthOfPracticum(int id);

	@Query("SELECT max(p.estimatedTotalTime) FROM Practicum p WHERE p.company.id = :id")
	Double maximumPeriodLengthOfPracticum(int id);

	@Query("SELECT count(ps) FROM PracticumSession ps WHERE ps.practicum.company.id = :id")
	Integer totalNumberOfSession(int id);

	@Query("SELECT avg(ps.totalTime) FROM PracticumSession ps WHERE ps.practicum.company.id = :id")
	Double averagePeriodLengthOfSession(int id);

	@Query("SELECT stddev(ps.totalTime) FROM PracticumSession ps WHERE ps.practicum.company.id = :id")
	Double deviationPeriodLengthOfSession(int id);

	@Query("SELECT min(ps.totalTime) FROM PracticumSession ps WHERE ps.practicum.company.id = :id")
	Double minimumPeriodLengthOfSession(int id);

	@Query("SELECT max(ps.totalTime) FROM PracticumSession ps WHERE ps.practicum.company.id = :id")
	Double maximumPeriodLengthOfSession(int id);

}
