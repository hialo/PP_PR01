<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      body {  background: #ffffff;
              color: #333333;
              margin: 0 auto;
              max-width: 960px;
              height: 800px;
              overflow-x: hidden; /* prevents box-shadow causing a horizontal scrollbar in firefox when viewport < 960px wide */
              -moz-box-shadow: 0 0 0.3em #255b17;
              -webkit-box-shadow: 0 0 0.3em #255b17;
               box-shadow: 0 0 0.3em #255b17; 
            }

      #map_canvas { height: 600px; }
    </style>
    
    <title>Welcome to SAHH-WEB</title>

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAaNZesYsy897xO9LhKRxNYA-3T7infu10&sensor=true">
    </script>
    <script type="text/javascript">

      function initialize() 
      {
        
        var mapOptions = {
          center: new google.maps.LatLng(-15.815895, -47.916047),
          zoom: 10,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
      
        var map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
        
        <g:each in="${ocorrenciaList}" status="i" var="ocorrencia">
          <g:if test="${ocorrencia.status != 'Finalizada'}">
              var point${ocorrencia.id} = new google.maps.LatLng(${ocorrencia.latitude},${ocorrencia.longitude})
              var marker${ocorrencia.id} = new google.maps.Marker({position: point${ocorrencia.id}, map: map, title: 'Paciente = ${ocorrencia.paciente}'});
          </g:if>
        </g:each>

        window.setTimeout('location.reload()', 15000);
      }

    </script>
  </head>
  <body onload="initialize()">
    <div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'heart.png')}" alt="Grails"/></a></div>
    <div class="nav" role="navigation">
      <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="Ocorrencia List" args="[entityName]" /></g:link></li>
      </ul>
    </div>
        <div id="map_canvas" style="width:70%; height:600px; top: 2%;bottom: 10%; left:15%"></div>
    <div class="footer" role="contentinfo"></div>
  </body>
</html>