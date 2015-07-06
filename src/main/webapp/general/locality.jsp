<%@page import="org.school.admin.dao.CityNamesImp"%>
<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.model.Locality"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="org.school.admin.dao.LocalityNamesImp"%>
<%@page import="java.util.List"%>
<%
int city_id = 0;
int city_size = 0; 
int locality_size = 0;
List<Locality> locality_list = null;
if (request.getParameterMap().containsKey("city_id")) {
    city_id = Integer.parseInt(request.getParameter("city_id"));
}
List<City> city_list = new CityNamesService().getAllCityNames();	
city_size = city_list.size(); 
if (city_id > 0) {
	locality_list = new LocalityNamesImp().getLocalityNames(city_id);
	locality_size = locality_list.size(); 
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
                    <li class="active">Locality</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                        	
                            <div class="contacts-list">
                            <div class="form-group">
				                <label class="col-sm-2 control-label">Select City</label>
				                <div class="col-sm-8">
					                <select name="searchcityId" id="searchcityId" class="form-control">
					                    <option value="">Select City</option>
					                    <%
					                    String search_tehsil_selected = ""; 
					                    for(int i=0; i < city_size; i++){
					                    	if(city_list.get(i).getId() == city_id){
					                    		search_tehsil_selected = "selected";
					                    	}else{
					                    		search_tehsil_selected = "";
					                    	}
					                    	out.print("<option value='"+city_list.get(i).getId()+"' "+search_tehsil_selected+">"+city_list.get(i).getName()+"</option>");
					                    }
					                    %>
					                </select>
				                </div>
			                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Locality</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Sort Order</th>
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<% if(locality_size > 0){
	                                        for(int i=0; i < locality_size; i++){
	                                        %>
	                                        <tr>
	                                            <td><% out.print(locality_list.get(i).getName()); %></td>
	                                            <td><% out.print(locality_list.get(i).getSortOrder()); %></td>
	                                            <td><% if(locality_list.get(i).getStatus()) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
	                                            <td class="alignRight">
	                                            	<a href="javascript:editCity(<% out.print(locality_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
	                                            </td>
	                                        </tr>
	                                        <% 
                                        	}
                                    	}
                                        %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Locality</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Locality</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="pune">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the Locality.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">City</label>
	                                <div class="col-sm-2">
	                                    <select name="cityId" id="cityId" class="form-control">
	                                        <option value="">Select City</option>
	                                        <%
	                                        for(int i=0; i < city_size; i++){
	                                        	out.print("<option value='"+city_list.get(i).getId()+"'>"+city_list.get(i).getName()+"</option>");
	                                        }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                City of locality.
                                            </div>
                                        </div>
                                    </div>
	                            </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of locality
                                            </div>
                                        </div>
                                    </div>
                                </div>

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
                                                Active/Inactive city.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveLocality();">Save</button>
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
function saveLocality(){
	$.post("../webapi/general/locality/save", {name: $("#name").val(), cityId: $("#cityId").val(), sortOrder: $("#sortOrder").val(), status: $('input[name=status]:checked').val()}, function(data){
		if($("#cityId").val() > 0){
			if(data.status == 1)
				window.location.href = "${baseUrl}/general/locality.jsp?city_id="+$("#cityId").val();
			else
				alert(data.message);
		}else{
			alert("Select City");
		}
	});
}

function editCity(id){
	window.location.href = "${baseUrl}/general/editlocality.jsp?locality_id="+id;
}

$("#searchcityId").change(function(){
	if($(this).val() > 0){
		window.location.href = "${baseUrl}/general/locality.jsp?city_id="+$(this).val();
	} else {
		alert("select city");
	}
});
	
</script>