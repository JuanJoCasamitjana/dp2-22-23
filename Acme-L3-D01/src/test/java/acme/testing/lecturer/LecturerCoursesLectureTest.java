
package acme.testing.lecturer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LecturerCoursesLectureTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/ls-cour-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive100ListShowCourses(final int recordIndex, final String code, final String title, //
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

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/ls-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive200ListShowLectures(final int recordIndex, final String title, final String abstractMessage, //
		final String time, final String body, final boolean isTheoretical, final String link) {

		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "List lectures");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, time);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("abstractMessage", abstractMessage);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("learningTime", time);
		super.checkInputBoxHasValue("optionalUrl", link);
	}
}
