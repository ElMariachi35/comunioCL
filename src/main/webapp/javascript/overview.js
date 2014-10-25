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
		if (a.groupName < b.groupName)
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

function isTeamInPlayoffs(position, team) {
	if (team.gamesPlayed === 0) {
		return false;
	}
	var numberOfTeams = determineNumberOfTeams();
	if (numberOfTeams <= 7 || numberOfTeams == 11) {
		switch (position) {
		case 0:
		case 1:
		case 2:
		case 3:
			return true;
		}
		return false;
	}

	if (numberOfTeams >= 8) {
		if (position === 0 || position === 1) {
			return true;
		}
	}

	if (numberOfTeams >= 12 && numberOfTeams <= 15) {
		if (position === 2) {
			return determineIfBestThirdPlace(team);
		}
	}
	return false;
}

function determineNumberOfTeams() {
	var numberOfTeams = 0;
	for (var i = 0; i < COMUNIO.groups.length; i++) {
		numberOfTeams = numberOfTeams + COMUNIO.groups[i].teams.length;
	}
	return numberOfTeams;
}

function determineIfBestThirdPlace(team) {
	var groupThirds = determineThirdsOfGroups();
	groupThirds.sort(function(team1, team2) {
		return determinePointsPerGame(team2) - determinePointsPerGame(team1);
	});
	if(groupThirds[0].teamId===team.teamId || groupThirds[1].teamId===team.teamId){
		return true;
	}
	return false;
}

function determinePointsPerGame(team) {
	if (team.gamesPlayed == 0) {
		return 0;
	}
	return team.points / team.gamesPlayed;
}

function determineThirdsOfGroups() {
	var groupThirds = new Array();
	for (var i = 0; i < COMUNIO.groups.length; i++) {
		groupThirds.push(COMUNIO.groups[i].sortedTeams[2]);
	}
	return groupThirds;
}