
package acme.testing.lecturer.lecture;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.Lecture;
import acme.testing.TestHarness;

public class LecturerLectureListShow extends TestHarness {

	@Autowired
	private LecturerLectureTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lecture/ls-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	@CsvFileSource(resources = "/lecturer/lecture/ls-oc-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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

	@Test
	public void hacking600Test() {
		//lecturer2 -> lecturer1 lecture
		final Collection<Lecture> lectures = this.repository.findLecturesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Lecture l : lectures) {
			final String param = "id=" + l.getId();
			super.request("/lecturer/lecture/show", param);
			super.checkPanicExists();
		}
	}
}
