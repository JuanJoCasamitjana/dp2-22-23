
package acme.testing.lecturer.lecture;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.Lecture;
import acme.testing.TestHarness;

public class LecturerLectureDelete extends TestHarness {

	@Autowired
	private LecturerLectureTestRepository repository;


	@Test
	public void positive100Test() {
		super.signIn("lecturer2", "lecturer2");
		final Collection<Lecture> lectures2 = this.repository.findLecturesByLecturerUname("lecturer2");
		for (final Lecture l : lectures2)
			if (!l.isPublished()) {
				final String param = "id=" + l.getId();
				super.request("/lecturer/lecture/delete", param);
				super.clickOnSubmit("Delete");
				super.request("/lecturer/lecture/delete", param);
				super.checkPanicExists();
			}
	}

	@Test
	public void hacking600Test() {
		//lecturer2 -> lecturer1 lecture
		final Collection<Lecture> lectures = this.repository.findLecturesByLecturerUname("lecturer1");
		super.signIn("lecturer2", "lecturer2");
		for (final Lecture l : lectures) {
			final String param = "id=" + l.getId();
			super.request("/lecturer/lecture/delete", param);
			super.checkPanicExists();
		}
		final Collection<Lecture> lectures2 = this.repository.findLecturesByLecturerUname("lecturer2");
		for (final Lecture l : lectures2)
			if (l.isPublished()) {
				final String param = "id=" + l.getId();
				super.request("/lecturer/lecture/delete", param);
				super.checkNotSubmitExists("Delete");
			}
	}
}
