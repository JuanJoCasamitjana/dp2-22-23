
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends AbstractRole {

	protected static final long	serialVersionUID	= 1L;

	@NotBlank
	@Size(max = 75)
	protected String			statement;

	@NotBlank
	@Size(max = 100)
	protected String			strongFeatures;

	@NotBlank
	@Size(max = 100)
	protected String			weekFeatures;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
