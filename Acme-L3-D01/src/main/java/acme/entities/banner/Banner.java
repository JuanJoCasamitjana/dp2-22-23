
package acme.entities.banner;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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

	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				instantiationOrUpdate;

	@NotBlank
	@Size(max = 75)
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


	public Period getPeriod() {
		return Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}

}
