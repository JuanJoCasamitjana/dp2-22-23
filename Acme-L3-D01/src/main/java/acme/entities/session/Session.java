
package acme.entities.session;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

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
	@Min(1)
	@Max(5)
	protected double			periodTime;
	@URL
	protected String			optionalUrl;
	@ManyToOne
	protected Tutorial			tutorial;

}
