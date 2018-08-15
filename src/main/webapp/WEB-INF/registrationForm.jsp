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
		
			<div class="genericForm">


				<h1>Register</h1>
				
				<p>${error }</p>
					
				<p><form:errors path="user.*"/></p>
			
				<form:form method="POST" action="/processRegistrationForm" modelAttribute="user">
			
					<div class="labelDiv">
						<form:label path="firstName">First Name:</form:label>
						<p></p>
						<form:label path="lastName">Last Name:</form:label>
						<p></p>
						<form:label path="email">Email:</form:label>
						<p></p>
						<form:label path="password">Password:</form:label>
						<p></p>
						<form:label path="passwordConfirmation">Confirm Password:</form:label>
					</div>
			
					<div class="inputDiv">
						<form:input type="text" path="firstName"/>
						<p></p>
						<form:input type="text" path="lastName"/>
						<p></p>
						<form:input type="email" path="email"/>
						<p></p>
						<form:input type="password" path="password"/>
						<p></p>
						<form:input type="password" path="passwordConfirmation"/>
					</div>
						
					<p></p>
					
					<input type="submit" value="Register"/>
					
				</form:form>
			</div>
		
		</div>
				
	</div>


			
</body>
</html>