<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.model.Tehsil"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="org.school.admin.dao.TehsilImpl"%>
<%@page import="java.util.List"%>
<%
int tehsil_id=0;
int city_size =0;
List<Tehsil> tehsil_list = new TehsilImpl().getTehsilList();
List<City> city_list = null;
int tehsil_size = tehsil_list.size(); 
if (request.getParameterMap().containsKey("tehsil_id")) {
  tehsil_id = Integer.parseInt(request.getParameter("tehsil_id"));
  city_list = new CityNamesService().getAllCityNamesByTehsilId(tehsil_id);
  city_size = city_list.size(); 
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
                    <li class="active">City</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                        	
                            <div class="contacts-list">
                            <div class="form-group">
				                <label class="col-sm-2 control-label">Select City</label>
				                <div class="col-sm-8">
					                <select name="searchtehsilId" id="searchtehsilId" class="form-control">
					                    <option value="">Select Tehsil</option>
					                    <%
					                    String search_tehsil_selected = ""; 
					                    for(int i=0; i < tehsil_size; i++){
					                    	if(tehsil_list.get(i).getId() == tehsil_id){
					                    		search_tehsil_selected = "selected";
					                    	}else{
					                    		search_tehsil_selected = "";
					                    	}
					                    	out.print("<option value='"+tehsil_list.get(i).getId()+"' "+search_tehsil_selected+">"+tehsil_list.get(i).getName()+"</option>");
					                    }
					                    %>
					                </select>
				                </div>
			                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> City</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>IsCity</th>
<!--                                             <th>Sort Order</th> -->
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < city_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(city_list.get(i).getName()); %></td>
                                            <td><% if(city_list.get(i).getIsCity() == 1) { out.print("City"); } else { out.print("Village"); } %></td>
<%--                                             <td><% out.print(city_list.get(i).getSortOrder()); %></td> --%>
                                            <td><% if(city_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editCity(<% out.print(city_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> City</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New City</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="pune">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the city.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Tehsil</label>
	                                <div class="col-sm-2">
	                                    <select name="tehsilId" id="tehsilId" class="form-control">
	                                        <option value="">Select Tehsil</option>
	                                        <%
	                                        for(int i=0; i < tehsil_size; i++){
	                                        	out.print("<option value='"+tehsil_list.get(i).getId()+"'>"+tehsil_list.get(i).getName()+"</option>");
	                                        }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Tehsil of city.
                                            </div>
                                        </div>
                                    </div>
	                            </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Is City</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="isCity" id="isCity" value="1" checked="checked">Yes
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isCity" id="isCity" value="0">No
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                City Or Village
                                            </div>
                                        </div>
                                    </div>
                                </div>


<!--                                 <div class="form-group"> 
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of city
                                            </div>
                                        </div>
                                    </div>
                                </div> -->

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
                                                Active/Inactive city.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveCity();">Save</button>
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
function saveCity(){
	var sortOrder=1;
	var msg = "";
	if($("#name").val() == ""){
		if(msg != "") msg= msg+",Please enter city name"; else msg = "Please enter city name";
	}
	if($("#tehsilId").val() == ""){
		if(msg != "") msg=msg+",Please select tehsil name"; else msg = "Please select tehsil name";
	}
	if(msg!=""){
		alert(msg);
	}else{
		$.post("../webapi/general/city/save", {name: $("#name").val(), tehsilId: $("#tehsilId").val(), isCity: $('input[name=isCity]:checked').val(), sortOrder: sortOrder, status: $('input[name=status]:checked').val()}, function(data){
			if($("#tehsilId").val() > 0){
				if(data.status == 1)
					window.location.href = "${baseUrl}/general/city.jsp?tehsil_id="+$("#tehsilId").val();
				else
					alert(data.message);
			}else{
				alert("Select Tehsil");
			}
		});
	}
}

function editCity(id){
	window.location.href = "${baseUrl}/general/editcity.jsp?city_id="+id;
}

$("#searchtehsilId").change(function(){
	if($(this).val() > 0){
		window.location.href = "${baseUrl}/general/city.jsp?tehsil_id="+$(this).val();
	} else {
		alert("select tehsil");
	}
});
	
</script>