<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<body>

 <div class="log_container">
 <div class="log_shadow">
 	<div class="message z-depth-5">
 		<div class="row">
 		<form:form method="POST" action="login" modelAttribute="user">
 		<div class="input-field col s12">
 			<form:input type="text" placeholder="Adres e-mail" id="login" name="login" class="validate" path="mail" />
         </div>
 		<div class="input-field col s12">
 			<form:input type="password" placeholder="Hasło" id="password" name="password" class="validate" path="pass" />
         </div>
         <c:if test="${error != null}"><h5>${error}</c:if>
 		</div>
         <div class="row">
          	<button class="waves-effect waves-light btn" type="submit">Dalej</button>
             </form:form>

         </div>
         </div>
 	</div>
 </div>

</body>
</html>

