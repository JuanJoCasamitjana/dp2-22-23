
package acme.testing.lecturer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LecturerCoursesLectureTest extends TestHarness {

	final private String	lecturer3	= "lecturer3";
	final private String	lecturer5	= "lecturer5";
	final private String	lecturer6	= "lecturer6";


	@BeforeAll
	public void initializeNewLecturers() {
		super.signUp(this.lecturer3, this.lecturer3, this.lecturer3, this.lecturer3, this.lecturer3 + "@mail.com");
		super.signIn(this.lecturer3, this.lecturer3);
		super.clickOnMenu("Account", "Become a Lecturer");
		super.fillInputBoxIn("almaMater", this.lecturer3);
		super.fillInputBoxIn("resume", this.lecturer3);
		super.fillInputBoxIn("listOfQualifications", this.lecturer3);
		super.clickOnSubmit("Register");
		super.signOut();
		super.signUp(this.lecturer5, this.lecturer5, this.lecturer5, this.lecturer5, this.lecturer5 + "@mail.com");
		super.signIn(this.lecturer5, this.lecturer5);
		super.clickOnMenu("Account", "Become a Lecturer");
		super.fillInputBoxIn("almaMater", this.lecturer5);
		super.fillInputBoxIn("resume", this.lecturer5);
		super.fillInputBoxIn("listOfQualifications", this.lecturer5);
		super.clickOnSubmit("Register");
		super.signOut();
		super.signUp(this.lecturer6, this.lecturer6, this.lecturer6, this.lecturer6, this.lecturer6 + "@mail.com");
		super.signIn(this.lecturer6, this.lecturer6);
		super.clickOnMenu("Account", "Become a Lecturer");
		super.fillInputBoxIn("almaMater", this.lecturer6);
		super.fillInputBoxIn("resume", this.lecturer6);
		super.fillInputBoxIn("listOfQualifications", this.lecturer6);
		super.clickOnSubmit("Register");
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
		super.requestHome();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-cour-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive100CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		super.signIn(this.lecturer3, this.lecturer3);
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
		super.requestHome();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-cour-lect-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative400CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		super.signIn(this.lecturer5, this.lecturer5);
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
		super.requestHome();
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-cour-lect-negative-lnp.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative700CreateCourseAndLectureAndPublishButLectureNotPublished(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		super.signIn(this.lecturer6, this.lecturer6);
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

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.checkErrorsExist();
		super.requestHome();
		super.signOut();
	}
	//Test cases exclusive to course
	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-course-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative500createCourse(final int recordIndex, final String code, final String title, final String abstractMessage, //
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
	public void negative900updateCourse(final int recordIndex, final String code, final String title, final String abstractMessage, //
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
	//Test cases exclusive to Lecture
	//	@ParameterizedTest
	//	@CsvFileSource(resources = "/lecturer/cre-lecture-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	//	public void negative600createLecture() {
	//
	//	}

	//	@ParameterizedTest
	//	@CsvFileSource(resources = "/lecturer/lc-cour-agg-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	//	public void positive800ShowListDeleteLectureCourseAggregation() {
	//
	//		String lecturer1 = "lecturer1";
	//		super.signIn(lecturer1, lecturer1);
	//		super.requestHome();
	//		super.clickOnMenu("Lecturer", "See courses with lectures");
	//		super.checkListingExists();
	//		
	//	}

}
