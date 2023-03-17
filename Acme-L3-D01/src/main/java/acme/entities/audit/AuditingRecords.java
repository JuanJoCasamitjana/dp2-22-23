
package acme.entities.audit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditingRecords extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;
	@NotBlank
	@Length(max = 75)
	protected String			subject;
	@NotBlank
	@Length(max = 100)
	protected String			assessment;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@PastOrPresent
	protected Date				startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@PastOrPresent
	protected Date				endDate;
	@NotNull
	protected Mark				mark;
	@URL
	protected String			optionalLink;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Audit				audit;
}
