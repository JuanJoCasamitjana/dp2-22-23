
package acme.entities;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Practicum extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@UniqueElements
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	private String				code;

	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotBlank
	@Length(max = 101)
	private String				abstractt;

	@NotBlank
	@Length(max = 101)
	private String				goals;

	private LocalTime			estimatedTotalTime;
}
