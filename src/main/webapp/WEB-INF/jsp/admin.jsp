
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/mainPage.css" rel="stylesheet" type="text/css" />

<title>Admin Login form</title>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<h3>College Information System</h3>
		</div>
		<div class="nav">
			<div class="login">
				<form:form id="loginForm" modelAttribute="admin" action="loginProcess" method="post">
					<form:input path="username" name="username" placeholder="Username" id="username" />
					<form:password path="password" name="password"
						placeholder="Password" id="password" />
					<form:button id="login-admin" name="login" >Login</form:button>
				</form:form>
			<span style="color:white;margin-left:40px">${message}</span>
				
			</div>
			
		</div>
		<div class="container"></div>
	</div>
</body>
</html>
