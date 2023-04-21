<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="assistant.tutorialSession.list.label.title" path="title"/>
	<acme:input-textarea code="assistant.tutorialSession.list.label.abstract$" path="abstract$"/>
	<acme:input-select code="assistant.tutorialSession.list.label.nature" path="nature" choices="${types}"/>
	<acme:input-moment code="assistant.tutorialSession.list.label.startPeriod" path="startPeriod"/>
	<acme:input-moment code="assistant.tutorialSession.list.label.endPeriod" path="endPeriod"/>	
	<acme:input-url code="assistant.tutorialSession.list.label.furtherInformationLink" path="furtherInformationLink"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="assistant.tutorialSession.button.update" action="/assistant/tutorial-session/update"/>
			<acme:submit code="assistant.tutorialSession.button.delete" action="/assistant/tutorial-session/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="assistant.tutorialSession.button.create2" action="/assistant/tutorial-session/create?tutorialId=${tutorialId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>