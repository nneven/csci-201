<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="./Login.css">
	<script></script>
</head>
<body>
<% 
if(request.getAttribute("message") == null){
	request.setAttribute("message", "");
}
%>
	<h1>
		<a href="HomePage.jsp"><img src="SC_Logo.png" alt="Schedule Builder" height="100"></a>
	</h1>
	<div class="welcome">
		Welcome Back! 
	</div>
	<div class="login-page">
	  <div class="form">
	    <form class="login-form" action="Login" method="post">
	    <%=request.getAttribute("message")%>
	      <input type="text" name="username" placeholder="username" required/>
	      <input type="password" name="password" placeholder="password" required/>
	      <button>login</button>
	      <p class="message">Not registered? <a href="Register.jsp">Create an account</a></p>
	    </form>
	  </div>
	</div>
</body>
</html>