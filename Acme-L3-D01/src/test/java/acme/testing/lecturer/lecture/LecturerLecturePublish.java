
package acme.testing.lecturer.lecture;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.Lecture;
import acme.testing.TestHarness;

public class LecturerLecturePublish extends TestHarness {

	@Autowired
	private LecturerLectureTestRepository	repository;

	private final String					lecturer1	= "lecturer1";
	private final String					lecturer2	= "lecturer2";


	//Los test de publish no cambian parámetros por lo que no necesitan ser tests parametricos
	@Test
	public void positive100Test() {
		final Collection<Lecture> lectures = this.repository.findNotPublishedLecturesByLecturerUname(this.lecturer1);
		super.signIn(this.lecturer1, this.lecturer1);
		for (final Lecture l : lectures) {
			final String param = "id=" + l.getId();
			super.request("/lecturer/lecture/publish", param);
			super.checkSubmitExists("Publish");
			super.clickOnSubmit("Publish");
			super.checkNotErrorsExist();
		}
	}

	@Test
	public void hacking200Test() {
		//lecturer2 -> lecturer1 course
		final Collection<Lecture> lectures = this.repository.findLecturesByLecturerUname(this.lecturer1);
		super.signIn(this.lecturer2, this.lecturer2);
		for (final Lecture l : lectures) {
			final String param = "id=" + l.getId();
			super.request("/lecturer/lecture/publish", param);
			super.checkPanicExists();
		}
		final Collection<Lecture> lectures2 = this.repository.findLecturesByLecturerUname(this.lecturer2);
		for (final Lecture l : lectures2)
			if (l.isPublished()) {
				final String param = "id=" + l.getId();
				super.request("/lecturer/lecture/publish", param);
				super.checkNotSubmitExists("Publish");
			}

	}
}
