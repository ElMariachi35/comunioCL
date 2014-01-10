<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h1>Create new Comunio-ChampionsLeague</h1>

<br>
<div>
	<form id="addComunioForm" action="saveComunio" method="POST">
		<div>
			<label for="comunioName">Comunio name:</label>
			<input type="text" name="comunioName" /><br />
			<label for="password">Password:</label>
			<input type="password" name="password" /><br />
			<label for="numberOfTeams">Number of teams:</label>
			<input id="numberOfTeams" type="text" name="numberOfTeams" /><br />
		</div>
	</form>
	<div id="addTeams"></div>
	<input id="createTeamFieldButton" type="button" value="Create" onclick="generateTeamFields()" /><br />
</div>
</body>
</html>