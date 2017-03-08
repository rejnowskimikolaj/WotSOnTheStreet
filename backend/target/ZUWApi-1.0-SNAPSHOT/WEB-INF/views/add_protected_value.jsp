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
<h5 class="center">Chroniona wartość dla orzeczenia ${aCase.signature}</h5>
<br />
<div class="row">
                        <div class="col s6">
                        <table><thead><tr><th>Wzorce kontroli</th></tr></thead>
                        <tbody>
                        <c:forEach items="${aCase.controlPatterns}" varStatus="i" >
                             <tr><td>${aCase.controlPatterns.get(i.index).name}</td></tr>
                        </c:forEach>
                        </tbody>
                        </table>
                        </div>
                        <div class="col s6">
                                    <table><thead><tr><th>Obiekty kontroli</th></tr></thead>
                                    <tbody>
                                    <c:forEach items="${aCase.controlObjects}" varStatus="i" >
                                        <tr><td>${aCase.controlObjects.get(i.index).name}</td></tr>
                                    </c:forEach>
                                    </tbody>
                                    </table>
                                    </div>
                        </div>
    <form:form method="POST" modelAttribute="protectedValue" action="add_protected_value" >
	<div class="row">

			<form:input type="text" id="value" name="value" class="validate" path="caseID" value="${aCase.caseID}"  readonly="true" hidden="true"/>
            		<div class="input-field col s8">

			<form:select multiple="true" path="valueID">
			     <c:forEach items="${values}" varStatus="i" >
                    <form:option value="${values.get(i.index).valueID}">${values.get(i.index).name}</form:option>
                 </c:forEach>
            </form:select>
            </div>
            <br />
            		<div class="input-field col s8">

	        <form:input type="text" id="newValues" name="name" class="validate" path="name" />
			<c:if test="${pageContext.request.method=='POST'}"><form:errors type="text" name="name" class="validate" path="name" /></c:if>
			<label for="newValues">Nowe wartości (aby dodać kilka wartości naraz, należy oddzielić je średnikiem)</label>
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