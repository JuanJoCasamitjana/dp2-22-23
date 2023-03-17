
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;
	protected Integer			totalNumberOfPractica;
	protected Integer			handOnCoursesMonthLastYear;
	protected Double			averagePeriodLengthOfSession;
	protected Double			deviationPeriodLengthSession;
	protected Integer			minimumPeriodLengthSession;
	protected Integer			maximunmPeriodLengthSession;
	protected Double			averagePeriodLengthPractica;
	protected Double			deviationPeriodLengthPractica;
	protected Integer			minimumPeriodLengthPractica;
	protected Integer			maximunmPeriodLengthPractica;
}
