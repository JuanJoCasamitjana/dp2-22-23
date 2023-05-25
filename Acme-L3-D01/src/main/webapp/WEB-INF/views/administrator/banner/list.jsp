<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan"/>
	<acme:list-column code="administrator.banner.list.label.instantiation" path="instantiation"/>
	<acme:list-column code="administrator.banner.list.label.periodStart" path="periodStart"/>
	<acme:list-column code="administrator.banner.list.label.periodEnd" path="periodEnd"/>				
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="administrator.banner.list.button.create" action="/administrator/banner/create"/>
</jstl:if>