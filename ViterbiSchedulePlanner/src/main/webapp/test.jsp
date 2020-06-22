<!DOCTYPE html>
<html>
<body>
<h2>Test Page!</h2>
<h3 id="h3"></h3>
<form action="Profile" method="get">
	<input type="submit" name="button" value="Profile" />
</form>
<form action="Schedule" method="post">
	<input type="submit" name="schedule" value="Schedule" />
</form>
<form onsubmit="return update()" >
	<input type="submit" name="ajax" value="Schedule" />
</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function update() {
	document.getElementById("h3").innerHTML = "test";
	$(document).ready(function() {
		$.post("Profile", 
		{
			data: ""
		},
		function(data, status) {
			$("h3").html(status);
		});
	});
	return false;
}
</script>
</html>
