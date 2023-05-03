<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="company.practicum-session.form.label.title" path="title"/>
	<acme:input-textbox code="company.practicum-session.form.label.abstractMessage" path="abstractMessage"/>
	<acme:input-moment code="company.practicum-session.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="company.practicum-session.form.label.periodEnd" path="periodEnd"/>
	<acme:input-double code="company.practicum-session.form.label.totalTime" path="totalTime" readonly="true"/>
	<acme:input-url code="company.practicum-session.form.label.optionalLink" path="optionalLink"/>
	<acme:input-checkbox code="company.practicum-session.form.label.addendum" path="addendum" readonly="true"/>
	<jstl:if test="${isAddendum == false}">
		<acme:input-checkbox code="company.practicum-session.form.label.confirmed" path="confirmed" readonly="true"/>
	</jstl:if> 
	<jstl:if test="${isAddendum == true}">
		<acme:input-checkbox code="company.practicum-session.form.label.confirmed" path="confirmed"/>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && isPublished == false}">
			<acme:submit code="company.practicum-session.form.button.update" action="/company/practicum-session/update"/>
			<acme:submit code="company.practicum-session.form.button.delete" action="/company/practicum-session/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="company.practicum-session.form.button.create" action="/company/practicum-session/create?practicumId=${practicumId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>