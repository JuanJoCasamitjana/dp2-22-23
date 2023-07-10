
package acme.testing.student.enrolment;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.enrolment.Enrolment;
import acme.testing.TestHarness;

public class StudentEnrolmentShowTest extends TestHarness {

	@Autowired
	protected StudentEnrolmentRepositoryTest repository;


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
		final Collection<Enrolment> enrolments = this.repository.findAllEnrolmentsByStudentUserName("student1");
		for (final Enrolment enrolment : enrolments) {
			String param;
			param = String.format("id=%d", enrolment.getId());

			super.checkLinkExists("Sign in");
			super.request("/student/enrolment/show", param);
			super.checkPanicExists();

			super.signIn("student3", "student3");
			super.request("/student/enrolment/show", param);
			super.checkPanicExists();
			super.signOut();

			super.signIn("student2", "student2");
			super.request("/student/enrolment/show", param);
			super.checkPanicExists();
			super.signOut();
		}
	}
}
