
package acme.entities.tutorial;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.course.Course;
import acme.entities.session.Session;
import acme.framework.data.AbstractEntity;
import acme.roles.Assistant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tutorial extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}\\d{3}$")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstract$;

	@NotBlank
	@Length(max = 100)
	protected String			goal;

	protected boolean			draftMode;

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	protected Course			course;

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	protected Assistant			assistant;


	public Double estimatedTotalTime(final Collection<Session> sessions) {
		double res = 0.0;
		if (!sessions.isEmpty())
			for (final Session sesion : sessions) {
				final Date start = sesion.getStartPeriod();
				final Date end = sesion.getEndPeriod();
				double horas = 0.0;
				double minutos = 0.0;
				horas = Math.abs(end.getTime() / 3600000 - start.getTime() / 3600000);
				minutos = Math.abs(end.getTime() / 60000 - start.getTime() / 60000) % 60;
				final double porcentajeMinutos = minutos / 60;
				res += horas + porcentajeMinutos;
			}
		return res;
	}

}

