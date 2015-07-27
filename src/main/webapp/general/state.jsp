<%@page import="org.school.admin.model.State"%>
<%@page import="org.school.admin.dao.CountryDAOImp"%>
<%@page import="org.school.admin.model.Country"%>
<%@page import="org.school.admin.dao.StateImp"%>
<%@page import="java.util.List"%>
<%
int country_id=0;
int country_size=0;
List<State> state_list = null;
List<Country> country_list = null;
int state_size=0;
if (request.getParameterMap().containsKey("country_id")) {
	country_id = Integer.parseInt(request.getParameter("country_id"));
	
	if (country_id > 0) {
		state_list = new StateImp().getStateByCountryId(country_id);
		state_size = state_list.size(); 
	}
}

country_list = new CountryDAOImp().getCountryList();
country_size = country_list.size();
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">State</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                        	
                            <div class="contacts-list">
                            <div class="form-group">
				                <label class="col-sm-2 control-label">Select Country</label>
				                <div class="col-sm-8">
					                <select name="searchcountryId" id="searchcountryId" class="form-control">
					                    <option value="">Select Country</option>
					                    <%
					                    String search_tehsil_selected = ""; 
					                    for(int i=0; i < country_size; i++){
					                    	if(country_list.get(i).getId() == country_id){
					                    		search_tehsil_selected = "selected";
					                    	}else{
					                    		search_tehsil_selected = "";
					                    	}
					                    	out.print("<option value='"+country_list.get(i).getId()+"' "+search_tehsil_selected+">"+country_list.get(i).getName()+"</option>");
					                    }
					                    %>
					                </select>
				                </div>
			                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> State</a>

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
                                        for(int i=0; i < state_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(state_list.get(i).getName()); %></td>
<%--                                             <td><% out.print(state_list.get(i).getSortOrder()); %></td> --%>
                                            <td><% if(state_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editState(<% out.print(state_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> State</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New State</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Maharashtra">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the state.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Country</label>
	                                <div class="col-sm-2">
	                                    <select name="countryId" id="countryId" class="form-control">
	                                        <option value="">Select Country</option>
	                                        <%
	                                        for(int i=0; i < country_size; i++){
	                                        	out.print("<option value='"+country_list.get(i).getId()+"'>"+country_list.get(i).getName()+"</option>");
	                                        }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Country of state.
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
<!--                                                 Display order of state -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->

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
                                                Active/Inactive state.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveState();">Save</button>
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
function saveState(){
	var sortOrder = 1;
	var msg = "";
	if($("name").val() == ""){
		if(msg != "")  msg = msg+",Please enter state name"; else msg = "Please enter state name";
	}
	if($("#countryId").val() == "" || $("#countryId").val() == 'undefined'){
		if(msg != "") msg = msg+",Please select country name"; else msg = "Please select country name";
	}
	if(msg != ""){
		alert(msg);
	}else{
		$.post("../webapi/general/state/save", {name: $("#name").val(), countryId: $("#countryId").val(), sortOrder: sortOrder, status: $('input[name=status]:checked').val()}, function(data){
			if($("#countryId").val() > 0){
				if(data.status == 1)
					window.location.href = "${baseUrl}/general/state.jsp?country_id="+$("#countryId").val();
				else
					alert(data.message);
			}else{
				alert("Select Country");
			}
		});
	}
}

function editState(id){
	window.location.href = "${baseUrl}/general/editstate.jsp?state_id="+id;
}

$("#searchcountryId").change(function(){
	if($(this).val() > 0){
		window.location.href = "${baseUrl}/general/state.jsp?country_id="+$(this).val();
	} else {
		alert("select Country");
	}
});
	
</script>