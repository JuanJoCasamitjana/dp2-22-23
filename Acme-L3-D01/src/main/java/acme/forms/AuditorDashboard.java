
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	int							theoryCourses;
	int							handsOnCourses;
	double						averageNumberOfAuditingRecords;
	double						deviationNumberOfAuditingRecords;
	double						minimumNumberOfAuditingRecords;
	double						maximumNumberOfAuditingRecords;
	double						averageTimeOfPeriodLengths;
	double						deviationTimeOfPeriodLengths;
	double						minimumTimeOfPeriodLengths;
	double						maximumTimeOfPeriodLengths;
}
