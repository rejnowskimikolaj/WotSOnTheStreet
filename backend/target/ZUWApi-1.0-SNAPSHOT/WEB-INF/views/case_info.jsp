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
      <div class="container main z-depth-5">
<!-- start content-->

<div class="row">
    <div class="col s12">
      <ul class="tabs teal-text text-lighten-1">
        <li class="tab col s3"><a class="active" href="#basic">Dane podstawowe</a></li>
        <li class="tab col s3"><a href="#team">Skład</a></li>
        <li class="tab col s3"><a href="#points">Punkty sentencji</a></li>
        <li class="tab col s3"><a href="#separatum">Zdania odrębne</a></li>
      </ul>
    </div>
</div>

<div  id="basic" class="padding-class">
<div class="row">
    <div class="col s6">
        <table>
        <thead>
            <tr><th>ID w bazie </th></tr></thead>
        <tbody>
            <tr><td>${aCase.caseID}</td></tr>
        </tbody>
        </table>
    </div>
    <div class="col s6">
        <table>
        <thead>
            <tr><th>Adres orzeczenia </th></tr></thead>
        <tbody>
            <tr><td><a href="${aCase.sentenceURL}" target="_blank">${aCase.sentenceURL}</a></td></tr>
        </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col s6">
        <table>
        <thead>
            <tr><th>Data </th></tr></thead>
        <tbody>
            <tr><td>${aCase.getLocalDate()} </td></tr>
        </tbody>
        </table>
    </div>
    <div class="col s6">
        <table>
            <thead>
                <tr><th>Instancja </th></tr></thead>
            <tbody>
                <tr><td>${aCase.instance.name}</td></tr>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col s6">
        <table>
            <thead>
                <tr><th>Liczba sędziów </th></tr></thead>
            <tbody>
                <tr><td>${aCase.judgesNumber} </td></tr>
            </tbody>
        </table>
    </div>
    <div class="col s6">
        <table>
            <thead>
                <tr><th>Liczba zdań odrębnych</th></tr></thead>
            <tbody>
                <tr><td>${aCase.separatumNumber}</td></tr>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col s6">
        <table><thead><tr><th>Chroniona wartość konstytucyjna <a href="<spring:url value="/add_protected_value?id=${aCase.caseID}" />" class="right green-text"><i class="material-icons">add</i></a></th></tr></thead>
            <tbody>
                <c:forEach items="${aCase.protectedValues}" varStatus="i" >
                    <tr><td>${aCase.protectedValues.get(i.index).name}</td></tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s6">
        <table>
            <thead>
                <tr><th>Przedmiot kontroli </th></tr></thead>
            <tbody>
                <tr><td>${aCase.controlSubject.name}</td></tr>
            </tbody>
        </table>
    </div>
