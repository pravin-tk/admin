<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>
<%@include file="LeftNav.jsp" %>    
            <!-- Right main content -->
             <%
    	SchoolDAOImp progress = new SchoolDAOImp();
        School school = progress.getSchoolDetails(Integer.parseInt(request.getParameter("school_id"))).get(0);
    	double per = progress.getPer(Integer.parseInt(request.getParameter("school_id")));
    %>
   <style>
   .progress{
   	width:80%;
   }
   </style>
            <div class="col-sm-12 col-md-12  main">
                <h2 class="page-header" style="text-transform: capitalize;"><% out.print(school.getName()); %>, <span><% out.print(school.getLocality().getName()); %></span></h2>
                Configuration <div class="progress">
    <div style="width:100%;background-color:#f5f5f5;height:30px;border:1px solid grey;">
     	<div id="school_progress" style="background-color:green;color:white;width:<%out.print(per);%>%;text-align:center;"><%out.print(per);%>% </div>
    </div>
  </div>
	<div id="validate_div" class="" style="float:right;margin-top:-50px;margin-left:-50px;width:10%;">
		<input type="button" class='btn btn-success icon-btn' style="display: none;"  onclick="verifySchool();" value="Verify" id ="verify"/>
		     	</div> 
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Config</a>
                        
                    </li>
                    
                    <li class="active">Currency</li>
                </ol>
<!--                 <form method="" action="" class="form-horizontal"> -->

                    <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                            <a href="#contact" data-toggle="tab" >contact</a>
                        </li>
                        
	                     <li >
	                            <a href="#school" data-toggle="tab">school</a>
	                        </li>
	                     <li>
                            <a href="#campus" data-toggle="tab" >campus</a>
                        </li>
                         <li>
                            <a href="#school-acheivement" data-toggle="tab">achievements</a>
                        </li>
                         <li>
                            <a href="#school-highlight-tab" data-toggle="tab">Highlights</a>
                        </li>
                         <li>
                            <a href="#school-timeline-tab" data-toggle="tab">Time Line</a>
                        </li>
                        
                         <li>
                            <a href="#old-student-profile" data-toggle="tab">old student profile</a>
                        </li>
                        <li>
                            <a href="#infrastructure" data-toggle="tab">infrastructure</a>
                        </li>
                       
                         <li>
                            <a href="#sales-detail" data-toggle="tab">sales details</a>
                        </li>
                         
                         <li>
                            <a href="#class-detail" data-toggle="tab" >class</a>
                        </li>
                         <li>
                            <a href="#school-pano-image-tab" data-toggle="tab">360<sup>0</sup></a>
                        </li>
                         <li>
                            <a href="#school-image-gallery-tab" data-toggle="tab">Gallery</a>
                        </li>
                        
                        
                    </ul>
                     
                    <div id="myTabContent" class="tab-content">
                      <!-- first tab content -->
                       <div class="tab-pane fade active in" id="contact" aria-labelledby="contact-tab">
                           <h2>Contact Detail</h2>
                               <%@include file = "config-tabs/contact-detail.jsp" %>
                        </div>
                        <!--end first tab content -->
                        <!-- sec tab content -->
                          <div class="tab-pane fade " id="school" aria-labelledby="country-tab">
                            <h2>School Detail</h2>
							<%@include file = "config-tabs/school_detail.jsp" %>
                        </div>                    
                        <!-- end sec tab content -->
                        
                        <!-- third tab content -->     
                       <div class="tab-pane fade " id="campus" aria-labelledby="defaults-tab">
                             <h2>Campus Detail</h2>
							<%@include file = "config-tabs/campus_detail.jsp" %>
                        </div>
                         
                        <!-- third tab content -->
                        
                         <!-- fourth tab content -->                         
                       <div class="tab-pane fade" id="school-acheivement" aria-labelledby="school-acheivement-tab">
                           	<h2>School achievement</h2>
 							<%@include file = "config-tabs/school_acheivement.jsp" %>
                       	</div>
                        <!-- fourth tab content -->
                        
                         <!-- sixth tab content --> 
                         <div class="tab-pane fade" id="school-highlight-tab" aria-labelledby="school-highlight-tab">
                            <h2>School Highlights</h2>
                            <%@include file = "config-tabs/school-highlight.jsp" %>
                        </div>
                                                
                       
                        <!-- sixth tab content -->
                        
                       	<!-- seventh tab content -->                         
                       	<div class="tab-pane fade" id="school-timeline-tab" aria-labelledby="school-timeline-tab">
                            <h2>School TimeLine</h2>
                            <%@include file = "config-tabs/school-timeline.jsp" %>
                        </div>
                        <!-- seventh tab content -->
                        
                         <!-- eighth tab content -->   
                          <div class="tab-pane fade" id="old-student-profile" aria-labelledby="old-student-profile-tab">
                            <h2>Old Student Profile</h2>
                            <%@include file = "config-tabs/old-student-profile.jsp"%>
                        </div>
                                              
                       
                        <!-- eighth tab content -->
                        
                        <!-- ninth tab content -->                         
                       	<div class="tab-pane fade" id="infrastructure" aria-labelledby="country-tab">
                           <h2>Infrastructure Detail</h2>
                              <%@include file="config-tabs/facility.jsp" %>
                       	</div>
                        <!-- ninth tab content -->  
                        <!-- 10th tab content -->                       
                         <div class="tab-pane fade" id="sales-detail" aria-labelledby="country-tab">
                            <h2>Sales Detail</h2>
							<%@include file = "config-tabs/sales_detail.jsp" %>
                        </div>
                        <!-- 10th tab content -->
                        <!-- 11th tab content -->                       
                         <div class="tab-pane fade" id="class-detail" aria-labelledby="class-tab">
                        <h2>Class Detail</h2>
							<%@include file = "config-tabs/class-detail.jsp" %>
                        </div>
                        <!-- 11th tab content -->
                        <!-- 12th tab content -->  
                         <div class="tab-pane fade" id="school-pano-image-tab" aria-labelledby="country-tab">
                            <h2>360<sup>0</sup> Pano</h2>
                              <%@include file = "config-tabs/pano-image-gallary.jsp" %>
                        </div>                     
                        <!-- 12th tab content -->
                        <!-- 13th tab content -->
                        <div class="tab-pane fade" id="school-image-gallery-tab" aria-labelledby="country-tab">
                            <h2>Image Gallery</h2>
                            <%@include file = "config-tabs/image-gallary.jsp" %>
                        </div>
                        <!-- 13th tab content -->
                 </div>
<!--                 </form> -->
            </div>
        
    <!-- /Right main content -->
   
    <%@include file="footer.jsp" %>
<script type="text/javascript">
function updateProgress(school_id){
	$.get("webapi/school/get_school_progress/"+school_id,{},function(data){
		$("#school_progress").html(data+"%");
		$("#school_progress").attr('width',data+"%");
	});
}
function deleteProgress(school_id){
	$.get("webapi/school/get_new_school_progress/"+school_id,{},function(data){
		$("#school_progress").html(data+"%");
		$("#school_progress").attr('width',data+"%");
	});
}
function viewVerify()
{
	var verify = <%out.print(per);%>
	if(verify == 100){
		$("#verify").show();
	}else{
		alert("Hi");
	}
	
}
$("#verfy").click(function(){
	verifySchool();
});
function verifySchool()
{
	var verify = <%out.print(per);%>
	if(verify == 100){
		
		var schoolId = <%out.print(request.getParameter("school_id")); %>
	  $.post("webapi/school/activate",{schoolId:schoolId},function(data){
		  alert(data.message);
	  });	
  }
	
	
}
</script>