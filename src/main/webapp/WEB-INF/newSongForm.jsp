<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Learn Songs</title>
  	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>

	<div class="sky">
	
		<div class="stars">
		
			<div id="navbar">

				<h1 class="funTitle">Learn Languages the fun way!</h1>
		
				<a href="/home" class="ovalButton" >Home</a>
				<a href="/logout" class="ovalButton"> Logout</a>
				
			</div>
			
			<div class="genericForm">
			
				<p><form:errors path="song.*"></form:errors>
				<p>${error }</p>
			
				<form:form action="/createNewSong" method="POST" modelAttribute="song">

					<div class="labelDiv">
						<form:label path="title">Song Title:</form:label>
						<p></p>
						<form:label path="artist">Artist:</form:label>
					</div>	
					
					<div class="inputDiv">
						<form:input type="text" path="title"/>
						<p></p>
						<form:input type="text" path="artist"/>
					</div>	
							
					<p></p>
										
					<input type="submit" value="Create" class="ovalButton"/>
									
				</form:form>
			
			</div>
		
		</div>
		
	</div>
	
	
</body>
</html>