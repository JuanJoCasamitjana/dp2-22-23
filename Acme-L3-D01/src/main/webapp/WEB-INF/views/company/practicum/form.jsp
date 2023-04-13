<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="company.practicum.form.label.code" path="code"/>
	<acme:input-textbox code="company.practicum.form.label.title" path="title"/>
	<acme:input-textarea code="company.practicum.form.label.abstractMessage" path="abstractMessage"/>
	<acme:input-double code="company.practicum.form.label.estimatedTotalTime" path="estimatedTotalTime"/>
	<acme:input-textarea code="company.practicum.form.label.goals" path="goals"/>
	<acme:input-textbox code="company.practicum.form.label.published" path="published" readonly="true"/>
	<jstl:if test="${published == true}">
	<acme:input-textbox code="company.practicum.form.label.course" path="course"/>
	</jstl:if>
	<jstl:if test="${published == false}">
		<acme:input-select code="company.practicum.form.label.course" path="course" choices="${courses}"/>
	</jstl:if>

	<acme:submit test="${acme:anyOf(_command, 'show|publish|update|delete') && published == false}" code="company.practicum.form.button.update" action="/company/practicum/update"/>
	<acme:submit test="${acme:anyOf(_command, 'show|publish|update|delete') && published == false}" code="company.practicum.form.button.delete" action="/company/practicum/delete"/>
	<acme:submit test="${acme:anyOf(_command, 'show|publish|update|delete') && published == false}" code="company.practicum.form.button.publish" action="/company/practicum/publish"/>

	<acme:submit test="${_command == 'show' && published == true}" code="company.practicum.form.button.practicumSession.list" action="/company/practicumSession/list"/>

	<acme:submit test="${_command == 'create'}" code="company.practicum.form.button.create" action="/company/practicum/create"/>
</acme:form>