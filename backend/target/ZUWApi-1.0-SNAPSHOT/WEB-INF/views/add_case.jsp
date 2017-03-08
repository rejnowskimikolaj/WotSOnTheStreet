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

<h5 class="center">Dane podstawowe</h5>

	<form:form class="col s12" method="POST" action="add_case" modelAttribute="caseCoreInfo">
	<div class="row">
		<div class="input-field col s4">
			<form:input type="text" id="date" name="date" class="validate" path="date" />
			<c:if test="${pageContext.request.method=='POST'}"><form:errors type="text" name="date" class="validate" path="date" /></c:if>
			<label for="date">Data</label>
        </div>

        <div class="input-field col s4">
            <form:input  id="signature" name="signature" type="text" class="validate" path="signature" />
            <c:if test="${pageContext.request.method=='POST'}"><form:errors type="text" name="signature" class="validate" path="signature" /></c:if>
            <label for="signature">Sygnatura</label>
        </div>

        <div class="input-field col s4">

			<label>Instancja</label>
		</div>
    </div>
<!-- </div> -->



<div class="section">
	<button class="waves-effect waves-light btn" type="submit">Dalej</button>
	</form>
</div>

<!-- end content -->
		</div>
	</div>
</div>

<%@ include file="scripts.jsp" %>
<%@ include file="footer.jsp" %>