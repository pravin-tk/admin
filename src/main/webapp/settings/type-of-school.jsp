<%@page import="org.school.admin.model.SchoolCategoryType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<SchoolCategoryType> schoolcategory_list = new SettingsImpl().getAllSchoolCategoryType();
	int schoolcategory_size = schoolcategory_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Type of School</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>Type of School</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Max Rating</th>
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < schoolcategory_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(schoolcategory_list.get(i).getName()); %></td>
                                            <td><% out.print(schoolcategory_list.get(i).getMaxPoints()); %></td>
                                            <td><% if(schoolcategory_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editSchoolCategory(<% out.print(schoolcategory_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Type of School</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Type of School</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                School Category Name.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" class="form-control" name="maxPoints" id="maxPoints" value="5">

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
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveSchoolCategory();">Save</button>
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
function saveSchoolCategory(){
	$.post("../webapi/settings/schoolcategorytype/save", {name: $("#name").val(), maxPoints: $("#maxPoints").val(), status: $('input[name=status]:checked').val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/type-of-school.jsp";
		else
			alert(data.message);
	});
}

function editSchoolCategory(id){
	window.location.href = "${baseUrl}/settings/edittype-of-school.jsp?id="+id;
}	
</script>