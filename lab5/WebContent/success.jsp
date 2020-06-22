<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
	<body>
		<table style="width:100%">
		  <tr>
		    <th>EMAIL</th>
		    <th>PASSWORD</th> 
		  </tr>
		  <tr>
		    <td><%=request.getParameter("email")%></td>
		    <td><%=request.getParameter("password")%></td>
		  </tr>
	</table>
	</body>
</html>