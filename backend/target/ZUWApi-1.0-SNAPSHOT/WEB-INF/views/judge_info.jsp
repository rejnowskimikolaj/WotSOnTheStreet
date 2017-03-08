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
	<h5 class="center">Dane podstawowe</h5>
	<div class="row">
		<div class="col s12">
			<h5>${judge.name} ${judge.lastname}  </h5>  <br />
			<p>ID w bazie: ${judge.judgeID}</p>
			<c:if test="${judge.birthYear != 0}"><p>Rok urodzenia: ${judge.birthYear} </p></c:if>
			<c:if test="${judge.deathYear != 0}"><p>Rok śmierci: ${judge.deathYear} </p></c:if>
			<c:if test="${judge.beginYear != 0}"><p>Początek kadencji: ${judge.beginYear}</p></c:if>
			<c:if test="${judge.endYear != 0}"><p>Koniec kadencji: ${judge.endYear}</p></c:if>
			<p>Liczba złożonych zdań odrębnych: ${separatum.size()}</p>
			<p>Liczba orzeczeń ogółem: ${judge.allCasesNum}</p>
			<p>Procent zdań odrębnych w orzeczeniach: ${judge.votumPercent()}%</p>

            <p>Nominacja:
            <c:forEach items="${nomination}" varStatus="i" >
                    <a href="<spring:url value="/nomination_info?id=${nomination.get(i.index).nominationID}" />">${nomination.get(i.index).partyName}</a><c:if test="${i.count < nomination.size()}">, </c:if>
            </c:forEach></p>
            <p>Specializacja:
            <c:forEach items="${specialization}" varStatus="i" >
                <a href="<spring:url value="/specialization_info?id=${specialization.get(i.index).specializationID}" />">${specialization.get(i.index).specializationName}</a><c:if test="${i.count < specialization.size()}">, </c:if>
            </c:forEach>
            </p>
            <div class="row">
		    <div class="col s6">



		    <table class="striped centered" style="border: none">
		    <thead><tr><th>Przewodniczący w sprawach:</th></tr>
		    </thead>
		    <tbody>
		    <c:if test="${leader.size() == 0}"><tr><td>---</td></th></c:if>
			<c:forEach items="${leader}" varStatus="i" >
                <tr><td><a href="<spring:url value="/case_info?id=${leader.get(i.index).caseID}" />">${leader.get(i.index).signature}</a></td></tr>
            </c:forEach>
            </tbody>
            </table>

            <table class="striped centered">
            <thead><tr><th>Sprawozdawca w sprawach:</th></tr>
            </thead>
            <tbody>
            <c:if test="${reporter.size() == 0}"><tr><td>---</td></th></c:if>
            <c:forEach items="${reporter}" varStatus="i" >
                 <tr><td><a href="<spring:url value="/case_info?id=${reporter.get(i.index).caseID}" />">${reporter.get(i.index).signature}</a></td></tr>
            </c:forEach>
            </tbody>
            </table>

          <table class="striped centered">
            		    <thead><tr><th>Złożył zdanie odrębne w sprawach:</th></tr>
            		    </thead>
            		    <tbody>
           <c:if test="${separatum.size() == 0}"><tr><td>---</td></th></c:if>
          <c:forEach items="${separatum}" varStatus="i" >
                                      <tr><td><a href="<spring:url value="/case_info?id=${separatum.get(i.index).sCase.caseID}" />">${separatum.get(i.index).sCase.signature}</a></td></tr>
                                  </c:forEach>
            </tbody>
            </table>

            </div>
            <div class="col s6">
            <table class="striped centered">
            <thead><tr><th>Członek składu w sprawach:</th></tr>
            </thead>
            <tbody>
            <c:if test="${team.size() == 0}"><tr><td>---</td></th></c:if>
            <c:forEach items="${team}" varStatus="i" >
               <tr><td><a href="<spring:url value="/case_info?id=${team.get(i.index).caseID}" />">${team.get(i.index).signature}</a></td></tr>
            </c:forEach>
                            </tbody></table>
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
