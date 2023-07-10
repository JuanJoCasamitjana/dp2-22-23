
package acme.testing.lecturer.lecture;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LecturerLectureCreate extends TestHarness {

	final private String	lecturer7	= "lecturer7";
	private boolean			notCreated	= true;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lecture/cre-lecture-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative100createLecture(final int recordIndex, final String title, final String abstractMessage, final String learningTime//
		, final String body, final String isTheoretical, final String optionalUrl) {

		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lecture/del-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive500CreateAndDelete(final int recordIndex, final String title, final String abstractMessage, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrl) {
		if (this.notCreated) {
			super.signUp(this.lecturer7, this.lecturer7, this.lecturer7, this.lecturer7, this.lecturer7 + "@mail.com");
			super.signIn(this.lecturer7, this.lecturer7);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer7);
			super.fillInputBoxIn("resume", this.lecturer7);
			super.fillInputBoxIn("listOfQualifications", this.lecturer7);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated = false;
		}

		super.signIn(this.lecturer7, this.lecturer7);
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();

		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		super.signOut();
	}
}
