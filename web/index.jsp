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
   <% 
   if (session.getAttribute("cena1min") == null) {

        session.setAttribute("cena1min", "0");
        session.setAttribute("cena2min", "50000");
        session.setAttribute("cena3min", "150000");
        session.setAttribute("cena4min", "350000");
        session.setAttribute("cena5min", "600000");
        
        session.setAttribute("cena1max", "50000");
        session.setAttribute("cena2max", "150000");
        session.setAttribute("cena3max", "350000");
        session.setAttribute("cena4max", "600000");
        session.setAttribute("cena5max", "2000000");
        
        session.setAttribute("metraz1min", "0");
        session.setAttribute("metraz2min", "18");
        session.setAttribute("metraz3min", "35");
        session.setAttribute("metraz4min", "75");
        session.setAttribute("metraz5min", "135");
        
        session.setAttribute("metraz1max", "18");
        session.setAttribute("metraz2max", "35");
        session.setAttribute("metraz3max", "75");
        session.setAttribute("metraz4max", "135");
        session.setAttribute("metraz5max", "400");
        
        session.setAttribute("odleglosc1min", "0");
        session.setAttribute("odleglosc2min", "100");
        session.setAttribute("odleglosc3min", "300");
        session.setAttribute("odleglosc4min", "1000");
        session.setAttribute("odleglosc5min", "5000");
        
        session.setAttribute("odleglosc1max", "100");
        session.setAttribute("odleglosc2max", "300");
        session.setAttribute("odleglosc3max", "1000");
        session.setAttribute("odleglosc4max", "5000");
        session.setAttribute("odleglosc5max", "20000");
     }
  %>
  
  <header>
	  <h1>Wyszukiwarka mieszkań</h1>
  </header>
  <div class="navigationBar">
  	  <h2 class="whereAmI">
  		  <b>1. Personalizacja &raquo;</b>
  	  </h2>
  </div>
  <div id="sliderNavigation">
     <a href="/Fuzzy/edit.jsp">Edycja przedziałów</a>
  </div>
  <form method="post" action="fuzzyLogic">
		  <div id="main"><br>
			  <fieldset>
			  	<legend>Jaki przedział cenowy mieszkania Cię interesuje ?</legend><br>		
			  			<div><select name="budget">
                                                        <option value="1">Bardzo Tanie</option>
				     			<option value="2">Tanie</option>
				     			<option value="3">Normalne</option>
                                                        <option value="4">Drogie</option>
                                                        <option value="5">Bardzo Drogie</option>
				     			</select>
                                                <input type="radio" name="group1" value="koniecznaKasa" checked> Warunek konieczny <br> </div>
			  </fieldset>
		   </div>
			<div id="main"><br>
			  <fieldset>
			  	<legend>Jaka powierzchnia Cię interesuje ?</legend><br>
                              <div><select name="area">
                                                        <option value="1">Bardzo Mała</option>
				     			<option value="2">Mała</option>
				     			<option value="3">Normalna</option>
                                                        <option value="4">Duża</option>
                                                        <option value="5">Bardzo Duża</option>
				     			</select>
                                <input type="radio" name="group1" value="koniecznaPowierzchnia"> Warunek konieczny <br> </div>
			  </fieldset>
                        </div>
        <div id="main"><br>
                          <fieldset>
                              <legend>Kwerenda Warunkowa <input type="checkbox" name="closeToCenter" value="closeToCenter" id="closeToCenter"></legend><br>
				     		
				     		<label for="closeToCenter">Odległość od <b>centrum</b> 
				     			<select name="req_distanceFromCenter">
                                                        <option value="1">Bardzo Blisko</option>
				     			<option value="2">Blisko</option>
				     			<option value="3">Umiarkowanie</option>
                                                        <option value="4">Daleko</option>
                                                        <option value="5">Bardzo Daleko</option>
				     			</select>
				     		</label>
                                                
                          </fieldset>
                        </div>
                         <div id="main"><br>
                <fieldset>
                    <legend>Której t-normy i s-normy użyć ?</legend><br>
                    T-norma: 
                        <select name="tnorm">
                        <option value="minimum">Minimum</option>
                        <option value="iloczyn">Iloczyn</option>
                        <option value="t_lukasiewicz">T-norma Łukasiewicza</option>
                        </select>
                    S-norma:
                        <select name="snorm">
                        <option value="maximum">Maximum</option>
                        <option value="suma">Suma probabilistyczna</option>
                        <option value="s_lukasiewicz">S-konorma Łukasiewicza</option>
                        </select>
                </fieldset>
            </div>  `   
		   <div id="main"><br>
		     <fieldset>
                         <legend>Cechy Twojego wymarzonego mieszkania: <br><b>(nie wpływa na zgodność w zapytaniach bipolarnych!)</b> </legend><br>
		     		<ul>
		     			<li>
		     				<input type="checkbox" name="garage" value="garage" id="garage">
		     				<label for="garage">Garaż</label>
		     			</li>
						<li>
				     		<input type="checkbox" name="closeToSchool" value="closeToSchool" id="closeToSchool">
				     		<label for="closeToSchool">Odległość od <b>szkoły</b>
				     		<select name="req_distanceFromSchool">
				     			<option value="1">Blisko</option>
				     			<option value="2">Umiarkowana</option>
				     			<option value="3">Daleko</option>
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/libs/jquery.min.js"><\/script>')</script>
    </body>
</html>
