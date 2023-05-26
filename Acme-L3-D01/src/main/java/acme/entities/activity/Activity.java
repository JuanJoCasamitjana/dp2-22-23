
package acme.entities.activity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


	public enum Type {
		THEORYCAL, ONHANDS;
	}


	@Enumerated(EnumType.STRING)
	@NotNull
	protected Type		type;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date		periodStart;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date		periodEnd;

	@NotNull
	protected Double	totalTime;

	@ManyToOne
	@Valid
	@NotNull
	Enrolment			enrolment;

	@URL
	protected String	link;

}
