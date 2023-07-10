
package acme.testing.lecturer.course;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.course.Course;
import acme.testing.TestHarness;

public class LecturerCourseListShow extends TestHarness {

	@Autowired
	private LecturerCourseTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/course/ls-cour-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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
		super.requestHome();
		super.signOut();

	}
	@Test
	public void hacking200Test() {
		//lecturer2 -> lecturer1 course
		final Collection<Course> courses = this.repository.findCoursesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Course c : courses) {
			final String param = "id=" + c.getId();
			super.request("/lecturer/course/show", param);
			super.checkPanicExists();
			super.request("/lecturer/lecture/list-of-course", param);
			super.checkPanicExists();
		}

	}
}
