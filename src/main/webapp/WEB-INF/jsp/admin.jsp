<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
			<div class="admin">
			<form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
			<form:input path="username" name="username" placeholder="Username" id="username"/>  
			 <form:password path="password" name="password" placeholder="Password" id="password"/>  
			<form:button id="login" name="login">Login</form:button>
			</form:form>
			</div>
		</div>
		<div class="container"></div>
		
		<div class="container">
	</div>
</div>
</body>
</html>
