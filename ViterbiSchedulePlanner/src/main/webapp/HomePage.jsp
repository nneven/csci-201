<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="HomePage.css">
	<meta charset="UTF-8">
<style>
* {
  box-sizing: border-box;
}
.menu {
  float: left;
  width: 20%;
}
.button {
  padding: 8px;
  margin-top: 7px;
  background-color: white;
  border-bottom: 1px solid #f1f1f1;
}
.main {
  float: left;
  width: 60%;
  padding: 0 20px;
  background-color: #c70e3a;
  overflow: hidden;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: white;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
li {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
@media only screen and (max-width:800px) {
  /* For tablets: */
  .main {
    width: 80%;
    padding: 0;
  }
  .right {
    width: 100%;
  }
}
@media only screen and (max-width:500px) {
  /* For mobile phones: */
  .menu, .main, .right {
    width: 100%;
  }
}
</style>
</head>
<body style="font-family:Verdana; background-color:#c70e3a;">
<div class="header">
<ul>
  <li><img src="SC_Logo.png" alt="Schedule Builder" height="100"></li>
  <li style="float:right"><form action = "Login.jsp" id="formA"><button id="Login">Login</button></form> </li>
  <li style="float:right"><form action = "Register.html" id="formB"><button id="Register">Register</button></form></li>
</ul>		
</div>
 <br>
 <br>

<div class="dropdown">
	<div class="form">
		<div class="text">
			<h2> Welcome to Schedule Builder!</h2>
			<br>
		</div>
		<form class="major-form" action="Schedule" method="get">
			<label>Please Register or Login</label><br>
			<label>         OR               </label><br>
			<label for="major">Select a Major as a Guest:</label>
		 	<select class="form-control" name="major" id="major" required>
				<option>CSCI</option>
				<option>CECS</option>
				<option>CSBA</option>
				<option>CSGM</option>
	     	</select>	
	     <button>Select!</button>
	     </form>
	     </div>
</div>
</body>
</html>