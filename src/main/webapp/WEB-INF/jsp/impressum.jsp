<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/impressum.css" />
<title>Com Champions League</title>
</head>
<body>
    <div class="container">
        <%@ include file="/WEB-INF/jsp/header.jsp"%>
        <div class="impressumContainer">
            <div>Impressum</div>
            <div class="header-separator"></div>
            Diese Website wird privat und ohne jeglicher Gewinnabsicht betrieben. <br /> Bei Problemen, Anregungen oder
            W&uuml;nsche wenden Sie sich bitte an:<br />
            <p class="contact-url">
                <a class="contact-url" href="mailto:com.champions.league@gmail.com">com.champions.league@gmail.com</a>
            </p>
            Disclaimer:
            <div class="analytics-textarea">
                Diese Website benutzt Google Analytics, einen Webanalysedienst der Google Inc. ('Google'). Google
                Analytics verwendet sog. 'Cookies', Textdateien, die auf Ihrem Computer gespeichert werden und die eine
                Analyse der Benutzung der Website durch Sie erm&ouml;glichen. Die durch den Cookie erzeugten
                Informationen &uuml;ber Ihre Benutzung dieser Website werden in der Regel an einen Server von Google in
                den USA &uuml;bertragen und dort gespeichert. Im Falle der Aktivierung der IP-Anonymisierung auf dieser
                Webseite, wird Ihre IP-Adresse von Google jedoch innerhalb von Mitgliedstaaten der Europ&auml;ischen
                Union oder in anderen Vertragsstaaten des Abkommens &uuml;ber den Europ&auml;ischen Wirtschaftsraum
                zuvor gek&uuml;rzt. Nur in Ausnahmef&auml;llen wird die volle IP-Adresse an einen Server von Google in
                den USA &uuml;bertragen und dort gek&uuml;rzt. Die IP-Anonymisierung ist auf dieser Website aktiv. Im
                Auftrag des Betreibers dieser Website wird Google diese Informationen benutzen, um Ihre Nutzung der
                Website auszuwerten, um Reports &uuml;ber die Websiteaktivit&auml;ten zusammenzustellen und um weitere
                mit der Websitenutzung und der Internetnutzung verbundene Dienstleistungen gegen&uuml;ber dem
                Websitebetreiber zu erbringen. Die im Rahmen von Google Analytics von Ihrem Browser &uuml;bermittelte
                IP-Adresse wird nicht mit anderen Daten von Google zusammengef&uuml;hrt. Sie k&ouml;nnen die Speicherung
                der Cookies durch eine entsprechende Einstellung Ihrer Browser-Software verhindern; wir weisen Sie
                jedoch darauf hin, dass Sie in diesem Fall gegebenenfalls nicht s&auml;mtliche Funktionen dieser Website
                vollumf&auml;nglich werden nutzen k&ouml;nnen. Sie k&ouml;nnen dar&uuml;ber hinaus die Erfassung der
                durch das Cookie erzeugten und auf Ihre Nutzung der Website bezogenen Daten (inkl. Ihrer IP-Adresse) an
                Google sowie die Verarbeitung dieser Daten durch Google verhindern, indem sie das unter dem folgenden
                Link verf&uuml;gbare Browser-Plugin herunterladen und installieren: <a
                    href="http://tools.google.com/dlpage/gaoptout?hl=de">http://tools.google.com/dlpage/gaoptout?hl=de</a>.

                Sie k&ouml;nnen die Erfassung durch Google Analytics verhindern, indem Sie auf folgenden Link klicken.
                Es wird ein Opt-Out-Cookie gesetzt, das die zuk&uuml;nftige Erfassung Ihrer Daten beim Besuch dieser
                Website verhindert: <a href=”javascript:gaOptout()”>Google Analytics deaktivieren</a> N&auml;here
                Informationen zu Nutzungsbedingungen und Datenschutz finden Sie unter <a
                    href="http://www.google.com/analytics/terms/de.html">http://www.google.com/analytics/terms/de.html</a>
                bzw. unter <a href="https://www.google.de/intl/de/policies/">https://www.google.de/intl/de/policies/</a>.
                Wir weisen Sie darauf hin, dass auf dieser Website Google Analytics um den Code 'gat._anonymizeIp();'
                erweitert wurde, um eine anonymisierte Erfassung von IP-Adressen (sog. IP-Masking) zu gew&auml;hrleisten
            </div>
        </div>

    </div>
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