<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|delete')}">
			<acme:input-textbox code="lecturer.aggregation.form.label.course.code" path="course.code"/>
			<acme:input-textbox code="lecturer.aggregation.form.label.course.title" path="course.title"/>
			<acme:input-textbox code="lecturer.aggregation.form.label.lecture.title" path="lecture.title"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:input-select code="lecturer.aggregation.form.label.select.course" path="course" choices="${courses}"/>
			<acme:input-select code="lecturer.aggregation.form.label.select.lecture" path="lecture" choices="${lectures}"/>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|delete')}">
			<acme:submit code="lecturer.aggregation.form.button.delete" action="/lecturer/lecture-course-aggregation/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.aggregation.form.button.create" action="/lecturer/lecture-course-aggregation/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>

