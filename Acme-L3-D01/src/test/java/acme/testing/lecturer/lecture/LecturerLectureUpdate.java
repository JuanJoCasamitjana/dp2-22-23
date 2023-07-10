
package acme.testing.lecturer.lecture;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.Lecture;
import acme.testing.TestHarness;

public class LecturerLectureUpdate extends TestHarness {

	@Autowired
	private LecturerLectureTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lecture/del-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive100updateLecture(final int recordIndex, final String title, final String abstractMessage, final String learningTime//
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
		super.checkListingExists();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/lecture/cre-lecture-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
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

	@Test
	public void hacking600Test() {
		//lecturer2 -> lecturer1 lecture
		final Collection<Lecture> lectures = this.repository.findLecturesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Lecture l : lectures) {
			final String param = "id=" + l.getId();
			super.request("/lecturer/lecture/update", param);
			super.checkPanicExists();
		}
	}
}
