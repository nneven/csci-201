<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="Header.css">
	<meta charset="ISO-8859-1">
	<title>Details</title>
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
<body onload="update()">
	<div class="header">
		<a href="HomePage.jsp"><img class="logo" src="bookworm.png"/></a>
			<form id="searchForm" method=GET action="searchServ">
				<input type="text" id="searchText" name="searchText" placeholder="What book is in your mind?" size="40">
				<input type="radio" name="radioSelect" value="intitle">Name
				<input type="radio" name="radioSelect" value="isbn">ISBN
				<input type="radio" name="radioSelect" value="inauthor">Author
				<input type="radio" name="radioSelect" value="inpublisher">Publisher
				<input class="button" type="submit" value="Search!">
			</form>
		<a href="Profile.jsp"><img id="profile" class="icon" src="profile.png"/></a>
	</div>
	<div id="results"></div>
	<h3 id="error"> <%=session.getAttribute("message")%></h3>
	
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
<script>

	var id = parseInt(window.location.search.substring(1).substring(3));
	var type = window.location.search.substring(16).trim();
	var data = <%= session.getAttribute("data") %>;
	
	$("#results").html("<hr style='border-top: dotted 1px;' />");
	$("#results").append('<table><tr><td rowspan="8">&nbsp'+'<img src="'+data.items[id].volumeInfo.imageLinks.smallThumbnail+'" onclick="history.back()"/>'+'</td><td>&nbsp</td></tr>'
			+'<tr><td><h2>&nbsp'+data.items[id].volumeInfo.title+'</h2></td></tr>'
			+'<tr><td><i>Author:</i>&nbsp'+data.items[id].volumeInfo.authors+'</td></tr>'
			+'<tr><td><i>Publisher:</i>&nbsp'+data.items[id].volumeInfo.publisher+'</td></tr>'
			+'<tr><td><i>Publish Date:</i>&nbsp'+data.items[id].volumeInfo.publishedDate+'</td></tr>'
			+'<tr><td><i>ISBN:</i>&nbsp'+data.items[id].volumeInfo.industryIdentifiers[0].identifier+'</td></tr>'
			+'<tr><td><i>Summary:</i>&nbsp'+data.items[id].volumeInfo.description+'</td></tr>'
			+'<tr><td><i>Rating:</i><span id="star"></span></td></tr>'
			+'<tr><td><button id="status"></button></td></tr>'
			+'</table>');
	// Using a jQuery library for rating
	$(function () {
	  $("#star").rateYo({
	    rating: data.items[id].volumeInfo.averageRating,
	    readOnly: true
	  });
	});
</script>
<script>
	function update() {
		message();
		if (<%=session.getAttribute("login")%> == true) {
			document.getElementById("profile").style = "display: inline-block;";
		} else {
			document.getElementById("profile").style = "visibility: hidden;";
		}
	}
</script>
<script>
	var num = parseInt(window.location.search.substring(1).substring(3));
	var data = <%= session.getAttribute("data") %>;
	$(document).ready(function() {
		$.post("profileServlet", 
		{
			id: data.items[num].id,
			user: "<%=session.getAttribute("user")%>",
			request: "Find"
		},
		function(data, status) {
			$("button").text(data);
		});
		$("button").click(function() {
			num = parseInt(window.location.search.substring(1).substring(3));
			data = <%= session.getAttribute("data") %>;
			$.post("profileServlet", 
			{
				id: data.items[num].id,
				user: "<%=session.getAttribute("user")%>",
				request: document.getElementById("status").innerHTML
			},
			function(data, status) {
				$("button").text(data);
			});
		});
	});
</script>
</html>