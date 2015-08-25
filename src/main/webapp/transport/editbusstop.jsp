<%@page import="org.school.admin.model.BusStop"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));	
	List<BusStop> detail = null;
	BusStop busstop = null;
	if (id > 0) {
		detail = new SettingsImpl().getBusStopById(id);
		if(detail.size() > 0)
			busstop = detail.get(0);
	}
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Transport</a>
                    </li>
                    <li class="active">Bus Stops</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update bus stop</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Bus Stop</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(busstop.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(busstop.getName()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the bus stop name.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
			                         <label class="col-sm-2 control-label">Pickup Latitude</label>
			                         <div class="col-sm-2">
			                             <input type="text" id="pickup_latitude" name="pickup_latitude" class="form-control" value="<% out.print(busstop.getPickupLatitude()); %>">
			                         </div>
			                         <div class="col-sm-8">
			                             <div class="tooltip custom-tool-tip right">
			                                 <div class="tooltip-arrow"></div>
			                                 <div class="tooltip-inner">
			                                     Pickup Latitude. 
			                                 </div>
			                             </div>
			                         </div>
			                    </div>
			                    
			                    <div class="form-group">
			                         <label class="col-sm-2 control-label">Pickup Longitude</label>
			                         <div class="col-sm-2">
			                             <input type="text" id="pickup_longitude" name="pickup_longitude" class="form-control" value="<% out.print(busstop.getPickupLongitude()); %>">
			                         </div>
			                         <div class="col-sm-8">
			                             <div class="tooltip custom-tool-tip right">
			                                 <div class="tooltip-arrow"></div>
			                                 <div class="tooltip-inner">
			                                     Pickup Longitude
			                                 </div>
			                             </div>
			                         </div>
			                    </div>
			                    
			                    <div class="form-group">
			                         <label class="col-sm-2 control-label">Drop Latitude</label>
			                         <div class="col-sm-2">
			                             <input type="text" id="drop_latitude" name="drop_latitude" class="form-control" value="<% out.print(busstop.getDropLatitude()); %>">
			                         </div>
			                         <div class="col-sm-8">
			                             <div class="tooltip custom-tool-tip right">
			                                 <div class="tooltip-arrow"></div>
			                                 <div class="tooltip-inner">
			                                     Drop Latitude
			                                 </div>
			                             </div>
			                         </div>
			                    </div>
			                    
			                    <div class="form-group">
			                         <label class="col-sm-2 control-label">Drop Longitude</label>
			                         <div class="col-sm-2">
			                             <input type="text" id="drop_longitude" name="drop_longitude" class="form-control" value="<% out.print(busstop.getDropLongitude()); %>">
			                         </div>
			                         <div class="col-sm-8">
			                             <div class="tooltip custom-tool-tip right">
			                                 <div class="tooltip-arrow"></div>
			                                 <div class="tooltip-inner">
			                                     Drop Longitude
			                                 </div>
			                             </div>
			                         </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label class="col-sm-2 control-label">Status</label>
			                        <div class="col-sm-2">
			                            <label class="radio-inline">
			                                <input type="radio" name="status" id="status" value="true" <% if(busstop.getStatus() == true){ %>checked="checked"<% }%>>Active
			                            </label>
			                            <label class="radio-inline">
			                                <input type="radio" name="status" id="status" value="false" <% if(busstop.getStatus() == false){ %>checked="checked"<% }%>>Inactive
			                            </label>
			                        </div>
			                        <div class="col-sm-8">
			                            <div class="tooltip custom-tool-tip right">
			                                <div class="tooltip-arrow"></div>
			                                <div class="tooltip-inner">
			                                    Active/Inactive.
			                                </div>
			                            </div>
			                        </div>
			                    </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveBusStop();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showBusStopList();">Cancel</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                    </div>
               </form>
 		</div>
    <!-- /Right main content -->
<%@ include file="../footer.jsp" %>
<script type="text/javascript">
	function saveBusStop(){
		$.post("../webapi/settings/busstop/update", {id: $("#id").val(),
			name: $("#name").val(),
			pickupLatitude: $("#pickup_latitude").val(), 
			pickupLongitude: $("#pickup_longitude").val(), 
			dropLatitude: $("#drop_latitude").val(), 
			dropLongitude: $("#drop_longitude").val(), 
			status: $('input[name=status]:checked').val()}, function(data){
				if(data.status == 0)
					alert(data.message);
				else
					window.location.href = "${baseUrl}/transport/busstop.jsp";
		});
	}
	
	function showBusStopList(){
		window.location.href = "${baseUrl}/transport/busstop.jsp";
	}
</script>