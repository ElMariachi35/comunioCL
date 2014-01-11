function generateTeamFields() {
	var numberOfTeams = $('#numberOfTeams').val();
	$('#addTeams').append('<span>Input teams:</span><br />');
	for (var i = 0; i < numberOfTeams; i++) {
		$('#addTeams').append(
				'<input type="text" id="team' + i + '" /><br />');
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