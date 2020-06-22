<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="./Register.css">
</head>
<body>
<%
if(request.getAttribute("UserExists") == null){
	request.setAttribute("UserExists", "");
}
%>
	<h1>
		<a href="HomePage.jsp"><img src="SC_Logo.png" alt="Schedule Builder" height="100"></a>
	</h1>
	<div class="start">
		Get Started!
	</div>
	<div class="register-page">
	  	<div class="form">
		    <form class="register-form" action="Register" method="post">
		    <%=request.getAttribute("UserExists")%>
		      <input type="text" name="name" placeholder="name" required />
		      <input type="password" name="password" placeholder="password" required/>
		      <input type="text" name="email" placeholder="email address" required />
		      <label for="major">Select a Major</label>
		      <select class="form-control" name="major" id="major" required>
			      <option>CSCI</option>
			      <option>CECS</option>
			      <option>CSBA</option>
			      <option>CSGM</option>
			  </select>	
			  <label for="gradyear">Select a Graduation Year</label>
			  <select class="form-control" name="gradyear" id="gradyear" required >
			      <option>Fall 2019</option>
			      <option>Spring 2020</option>
			      <option>Fall 2020</option>
			      <option>Spring 2021</option>
			      <option>Fall 2021</option>
			      <option>Spring 2022</option>
			      <option>Fall 2022</option>
			      <option>Spring 2023</option>
			  </select>	      
		      <button>register</button>
		      <p class="message">Already registered? <a href="Login.jsp">Sign In</a></p>
		    </form>
		</div>
	</div>
</body>
</html>