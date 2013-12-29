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

<br>
<div>
	<form action="add" method="POST">
		<div>
			<label for="name">Comunio name:</label>
			<input type="text" name="name" /><br />
			<label for="password">Password:</label>
			<input type="password" name="password" /><br />
			<label for="numberOfTeams">Number of teams:</label>
			<input type="text" name="numberOfTeams" /><br />
			<label for="numberOfGroups">Number of groups:</label>
			<input type="text" name="numberOfGroups" />
		</div>
		<input type="submit" name="action" value="Continue" />
		
	</form>
</div>

<h2>Name: ${comunio.name}</h2>
<h3>ID: ${comunio.comunioId}</h3>
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