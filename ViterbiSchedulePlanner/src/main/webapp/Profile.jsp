<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="Profile.css">
<style>
.main-container {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 80%;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}

body {
  padding: 0;
  margin: 0;
  background: #c70e3a; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #c70e3a, #c73850);
  background: -moz-linear-gradient(right, #c70e3a, #c73850);
  background: -o-linear-gradient(right, #c70e3a, #c73850);
  background: linear-gradient(to left, #c70e3a, #c73850);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
.header{
  width: 100%;
  background-color: white;
  padding: 0;
  margin: 0;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  position: sticky;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: white;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
li {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
</style>
<meta charset="UTF-8">
<title>My Profile Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
</head>
<body onload="update()">
<div class="header">
  	<ul>
	  <li><a href="HomePage.jsp"><img src="SC_Logo.png" alt="Schedule Builder" height="100"></a>    </li>
	  <li style="float:right"><form action = "schedule.jsp" id="formA"><button id="Schedule" style="float: right">Schedule!</button></form> </li>
</ul>
  </div>
  <br><br><br>
<div class="main-container">
	<h2> <%= session.getAttribute("name") %> </h2>
<h3> <%= session.getAttribute("major") %> </h3>
<h4> <%= session.getAttribute("gradyear") %> </h4>
	<p id="table"></p>	
<div id="demo"></div>
</div>
<script>
	var modal = "";
	var txt = "<table id='classes' class='display' border='1'><thead><tr><th>Class</th><th>Description</th><th>Details</th></tr></thead><tbody>";
	DATA = <%= session.getAttribute("classes") %>;
	for(i in DATA.schedule) {
		for(j in DATA.schedule[i].classes) {
			var course = DATA.schedule[i].classes[j];
			txt += "<tr><td>" + course.deptcode + " " + course.coursecode + "</td><td>" + course.name + "</td><td><a href='#ex" + course.deptcode + course.coursecode + "' rel='modal:open'>View</a></td></tr>";
			modal += "<div id='ex" + course.deptcode + course.coursecode + "' class='modal'><p>" + course.description + "</p><a href='#' rel='modal:close'>Close</a></div>";
		}
	}
	txt += "</tbody></table>"    
	document.getElementById("table").innerHTML = txt;
	document.getElementById("demo").innerHTML = modal;
	$(document).ready(function() {
	    $('#classes').DataTable();
	} );
</script>

</body>
</html>