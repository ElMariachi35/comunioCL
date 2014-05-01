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
	<h1>Playoff</h1>
	<h3>Viertelfinale</h3>
	<span>${comunio.playoff.quaterFinal}</span>
	<c:forEach var="pairing" items="${comunio.playoff.quaterFinal.pairings}" varStatus="counter">
		<p>Paarung ${counter.index+1 }</p>
		<span>${pairing.firstLeg.homeTeam.teamName } : ${pairing.firstLeg.awayTeam.teamName } </span><br />
		<span>${pairing.secondLeg.homeTeam.teamName } : ${pairing.secondLeg.awayTeam.teamName } </span><br />
	</c:forEach>
	<h3>Halbfinale</h3>
	<h3>Finale</h3>
</body>
</html>