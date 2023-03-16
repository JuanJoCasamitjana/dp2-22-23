
package acme.entities.bulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bulletin extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				instantiationMomment;
	@NotBlank
	@Length(max = 76)
	protected String			title;
	@NotBlank
	@Length(max = 101)
	protected String			message;
	@URL
	protected String			optionalLink;
	protected boolean			isCritical			= false;
}