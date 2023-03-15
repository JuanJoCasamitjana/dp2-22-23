
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDashboard extends AbstractForm {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	protected Integer			theoryLectures;
	protected Integer			handsOnLectures;
	protected Double			averageLearningTimeOfLectures;
	protected Double			deviationLearningTimeOfLectures;
	protected Double			minimumLearningTimeOfLectures;
	protected Double			maximumLearningTimeOfLectures;
	protected Double			averageLearningTimeOfCourses;
	protected Double			deviationLearningTimeOfCourses;
	protected Double			minimumLearningTimeOfCourses;
	protected Double			maximumLearningTimeOfCourses;
}
