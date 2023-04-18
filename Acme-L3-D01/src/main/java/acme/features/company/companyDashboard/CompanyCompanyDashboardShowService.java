
package acme.features.company.companyDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.CompanyDashboard;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyCompanyDashboardShowService extends AbstractService<Company, CompanyDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyCompanyDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean rolOk;
		int companyId;
		Company company;

		companyId = super.getRequest().getData("id", int.class);
		company = this.repository.findOneCompanyById(companyId);
		rolOk = super.getRequest().getPrincipal().hasRole(Company.class);
		status = company != null && rolOk;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		CompanyDashboard object;

		object = new CompanyDashboard();

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
