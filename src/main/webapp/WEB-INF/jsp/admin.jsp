<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div id="admin">
		<script>
			var teams = $.parseJSON('${teams}');
			var matchdays = "${numberOfMatchdays}";
			createPointInputTable(matchdays, teams);
		</script>	
	</div>
</body>
</html>