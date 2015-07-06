<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="baseUrl" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<%@page import="org.school.admin.model.AdminUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${baseUrl}/images/favicon.ico">
    <title>Edbuddy Admin Control Panel</title>
    <!-- Bootstrap core CSS -->
    <link href="${baseUrl}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font awesome CSS -->
    <link href="${baseUrl}/css/font-awesome.min.css" rel="stylesheet">
    <!-- Dashboard CSS -->
    <link href="${baseUrl}/css/dashboard.css" rel="stylesheet">
    <!-- Time Picker css -->
    <link href="${baseUrl}/css/bootstrap-timepicker.css" rel="stylesheet">
    <!-- Time picker js -->
    <!-- Date picker CSS -->
    <link href="${baseUrl}/css/datepicker3.css" rel="stylesheet">
    <script src="${baseUrl}/js/jquery.min.js"></script>
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
                <a class="navbar-brand" href="${baseUrl}/home.jsp">
                    <img src="${baseUrl}/images/edbuddy-logo.jpg"  height="50" width="50" alt="school" title="school">
                </a>
                 
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-user"></i>  <% 
                        
                        session = request.getSession(false);
                        AdminUser registration = new AdminUser();
                        if(session!=null)
                        {
                        	System.out.print("session is not null..");
                        	if(session.getAttribute("uname") != null)
                        	{
                        		System.out.print("session attribute is not null..");
                     			registration  = (AdminUser)session.getAttribute("uname");
                        		out.print(registration.getName());
                        	}
                        	else
                            {
                            	response.sendRedirect("index.jsp");
                            }
                        }
                        else
                        {
                        	response.sendRedirect("index.jsp");
                        }
                        
                        %> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                           <li><a  href="${baseUrl}/settings/registration.jsp">
								<i class="fa fa-registration"></i>Admin User
							</a></li>
                            <li><a href="${baseUrl}/webapi/validate/logout"><i class="fa fa-sign-out"></i> Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
              <form class="navbar-form navbar-right">
                    <div class="input-group margin-bottom-sm select-country">
                    	<a class="input-group-addon" href="javascript:toggleMenu();"><i class="fa fa-sitemap"></i></a>
                    </div>
                </form>
              
            </div>
        </div>
    </nav>
    <!-- /Header bar -->
    
   
</body>

</html>