<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for student particular
- purposes.  The copyright owner does not offer student warranties or representations, nor do
- they accept student liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<jstl:choose>
		<jstl:when
			test="${_command == 'show' || _command == 'update' || _command == 'delete'}">
			<acme:input-textbox code="student.activity.list.label.title"
				path="title" />
			<acme:input-textbox code="student.activity.list.label.text"
				path="text" />
			<acme:input-select code="student.activity.list.label.types"
				path="type" choices="${types}" />
			<acme:input-select code="student.activity.list.label.enrolments"
				path="enrolment" choices="${enrolments}" />

			<acme:input-moment code="student.activity.list.label.periodStart"
				path="periodStart" />
			<acme:input-moment code="student.activity.list.label.periodEnd"
				path="periodEnd" />
			<acme:input-textbox code="student.activity.list.label.link"
				path="link" />

			<jstl:if test="${draft == false}">
				<acme:submit code="student.activity.form.button.update"
					action="/student/activity/update" />
				<acme:submit code="student.activity.form.button.delete"
					action="/student/activity/delete" />
			</jstl:if>

		</jstl:when>
		<jstl:when test="${_command == 'create' }">
			<acme:input-textbox code="student.activity.list.label.title"
				path="title" />
			<acme:input-textbox code="student.activity.list.label.text"
				path="text" />
			<acme:input-select code="student.activity.list.label.types"
				path="type" choices="${types}" />
			<acme:input-select code="student.activity.list.label.enrolments"
				path="enrolment" choices="${enrolments}" />

			<acme:input-moment code="student.activity.list.label.periodStart"
				path="periodStart" />
			<acme:input-moment code="student.activity.list.label.periodEnd"
				path="periodEnd" />
			<acme:input-textbox code="student.activity.list.label.link"
				path="link" />

			<acme:submit code="student.activity.list.button.create"
				action="/student/activity/create" />
		</jstl:when>


	</jstl:choose>


</acme:form>
