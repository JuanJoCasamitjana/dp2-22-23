<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="company.practicum.list.label.code" path="code" width="10%"/>
	<acme:list-column code="company.practicum.list.label.title" path="title" width="20%"/>
	<acme:list-column code="company.practicum.list.label.abstractMessage" path="abstractMessage" width="40%"/>
	<acme:list-column code="company.practicum.list.label.estimatedTotalTime" path="estimatedTotalTime" width="10%"/>
	<acme:list-column code="company.practicum.list.label.goals" path="goals" width="10%"/>
	<acme:list-column code="company.practicum.list.label.published" path="published" width="10%"/>
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="company.practicum.list.button.create" action="/company/practicum/create"/>
</jstl:if>