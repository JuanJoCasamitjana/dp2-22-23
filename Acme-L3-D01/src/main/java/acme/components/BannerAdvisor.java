
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.banner.Banner;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	protected BannerRepository repository;


	@ModelAttribute("banner")
	public Banner getBanner() {
		Banner res;

		try {
			res = this.repository.findRandomBanner();
		} catch (final Throwable oops) {
			res = null;
		}

		return res;
	}
}
