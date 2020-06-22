<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="Header.css">
	<meta charset="UTF-8">
	<title>Profile</title>
</head>
<body onload="update()">
	<div class="header">
		<a href="HomePage.jsp"><img class="logo" src="bookworm.png"/></a>
			<form id="searchForm" method="GET" action="searchServ">
				<input type="text" id="searchText" name="searchText" placeholder="What book is in your mind?" size="40">
				<input type="radio" name="radioSelect" value="intitle">Name
				<input type="radio" name="radioSelect" value="isbn">ISBN
				<input type="radio" name="radioSelect" value="inauthor">Author
				<input type="radio" name="radioSelect" value="inpublisher">Publisher
				<input class="button" type="submit" value="Search!">
			</form>
		<a href="Profile.jsp"><img class="icon" src="profile.png"/></a>
	</div>
	<div>
		<h2> Welcome <%=session.getAttribute("user")%>! Favorite books:</h2>
	</div>
	<div id="results"></div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
		$.get("profileServlet", function(data, status) {
			var data = <%= session.getAttribute("profile") %>;
			$("#results").append("<hr style='border-top: dotted 1px;' />");
			for(i=0; i<data.items.length; i++) {
				$("#results").append('<table><tr><td rowspan="4">&nbsp'+'<a href="Details.jsp?id='+i+'&attribute=profile"><img src="'+data.items[i].volumeInfo.imageLinks.smallThumbnail+'"/></a>'+'</td><td>&nbsp</td></tr>'
				+'<tr><td>&nbsp'+data.items[i].volumeInfo.title+'</td></tr>'
				+'<tr><td>&nbsp'+data.items[i].volumeInfo.authors+'</td></tr>'
				+'<tr><td>&nbsp<i>Summary:</i> '+data.items[i].volumeInfo.description+'</td></tr>'
				+'<tr><td><button id='+data.items[i].id+'>Remove</button></td></tr></table>'
				+"<hr style='border-top: dotted 1px;' />");
			}
			
		});
</script>
<script> <%session.setAttribute("login", true);%></script>
<script>
	$(document).ready(function() {
		$("#results").on('click', function() {
			var name = this.id;
			var data = <%= session.getAttribute("profile") %>;
			$.post("profileServlet", 
			{
				id: name,
				user: "<%=session.getAttribute("user")%>",
				request: "Remove"
			},
			function(data, status) {
				$("button").text("Favorite");
				document.location.reload(true);
			});
		});
	});
</script>
</html>