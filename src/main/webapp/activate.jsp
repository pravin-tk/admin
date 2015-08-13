<%@page import="org.school.admin.model.AdminUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="baseUrl" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">
    <title>Settings</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Font awesome CSS -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <!-- Dashboard CSS -->
    <link href="css/dashboard.css" rel="stylesheet">
    <!-- Date picker CSS -->
    <link href="resources/css/datepicker3.css" rel="stylesheet">
    
   
</head>

<body>
    <!-- Header bar -->
    <nav class="navbar navbar-inverse navbar-fixed-top custom-header">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="resources/images/logo.png" alt="School" title="School">
                </a>
            </div>
            
        </div>
    </nav>
    <!-- /Header bar -->



    <!--Login form starts-->

    <div class="panel panel-default login-form">
        <div class="panel-heading">
       
            <h3 class="panel-title">Please sign in</h3>
        </div>
         <div id="error"></div>
        <div class="panel-body">
        <%
			session = request.getSession(false);    
        AdminUser registration = new AdminUser();
      
        	if(!session.equals(null))
        	{
        		if(!session.getAttribute("uname").equals(null))
        		{
        			registration = (AdminUser) session.getAttribute("uname");
        		}
        	}
        %>
            <form action="" method="post">
                        	<div class="form-group">
            		<label for="username">New password</label>
            		<input type="password" name="npassword" id="npassword" class="form-control" placeholder="Enter your new password"/>
            		
            	</div>
                <div class="form-group">
                	<label for="Password">Confirm password</label>
                	<input type="password" name="cpassword" id="cpassword" class="form-control" placeholder="Please confirm your new password">
                
                </div>
                <input type="button" class="btn btn-success" id="activate" value="Activate"/> 
            </form>
        </div>
    </div>

    <!--Login form ends-->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-1.8.0.min.js"></script>
    <!-- script src="js/bootstrap.min.js"></script-->
    <!-- datepicker Js -->
    
    <!-- Validation Js -->
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/validation.js"></script>
    <script type="text/javascript">
    	$('#activate').click(function(){
    		var newPassword = $('#npassword').val();
    		var confirmPassword = $('#cpassword').val();
    		
    		if(newPassword == confirmPassword)
    			{
	    		var id = <%out.print(registration.getId());%>;
	    		$.post('webapi/validate/activate',{id : id, password: $("#cpassword").val()}, function(data){
	    			window.location.href="${baseUrl}/hometab.jsp";	
	    		});
    		}
    		else
   			{
   				alert("Password not match");
   			}
    	});
    	
    </script>
</body>

</html>