
package acme.entities.note;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Note extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				instantiation;

	@NotBlank
	@Size(max = 75)
	protected String			title;

	@NotBlank
	@Size(max = 75)
	protected String			author;

	@NotBlank
	@Size(max = 100)
	protected String			message;

	@Email
	protected String			mail;

	@URL
	protected String			link;

}
