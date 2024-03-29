
package acme.entities.offer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				instantiationMomment;
	@NotBlank
	@Length(max = 75)
	protected String			heading;
	@NotBlank
	@Length(max = 100)
	protected String			summary;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodStart;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				periodEnd;
	@NotNull
	protected Money				price;

}
