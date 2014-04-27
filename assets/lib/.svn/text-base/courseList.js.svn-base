$(document).on('pageinit', '#courseSelectionScreen', function(event) {
	$('#courselist').listview();

	list = $('#courselist');
	console.log("course");

	// enable closing the popup when user clicks on 'confirm' button
	$(document).on("vclick",'#newCourseButton', function (ev) {
		var courseInput = $('#newCourseInput').val();
	 	if (courseInput != ""){
	 		
			console.log("input in if = " + courseInput);
		
			$('#driverIdInput').val("");
			jsBridge.handleJs("getNewCourse", courseInput);
			
		}
	 	
	    ev.preventDefault();
});
});

var listCreated = false;
var name, number;
var listHtml = "";
var courseNumberChoosed;

function generateCourseList(jsonResult){
	
	$.mobile.loadPage('#courseSelectionScreen');
	
	
	if (listCreated === true) {
		$.mobile.changePage("#courseSelectionScreen", true, false);
		return;
	} else {
		
		for (var i = jsonResult.length-1;i >= 0; i--){
			
			listHtml += '<li data-icon = "false" style = "text-align:center ; margin-top:5%; margin-right:15%; margin-left:15%;"><a href="javascript:void(0)" onclick = "openCourse('+jsonResult[i].courseName +');"  style=";padding-top: 0px;padding-bottom: 0px;padding-right: 42px;padding-left: 0px;" data-corners = "false"><label style="text-align:center; font-size:20px; padding:10px 0px 0px 10px;"><h3 style = "font-size: 20px;">'+jsonResult[i].courseName +'</h3><h3 style = "font-size: 20px;">'+ jsonResult[i].courseNumber+'</h3></label></label></a></li>';
			
			
		}
		
		list.append(listHtml).listview("refresh");
		$('#courseSelectionScreen').trigger('create');
	
	}
	
	$.mobile.changePage("#courseSelectionScreen", true, false);

}

function openCourse(number){
	
	if (number != courseNumberChoosed){
		courseNumberChoosed = number;
		listCreated = false;
	}
	
	jsBridge.handleJs("getClientsList",number);
}

function courseDenied(){
	document.getElementById("coursedialogHeaderText").innerHTML = jsonContent.courseDenied[1];
	document.getElementById("coursedialogContentText").innerHTML = jsonContent.courseDenied[0];
	
	$("#courseDeniedDialog").popup();
	$("#courseDeniedDialog").popup('open');

	// enable closing the popup when user clicks on 'confirm' button
	$(document).on("vclick",'#confirmCourseDeniedButton', function (ev) {
		$( "#courseDeniedDialog" ).popup( "close" );
});
}