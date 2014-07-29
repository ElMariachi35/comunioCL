<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/register.css" />
<title>Com Champions League</title>
</head>
<body onload="generateTeamFields()">
	<div class="container">
		<form id="registerForm" class="registerForm" action="save"
			method="POST">
			<div class="registerHeader">Neue Com Champions League anlegen</div>
			<table id="registerFormTable">
				<tr>
					<td class="labelColumn">Com CL Name:</td>
					<td><input type="text" name="comName" id="comName"/></td>
				</tr>
				<tr>
					<td>Passwort:</td>
					<td><input type="password" name="password" id="registerPassword" /></td>
				</tr>
				<tr>
					<td>Passwort wiederholen:</td>
					<td><input type="password" name="password"  id="registerPasswordRepeated"/></td>
				</tr>
				<tr>
					<td>Anzahl an Mannschaften:</td>
					<td><select id="numberOfTeams" name="numberOfTeams"
						onchange="generateTeamFields()">
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
					</select></td>
				</tr>
			</table>
			<table id="teamNamesTable">
			</table>
			<table id="registerValidationErrors" class="error-text">
			</table>
		</form>
	</div>
</body>
</html>