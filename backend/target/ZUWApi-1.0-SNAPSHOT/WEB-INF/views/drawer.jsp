<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="drawer">
	<ul class="drawer_menu">
 		<li> <a href="<spring:url value="/dashboard" />"> <i class="small material-icons">dashboard</i> Strona główna </a></li>
 		<li> <a href="<spring:url value="/cases" />"> <i class="small material-icons">assignment</i> Orzeczenia </a></li>
 		<li> <a href="<spring:url value="/judges" />"> <i class="small material-icons">assignment_ind</i> Sędziowie </a></li>
 		<li> <a href="<spring:url value="/nominations" />"> <i class="small material-icons">library_add</i> Nominacje </a></li>
 		<li> <a href="<spring:url value="/specializations" />"> <i class="small material-icons">assignment_late</i> Specjalizacje </a></li>
 		<li> <a href="<spring:url value="/logout" />"> <i class="small material-icons">power_settings_new</i> wyloguj</a></li>
 	</ul>
</div>