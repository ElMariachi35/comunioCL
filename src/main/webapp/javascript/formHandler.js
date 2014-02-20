function generateTeamFields() {
	var numberOfTeams = $('#numberOfTeams').val();
	$('#addTeams').append('<span>Input teams:</span><br />');
	for (var i = 0; i < numberOfTeams; i++) {
		$('#addTeams').append(
				'<input type="text" id="team' + i + '" value="team' + (i + 1)
						+ '" /><br />');
	}
	$('#addTeams')
			.append(
					'<input type="button" onclick="submitAddComunioForm()" value="Continue"/><br />');

	$('#createTeamFieldButton').remove();
}

function submitAddComunioForm() {
	var teams = "";
	var numberOfTeams = $('#numberOfTeams').val();
	for (var i = 0; i < numberOfTeams; i++) {
		teams = teams + $('#team' + i).val() + ";";
	}
	$('#addComunioForm').append(
			'<input type="hidden" name="teams" value="' + teams + '" />');
	$('#addComunioForm').submit();

}

function sendOverviewRequest() {
	var comunioId = $('#inputComunioId').val();
	var url = 'showComunio/' + comunioId + '/A';
	window.location.href = url;
}

function createPointInputTable(matchdays, teams) {
	$('#admin').append("<div id='rowHeader'></div>");
	$('#rowHeader').append("<div class='teamHeader'>Teams</div>");
	for (var j = 1; j <= matchdays; j++) {
		$('#rowHeader').append(
				"<input disabled='true' class='pointInput rowHeader' value='"
						+ j + "' />");
	}
	$('#admin').append("<div id='rowBody'></div>");
	for (var i = 0; i < teams.length; i++) {
		$('#rowBody').append("<div id='row" + i + "'></div>");
		$('#row' + i).append("<div class='teamHeader'>" + teams[i] + "</div>");
		for (var k = 1; k <= matchdays; k++) {
			$('#row' + i).append(
					'<input type="text" class="pointInput matchday' + k
							+ ' team' + i + '">');
		}
	}
	$('#admin').append("<input type='button' value='Save' onclick='saveInputTable()'></div>");
}

function saveInputTable(){
	var comunioId = $('#inputComunioId').val();
	var inputTable = [];
	var numberOfTeams = $.find('.teamHeader').length-1;
	var teamResult = "";
	for(var i=0; i<numberOfTeams; i++){
		teamResult=$('#row'+i).find('.teamHeader').html()+";";
		var inputFields = $.find('.team'+i);
		for(var j=0;j<inputFields.length;j++){
			var value = $(inputFields[j]).val();
			if(value !=""){
				teamResult+=value+";";
			}else{
				teamResult+="-;";
			}
		}
		inputTable.push(teamResult);
	}
	$.ajax({
        url: '/updateComunio/'+comunioId,
        type: 'post',
        dataType: 'json',
        success: function (data) {
        	alert(data);
        },
        data: JSON.stringify(inputTable)
    });
}
