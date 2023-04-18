
package acme.features.company.companyDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface CompanyCompanyDashboardRepository extends AbstractRepository {

	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findOneCompanyById(int id);

}
