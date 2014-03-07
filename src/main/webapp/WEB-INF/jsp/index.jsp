<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comunio Champions League</title>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h1>Welcome to Comunio Champions League</h1>

	<a href="/add">Create new Champions League for your Comunio</a>
	<br />
	<br />
	<label for="inputComunioId">ComunioId</label>
	<input id="inputComunioId" type="text" name="inputComunioId" />
	<input type="button" value="Show" onclick="sendOverviewRequest()"/>
</body>
</html>