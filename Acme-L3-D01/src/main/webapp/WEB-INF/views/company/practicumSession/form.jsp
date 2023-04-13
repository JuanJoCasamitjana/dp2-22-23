<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.practica.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.practica.form.label.abstractMessage" path="abstractMessage"/>
	<acme:input-moment code="authenticated.practica.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="authenticated.practica.form.label.periodEnd" path="periodEnd"/>
	<acme:input-url code="authenticated.practica.form.label.optionalLink" path="optionalLink"/>
</acme:form>