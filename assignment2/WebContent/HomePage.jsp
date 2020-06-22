<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="HomePage.css">
		<title>HomePage</title>
	</head>
	<body>
		<div class="header">
			<table>
				<tr>
					<td><img class="logo" src="bookworm.png" /></td>
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
								<input type="radio" name="radioSelect" value="intitle" ><font color="white">Name</font></div>
							</td>
							<td>
								<input type="radio" name="radioSelect" value="inauthor"><font color="white">Author</font></div>
								
							</td>
							</tr>
						<tr>
							<td>
								<input type="radio" name="radioSelect" value="isbn"><font color="white">ISBN</font></div>
							</td>
							<td>
								<input type="radio" name="radioSelect" value="inpublisher"><font color="white">Publisher</font></div>
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
	</body>
</html>