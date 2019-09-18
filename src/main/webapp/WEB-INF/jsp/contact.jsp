<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Heroku Connect Demo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
</head>
<body>
<!--<div style="margin-top: 20px;margin-left: 50px">-->
<div class="container">
<h3> Salesforce Contacts : </h3>
<table>
<th><td>Id</td><td>SFID</td><td>First</td><td>Last</td><td>Email</td></th>
<c:forEach var="contact" items="${contacts}" varStatus="loopCounter" >

<tr>
    <td></td>
    <td><c:out value="${'Welcome to javaTpoint'}"/>  </td>
   
</tr>
</c:forEach>
</table>
</div>
</body>
</html>