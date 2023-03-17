
package acme.entities.lecture;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.course.Course;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lecture extends AbstractEntity {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;
	@NotBlank
	@Length(max = 76)
	protected String			title;
	@NotBlank
	@Length(max = 101)
	protected String			abstractMessage;
	@Min(0)
	protected double			learningTime;
	@NotBlank
	@Length(max = 101)
	protected String			body;

	protected boolean			isTheoretical;
	@URL
	protected String			optionalUrl;
	@ManyToOne
	protected Course			course;

}
