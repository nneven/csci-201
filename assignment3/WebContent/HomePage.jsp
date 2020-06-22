<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="HomePage.css">
		<title>HomePage</title>
	</head>
	<body onload="update()">
		<div class="header">
			<table>
				<tr>
					<td><img class="logo" src="bookworm.png" /></td>
					<td><form id="f1"><button type="submit" id="b1"></button></form></td>
					<td><form id="f2"><button type="submit" id="b2"></button></form></td>
				</tr>
			</table> 	
		</div>
		
		<div class="content">
			<div class="form-content">
				<h1>BookWorm: Just a Mini Program... Happy Days!</h1>
				<form method="GET" action="searchServ">
					<input type="text" name="searchText" placeholder="Search for your favorite book!"><br>
					<table style="float: right;margin-right:400px;">
						<tr>
							<td>
								<input type="radio" name="radioSelect" value="intitle" ><font color="white">Name</font>
							</td>
							<td>
								<input type="radio" name="radioSelect" value="inauthor"><font color="white">Author</font>
								
							</td>
							</tr>
						<tr>
							<td>
								<input type="radio" name="radioSelect" value="isbn"><font color="white">ISBN</font>
							</td>
							<td>
								<input type="radio" name="radioSelect" value="inpublisher"><font color="white">Publisher</font>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp
							</td>
							<td>
								&nbsp
							</td>
						</tr>
						<tr>
							<td>
								<input class="button" type="submit" value="Search!" style="float: left;">
							</td>
							<td>
								
							</td>
						</tr>
					</table>	
				</form>
			</div>
		</div>
		
		<script type="text/javascript">
			function update() {
				if (<%=session.getAttribute("login")%> == true) {
					<%session.setAttribute("login", true);%>
					document.getElementById("f1").action = "Profile.jsp";
					document.getElementById("b1").innerHTML = "Profile";
					document.getElementById("b2").innerHTML = "Sign Out";
					document.getElementById("f2").action = "javascript:signOut()";
				} else {
					<%session.setAttribute("login", false);%>
					document.getElementById("b1").innerHTML = "Login";
					document.getElementById("f1").action = "Login.jsp";
					document.getElementById("b2").innerHTML = "Register";
					document.getElementById("f2").action = "Register.jsp";
				}
			}
			
			function signOut() {
				<%session.setAttribute("login", false);%>
				document.location.reload(true);
				return false;
			}
		</script>
	</body>
</html>