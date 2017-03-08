<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="gray" role="navigation">
    <div class="nav-wrapper ">
     	<span id="logo-container" class="brand-logo page_title">${label}</span>

        <div class="row ">
     	<form name="search" method="GET" action="search_case">
        		<div class="col s3 offset-s6 m2 offset-m7 l1 offset-l8 inline">
        			<input type="text" placeholder="Sygnatura" name="s" style="color: #000" />
        		</div>
        <button class="waves-effect waves-light btn inline" type="submit">Szukaj</button>
            </form>
    </div>
</nav>
