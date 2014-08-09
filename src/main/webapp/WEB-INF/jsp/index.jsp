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
		<img class="logo" src="../../images/com-cl-logo.png" />
		<div class="header">Com Champions League</div>
		<table class="login-input">
			<tr>
				<td class="label">Com-Id</td>
				<td></td>
				<td class="label">Com-Name</td>
			</tr>
			<tr>
				<td><input type="text" name="inputComId" id="inputComId" /></td>
				<td class="center or">oder</td>
				<td><input type="text" name="inputComName" id="inputComName" /></td>
			</tr>
			<tr>
				<td class="center" colspan="3"><input type="button"
					value="Weiter" onclick="sendOverviewRequest()"></td>
			</tr>
			<tr>
				<td class="center" colspan="3"><div
						class="animation-marker load-animation-big"></div></td>
			</tr>
			<tr>
				<td class="center" colspan="3"><div id="errorMessage"
						class="error-text">&nbsp;</div></td>
			</tr>
		</table>

		<div class="horizontal-separator"></div>
		<div class="info-text">
			<span class="bold">Zum ersten Mal hier?</span><br /> Bei Com
			Champions League handelt es sich um eine Erweiterung des
			Managerspiels <a href="http://www.comunio.de">Comunio</a>. Im
			Gegensatz zum normalen Modus, spielen hier die Teams in direkten
			Duellen gegeneinander. Wie bei der "echten" Champions League gibt es
			eine Gruppenphase mit anschließender KO-Phase.
		</div>
		<div class="new-com-url">
			<span class="bold"><a href="/register">Neue Com Champions
					League registrieren</a></span>
		</div>
	</div>
</body>
</html>
