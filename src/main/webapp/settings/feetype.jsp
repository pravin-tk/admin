<%@page import="org.school.admin.model.FeeType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<FeeType> feetype_list = new SettingsImpl().getAllFeeType();
	int feetype_size = feetype_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Fee Type</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Fee Type</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < feetype_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(feetype_list.get(i).getTitle()); %></td>
                                            <td><% if(feetype_list.get(i).getIsDeleted() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editFeeType(<% out.print(feetype_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Fee Type</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Fee Type</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the title of the fee type.
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
			                        <div class="col-sm-4">
			                            <label class="radio-inline">
			                                <input type="radio" name="isDeleted" id="isDeleted" value="1" checked="checked">Active
			                            </label>
			                            <label class="radio-inline">
			                                <input type="radio" name="isDeleted" id="isDeleted" value="0">Inactive
			                            </label>
			                        </div>
			                        <div class="col-sm-6">
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
                                        <button type="button" class="btn btn-success" onclick="saveFeeType();">Save</button>
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
function saveFeeType(){
	$.post("../webapi/settings/feetype/save", {title: $("#title").val(), description: $("#description").val(), isDeleted: $('input[name=isDeleted]:checked').val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/feetype.jsp";
		else
			alert(data.message);
	});
}

function editFeeType(id){
	window.location.href = "${baseUrl}/settings/editfeetype.jsp?id="+id;
}	
</script>