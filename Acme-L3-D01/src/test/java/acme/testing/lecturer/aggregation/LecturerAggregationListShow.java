
package acme.testing.lecturer.aggregation;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.lecture.LectureCourseAggregation;
import acme.testing.TestHarness;

public class LecturerAggregationListShow extends TestHarness {

	@Autowired
	protected LecturerAggregationTestRepository repository;


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/lc-cour-agg-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive400ListShowLectureCourseAggregation(final int recordIndex, final String course, final String lecture) {
		final String lecturer1 = "lecturer1";
		super.signIn(lecturer1, lecturer1);
		super.requestHome();
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("course", course);
		super.checkInputBoxHasValue("lecture", lecture);

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
		}
		super.signOut();
	}
}
