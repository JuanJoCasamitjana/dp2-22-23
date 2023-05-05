
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBannerRepository extends AbstractRepository {

	@Query("SELECT b FROM Banner b")
	Collection<Banner> findAllBanner();

	@Query("SELECT b FROM Banner b WHERE b.id = :id")
	Banner findOneBannerById(int id);

	@Query("SELECT b FROM Banner b WHERE b.slogan = :slogan")
	Banner findOneBannerBySlogan(String slogan);

}
