<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="company.practicumSession.form.label.title" path="title"/>
	<acme:input-textbox code="company.practicumSession.form.label.abstractMessage" path="abstractMessage"/>
	<acme:input-moment code="company.practicumSession.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="company.practicumSession.form.label.periodEnd" path="periodEnd"/>
	<acme:input-url code="company.practicumSession.form.label.optionalLink" path="optionalLink"/>
	<acme:input-checkbox code="company.practicumSession.form.label.addendum" path="addendum" readonly="true"/>
	<acme:input-checkbox code="company.practicumSession.form.label.confirmed" path="confirmed"/>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && published == false}">
			<acme:submit code="company.practicum-session.form.button.update" action="/company/practicum-session/update"/>
			<acme:submit code="company.practicum-session.form.button.delete" action="/company/practicum-session/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="company.practicum-session.form.button.create" action="/company/practicum-session/create?practicumId=${practicumId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>