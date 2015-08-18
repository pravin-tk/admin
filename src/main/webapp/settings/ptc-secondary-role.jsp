<%@page import="org.school.admin.model.SecondaryRole"%>
<%@page import="org.school.admin.model.Role"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<SecondaryRole> secondaryrole_list = new SettingsImpl().getAllSecondaryRole();
	List<Role> role_list = new SettingsImpl().getAllRole();
	int secondaryrole_size = secondaryrole_list.size(); 
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
                    <li class="active">PTC Secondary Role</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>PTC Secondary Role</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>PTC Role</th>
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < secondaryrole_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(secondaryrole_list.get(i).getTitle()); %></td>
                                            <td><% out.print(secondaryrole_list.get(i).getRole().getName()); %></td>
                                            <td><% if(secondaryrole_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editSecondaryRole(<% out.print(secondaryrole_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i>PTC Secondary Role</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New PTC Secondary Role</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                              PTC Secondary Role Title.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">PTC Secondary Role</label>
	                                <div class="col-sm-6">
	                                    <select name="roleId" id="roleId" class="form-control">
	                                        <option value="">Select PTC Primary Role</option>
	                                        <%
	                                        for(int i=0; i < role_size; i++){
	                                        	out.print("<option value='"+role_list.get(i).getId()+"'>"+role_list.get(i).getName()+"</option>");
	                                        }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                               PTC Primary Role.
                                            </div>
                                        </div>
                                    </div>
	                            </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"></textarea>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Description.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" checked="checked">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0">Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-4">
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
                                        <button type="button" class="btn btn-success" onclick="saveSecondaryRole();">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
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
function saveSecondaryRole(){
	$.post("../webapi/settings/secondaryrole/save", {title: $("#title").val(), roleId: $("#roleId").val(), description: $("#description").val(), status: $('input[name=status]:checked').val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/ptc-secondary-role.jsp";
		else
			alert(data.message);
	});
}

function editSecondaryRole(id){
	window.location.href = "${baseUrl}/settings/editptc-secondary-role.jsp?id="+id;
}	
</script>