<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Com Champions League</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/overview.css" />
<script>
	var COMUNIO = $.parseJSON('${comunioJSON}');
	console.log(COMUNIO);
</script>
</head>
<body>
    <div class="container">
        <div id="comunio">
            <div data-bind="text: comunioId"></div>
            <div data-bind="text: name"></div>
            <ul class="idTabs">
                <li id="headerA" class="hidden"><a href="#groupA">Gruppe A</a></li>
                <li id="headerB" class="hidden"><a href="#groupB">Gruppe B</a></li>
                <li id="headerC" class="hidden"><a href="#groupC">Gruppe C</a></li>
                <li id="headerD" class="hidden"><a href="#groupD">Gruppe D</a></li>
            </ul>
            <div id="groupA">
                <div id="tableContainer">
                    <div>Tabelle</div>
                    <table>
                        <thead>
                            <td>Pos</td>
                            <td>Name</td>
                            <td>Sp</td>
                            <td>S</td>
                            <td>U</td>
                            <td>N</td>
                            <td>T</td>
                            <td>GT</td>
                            <td>+/-</td>
                            <td>P</td>
                        </thead>
                        <tbody data-bind="foreach: groups[0].sortedTeams">
                            <tr>
                                <td data-bind="text: presentPos($index())"></td>
                                <td data-bind="text: teamName"></td>
                                <td data-bind="text: gamesPlayed"></td>
                                <td data-bind="text: gamesWon"></td>
                                <td data-bind="text: gamesDrawn"></td>
                                <td data-bind="text: gamesLost"></td>
                                <td data-bind="text: goalsFor"></td>
                                <td data-bind="text: goalsAgainst"></td>
                                <td data-bind="text: goalDifference"></td>
                                <td data-bind="text: points"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="scheduleContainer"></div>
            </div>
            <div id="groupB">
                <div id="tableContainer">
                    <div>Tabelle</div>
                    <table>
                        <thead>
                            <td>Pos</td>
                            <td>Name</td>
                            <td>Sp</td>
                            <td>S</td>
                            <td>U</td>
                            <td>N</td>
                            <td>T</td>
                            <td>GT</td>
                            <td>+/-</td>
                            <td>P</td>
                        </thead>
                        <tbody data-bind="foreach: groups[1].sortedTeams">
                            <tr>
                                <td data-bind="text: presentPos($index())"></td>
                                <td data-bind="text: teamName"></td>
                                <td data-bind="text: gamesPlayed"></td>
                                <td data-bind="text: gamesWon"></td>
                                <td data-bind="text: gamesDrawn"></td>
                                <td data-bind="text: gamesLost"></td>
                                <td data-bind="text: goalsFor"></td>
                                <td data-bind="text: goalsAgainst"></td>
                                <td data-bind="text: goalDifference"></td>
                                <td data-bind="text: points"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="groupC">Groupe C</div>
            <div id="groupD">Groupe D</div>
        </div>
    </div>
</body>
</html>