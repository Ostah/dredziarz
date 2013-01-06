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
  		  <b>Edytuj wartości przedziałów</b>
  	  </h2>
  </div>
 
  
  <form method="post" action="valueEditor">
		  <div id="main">
			  <fieldset>
			  	<legend>Cena</legend> <br>	
                                <table >
                                        <tr> <td>       B. Tanie </td><td> Od <input name="cena1min" id="cena1min" type="number" readonly="true" value="<%= session.getAttribute("cena1min") %>"> zł</td> <td> do <input name="cena1max" id="cena1max" type="number" value="<%= session.getAttribute("cena1max") %>"> zł</td> </tr>
			  		<tr> <td>  	Tanie </td><td> Od <input name="cena2min" id="cena2min" type="number" value="<%= session.getAttribute("cena2min") %>"> zł</td><td>  do <input name="cena2max" id="cena2max" type="number" value="<%= session.getAttribute("cena2max") %>"> zł</td></tr>
			  		<tr> <td>  	Normalne </td><td>  Od <input name="cena3min" id="cena3min" type="number" value="<%= session.getAttribute("cena3min") %>"> zł</td><td>  do <input name="cena3max" id="cena3max" type="number" value="<%= session.getAttribute("cena3max") %>"> zł</td> </tr>
			  		<tr> <td>  	Drogie </td><td> Od <input name="cena4min" id="cena4min" type="number" value="<%= session.getAttribute("cena4min") %>"> zł</td><td>  do <input name="cena4max" id="cena4max" type="number" value="<%= session.getAttribute("cena4max") %>"> zł</td></tr>
			  		<tr> <td>  	B. Drogie</td><td> Od <input name="cena5min" id="cena5min" type="number" value="<%= session.getAttribute("cena5min") %>"> zł</td><td>  do <input name="cena5max" id="cena5max" type="number" readonly="true" value="<%= session.getAttribute("cena5max") %>"> zł</td></tr>
                                </table>
                                                </fieldset>
		   </div>
		    <div id="main">
			  <fieldset>
			  	<legend>Metraż</legend>	<br>
                                <table>
			  			<tr> <td>B. Małe</td><td> Od <input name="metraz1min" id="metraz1min" type="number" readonly="true" value="<%= session.getAttribute("metraz1min") %>"> m<sup>2</sup></td><td> do <input name="metraz1max" id="metraz1max" type="number" value="<%= session.getAttribute("metraz1max") %>"> m<sup>2</sup> </td> </tr>
			  			<tr> <td>Małe</td><td> Od <input name="metraz2min" id="metraz2min" type="number" value="<%= session.getAttribute("metraz2min") %>"> m<sup>2</sup> </td><td>do <input name="metraz2max" id="metraz2max" type="number" value="<%= session.getAttribute("metraz2max") %>"> m<sup>2</sup></td> </tr>
			  			<tr> <td>Normalne</td><td> Od <input name="metraz3min" id="metraz3min" type="number" value="<%= session.getAttribute("metraz3min") %>"> m<sup>2</sup></td><td> do <input name="metraz3max" id="metraz3max" type="number" value="<%= session.getAttribute("metraz3max") %>"> m<sup>2</sup> </td> </tr>
			  			<tr> <td>Duże</td><td> Od <input name="metraz4min" id="metraz4min" type="number" value="<%= session.getAttribute("metraz4min") %>"> m<sup>2</sup></td><td> do <input name="metraz4max" id="metraz4max" type="number" value="<%= session.getAttribute("metraz4max") %>"> m<sup>2</sup> </td> </tr>
			  			<tr> <td>B. Duże</td><td> Od <input name="metraz5min" id="metraz5min" type="number" value="<%= session.getAttribute("metraz5min") %>"> m<sup>2</sup></td><td> do <input name="metraz5max" id="metraz5max" type="number" readonly="true" value="<%= session.getAttribute("metraz5max") %>"> m<sup>2</sup></td> </tr>
			  </table>
                          </fieldset>
		   </div>
      
                   <div id="main">
			  <fieldset>
			  	<legend>Odległość od centrum</legend> <br> <table>	
			  			<tr> <td>B. Mała</td><td> Od <input name="odleglosc1min" id="odleglosc1min" type="number" readonly="true" value="<%= session.getAttribute("odleglosc1min") %>"> m </td><td>do <input name="odleglosc1max" id="odleglosc1max" type="number" value="<%= session.getAttribute("odleglosc1max") %>"> m </td> </tr>
			  			<tr> <td>Małe</td><td> Od <input name="odleglosc2min" id="odleglosc2min" type="number" value="<%= session.getAttribute("odleglosc2min") %>"> m </td><td>do <input name="odleglosc2max" id="odleglosc2max" type="number" value="<%= session.getAttribute("odleglosc2max") %>"> m </td> </tr>
			  			<tr> <td>Normalna</td><td> Od <input name="odleglosc3min" id="odleglosc3min" type="number" value="<%= session.getAttribute("odleglosc3min") %>"> m </td><td>do <input name="odleglosc3max" id="odleglosc3max" type="number" value="<%= session.getAttribute("odleglosc3max") %>"> m </td> </tr>
			  			<tr> <td>Duża</td><td> Od <input name="odleglosc4min" id="odleglosc4min" type="number" value="<%= session.getAttribute("odleglosc4min") %>"> m </td><td>do <input name="odleglosc4max" id="odleglosc4max" type="number" value="<%= session.getAttribute("odleglosc4max") %>"> m </td> </tr>
			  			<tr> <td>B. Duża</td><td> Od <input name="odleglosc5min" id="odleglosc5min" type="number" value="<%= session.getAttribute("odleglosc5min") %>"> m</td><td> do <input name="odleglosc5max" id="odleglosc5max" type="number" readonly="true" value="<%= session.getAttribute("odleglosc5max") %>"> m </td> </tr>
			 </table>  </fieldset>
		   </div>
		    
		    <div id="sliderNavigation">                 
                            <input type="submit" value="Zmień wartości" />
		    </div>
  </form>
  <footer>

  </footer>


  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="js/libs/jquery.min.js"><\/script>')</script>
</body>
</html>