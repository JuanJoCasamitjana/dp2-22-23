<%--
- menu.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:menu-bar code="master.menu.home">

	<acme:menu-left>
	
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/" />
			<acme:menu-suboption code="master.menu.juacasben.favourite-link" action="https://play.pokemonshowdown.com/" />
			<acme:menu-suboption code="master.menu.davgavser.favourite-link" action="https://primevideo.com/" />
			<acme:menu-suboption code="master.menu.aleortpag.favourite-link" action="https://www.naeu.playblackdesert.com/es-ES/News/Notice/" />
			<acme:menu-suboption code="master.menu.marbarmar16.favourite-link" action="https://www.hbomax.com/" />
			<acme:menu-suboption code="master.menu.davgonher1.favourite-link" action="https://mangaplus.shueisha.co.jp/updates/" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.Any" >
			<acme:menu-suboption code="master.menu.list.courses" action="/any/course/list"/>
			<acme:menu-suboption code="master.menu.list.peeps" action="/any/peep/list"/>
			<acme:menu-suboption code="master.menu.create.peep" action="/any/peep/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.practicum.list" action="/authenticated/practicum/list"/>
			<acme:menu-suboption code="master.menu.authenticated.offer.list" action="/authenticated/offer/list"/>
			<acme:menu-suboption code="master.menu.authenticated.note.list" action="/authenticated/note/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.banner.list" action="/administrator/banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.offer.list" action="/administrator/offer/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.company" access="hasRole('Company')">
			<acme:menu-suboption code="master.menu.company.practicum.list" action="/company/practicum/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.company.companyDashboard" action="/company/company-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.student" access="hasRole('Student')">
			<acme:menu-suboption code="master.menu.student.list.enrolments" action="/student/enrolment/list" />
			<acme:menu-suboption code="master.menu.student.list.courses" action="/student/course/list" />
			<acme:menu-suboption code="master.menu.student.list.activities" action="/student/activity/list" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.audit" action="/auditor/audit/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.Lecturer" access="hasRole('Lecturer')">
			<acme:menu-suboption code="master.menu.list.courses.list" action="/lecturer/course/list"/>
			<acme:menu-suboption code="master.menu.list.courses.create" action="/lecturer/course/create"/>
			<acme:menu-suboption code="master.menu.list.lectures.list" action="/lecturer/lecture/list"/>
			<acme:menu-suboption code="master.menu.list.lectures.create" action="/lecturer/lecture/create"/>
			<acme:menu-suboption code="master.menu.list.aggregate.list" action="/lecturer/lecture-course-aggregation/list"/>
			<acme:menu-suboption code="master.menu.list.dashboard" action="/lecturer/lecturer-dashboard/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.assistant" access="hasRole('Assistant')">
			<acme:menu-suboption code="master.menu.assistant.tutorial" action="/assistant/tutorial/list"/>
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.company.create" action="/authenticated/company/create" access="!hasRole('Company')"/>
			<acme:menu-suboption code="master.menu.user-account.company.update" action="/authenticated/company/update" access="hasRole('Company')"/>
			<acme:menu-suboption code="master.menu.user-account.lecturer.create" action="/authenticated/lecturer/create" access="!hasRole('Lecturer')"/>
			<acme:menu-suboption code="master.menu.user-account.lecturer.update" action="/authenticated/lecturer/update" access="hasRole('Lecturer')"/>
			<acme:menu-suboption code="master.menu.authenticated.assistant.create" action="/authenticated/assistant/create" access="!hasRole('Assistant')"/>
			<acme:menu-suboption code="master.menu.authenticated.assistant.update" action="/authenticated/assistant/update" access="hasRole('Assistant')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor.create" action="/authenticated/auditor/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor.update" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.student.create" action="/authenticated/student/create" access="!hasRole('Student')" />
			<acme:menu-suboption code="master.menu.user-account.student.update" action="/authenticated/student/update" access="hasRole('Student')" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	
	</acme:menu-right>
	
</acme:menu-bar>

