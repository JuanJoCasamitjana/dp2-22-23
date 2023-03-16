
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
	protected static final long	serialVersionUID		= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				instantiationMomment	= new Date();
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


	public void setPeriodStart(final Date date) throws Exception {
		this.isPeriodCorrect();
		this.periodStart = date;
	}
	public void setPeriodEnd(final Date date) throws Exception {
		this.isPeriodCorrect();
		this.periodEnd = date;
	}
	private void isPeriodCorrect() throws Exception {
		if (this.periodStart != null) {
			final Integer days = Period.between(this.instantiationMomment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
			if (days < 1)
				throw new Exception("Must start 1 day after");
		}
		if (this.periodEnd != null) {
			final Integer days = Period.between(this.instantiationMomment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
			if (days < 8)
				throw new Exception("Imposible time end");
			if (this.periodStart != null) {
				final Integer start = Period.between(this.instantiationMomment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
				final Integer availability = Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
				if (start < 1)
					throw new Exception("Must start 1 day after");
				if (availability < 7)
					throw new Exception("Must be available for at least 7 days");
			}
		}
	}
}
