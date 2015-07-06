<%@page import="org.school.admin.model.District"%>
<%@page import="org.school.admin.model.State"%>
<%@page import="org.school.admin.dao.DistrictImpl"%>
<%@page import="org.school.admin.dao.StateImp"%>
<%@page import="java.util.List"%>
<%
	int district_id = Integer.parseInt(request.getParameter("district_id"));
	List<State> state_list = new StateImp().getStateList();
	List<District> district_detail = null;
	District district = null;
	if (district_id > 0) {
		district_detail = new DistrictImpl().getDistrictDetailById(district_id);
		if(district_detail.size() > 0)
			district = district_detail.get(0);
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
                    <li class="active">District Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update district</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update District</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(district.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(district.getName()); %>" placeholder="Nagpur">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the district.
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
	                                        int tehsil_size = state_list.size(); 
	                                        for(int i=0; i < tehsil_size; i++){
	                                        	String selected = "";
	                                        	if(district.getState().getId() == state_list.get(i).getId()){
	                                        		selected = "selected";
	                                        	}
	                                        	out.print("<option value='"+state_list.get(i).getId()+"' "+selected+">"+state_list.get(i).getName()+"</option>");
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

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(district.getSortOrder()); %>" placeholder="1">
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
                                            <input type="radio" name="status" id="status" value="1" <%if(district.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(district.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveDistrict();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showDistrictList(<% out.print(district.getState().getId()); %>);">Cancel</button>
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
	$.post("../webapi/general/district/update", {id: $("#id").val(), name: $("#name").val(), stateId: $("#stateId").val(), sortOrder: $("#sortOrder").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/general/district.jsp?state_id="+$("#stateId").val();
	});
}

function showDistrictList(id){
	window.location.href = "${baseUrl}/general/district.jsp?state_id="+id;
}
	
</script>