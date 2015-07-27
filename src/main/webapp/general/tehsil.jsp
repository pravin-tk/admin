<%@page import="org.school.admin.model.Tehsil"%>
<%@page import="org.school.admin.dao.DistrictImpl"%>
<%@page import="org.school.admin.model.District"%>
<%@page import="org.school.admin.dao.TehsilImpl"%>
<%@page import="java.util.List"%>
<%
int district_id=0;
List<District> district_list = null;
List<Tehsil> tehsil_list = null;
int district_size = 0;
int tehsil_size = 0;
if (request.getParameterMap().containsKey("district_id")) {
	district_id = Integer.parseInt(request.getParameter("district_id"));
}
district_list = new DistrictImpl().getDistrictNames();
district_size = district_list.size(); 	

if (district_id > 0) {
	tehsil_list = new TehsilImpl().getAllTehsilByDistrictId(district_id);
	tehsil_size = tehsil_list.size(); 
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
                    <li class="active">Tehsil</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                        	
                            <div class="contacts-list">
                            <div class="form-group">
				                <label class="col-sm-2 control-label">Select District</label>
				                <div class="col-sm-8">
					                <select name="searchdistrictId" id="searchdistrictId" class="form-control">
					                    <option value="">Select District</option>
					                    <%
					                    String search_tehsil_selected = ""; 
					                    for(int i=0; i < district_size; i++){
					                    	if(district_list.get(i).getId() == district_id){
					                    		search_tehsil_selected = "selected";
					                    	}else{
					                    		search_tehsil_selected = "";
					                    	}
					                    	out.print("<option value='"+district_list.get(i).getId()+"' "+search_tehsil_selected+">"+district_list.get(i).getName()+"</option>");
					                    }
					                    %>
					                </select>
				                </div>
			                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Tehsil</a>

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
                                        for(int i=0; i < tehsil_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(tehsil_list.get(i).getName()); %></td>
<%--                                             <td><% out.print(tehsil_list.get(i).getSortOrder()); %></td> --%>
                                            <td><% if(tehsil_list.get(i).getStatus() == true) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editTehsil(<% out.print(tehsil_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Tehsil</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Tehsil</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Haveli">
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
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">District</label>
	                                <div class="col-sm-2">
	                                    <select name="districtId" id="districtId" class="form-control">
	                                        <option value="">Select District</option>
	                                        <%
	                                        for(int i=0; i < district_size; i++){
	                                        	out.print("<option value='"+district_list.get(i).getId()+"'>"+district_list.get(i).getName()+"</option>");
	                                        }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                District of tehsil.
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
<!--                                                 Display order of tehsil -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="true" checked="checked">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="false">Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveTehsil();">Save</button>
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
function saveTehsil(){
	var sortOrder = 1;
	var msg = "";
	if($("#name").val() == ""){
		if(msg != "") msg = msg+",Please enter tehsil name"; else msg = "Please enter tehsil name";
	}
	if($("#districtId").val() == ""){
		if(msg != "") msg = msg+",Please select district name"; else msg = "Please select district name";
	}else{
			$.post("../webapi/general/tehsil/save", {name: $("#name").val(), districtId: $("#districtId").val(), sortOrder: sortOrder, status: $('input[name=status]:checked').val()}, function(data){
			if($("#districtId").val() > 0){
				if(data.status == 1)
					window.location.href = "${baseUrl}/general/tehsil.jsp?district_id="+$("#districtId").val();
				else
					alert(data.message);
			}else{
				alert("Select District");
			}
		});
	}
}

function editTehsil(id){
	window.location.href = "${baseUrl}/general/edittehsil.jsp?tehsil_id="+id;
}

$("#searchdistrictId").change(function(){
	if($(this).val() > 0){
		window.location.href = "${baseUrl}/general/tehsil.jsp?district_id="+$(this).val();
	} else {
		alert("select District");
	}
});
	
</script>