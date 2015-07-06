<%@page import="org.school.admin.model.Role"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<Role> role_detail = null;
	Role role = null;
	if (id > 0) {
		role_detail = new SettingsImpl().getRoleById(id);
		if(role_detail.size() > 0)
			role = role_detail.get(0);
	}
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">PTC Primary Role Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update ptc primary role</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update PTC Primary Role</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(role.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">PTC Primary Role Name</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="role" id="role" value="<% out.print(role.getName()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                               PTC Primary Role Name.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(role.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(role.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-6">
                                        <button type="button" class="btn btn-success" onclick="saveRole();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showRoleList();">Cancel</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                    </div>
               </form>
 		</div>
    </div>
    <!-- /Right main content -->
<%@ include file="../footer.jsp" %>
<script type="text/javascript">
function saveRole(){
	$.post("../webapi/settings/role/update", {id: $("#id").val(), name: $("#role").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/settings/ptc-primary-role.jsp";
	});
}

function showRoleList(){
	window.location.href = "${baseUrl}/settings/ptc-primary-role.jsp";
}
	
</script>