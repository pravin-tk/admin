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
<div id="map-canvas" style="width:100%;height:550px;"></div>
aaaa
<input type="hidden" name="latitude" id="latitude" value="" />
<input type="hidden" name="longitude" id="longitude" value="" />






<script type="text/javascript">
    var markers = new Array();

    var lat1 = new Array();
    var lng1 = new Array();

    lat1[0] = "18.5463286";
    lng1[0] = "73.90331390000006";

    lat1[1] = "18.566644";
    lng1[1] = "73.9154069";

    lat1[2] = "18.5619384";
    lng1[2] = "73.9109977";

    lat1[3] = "18.535787";
    lng1[3] = "73.8918897";


    var myMarker, myMarkernew;
    function initialize() {
        var bounds = new google.maps.LatLngBounds();
        ulat = "18.5481";
        ulng = "73.9033";
        var mapOptions = {
            center: new google.maps.LatLng(ulat, ulng),
            zoom: 8
        };
        var myLatlng = new google.maps.LatLng(ulat, ulng);
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
            radius: 2500 // in meters
        };
        cityCircle = new google.maps.Circle(sunCircle)
        cityCircle.bindTo('center', marker, 'position');

        for (i = 1; i <= 3; i++) {
            var position = new google.maps.LatLng(lat1[i], lng1[i]);
            //bounds.extend(position);
            var marker = new google.maps.Marker({
                position: position,
                map: map,
                title: 'viman'
            });
            markers.push(marker);

        }

        var bounds = new google.maps.LatLngBounds();
        //  Go through each...
        for (var i = 0; i < markers.length; i++) {
            bounds.extend(markers[i].position);
        }
        //  Fit these bounds to the map
        map.fitBounds(bounds);
       // map.setCenter(ulat, ulng);

    }

    google.maps.event.addDomListener(window, 'load', initialize);



</script>
</body>
</html>