
package acme.testing.student.enrolment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class StudentEnrolmentListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/student/enrolment/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String course) {

		super.signIn("student1", "student1");
		super.clickOnMenu("Student", "Enrolments");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, course);

		super.signOut();
	}

	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/student/enrolment/list");
		super.checkPanicExists();
	}
}
