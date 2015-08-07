<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Distribution</title>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?libraries=places,geometry,drawing"></script>
</head>
<body>
distribution test
<div id="map-canvas" style="width:100%;height:550px;"></div>
aaaa
                    <input type="hidden" name="latitude" id="latitude" value="" />
                    <input type="hidden" name="longitude" id="longitude" value="" />


                



<script type="text/javascript">
           
            ulat = "18.5463286";
            ulng = "73.90331390000006";
  function initialize() {
	  
	   //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  //fetch via ajax all school lat long
 	<%--  var cityId = <%out.print(session.getAttribute("cityid"));%> 
// 	  $.get("webapi/school/getlatlong/"+cityId,{},function(data){
		  
// 	  });--%>
	  
	  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        var mapOptions = {
          center: new google.maps.LatLng(ulat, ulng),
          zoom: 8
        };
        var myLatlng = new google.maps.LatLng(ulat,ulng);
        var map = new google.maps.Map(document.getElementById('map-canvas'),
          mapOptions);
        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            title: 'Hello World!'
        });
        var drawingManager = new google.maps.drawing.DrawingManager({
          drawingMode: google.maps.drawing.OverlayType.MARKER,
          drawingControl: true,
          drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,
            drawingModes: [
              google.maps.drawing.OverlayType.MARKER,
              google.maps.drawing.OverlayType.CIRCLE,
              google.maps.drawing.OverlayType.POLYGON,
              google.maps.drawing.OverlayType.POLYLINE,
              google.maps.drawing.OverlayType.RECTANGLE
            ]
          },
          markerOptions: {
            icon: 'images/beachflag.png'
          },

        });
      // Define a symbol using SVG path notation, with an opacity of 1.
    var lineSymbol = {
            path: 'M 0,-1 0,1',
            strokeOpacity: 1,
            scale: 4
    };
       var sunCircle = {
        strokeColor: "#c3fc49",
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: "#1E90FF",
        fillOpacity: 0.35,
         icons: [{
            icon: lineSymbol,
             offset: '0',
            repeat: '20px'
         }],
        map: map,
        center: myLatlng,
        radius: 110000 // in meters
    };
    cityCircle = new google.maps.Circle(sunCircle)
    cityCircle.bindTo('center', marker, 'position');
}

google.maps.event.addDomListener(window, 'load', initialize);



</script>
</body>
</html>