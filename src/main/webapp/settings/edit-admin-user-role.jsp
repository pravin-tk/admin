
<%@page import="org.school.admin.model.AdminUserRole"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));	
	List<AdminUserRole> detail = null;
	AdminUserRole adminUserRole = null;
	if (id > 0) {
		detail = new SettingsImpl().getAdminUserRoleById(id);
		if(detail.size() > 0)
			adminUserRole = detail.get(0);
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
                    <li class="active">Admin User Role</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update admin user role</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Admin User Role</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(adminUserRole.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Admin User Role</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="adminuserrole" id="adminuserrole" value="<% out.print(adminUserRole.getRoleName()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the admin user role.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="updateAdminUserRole();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showAdminUserRoleList();">Cancel</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                    </div>
               </form>
 		</div>
    
    <!-- /Right main content -->
<%@ include file="../footer.jsp" %>
<script type="text/javascript">
	function updateAdminUserRole(){
		$.post("../webapi/settings/adminuserrole/update", {id: $("#id").val(), adminuserrole: $("#adminuserrole").val()}, function(data){
			if(data.status == 1)
				window.location.href = "${baseUrl}/settings/adminuserrole.jsp";
			else
				alert(data.message);
		});
	}
	
	function showAdminUserRoleList(){
		window.location.href = "${baseUrl}/settings/adminuserrole.jsp";
	}
</script>