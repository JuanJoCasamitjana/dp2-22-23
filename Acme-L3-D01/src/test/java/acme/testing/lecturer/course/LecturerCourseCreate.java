
package acme.testing.lecturer.course;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LecturerCourseCreate extends TestHarness {

	final private String	lecturer8	= "lecturer8";
	private boolean			notCreated	= true;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/course/cre-course-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative100createCourse(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl) {
		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		super.requestHome();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/course/del-cour-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive200CreateAndDelete(final int recordIndex, final String code, final String title, //
		final String abstractMessage, final String retailPrice, final String optionalUrl) {
		if (this.notCreated) {
			super.signUp(this.lecturer8, this.lecturer8, this.lecturer8, this.lecturer8, this.lecturer8 + "@mail.com");
			super.signIn(this.lecturer8, this.lecturer8);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer8);
			super.fillInputBoxIn("resume", this.lecturer8);
			super.fillInputBoxIn("listOfQualifications", this.lecturer8);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated = false;
		}

		super.signIn(this.lecturer8, this.lecturer8);
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		super.signOut();
	}
}
