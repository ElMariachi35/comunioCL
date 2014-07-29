<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Com Champions League</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/index.css" />
</head>
<body>
	<div class="container">
		<div class="header">Com Champions League</div>
		<div class="login-input">
			<div class="id-block">
				<div class="label">Com-Id</div>
				<input type="text" name="inputComId" id="inputComId" />
			</div>
			<div class="or">oder</div>
			<div class="name-block">
				<div class="label">Com-Name</div>
				<input type="text" name="inputComName" id="inputComName" />
			</div>
		</div>
		<input class="continue-button" type="button" value="Weiter"
			onclick="sendOverviewRequest()">
			<div id="errorMessage" class="errorText">&nbsp;</div>
		<div class="horizontal-separator"></div>
		<div class="info-text">
			Zum ersten Mal hier?<br /> Bei Com Champions League handelt es sich
			um eine Erweiterung des Managerspiels <a href="http://www.comunio.de">Comunio</a>.
			Im Gegensatz zum normalen Modus, spielen hier die Teams in direkten
			Duellen gegeneinander. Wie bei der "echten" Champions League gibt es
			eine Gruppenphase mit anschließender KO-Phase.
		</div>
		<div class="new-com-url">
			<a href="/register">Neue Com Champions League registrieren</a>
		</div>
		<!-- <a href="/add">Create -->
		<!-- 			new Champions League for your Comunio</a> <br /> <br /> <label -->
		<!-- 			for="inputComunioId">ComunioId</label> <input id="inputComunioId" -->
		<!-- 			type="text" name="inputComunioId" /> <input type="button" -->
		<!-- 			value="Show" onclick="sendOverviewRequest()" /> -->
	</div>
</body>
</html>
