
package acme.features.administrator.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;
import acme.system.configuration.SystemConfiguration;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("SELECT c FROM Company c WHERE c.id = :id")
	Company findOneCompanyById(int id);

	@Query("SELECT sc FROM SystemConfiguration sc")
	SystemConfiguration findCurrentSystemConfiguration();
}
