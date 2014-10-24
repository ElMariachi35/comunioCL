function loadMatchday() {
	$('#message').html('');
	$('.message-container').hide();
	$('.load-loader').show();
	var selectedMatchday = $('#numberOfMatchday').val();
	$('#numberOfMatchday').prop('disabled', 'disabled');
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
			checkPlayoffInitialization(selectedMatchday);
			$('.load-loader').hide();
			$('#numberOfMatchday').prop('disabled', false);
		}
	});
}

function checkPlayoffInitialization(matchday) {
	if (matchday == 10) {
		$('.admin-message-container').show();
		return;
	}
	$('.admin-message-container').hide();
}

function loadNextMatchday() {
	$.ajax({
		type : "POST",
		url : "rest/admin/findNextMatchday/" + COMUNIO.comunioId,
		success : function(data) {
			var nextMatchday = $.parseJSON(data);
			$('#numberOfMatchday').val(nextMatchday);
			loadMatchday();
		}
	});
}

function saveMatchday() {
	$('#message').html('');
	$('.message-container').hide();
	if (!isPasswordCorrect()) {
		$('#message')
				.html(
						'<span class="error-text">Das eingegebene Passwort ist falsch!</span>');
		$('.message-container').width($('.admin-container').width());
		$('.message-container').show();
		return;
	}
	var results = new Array();
	var matchday = $("#numberOfMatchday").val();
	for (var i = 0; i < teams.length; i++) {
		var team = teams[i];
		var points = $("#" + team.teamId).val();
		if (isNaN(points)) {
			$('#message')
					.html(
							'<span class="error-text">Es sind nur Zahlen als Eingabe erlaubt!</span>');
			$('.message-container').width($('.admin-container').width());
			$('.message-container').show();
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
	$(".save-button").prop("disabled", true);
	$(".save-loader").show();

	var jsonResult = JSON.stringify(results);

	$
			.ajax({
				type : "POST",
				url : "rest/admin/save" + "/" + COMUNIO.comunioId,
				dataType : "json",
				data : jsonResult,
				success : function(data) {
					$(".save-button").prop("disabled", false);
					$(".save-loader").hide();
					$('#message')
							.html(
									data
											+ "<br /><span class='back-to-overview' onclick='toOverview()'>Zur Übersicht</span>");
					$('.message-container')
							.width($('.admin-container').width());
					$('.message-container').show();
				},
				error : function(data, status, error) {
					$(".save-button").prop("disabled", false);
					$(".save-loader").hide();
					$('#message').html(
							"<span class='error-text'>" + data.responseText
									+ "</span>");
					$('.message-container')
							.width($('.admin-container').width());
					$('.message-container').show();
				}
			});
}

function toOverview() {
	window.location.replace("/show/" + COMUNIO.comunioId);
}

function isPasswordCorrect() {
	var password = $('#adminPassword').val();
	var isCorrect = false;
	$.ajax({
		type : "POST",
		url : "rest/admin/password" + "/" + COMUNIO.comunioId + "/" + password,
		success : function(data) {
			isCorrect = true;
		},
		async : false
	});
	return isCorrect;
}