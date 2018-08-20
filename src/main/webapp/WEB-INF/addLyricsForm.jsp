<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Learn Songs</title>
  	<link rel="stylesheet" type="text/css" href="../css/style.css">
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
			
				<h4>Select a language for entering lyrics for ${song.title }</h4>
			
				<p><form:errors path="lyric.*"></form:errors>
				<p>${error }</p>
			
				<form:form action="/createNewLyrics" method="POST" modelAttribute="lyric">

					<div class="labelDiv">
						<form:label path="language">Language:</form:label>
						<p></p>
					</div>	
					
					<div class="inputDiv">
						<form:input type="text" path="language"/>
						<form:input type="hidden" path="song" value="${song.id }"/>
						<p></p>
					</div>	
							
					<p></p>
										
					<input type="submit" value="Create" class="ovalButton"/>
									
				</form:form>
			
			</div>
		
		</div>
		
	</div>
	
	
</body>
</html>