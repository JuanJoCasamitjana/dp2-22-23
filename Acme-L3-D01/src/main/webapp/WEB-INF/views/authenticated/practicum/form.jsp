<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.practicum.form.label.code" path="code"/>
	<acme:input-textbox code="authenticated.practicum.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.practicum.form.label.abstractMessage" path="abstractMessage"/>
	<acme:input-double code="authenticated.practicum.form.label.estimatedTotalTime" path="estimatedTotalTime"/>
	<acme:input-textarea code="authenticated.practicum.form.label.goals" path="goals"/>
	<acme:input-textbox code="authenticated.practicum.form.label.published" path="published"/>
	<acme:input-textbox code="authenticated.practicum.form.label.company" path="company.name"/>
</acme:form>