<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="lecturer.aggregation.list.label.course.title" path="course.title" width="50%"/>
	<acme:list-column code="lecturer.aggregation.list.label.lecture.title" path="lecture.title" width="50%"/>
</acme:list>
