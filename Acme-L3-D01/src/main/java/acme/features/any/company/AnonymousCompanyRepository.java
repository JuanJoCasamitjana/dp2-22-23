
package acme.features.any.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface AnonymousCompanyRepository extends AbstractRepository {

	@Query("SELECT company FROM Company company WHERE company.id = :id")
	Company findOneCompanyById(int id);

	@Query("SELECT company FROM Company company WHERE company.name = :name")
	Company findOneCompanyByName(String name);
}
