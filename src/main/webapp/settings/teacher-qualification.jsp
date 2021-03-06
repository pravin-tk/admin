<%@page import="org.school.admin.model.EducationType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<EducationType> education_list = new SettingsImpl().getAllEducationType();
	int education_size = education_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Teacher Qualification</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>Teacher Qualification</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Short Title</th>
<!--                                             <th>Sort Order</th> -->
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < education_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(education_list.get(i).getTitle()); %></td>
                                            <td><% out.print(education_list.get(i).getShortTitle()); %></td>
<%--                                             <td><% out.print(education_list.get(i).getSortOrder()); %></td> --%>
                                            <td class="alignRight">
                                            	<a href="javascript:editEducationType(<% out.print(education_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Teacher Qualification</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Teacher Qualification</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the title of the teacher qualification.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Short Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="shortTitle" id="shortTitle" placeholder="">
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


<!--                                 <div class="form-group"> -->
<!--                                     <label class="col-sm-2 control-label">Display Order</label> -->
<!--                                     <div class="col-sm-2"> -->
<!--                                         <input type="text" class="form-control" name="sortOrder" id="sortOrder" placeholder="1"> -->
<!--                                     </div> -->
<!--                                     <div class="col-sm-8"> -->
<!--                                         <div class="tooltip custom-tool-tip right"> -->
<!--                                             <div class="tooltip-arrow"></div> -->
<!--                                             <div class="tooltip-inner"> -->
<!--                                                 Display order of teacher qualification -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->


                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveEducationType();">Save</button>
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
function saveEducationType(){
	var sortOder = 1;
	$.post("../webapi/settings/educationtype/save", {title: $("#title").val(), shortTitle: $("#shortTitle").val(), description: $("#description").val(), sortOrder: sortOrder}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/teacher-qualification.jsp";
		else
			alert(data.message);
	});
}

function editEducationType(id){
	window.location.href = "${baseUrl}/settings/editeteacher-qualification.jsp?id="+id;
}	
</script>