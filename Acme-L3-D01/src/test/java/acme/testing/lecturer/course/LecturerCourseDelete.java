
package acme.testing.lecturer.course;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.course.Course;
import acme.testing.TestHarness;

public class LecturerCourseDelete extends TestHarness {

	@Autowired
	private LecturerCourseTestRepository repository;


	@Test
	public void hacking200Test() {
		//lecturer2 -> lecturer1 course
		final Collection<Course> courses = this.repository.findCoursesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Course c : courses) {
			final String param = "id=" + c.getId();
			super.request("/lecturer/course/delete", param);
			super.checkPanicExists();
		}
		final Collection<Course> courses2 = this.repository.findCoursesByLecturerUname("lecturer2");
		for (final Course c : courses2)
			if (!c.isInDraft()) {
				final String param = "id=" + c.getId();
				super.request("/lecturer/course/delete", param);
				super.checkNotSubmitExists("Delete");
			}

	}
}
