
package acme.features.company.companyDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.CompanyDashboard;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyDashboardShowService extends AbstractService<Company, CompanyDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Company.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean rolOk;
		int companyId;
		Company company;

		companyId = super.getRequest().getPrincipal().getActiveRoleId();
		company = this.repository.findOneCompanyById(companyId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = company != null && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int companyId;
		CompanyDashboard object;
		Integer totalNumberOfPracticums;
		Double averagePeriodLengthOfPracticums;
		Double deviationPeriodLengthOfPracticums;
		Double minimumPeriodLengthOfPracticums;
		Double maximumPeriodLengthOfPracticums;
		Integer totalNumberOfSessions;
		Double averagePeriodLengthOfSessions;
		Double deviationPeriodLengthOfSessions;
		Double minimumPeriodLengthOfSessions;
		Double maximumPeriodLengthOfSessions;

		companyId = super.getRequest().getPrincipal().getActiveRoleId();
		totalNumberOfPracticums = this.repository.totalNumberOfPracticum(companyId);
		averagePeriodLengthOfPracticums = this.repository.averagePeriodLengthOfPracticum(companyId);
		deviationPeriodLengthOfPracticums = this.repository.deviationPeriodLengthOfPracticum(companyId);
		minimumPeriodLengthOfPracticums = this.repository.minimumPeriodLengthOfPracticum(companyId);
		maximumPeriodLengthOfPracticums = this.repository.maximumPeriodLengthOfPracticum(companyId);
		totalNumberOfSessions = this.repository.totalNumberOfSession(companyId);
		averagePeriodLengthOfSessions = this.repository.averagePeriodLengthOfSession(companyId);
		deviationPeriodLengthOfSessions = this.repository.deviationPeriodLengthOfSession(companyId);
		minimumPeriodLengthOfSessions = this.repository.minimumPeriodLengthOfSession(companyId);
		maximumPeriodLengthOfSessions = this.repository.maximumPeriodLengthOfSession(companyId);

		object = new CompanyDashboard();
		object.setTotalNumberOfPracticums(totalNumberOfPracticums);
		object.setAveragePeriodLengthOfPracticums(averagePeriodLengthOfPracticums);
		object.setDeviationPeriodLengthOfPracticums(deviationPeriodLengthOfPracticums);
		object.setMinimumPeriodLengthOfPracticums(minimumPeriodLengthOfPracticums);
		object.setMaximumPeriodLengthOfPracticums(maximumPeriodLengthOfPracticums);
		object.setTotalNumberOfSessions(totalNumberOfSessions);
		object.setAveragePeriodLengthOfSessions(averagePeriodLengthOfSessions);
		object.setDeviationPeriodLengthOfSessions(deviationPeriodLengthOfSessions);
		object.setMinimumPeriodLengthOfSessions(minimumPeriodLengthOfSessions);
		object.setMaximumPeriodLengthOfSessions(maximumPeriodLengthOfSessions);

		super.getBuffer().setData(object);

	}

	@Override
	public void unbind(final CompanyDashboard object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "totalNumberOfPracticums", "averagePeriodLengthOfPracticums", "deviationPeriodLengthOfPracticums", "minimumPeriodLengthOfPracticums", "maximumPeriodLengthOfPracticums", "totalNumberOfSessions",
			"averagePeriodLengthOfSessions", "deviationPeriodLengthOfSessions", "minimumPeriodLengthOfSessions", "maximumPeriodLengthOfSessions");

		super.getResponse().setData(tuple);
	}
}
