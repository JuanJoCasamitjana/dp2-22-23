
package acme.entities.offer;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.convert.PeriodUnit;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Offer extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Past
	protected Date				instantiation;

	@NotBlank
	@Size(max = 76)
	protected String			heading;

	@NotBlank
	@Size(max = 101)
	protected String			summary;

	@PeriodUnit(ChronoUnit.WEEKS)
	@DurationMin(days = 1)
	@DurationMax(days = 7)
	protected Period			availability;

	@PositiveOrZero
	protected Double			price;

	@URL
	protected String			link;

}
