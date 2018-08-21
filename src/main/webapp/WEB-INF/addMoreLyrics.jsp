<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Learn Songs</title>
  	<link rel="stylesheet" type="text/css" href="../../css/style.css">
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
					
				<h1 class="funTitle">Lyrics for ${song.title } in ${lyric.language }</h1>	
				<p></p>
				
				<a href="/editLanguage/${lyric.id }" class="editLyricButton">Edit Language</a>
				<a href="/deleteLyric/${lyric.id }" class="editLyricButton">Delete these lyrics</a>

				<c:forEach items = "${allLyricLines }" var = "thisLine">
					<p class="lyricLine">${thisLine.lyricLine }</p>
				</c:forEach>
				
				<form:form action="/addLyrics" method="Post" modelAttribute="sLine">
					<div class="inputDiv">
						<p class="lyricLine">Line:</p>
						<input type="text" class="lyricInputText" name="lyricLine" value=""/>
						<input type="submit" value="submit"/>
					</div>					
				</form:form>
			</div>
		
		</div>
		
	</div>
	
	
</body>
</html>