<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-integer code="company.companyDashboard.form.label.totalNumberOfPracticums" path="totalNumberOfPracticums" readonly="true"/>
	<acme:input-double code="company.companyDashboard.form.label.averagePeriodLengthOfPracticums" path="averagePeriodLengthOfPracticums" readonly="true"/>	
	<acme:input-double code="company.companyDashboard.form.label.deviationPeriodLengthOfPracticums" path="deviationPeriodLengthOfPracticums" readonly="true"/>	
	<acme:input-double code="company.companyDashboard.form.label.minimumPeriodLengthOfPracticums" path="minimumPeriodLengthOfPracticums" readonly="true"/>	
	<acme:input-double code="company.companyDashboard.form.label.maximumPeriodLengthOfPracticums" path="maximumPeriodLengthOfPracticums" readonly="true"/>	
	<acme:input-double code="company.companyDashboard.form.label.totalNumberOfSessions" path="totalNumberOfSessions" readonly="true"/>
	<acme:input-double code="company.companyDashboard.form.label.averagePeriodLengthOfSessions" path="averagePeriodLengthOfSessions" readonly="true"/>
	<acme:input-double code="company.companyDashboard.form.label.deviationPeriodLengthOfSessions" path="deviationPeriodLengthOfSessions" readonly="true"/>
	<acme:input-double code="company.companyDashboard.form.label.minimumPeriodLengthOfSessions" path="minimumPeriodLengthOfSessions" readonly="true"/>
	<acme:input-double code="company.companyDashboard.form.label.maximumPeriodLengthOfSessions" path="maximumPeriodLengthOfSessions" readonly="true"/>
</acme:form>