<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Teams</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h1>Add Teams</h1>

	<h2>Name: ${comunio.name}</h2>
	<h3>ID: ${comunio.comunioId}</h3>
	<br />
	<h3>Input teamnames:</h3>
	<form id="addTeamsForm" action="addTeams" method="POST">
	
	</form>

	<script>
		var numberOfTeams = ${numberOfTeams};
		for (var i = 0; i < numberOfTeams; i++) {
			$('#addTeamsForm').append('<input type="text" name="team'+i+'" /><br />');
		}
		$('#addTeamsForm').append('<input type="submit" name="action" value="Continue"/><br />');
	</script>

</body>
</html>