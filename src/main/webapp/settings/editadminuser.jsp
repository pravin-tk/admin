
<%@page import="org.school.admin.model.AdminUserRole"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));	
	List<AdminUser> detail = null;
	AdminUser adminUser = null;
	if (id > 0) {
		detail = new SettingsImpl().getAdminUserById(id);
		if(detail.size() > 0)
			adminUser = detail.get(0);
	}
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Settings</a>
                    </li>
                    <li class="active">Admin User</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
                <input type="hidden" id="id" value="<%out.print(id);%>" />
					 <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="basic" aria-labelledby="basic-tab">
                            <h3>Update Admin User</h3>
                            <div class="alert alert-danger" role="alert" style="display:none;" id="message_body">
							  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							  	<span class="sr-only">Error:</span>
							  	<span id="output"></span>
							</div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Update Registration Name">Name*</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" value="<%out.print(adminUser.getName()); %>" name="registartion_name" id="uregistration_name" placeholder="Name of new joined person">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Plot No.">Email*</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" value="<%out.print(adminUser.getEmail());%>" name="registration_email" id="uregistration_email" placeholder="Email id of new join person.">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Password*</label>
                                <div class="col-sm-6">
                                <input data-brackets-id="3402" type="password" class="form-control" value="<%out.print(adminUser.getPassword()); %>" name="registration_password" id="uregistration_password" placeholder="Password of new join person.">
                                                                  </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Mobile No.*</label>
                                <div class="col-sm-6">
                                    <input type="text" name="registration_mobileno" class="form-control" value="<%out.print(adminUser.getMobile()); %>" id="uregistration_mobleno" maxlength=10 placeholder="Mobile number of new join person">
                                </div>
                            </div>
                             <input type="hidden" id="user_status" value = "<%out.print(adminUser.getStatus());%>"/>
                           
                            <%
                            System.out.print("Hello : "+adminUser.getName()+" your staus is : "+adminUser.getStatus());
                            
                            if(registration.getAdminUserRole().getId() == 1)
                            {
                            	SchoolDAOImp adminUserRole = new SchoolDAOImp();
                            	List<AdminUserRole> adminUserRoles = adminUserRole.getAdminUserRole();
                            	
                            %>
                            <div class="form-group">
								<label class="col-sm-2 control-label">User Type</label>
									<div class="col-sm-6">
										<select id="uuser_type" name="user_type" class="form-control">
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
                          <input type="hidden" id="uuser_type" value = "<%out.print(adminUser.getAdminUserRole().getId());%>"/>
                          
                          <%
                            }
                          %>  
                            <input type="hidden" value="<%out.print(adminUser.getId());%>" id="registration_id"/>
                            <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="updateAdminUser();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showAdminUserList();">Cancel</button>
                                    </div>
                                </div>
                        </div>            
		   			</div>
                </form> 		</div>
    
    <!-- /Right main content -->
<%@ include file="../footer.jsp" %>
<script type="text/javascript">
	function updateAdminUser(){
		$.post("../webapi/settings/adminuser/update", {id: $("#id").val(), name: $("#uregistration_name").val(),
			email : $("#uregistration_email").val(),
			mobile : $("#uregistration_mobleno").val(),
			upassword : $("#uregistration_password").val(),
			role_id :$("#registration_id").val(),
			user_status : $("#user_status").val()
		}, function(data){
			window.location.href = "${baseUrl}/settings/registration.jsp";
		});
	}
	
	function showAdminUserList(){
		window.location.href = "${baseUrl}/settings/registration.jsp";
	}
</script>