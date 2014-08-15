$(document).ready(function() {
	initializeGroupHeader();
	bindOverview();
	showInfoMessage();
});

function bindOverview() {
	COMUNIO.groups = sortGroups(COMUNIO.groups);
	var comunio = ko.observable(COMUNIO);
	ko.applyBindings(comunio, $('#comunio')[0]);
}

function sortGroups(groups) {

	function compare(a, b) {
		if (a.groupName< b.groupName)
			return -1;
		if (a.groupName > b.groupName)
			return 1;
		return 0;
	}
	groups.sort(compare);
	return groups;
}

function initializeGroupHeader() {
	var groups = COMUNIO.groups;
	for (var i = 0; i < groups.length; i++) {
		$('#header' + groups[i].groupName).removeClass("hidden");
	}
	$('#playoff').removeClass("hidden");
}

function presentPos(index) {
	return (index + 1) + ".";
}

function presentMatchdayHeader(matchdayNumber) {
	return "Spieltag " + matchdayNumber;
}

function closeInfoMessage() {
	$('.info-message').hide();
}

function showInfoMessage() {
	if (infoMessage) {
		$('.info-message').show();
	}
}