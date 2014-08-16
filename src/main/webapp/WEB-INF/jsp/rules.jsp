<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/rules.css" />

<title>Com Champions League</title>
</head>
<body>
    <div class="container">
        <%@ include file="/WEB-INF/jsp/header.jsp"%>
        <div class="rulesContainer">
            <div class="header rules-header">Regeln</div>
            <div class="header-separator"></div>
            <div>
                Im Gegensatz zur normalen Comunio-Meisterschaft treten in der Com Champions League die Mannschaften
                immer gegeneinander an. Die angesetzten Paarungen k&ouml;nnen dem Spielplan entnommen werden.<br /> <br />
                Die Berechnung des Siegers eines Spiels ergibt sich aus den erreichten Punkten eines Spieltages aus der
                normalen Comunio-Berechnung. Die erreichten Punkte einer Mannschaft werden durch 10 geteilt und
                anschlie&szlig;end kaufm&auml;nnisch gerundet. Der so entstandene Wert ergibt die Anzahl der
                geschossenen Tore an diesem Spieltag.
            </div>
            <div>
                <p>Ein Beispiel:</p>

                <p>In der ersten Runde treffen die Mannschaften TeamA und TeamB aufeinander. In der normalen
                    Comunio-Abrechnung erreichen:</p>

                <p>
                    TeamA: 43 Punkte<br /> TeamB: 15 Punkte
                </p>

                <p>Die Berechnung der Tore ergeht wie folgt:</p>

                <p>
                    TeamA: 43/10 = 4,3 ~ 4 Tore<br /> TeamB: 15/10 = 1,5 ~ 2 Tore
                </p>

                <p>Somit endet das Spiel:</p>

                <p>TeamA &ndash; TeamB 4:2</p>

                <p>
                    Team A erh&auml;lt 3 Punkte, Team B erh&auml;lt 0 Punkte. <br />
                </p>
            </div>
            <div class="header">Gruppenphase</div>
            <div class="header-separator"></div>
            <p>Die Gruppenphase beginnt am 1. (18.) Spieltag und endet am 10. (27.) Spieltag der deutschen
                Bundesliga. Abhängig von der Anzahl der teilnehmenden Teams spielt jeder zwischen 1-3 Mal gegeneinander</p>

            <p>F&uuml;r einen Sieg gibt es 3 Punkte, f&uuml;r ein Unentschieden 1 Punkt und f&uuml;r eine Niederlage
                0 Punkte.</p>

            <p>
                F&uuml;r die Reihung der Tabelle gelten folgende Regeln:<br /> &bull; h&ouml;here Anzahl der Punkte<br />
                &bull; bessere Tordifferenz<br /> &bull; h&ouml;here Anzahl der erzielten Tore<br /> &bull; höhere
                Anzahl der Siege<br /> &bull; Losentscheid
            </p>
            <div class="header">KO-Phase</div>
            <div class="header-separator"></div>
            <p>
                Nach Abschluss der Gruppenphase wird in der KO-Phase der Sieger der Com-CL ermittelt. Je nach Anzahl der
                Gruppen und Teilnehmer steigen entweder 4 oder 8 Teams in die KO-Phase auf.<br /> Genaueres in der
                folgenden Tabelle:
            </p>
            <table>
                <tr>
                    <th>Anz. der Teams</th>
                    <th>Anz. der Gruppen</th>
                    <th>Anz. Teams in KO-Phase</th>
                    <th class="promoted">Aufsteiger</th>
                </tr>
                <tr>
                    <td class="center">4, 5, 6, 7</td>
                    <td class="center">1</td>
                    <td class="center">4</td>
                    <td class="promoted">Die 4 Gruppenbesten</td>
                </tr>
                <tr>
                    <td class="center">8, 9, 10</td>
                    <td class="center">2</td>
                    <td class="center">4</td>
                    <td class="promoted">jeweils die 2 Gruppenbesten</td>
                </tr>
                <tr>
                    <td class="center">11</td>
                    <td class="center">2</td>
                    <td class="center">8</td>
                    <td class="promoted">jeweils die 4 Gruppenbesten</td>
                </tr>
                <tr>
                    <td class="center">12, 13, 14, 15</td>
                    <td class="center">3</td>
                    <td class="center">8</td>
                    <td class="promoted">jeweils die 2 Gruppenbesten + 2 besten Drittplatzierten</td>
                </tr>
                <tr>
                    <td class="center">16</td>
                    <td class="center">4</td>
                    <td class="center">8</td>
                    <td class="promoted">jeweils die 2 Gruppenbesten</td>
                </tr>
            </table>
            <p>Weitere Information zum Ablauf der KO-Phase erfolgen in Kürze.</p>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/footer.jsp"%>
    <script>
					(function(i, s, o, g, r, a, m) {
						i['GoogleAnalyticsObject'] = r;
						i[r] = i[r] || function() {
							(i[r].q = i[r].q || []).push(arguments)
						}, i[r].l = 1 * new Date();
						a = s.createElement(o),
								m = s.getElementsByTagName(o)[0];
						a.async = 1;
						a.src = g;
						m.parentNode.insertBefore(a, m)
					})(window, document, 'script',
							'//www.google-analytics.com/analytics.js', 'ga');

					ga('create', 'UA-53882327-1', 'auto');
					ga('send', 'pageview');
				</script>
</body>
</html>