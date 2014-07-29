
function sendOverviewRequest() {
	var comunioId = $('#inputComunioId').val();
	var url = 'show/' + comunioId + '/A';
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
		$('#row' + i).append("<div class='teamHeader'>" + teams[i].teamName + "</div>");
		for (var k = 1; k <= matchdays; k++) {
			var points = getPointsForMatchday(teams[i],k);
//			if(!points){
//				points = Math.floor(Math.random() * 33) + 15;
//			}
			$('#row' + i).append(
					'<input type="text" value="'+points+'" class="pointInput matchday' + k
							+ ' team' + i + '">');
		}
	}
	$('#admin').append("<input type='button' value='Save' onclick='saveInputTable()'></div>");
}

function getPointsForMatchday (team, matchday){
	var points="";
	for(var i=0;i<team.result.length;i++){
		if(team.result[i].matchday==matchday){
			points=team.result[i].points;
		}
	}
	return points;
}

function saveInputTable(){
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
