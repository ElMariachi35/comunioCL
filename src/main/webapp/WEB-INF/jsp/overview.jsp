<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Com Champions League</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/overview.css" />
<script>
	var COMUNIO = $.parseJSON('${comunioJSON}');
	console.log(COMUNIO);
	var infoMessage = $.parseJSON('${showInfoMessage}');
</script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<table class="overview-name-id">
			<tr>
				<td class="label">Com CL Name:</td>
				<td class="com" data-bind="text: name"></td>
			</tr>
			<tr>
				<td class="label">Com Id:</td>
				<td class="id" data-bind="text: comunioId"></td>
			</tr>
		</table>
		<ul class="idTabs">
			<li id="headerA" class="hidden"><a href="#groupA">Gruppe A</a></li>
			<li id="headerB" class="hidden"><a href="#groupB">Gruppe B</a></li>
			<li id="headerC" class="hidden"><a href="#groupC">Gruppe C</a></li>
			<li id="headerD" class="hidden"><a href="#groupD">Gruppe D</a></li>
			<li id="headerPlayoff"><a href="#playoff">KO-Phase</a></li>
		</ul>

		<div id="groupA">
			<div class="tableContainer">
				<div class="header">Tabelle</div>
				<div class="header-separator"></div>
				<table class="table">
					<thead>
						<td class="position">Pos</td>
						<td class="teamName">Name</td>
						<td class="gamesPlayed">Sp</td>
						<td class="gamesWon">S</td>
						<td class="gamesDrawn">U</td>
						<td class="gamesLost">N</td>
						<td class="goalsFor">T</td>
						<td class="goalsAgainst">GT</td>
						<td class="goalDifference">+/-</td>
						<td class="points">P</td>
					</thead>
					<tbody data-bind="foreach: groups[0].sortedTeams">
						<tr>
							<td class="position" data-bind="text: presentPos($index())"></td>
							<td class="teamName" data-bind="text: teamName"></td>
							<td class="gamesPlayed" data-bind="text: gamesPlayed"></td>
							<td class="gamesWon" data-bind="text: gamesWon"></td>
							<td class="gamesDrawn" data-bind="text: gamesDrawn"></td>
							<td class="gamesLost" data-bind="text: gamesLost"></td>
							<td class="goalsFor" data-bind="text: goalsFor"></td>
							<td class="goalsAgainst" data-bind="text: goalsAgainst"></td>
							<td class="goalDifference" data-bind="text: goalDifference"></td>
							<td class="points" data-bind="text: points"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="scheduleContainer">
				<div class="header">Spielplan</div>
				<div class="header-separator"></div>
				<div data-bind="foreach: groups[0].fixture.matchdays">
					<table class="matchday">
						<thead>
							<tr>
								<td
									data-bind="text: presentMatchdayHeader(comunioMatchdayNumber)"></td>
							</tr>
						</thead>
						<tbody data-bind="foreach: matches">
							<tr>
								<td class="home-team" data-bind="text: homeTeam.teamName"></td>
								<td class="home-goals" data-bind="text: homeGoals"></td>
								<td>:</td>
								<td class="away-goals" data-bind="text: awayGoals"></td>
								<td class="away-team" data-bind="text: awayTeam.teamName"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="groupB">
			<div class="tableContainer">
				<div class="header">Tabelle</div>
				<div class="header-separator"></div>
				<table class="table">
					<thead>
						<td class="position">Pos</td>
						<td class="teamName">Name</td>
						<td class="gamesPlayed">Sp</td>
						<td class="gamesWon">S</td>
						<td class="gamesDrawn">U</td>
						<td class="gamesLost">N</td>
						<td class="goalsFor">T</td>
						<td class="goalsAgainst">GT</td>
						<td class="goalDifference">+/-</td>
						<td class="points">P</td>
					</thead>
					<tbody
						data-bind="foreach: groups[1]==undefined ? new Array() : groups[1].sortedTeams">
						<tr>
							<td class="position" data-bind="text: presentPos($index())"></td>
							<td class="teamName" data-bind="text: teamName"></td>
							<td class="gamesPlayed" data-bind="text: gamesPlayed"></td>
							<td class="gamesWon" data-bind="text: gamesWon"></td>
							<td class="gamesDrawn" data-bind="text: gamesDrawn"></td>
							<td class="gamesLost" data-bind="text: gamesLost"></td>
							<td class="goalsFor" data-bind="text: goalsFor"></td>
							<td class="goalsAgainst" data-bind="text: goalsAgainst"></td>
							<td class="goalDifference" data-bind="text: goalDifference"></td>
							<td class="points" data-bind="text: points"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="scheduleContainer">
				<div class="header">Spielplan</div>
				<div class="header-separator"></div>
				<div
					data-bind="foreach: groups[1]==undefined ? new Array() : groups[1].fixture.matchdays">
					<table class="matchday">
						<thead>
							<tr>
								<td
									data-bind="text: presentMatchdayHeader(comunioMatchdayNumber)"></td>
							</tr>
						</thead>
						<tbody data-bind="foreach: matches">
							<tr>
								<td class="home-team" data-bind="text: homeTeam.teamName"></td>
								<td class="home-goals" data-bind="text: homeGoals"></td>
								<td>:</td>
								<td class="away-goals" data-bind="text: awayGoals"></td>
								<td class="away-team" data-bind="text: awayTeam.teamName"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="groupC">
			<div class="tableContainer">
				<div class="header">Tabelle</div>
				<div class="header-separator"></div>
				<table class="table">
					<thead>
						<td class="position">Pos</td>
						<td class="teamName">Name</td>
						<td class="gamesPlayed">Sp</td>
						<td class="gamesWon">S</td>
						<td class="gamesDrawn">U</td>
						<td class="gamesLost">N</td>
						<td class="goalsFor">T</td>
						<td class="goalsAgainst">GT</td>
						<td class="goalDifference">+/-</td>
						<td class="points">P</td>
					</thead>
					<tbody
						data-bind="foreach: groups[2]==undefined ? new Array() : groups[2].sortedTeams">
						<tr>
							<td class="position" data-bind="text: presentPos($index())"></td>
							<td class="teamName" data-bind="text: teamName"></td>
							<td class="gamesPlayed" data-bind="text: gamesPlayed"></td>
							<td class="gamesWon" data-bind="text: gamesWon"></td>
							<td class="gamesDrawn" data-bind="text: gamesDrawn"></td>
							<td class="gamesLost" data-bind="text: gamesLost"></td>
							<td class="goalsFor" data-bind="text: goalsFor"></td>
							<td class="goalsAgainst" data-bind="text: goalsAgainst"></td>
							<td class="goalDifference" data-bind="text: goalDifference"></td>
							<td class="points" data-bind="text: points"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="scheduleContainer">
				<div class="header">Spielplan</div>
				<div class="header-separator"></div>
				<div
					data-bind="foreach: groups[2]==undefined ? new Array() : groups[2].fixture.matchdays">
					<table class="matchday">
						<thead>
							<tr>
								<td
									data-bind="text: presentMatchdayHeader(comunioMatchdayNumber)"></td>
							</tr>
						</thead>
						<tbody data-bind="foreach: matches">
							<tr>
								<td class="home-team" data-bind="text: homeTeam.teamName"></td>
								<td class="home-goals" data-bind="text: homeGoals"></td>
								<td>:</td>
								<td class="away-goals" data-bind="text: awayGoals"></td>
								<td class="away-team" data-bind="text: awayTeam.teamName"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="groupD">
			<div class="tableContainer">
				<div class="header">Tabelle</div>
				<div class="header-separator"></div>
				<table class="table">
					<thead>
						<td class="position">Pos</td>
						<td class="teamName">Name</td>
						<td class="gamesPlayed">Sp</td>
						<td class="gamesWon">S</td>
						<td class="gamesDrawn">U</td>
						<td class="gamesLost">N</td>
						<td class="goalsFor">T</td>
						<td class="goalsAgainst">GT</td>
						<td class="goalDifference">+/-</td>
						<td class="points">P</td>
					</thead>
					<tbody
						data-bind="foreach: groups[3]==undefined ? new Array() : groups[3].sortedTeams">
						<tr>
							<td class="position" data-bind="text: presentPos($index())"></td>
							<td class="teamName" data-bind="text: teamName"></td>
							<td class="gamesPlayed" data-bind="text: gamesPlayed"></td>
							<td class="gamesWon" data-bind="text: gamesWon"></td>
							<td class="gamesDrawn" data-bind="text: gamesDrawn"></td>
							<td class="gamesLost" data-bind="text: gamesLost"></td>
							<td class="goalsFor" data-bind="text: goalsFor"></td>
							<td class="goalsAgainst" data-bind="text: goalsAgainst"></td>
							<td class="goalDifference" data-bind="text: goalDifference"></td>
							<td class="points" data-bind="text: points"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="scheduleContainer">
				<div class="header">Spielplan</div>
				<div class="header-separator"></div>
				<div
					data-bind="foreach: groups[3]==undefined ? new Array() : groups[3].fixture.matchdays">
					<table class="matchday">
						<thead>
							<tr>
								<td
									data-bind="text: presentMatchdayHeader(comunioMatchdayNumber)"></td>
							</tr>
						</thead>
						<tbody data-bind="foreach: matches">
							<tr>
								<td class="home-team" data-bind="text: homeTeam.teamName"></td>
								<td class="home-goals" data-bind="text: homeGoals"></td>
								<td>:</td>
								<td class="away-goals" data-bind="text: awayGoals"></td>
								<td class="away-team" data-bind="text: awayTeam.teamName"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="playoff">
			<div class="playoffContainer">
				<div class="header">KO-Phase</div>
				<div class="header-separator"></div>
				<div>Die KO-Phase beginnt erst am 11. Spieltag.</div>
			</div>
		</div>
		<div class="info-message">
			<div class="header">Willkommen!</div>
			<div class="closer" onclick="closeInfoMessage()">X</div>
			<div class="header-separator"></div>

			<table>
				<tr>
					<td class="label">Com CL Name:</td>
					<td class="com" data-bind="text: name"></td>
				</tr>
				<tr>
					<td class="label">Com Id:</td>
					<td class="id" data-bind="text: comunioId"></td>
				</tr>
			</table>
			<div>
				Die Com CL wurde erfolgreich erstellt. Um die Com CL Übersichtsseite
				zu erreichen, müssen auf der Startseite entweder Com-Id oder
				Com-Name eingegeben werden. Diese sollten daher den anderen
				Teilnehmern mitgeteilt werden. <br /> <br />Der Admin hat die
				Aufgabe die erreichten Comunio-Punkte eines Spieltages im
				Admin-Bereich (Link rechts oben) einzutragen, dazu wird auch dass
				Passwort benötigt. Anhand dieser Punkte werden die erzielten Tore in
				den Begegnungen errechnet. Weitere Informationen zum Spiel sind in
				den Regeln zu finden. <br /> <br />Viel Spaß in der kommenden
				Saison!
			</div>
		</div>
	</div>
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-53882327-1', 'auto');
  ga('send', 'pageview');

</script>
</body>
</html>