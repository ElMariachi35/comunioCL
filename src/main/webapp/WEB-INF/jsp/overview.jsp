<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	var comunio = $.parseJSON('${comunioJSON}');
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<%@ include file="/WEB-INF/jsp/menu.jsp"%>

	<div>Gruppe ${group.groupName}</div>

	<div id="groupHeader">
		<div class="groupColumnPadding position">Pos</div>
		<div class="groupColumnPadding team">Team</div>
		<div class="groupColumnPadding numeric">G</div>
		<div class="groupColumnPadding numeric">W</div>
		<div class="groupColumnPadding numeric">D</div>
		<div class="groupColumnPadding numeric">L</div>
		<div class="groupColumnPadding numeric">GF</div>
		<div class="groupColumnPadding numeric">GA</div>
		<div class="groupColumnPadding difference">Diff</div>
		<div class="groupColumnPadding numeric">Pts</div>
	</div>
	<div id="groupBody" class="clear">
		<c:forEach var="team" items="${teams}" varStatus="counter">
		<div class="groupColumnPadding position">${counter.index+1}.</div>
		<div class="groupColumnPadding team">${team.teamName}</div>
		<div class="groupColumnPadding numeric">${team.gamesPlayed}</div>
		<div class="groupColumnPadding numeric">${team.gamesWon}</div>
		<div class="groupColumnPadding numeric">${team.gamesDrawn}</div>
		<div class="groupColumnPadding numeric">${team.gamesLost}</div>
		<div class="groupColumnPadding numeric">${team.goalsFor}</div>
		<div class="groupColumnPadding numeric">${team.goalsAgainst}</div>
		<div class="groupColumnPadding difference">${team.goalDifference}</div>
		<div id="points" class="groupColumnPadding numeric">${team.points}</div>
		</c:forEach>
	</div>
	<br />
	<br />
	<br />
	<div id="fixture" class="clear">
		<br />
		<c:forEach var="matchday" items="${matchdays}">
			<h2>Matchday ${matchday.comunioMatchdayNumber }</h2>
			<div class="match clear">
			<c:forEach var="match" items="${matchday.matches}">
				<div>${match.homeTeam.teamName }</div>
				<div> - </div>
				<div>${match.awayTeam.teamName }</div>
				<div></div>
				<div>${match.homeGoals }</div>
				<div>:</div>
				<div>${match.awayGoals }</div>
				<div></div><br />
			</c:forEach>
			<div class="clear">Bye week: ${matchday.byeTeam.teamName}</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>