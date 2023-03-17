
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDashboard extends AbstractForm {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	protected Integer			theoryWorkbooks;
	protected Integer			handsOnWorkbooks;
	protected Double			averageLearningTimeOfWorkbooks;
	protected Double			deviationLearningTimeOfWorkbooks;
	protected Double			minimumLearningTimeOfWorkbooks;
	protected Double			maximumLearningTimeOfWorkbooks;
	protected Double			averageLearningTimeOfCourses;
	protected Double			deviationLearningTimeOfCourses;
	protected Double			minimumLearningTimeOfCourses;
	protected Double			maximumLearningTimeOfCourses;
}
