
package acme.testing.lecturer;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.course.Course;
import acme.testing.TestHarness;

public class LecturerCoursesTest extends TestHarness {

	@Autowired
	private LecturerTestRepository	repository;

	final private String			lecturer8	= "lecturer8";
	private boolean					notCreated	= true;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-course-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	@CsvFileSource(resources = "/lecturer/updt-course-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative200updateCourse(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl) {
		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "List courses");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		super.requestHome();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/ls-cour-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive300ListShowCourses(final int recordIndex, final String code, final String title, //
		final String abstractMessage, final String type, final String price, final String link) {

		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "List courses");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, price);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("abstractMessage", abstractMessage);
		super.checkInputBoxHasValue("retailPrice", price);
		super.checkInputBoxHasValue("typeOfCourse", type);
		super.checkInputBoxHasValue("optionalUrl", link);
		super.requestHome();
		super.signOut();

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/del-cour-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive400CreateAndDelete(final int recordIndex, final String code, final String title, //
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
	@Test
	public void hacking500Test() {
		//lecturer2 -> lecturer1 course
		final Collection<Course> courses = this.repository.findCoursesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Course c : courses) {
			final String param = "id=" + c.getId();
			super.request("/lecturer/course/show", param);
			super.checkPanicExists();
			super.request("/lecturer/course/delete", param);
			super.checkPanicExists();
			super.request("/lecturer/course/update", param);
			super.checkPanicExists();
			super.request("/lecturer/lecture/list-of-course", param);
			super.checkPanicExists();

		}

	}

}
