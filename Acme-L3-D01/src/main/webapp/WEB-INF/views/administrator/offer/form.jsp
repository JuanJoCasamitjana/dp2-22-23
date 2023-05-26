<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="administrator.offer.form.label.heading" path="heading"/>
	<acme:input-textarea code="administrator.offer.form.label.summary" path="summary"/>
	<acme:input-money code="administrator.offer.form.label.price" path="price"/>
	<acme:input-moment code="administrator.offer.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="administrator.offer.form.label.periodEnd" path="periodEnd"/>
	<acme:input-moment code="administrator.offer.form.label.instantiationMomment" path="instantiationMomment" readonly="true"/>

<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="administrator.offer.form.button.update" action="/administrator/offer/update"/>
			<acme:submit code="administrator.offer.form.button.delete" action="/administrator/offer/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrator.offer.form.button.create" action="/administrator/offer/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>