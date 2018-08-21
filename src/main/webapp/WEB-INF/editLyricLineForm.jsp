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
					
				<h1 class="funTitle">Edit line for ${song.title } in ${lyric.language }</h1>	
				<p></p>
								
				<form:form action="/processEditLine/${song.id }/${lyric.id}/${sLine.id }" method="Post" modelAttribute="sLine">
					<div class="inputDiv">
						<p class="lyricLine">Line:</p>
						<input type="text" class="lyricInputText" name="lyricLine" value="${sLine.lyricLine }"/>
						<input type="submit" value="submit"/>
					</div>					
				</form:form>
			</div>
		
		</div>
		
	</div>
	
	
</body>
</html>