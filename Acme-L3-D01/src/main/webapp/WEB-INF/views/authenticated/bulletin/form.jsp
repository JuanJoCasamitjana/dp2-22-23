<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for student particular
- purposes.  The copyright owner does not offer student warranties or representations, nor do
- they accept student liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.bulletin.form.label.title"
		path="title" />
	<acme:input-textbox code="authenticated.bulletin.form.label.message"
		path="message" />
	<acme:input-textbox
		code="authenticated.bulletin.form.label.optionalLink"
		path="optionalLink" />
	<acme:input-moment
		code="authenticated.bulletin.form.label.instantiationMomment"
		path="instantiationMomment" />
	<acme:input-checkbox
		code="authenticated.bulletin.form.label.isCritical" path="isCritical" />



	<jstl:if test="${_command == 'create'}">
		<acme:input-checkbox code="authenticated.bulletin.form.label.check"
			path="check" />
		<acme:submit code="authenticated.bulletin.list.button.create"
			action="/authenticated/bulletin/create" />

	</jstl:if>

</acme:form>
