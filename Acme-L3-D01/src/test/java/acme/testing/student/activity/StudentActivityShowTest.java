
package acme.testing.student.activity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class StudentActivityShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/student/activity/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String title, final String text, final String type, final String periodStart, final String periodEnd, final String enrolment, final String link) {

		super.signIn("student1", "student1");
		super.clickOnMenu("Student", "Activities");
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

	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/student/activity/create");
		super.checkPanicExists();
	}

}
