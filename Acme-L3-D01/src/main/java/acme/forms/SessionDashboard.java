
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDashboard extends AbstractForm {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	protected Integer 			totalTutorials;
	protected Integer			theorySessions;
	protected Integer			handsOnSessions;
	protected Double			averageLearningTimeOfSessions;
	protected Double			deviationLearningTimeOfSessions;
	protected Double			minimumLearningTimeOfSessions;
	protected Double			maximumLearningTimeOfSessions;
	protected Double			averageLearningTimeOfTutorials;
	protected Double			deviationLearningTimeOfTutorials;
	protected Double			minimumLearningTimeOfTutorials;
	protected Double			maximumLearningTimeOfTutorials;
	
}
