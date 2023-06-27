
package acme.entities.enrolment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.entities.course.Course;
import acme.framework.data.AbstractEntity;
import acme.roles.Student;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Enrolment extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Student			student;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Course			course;

	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	@Column(unique = true)
	protected String			code;

	@NotBlank
	@Size(max = 75)
	protected String			motivation;

	@NotBlank
	@Size(max = 100)
	protected String			goals;

	@NotNull
	protected double			workTime;

	@NotNull
	protected boolean			draft;

	protected String			holderName;

	protected String			lowerNibble;

	@Transient
	private String				creditCard;

	@Transient
	private String				cvc;

	@Transient
	private String				expiryDate;

}
