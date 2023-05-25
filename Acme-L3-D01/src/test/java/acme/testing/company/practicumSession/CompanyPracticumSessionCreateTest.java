
package acme.testing.company.practicumSession;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.practicum.Practicum;
import acme.testing.TestHarness;

public class CompanyPracticumSessionCreateTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum-session/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int practicumRecordIndex, final int sessionRecordIndex, final String title, final String abstractMessage, final String periodStart, final String periodEnd, final String optionalLink, final String addendum,
		final String confirm) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();
		super.clickOnButton("Session list");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.checkFormExists();

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("optionalLink", optionalLink);
		if (addendum == "true")
			super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(practicumRecordIndex);
		super.clickOnButton("Session list");
		super.checkListingExists();

		super.checkColumnHasValue(sessionRecordIndex, 0, title);

		super.clickOnListingRecord(sessionRecordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("abstractMessage", abstractMessage);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		if (addendum == "true")
			super.checkInputBoxHasValue("confirm", confirm);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum-session/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int practicumRecordIndex, final int sessionRecordIndex, final String title, final String abstractMessage, final String periodStart, final String periodEnd, final String addendum, final String confirm) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(practicumRecordIndex);
		super.clickOnButton("Session list");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.checkFormExists();

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		if (addendum == "true")
			super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

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

	@Test
	public void test301Hacking() {

		Collection<Practicum> practicums;
		String param;

		practicums = this.repository.findManyPracticumByCompanyUsername("company1");
		for (final Practicum practicum : practicums)
			if (practicum.isPublished()) {
				param = String.format("id", practicum.getId());
				super.request("/company/practicum-session/create", param);
				super.checkPanicExists();
			}
	}

	@Test
	public void test302Hacking() {

		Collection<Practicum> practicums;
		String param;

		practicums = this.repository.findManyPracticumByCompanyUsername("company1");
		for (final Practicum practicum : practicums) {
			param = String.format("id", practicum.getId());
			super.request("/company/practicum-session/create", param);
			super.checkPanicExists();
		}
	}
}
