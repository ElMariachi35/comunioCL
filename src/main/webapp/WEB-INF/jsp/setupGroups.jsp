<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h1>Set up Groups</h1>
	<form id="setupGroupsFrom" action="saveGroups" method="POST">
		<div>
			<label for="numberOfGroups">Number of groups:</label> 
			<input type="text" name="numberOfGroups" /><br />
			<div>
			    <input type="radio" name="drawMode" value="automatic"> automatic draw<br>
	    		<input type="radio" name="drawMode" value="manual">manual draw<br>
	    	</div>
	    	<input type="submit" value="Continue" />
		</div>
	</form>

	
</body>
</html>