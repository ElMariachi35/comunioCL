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
	<c:forEach var="pairing" items="${comunio.playoff.quaterFinal.pairings}" varStatus="counter">
		<h4>Paarung ${counter.index+1 }</h4>
		<span>${pairing.firstLeg.homeTeam.teamName } - ${pairing.firstLeg.awayTeam.teamName} &nbsp;&nbsp;&nbsp;${pairing.firstLeg.homeGoals } : ${pairing.firstLeg.awayGoals}</span><br />
		<span>${pairing.secondLeg.homeTeam.teamName } - ${pairing.secondLeg.awayTeam.teamName } &nbsp;&nbsp;&nbsp;${pairing.secondLeg.homeGoals } : ${pairing.secondLeg.awayGoals} </span><br />
	</c:forEach>
	<h3>Halbfinale</h3>
	<c:forEach var="pairing" items="${comunio.playoff.semiFinal.pairings}" varStatus="counter">
		<p>Paarung ${counter.index+1 }</p>
		<span>${pairing.firstLeg.homeTeam.teamName } - ${pairing.firstLeg.awayTeam.teamName } &nbsp;&nbsp;&nbsp;${pairing.firstLeg.homeGoals } : ${pairing.firstLeg.awayGoals}</span><br />
		<span>${pairing.secondLeg.homeTeam.teamName } - ${pairing.secondLeg.awayTeam.teamName } &nbsp;&nbsp;&nbsp;${pairing.secondLeg.homeGoals } : ${pairing.secondLeg.awayGoals} </span><br />
	</c:forEach>
	<h3>Finale</h3>
	<span>${comunio.playoff.playoffFinal.firstLeg.homeTeam.teamName} - ${comunio.playoff.playoffFinal.firstLeg.awayTeam.teamName} &nbsp;&nbsp;&nbsp;${comunio.playoff.playoffFinal.firstLeg.homeGoals } : ${comunio.playoff.playoffFinal.firstLeg.awayGoals} </span><br />
	<span>${comunio.playoff.playoffFinal.secondLeg.homeTeam.teamName} - ${comunio.playoff.playoffFinal.secondLeg.awayTeam.teamName} &nbsp;&nbsp;&nbsp;${comunio.playoff.playoffFinal.secondLeg.homeGoals } : ${comunio.playoff.playoffFinal.secondLeg.awayGoals}</span><br />
	<span>${comunio.playoff.playoffFinal.thirdLeg.homeTeam.teamName} - ${comunio.playoff.playoffFinal.thirdLeg.awayTeam.teamName} &nbsp;&nbsp;&nbsp;${comunio.playoff.playoffFinal.thirdLeg.homeGoals } : ${comunio.playoff.playoffFinal.thirdLeg.awayGoals}</span>
</body>
</html>
