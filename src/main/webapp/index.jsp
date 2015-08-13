<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="baseUrl" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<%@page import="org.school.admin.model.AdminUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="java.util.List"%>
<%
	List<City> city_list = null; 
	int city_size =0;
	city_list = new CityNamesService().getAllCityNames();
	city_size = city_list.size(); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">
    <title>Edbuddy Admin Control Panel</title>
    <!-- Bootstrap core CSS -->
    <link href="${baseUrl}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font awesome CSS -->
    <link href="${baseUrl}/css/font-awesome.min.css" rel="stylesheet">
    <!-- Dashboard CSS -->
    <link href="${baseUrl}/css/dashboard.css" rel="stylesheet">
    <!-- Date picker CSS -->
    <link href="${baseUrl}/css/datepicker3.css" rel="stylesheet">
    <script src="${baseUrl}/js/jquery.min.js"></script>
</head>

<body>
    <!-- Header bar -->
    <nav class="navbar navbar-inverse navbar-fixed-top custom-header">
        <div class="container-fluid">
            <div class="navbar-header">
<!--                 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> -->
<!--                     <span class="sr-only">Toggle navigation</span> -->
<!--                     <span class="icon-bar"></span> -->
<!--                     <span class="icon-bar"></span> -->
<!--                     <span class="icon-bar"></span> -->
<!--                 </button> -->
                <a class="navbar-brand" href="#">
                    <img src="images/edbuddy-logo.jpg" alt="school" title="school" width="50" height="50">
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              
            </div>
        </div>
    </nav>
    <!-- /Header bar -->
    <!--Login form starts-->
	<div class="vertical-center">
    <div class="panel panel-default login-form">
        <div class="panel-heading">
       
            <h3 class="panel-title">Please sign in</h3>
        </div>
          <p id="error" class="bg-danger nopadding" ></p>
        <div class="panel-body">
       
            <form action="" method="post">
            	<div class="form-group">
            		<label for="username">Select City</label>
            		<select id="cityid" name="cityid" class="form-control">
	                    <%
	                    String search_tehsil_selected = ""; 
	                    for(int i=0; i < city_size; i++){
	                    	out.print("<option value='"+city_list.get(i).getId()+"' >"+city_list.get(i).getName()+"</option>");
	                    }
	                    %>
                    </select>
            		
            	</div>
            	<div class="form-group">
            		<label for="username">User ID</label>
            		<input type="text" name="uname" id="uname" class="form-control" placeholder="Enter your user name"/>
            		
            	</div>
                <div class="form-group">
                	<label for="Password">Password</label>
                	<input type="password" name="upassword" id="upassword" class="form-control" placeholder="Please enter your password">
                
                </div>
                <input type="button" class="btn btn-success" id="save" value="Login"/> 
            </form>
        </div>
    </div>
    </div>

    <!--Login form ends-->

<%--    <%@ include file="footer.jsp" %> --%>
    <script type="text/javascript">
    
    function login(){
    	$.post('webapi/validate/info',{email: $("#uname").val(), password: $("#upassword").val(), cityid: $("#cityid").val()}, function(data){
			var success = data.status;
			var status =parseInt(success);
			if(status==1)
				{
				$('#error').empty();
					window.location.href="hometab.jsp";	
				}
			
			else if(status == 0)
				{
					$('#error').empty();
					window.location.href="activate.jsp";
				}
			else
				{
			$('#error').empty();
		
			$('#error').append(data.message);
				}
			
		},'json');
    }
    	$('#save').click(function(){
    		login();
    	});
    	
    	
    	$("#upassword").keydown(function (e) {
    		  if (e.keyCode == 13) {
    			  login();
    		  }
    	});
    </script>
</body>

</html>