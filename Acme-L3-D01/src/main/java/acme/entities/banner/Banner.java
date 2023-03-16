
package acme.entities.banner;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Banner extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Past
	protected Date				instantiationOrUpdate;

	@NotBlank
	@Size(max = 76)
	protected String			slogan;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodStart;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodEnd;

	@URL
	protected String			pictureLink;

	@URL
	protected String			webDocLink;


	public Period availabilityPeriod() throws Exception {
		final Period availability = Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		if (availability.getDays() < 7 && this.instantiationOrUpdate.before(this.periodStart))
			throw new Exception("Must start 1 day after and must last a week");
		return availability;
	}

}
