
package acme.entities;

import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PracticumSession extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 75)
	private String				title;

	@NotBlank
	@Length(max = 100)
	private String				abstractMessage;

	//@Temporal(TemporalType.TIMESTAMP)
	private Period				period;

	@URL
	private String				optionalLink;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "practicum_id")
	private Practicum			practicum;
}
