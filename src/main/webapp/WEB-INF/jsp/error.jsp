<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/error.css" />
<title>Com Champions League</title>
</head>
<body>
    <div class="container">
        <div class="errorContainer">
            <div class="header bold">Fehler</div>
            <div class="header-separator"></div>
            <div>
                <p>Leider ist ein unerwarteter Fehler aufgetreten. Wir bitten um Verständnis!</p>

                <a href="/index">Zur Startseite</a>
            </div>
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