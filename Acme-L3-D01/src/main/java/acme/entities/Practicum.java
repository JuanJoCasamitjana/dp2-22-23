
package acme.entities;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

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
	@UniqueElements
	@Pattern(regexp = "[A-Z]{1,3} [0-9]{3}")
	private String				code;

	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotBlank
	@Length(max = 101)
	private String				abstractMessage;

	@NotBlank
	@Length(max = 101)
	private String				goals;

	@NotNull
	@Temporal(TemporalType.TIME)
	private LocalTime			estimatedTotalTime;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "company_id")
	private Company				company;
}
