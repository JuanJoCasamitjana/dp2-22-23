
package acme.entities.activity;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.enrolment.Enrolment;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Activity extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Size(max = 75)
	protected String			title;

	@NotBlank
	@Size(max = 100)
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


	public Period getPeriod() {
		return Period.between(this.periodStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.periodEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}


	@ManyToOne
	@Valid
	Enrolment			enrolment;

	@URL
	protected String	link;

}
