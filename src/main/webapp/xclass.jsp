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
    <style type="text/css">
    </style>
   
</head>

<body>
    <!-- Header bar -->
    <nav class="navbar navbar-inverse navbar-fixed-top custom-header">
        <div class="container-fluid">
            <div class="navbar-header">
                
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
          <p id="error" class="bg-danger nopadding" ></p>
        <div class="panel-body">
       
            <form action="" method="post">
            	
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

    <!--Login form ends-->



   <%@ include file="footer.jsp" %>
    <script type="text/javascript">
    	$('#save').click(function(){
    		
    		$.post('webapi/validate/info',{email: $("#uname").val(), password: $("#upassword").val()}, function(data){
    			var success = data.status;
    			var status =parseInt(success);
    			if(status==1)
    				{
    				$('#error').empty();
    					window.location.href="home.jsp";	
    				}
    			
    			else if(status == 0)
    				{
    					$('#error').empty();
    					window.location.href="activate.jsp";
    				}
    			else
    				{
    			$('#error').empty();
    		
    			$('#error').append(data.errorMessage.errormsg);
    				}
    			
    		},'json');
    		
    		
    	});
    </script>
</body>

</html>