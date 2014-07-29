function generateTeamFields() {
	$('#teamNamesTable').empty();
	var numberOfTeams = $('#numberOfTeams').val();
	$('#teamNamesTable')
			.append(
					'<tr><td class="labelColumn">Eingabe der Teamnamen:</td><td><input type="text" id="team0"/></td></tr>');
	for (var i = 1; i < numberOfTeams; i++) {
		$('#teamNamesTable').append(
				'<tr><td></td><td><input type="text" id="team' + i
						+ '"/></td></tr>');
	}
	$('#teamNamesTable')
			.append(
					'<tr><td></td><td><input type="button" class="registerButton" onclick="submitRegisterComForm()" value="Erstellen"/></td></tr>');
}

function submitRegisterComForm() {
	if (!validateRegisterComForm()) {
		return;
	}
	var teams = "";
	var numberOfTeams = $('#numberOfTeams').val();
	for (var i = 0; i < numberOfTeams; i++) {
		teams = teams + $('#team' + i).val() + ";";
	}
	$('#registerForm').append(
			'<input type="hidden" name="teams" value="' + teams + '" />');
	$('#registerForm').submit();

}

function validateRegisterComForm() {
	var isValid = "true";
	$('#registerValidationErrors').empty();
	// validate unique name
	var comName = $('#comName').val();
	if (comName == "" || comName == null) {
		$('#registerValidationErrors').append(
				'<tr><td>Feld "Com CL Name" darf nicht leer sein.</td><tr>');
		isValid = false;
	}
	var password = $('#registerPassword').val();
	var passwordRepeated = $('#registerPasswordRepeated').val();
	if (password == "" || password == null) {
		$('#registerValidationErrors').append(
				'<tr><td>Feld "Passwort" darf nicht leer sein.</td><tr>');
		isValid = false;
	}
	if (passwordRepeated == "" || passwordRepeated == null) {
		$('#registerValidationErrors')
				.append(
						'<tr><td>Feld "Passwort wiederholen" darf nicht leer sein.</td><tr>');
		isValid = false;
	}
	if (password != passwordRepeated) {
		$('#registerValidationErrors').append(
				'<tr><td>Passwörter stimmen nicht überein.</td><tr>');
		isValid = false;
	}
	var numberOfTeams = $('#numberOfTeams').val();
	var teamNames = new Array();
	for (var i = 0; i < numberOfTeams; i++) {
		teamNames.push($('#team' + i).val());
	}
	isValid = validateTeamNames(teamNames);
	return isValid;
}

function validateTeamNames(teamNames) {
	var isValid = true;
	for (var i = 0; i < teamNames.length; i++) {
		var teamName = teamNames[i];
		if (teamName == "" || teamName == null) {
			$('#registerValidationErrors').append(
					'<tr><td>Es wurden nicht alle Teamnamen ausgefüllt.</td><tr>');
			isValid = false;
			return;
		}
	}
	return isValid;
}