<%@page import="org.school.admin.model.State"%>
<%@page import="org.school.admin.dao.DistrictImpl"%>
<%@page import="org.school.admin.model.District"%>
<%@page import="org.school.admin.dao.StateImp"%>
<%@page import="java.util.List"%>
<%
int state_id=0;
List<State> state_list = new StateImp().getStateList();
int state_size=0;
List<District> district_list = null;
int district_size=0;
if (request.getParameterMap().containsKey("state_id")) {
	state_id= Integer.parseInt(request.getParameter("state_id"));
	if (state_id > 0) {
		district_list = new DistrictImpl().getAllDistrictByStateId(state_id);
		district_size = district_list.size(); 
	}
}
state_size = state_list.size();
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">District</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                            <div class="form-group">
				                <label class="col-sm-2 control-label">Select State</label>
				                <div class="col-sm-8">
					                <select name="searchstateId" id="searchstateId" class="form-control">
					                    <option value="">Select State</option>
					                    <%
					                    String search_tehsil_selected = ""; 
					                    for(int i=0; i < state_size; i++){
					                    	if(state_list.get(i).getId() == state_id){
					                    		search_tehsil_selected = "selected";
					                    	}else{
					                    		search_tehsil_selected = "";
					                    	}
					                    	out.print("<option value='"+state_list.get(i).getId()+"' "+search_tehsil_selected+">"+state_list.get(i).getName()+"</option>");
					                    }
					                    %>
					                </select>
				                </div>
			                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> District</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
<!--                                             <th>Sort Order</th> -->
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < district_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(district_list.get(i).getName()); %></td>
<%--                                             <td><% out.print(district_list.get(i).getSortOrder()); %></td> --%>
                                            <td><% if(district_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editDistrict(<% out.print(district_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> District</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New District</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Nagpur">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the tehsil.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">State</label>
	                                <div class="col-sm-2">
	                                    <select name="stateId" id="stateId" class="form-control">
	                                        <option value="">Select State</option>
	                                        <%
	                                        for(int i=0; i < state_size; i++){
	                                        	out.print("<option value='"+state_list.get(i).getId()+"'>"+state_list.get(i).getName()+"</option>");
	                                        }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                State of district.
                                            </div>
                                        </div>
                                    </div>
	                            </div>

                              <!--   <div class="form-group"> -->
<!--                                     <label class="col-sm-2 control-label">Display Order</label> -->
<!--                                     <div class="col-sm-2"> -->
<!--                                         <input type="text" class="form-control" name="sortOrder" id="sortOrder" placeholder="1"> -->
<!--                                     </div> -->
<!--                                     <div class="col-sm-8"> -->
<!--                                         <div class="tooltip custom-tool-tip right"> -->
<!--                                             <div class="tooltip-arrow"></div> -->
<!--                                             <div class="tooltip-inner"> -->
<!--                                                 Display order of tehsil -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div>  -->

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" checked="checked">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0">Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive tehsil.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveDistrict();">Save</button>
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
function saveDistrict(){
	var sortOrder = 1;
	var msg = "";
	if($("#name").val() == ""){
		if(msg != "") msg = msg+",Please enter district name"; else msg = "Please enter district name";
	}
	if($("#stateId").val() == ""){
	    if(msg != "") msg = msg+",Please select state name"; else msg = "Please select state name";
	}
	if(msg != ""){
		alert(msg);
	}else{
		$.post("../webapi/general/district/save", {name: $("#name").val(), stateId: $("#stateId").val(), sortOrder: sortOrder, status: $('input[name=status]:checked').val()}, function(data){
			if($("#stateId").val() > 0){
				if(data.status == 1)
					window.location.href = "${baseUrl}/general/district.jsp?state_id="+$("#stateId").val();
				else
					alert(data.message);
			}else{
				alert("Select State");
			}
		});
	}
}

function editDistrict(id){
	window.location.href = "${baseUrl}/general/editdistrict.jsp?district_id="+id;
}

$("#searchstateId").change(function(){
	if($(this).val() > 0){
		window.location.href = "${baseUrl}/general/district.jsp?state_id="+$(this).val();
	} else {
		alert("select State");
	}
});
	
</script>