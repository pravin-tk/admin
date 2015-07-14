	 <%@page import="org.school.admin.model.AdminUser"%>
<% 
                        
                        session = request.getSession(false);
						int loggedin_id = 0;
                        AdminUser leftNav = new AdminUser();
                        if(session!=null)
                        {
                        	System.out.print("session is not null..");
                        	if(session.getAttribute("uname") != null)
                        	{
                        		System.out.print("session attribute is not null..");
                        		leftNav  = (AdminUser)session.getAttribute("uname");
                        		loggedin_id = leftNav.getAdminUserRole().getId();
                        		//out.print(leftNav.getName());
                        	}
                        }
                        
                        %>
	
	<div id="footer_higher" class="col-sm-12 col-md-12 main site-map">
		<div class="nav">
		<label class="col-sm-11 control-label nav panel panel-heading">School Search</label>
		<div class="col-sm-1">
			<a href="javascript:closeSiteMap();" class="btn btn-danger icon-btn"><i class="fa fa-close"></i></a>
		</div>
		</div>
		<div id="footer-content">
		<h4>Registration</h4>
			<ul class="nav">
			 <% if(loggedin_id == 1) {%>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/settings/adminuserrole.jsp">
						<i class="fa"></i>Admin User Role
				</a></li>
				<%} %>
		</ul>
		<h4>Address Configuration</h4>
			<ul class="nav">
			
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/general/country.jsp">
						<i class="fa"></i>Country
				</a></li>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/general/state.jsp">
						<i class="fa"></i>State
				</a></li>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/general/district.jsp">
						<i class="fa"></i>District
				</a></li>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/general/tehsil.jsp">
						<i class="fa"></i>Tehsil
				</a></li>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/general/city.jsp">
						<i class="fa"></i>City
				</a></li>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/general/locality.jsp">
						<i class="fa"></i>Locality
				</a></li>

			</ul>
			<h4>Transport</h4>
			<ul class="nav">
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/transport/businfo.jsp">
						<i class="fa"></i>Bus Info
				</a></li>
				<li class="col-sm-2"><a  class=" footer-link" href="${baseUrl}/transport/busstop.jsp">
						<i class="fa"></i>Bus Stop
				</a></li>
				<li class="col-sm-2"><a class=" footer-link" href="${baseUrl}/transport/route.jsp">
						<i class="fa"></i>Route
				</a></li>
				<li class="col-sm-2"><a class=" footer-link"
					href="${baseUrl}/transport/route-stop.jsp">
					 <i class="fa"></i>Route
						Stop
				</a></li>

			</ul>

			<h4>Creation</h4>
			<ul class="nav">
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/accessory.jsp"> <i class="fa"></i>Accessory
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/settings/areaunit.jsp">
						<i class="fa"></i>AreaUnit
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/boardtype.jsp"> <i class="fa"></i>Board
						Type
				</a></li>
				
				<li class="col-sm-2"><a href="${baseUrl}/settings/feetype.jsp">
						<i class="fa"></i>Fee Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/leavetype.jsp"> <i class="fa"></i>Leave
						Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/medium-of-instruction.jsp"> <i class="fa"></i>Medium
						of instruction
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/notificationtype.jsp"> <i class="fa"></i>Notification
						Type
				</a></li>
				
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/paymentmode.jsp"> <i class="fa"></i>Payment
						Mode
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/ratingcategorytype.jsp"> <i
						class="fa"></i>Rating Category Type
				</a></li>
				
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/type-of-school.jsp"> <i
						class="fa"></i>Type of School 
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/schoolclassificationtype.jsp"> <i
						class="fa"></i>School Classification 
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/teaching-approach-type.jsp"> <i
						class="fa "></i>Teaching Approach Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/school-management.jsp"> <i class="fa"></i>School
						Management
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/standardtype.jsp"> <i class="fa"></i>Standard
						Type
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/settings/subject.jsp">
						<i class="fa"></i>Subject
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/settings/streamtype.jsp">
						<i class="fa"></i>Stream type
				</a></li>

			</ul>

			<h4>Safety / Infrastructure</h4>
			<ul class="nav">
				<li class="col-sm-2"><a href="${baseUrl}/facility/safety-category.jsp"> <i
						class="fa "></i>Safety Category
				</a></li>


				<li class="col-sm-2"><a href="${baseUrl}/facility/safety-cat-item.jsp"> <i
						class="fa "></i>Safety Category Item
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/facility/infra-cat.jsp"> <i
						class="fa "></i>Infrastructure Category
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/facility/infra-category-item.jsp"> <i
						class="fa "></i>Infrastructure Category Item
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/facility/activity-category.jsp"> <i
						class="fa "></i>Activity Category
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/facility/activity-cat-item.jsp"> <i
						class="fa "></i>Activity Category Item
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/paymentmode.jsp"> <i class="fa"></i>Payment
						Mode
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/ratingcategorytype.jsp"> <i
						class="fa"></i>Rating Category Type
				</a></li>
			</ul>
		</div>
		<div class="nav">
			<label class="col-sm-11 control-label nav panel panel-heading">PTC</label>
			<div class="col-sm-1">
			</div>
			<ul class="nav">
			<li class="col-sm-2"><a
					href="${baseUrl}/settings/educationtype.jsp"> <i class="fa"></i>Teacher Qualification
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/settings/examtype.jsp">
						<i class="fa"></i>Exam Type
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/settings/ptc-primary-role.jsp">
						<i class="fa"></i>PTC Primary Role
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/ptc-secondary-role.jsp"> <i class="fa"></i>PTC Secondary
						Role
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/certificatetype.jsp"> <i class="fa"></i>Certificate
						Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/paymentmode.jsp"> <i class="fa"></i>Payment
						Mode
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/ratingcategorytype.jsp"> <i
						class="fa"></i>Rating Category Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/parent-occupation.jsp"> <i class="fa"></i>Parent Occupation
						
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/blood-group.jsp"> <i class="fa"></i>Blood Group
						
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/cast.jsp"> <i class="fa"></i>Cast
						
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/leavetype.jsp"> <i class="fa"></i>Leave
						Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/notificationtype.jsp"> <i class="fa"></i>Notification
						Type
				</a></li>
				<li class="col-sm-2"><a
					href="${baseUrl}/settings/facultytype.jsp"> <i class="fa"></i>Faculty
						Type
				</a></li>
				<li class="col-sm-2"><a href="${baseUrl}/settings/feetype.jsp">
						<i class="fa"></i>Fee Type
				</a></li>
				</ul>
				
		</div>
	</div>
