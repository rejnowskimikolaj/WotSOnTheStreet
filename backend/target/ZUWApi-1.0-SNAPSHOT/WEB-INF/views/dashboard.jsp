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
<div class="row">
  <a href="<spring:url value="/cases" />"><div class="col s5 m5 mainbox z-depth-3"><div class="green darken-1 center"><h1 class="mainbox-header">${cases}</h1></div><p> Orzeczenia</p></div></a>
  <a href="<spring:url value="/judges" />"><div class="col s5 m5 mainbox z-depth-3"><div class="green center"><h1 class="mainbox-header">${judges}</h1></div><p> Sędziowie</p></div></a>

  <a href="<spring:url value="/nominations" />"><div class="col s5 m5 mainbox z-depth-3"><div class="green lighten-1 center"><h1 class="mainbox-header">${nominations}</h1></div><p> Nominacje</p></div></a>
  <a href="<spring:url value="/specializations" />"><div class="col s5 m5 mainbox z-depth-3"><div class="green lighten-2 center"><h1 class="mainbox-header">${specializations}</h1></div><p> Specjalizacje</p></div></a>
</div>


<!-- end content -->
    </div>
  </div>

<!--   <div class="fixed-action-btn horizontal">
    <a class="btn-floating btn-large red">
      <i class="large material-icons">mode_edit</i>
    </a>
    <ul>
      <li><a href="<spring:url value="/add_case" />" class="btn-floating red"><i class="material-icons">assignment</i></a></li>
      <li><a href="<spring:url value="/add_judge" />" class="btn-floating yellow darken-1"><i class="material-icons">assignment_ind</i></a></li>
      <li><a href="<spring:url value="/add_nomination" />" class="btn-floating green"><i class="material-icons">library_add</i></a></li>
      <li><a href="<spring:url value="/add_specialization" />" class="btn-floating blue"><i class="material-icons">assignment_late</i></a></li>
    </ul>
  </div>
-->
</div>
<%@ include file="scripts.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();

  });


</script>
<%@ include file="footer.jsp" %>
