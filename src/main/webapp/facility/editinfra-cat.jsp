<%@page import="org.school.admin.model.InfrastructureCategory"%>
<%@page import="org.school.admin.dao.FacilityImpl"%>
<%@page import="java.util.List"%>
<%
	int scat_id = Integer.parseInt(request.getParameter("scat_id"));	
	List<InfrastructureCategory> activitycatDetail = null;
	InfrastructureCategory rowactivitycat = null;
	if (scat_id > 0) {
		activitycatDetail = new FacilityImpl().getInfrastructureCategoryById(scat_id);
		if(activitycatDetail.size() > 0)
			rowactivitycat = activitycatDetail.get(0);
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
                    <li class="active">Infrastructure Category Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update activity category detail</p>

                            </div>
                            <div class="contacts-new">
                                <h4>Update Activity Category</h4>
								<div id="aerror" class="bg-danger" ></div>
								<input type="hidden" name="id" id="id" value="<% out.print(rowactivitycat.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtname" id="txtname" value="<% out.print(rowactivitycat.getName()); %>" placeholder="High tech Infra">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the infrastructure category.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveActivityCat();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button"
                                         onclick="showActivityCatList();">Cancel</button>
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
function saveActivityCat(){
	var strcat=$.trim($("#txtname").val());
	if (strcat == "") {
		$("#aerror").html("Category name cannot be empty");
		$("#txtname").addClass('has-error');  
	} else {	
		$.post("../webapi/facility/infracat/update", {id: $("#id").val(), name: $("#txtname").val()}, function(data){
			window.location.href = "${baseUrl}/facility/infra-cat.jsp";
		});
	}
}

function showActivityCatList(){
	window.location.href = "${baseUrl}/facility/infra-cat.jsp";
}
	
</script>