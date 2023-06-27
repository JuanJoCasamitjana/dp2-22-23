
package acme.components;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select count(a) from Banner a")
	int countBanners();

	@Query("select a from Banner a")
	List<Banner> findManyBanners(PageRequest pageRequest);

	default Banner findRandomBanner() {
		Banner result;
		int count;
		int index;
		ThreadLocalRandom random;
		PageRequest page;
		List<Banner> list;
		count = this.countBanners();
		if (count == 0)
			result = null;
		else {
			random = ThreadLocalRandom.current();
			index = random.nextInt(0, count);
			page = PageRequest.of(index, 1);
			list = this.findManyBanners(page);
			result = list.isEmpty() ? null : list.get(0);
		}
		return result;
	}
}
