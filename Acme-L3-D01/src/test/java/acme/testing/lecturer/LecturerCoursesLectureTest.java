
package acme.testing.lecturer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LecturerCoursesLectureTest extends TestHarness {

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

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-cour-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive100CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		final String lecturer = "lecturer3";
		super.signUp(lecturer, lecturer, lecturer, lecturer, lecturer + "@mail.com");
		super.signIn(lecturer, lecturer);
		super.clickOnMenu("Account", "Become a Lecturer");
		super.fillInputBoxIn("almaMater", lecturer);
		super.fillInputBoxIn("resume", lecturer);
		super.fillInputBoxIn("listOfQualifications", lecturer);
		super.clickOnSubmit("Register");

		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", titleLecture);
		super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnLink("Add to course");

		super.clickOnSubmit("Create");
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.checkColumnHasValue(0, 0, code);
		super.checkColumnHasValue(0, 1, titleLecture);

		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.checkNotFormExists();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-cour-lect-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative400CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		final String lecturer = "lecturer5";
		super.signUp(lecturer, lecturer, lecturer, lecturer, lecturer + "@mail.com");
		super.signIn(lecturer, lecturer);
		super.clickOnMenu("Account", "Become a Lecturer");
		super.fillInputBoxIn("almaMater", lecturer);
		super.fillInputBoxIn("resume", lecturer);
		super.fillInputBoxIn("listOfQualifications", lecturer);
		super.clickOnSubmit("Register");

		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", titleLecture);
		super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnLink("Add to course");

		super.clickOnSubmit("Create");
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.checkColumnHasValue(0, 0, code);
		super.checkColumnHasValue(0, 1, titleLecture);

		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.checkErrorsExist();
	}
}
