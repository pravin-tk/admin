<%@page import="org.school.admin.model.SecondaryRole"%>
<%@page import="org.school.admin.model.Role"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));	
	List<SecondaryRole> secondaryroles = null;
	SecondaryRole secondaryrole = null;
	if (id > 0) {
		secondaryroles = new SettingsImpl().getSecondaryRoleById(id);
		if(secondaryroles.size() > 0)
			secondaryrole = secondaryroles.get(0);
	}
	List<Role> role_list = new SettingsImpl().getAllRole();
	int role_size = role_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Settings</a>
                    </li>
                    <li class="active">PTC Secondary Role Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update PTC Secondary Role</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update PTC Secondary Role</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(secondaryrole.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(secondaryrole.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Title of the PTC Secondary Role.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Role</label>
	                                <div class="col-sm-6">
	                                    <select name="roleId" id="roleId" class="form-control">
	                                        <option value="">Select PTC Primary Role</option>
	                                        <%
	                                        for(int i=0; i < role_size; i++){
	                                        	String selected = "";
	                                        	if(secondaryrole.getRole().getId() == role_list.get(i).getId()){
	                                        		selected = "selected";
	                                        	}else{
	                                        		selected = "";
	                                        	}
	                                        	out.print("<option value='"+role_list.get(i).getId()+"' "+selected+">"+role_list.get(i).getName()+"</option>");
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
                                        <textarea class="form-control" name="description" id="description"><% out.print(secondaryrole.getDescription()); %></textarea>
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
                                            <input type="radio" name="status" id="status" value="1" <%if(secondaryrole.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(secondaryrole.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveSecondaryRole();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showSecondaryRoleList();">Cancel</button>
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
	function saveSecondaryRole(){
		$.post("../webapi/settings/secondaryrole/update", {id: $("#id").val(), title: $("#title").val(), roleId: $("#roleId").val(), description: $("#description").val(), status: $('input[name=status]:checked').val()}, function(data){
			window.location.href = "${baseUrl}/settings/ptc-secondary-role.jsp";
		});
	}	
	function showSecondaryRoleList(){
		window.location.href = "${baseUrl}/settings/ptc-secondary-role.jsp";
	}
</script>