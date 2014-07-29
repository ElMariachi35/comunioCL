function sendOverviewRequest() {
	var comunioId = $('#inputComId').val();
	var url = 'show/' + comunioId + '/A';
	window.location.href = url;
}