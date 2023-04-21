
package acme.entities.lecture;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import acme.roles.Lecturer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lecture extends AbstractEntity {

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
	@Min(0)
	protected double			learningTime;
	@NotBlank
	@Length(max = 100)
	protected String			body;

	protected boolean			isTheoretical;
	@URL
	protected String			optionalUrl;
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Lecturer			lecturer;

	protected boolean			published			= false;

}
