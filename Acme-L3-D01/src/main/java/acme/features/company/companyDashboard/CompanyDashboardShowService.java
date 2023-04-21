
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
		CompanyDashboard object;
		final Integer theoryPractica;
		final Integer handsOnPractica;
		final Double averagePeriodLengthOfSession;
		final Double deviationPeriodLengthOfSession;
		final Double minimumPeriodLengthOfSession;
		final Double maximunmPeriodLengthOfSession;
		final Double averagePeriodLengthOfPractica;
		final Double deviationPeriodLengthOfPractica;
		final Double minimumPeriodLengthOfPractica;
		final Double maximunmPeriodLengthOfPractica;

		//		theoryPractica = this.repository.theoryPractica();
		//		handsOnPractica = this.repository.handsOnPractica();
		//		averagePeriodLengthOfSession = this.repository.averagePeriodLengthOfSession();
		//		deviationPeriodLengthOfSession = this.repository.deviationPeriodLengthOfSession();
		//		minimumPeriodLengthOfSession = this.repository.minimumPeriodLengthOfSession();
		//		maximunmPeriodLengthOfSession = this.repository.maximunmPeriodLengthOfSession();
		//		averagePeriodLengthOfPractica = this.repository.averagePeriodLengthOfPractica();
		//		deviationPeriodLengthOfPractica = this.repository.deviationPeriodLengthOfPractica();
		//		minimumPeriodLengthOfPractica = this.repository.minimumPeriodLengthOfPractica();
		//		maximunmPeriodLengthOfPractica = this.repository.maximunmPeriodLengthOfPractica();

		object = new CompanyDashboard();
		//		object.setTheoryPractica(theoryPractica);
		//		object.setHandsOnPractica(handsOnPractica);
		//		object.setAveragePeriodLengthOfSession(averagePeriodLengthOfSession);
		//		object.setDeviationPeriodLengthOfSession(deviationPeriodLengthOfSession);
		//		object.setMinimumPeriodLengthOfSession(minimumPeriodLengthOfSession);
		//		object.setMaximunmPeriodLengthOfSession(maximunmPeriodLengthOfSession);
		//		object.setAveragePeriodLengthOfPractica(averagePeriodLengthOfPractica);
		//		object.setDeviationPeriodLengthOfPractica(deviationPeriodLengthOfPractica);
		//		object.setMinimumPeriodLengthOfPractica(minimumPeriodLengthOfPractica);
		//		object.setMaximunmPeriodLengthOfPractica(maximunmPeriodLengthOfPractica);

		super.getBuffer().setData(object);

	}

	@Override
	public void unbind(final CompanyDashboard object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "theoryPractica", "handsOnPractica", "averagePeriodLengthOfSession", "deviationPeriodLengthOfSession", "minimumPeriodLengthOfSession", "maximunmPeriodLengthOfSession", "averagePeriodLengthOfPractica",
			"deviationPeriodLengthOfPractica", "minimumPeriodLengthOfPractica", "maximunmPeriodLengthOfPractica");

		super.getResponse().setData(tuple);
	}
}
