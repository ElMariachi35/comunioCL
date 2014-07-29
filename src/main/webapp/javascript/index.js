function sendOverviewRequest() {
	var comId = $('#inputComId').val();
	var comName = $('#inputComName').val();
	if (comName == "" && comId == "") {
		return;
	}
	if (comName == "") {
		var url = 'show/' + comId + '/A';
		window.location.href = url;
	}

	$.ajax({
		type : "POST",
		url : "rest/findByName/"+comName,
		success : function(data){
			var url = 'show/' + data + '/A';
			window.location.href = url;
		}
	});

	
}