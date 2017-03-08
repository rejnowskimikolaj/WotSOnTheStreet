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

    <form name="sort_form" method="GET" action="cases">
<button class="waves-effect waves-light btn right" id="sortBtn" type="submit">Sortuj</button>
		<div class="input-field col s4 inline right">
        		<select name="sort">
                      <option value="" disabled selected>Sortuj...</option>
                      <option value="1">wg daty rosnąco</option>
                      <option value="2">wg daty malejąco</option>
                      <option value="3">wg zdań odrębnych rosnąco</option>
                      <option value="4">wg zdań odrębnych malejąco</option>
                      <option value="5">wg punktów sentencji rosnąco</option>
                      <option value="6">wg punktów sentencji malejąco</option>
                    </select>
        		</div>
</form>
</div>

</div>
<div class="section">
<h5 class="center">Wykaz orzeczeń</h5>

	<div class="row">
		<div class="col s12">
			<table class="striped centered">
			<thead>
			    <tr><th> </th><th>Sygnatura</th><th>Data</th><th>Liczba zdań odrębnych</th><th>Ilość punktów sentencji</th></tr>
			</thead>
			<tbody>
			<c:forEach items="${cases}" varStatus="i" >
			    <tr>
			    <td>
			    <c:if test="${cases.get(i.index).protectedValues.size() == 0}">
                                    <a class="tooltipped" data-position="bottom" data-delay="50" data-tooltip="Brak chronionej wartości">
                                    <i class="material-icons">error_outline</i></a></c:if>
			    </td>

                   <td>
                   <a href="<spring:url value="/case_info?id=${cases.get(i.index).caseID}" />"> ${cases.get(i.index).signature}</a></td>
                    <td><a href="<spring:url value="/case_info?id=${cases.get(i.index).caseID}" />"> ${cases.get(i.index).getLocalDate()} </a></td>
                    <td> ${cases.get(i.index).separatumNumber}</td>
                    <td> ${cases.get(i.index).getPointsNumber()}</td>
			    </tr>
            </c:forEach>
			</tbody>
			</table>
		</div>
	</div>
</div>
    <a class="btn-floating btn-large waves-effect waves-light red fab_fix" href="http://dweb.pl/votum_separatum/add_case.php"><i class="material-icons">add</i></a>

<div id="modal1" class="modal">
    <div class="modal-content">
      <h4>Tryb debugowania jest włączony!</h4>
      <p>Brak możliwości zapisu do bazy, automatycznego uzupełniania i walidacji danych. Mogą nie działać różne rzeczy, w zależności od zakresu ostatnio prowadzonych prac. Nie stosować do użytku produkcyjnego!</p>
    </div>
    <div class="modal-footer">
      <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">OK</a>
    </div>
  </div>
 <ul class="pagination center">
    <li <c:choose>
           <c:when test="${page < 2}" >
                class="disabled" >
           </c:when>
           <c:otherwise>
                class="waves-effect" >
           </c:otherwise>
        </c:choose>
        <a href='cases?page=${page-1}<c:if test="${sort != 0}">&sort=${sort}</c:if>'><i class="material-icons ">chevron_left</i></a>
    </li>
    <c:forEach var="i" begin="1" end="${pages}">
    <li <c:choose>
            <c:when test="${i == page}">
                class="active teal lighten-1" >
            </c:when>
            <c:otherwise>
                class="waves-effect" >
            </c:otherwise>
        </c:choose>
        <a href='cases?page=${i}<c:if test="${sort != 0}">&sort=${sort}</c:if> '>${i}</a></li>
    </c:forEach>
    <li <c:choose>
               <c:when test="${page == pages}" >
                    class="disabled" >
               </c:when>
               <c:otherwise>
                    class="waves-effect" >
               </c:otherwise>
            </c:choose>
            <a href='cases?page=${page+1}<c:if test="${sort != 0}">&sort=${sort}</c:if>'><i class="material-icons">chevron_right</i></a></li>
  </ul>
<!-- end content -->
    </div>
  </div>



</div>
<%@ include file="scripts.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();

  });


</script>
<%@ include file="footer.jsp" %>
