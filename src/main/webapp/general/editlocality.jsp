<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.model.Locality"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="org.school.admin.dao.LocalityNamesImp"%>
<%@page import="java.util.List"%>
<%
	int locality_id = Integer.parseInt(request.getParameter("locality_id"));
	List<City> city_list = new CityNamesService().getAllCityNames();
	List<Locality> locality_detail = null;
	Locality locality = null;
	if (locality_id > 0) {
		locality_detail = new LocalityNamesImp().getLocalityDetailById(locality_id);
		if(locality_detail.size() > 0)
		locality = locality_detail.get(0);
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
                    <li class="active">Locality Edit</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                 <h2>Update Locality</h2>

                            </div>
                            <div class="contacts-new">

								<input type="hidden" name="id" id="id" value="<% out.print(locality.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(locality.getName()); %>" placeholder="pune">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the locality.
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
	                                        int tehsil_size = city_list.size(); 
	                                		String selected = "";
	                                        for(int i=0; i < tehsil_size; i++){
	                                        	if(locality.getCity().getId() == city_list.get(i).getId()){
	                                        		selected = "selected";
	                                        	}else{
	                                        		selected = "";
	                                        	}
	                                        	out.print("<option value='"+city_list.get(i).getId()+"' "+selected+">"+city_list.get(i).getName()+"</option>");
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

<!--                                 <div class="form-group"> -->
<!--                                     <label class="col-sm-2 control-label">Display Order</label> -->
<!--                                     <div class="col-sm-2"> -->
<%--                                         <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(locality.getSortOrder()); %>" placeholder="1"> --%>
<!--                                     </div> -->
<!--                                     <div class="col-sm-8"> -->
<!--                                         <div class="tooltip custom-tool-tip right"> -->
<!--                                             <div class="tooltip-arrow"></div> -->
<!--                                             <div class="tooltip-inner"> -->
<!--                                                 Display order of locality -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="true" <%if(locality.getStatus() == true){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="false" <%if(locality.getStatus() == false){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive locality.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveLocality();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showLocalityList(<% out.print(locality.getCity().getId()); %>);">Cancel</button>
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
	var msg = "";
	if($("#cityId").val() == "" || $("#cityId").val() == 'undefined'){
		if(msg!="") msg = msg+",Please select city name"; else msg = "Please select city name";
	}
	if($("#name").val() == ""){
		if(msg != "") msg = msg+",Please enter locality name"; else msg = "Please enter locality name";
	}
	if(msg != ""){
		alert(msg);
	}
	else{
		if($("#cityId").val() > 0){
			var sortOrder = 1;
			$.post("../webapi/general/locality/update", {id: $("#id").val(), name: $("#name").val(), cityId: $("#cityId").val(), sortOrder: sortOrder, status: $('input[name=status]:checked').val()}, function(data){
				if(data.status == 1){
					window.location.href = "${baseUrl}/general/locality.jsp?city_id="+$("#cityId").val();
				}else{
					alert(data.message);
				}
			});
		}else{
			alert("Select City");
		}
	}
}

function showLocalityList(cityid){
	window.location.href = "${baseUrl}/general/locality.jsp?city_id="+cityid;
}
	
</script>