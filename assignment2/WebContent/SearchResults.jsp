<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="HomePage.css">
	<meta charset="ISO-8859-1">
	<title>Search Results</title>
	
</head>
<body>
	<div class="header">
		<table>
			<tr>
				<td><a href="HomePage.jsp"><img class="logo" src="bookworm.png"/></a></td>
				<td>
					<form method="GET" action="searchServ">
						<input type="text" id="searchText" name="searchText" placeholder="What book is in your mind!" size="40"><br>
						
						<div>
							<input type="radio" name="radioSelect" value="intitle">Name<input
								type="radio" name="radioSelect" value="isbn">ISBN<input
								type="radio" name="radioSelect" value="inauthor">Author<input
								type="radio" name="radioSelect" value="inpublisher">Publisher</div>
						<input class="button" type="submit" value="Search!">
						<style>
							.button {
								  background-color: #4CAF50; /* Green */
								  border: none;
								  color: white;
								  padding: 10px 25px;
								  text-align: center;
								  text-decoration: none;
								  display: inline-block;
								  font-size: 15px;
							 }
						</style>
					</form>
				</td>
			</tr>
		</table>
	</div>
    <h1><i>&nbsp&nbsp&nbspSearch Results:</i></h1>
    <div id="results"></div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
		var data = <%= session.getAttribute("data") %>;
		$("#results").append("<hr style='border-top: dotted 1px;' />");
		if (data==null || data.items==null || data.items.length<1){
	        alert("No result found!");
			var link = document.createElement('a');
	        link.href = "HomePage.jsp";
	        document.body.appendChild(link);
	        link.click();
		}
		
		for(i=0; i<data.items.length; i++) {
			$("#results").append('<table><tr><td rowspan="4">&nbsp'+'<a href="Details.jsp?id='+i+'"><img src="'+data.items[i].volumeInfo.imageLinks.smallThumbnail+'"/></a>'+'</td><td>&nbsp</td></tr>'
			+'<tr><td>&nbsp'+data.items[i].volumeInfo.title+'</td></tr>'
			+'<tr><td>&nbsp'+data.items[i].volumeInfo.authors+'</td></tr>'
			+'<tr><td>&nbsp<i>Summary:</i> '+data.items[i].volumeInfo.description+'</td></tr></table>'
			+"<hr style='border-top: dotted 1px;' />");
		}
</script>
	
	