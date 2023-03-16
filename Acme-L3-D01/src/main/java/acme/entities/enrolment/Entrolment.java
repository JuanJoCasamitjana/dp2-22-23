
package acme.entities.enrolment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.entities.workbook.Workbook;
import acme.framework.data.AbstractEntity;
import acme.roles.Student;

@Entity

public class Entrolment extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@OneToOne
	Student						student;

	@NotBlank
	@Size(max = 76)
	@Pattern(regexp = "“[A-Z]{1,3}[0-9][0-9]{3}")
	@Column(unique = true)
	protected String			code;

	@NotBlank
	@Size(max = 76)
	protected String			motivation;

	@NotBlank
	@Size(max = 76)
	protected String			goals;

	@ManyToOne
	Workbook					workbook;

}
