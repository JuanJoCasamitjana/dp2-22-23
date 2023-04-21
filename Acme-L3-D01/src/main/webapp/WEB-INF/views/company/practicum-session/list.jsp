<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="company.practicumSession.list.label.title" path="title"/>
	<acme:list-column code="company.practicumSession.list.label.periodStart" path="periodStart"/>
	<acme:list-column code="company.practicumSession.list.label.periodEnd" path="periodEnd"/>
	<acme:list-column code="company.practicumSession.list.label.addendum" path="addendum"/>
</acme:list>

<jstl:if test="${_command == 'list' && hasAddendum==false}">
	<acme:button code="company.practicum-session.list.button.create" action="/company/practicum-session/create?practicumId=${practicumId}"/>
</jstl:if>