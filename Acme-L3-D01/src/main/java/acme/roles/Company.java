
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Company extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 75)
	protected String			name;

	@NotBlank
	@Length(max = 25)
	protected String			vatNumber;

	@NotBlank
	@Length(max = 100)
	protected String			summary;

	@URL
	protected String			optionalLink;
}
