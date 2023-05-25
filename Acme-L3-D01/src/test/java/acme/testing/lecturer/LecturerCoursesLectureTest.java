
package acme.testing.lecturer;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.LectureCourseAggregation;
import acme.testing.TestHarness;

public class LecturerCoursesLectureTest extends TestHarness {

	@Autowired
	private LecturerTestRepository	repository;

	final private String			lecturer3	= "lecturer3";
	private boolean					notCreated3	= true;
	final private String			lecturer5	= "lecturer5";
	private boolean					notCreated5	= true;
	final private String			lecturer6	= "lecturer6";
	private boolean					notCreated6	= true;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-cour-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive100CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		if (this.notCreated3) {
			super.signUp(this.lecturer3, this.lecturer3, this.lecturer3, this.lecturer3, this.lecturer3 + "@mail.com");
			super.signIn(this.lecturer3, this.lecturer3);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer3);
			super.fillInputBoxIn("resume", this.lecturer3);
			super.fillInputBoxIn("listOfQualifications", this.lecturer3);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated3 = false;
		}
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
	public void negative200CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		if (this.notCreated5) {
			super.signUp(this.lecturer5, this.lecturer5, this.lecturer5, this.lecturer5, this.lecturer5 + "@mail.com");
			super.signIn(this.lecturer5, this.lecturer5);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer5);
			super.fillInputBoxIn("resume", this.lecturer5);
			super.fillInputBoxIn("listOfQualifications", this.lecturer5);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated5 = false;
		}

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
	public void negative300CreateCourseAndLectureAndPublishButLectureNotPublished(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {

		if (this.notCreated6) {
			super.signUp(this.lecturer6, this.lecturer6, this.lecturer6, this.lecturer6, this.lecturer6 + "@mail.com");
			super.signIn(this.lecturer6, this.lecturer6);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer6);
			super.fillInputBoxIn("resume", this.lecturer6);
			super.fillInputBoxIn("listOfQualifications", this.lecturer6);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated6 = false;
		}

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

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lc-cour-agg-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive400ListShowDeleteLectureCourseAggregation(final int recordIndex, final String course, final String lecture) {
		final String lecturer1 = "lecturer1";
		super.signIn(lecturer1, lecturer1);
		super.requestHome();
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("course", course);
		super.checkInputBoxHasValue("lecture", lecture);
		super.clickOnSubmit("Delete");
		super.checkListingExists();

	}

	@Test
	public void hacking500Test() {
		//lecturer2 -> lecturer1 aggregation
		final Collection<LectureCourseAggregation> aggregations = this.repository.findAggregationsByLecturerUname("lecturer2");
		super.signIn("lecturer1", "lecturer1");
		for (final LectureCourseAggregation lca : aggregations) {
			final String param = "id=" + lca.getId();
			super.request("/lecturer/lecture-course-aggregation/show", param);
			super.checkPanicExists();
			super.request("/lecturer/lecture-course-aggregation/delete", param);
			super.checkPanicExists();
		}
		super.signOut();
	}

}
