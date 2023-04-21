<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="assistant.tutorialSession.list.label.title" path="title" width="10%"/>
	<acme:list-column code="assistant.tutorialSession.list.label.nature" path="nature" width="10%"/>
	<acme:list-column code="assistant.tutorialSession.list.label.startPeriod" path="startPeriod" width="10%"/>
	<acme:list-column code="assistant.tutorialSession.list.label.endPeriod" path="endPeriod" width="70%"/>	
</acme:list>


<acme:button test="${showCreate}" code="assistant.tutorialSession.button.create" action="/assistant/tutorial-session/create?tutorialId=${tutorialId}"/>
		
	
			
