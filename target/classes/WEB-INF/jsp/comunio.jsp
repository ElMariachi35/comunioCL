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
<h1>Comunios Data</h1>
<form:form action="comunio.do" method="POST" commandName="comunio">
	<table>
		<tr>
			<td>Comunio ID</td>
			<td><form:input path="comunioId" /></td>
		</tr>
		<tr>
			<td>Team name</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
				<input type="submit" name="action" value="Edit" />
				<input type="submit" name="action" value="Delete" />
				<input type="submit" name="action" value="Search" />
			</td>
		</tr>
	</table>
</form:form>
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