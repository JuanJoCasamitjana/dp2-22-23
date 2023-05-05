<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.offer.list.label.heading" path="heading"/>
	<acme:list-column code="authenticated.offer.list.label.periodStart" path="periodStart"/>
	<acme:list-column code="authenticated.offer.list.label.periodEnd" path="periodEnd"/>
	<acme:list-column code="authenticated.offer.list.label.price" path="price"/>
</acme:list>