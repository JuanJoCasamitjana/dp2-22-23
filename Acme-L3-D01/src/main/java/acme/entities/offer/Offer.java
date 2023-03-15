
package acme.entities.offer;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	protected static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				instantiationMomment;
	@NotBlank
	@Length(max = 76)
	protected String			heading;
	@NotBlank
	@Length(max = 101)
	protected String			summary;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodStart;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodEnd;
	@NotNull
	protected Money				price;


	//Moverlo al servicio en save si es posible
	public Period availabilityPeriod() throws Exception {
		final Integer days = Period.between(this.instantiationMomment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
		final Period availability = Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		if (days < 1 || availability.getDays() < 7)
			throw new Exception("Must start 1 day after and must last a week");
		return availability;
	}
}
