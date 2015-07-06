<%-- <!-- Left Nav bar -->     
<div class="container-fluid">	        
  <div class="row">	              
    <div class="col-sm-3 col-md-2 sidebar">	                    
      <ul class="nav nav-sidebar">	                          
        <li>        
        <a href="${baseUrl}/home.jsp">          
          <i class="fa fa-bar-chart"></i> Dashboard</a>	                          
        </li>	                          
        <!-- start general -->                 
        <div class="panel-group" id="accordion">				               
          <!-- Panel  1 --> 				               
          <div class="panel panel-default">				                     
            <div class="panel-heading">				                                                          
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed">	                  
                <i class="fa fa-map-marker"></i> General 				                </a>				                                
            </div>				                     
            <div id="collapseOne" class="panel-collapse collapse">				                           
              <div class="panel-body">				                                     
                <a href="${baseUrl}/general/country.jsp">
                  <i class="fa fa-pencil-square-o"></i>Country</a>                           
              </div>				                            
              <div class="panel-body">                       
                <a href="${baseUrl}/general/state.jsp"> 
                  <i class="fa fa-pencil-square-o"></i>State</a>              
              </div>	                 
              <div class="panel-body">                    
                <a href="${baseUrl}/general/district.jsp">
                  <i class="fa fa-pencil-square-o"></i>District</a>              
              </div>	                
              <div class="panel-body">             
                <a href="${baseUrl}/general/tehsil.jsp"> 
                  <i class="fa fa-pencil-square-o"></i>Tehsil</a>              
              </div>	                
              <div class="panel-body">                
                <a href="${baseUrl}/general/city.jsp">
                  <i class="fa fa-pencil-square-o"></i>City</a>              
              </div>	               
              <div class="panel-body">                
                <a href="${baseUrl}/general/locality.jsp">
                  <i class="fa fa-pencil-square-o"></i>Locality</a>              
              </div>		             				                     
            </div>				               
          </div>    				            
        </div>	            
        <!-- start general -->                           
        <!-- start setting -->            
        <div class="panel-group" id="accordion">				               
          <!-- Panel  1 --> 				               
          <div class="panel panel-default">				                     
            <div class="panel-heading">				                                                         
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed">	                  
                <i class="fa fa-gear"></i> Setting 				                </a>				                        
            </div>				                     
            <div id="collapseTwo" class="panel-collapse collapse">				                           
              <div class="panel-body">
              	<a href="${baseUrl}/settings/accessory.jsp">
              		<i class="fa fa-pencil-square-o"></i>Accessory</a>
              </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/areaunit.jsp">
		      		<i class="fa fa-pencil-square-o"></i>AreaUnit</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/boardtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Board Type</a>
		      </div>
		      
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/certificatetype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Certificate Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/educationtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Education Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/examtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Exam Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/facultytype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Faculty Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/feetype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Fee Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/leavetype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Leave Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/mediumtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Medium Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/notificationtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Notification Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/occupationtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Occupation Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/paymentmode.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Payment Mode</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/ratingcategorytype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Rating Category Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/role.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Role</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/secondaryrole.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Secondary Role</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/schoolcategorytype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>School Category Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/schoolclassificationtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>School Classification Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/teaching-approach-type.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Teaching Approach Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/schooltype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>School Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/standardtype.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Standard Type</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/settings/subject.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Subject </a>
		      </div>	              		             				                     
            </div>				               
          </div>    				            
        </div>	                         
        <!-- end setting -->                   
        <!-- start facility    -->                                  
        <div class="panel-group" id="accordion">				               
          <!-- Panel  1 --> 				               
          <div class="panel panel-default">				                     
            <div class="panel-heading">				                                                         
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" class="collapsed">	               
                <i class="fa fa-laptop"></i>    Facility  </a>				          		                     
            </div>				                     
            <div id="collapseThree" class="panel-collapse collapse">				                           
              <div class="panel-body">				                                    
                <a href="${baseUrl}/facility/safety-category.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Safety Category</a>				                           
              </div>	          
              <div class="panel-body">                
                <a href="${baseUrl}/facility/safety-cat-item.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Safety Category Item</a>              
              </div>	                
              <div class="panel-body">                
                <a href="${baseUrl}/facility/infra-category.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Infrastructure Category</a>                
              </div>	
              <div class="panel-body">                
                <a href="${baseUrl}/facility/activity-category.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Activity Category</a>              
              </div>	               
              <div class="panel-body">                
                <a href="${baseUrl}/facility/activity-cat-item.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Activity Category Item         </a>                    
              </div>			             				                     
            </div>				               
          </div>    				            
        </div>	           
        <!-- end facility  -->  
        <!-- start Transport  -->
                                     
        <div class="panel-group" id="accordion">				               
          <!-- Panel  1 --> 				               
          <div class="panel panel-default">				                     
            <div class="panel-heading">				                                                         
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour" class="collapsed">	               
                <i class="fa fa-bus"></i>    Transport  </a>				          		                     
            </div>				                     
            <div id="collapseFour" class="panel-collapse collapse">	
            <div class="panel-body">
		      	<a href="${baseUrl}/transport/businfo.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Bus Info</a>
		      </div>
		      <div class="panel-body">
		      	<a href="${baseUrl}/transport/busstop.jsp">
		      		<i class="fa fa-pencil-square-o"></i>Bus Stop</a>
		      </div>			                           
              <div class="panel-body">				                                    
                <a href="${baseUrl}/transport/route.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Route</a>				                           
              </div>	          
              <div class="panel-body">                
                <a href="${baseUrl}/transport/route-stop.jsp">                  
                  <i class="fa fa-pencil-square-o"></i>Route Stop</a>              
              </div>	                
              		             				                     
            </div>				               
          </div>    				            
        </div>	           
      
        <!-- end transport -->               	                    
      </ul>	              
    </div>                 
    <!-- /Left Nav bar -->  --%>