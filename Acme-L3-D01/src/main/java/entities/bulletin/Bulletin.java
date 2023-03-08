
package entities.bulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bulletin extends AbstractEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private Date				instantiationMomment;
	@NotBlank
	@Length(max = 76)
	private String				title;
	@NotBlank
	@Length(max = 76)
	private String				message;
	@URL
	private String				optionalLink;
	private boolean				isCritical			= false;
}
