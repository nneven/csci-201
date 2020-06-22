<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Successful!</title>
</head>
	<body>
		<table style="width:100%">
		  <tr>
		    <th>First</th>
		    <th>Last</th> 
		  </tr>
		  <tr>
		    <td><%=request.getParameter("fname")%></td>
		    <td><%=request.getParameter("lname")%></td>
		  </tr>
	</table>
	</body>
</html>