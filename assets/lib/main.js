﻿var thermometer;
window.onload = function() {
	thermometer = new RGraph.Thermometer('cvs', -30, 50, 20);
	thermometer.Draw();

}

$(document).on ('pageinit','#login', function(event) {
	
console.log("page init");

$('.settingsButton').children().children().children().css('text-overflow',"ellipsis");
$('.settingsButton').children().children().children().css('white-space',"normal");

$('.settingsButton').children().children().children().css('overflow',"visible");


$(document).on ("click",'#submitDriverIdInput', function (ev) {
	driverInputId = $('#driverIdInput').val();
 	if (driverInputId != ""){
 		
		console.log("input in if = " + driverInputId);
	
		$('#driverIdInput').val("");
		jsBridge.handleJs("verifyDriverId", driverInputId);
		
	}
 	
    ev.preventDefault();
});

// add onClick event to distribute Button 

$('#distributeButton').bind("click", function (ev) {

//	iclicked();

	showLoadingMessage("searchPaths");
	
	jsBridge.handleJs("getCoursesList", null);
	
//		jsBridge.handleJs("getClientsList", null);
		
	
	ev.preventDefault();
});


// add onClick event to administrativeDriveButton button
$(document).on("click",'#administrativeDriveButton', function (ev) {


	$.mobile.changePage("#administrativeDriveScreen", true, false);
		
    ev.preventDefault();
});


$(document).on("click",'#endAdmindrive', function (ev) {


	$.mobile.changePage("#driveStatusSelectionScreen", true, false);
		
    ev.preventDefault();
});




});

$(document).on("click",'#changeTemp', function (ev) {
 		console.log("button clicked");
		jsBridge.handleJs("changeTemp", null);
    ev.preventDefault();
});

$(document).on ('pageinit','#driveStatusSelectionScreen', function(event) {
	
	console.log("page init driveStatusSelectionScreen ");

//	$('.settingsButton').children().children().children().css('text-overflow',"ellipsis");
//	$('.settingsButton').children().children().children().css('overflow',"visible");
	$('.settingsButton').children().children().children().css('white-space',"normal");

});


//Listen for orientation changes
//window.addEventListener("orientationchange", function() {
//	console.log ("orientation changed" );
//	var buttons = $('.settingsButton').width();
//	console.log ("orientation changed " +buttons );
//	var fontSize = buttons / 10;
//	
//}, false);


function showLoadingMessage(message){
	
	switch (message) {
	case "searchPaths":
		
		 $.mobile.showPageLoadingMsg('f','המערכת מאתרת מסלול הפצה');
//		setTimeout( function() { $.mobile.hidePageLoadingMsg(); }, 2000 );
		break;
	}
}


var jsonContent;
$.getJSON("strings.json", function(json){
	   jsonContent = json;
	 });

function handleDriverAuthentication(message) {
	
	if (message === "true") {
		driverId.html(driverInputId);
		$.mobile.changePage("#driveStatusSelectionScreen", true, false);
		
	} else {
		
		document.getElementById("dialogHeaderText").innerHTML = jsonContent.usedDeniedPopup[1];
		document.getElementById("dialogContentText").innerHTML = jsonContent.usedDeniedPopup[0];
		
		$("#userDeniedDialog").popup();
		$("#userDeniedDialog").popup('open');

		// enable closing the popup when user clicks on 'confirm' button
		$(document).on("vclick",'#confirmUserIdDeniedButton', function (ev) {
			$( "#userDeniedDialog" ).popup( "close" );
	});
	}

}

function tempChanged(value){

	thermometer.value = parseInt(value); 
	if (thermometer.value < -30) {
		thermometer.value = -30;
	} else {
		if (thermometer.value > 50) {
			thermometer.value = 50;
		}
	}

	if (thermometer.value < -10 || thermometer.value > 20) {
		thermometer.Set('chart.colors', ['rgba(255,0,0,1)']);
	}
	else {
		thermometer.Set('chart.colors', ['rgba(124,252,0,1)']);
	}

	thermometer.Draw();
}

$(document).bind('pageinit', function() {
    $( "#testlist" ).sortable();
    $( "#testlist" ).disableSelection();
    <!-- Refresh list to the end of sort to have a correct display -->
    $( "#testlist" ).bind( "sortstop", function(event, ui) {
      $('#testlist').listview('refresh');
    });
  });