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