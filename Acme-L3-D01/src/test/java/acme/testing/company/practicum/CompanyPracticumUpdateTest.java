
package acme.testing.company.practicum;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.practicum.Practicum;
import acme.testing.TestHarness;

public class CompanyPracticumUpdateTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumTestRepository repository;
	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int practicumRecordIndex, final String code, final String title, final String abstractMessage, final String goals, final String course) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(practicumRecordIndex, 0, code);

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("abstractMessage", abstractMessage);
		super.checkInputBoxHasValue("goals", goals);

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("goals", goals);
		super.clickOnSubmit("Update");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(practicumRecordIndex, 0, code);

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("abstractMessage", abstractMessage);
		super.checkInputBoxHasValue("goals", goals);
		super.checkInputBoxHasValue("course", course);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int practicumRecordIndex, final String code, final String title, final String abstractMessage, final String goals, final String course) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(practicumRecordIndex, 0, code);

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("goals", goals);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	public void test300Hacking() {

		Collection<Practicum> practicums;
		String param;

		practicums = this.repository.findManyPracticumByCompanyUsername("company1");
		for (final Practicum practicum : practicums) {
			param = String.format("id=%d", practicum.getId());

			super.checkLinkExists("Sign in");
			super.request("/company/practicum/update", param);
			super.checkPanicExists();

			super.signIn("administrator1", "administrator1");
			super.request("/company/practicum/update", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("company2", "company2");
			super.request("/company/practicum/update", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("student1", "student1");
			super.request("/company/practicum/update", param);
			super.checkPanicExists();
			super.signOut();
		}
	}

	@Test
	public void test301Hacking() {

		Collection<Practicum> practicums;
		String param;

		super.signIn("company1", "company1");
		practicums = this.repository.findManyPracticumByCompanyUsername("company1");
		for (final Practicum practicum : practicums)
			if (!practicum.isPublished() == true) {
				param = String.format("id=%d", practicum.getId());
				super.request("/company/practicum/update", param);
				super.checkPanicExists();
			}
		super.signOut();
	}

}
