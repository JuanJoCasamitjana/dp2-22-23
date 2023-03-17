
package acme.entities.tutorial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.session.Session;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tutorial extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3} [0-9]{3}")
	protected String			code;
	@NotBlank
	@Length(max = 75)
	protected String			title;
	@NotBlank
	@Length(max = 100)
	protected String			abstractMessage;
	@NotBlank
	@Length(max = 100)
	protected String			goals;
	protected Double			estimatedTime;
	@ManyToOne
	protected Session			session;

}