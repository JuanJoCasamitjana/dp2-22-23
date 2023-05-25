<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="student.enrolment.list.label.code" path="code"/>
	<acme:list-column code="student.enrolment.list.label.course" path="course.title"/>
	<acme:list-column code="student.enrolment.list.label.draft" path="draft"/>
	
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="student.enrolment.list.button.create" action="/student/enrolment/create"/>
</jstl:if>