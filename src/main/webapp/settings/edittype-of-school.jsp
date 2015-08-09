<%@page import="org.school.admin.model.SchoolCategoryType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	List<SchoolCategoryType> schoolcategory_detail = null;
	SchoolCategoryType schoolcategory = null;
	if (id > 0) {
		schoolcategory_detail = new SettingsImpl().getSchoolCategoryTypeById(id);
		if(schoolcategory_detail.size() > 0)
			schoolcategory = schoolcategory_detail.get(0);
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
                    <li class="active">Type of School Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update type of school</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Type of School</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(schoolcategory.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(schoolcategory.getName()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Type of School Name.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="maxPoints" id="maxPoints" value="<% out.print(schoolcategory.getMaxPoints()); %>" placeholder="">

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(schoolcategory.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(schoolcategory.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveSchoolCategory();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showSchoolCategoryList();">Cancel</button>
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
	$.post("../webapi/settings/schoolcategorytype/update", {id: $("#id").val(), name: $("#name").val(), maxPoints: $("#maxPoints").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/settings/type-of-school.jsp";
	});
}

function showSchoolCategoryList(){
	window.location.href = "${baseUrl}/settings/type-of-school.jsp";
}
	
</script>