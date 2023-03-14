
package entities.offer;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private Date				instantiationMomment;
	@NotBlank
	@Length(max = 76)
	private String				heading;
	@NotBlank
	@Length(max = 101)
	private String				summary;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private Date				periodStart;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private Date				periodEnd;
	@NotNull
	private Money				price;

}
