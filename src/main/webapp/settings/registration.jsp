<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.AdminUserRole"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.BusInfo"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
 <%
		 session = request.getSession(false);
		 AdminUser registration5 = new AdminUser();
		 List<AdminUser> adminUsers = null;
		 SchoolDAOImp adminUserid = null;
		 if(session!=null)
		 {
		 	if(session.getAttribute("uname") != null)
		 	{
		 			registration5  = (AdminUser)session.getAttribute("uname");
		 				System.out.println();
		 				System.out.println("user id : "+registration5.getId());
		 				System.out.println("UserAdminRoleName : "+registration5.getAdminUserRole().getId());
		 	}
		  	adminUserid = new SchoolDAOImp();
		 	adminUsers = adminUserid.getAdminUserById(registration5.getAdminUserRole().getId());
		 }	
%>         
 	<div class="col-sm-12 col-md-12  main">
      	  <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>Admin User</h2>
                     <p>Here you can add admin user.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>Admin User</a>
                     <table class="table table-striped table-bordered" id="contacts-table">
                         <thead>
                             <tr>
                                 <th>Id</th>
                                 <th>Admin User Name</th>
                                  <th>Admin User Mobile No.</th>
                                   <th>Admin User Email</th>
                                  <th>Admin User Role</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
 								int country_size = adminUsers.size(); 
                        		
 								for(int i=0; i < country_size; i++){
 									 
 									%> 
								 <tr>
									<td> <%out.print(adminUsers.get(i).getId());%>    </td>
									<td> <%out.print(adminUsers.get(i).getName());%>    </td>
									<td> <%out.print(adminUsers.get(i).getMobile());%>    </td>
									<td> <%out.print(adminUsers.get(i).getEmail());%>    </td>
									<td> <%out.print(adminUsers.get(i).getAdminUserRole().getRoleName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editAdminUser(<% out.print(adminUsers.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>
								<%
 								 }
                        		
								%>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>Admin User</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Admin User</h4>
                    <div id="error"></div>
					<form method="post" action=""  class="form-horizontal" id="schoolAddForm">
					<input type="hidden" value="0" id="status"/>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="basic" aria-labelledby="basic-tab">
                            <h3>Registration Information</h3>
                            <div class="alert alert-danger" role="alert" style="display:none;" id="message_body">
							  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							  	<span class="sr-only">Error:</span>
							  	<span id="output"></span>
							</div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Registration Name">Name*</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="registartion_name" id="registration_name" placeholder="Name of new joined person">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Plot No.">Email*</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="registration_email" id="registration_email" placeholder="Email id of new join person.">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Password*</label>
                                <div class="col-sm-6">
                                <input data-brackets-id="3402" type="password" class="form-control" name="registration_password" id="registration_password" placeholder="Password of new join person.">
                                                                  </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Mobile No.*</label>
                                <div class="col-sm-6">
                                    <input type="text" name="registration_mobileno" class="form-control" id="registration_mobleno" maxlength=10 placeholder="Mobile number of new join person">
                                </div>
                            </div>
                            <%
                            
                            if(registration5.getAdminUserRole().getId() == 1)
                            {
                            	SchoolDAOImp adminUserRole = new SchoolDAOImp();
                            	List<AdminUserRole> adminUserRoles = adminUserRole.getAdminUserRole();
                            	
                            %>
                            <div class="form-group">
								<label class="col-sm-2 control-label">User Type</label>
									<div class="col-sm-6">
										<select id="user_type" name="user_type" class="form-control">
											<option value="">-- Select User Type --</option>
                            <%
                            	for(int y=0;y<adminUserRoles.size();y++)
                            	{
                            %>
                              
											<option value="<%out.print(adminUserRoles.get(y).getId());%>">
											<%out.print(adminUserRoles.get(y).getRoleName());%></option>
								<%
								
                            	}
								%>			
										</select>
									</div>
							<div class="col-sm-4">
								<div class="tooltip custom-tool-tip right">
									<div class="tooltip-arrow"></div>
										<div class="tooltip-inner">This is to select the type of the user for eg:- Admin or Sales executive or data collector.</div>
									</div>
								</div>
							</div>
                          <%
                            }
                            
                            else
                            {
                          %>
                          <input type="hidden" id="user_type" value = "<%out.print(registration5.getAdminUserRole().getId());%>"/>
                          <%
                            }
                          %>  
                            <input type="hidden" value="<%out.print(registration5.getId());%>" id="registration_id"/>
                            <div class="form-group">
                                <div class="col-sm-2 col-sm-offset-2">
                                    <button type="button" id="registration" class="btn btn-success">Save</button>
                                      <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                                    
                                </div>
                            </div>
                        </div>            
		   			</div>
                </form>
              	</div>
          	</div>
 	</div>
    <%@include file="../footer.jsp" %>
	  <script type="text/javascript">
	  $("#registration").click(function()
	    		{
	    		 	$.post("../webapi/school/saveuser",{registartion_name : $("#registration_name").val(),
	    		 		registration_email : $("#registration_email").val(), 
	    		 		registration_password : $("#registration_password").val(), 
	    		 		registration_mobleno : $("#registration_mobleno").val(),
	    		 		user_type : $("#user_type").val(),
	    		 		status : $("#status").val()
	    		 	},function(data){
	    		 			if(data.status == 1)
		    				{
		    					window.location.href = "${baseUrl}/settings/registration.jsp";
		    				}
		    			else
		    				alert(data.message);
	    		 	},'json');   
	    		});
	    	
			function editAdminUser(id){
				window.location.href = "${baseUrl}/settings/editadminuser.jsp?id="+id;
			}
  	</script>