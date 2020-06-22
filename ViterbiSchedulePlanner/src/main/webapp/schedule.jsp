<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  
  <title>Viterbi Course Plannerl</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="./style.css">
  <script>
  	var user = <%=session.getAttribute("user")%>;
  </script>
<style>
#ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: white;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
#li {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
</style>
</head>

<body onload="loadSaved(user);">
  	<div class="header">
<ul id="ul">
  <li id="li"><a style="text-decoration: none; border-bottom: 1px solid white;" href="HomePage.jsp"><img src="SC_Logo.png" alt="Schedule Builder" height="100"></a></li>
  <li id="li" style="float:right"><a href="Profile"><img id="profile" src="ProfileLogo.png" ALIGN="right" alt="ProfileLogo" height="80"></a></li>
</ul>		
</div>
<script>
var guest = <%= session.getAttribute("guest") %>
	if (guest != null) {
		document.getElementById("profile").style.display = "none";
	}
</script>
      <br><br><br>
<div class="main-container">
    <form onsubmit="return saveData();" action="HomePage.jsp"><input type="hidden" name="data"><input class="button" type="submit" value="Save" style="float: left;"> </form>
    <table id="course-table" cellspacing="0" cellpadding="0" > 
    <tr style="background-color:white;">
      <td> <table style="background-color:white;"> <tr style="background-color:white;">
      <td style="text-align:center; padding: 10px;vertical-align: bottom;"> Freshman </td>
      <td style="text-align:center; padding: 10px;vertical-align: bottom;"> Sophomore </td>
      </tr> </table> </td>
      <td> <table style="background-color:white;"> <tr style="background-color:white;">
      <td style="text-align:center; padding: 10px;vertical-align: bottom;"> Junior</td>
      <td style="text-align:center; padding: 10px;vertical-align: bottom;"> Senior</td>
      </tr> </table> </td>
    </tr>
    <tr> 
      <td> <ul class="columns">
        <li class="column sem1-column">
          <!-- <div class="column-header">
            <h4>Frosh Fall</h4>
          </div> -->
          <ul class="class-list" id="sem1">
          </ul>
        </li>

        <li class="column sem3-column">
          <ul class="class-list" id="sem3">
          </ul>
        </li>
      </ul>
       </td><td><ul class="columns">

        <li class="column sem5-column">
          <ul class="class-list" id="sem5">
          </ul>
        </li>

        <li class="column sem7-column">
          <ul class="class-list" id="sem7">
          </ul>
        </li>
      </ul> </td>
    </tr>
    <tr> 
      <td> <ul class="columns">
        <li class="column sem2-column">
          <ul class="class-list" id="sem2">
          </ul>
        </li>

        <li class="column sem4-column">
          <ul class="class-list" id="sem4">
          </ul>
        </li>
      </ul>
      </td><td>
        <ul class="columns">
        <li class="column sem6-column">
          <ul class="class-list" id="sem6">
          </ul>
        </li>

        <li class="column sem8-column">
          <ul class="class-list" id="sem8">
          </ul>
        </li>
      </ul> </td>
    </tr>

    <tr style="background-color:white;">
      <td style="text-align:center; padding: 10px;vertical-align: bottom;">Major Requirements</td>
      <td style="text-align:center; padding: 10px;vertical-align: bottom;">GE Requirements</td>
    </tr>


    <tr style="background-color:white;" >
      <td>
    <ul class="columns">
      <li class="column major-column">
        <ul class="class-list" id="premajor-reqs">
        </ul>
      </li>

      <li class="column major-column">
        <ul class="class-list" id="lower-division">
        </ul>
      </li>
    </ul> 
  </td>
  <td>
    <ul class="columns">
      <li class="column major-column">
        <ul class="class-list" id="upper-division">
        </ul>
      </li>

      <li class="column ges-column">
        <ul class="class-list" id="general-education">
        </ul>
      </li>
    </ul> 
  </td>
  </tr></table>
</div>

<footer>
  <p></p>
</footer>
<!-- partial -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/dragula/3.7.2/dragula.js'></script><script  src="./script.js"></script>

</body>
</html>