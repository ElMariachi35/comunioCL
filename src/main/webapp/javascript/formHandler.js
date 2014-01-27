function generateTeamFields() {
	var numberOfTeams = $('#numberOfTeams').val();
	$('#addTeams').append('<span>Input teams:</span><br />');
	for (var i = 0; i < numberOfTeams; i++) {
		$('#addTeams').append(
				'<input type="text" id="team' + i + '" value="team'+(i+1)+'" /><br />');
	}
	$('#addTeams')
			.append(
					'<input type="button" onclick="submitAddComunioForm()" value="Continue"/><br />');

	$('#createTeamFieldButton').remove();
}

function submitAddComunioForm(){
	var teams="";
	var numberOfTeams = $('#numberOfTeams').val();
	for(var i=0;i<numberOfTeams;i++){
		teams=teams+$('#team'+i).val()+";";
	}
	$('#addComunioForm').append('<input type="hidden" name="teams" value="'+teams+'" />');
	$('#addComunioForm').submit();
	
}

function sendOverviewRequest(){
	var comunioId = $('#inputComunioId').val();
	var url='showComunio/'+comunioId+'/A';
	window.location.href = url;
}