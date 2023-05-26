<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.bulletin.list.label.title"
		path="title" />
</acme:list>

<acme:button code="authenticated.bulletin.list.button.create"
	action="/authenticated/bulletin/create" />
