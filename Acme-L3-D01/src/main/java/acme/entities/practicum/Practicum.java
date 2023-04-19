
package acme.entities.practicum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.course.Course;
import acme.framework.data.AbstractEntity;
import acme.roles.Company;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Practicum extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3} [0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractMessage;

	@NotBlank
	@Length(max = 100)
	protected String			goals;

	@NotNull
	@Column(nullable = false)
	protected Double			estimatedTotalTime;

	@NotNull
	protected Boolean			published;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "company_id")
	protected Company			company;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id")
	protected Course			course;
}
