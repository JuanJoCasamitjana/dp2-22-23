<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.company.form.label.name" path="name"/>
	<acme:input-textbox code="authenticated.company.form.label.vatNumber" path="vatNumber"/>
	<acme:input-textbox code="authenticated.company.form.label.summary" path="summary"/>
	<acme:input-textbox code="authenticated.company.form.label.optionalLink" path="optionalLink"/>
	
	<acme:submit test="${_command == 'create'}" code="authenticated.company.form.button.create" action="/authenticated/company/create"/>
	<acme:submit test="${_command == 'update'}" code="authenticated.company.form.button.update" action="/authenticated/company/update"/>
</acme:form>