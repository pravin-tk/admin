<%@page import="org.school.admin.model.District"%>
<%@page import="org.school.admin.model.Tehsil"%>
<%@page import="org.school.admin.dao.DistrictImpl"%>
<%@page import="org.school.admin.dao.TehsilImpl"%>
<%@page import="java.util.List"%>
<%
	int tehsil_id = Integer.parseInt(request.getParameter("tehsil_id"));
	List<District> district_list = new DistrictImpl().getDistrictNames();
	List<Tehsil> tehsil_detail = null;
	Tehsil tehsil = null;
	if (tehsil_id > 0) {
		tehsil_detail = new TehsilImpl().getTehsilDetailById(tehsil_id);
		if(tehsil_detail.size() > 0)
			tehsil = tehsil_detail.get(0);
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
                    <li class="active">Tehsil Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update tehsil</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Tehsil</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(tehsil.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(tehsil.getName()); %>" placeholder="Haveli">
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
	                                        int tehsil_size = district_list.size(); 
	                                		String selected = "";
	                                        for(int i=0; i < tehsil_size; i++){
	                                        	if(tehsil.getDistrict().getId() == district_list.get(i).getId()){
	                                        		selected = "selected";
	                                        	}
	                                        	out.print("<option value='"+district_list.get(i).getId()+"' "+selected+">"+district_list.get(i).getName()+"</option>");
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

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(tehsil.getSortOrder()); %>" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of tehsil
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="true" <%if(tehsil.getStatus() == true){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="false" <%if(tehsil.getStatus() == false){ %>checked="checked"<%} %> >Inactive
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
                                        <button type="button" class="btn btn-success" onclick="saveTehsil();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showTehsilList(<% out.print(tehsil.getDistrict().getId()); %>);">Cancel</button>
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
	$.post("../webapi/general/tehsil/update", {id: $("#id").val(), name: $("#name").val(), districtId: $("#districtId").val(), sortOrder: $("#sortOrder").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/general/tehsil.jsp?district_id="+$("#districtId").val();
	});
}

function showTehsilList(id){
	window.location.href = "${baseUrl}/general/tehsil.jsp?district_id="+id;
}
	
</script>