<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp" %>
<body>
<%@ include file="drawer.jsp" %>
<%@ include file="navbar.jsp" %>

<div class="row margin">
	<div class="col s12 m12">
  		<div class="container main">
<!-- start content-->

<div class="section">
<h5 class="center">Dodaj specializację</h5>
    <form:form method="POST" action="add_specialization" modelAttribute="specialization">
	<div class="row">
		<div class="input-field col s6">
			<form:input type="text" name="specialization" class="validate" path="name"/>
            <c:if test="${pageContext.request.method=='POST'}"><form:errors type="text" name="specialization" class="validate" path="name" /></c:if>
			<label>Specializacja</label>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<button class="waves-effect waves-light btn" type="submit">Dodaj</button>
		</div>
	</div>
	</form:form>
</div>

<!-- end content -->
		</div>
	</div>
</div>

<%@ include file="scripts.jsp" %>
<%@ include file="footer.jsp" %>