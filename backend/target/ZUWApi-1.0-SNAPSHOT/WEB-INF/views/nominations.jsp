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
<h5 class="center">Wykaz nominacji</h5>

	<div class="row">
		<div class="col s12">
			<ul class="collection">
			<c:forEach items="${nominations}" varStatus="i" >
			    <li class="collection-item"><a href="<spring:url value="/nomination_info?id=${nominations.get(i.index).nominationID}" />">${nominations.get(i.index).partyName} </a></li>
            </c:forEach>
			</ul>
		</div>
	</div>
</div>
    <a class="btn-floating btn-large waves-effect waves-light red fab_fix" href="<spring:url value="/add_nomination" />"><i class="material-icons">add</i></a>

<div id="modal1" class="modal">
    <div class="modal-content">
      <h4>Tryb debugowania jest włączony!</h4>
      <p>Brak możliwości zapisu do bazy, automatycznego uzupełniania i walidacji danych. Mogą nie działać różne rzeczy, w zależności od zakresu ostatnio prowadzonych prac. Nie stosować do użytku produkcyjnego!</p>
    </div>
    <div class="modal-footer">
      <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">OK</a>
    </div>
  </div>


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
