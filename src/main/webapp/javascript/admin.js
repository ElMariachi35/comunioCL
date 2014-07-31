function loadMatchday() {
	var selectedMatchday = $('#numberOfTeams').val();

	$.ajax({
		type : "POST",
		url : "rest/admin/findPoints/" + COMUNIO.comunioId+"/"+selectedMatchday,
		success : function(data) {
			var results =$.parseJSON(data);
			console.log(results);
		},
		error : function(jqXHR) {
			// if (jqXHR.status == 404) {
			// var errorMessage = "";
			// if (searchParam == "Id") {
			// errorMessage = "Die Com-CL mit der Id '" + comId
			// + "' konnte leider nicht gefunden werden.";
			// } else {
			// errorMessage = "Die Com-CL mit dem Namen '" + comName
			// + "' konnte leider nicht gefunden werden.";
			// }
			// $('#errorMessage').html(errorMessage);
			// }
		}
	});
}

function saveMatchday() {
	$(".save-button").val("");
	$(".small-loader").show();
	var results = new Array();
	var matchday = $("#numberOfTeams").val();
	for (var i = 0; i < teams.length; i++) {
		var team = teams[i];
		var points = $("#" + team.teamId).val();
		var result = {
			teamId : team.teamId,
			matchdayNumber : matchday,
			points : points
		};
		results.push(result);
	}
	var jsonResult = JSON.stringify(results);

	$.ajax({
		type : "POST",
		url : "rest/admin/save"+"/"+COMUNIO.comunioId,
		dataType : "json",
		data : jsonResult,
		success : function(data) {
			$(".save-button").val("Speichern");
			$(".small-loader").hide();
			$('#message').html(data);
		},
		error : function(jqXHR) {
			// if (jqXHR.status == 404) {
			// var errorMessage = "";
			// if (searchParam == "Id") {
			// errorMessage = "Die Com-CL mit der Id '" + comId
			// + "' konnte leider nicht gefunden werden.";
			// } else {
			// errorMessage = "Die Com-CL mit dem Namen '" + comName
			// + "' konnte leider nicht gefunden werden.";
			// }
			// $('#errorMessage').html(errorMessage);
			// }
		}
	});
}