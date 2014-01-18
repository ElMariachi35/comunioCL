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
	<%@ include file="/WEB-INF/jsp/menu.jsp"%>

	<div>Gruppe ${group.groupName}</div>

	<div id="groupHeader">
		<div class="groupHeaderPadding position">Pos</div>
		<div class="groupHeaderPadding team">Team</div>
		<div class="groupHeaderPadding numeric">G</div>
		<div class="groupHeaderPadding numeric">W</div>
		<div class="groupHeaderPadding numeric">D</div>
		<div class="groupHeaderPadding numeric">L</div>
		<div class="groupHeaderPadding numeric">GF</div>
		<div class="groupHeaderPadding numeric">GA</div>
		<div class="groupHeaderPadding difference">Diff</div>
		<div class="groupHeaderPadding numeric">Pts</div>
	</div>
	<div id="groupBody">
		<c:forEach var="team" items="${group.teams}">
		<div class="position"></div>
		<div class="team">${team.teamName}</div>
		<div class="numeric"></div>
		<div class="numeric"></div>
		<div class="numeric"></div>
		<div class="numeric"></div>
		<div class="numeric"></div>
		<div class="numeric"></div>
		<div class="difference"></div>
		<div class="numeric"></div>
		</c:forEach>
	</div>

</body>
</html>