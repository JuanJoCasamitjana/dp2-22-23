<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>

	<jstl:choose>
		<jstl:when
			test="${_command == 'show' || _command == 'update' || _command == 'finalise'}">
			<acme:input-textbox code="student.enrolment.form.label.code"
				path="code" />
			<acme:input-textbox code="student.enrolment.list.label.motivation"
				path="motivation" />
			<acme:input-textbox code="student.enrolment.list.label.goals"
				path="goals" />
			<acme:input-select code="student.enrolment.list.label.course"
				path="course" choices="${courses}" />
			<acme:input-textbox readonly="TRUE"
				code="student.enrolment.form.label.workTime" path="workTime" />

			<acme:input-textbox readonly="true"
				code="student.enrolment.list.label.lowerNibble" path="lowerNibble" />
			<acme:input-textbox readonly="false"
				code="student.enrolment.list.label.holderName" path="holderName" />
			<acme:input-checkbox readonly="true"
				code="student.enrolment.list.label.draft" path="draft" />

			<jstl:if test="${draft == true}">
				<acme:input-textbox code="student.enrolment.list.label.creditCard"
					path="creditCard" />
				<acme:input-textbox code="student.enrolment.list.label.expiryDate"
					path="expiryDate" placeholder="mm/yy"/>
				<acme:input-textbox code="student.enrolment.list.label.cvc"
					path="cvc" />
				<acme:submit code="student.enrolment.form.button.finalise"
					action="/student/enrolment/finalise" />
				<acme:submit code="student.enrolment.form.button.update"
					action="/student/enrolment/update" />
				<acme:submit code="student.enrolment.form.button.delete"
					action="/student/enrolment/delete" />
			</jstl:if>



		</jstl:when>
		<jstl:when test="${_command == 'create' }">
			<acme:input-textbox code="student.enrolment.form.label.code"
				path="code" />
			<acme:input-textbox code="student.enrolment.list.label.motivation"
				path="motivation" />
			<acme:input-textbox code="student.enrolment.list.label.goals"
				path="goals" />
			<acme:input-select code="student.enrolment.list.label.course"
				path="course" choices="${courses}" />
			<acme:submit code="student.enrolment.form.button.create"
				action="/student/enrolment/create" />
		</jstl:when>


	</jstl:choose>


	<%-- 	<acme:input-textbox code="student.enrolment.form.label.code"
		path="code" />
	<acme:input-textbox code="student.enrolment.list.label.motivation"
		path="motivation" />
	<acme:input-textbox code="student.enrolment.list.label.goals"
		path="goals" />
	<acme:input-select code="student.enrolment.list.label.course"
		path="course" choices="${courses}" />

	<acme:input-textbox code="student.enrolment.list.label.lowerNibble"
		path="lowerNibble" />
	<acme:input-textbox code="student.enrolment.list.label.holderName"
		path="holderName" />
	<acme:input-checkbox code="student.enrolment.list.label.draft"
		path="draft" />



	<jstl:choose>
		<jstl:when
			test="${draft == true}">
			<acme:input-textbox code="student.enrolment.list.label.creditCard"
				path="creditCard" />
			<acme:input-textbox code="student.enrolment.list.label.expiryDate"
				path="expiryDate" />
			<acme:input-textbox code="student.enrolment.list.label.cvc"
				path="cvc" />
		</jstl:when>
	</jstl:choose>

	<jstl:choose>
		<jstl:when
			test="${(_command == 'show' || _command == 'update'|| _command == 'delete' || _command == 'finalise') && draft == true}">
			<acme:submit code="student.enrolment.form.button.update"
				action="/student/enrolment/update" />
			<acme:submit code="student.enrolment.form.button.delete"
				action="/student/enrolment/delete" />
			<acme:submit code="student.enrolment.form.button.finalise"
				action="/student/enrolment/finalise" />
		</jstl:when>
		
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="student.enrolment.form.button.create"
				action="/student/enrolment/create" />
		</jstl:when>
	</jstl:choose> --%>


</acme:form>