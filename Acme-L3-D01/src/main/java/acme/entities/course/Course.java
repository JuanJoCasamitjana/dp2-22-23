
package acme.entities.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import acme.roles.Lecturer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3} [0-9]{3}")
	protected String			code;
	@NotBlank
	@Length(max = 76)
	protected String			title;
	@NotBlank
	@Length(max = 101)
	protected String			abstractMessage;
	@NotNull
	protected Type				typeOfCourse;
	@NotNull
	protected Money				retailPrice;
	@URL
	protected String			optionalUrl;
	@ManyToOne
	protected Lecturer			lecturer;
	protected boolean			inDraft;

}
