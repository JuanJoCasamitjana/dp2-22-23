
package acme.entities.workbook;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Workbook extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Size(max = 76)
	protected String			title;

	@NotBlank
	@Size(max = 101)
	protected String			text;


	protected enum Type {
		THEORYCAL, ONHANDS;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date	periodStart;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date	periodEnd;


	public Period availabilityPeriod() throws Exception {

		if (this.periodStart.before(this.periodEnd)) {
			final Period availability = Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			return availability;
		} else
			throw new Exception("the start date must be before the end date");

	}


	@URL
	protected String link;

}
