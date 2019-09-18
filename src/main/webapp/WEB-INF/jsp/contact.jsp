<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Heroku Connect Demo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../assets/css/bootstrap.css" th:href="@{/assets/css/bootstrap.css}" />
    <link rel="stylesheet" href="../assets/css/thymeleaf-demo.css" th:href="@{/assets/css/style.css}" />
</head>
<body>
<!--<div style="margin-top: 20px;margin-left: 50px">-->
<div class="container">
<h3> Salesforce Contacts : </h3>
<table>
<th><td>Id</td><td>SFID</td><td>First</td><td>Last</td><td>Email</td></th>
<tr th:each="contact : ${contacts}">
    <td></td>
    <td th:text="${contact.id}" ></td>
    <td th:text="${contact.sfid}" ></td>
    <td th:text="${contact.firstName}" ></td>
    <td th:text="${contact.lastName}" ></td>
    <td th:text="${contact.email}" ></td>
</tr>
</table>
</div>
</body>
</html>