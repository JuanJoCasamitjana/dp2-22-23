
package acme.entities.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.course.Course;
import acme.framework.data.AbstractEntity;
import acme.roles.Auditor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Audit extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}\\d{3}")
	protected String			code;
	@NotBlank
	@Length(max = 100)
	protected String			conclusion;
	@NotBlank
	@Length(max = 100)
	protected String			strongPoints;
	@NotBlank
	@Length(max = 100)
	protected String			weakPoints;

	@ManyToOne
	protected Auditor			auditor; //REVISAR
	@ManyToOne
	protected Course			course; //REVISAR

}
