/* custom number validation script */
function isNumber(evt, element) {

    var charCode = (evt.which) ? evt.which : event.keyCode

    if (
        (charCode != 45 || $(element).val().indexOf('-') != -1) &&      // “-” CHECK MINUS, AND ONLY ONE.
        (charCode != 46 || $(element).val().indexOf('.') != -1) &&      // “.” CHECK DOT, AND ONLY ONE.
        (charCode < 48 || charCode > 57))
        return false;

    return true;
}    
/* end */

/* Only number validation */
function checkNumber(evt, element)
{
	/*evt = (evt) ? evt : window.event
	var charCode = (evt.which) ? evt.which :evt.keyCode
	if(charCode > 31 && (charCode>57  ||   charCode < 48)){
		return false;
	}		  
	return true;
	*/
	var charCode = (evt.which) ? evt.which : event.keyCode

    if (charCode > 31 &&(
        (charCode != 45 || $(element).val().indexOf('-') != -1) &&      // “-” CHECK MINUS, AND ONLY ONE.
        (charCode != 46 || $(element).val().indexOf('.') != -1) &&      // “.” CHECK DOT, AND ONLY ONE.
        (charCode < 48 || charCode > 57))){
        return false
    }
	return true;
}
/* end */

/**
 * Convert time from 12hr to 24hr format
 * @return 24hr format time
 */
function getTime(str){
	 // console.log("Time : "+str);
	   var a = str;
	   var b = " ";
	   var n = a.indexOf(":");
	   var position = n+3;
	   var output = [a.slice(0, position), b, a.slice(position)].join('');
 	   var time1 = output;
	   var hours = Number(time1.match(/^(\d+)/)[1]);
	   var minutes = Number(time1.match(/:(\d+)/)[1]);
	   var AMPM = time1.match(/\s(.*)$/)[1];
	   
	   if ((AMPM == "PM" || AMPM == "pm") && hours < 12) hours = hours + 12;
	   if ((AMPM == "AM" || AMPM == "am") && hours == 12) hours = hours - 12;
	   var sHours = hours.toString();
	   var sMinutes = minutes.toString();
	   if (hours < 10) sHours = "0" + sHours;
	   if (minutes < 10) sMinutes = "0" + sMinutes;
	   var time2 = sHours+":"+sMinutes+":00";
	   console.log("Time : "+time2);
	   return time2;
	}
/* end */

/**
 * Converting 24hr time format into 12hr format
 * @return 12hr format time
 */
function hours_am_pm(time) {
    var hours = time[0] + time[1];
    var min = time[3] + time[4];
    if (hours < 12) {
        return hours+':'+min+'am';
    } else {
    	 if(hours == 12){
             hours = hours;
         }else{
         hours=hours - 12;
         hours=(hours.length < 10) ? hours:'0'+hours;
         }
        return hours+':'+min +'pm';
    }
}
/*end*/