
package acme.testing.student.enrolment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class StudentEnrolmentShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/student/enrolment/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String motivation, final String goals, final String course, final String workTime, final String holderName, final String lowerNibble, final String draft) {

		super.signIn("student1", "student1");
		super.clickOnMenu("Student", "Enrolments");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("motivation", motivation);
		super.checkInputBoxHasValue("goals", goals);
		super.checkInputBoxHasValue("course", course);
		super.checkInputBoxHasValue("lowerNibble", lowerNibble);
		super.checkInputBoxHasValue("holderName", holderName);
		super.checkInputBoxHasValue("workTime", workTime);
		super.checkInputBoxHasValue("draft", draft);

		super.signOut();
	}
	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/student/enrolment/show");
		super.checkPanicExists();
	}
}
