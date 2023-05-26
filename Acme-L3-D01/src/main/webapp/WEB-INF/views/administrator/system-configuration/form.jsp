<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="administrator.system-configuration.form.label.defaultSystemCurrency" path="defaultSystemCurrency"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>			
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update')}">
			<acme:submit code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration/update"/>
		</jstl:when>
	</jstl:choose>

</acme:form>