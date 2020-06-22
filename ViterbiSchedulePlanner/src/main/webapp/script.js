var data; 


/* Dragula JS */
dragula([
  document.getElementById("trash"),
  document.getElementById("sem1"),
  document.getElementById("sem2"),
  document.getElementById("sem3"),
  document.getElementById("sem4"),
  document.getElementById("sem5"),
  document.getElementById("sem6"),
  document.getElementById("sem7"),
  document.getElementById("sem8"),
  document.getElementById("premajor-reqs"),
  document.getElementById("lower-division"),
  document.getElementById("upper-division"),
  document.getElementById("general-education")
]);
removeOnSpill: false
  .on("drag", function(el) {
    el.className.replace("ex-moved", "");
  })
  .on("drop", function(el, container) {
    el.className += "ex-moved";
    if(container.className == "trash"){
      trash.innerHTML == "";
    }
  })
  .on("over", function(el, container) {
    container.className += "ex-over";
  })
  .on("out", function(el, container) {
    container.className.replace("ex-over", "");
  });


function loadSaved(DATA) {
	data = DATA;
  for(i in DATA.schedule ){
    var section = document.getElementById( DATA.schedule[i].section );
    for(j in DATA.schedule[i].classes){
      var course = DATA.schedule[i].classes[j];
      if(section != null) { 
        section.innerHTML += '<li class="class ' + course.deptcode + '-class" id= ' + course.deptcode + '-' + course.coursecode + '>' +
              '<p>' + course.deptcode + " " + course.coursecode + '</p></li>'
      }
    }
  }
}


function saveData(){
	var json = '{ "schedule": [ ';
	var containers = document.getElementsByClassName("class-list");
	for (element of containers) {
		
		json += '{ "section": "' + element.id + '", "classes": [';
		var classes = element.querySelectorAll(".class");
		
		for (i of classes){
			var name = i.id;
			var deptcode = name.substring(0, name.indexOf("-"));
			var coursecode = name.substring(name.indexOf("-")+1);
			json += '{ "deptcode":"' + deptcode + '", "coursecode":"'+ coursecode +'"},';
		}
		if (classes[0] != null) json = json.substring(0, json.length - 1);
		json += '] },'
	}
	json = json.substring(0, json.length - 1);
	json += "]}";
	
	
	
	
	
	
	$(document).ready(function() {
		$.post("Schedule",
		{
			schedule: json
		}, 
		function(message, status) {
			if (message != "success") {
				window.alert(message);
			}
		});
	});
	
	return false;
}





/* Vanilla JS to add a new class */
function addclass() {
  /* Get class text from input */
  var inputclass = document.getElementById("classText").value;
  /* Add class to the 'To Do' column */
  document.getElementById("to-do").innerHTML +=
    "<li class='class'><p>" + inputclass + "</p></li>";
  /* Clear class text from input after adding class */
  document.getElementById("classText").value = "";
}

/* Vanilla JS to delete classes in 'Trash' column */
function emptyTrash() {
  /* Clear classes from 'Trash' column */
  document.getElementById("sem4").innerHTML = "";
}

