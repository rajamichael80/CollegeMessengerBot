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
			<tr>
		    <form:form action="userInfo" method="GET">
			
			<td><a href="<c:url value="/userInfo"/>">User Details</a></td>
					 </form:form>
					 			<td><a href="<c:url value="/userQuestions"/>">Expected Questions</a></td>
					 
		      <form:form action="userQuestions" method="GET">
			
			
			</tr>
     		</table>
		 </form:form>
		</div>
		<div class="container"></div>
	</div>
</body>
</html>
