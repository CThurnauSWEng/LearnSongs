<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Register</title>
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
			
				<h4>Select languages for ${song.title } by ${song.artist }</h4>	
				
				<form method="POST" action="/showLyrics/${song.id }">		

					<div class="twoPerPage">
						<p>Language 1</p>
						<select name="language1">
							<c:forEach var="lyric" items="${lyricsForThisSong }">
								<option label="${lyric.language }" value="${lyric.id}"/>
							</c:forEach>
						</select>
					</div>
					<div class="twoPerPage">
						<p>Language 2</p>
						<select name="language2">
							<c:forEach var="lyric" items="${lyricsForThisSong }">
								<option label="${lyric.language }" value="${lyric.id}"/>
							</c:forEach>
						</select>
					</div>
					<input type="submit" value="Display Lyrics" class="createButton"/>
					
				</form>

			</div>
			
		</div>
		
	</div>	

</body>
</html>