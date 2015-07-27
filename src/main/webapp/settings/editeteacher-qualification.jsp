<%@page import="org.school.admin.model.EducationType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<EducationType> education_detail = null;
	EducationType education = null;
	if (id > 0) {
		education_detail = new SettingsImpl().getEducationTypeById(id);
		if(education_detail.size() > 0)
			education = education_detail.get(0);
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
                    <li class="active">Teacher Qualification Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update Teacher Qualification</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Teacher Qualification</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(education.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(education.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the teacher qualification.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								
								<div class="form-group">
                                    <label class="col-sm-2 control-label">Short Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="shortTitle" id="shortTitle" value="<% out.print(education.getShortTitle()); %>">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the short title of the teacher qualification.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"><% out.print(education.getDescription()); %></textarea>
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

<!--                                 <div class="form-group"> -->
<!--                                     <label class="col-sm-2 control-label">Display Order</label> -->
<!--                                     <div class="col-sm-2"> -->
<%--                                         <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(education.getSortOrder()); %>" placeholder="1"> --%>
<!--                                     </div> -->
<!--                                     <div class="col-sm-8"> -->
<!--                                         <div class="tooltip custom-tool-tip right"> -->
<!--                                             <div class="tooltip-arrow"></div> -->
<!--                                             <div class="tooltip-inner"> -->
<!--                                                 Display order of teacher qualification. -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveEducationType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showEducationList();">Cancel</button>
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
function saveEducationType(){
	var sortOrder = 1;
	$.post("../webapi/settings/certificatetype/update", {id: $("#id").val(), title: $("#title").val(), shortTitle: $("#shortTitle").val(), description: $("#description").val(), sortOrder: sortOrder}, function(data){
		window.location.href = "${baseUrl}/settings/teacher-qualification.jsp";
	});
}

function showEducationList(){
	window.location.href = "${baseUrl}/settings/teacher-qualification.jsp";
}
	
</script>