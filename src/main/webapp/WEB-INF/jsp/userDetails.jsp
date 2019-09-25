<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<table width="100%" border="1">
				<th>Id
				
				<th>FirstName</th>
				<th>LastName</th>
				<th>SenderId</th>

				<c:forEach var="user" items="${users}">
					<tr>
						<td><c:out value="${user.id}"/></td>
						<td><c:out value="${user.firstName}"/></td>
						<td><c:out value="${user.lastName}"/></td>
						<td><c:out value="${user.senderId}"/></td>
					</tr>
				</c:forEach>

			</table>

		</div>
		<div class="container"></div>
	</div>
</body>
</html>
