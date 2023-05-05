<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.note.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.note.form.label.message" path="message"/>
	<acme:input-textbox code="authenticated.note.form.label.author" path="author" readonly="true"/>
	<acme:input-email code="authenticated.note.form.label.mail" path="mail"/>
	<acme:input-url code="authenticated.note.form.label.link" path="link"/>
	<acme:input-moment code="authenticated.note.form.label.instantiation" path="instantiation" readonly="true"/>
	<jstl:if test="${_command == 'create'}">
		<acme:input-checkbox code="authenticated.note.form.label.confirm" path="confirm"/>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="authenticated.note.form.button.create" action="/authenticated/note/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>