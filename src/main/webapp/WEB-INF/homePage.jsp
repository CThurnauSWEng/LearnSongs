<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Register</title>
  	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	
	<div class="sky">
	
		<div class="stars">
		
			<div id="navbar">

				<h1 class="funTitle">Welcome <c:out value="${user.firstName }"/></h1>
		
				<a href="/logout" class="ovalButton" >Logout</a>
				
			</div>
			
			<p><a href="/newSong" class="linkOnSky">Add a new song</a>
			
	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Artist</th>
				<th>Languages</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items = "${allSongs }" var = "song">
			<tr>
				<td>
					<a href="/${song.id}">
						<c:out value="${song.title}"/></a>
				</td>
				<td><c:out value="${song.artist}"/></td>
				<td>Placeholder for languages</td>
				<td>
					<a href="/delete/${song.id}">Delete</a>											
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
			
			
		
		</div>
		
	</div>
	
</body>
</html>