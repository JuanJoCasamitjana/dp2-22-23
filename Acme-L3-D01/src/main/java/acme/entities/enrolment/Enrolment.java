
package acme.entities.enrolment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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

	@OneToOne(optional = false)
	protected Student			student;

	@OneToOne(optional = false)
	protected Course			course;

	@NotBlank
	@Pattern(regexp = "“[A-Z]{1,3}[0-9][0-9]{3}")
	@Column(unique = true)
	protected String			code;

	@NotBlank
	@Size(max = 75)
	protected String			motivation;

	@NotBlank
	@Size(max = 100)
	protected String			goals;

	@NotNull
	protected Integer			workTime;

}
