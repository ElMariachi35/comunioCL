function sendOverviewRequest() {
	$('#errorMessage').html("&nbsp;");
	var comId = $('#inputComId').val();
	var comName = $('#inputComName').val();
	if (comName == "" && comId == "") {
		$('#errorMessage').html(
				"Bitte geben Sie entweder Com-Id oder Com-Name ein.");
		return;
	}
	var searchParam = "Id";
	var url = "rest/findBy/id/" + comId;
	if (comId == "") {
		url = "rest/findBy/name/" + comName;
		searchParam = "Name";
	}
	$.ajax({
		type : "POST",
		url : url,
		success : function(data) {
			url = 'show/' + data;
			$('.load-animation-big').show();
			window.location.href = url;
		},
		error : function(jqXHR) {
			if (jqXHR.status == 404) {
				var errorMessage = "";
				if (searchParam == "Id") {
					errorMessage = "Die Com-CL mit der Id '" + comId
							+ "' konnte leider nicht gefunden werden.";
				} else {
					errorMessage = "Die Com-CL mit dem Namen '" + comName
							+ "' konnte leider nicht gefunden werden.";
				}
				$('#errorMessage').html(errorMessage);
			}
		}
	});

}