<%@page import="org.school.admin.model.SafetyCategory"%>
<%@page import="org.school.admin.dao.FacilityImpl"%>
<%@page import="java.util.List"%>
<%
	int scat_id = Integer.parseInt(request.getParameter("scat_id"));	
	List<SafetyCategory> safetycatDetail = null;
	SafetyCategory rowsafetycat = null;
	if (scat_id > 0) {
		safetycatDetail = new FacilityImpl().getSafetyCategoryById(scat_id);
		if(safetycatDetail.size() > 0)
			rowsafetycat = safetycatDetail.get(0);
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
                    <li class="active">Safety Category Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update safety category detail</p>

                            </div>
                            <div class="contacts-new">
                                <h4>Update Safety Category</h4>
								<div id="aierror" class="bg-danger" ></div>
								<input type="hidden" name="id" id="id" value="<% out.print(rowsafetycat.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="tname" id="tname" value="<% out.print(rowsafetycat.getName()); %>" placeholder="Campus Safety">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the category.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								
                               

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-2">
                                        <textarea class="form-control" name="desc" id="desc" placeholder="Description..."><% out.print(rowsafetycat.getDesc()); %></textarea>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                               description if any
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(rowsafetycat.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(rowsafetycat.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive safety category.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveSafetyCat();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button"
                                         onclick="showSafetyCatList();">Cancel</button>
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
function saveSafetyCat(){
	var strcat=$.trim($("#tname").val());
	if (strcat == "" ) {
		$("#aierror").html("Category name cannot be empty");
		$("#tname").addClass('has-error');    	
	} else {
		$.post("../webapi/facility/safetycat/update", {id: $("#id").val(), name: $("#tname").val(), desc: $("#desc").val(), status: $('input[name=status]:checked').val()}, function(data){
			window.location.href = "${baseUrl}/facility/safety-category.jsp";
		});
	}
}

function showSafetyCatList(){
	window.location.href = "${baseUrl}/facility/safety-category.jsp";
}
	
</script>