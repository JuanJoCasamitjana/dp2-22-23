<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.instantiationOrUpdate" path="instantiationOrUpdate"/>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan"/>
	<acme:list-column code="administrator.banner.list.label.pictureLink" path="pictureLink"/>
	<acme:list-column code="administrator.banner.list.label.webDocLink" path="webDocLink"/>				
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="administrator.banner.list.button.create" action="/administrator/banner/create"/>
</jstl:if>