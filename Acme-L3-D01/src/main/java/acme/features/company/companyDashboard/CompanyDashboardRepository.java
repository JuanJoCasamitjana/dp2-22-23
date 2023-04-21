
package acme.features.company.companyDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface CompanyDashboardRepository extends AbstractRepository {

	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findOneCompanyById(int id);

	//	@Query("SELECT COUNT(p) FROM Practicum p WHERE p.company.id = c.id FROM Company c")
	//	Integer theoryPractica();
	//
	//	Integer handsOnPractica();
	//
	//	Double averagePeriodLengthOfSession();
	//
	//	Double deviationPeriodLengthOfSession();
	//
	//	Double minimumPeriodLengthOfSession();
	//
	//	Double maximunmPeriodLengthOfSession();
	//
	//	Double averagePeriodLengthOfPractica();
	//
	//	Double deviationPeriodLengthOfPractica();
	//
	//	Double minimumPeriodLengthOfPractica();
	//
	//	Double maximunmPeriodLengthOfPractica();

}
