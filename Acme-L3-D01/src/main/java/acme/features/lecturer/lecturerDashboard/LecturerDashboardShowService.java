/*
 * AdministratorDashboardShowService.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.forms.LecturerDashboard;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerDashboardShowService extends AbstractService<Lecturer, LecturerDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final LecturerDashboard dashboard = new LecturerDashboard();
		final int lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		final int totalTheoryLectures = this.repository.totalNumberOfTheoryLectures(lecturerId);
		final int totalHandsOnLectures = this.repository.totalNumberOfHandsOnLectures(lecturerId);
		final double maxLearningTimeLectures = this.repository.maxLearningTimeOfLectures(lecturerId);
		final double minLearningTimeLectures = this.repository.minLearningTimeOfLectures(lecturerId);
		final double avgLearningTimeLectures = this.repository.averageLearningTimeOfLectures(lecturerId);
		final double stdLearningTimeLectures = this.repository.deviationLearningTimeOfLectures(lecturerId);
		final Collection<Course> courses = this.repository.findAllCoursesOfLecturer(lecturerId);
		double maxLearningTimeCourses = 0.;
		double minLearningTimeCourses = Double.MAX_VALUE;
		double avgLearningTimeCourses = 0.;
		double stdLearningTimeCourses = 0.;
		double totalTime = 0.;
		final List<Double> times = new ArrayList<>();
		for (final Course c : courses) {
			final double learningTimeOfCourse = this.repository.totalLearningTimeOfCourse(lecturerId, c.getId());
			if (learningTimeOfCourse >= maxLearningTimeCourses)
				maxLearningTimeCourses = learningTimeOfCourse;
			if (learningTimeOfCourse <= minLearningTimeCourses)
				minLearningTimeCourses = learningTimeOfCourse;
			times.add(learningTimeOfCourse);
			totalTime += learningTimeOfCourse;
		}
		avgLearningTimeCourses = totalTime / times.size();
		final double avg = avgLearningTimeCourses;
		stdLearningTimeCourses = Math.sqrt(times.stream().map(d -> Math.pow(d - avg, 2)).collect(Collectors.summingDouble(Double::doubleValue)) / times.size());
		dashboard.setAverageLearningTimeOfCourses(avgLearningTimeCourses);
		dashboard.setAverageLearningTimeOfLectures(avgLearningTimeLectures);
		dashboard.setDeviationLearningTimeOfCourses(stdLearningTimeCourses);
		dashboard.setDeviationLearningTimeOfLectures(stdLearningTimeLectures);
		dashboard.setMaximumLearningTimeOfCourses(maxLearningTimeCourses);
		dashboard.setMaximumLearningTimeOfLectures(maxLearningTimeLectures);
		dashboard.setMinimumLearningTimeOfCourses(minLearningTimeCourses);
		dashboard.setMinimumLearningTimeOfLectures(minLearningTimeLectures);
		dashboard.setTheoryLectures(totalTheoryLectures);
		dashboard.setHandsOnLectures(totalHandsOnLectures);
		super.getBuffer().setData(dashboard);
	}

	@Override
	public void unbind(final LecturerDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, //
			"theoryLectures", "handsOnLectures", "averageLearningTimeOfLectures", //
			"deviationLearningTimeOfLectures", "minimumLearningTimeOfLectures", //
			"maximumLearningTimeOfLectures", "averageLearningTimeOfCourses", //
			"deviationLearningTimeOfCourses", "minimumLearningTimeOfCourses", //
			"maximumLearningTimeOfCourses");
		super.getResponse().setData(tuple);
	}

}
