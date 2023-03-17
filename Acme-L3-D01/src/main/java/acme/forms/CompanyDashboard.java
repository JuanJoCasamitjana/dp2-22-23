
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;
	protected Integer			theoryPractica;
	protected Integer			handsOnPractica;
	protected Double			averagePeriodLengthOfSession;
	protected Double			deviationPeriodLengthOfSession;
	protected Double			minimumPeriodLengthOfSession;
	protected Double			maximunmPeriodLengthOfSession;
	protected Double			averagePeriodLengthOfPractica;
	protected Double			deviationPeriodLengthOfPractica;
	protected Double			minimumPeriodLengthOfPractica;
	protected Double			maximunmPeriodLengthOfPractica;
}
