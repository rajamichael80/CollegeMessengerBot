<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/mainPage.css" rel="stylesheet" type="text/css" />
<link href="/css/tableDesign.css" rel="stylesheet" type="text/css" />
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"	rel="stylesheet" type="text/css" />
<link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>

<title>Admin Login form</title>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<h3>College Information System</h3>
			 <form:form action="home" method="GET">
		   <span><a href="<c:url value="/home"/>" style="color: white;">Back to menu</a></span>
		     </form:form>
			
		</div>
		<div class="nav">
					 <form:form action="deleteQuestion" method="GET">
		
			<table id="example" class="table table-striped table-bordered">
			<thead>
				<th th class="th-sm">Id</th>
				<th th class="th-sm">Expected Questions</th>
				<th></th>
				</thead>
				<tbody> 
				<c:forEach var="userQuestion" items="${userQuestions}">
					<tr>
						<td><c:out value="${userQuestion.id}" /></td>
						<td><c:out value="${userQuestion.question}" /></td>
						<td><a href="deleteQuestion/${userQuestion.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody> 
			</table>
			</form:form>

		</div>
	</div>
</body>
</html>