</div>


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
</div>
<div id="team" class="padding-class">
<div class="row">
    <h5 class="center-align">Skład orzekający</h5><br />
        <div class="row">
            <div class="col s6">
                <table><thead><tr><th>Przewodniczący</th></tr></thead>
                    <tbody>
                        <tr><td><a href="judge_info.php?id=${aCase.leader.judgeID}" >${aCase.leader.name} ${aCase.leader.lastname}</a>
                           (Staż: ${aCase.getCaseYear() - aCase.leader.beginYear}
                              <c:choose><c:when test="${(aCase.getCaseYear() - aCase.leader.beginYear) == 1}">rok</c:when>
                              <c:when test="${(aCase.getCaseYear() - aCase.leader.beginYear) > 1 && (aCase.getCaseYear() - aCase.leader.beginYear) < 5}">lata</c:when>
                              <c:otherwise>lat</c:otherwise></c:choose> w TK)
                           </td></tr>
                    </tbody>
                </table>

                <table><thead><tr><th>Sprawozdawcy</th></tr></thead>
                    <tbody>
                        <c:forEach items="${aCase.judgesReporter}" varStatus="i" >
                            <tr><td><a href="<spring:url value="/judge_info?id=${aCase.judgesReporter.get(i.index).judgeID}" />">${aCase.judgesReporter.get(i.index).name} ${aCase.judgesReporter.get(i.index).lastname}</a>
                                                    (Staż: ${aCase.getCaseYear() - aCase.judgesReporter.get(i.index).beginYear}
                                                    <c:choose><c:when test="${(aCase.getCaseYear() - aCase.judgesReporter.get(i.index).beginYear) == 1}">rok</c:when>
                                                   <c:when test="${(aCase.getCaseYear() - aCase.judgesReporter.get(i.index).beginYear) > 1 && (aCase.getCaseYear() - aCase.judgesReporter.get(i.index).beginYear) < 5}">lata</c:when>
                                                    <c:otherwise>lat</c:otherwise></c:choose> w TK)</td></tr>
                      </c:forEach>
                    </tbody>
                </table>

                <table><thead><tr><th>Sędziowie składający zdanie odrębne</th></tr></thead>
                    <tbody>
                        <c:forEach items="${aCase.separatums}" varStatus="i" >
                        <c:if test="${i.index == 0 || (i.index > 0 && aCase.separatums.get(i.index).judge.judgeID != aCase.separatums.get(i.index -1).judge.judgeID)}">
                            <tr><td><a href="<spring:url value="/judge_info?id=${aCase.separatums.get(i.index).judge.judgeID}" />">
                            ${aCase.separatums.get(i.index).judge.name} ${aCase.separatums.get(i.index).judge.lastname}</a> (Staż:  ${aCase.getCaseYear() - aCase.separatums.get(i.index).judge.beginYear}
                            <c:choose><c:when test="${(aCase.getCaseYear() - aCase.separatums.get(i.index).judge.beginYear) == 1}">rok</c:when>
                            <c:when test="${(aCase.getCaseYear() - aCase.separatums.get(i.index).judge.beginYear) > 1 && (aCase.getCaseYear() - aCase.separatums.get(i.index).judge.beginYear) < 5}">lata</c:when>
                            <c:otherwise>lat</c:otherwise></c:choose> w TK)</td></tr></c:if>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="col s6">
                <table><thead><tr><th>Skład</th></tr></thead>
                    <tbody>
                        <c:forEach items="${aCase.judgesTeam}" varStatus="i" >
                            <tr><td><a href="<spring:url value="/judge_info?id=${aCase.judgesTeam.get(i.index).judgeID}" />">${aCase.judgesTeam.get(i.index).name} ${aCase.judgesTeam.get(i.index).lastname}</a>
                                (Staż: ${aCase.getCaseYear() - aCase.judgesTeam.get(i.index).beginYear}
                                <c:choose><c:when test="${(aCase.getCaseYear() - aCase.judgesTeam.get(i.index).beginYear) == 1}">rok</c:when>
                                <c:when test="${(aCase.getCaseYear() - aCase.judgesTeam.get(i.index).beginYear) > 1 && (aCase.getCaseYear() - aCase.judgesTeam.get(i.index).beginYear) < 5}">lata</c:when>
                                <c:otherwise>lat</c:otherwise></c:choose> w TK)</td></tr>
                            </td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
</div>
</div>

<div id="points" class="padding-class">
<div class="row">
    <div class="col s6">
           <c:forEach items="${aCase.points}" varStatus="i" >
                <table><thead><tr><th>Punkt ${aCase.points.get(i.index).pointNumber}</th></tr></thead><tbody>
                    <c:forEach items="${aCase.points.get(i.index).pointDecisions}" varStatus="j" >
<tr><td>${aCase.points.get(i.index).pointDecisions.get(j.index).decision.name} -
${aCase.points.get(i.index).pointDecisions.get(j.index).pattern.name}</td></tr>

                    </c:forEach>

        </tbody>
    </table>
            </c:forEach>
            </div>
    <div class="col s6">

   <table><thead><tr><th>Ponadto postanawia</th></tr></thead><tbody>
              <c:forEach items="${aCase.besides}" varStatus="i" >
        <tr><td>${aCase.besides.get(i.index).name}</td></tr>
                            </c:forEach>

   </tbody></table>
    </div>
</div>
</div>

<div id="separatum" class="padding-class">
                <table><thead><tr><th>Zdania odrębne</th></tr></thead>
                    <tbody>
                        <c:forEach items="${votums}" varStatus="i" >
                            <tr><td>${votums.get(i.index)}</td></tr>
                        </c:forEach>
                    </tbody>
                </table>
</div>

<!-- end content -->
    </div>
  </div>



</div>
<script>

  $(document).ready(function(){
    $('ul.tabs').tabs();
  });

</script>

<%@ include file="scripts.jsp" %>
<%@ include file="footer.jsp" %>
