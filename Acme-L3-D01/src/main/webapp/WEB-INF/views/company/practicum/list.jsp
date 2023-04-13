<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="company.practicum.list.label.code" path="code"/>
	<acme:list-column code="company.practicum.list.label.title" path="title"/>
	<acme:list-column code="company.practicum.list.label.abstractMessage" path="abstractMessage"/>
	<acme:list-column code="company.practicum.list.label.estimatedTotalTime" path="estimatedTotalTime"/>
	<acme:list-column code="company.practicum.list.label.goals" path="goals"/>
	<acme:list-column code="company.practicum.list.label.published" path="published"/>
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="company.practicum.list.button.create" action="/company/practicum/create"/>
</jstl:if>