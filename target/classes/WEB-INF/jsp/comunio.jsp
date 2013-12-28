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
<h1>Create new Comunio-ChampionsLeage</h1>

<h1>Comunios Data</h1>
<form action="add" method="POST">
	<table>
		<tr>
			<td>Comunio name</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>Number of Groups</td>
			<td><input type="text" name="numberOfGroups" /></td>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
			</td>
		</tr>
	</table>
</form>
<br>
<table border="1">
	<th>ID</th>
	<th>Name</th>
	<c:forEach items="${comunioList}" var="comunio">
		<tr>
			<td>${comunio.comunioId}</td>
			<td>${comunio.name}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>