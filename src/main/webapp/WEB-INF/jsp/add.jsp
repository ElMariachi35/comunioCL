<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comunio Management</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h1>Create new Comunio-ChampionsLeague</h1>

<h1>Comunios Data</h1>
<form action="add" method="POST">
	<table>
		<tr>
			<td>Comunio name</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="password" /></td>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Continue" />
			</td>
		</tr>
	</table>
</form>
<br>
<div>
	<h2>${comunio.name} ${comunio.comunioId}</h2>
	<form action="inputComunioSize" method="POST">
		<div>
			<label for="numberOfTeams">Number of teams:</label>
			<input type="text" name="numberOfTeams" /><br />
			<label for="numberOfGroups">Number of groups:</label>
			<input type="text" name="numberOfGroups" />
			<input type="hidden" name="comunioId" value=${comunio.comunioId}>
		</div>
		<input type="submit" name="action" value="Continue" />
		
	</form>
</div>


<table border="1">
	<th>ID</th>
	<th>Name</th>
	<c:forEach items="${groups}" var="group">
		<tr>
			<td>${group.groupId}</td>
			<td>${group.groupName}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>