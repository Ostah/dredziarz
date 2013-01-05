<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title></title>
  <meta name="description" content="">

  <meta name="viewport" content="width=device-width">
  <link rel="stylesheet" href="css/style.css">

  <script src="js/libs/modernizr-2.5.3.min.js"></script>
</head>
<body>
  <!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
  <header>
	  <h1>Wyszukiwarka mieszkań</h1>
  </header>
  <div class="navigationBar">
  	  <h2 class="whereAmI">
  		  1. Personalizacja &raquo; <b>2. Wyniki wyszukiwania</b>
  	  </h2>
  </div>
<div id="main">
<div class="searchParams">
	<div class="requiredParams">
	<h2>Wybrane atrybuty</h2>
            <ul>
                <li>Cena od <b>${minCena}zł</b> do <b>${maxCena}zł</b></li>
                 <li>Powierzchnia od <b>${minArea}m<sup>2</sup></b> do <b>${maxArea}m<sup>2</sup></b></li>
                <c:if test="${garaz!=null}">
                    <li>Garaż</li>
                </c:if>
                <c:if test="${ochrona!=null}">
                    <li>Strzeżone osiedle</li>
                </c:if>
                 <c:if test="${placZabaw!=null}">
                    <li>Plac zabaw na osiedlu</li>
                </c:if>
                <c:if test="${winda!=null}">
                    <li>Winda</li>
                </c:if>
                 <c:if test="${bliskoCentrum!=null}">
                    <li>Odległość od centrum jest <b>ważna</b></li>
                </c:if>
                 <c:if test="${bliskoSzkoly!=null}">
                    <li>Odległość od szkoły jest <b>ważna</b></li>
                </c:if>
            </ul>
	</div>

	<div class="searchParams">
		<table id="tabelaWynikow">
		<thead>
			<tr>
				<th>
					Nazwa ogłoszenia
				</th>
                                <th>
                                        Zgodność
                                </th>
				<th>
					Adres
				</th>
				<th>
					Powierzchnia
				</th>
				<th>
					Cena
				</th>
				<th>
					 Od centrum
				</th>
				<th>
					Od szkoły
				</th>
				<th>
					Winda
				</th>
				<th>
					Plac zabaw
				</th>
				<th>
					Strzeżone osiedle
				</th>
                                <th>
                                        Informacja
                                </th>
			</tr>
		</thead>
		<tbody>
                  
                        <c:forEach var="u" items="${resultList}">
                             
                                 ${u}                                  
                             
                        </c:forEach>
                   
		</tbody>
		</table>
		
	</div>
</div>
</div>

  <footer>

  </footer>

  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.1.min.js"><\/script>')</script>

  <script src="js/plugins.js"></script>
  <script src="js/script.js"></script>

  <script>
    var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
    (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
    g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
    s.parentNode.insertBefore(g,s)}(document,'script'));
  </script>
</body>
</html>