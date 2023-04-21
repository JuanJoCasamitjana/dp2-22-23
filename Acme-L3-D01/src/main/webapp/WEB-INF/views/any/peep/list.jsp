<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.course.list.label.nick" path="nick" width="20%"/>
	<acme:list-column code="any.course.list.label.title" path="title" width="60%"/>	
	<acme:list-column code="any.course.list.label.mail" path="mail" width="20%"/>
</acme:list>
