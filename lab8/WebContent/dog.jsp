<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>cat page</title>
</head>
<body>

	<form method="GET" action="JDBCServer">
		<input type="hidden" name="requestPage" value="dog.jsp" />
		<input type="hidden" name="redirectPage" value="cat.jsp" />
		<input type="submit" value="Go to Meow Page">
	</form>
	<form method="GET" action="JDBCServer">
		<input type="hidden" name="requestPage" value="dog.jsp" />
		<input type="hidden" name="redirectPage" value="statistics.jsp" />
		<input type="submit" value="Stats">
	</form>


</body>
</html>