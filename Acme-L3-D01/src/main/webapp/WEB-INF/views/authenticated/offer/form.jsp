<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.offer.form.label.heading" path="heading"/>
	<acme:input-textarea code="authenticated.offer.form.label.summary" path="summary"/>
	<acme:input-money code="authenticated.offer.form.label.price" path="price"/>
	<acme:input-moment code="authenticated.offer.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="authenticated.offer.form.label.periodEnd" path="periodEnd"/>
	<acme:input-moment code="authenticated.offer.form.label.instantiationMomment" path="instantiationMomment"/>
</acme:form>