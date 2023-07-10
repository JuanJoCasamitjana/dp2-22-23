
package acme.testing.student.activity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class StudentActivityUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/student/activity/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String title, final String text, final String type, final String periodStart, final String periodEnd, final String enrolment, final String link) {

		super.signIn("student1", "student1");
		super.clickOnMenu("Student", "Activities");
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("enrolment", enrolment);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.clickOnMenu("Student", "Activities");
		this.checkListingExists();

		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("type_proxy", type);
		super.checkInputBoxHasValue("enrolment_proxy", enrolment);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("link", link);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/student/activity/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Negative(final int recordIndex, final String title, final String text, final String type, final String periodStart, final String periodEnd, final String enrolment, final String link) {

		super.signIn("student1", "student1");
		super.clickOnMenu("Student", "Activities");
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("enrolment", enrolment);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

		super.signOut();

	}

	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/student/activity/update");
		super.checkPanicExists();

	}

}
