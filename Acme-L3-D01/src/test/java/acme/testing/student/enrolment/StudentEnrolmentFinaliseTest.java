
package acme.testing.student.enrolment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class StudentEnrolmentFinaliseTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/student/enrolment/finalise-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String motivation, final String goals, final String course, final String workTime, final String holderName, final String lowerNibble, final String draft,
		final String creditCard, final String expiryDate, final String cvc) {

		super.signIn("student2", "student2");
		super.clickOnMenu("Student", "Enrolments");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("holderName", holderName);
		super.fillInputBoxIn("creditCard", creditCard);
		super.fillInputBoxIn("expiryDate", expiryDate);
		super.fillInputBoxIn("cvc", cvc);

		super.clickOnSubmit("Finalise");

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

	@ParameterizedTest
	@CsvFileSource(resources = "/student/enrolment/finalise-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Negative(final int recordIndex, final String code, final String motivation, final String goals, final String course, final String workTime, final String holderName, final String draft, final String creditCard,
		final String expiryDate, final String cvc) {

		super.signIn("student2", "student2");
		super.clickOnMenu("Student", "Enrolments");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("holderName", holderName);
		super.fillInputBoxIn("creditCard", creditCard);
		super.fillInputBoxIn("expiryDate", expiryDate);
		super.fillInputBoxIn("cvc", cvc);

		super.clickOnSubmit("Finalise");
		super.checkErrorsExist();

		super.signOut();
	}
	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/student/enrolment/finalise");
		super.checkPanicExists();
	}
}
