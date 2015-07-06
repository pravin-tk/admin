<%@page import="org.school.admin.model.FacultyType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<FacultyType> faculty_list = new SettingsImpl().getAllFacultyType();
	int faculty_size = faculty_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Faculty Type</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Faculty Type</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                        	<th>ID</th>
                                            <th>Name</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < faculty_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(faculty_list.get(i).getId()); %></td>
                                            <td><% out.print(faculty_list.get(i).getName()); %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editFacultyType(<% out.print(faculty_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Faculty Type</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Faculty Type</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the faculty type.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveFacultyType();">Save</button>
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
function saveFacultyType(){
	$.post("../webapi/settings/facultytype/save", {name: $("#name").val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/facultytype.jsp";
		else
			alert(data.message);
	});
}

function editFacultyType(id){
	window.location.href = "${baseUrl}/settings/editfacultytype.jsp?id="+id;
}	
</script>