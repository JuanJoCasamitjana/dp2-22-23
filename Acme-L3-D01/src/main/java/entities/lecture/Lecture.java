
package entities.lecture;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import entities.course.Course;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lecture extends AbstractEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@NotBlank
	@Length(max = 76)
	private String				title;
	@NotBlank
	@Length(max = 101)
	private String				abstractMessage;
	@Min(0)
	private double				learningTime;
	@NotBlank
	@Length(max = 101)
	private String				body;

	private boolean				isTheoretical;
	@URL
	private String				optionalUrl;
	@ManyToOne
	private Course				course;

}
