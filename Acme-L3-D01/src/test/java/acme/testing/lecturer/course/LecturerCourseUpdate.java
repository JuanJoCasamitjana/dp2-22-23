
package acme.testing.lecturer.course;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.course.Course;
import acme.testing.TestHarness;

public class LecturerCourseUpdate extends TestHarness {

	@Autowired
	private LecturerCourseTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/course/updt-course-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative100updateCourse(final int recordIndex, final String code, final String title, final String abstractMessage, //
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
	@CsvFileSource(resources = "/lecturer/course/updt-cour-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive200updateCourse(final int recordIndex, final String code, final String title, final String abstractMessage, //
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
		super.checkListingExists();
		super.requestHome();
		super.signOut();
	}

	@Test
	public void hacking300Test() {
		//lecturer2 -> lecturer1 course
		final Collection<Course> courses = this.repository.findCoursesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Course c : courses) {
			final String param = "id=" + c.getId();
			super.request("/lecturer/course/update", param);
			super.checkPanicExists();
		}

	}
}
