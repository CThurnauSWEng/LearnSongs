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
			
				<form method="POST" action="/showLyrics/${song.id }">		

					<div class="twoPerPage">
						<p>${lyric1.language }
						<c:forEach var="line" items="${lines1 }">
							<p>${line.lyricLine }</p>
						</c:forEach>
					</div>
					<div class="twoPerPage">
						<p>${lyric2.language }
						<c:forEach var="line" items="${lines2 }">
							<p>${line.lyricLine }</p>
						</c:forEach>
					</div>
					<input type="submit" value="Display Lyrics" class="createButton"/>
					
				</form>

			</div>
			
		</div>
		
	</div>	

</body>
</html>