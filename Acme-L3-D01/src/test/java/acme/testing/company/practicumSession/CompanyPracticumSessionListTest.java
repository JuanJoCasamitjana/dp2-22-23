
package acme.testing.company.practicumSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.testing.TestHarness;

public class CompanyPracticumSessionListTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum-session/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int practicumRecordIndex, final String code, final int sessionRecordIndex, final String title, final String periodStart, final String periodEnd, final String totalTime, final String addendum) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(practicumRecordIndex, 0, code);

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();
		super.clickOnButton("Session list");
		super.checkListingExists();

		super.checkColumnHasValue(sessionRecordIndex, 0, title);
		super.checkColumnHasValue(sessionRecordIndex, 1, periodStart);
		super.checkColumnHasValue(sessionRecordIndex, 2, periodEnd);
		super.checkColumnHasValue(sessionRecordIndex, 3, totalTime);
		super.checkColumnHasValue(sessionRecordIndex, 4, addendum);

		super.signOut();
	}

	@Test
	public void test300Hacking() {

		super.checkLinkExists("Sign in");
		super.request("/company/practicum-session/create");
		super.checkPanicExists();

		super.signIn("administrator1", "administrator1");
		super.request("/company/practicum-session/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("student1", "student1");
		super.request("/company/practicum-session/create");
		super.checkPanicExists();
		super.signOut();

	}

}
