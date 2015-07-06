<%@page import="org.school.admin.model.Role"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<Role> role_list = new SettingsImpl().getAllRole();
	int role_size = role_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">PTC Primary Role</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>PTC Primary Role</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>PTC Primary Role</th>
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < role_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(role_list.get(i).getName()); %></td>
                                            <td><% if(role_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editRole(<% out.print(role_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i>PTC Primary Role</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New PTC Primary Role</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">PTC Primary Role</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="role" id="role" placeholder="">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                               PTC Primary Role name.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" checked="checked">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0">Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveRole();">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
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
	$.post("../webapi/settings/role/save", {name: $("#role").val(), status: $('input[name=status]:checked').val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/ptc-primary-role.jsp";
		else
			alert(data.message);
	});
}

function editRole(id){
	window.location.href = "${baseUrl}/settings/editptc-primary-role.jsp?id="+id;
}	
</script>