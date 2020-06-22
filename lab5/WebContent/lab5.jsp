<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CSCI 201 FORM</title>
</head>
	<body>
		<h2>Lab 5 Form</h2>
		<form action="ValidateServer" method="Get">
            Email: <input type="email" name="email" value=<%=request.getAttribute("email")%> /><br/>
            Password: <input type="text" name="password" /><br/>
            Are you a USC student? <input type="checkbox" /><br/>
            <input type="submit" name="submit" value = "Submit Form" />
            <input type="reset" name="reset" value = "Reset" />
        </form>
	</body>
</html>