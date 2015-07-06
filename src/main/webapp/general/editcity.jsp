<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.model.Tehsil"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="org.school.admin.dao.TehsilImpl"%>
<%@page import="java.util.List"%>
<%
	int city_id = Integer.parseInt(request.getParameter("city_id"));
	List<Tehsil> tehsil_list = new TehsilImpl().getTehsilList();
	List<City> city_detail = null;
	City city = null;
	if (city_id > 0) {
		city_detail = new CityNamesService().getCityDetailById(city_id);
		if(city_detail.size() > 0)
		city = city_detail.get(0);
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
                    <li class="active">City Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update city</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update City</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(city.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(city.getName()); %>" placeholder="pune">
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
	                                        int tehsil_size = tehsil_list.size(); 
	                                		String selected = "";
	                                        for(int i=0; i < tehsil_size; i++){
	                                        	if(city.getTehsil().getId() == tehsil_list.get(i).getId()){
	                                        		selected = "selected";
	                                        	}
	                                        	out.print("<option value='"+tehsil_list.get(i).getId()+"' "+selected+">"+tehsil_list.get(i).getName()+"</option>");
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
                                            <input type="radio" name="isCity" id="isCity" value="1" <%if(city.getIsCity() == 1){ %>checked="checked"<%} %> >Yes
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isCity" id="isCity" value="0" <%if(city.getIsCity() == 0){ %>checked="checked"<%} %> >No
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


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(city.getSortOrder()); %>" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of city
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(city.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(city.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveCity();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showCityList(<% out.print(city.getTehsil().getId()); %>);">Cancel</button>
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
	$.post("../webapi/general/city/update", {id: $("#id").val(), name: $("#name").val(), tehsilId: $("#tehsilId").val(), isCity: $('input[name=isCity]:checked').val(), sortOrder: $("#sortOrder").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/general/city.jsp?tehsil_id="+$("#tehsilId").val();
	});
}

function showCityList(tehsilid){
	window.location.href = "${baseUrl}/general/city.jsp?tehsil_id="+tehsilid;
}
	
</script>