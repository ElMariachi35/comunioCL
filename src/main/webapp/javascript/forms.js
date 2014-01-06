function generateAddTeamsForm(numberOfTeams) {

	for (var i = 0; i < numberOfTeams; i++) {
		$('#addTeamsForm').append(
				'<input type="text" id="team' + i + '" /><br />');
	}
	$('#addTeamsForm')
			.append(
					'<input type="button" onclick="submitTeamsString()" value="Continue"/><br />');

}

function submitTeamsString() {
	var teams="";
	for(var i=0;i<numberOfTeams;i++){
		teams=teams+$('#team'+i).val()+";";
	}
	$('#addTeamsForm').append('<input type="hidden" name="teams" value="'+teams+'" />');
	$('#addTeamsForm').submit();
	
}