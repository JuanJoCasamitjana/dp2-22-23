
package entities.offer;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@PastOrPresent
	private Date				instantiationMomment;
	@NotBlank
	@Length(max = 76)
	private String				heading;
	@NotBlank
	@Length(max = 101)
	private String				summary;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@Future
	private Date				periodStart;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@Future
	private Date				periodEnd;
	@NotNull
	private Money				price;


	//Moverlo al servicio en save si es posible
	public Period availabilityPeriod() throws Exception {
		final Integer days = Period.between(this.instantiationMomment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
		final Period availability = Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		if (days < 1 || availability.getDays() < 7)
			throw new Exception("Must start 1 day after and must last a week");
		return availability;
	}
}
