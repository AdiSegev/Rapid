/**
 * This file used to generate clients list
 */

// list element on clientsListScreen
var list;

// when set to true, this flag indicates that the list was generated, in order
// to prevet recreate when navigatin in/out page.
var listCreated = false;

// seconds before popup is closing
var seconds;

$(document).on('pageinit', '#clientsListScreen', function(event) {
	$('#testlist').listview();

	list = $('#testlist');
	console.log("clients");

});

// this function creates the list of clients.
// it gets an array with clients objects.
// each object has 2 fields:name for the clients name, and hasLocation indicates
// weather the client has known location.
// if the client dosent have known location, a pin icon is added to it's row in
// the list.
function generateClientsList(clientsArr) {

	$.mobile.loadPage('#clientsListScreen');

	if (listCreated === true) {
		$.mobile.changePage("#clientsListScreen", true, false);
		return;
	} else {

		console.log("list created");
		listCreated = true;

		var name, hasLocation;
		var listHtml = "";

		list.empty();

		console.log("array len = " + clientsArr.length);
		for ( var i = clientsArr.length - 1; i >= 0; i--) {

			// when client has no location, add 'mappin' icon to list item
			if (clientsArr[i].hasLocation === false) {
				listHtml += '<li data-icon = "mappin"><a href="#" style="padding-top: 0px;padding-bottom: 0px;padding-right: 42px;padding-left: 0px;" data-corners = "false"><label style="border-top-width: 0px;margin-top: 0px;border-bottom-width: 0px;margin-bottom: 0px;border-left-width: 0px;border-right-width: 0px;" data-corners="false"><fieldset data-role="controlgroup"><input id="SelectedSensors_0__Value" name="SelectedSensors[0].Value" type="checkbox" value="true"  onclick=\'handleNoLocationClick(this);\'/><input id="SelectedSensors_0__Id" name="SelectedSensors[0].Id" type="hidden" value="16" /><label for="SelectedSensors_0__Value" style="border-top-width: 0px;margin-top: 0px;border-bottom-width: 0px;margin-bottom: 0px;border-left-width: 0px;border-right-width: 0px;"><label style="text-align:right; font-size:20px; padding:10px 0px 0px 10px;"><h3 style = "font-size: 20px;">'
						+ clientsArr[i].name
						+ '</h3></label></label></fieldset></label></a></li>';

				// enable closing the popup when user clicks on 'confirm' button
				$(document).on("vclick", '#confirmClientComplete', function(ev) {
					$("#clientCompleteDialog").popup("close");
				});
			} else {
				listHtml += '<li data-icon = "false"><a href="#" style="padding-top: 0px;padding-bottom: 0px;padding-right: 42px;padding-left: 0px;" data-corners = "false"><label style="border-top-width: 0px;margin-top: 0px;border-bottom-width: 0px;margin-bottom: 0px;border-left-width: 0px;border-right-width: 0px;" data-corners="false"><fieldset data-role="controlgroup"><input id="SelectedSensors_0__Value" name="SelectedSensors[0].Value" type="checkbox" value="true"  onclick=\'handleClick(this);\'/><input id="SelectedSensors_0__Id" name="SelectedSensors[0].Id" type="hidden" value="16" /><label for="SelectedSensors_0__Value" style="border-top-width: 0px;margin-top: 0px;border-bottom-width: 0px;margin-bottom: 0px;border-left-width: 0px;border-right-width: 0px;"><label style="text-align:right; font-size:20px; padding:10px 0px 0px 10px;"><h3 style = "font-size: 20px;">'
						+ clientsArr[i].name
						+ '</h3></label></label></fieldset></label></a></li>';
			}

			// enable closing the popup when user clicks on 'confirm' button
			$(document).on("vclick", '#confirm', function(ev) {
				$("#updateClientDialog").popup("close");
			});

		}
		list.append(listHtml).listview("refresh");
		$('#clientsListScreen').trigger('create');

	}
	$.mobile.changePage("#clientsListScreen", true, false);

}

// this function updates the seconds in popup window. when seconds = 0, it close
// the popup.
function updateCounter(message) {
	var count;
	if (message === 'hasLocation') {
		count = document.getElementById("clientCompleteCounter");
	} else {
		count = document.getElementById("counter");
	}
	count.innerHTML = --seconds;

	if (seconds === 0) {
		$("#updateClientDialog").popup("close");
		$("#clientCompleteDialog").popup("close");
	}
}
// handle clickes from list item with location
function handleClick(SelectedSensors_1__Value) {
	SelectedSensors_1__Value.setAttribute('disabled', true);
	// console.log("Click, new value = " + SelectedSensors_1__Value.disabled);
	$("#clientCompleteDialog").popup();
	$("#clientCompleteDialog").popup('open');

	seconds = 6;

	// start count down, generates 6 calls to updateCounter with interval of 1
	// second between them
	for ( var i = 0; i < 6; i++) {
		setTimeout(updateCounter, 1000 * i, 'hasLocation');
	}
}

// handle clickes from list item without location
// this function opens the popup and start the countdown
function handleNoLocationClick(SelectedSensors_1__Value) {

	SelectedSensors_1__Value.setAttribute('disabled', true);
	// console.log("Click, new value = " + SelectedSensors_1__Value.disabled);

	$("#updateClientDialog").popup();
	$("#updateClientDialog").popup('open');

	seconds = 6;

	// start count down, generates 6 calls to updateCounter with interval of 1
	// second between them
	for ( var i = 0; i < 6; i++) {
		setTimeout(updateCounter, 1000 * i);
	}
}
