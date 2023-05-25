
package acme.testing.lecturer;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.Lecture;
import acme.testing.TestHarness;

public class LecturerLectureTests extends TestHarness {

	@Autowired
	private LecturerTestRepository	repository;

	final private String			lecturer7	= "lecturer7";
	private boolean					notCreated	= true;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/cre-lecture-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	@CsvFileSource(resources = "/lecturer/cre-lecture-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative200updateLecture(final int recordIndex, final String title, final String abstractMessage, final String learningTime//
		, final String body, final String isTheoretical, final String optionalUrl) {

		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "List lectures");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/ls-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive300ListShowLectures(final int recordIndex, final String title, final String abstractMessage, //
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
	@CsvFileSource(resources = "/lecturer/ls-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive400ListShowLecturesOfCourse(final int recordIndex, final String title, final String abstractMessage, //
		final String time, final String body, final boolean isTheoretical, final String link) {

		super.requestHome();
		super.signIn("lecturer1", "lecturer1");
		super.clickOnMenu("Lecturer", "List courses");
		super.checkListingExists();

		super.clickOnListingRecord(0);
		super.clickOnLink("List lectures of this course");
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
	@CsvFileSource(resources = "/lecturer/del-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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

	@Test
	public void hacking600Test() {
		//lecturer2 -> lecturer1 lecture
		final Collection<Lecture> lectures = this.repository.findLecturesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Lecture l : lectures) {
			final String param = "id=" + l.getId();
			super.request("/lecturer/lecture/show", param);
			super.checkPanicExists();
			super.request("/lecturer/lecture/delete", param);
			super.checkPanicExists();
			super.request("/lecturer/lecture/update", param);
			super.checkPanicExists();
		}
	}
}
