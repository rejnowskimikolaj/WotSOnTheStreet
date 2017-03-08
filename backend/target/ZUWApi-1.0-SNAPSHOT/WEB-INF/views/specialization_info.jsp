<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="header.jsp" %>
<body>
<%@ include file="drawer.jsp" %>
<%@ include file="navbar.jsp" %>


<div class="row margin">
  <div class="col s12 m12">
      <div class="container main">
<!-- start content-->

<div class="section">
	<div class="row">
		<div class="col s12">
			<h5>${spec.specializationName} </h5>  <br />
			<p>ID w bazie: ${spec.specializationID}</p>
		<div class="row">
            <div class="col s6">
                <table>
                <thead>
                    <tr><th>Sędziowie powiązani ze specializacją </th></tr></thead>
                    <tbody>
                    <c:forEach items="${spec.judges}" varStatus="i" >
                        <tr><td><a href="<spring:url value="/judge_info?id=${spec.judges.get(i.index).judgeID}" />">${spec.judges.get(i.index).name} ${spec.judges.get(i.index).lastname}</a></td></tr>
                    </c:forEach>
                    </tbody>
                    </table>
                    </div>
                    </div>
		</div>
	</div>
</div>

<!-- end content -->
    </div>
  </div>



</div>
<%@ include file="scripts.jsp" %>
<%@ include file="footer.jsp" %>
