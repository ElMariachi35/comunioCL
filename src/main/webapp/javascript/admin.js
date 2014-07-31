function loadMatchday() {
	$('.load-loader').show();
	var selectedMatchday = $('#numberOfTeams').val();
	$.ajax({
		type : "POST",
		url : "rest/admin/findPoints/" + COMUNIO.comunioId + "/"
				+ selectedMatchday,
		success : function(data) {
			var results = $.parseJSON(data);
			if (results.length == 0) {
				for (var i = 0; i < teams.length; i++) {
					var team = teams[i];
					$("#" + team.teamId).val("");
				}
			}
			for (var i = 0; i < results.length; i++) {
				var result = results[i];
				$("#" + result.team.teamId).val(result.points);
			}
			$('.load-loader').hide();
		}
	});
}

function saveMatchday() {
	$('#message').html('');
	var results = new Array();
	var matchday = $("#numberOfTeams").val();
	for (var i = 0; i < teams.length; i++) {
		var team = teams[i];
		var points = $("#" + team.teamId).val();
		if (isNaN(points)) {
			$('#message')
					.html(
							'<span class="error-text">Es sind nur Zahlen als Eingabe erlaubt!</span>');
			return;
		}
		if (points == "") {
			points = 0;
		}
		var result = {
			teamId : team.teamId,
			matchdayNumber : matchday,
			points : points
		};
		results.push(result);
	}
	$(".save-button").val("");
	$(".save-loader").show();

	var jsonResult = JSON.stringify(results);

	$.ajax({
		type : "POST",
		url : "rest/admin/save" + "/" + COMUNIO.comunioId,
		dataType : "json",
		data : jsonResult,
		success : function(data) {
			$(".save-button").val("Speichern");
			$(".save-loader").hide();
			$('#message').html(data);
		}
	});
}