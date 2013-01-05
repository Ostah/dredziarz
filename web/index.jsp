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
			  	<legend>Jaki masz budżet na zakup nieruchomości ?</legend>		
			  			Od <input name="minBudget" id="maxBudget" type="number"> zł do <input name="maxBudget" id="maxBudget" type="number"> zł 

			  </fieldset>
		   </div>
			<div id="main">
			  <fieldset>
			  	<legend>Jaka powierzchnia Cię interesuje ?</legend>
			  	<select name="flatSize">
			  		<option value="verySmallArea">Bardzo Mała</option>
			  		<option value="smallArea">Mała</option>
			  		<option value="mediumArea">Średnia</option>
			  		<option value="largeArea">Duża</option>
			  		<option value="veryLargeArea">Bardzo duża</option>
				</select>
			  </fieldset>
		   </div>
		   <div id="main">
		     <fieldset>
		     	<legend>Cechy Twojego  JACEK TO PAŁA wymarzonego mieszkania </legend>
		     		<ul>
		     			<li>
		     				<input type="checkbox" name="garage" value="garage" id="garage">
		     				<label for="garage">Garaż</label>
		     			</li>
		     			<li>
				     		<input type="checkbox" name="closeToCenter" value="closeToCenter" id="closeToCenter">
				     		<label for="closeToCenter">Odległość od <b>centrum</b> 
				     			<select name="req_distanceFromCenter">
				     				<option value="2">Blisko</option>
				     				<option value="5">Umiarkowana</option>
				     				<option value="15">Daleko</option>
				     			</select>
				     		</label>
						</li>
						<li>
				     		<input type="checkbox" name="closeToSchool" value="closeToSchool" id="closeToSchool">
				     		<label for="closeToSchool">Odległość od <b>szkoły</b>
				     		<select name="req_distanceFromSchool">
				     			<option value="2">Blisko</option>
				     			<option value="5">Umiarkowana</option>
				     			<option value="15">Daleko</option>
				     		</select>
				     		
				     		</label>
				     	</li>
				     	<li>
				     		<input type="checkbox" name="secutityEstate" value="secutityEstate" id="secutityEstate">
				     		<label for="secutityEstate">Strzeżone osiedle</label>
				     	</li>
				     	<li>
		     				<input type="checkbox" name="playground" value="playground" id="playground">
		     				<label for="playground">Plac zabaw na osiedlu</label>
		     			</li>
		     			<li>
				     		<input type="checkbox" name="elevator" value="elevator" id="elevator">
				     		<label for="elevator">Winda</label>
				     	</li>
				     </ul>
		     </fieldset>
		    </div>
		    <div id="sliderNavigation">
		    	 <input type="submit" value="Wyszukaj" />
		    </div>
  </form>
  <footer>

  </footer>


  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="js/libs/jquery.min.js"><\/script>')</script>
</body>
</html>