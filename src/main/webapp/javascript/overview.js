$(document).ready(function() {
	initializeGroupHeader();
	bindOverview();
});

function bindOverview() {
	var comunio = ko.observable(COMUNIO);
	ko.applyBindings(comunio, $('#comunio')[0]);
}

function initializeGroupHeader() {
	var groups = COMUNIO.groups;
	for(var i=0;i<groups.length;i++){
		$('#header'+groups[i].groupName).removeClass("hidden");
	}
}

function presentPos(index){
	return (index+1)+".";
}