
package acme.testing.lecturer.aggregation;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.LectureCourseAggregation;
import acme.testing.TestHarness;

public class LecturerAggregationDelete extends TestHarness {

	@Autowired
	protected LecturerAggregationTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/lc-cour-agg-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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
		//lecturer1 -> lecturer2 aggregation
		final Collection<LectureCourseAggregation> aggregations = this.repository.findAggregationsByLecturerUname("lecturer2");
		super.signIn("lecturer1", "lecturer1");
		for (final LectureCourseAggregation lca : aggregations) {
			final String param = "id=" + lca.getId();
			super.request("/lecturer/lecture-course-aggregation/delete", param);
			super.checkPanicExists();
		}
		final Collection<LectureCourseAggregation> aggregations2 = this.repository.findAggregationsByLecturerUname("lecturer1");
		for (final LectureCourseAggregation lca : aggregations2) {
			final String param = "id=" + lca.getId();
			super.request("/lecturer/lecture-course-aggregation/delete", param);
			super.checkSubmitExists("Delete");
		}
		super.signOut();
	}
}
