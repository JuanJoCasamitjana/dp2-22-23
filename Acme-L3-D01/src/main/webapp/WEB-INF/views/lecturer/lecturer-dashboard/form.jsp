<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<h2>
	<acme:message code="lecturer.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.total.theory"/>
		</th>
		<td>
			<acme:print value="${theoryLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.total.hands.on"/>
		</th>
		<td>
			<acme:print value="${handsOnLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.avg.lectures"/>
		</th>
		<td>
			<acme:print value="${averageLearningTimeOfLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.std.lectures"/>
		</th>
		<td>
			<acme:print value="${deviationLearningTimeOfLectures}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.max.lectures"/>
		</th>
		<td>
			<acme:print value="${maximumLearningTimeOfLectures}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.min.lectures"/>
		</th>
		<td>
			<acme:print value="${minimumLearningTimeOfLectures}"/>
		</td>
	</tr>	
		
		
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.avg.courses"/>
		</th>
		<td>
			<acme:print value="${averageLearningTimeOfCourses}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.std.courses"/>
		</th>
		<td>
			<acme:print value="${deviationLearningTimeOfCourses}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.min.courses"/>
		</th>
		<td>
			<acme:print value="${minimumLearningTimeOfCourses}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.max.courses"/>
		</th>
		<td>
			<acme:print value="${maximumLearningTimeOfCourses}"/>
		</td>
	</tr>	
	
</table>
<acme:return/>

