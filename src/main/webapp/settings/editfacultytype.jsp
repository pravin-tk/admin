<%@page import="org.school.admin.model.FacultyType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<FacultyType> faculty_detail = null;
	FacultyType faculty = null;
	if (id > 0) {
		faculty_detail = new SettingsImpl().getFacultyTypeById(id);
		if(faculty_detail.size() > 0)
			faculty = faculty_detail.get(0);
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
                    <li class="active">Faculty Type Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update faculty Type</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Faculty Type</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(faculty.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(faculty.getName()); %>" placeholder="">
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
                                        <button type="button" class="btn btn-success" onclick="saveFacultyType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showFacultyList();">Cancel</button>
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
	$.post("../webapi/settings/facultytype/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
		window.location.href = "${baseUrl}/settings/facultytype.jsp";
	});
}

function showFacultyList(){
	window.location.href = "${baseUrl}/settings/facultytype.jsp";
}
	
</script>