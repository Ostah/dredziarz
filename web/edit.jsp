<%-- 
    Document   : edit
    Created on : 2013-01-06, 08:18:49
    Author     : Jacek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<style>
.fieldrow {
	float: left;
	width: 100%;
}
</style>
  <meta name="viewport" content="width=device-width">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
  <header>
	  <h1>Wyszukiwarka mieszkań</h1>
  </header>
  <div class="navigationBar">
  	  <h2 class="whereAmI">
  		  <b>1. Personalizacja &raquo;</b>
  	  </h2>
  </div>
  <form method="post" action="fuzzyLogic">
		  <div id="main">
			  <fieldset>
			  	<legend>Cena</legend> <br>		
                                <div class="fieldrow">B. Tanie Od <input name="cena1min" id="cena1min" type="number"> zł do <input name="cena1max" id="cena1max" type="number"> zł </div>
			  			<div class="fieldrow">Tanie &#9 Od <input name="cena2min" id="cena2min" type="number"> zł do <input name="cena2max" id="cena2max" type="number"> zł </div>
			  			<div class="fieldrow">Normalne Od <input name="cena3min" id="cena3min" type="number"> zł do <input name="cena3max" id="cena3max" type="number"> zł </div>
			  			<div class="fieldrow">Drogie Od <input name="cena4min" id="cena4min" type="number"> zł do <input name="cena4max" id="cena4max" type="number"> zł </div>
			  			<div class="fieldrow">B. Drogie Od <input name="cena5min" id="cena5min" type="number"> zł do <input name="cena5max" id="cena5max" type="number"> zł </div>
			  </fieldset>
		   </div>
		    <div id="main">
			  <fieldset>
			  	<legend>Metraż</legend>	<br>
			  			<div class="fieldrow">B. Małe Od <input name="metraz1min" id="metraz1min" type="number"> m<sup>2</sup> do <input name="metraz1max" id="metraz1max" type="number"> m<sup>2</sup> </div>
			  			<div class="fieldrow">Małe Od <input name="metraz2min" id="metraz2min" type="number"> m<sup>2</sup> do <input name="metraz2max" id="metraz2max" type="number"> m<sup>2</sup> </div>
			  			<div class="fieldrow">Normalne Od <input name="metraz3min" id="metraz3min" type="number"> m<sup>2</sup> do <input name="metraz3max" id="metraz3max" type="number"> m<sup>2</sup> </div>
			  			<div class="fieldrow">Duże Od <input name="metraz4min" id="metraz4min" type="number"> m<sup>2</sup> do <input name="metraz4max" id="metraz4max" type="number"> m<sup>2</sup> </div>
			  			<div class="fieldrow">B. Duże Od <input name="metraz5min" id="metraz5min" type="number"> m<sup>2</sup> do <input name="metraz5max" id="metraz5max" type="number"> m<sup>2</sup> </div>
			  </fieldset>
		   </div>
      
                   <div id="main">
			  <fieldset>
			  	<legend>Odległość od centrum</legend> <br>	
			  			<div class="fieldrow">B. Mała Od <input name="odleglosc1min" id="odleglosc1min" type="number"> m do <input name="odleglosc1max" id="odleglosc1max" type="number"> m </div>
			  			<div class="fieldrow">Małe Od <input name="odleglosc2min" id="odleglosc2min" type="number"> m do <input name="odleglosc2max" id="odleglosc2max" type="number"> m </div>
			  			<div class="fieldrow">Normalna Od <input name="odleglosc3min" id="odleglosc3min" type="number"> m do <input name="odleglosc3max" id="odleglosc3max" type="number"> m </div>
			  			<div class="fieldrow">Duża Od <input name="odleglosc4min" id="odleglosc4min" type="number"> m do <input name="odleglosc4max" id="odleglosc4max" type="number"> m </div>
			  			<div class="fieldrow">B. Duża Od <input name="odleglosc5min" id="odleglosc5min" type="number"> m do <input name="odleglosc5max" id="odleglosc5max" type="number"> m </div>
			  </fieldset>
		   </div>
		    
		    <div id="sliderNavigation">
		    	 <input type="submit" value="Edytuj" />
		    </div>
  </form>
  <footer>

  </footer>


  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="js/libs/jquery.min.js"><\/script>')</script>
</body>
</html>