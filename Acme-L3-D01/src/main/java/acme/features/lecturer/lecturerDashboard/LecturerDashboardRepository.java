/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.lecturerDashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface LecturerDashboardRepository extends AbstractRepository {

	@Query("SELECT count(l) FROM Lecture l WHERE l.lecturer.id = :id AND l.theoretical = true")
	int totalNumberOfTheoryLectures(int id);
	@Query("SELECT count(l) FROM Lecture l WHERE l.lecturer.id = :id AND l.theoretical = false")
	int totalNumberOfHandsOnLectures(int id);
	@Query("SELECT min(l.learningTime) FROM Lecture l WHERE l.lecturer.id = :id")
	double minLearningTimeOfLectures(int id);
	@Query("SELECT max(l.learningTime) FROM Lecture l WHERE l.lecturer.id = :id")
	double maxLearningTimeOfLectures(int id);
	@Query("SELECT avg(l.learningTime) FROM Lecture l WHERE l.lecturer.id = :id")
	double averageLearningTimeOfLectures(int id);
	@Query("SELECT stddev(l.learningTime) FROM Lecture l WHERE l.lecturer.id = :id")
	double deviationLearningTimeOfLectures(int id);
	@Query("SELECT sum(lca.lecture.learningTime) FROM LectureCourseAggregation lca WHERE lca.lecture.lecturer.id = :id AND lca.course.id = :courseId")
	double totalLearningTimeOfCourse(int id, int courseId);
	@Query("SELECT c FROM Course c WHERE c.lecturer.id = :lecturerId")
	Collection<Course> findAllCoursesOfLecturer(int lecturerId);

}
