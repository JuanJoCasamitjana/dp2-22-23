<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.company.form.label.name" path="name"/>
	<acme:input-textbox code="authenticated.company.form.label.vatNumber" path="vatNumber"/>
	<acme:input-textbox code="authenticated.company.form.label.summary" path="summary"/>
	<acme:input-textbox code="authenticated.company.form.label.optionalLink" path="optionalLink"/>
	
	<jstl:choose>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="authenticated.company.form.button.create" action="/authenticated/company/create"/>
		</jstl:when>
		<jstl:when test="${_command == 'update'}">
			<acme:submit code="authenticated.company.form.button.update" action="/authenticated/company/update"/>
		</jstl:when>
	</jstl:choose>
</acme:form>