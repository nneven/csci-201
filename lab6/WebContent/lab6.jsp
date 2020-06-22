<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lab 6 Form</title>
		<script>
		function validate() {
			var xhttp = XMLHttpRequest();
			xhttp.open("GET", "ValidateServer?fname=" + document.myform.fname.value + "&lname=" + document.myform.lname.value, false);
			xhttp.send();
			if (xhttp.responseText.trim().length > 0) {
				document.getElementById("formerror").innerHTML = xhttp.responseText;
				return false;
			}
			return false;
		}
		</script>
	</head>
	<body>
		<form name="myform" method="GET" action="success.jsp" onsubmit="return validate();">
			<div id="formerror"></div>
			First Name <input type="text" name="fname" /><br />
			Last Name <input type="text" name="lname" /><br />
			<input type="submit" name="submit" value="Submit" />
		</form>
	</body>
</html>