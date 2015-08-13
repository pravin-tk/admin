<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>School details</title>
</head>
<body>
<form action="CreateSchoolController" method="post"  enctype="multipart/form-data">
<table >
<tr>
<td>Name of school</td>
<td>
<input type="text" name="name_of_school"/>

</td>
</tr>
<tr><td>Alias Name *</td>
<td>
<input type="text" name="AliasName" required/> </td>
<tr>
<tr><td>Tag Line : </td><td><input type="text" name="tag_line"/></td></tr>
<tr><td>About school :</td><td><textarea name="about_school"></textarea></td></tr>
<tr><td>Logo of school</td><td><input type="file" name="logo_of_school"/></td></tr>
<tr>
<td>Plot No</td>
<td>
<input type="number" name="plot_no"/>
</td>
</tr>

<tr>
<td>Street name</td>
<td>
<input type="text" name="name_of_street" list="street_nos">
<datalist id="street_nos">
</datalist>
</td>
</tr>
<tr><td>Locality</td>
<td>

<input type="text" name="name_of_locality" list="locality_nos" autofocus required>
<datalist id="locality_nos">


</datalist>
</td>
</tr>

<tr><td>Landmark</td>
<td>

<input type="text" name="name_of_landmark" list="landmark_nos" required>
<datalist id="landmark_nos">

</datalist>
</td>
</tr>

<tr><td>Village</td>
<td>

<input type="text" name="name_of_village" list="village_nos" autofocus>
<datalist id="village_nos">


</datalist>
</td>
</tr>

<tr><td>Tehsil</td>
<td>

<input type="text" name="name_of_tehsil" list="tehsil_nos" autofocus>
<datalist id="tehsil_nos">


</datalist>
</td>
</tr>

<tr><td>City</td>
<td>

<input type="text" name="name_of_city" list="city_nos" autofocus required>
<datalist id="city_nos">


</datalist>
</td>
</tr>

<tr><td>District</td>
<td>

<input type="text" name="name_of_district" list="district_nos" autofocus required>
<datalist id="district_nos">


</datalist>
</td>
</tr>

<tr><td>State</td>
<td>

<input type="text" name="name_of_state" list="state_nos" autofocus required>
<datalist id="state_nos">


</datalist>
</td>
</tr>

<tr><td>Country</td>
<td>

<input type="text" name="name_of_country" list="country_nos" autofocus required>
<datalist id="country_nos">


</datalist>
</td>
</tr>
<tr><td>Pincode *</td>
<td>
<input type="text" name="pincode" required/> 
<tr><th><label>Location </label></th></tr>
<tr><td><label>Longitude *</label></td><td><input type="text" name="longitude" required maxlength="15"/></td>
<tr><td><label>Latitude *</label></td><td><input type="text" name="latitude" required maxlength="15"/></td>
<tr><th>Images of School</th></tr>
<tr><td>Select File for upload</td><td><input type="file" name="imageFile1"/></td><td>Image Name</td><td><input type="text" name="imageName1"/></td></tr>
<tr><td>Select File for upload</td><td><input type="file" name="imageFile2"/></td><td>Image Name</td><td><input type="text" name="imageName2"/></td></tr>
<tr><td>Select File for upload</td><td><input type="file" name="imageFile3"/></td><td>Image Name</td><td><input type="text" name="imageName3"/></td></tr>
<tr><td>Select File for upload</td><td><input type="file" name="imageFile4"/></td><td>Image Name</td><td><input type="text" name="imageName4"/></td></tr>
<tr><td>Select File for upload</td><td><input type="file" name="imageFile5"/></td><td>Image Name</td><td><input type="text" name="imageName5"/></td></tr>

</table>
<table id="moreimages">
</table>
<table>
<!-- <table id="createTable"></table> -->
<!-- <table> -->
<!-- <tr><td><input type="button" value="Add More Images" id="AddMoreImages"/></td></tr> -->
<tr><td><input type="submit" value="Create" onclick="CreateSchoolController"/><td>
</tr></table></form>
</body>
<script src="js/imageFileUpload.js"></script>
</html>