<div style="min-height:50px;">
&nbsp;
</div>
<div id="footer_lower">
	<div id="footer_info">
		<div id="copyright">
			&copy;  All Rights Reserved.
		</div>
		<div id="attr">
			By <a href="http:/return-true.com/themes/">edbuddy</a>.
		</div>
		<div class="clear"></div>
	</div>
</div>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${baseUrl}/js/bootstrap.min.js"></script>
<!-- datepicker Js -->
<script src="${baseUrl}/js/bootstrap-datepicker.js"></script>
<!-- Timepicker Js -->
<script src="${baseUrl}/js/bootstrap-timepicker.min.js"></script>
<!-- datatable Js -->
<script src="${baseUrl}/js/jquery.dataTables.min.js"></script>
<script src="${baseUrl}/js/dataTables.bootstrap.js"></script>
<!-- Validation Js -->
<script src="${baseUrl}/js/jquery.validate.min.js"></script>
<script src="${baseUrl}/js/validation.js"></script>
<script src="${baseUrl}/js/custom.js"></script>

<script>
	function toggleMenu(){
		$("#footer_higher").slideToggle("slow").animate({
		    position: "absolute",
		    left: "0",
		  }, 100, function() {
		    // Animation complete.
		  });
	}
	
	function closeSiteMap(){
		$("#footer_higher").slideToggle("slow").animate({
		    position: "absolute",
		    left: "0",
		  }, 100, function() {
		    // Animation complete.
		  });
	}
    
    </script>
</body>
</html>