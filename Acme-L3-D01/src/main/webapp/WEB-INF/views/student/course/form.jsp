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
	<acme:input-textbox code="student.course.form.label.code" path="code" />
	<acme:input-textbox code="student.course.form.label.title" path="title" />
	<acme:input-textarea code="student.course.form.label.abstractMessage"
		path="abstractMessage" />
	<acme:input-double code="student.course.form.label.retailPrice"
		path="retailPrice" />
	<acme:input-select code="student.course.form.label.typeOfCourse"
		path="typeOfCourse" choices="${types}" />
	<acme:input-url code="student.course.form.label.optionalUrl"
		path="optionalUrl" />
	<acme:input-checkbox code="student.course.form.label.inDraft"
		path="inDraft" />
	<acme:input-textbox code="student.course.form.label.lectures"
		path="lectures" />
	<acme:input-textbox code="student.course.form.label.lecturer"
		path="lecturer" />
</acme:form>
