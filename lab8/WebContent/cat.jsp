<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>cat page</title>
</head>
<body>

	<form method="GET" action="JDBCServer">
		<input type="hidden" name="requestPage" value="cat.jsp" /> 
		<input type="hidden" name="redirectPage" value="dog.jsp" />
		<input type="submit" value="Go to Woof Page">
	</form>
	<form method="GET" action="JDBCServer">
		<p> Stats </p>
		<input type="hidden" name="requestPage" value="cat.jsp" />
		<input type="hidden" name="redirectPage" value="stats.jsp" />
		<input type="submit" value="Stats">
	</form>


</body>
</html>