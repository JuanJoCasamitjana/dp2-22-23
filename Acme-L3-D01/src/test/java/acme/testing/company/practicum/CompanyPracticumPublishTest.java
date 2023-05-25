
package acme.testing.company.practicum;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.practicum.Practicum;
import acme.entities.practicum.PracticumSession;
import acme.testing.TestHarness;

public class CompanyPracticumPublishTest extends TestHarness {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumTestRepository repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int practicumRecordIndex, final String code) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(practicumRecordIndex, 0, code);

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("code", code);

		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/company/practicum/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int practicumRecordIndex, final String code) {

		super.signIn("company1", "company1");

		super.clickOnMenu("Company", "My practicum list");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(practicumRecordIndex, 0, code);

		super.clickOnListingRecord(practicumRecordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("code", code);

		super.clickOnSubmit("Publish");
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
			super.request("/company/practicum/publish", param);
			super.checkPanicExists();

			super.signIn("administrator1", "administrator1");
			super.request("/company/practicum/publish", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("company2", "company2");
			super.request("/company/practicum/publish", param);
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
			if (practicum.isPublished() == true) {
				param = String.format("id=%d", practicum.getId());
				super.request("/company/practicum/publish", param);
				super.checkPanicExists();
			}
		super.signOut();
	}

	@Test
	public void test302Hacking() {

		Collection<Practicum> practicums;
		Collection<PracticumSession> sesiones;
		String param;

		super.signIn("company1", "company1");
		practicums = this.repository.findManyPracticumByCompanyUsername("company1");
		for (final Practicum practicum : practicums) {
			sesiones = this.repository.findManyPracticumSessionByPracticumId(practicum.getId());
			if (sesiones.isEmpty()) {
				param = String.format("id=%d", practicum.getId());
				super.request("/company/practicum/publish", param);
				super.checkPanicExists();
			}
		}
		super.signOut();
	}
}
