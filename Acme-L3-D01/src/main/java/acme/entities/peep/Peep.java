
package acme.entities.peep;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Peep extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Past
	protected Date				instantiation;

	@NotBlank
	@Size(max = 76)
	protected String			title;

	@NotBlank
	@Size(max = 76)
	protected String			nick;

	@NotBlank
	@Size(max = 101)
	protected String			message;

	@Email
	protected String			mail;

	@URL
	protected String			link;

}
