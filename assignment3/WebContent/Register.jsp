<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="Header.css">
	<meta charset="UTF-8">
	<title>Register</title>
	<script type="text/javascript">
		function message() {
			if (<%=session.getAttribute("message")%> != null) {
				document.getElementById("error").style = "display: inline-block;";
			} else {
				document.getElementById("error").style = "visibility: hidden;";
			}
		}
	</script>
</head>
<body onload="message()">
	<div class="header">
		<a href="HomePage.jsp"><img class="logo" src="bookworm.png"/></a>
			<form method="GET" action="searchServ">
				<input type="text" id="searchText" name="searchText" placeholder="What book is in your mind?" size="40">
				<input type="radio" name="radioSelect" value="intitle">Name
				<input type="radio" name="radioSelect" value="isbn">ISBN
				<input type="radio" name="radioSelect" value="inauthor">Author
				<input type="radio" name="radioSelect" value="inpublisher">Publisher
				<input class="button" type="submit" value="Search!">
			</form>
	</div>
	<div>
		<form class="body" action="userServlet" method="POST">
			Username <br> <input type="text" name="username"> <br>
			Password <br> <input type="password" name="password"> <br>
			Confirm Password <br> <input type="password" name="cPassword"> <br>
			<button onclick="message()" type="submit">Register</button>
			<input type="hidden" name="page" value="Register.jsp" />
		</form>
		<h3 id="error"> <%=session.getAttribute("message")%></h3>
	</div>
</body>
</html>