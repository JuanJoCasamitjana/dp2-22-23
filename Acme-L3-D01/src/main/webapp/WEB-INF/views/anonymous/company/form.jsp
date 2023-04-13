<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="anonymous.company.form.label.name"
		path="name" />
	<acme:input-integer code="anonymous.company.form.label.vatNumber"
		path="vatNumber" />
	<acme:input-textbox code="anonymous.company.form.label.summary"
		path="summary" />
	<acme:input-url code="anonymous.company.form.label.optionalLink"
		path="optionalLink" />
</acme:form>
