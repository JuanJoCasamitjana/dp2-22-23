
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;
	protected Integer			totalNumberOfPracticums;
	protected Double			averagePeriodLengthOfPracticums;
	protected Double			deviationPeriodLengthOfPracticums;
	protected Double			minimumPeriodLengthOfPracticums;
	protected Double			maximumPeriodLengthOfPracticums;
	protected Integer			totalNumberOfSessions;
	protected Double			averagePeriodLengthOfSessions;
	protected Double			deviationPeriodLengthOfSessions;
	protected Double			minimumPeriodLengthOfSessions;
	protected Double			maximumPeriodLengthOfSessions;
}
