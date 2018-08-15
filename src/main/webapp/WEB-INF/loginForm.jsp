<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
  	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	
	<div class="sky">
	
		<div class="stars">
		
			<div class="genericForm">

				<h1>Login</h1>
				
				<p><c:out value="${error }" /></p>
				
				<form method = "post" action="/login">
					<div class="labelDiv">
						<label for="email">Email:</label>
						<p></p>
						<label for="password">Password:</label>
					</div>
					
					<div class="inputDiv">
						<input type="text" id="email" name="email"/>
						<p></p>
						<input type="password" id="password" name="password"/>
					</div>
						
					<p></p>
					
					<input type="submit" value="Login"/>
					
				</form>
			
			</div>
		
		</div>
				
	</div>

</body>
</html>