<%@page import="org.school.admin.model.Country"%>
<%@page import="org.school.admin.model.State"%>
<%@page import="org.school.admin.dao.CountryDAOImp"%>
<%@page import="org.school.admin.dao.StateImp"%>
<%@page import="java.util.List"%>
<%
	int state_id = Integer.parseInt(request.getParameter("state_id"));
	List<Country> country_list = new CountryDAOImp().getCountryList();
	List<State> state_detail = null;
	State state = null;
	if (state_id > 0) {
		state_detail = new StateImp().getStateById(state_id);
		if(state_detail.size() > 0)
			state = state_detail.get(0);
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
                    <li class="active">State Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update state</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update State</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(state.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(state.getName()); %>" placeholder="Maharashtra">
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
	                                        int tehsil_size = country_list.size(); 
	                                        for(int i=0; i < tehsil_size; i++){
	                                        	String selected = "";
	                                        	if(state.getCountry().getId() == country_list.get(i).getId()){
	                                        		selected = "selected";
	                                        	}
	                                        	out.print("<option value='"+country_list.get(i).getId()+"' "+selected+">"+country_list.get(i).getName()+"</option>");
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

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(state.getSortOrder()); %>" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of district
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(state.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(state.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive district.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveState();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showStateList(<% out.print(state.getCountry().getId()); %>);">Cancel</button>
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
	$.post("../webapi/general/state/update", {id: $("#id").val(), name: $("#name").val(), countryId: $("#countryId").val(), sortOrder: $("#sortOrder").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/general/state.jsp?country_id="+$("#countryId").val();
	});
}

function showStateList(id){
	window.location.href = "${baseUrl}/general/state.jsp?country_id="+id;
}
	
</script>