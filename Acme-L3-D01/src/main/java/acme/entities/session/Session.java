
package acme.entities.session;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.tutorial.Tutorial;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;
	@NotBlank
	@Length(max = 75)
	protected String			title;
	@NotBlank
	@Length(max = 100)
	protected String			abstractMessage;
	protected boolean			isTheoretical;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodStart;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodEnd;
	@URL
	protected String			optionalUrl;

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	protected Tutorial			tutorial;
}
