<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/admin.css" />
</head>
<body onload="loadNextMatchday()">
	<script>
		var teams = $.parseJSON('${teams}');
		var matchdays = "${numberOfMatchdays}";
		var COMUNIO = $.parseJSON('${comunioJSON}');
		//    		 createPointInputTable(matchdays, teams);
	</script>
	<div class="container">
		<%@ include file="/WEB-INF/jsp/header.jsp"%>

		<div class="admin-container">
			<div class="admin-header">Punkteeingabe</div>
			<div class="header-separator"></div>
			<table class="table">
				<tr>
					<td class="labelColumn">Spieltag auswählen:</td>
					<td><select id="numberOfMatchday" name="numberOfMatchday"
						onchange="loadMatchday()">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
					</select>
						<div class="load-loader"></div></td>
				</tr>
				<tbody data-bind="foreach: teams">
					<tr>
						<td data-bind="text: teamName"></td>
						<td><input type="text"
							data-bind="attr:{ id:teamId, name:teamId}" />
					</tr>
				</tbody>
				<tr>
					<td class="admin-password-label">Passwort</td>
					<td><input type="password" name="password"
						class="admin-password-input" id="adminPassword" /></td>
				</tr>
				<tr>
					<td colspan="2" class="save-button-cell"><input
						class="save-button" type="button" value="Speichern"
						onclick="saveMatchday()"></td>
					<td class="save-loader-cell"><div class="save-loader"></div></td>

				</tr>
				<tr>
					<td colspan="2"><div id="message"></div></td>
				</tr>
			</table>
		</div>

	</div>
    <%@ include file="/WEB-INF/jsp/footer.jsp"%>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-53882327-1', 'auto');
		ga('send', 'pageview');
	</script>
</body>
</html>