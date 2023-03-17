
package acme.forms;

import java.util.Map;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	protected Map<String, Integer>	totalNumbersOfPrincipalsForEachRol;
	protected Double				ratioOfPeepsWithEmail;
	protected Double				ratioOfPeepsWithLink;
	protected Double				ratioOfCriticalBulletins;
	protected Double				ratioOfNonCriticalBulletins;
	protected Double				averageNumberOfBudgetGroupedByCurrency;
	protected Double				minimumNumberOfBudgetGroupedByCurrency;
	protected Double				maximumNumberOfBudgetGroupedByCurrency;
	protected Double				standartDeviatonOfBudgetGroupedByCurrency;
	protected Double				averageNumberOfNotesPostedOverLastTenWeeks;
	protected Double				minimumNumberOfNotesPostedOverLastTenWeeks;
	protected Double				maximumNumberOfNotesPostedOverLastTenWeeks;
	protected Double				standartDeviatonOfNotesPostedOverLastTenWeeks;

}
