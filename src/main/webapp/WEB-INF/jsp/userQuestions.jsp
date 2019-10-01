<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/mainPage.css" rel="stylesheet" type="text/css" />
<link href="/css/tableDesign.css" rel="stylesheet" type="text/css" />


<title>Admin Login form</title>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<h3>College Information System</h3>
		</div>
		<div class="nav">
			<table class="imagetable">
				<th>Id
				<th>Expected Questions</th>
				<c:forEach var="userQuestion" items="${userQuestions}">
					<tr>
						<td><c:out value="${userQuestion.id}" /></td>
						<td><c:out value="${userQuestion.question}" /></td>
					</tr>
				</c:forEach>

			</table>

		</div>
		<div class="container"></div>
	</div>
</body>
</html>
