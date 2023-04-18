<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="company.practicum.form.label.code" path="code"/>
	<acme:input-textbox code="company.practicum.form.label.title" path="title"/>
	<acme:input-textarea code="company.practicum.form.label.abstractMessage" path="abstractMessage"/>
	<acme:input-double code="company.practicum.form.label.estimatedTotalTime" path="estimatedTotalTime"/>
	<acme:input-textarea code="company.practicum.form.label.goals" path="goals"/>
	<acme:input-textbox code="company.practicum.form.label.published" path="published" readonly="true"/>
	<acme:input-select code="company.practicum.form.label.course" path="course" choices="${courses}"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|publish|update|delete') && published == false}">
			<acme:submit code="company.practicum.form.button.update" action="/company/practicum/update"/>
			<acme:submit code="company.practicum.form.button.delete" action="/company/practicum/delete"/>
			<acme:submit code="company.practicum.form.button.publish" action="/company/practicum/publish"/>
			<acme:button code="company.practicum.form.button.practicumSession.list" action="/company/practicum-session/list?id=${id}"/>
		</jstl:when>
		<jstl:when test="${_command == 'show' && published == true}">
			<acme:button code="company.practicum.form.button.practicumSession.list" action="/company/practicum-session/list?id=${id}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="company.practicum.form.button.create" action="/company/practicum/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>