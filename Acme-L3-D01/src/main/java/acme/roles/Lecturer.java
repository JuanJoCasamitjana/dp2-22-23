
package acme.roles;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractRole;

public class Lecturer extends AbstractRole {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@NotBlank
	@Length(max = 76)
	private String				almaMater;
	@NotBlank
	@Length(max = 101)
	private String				resume;
	@NotBlank
	@Length(max = 101)
	private String				listOfQualifications;
	@URL
	private String				optionalLink;

}
