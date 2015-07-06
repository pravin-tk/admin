<%@page import="org.school.admin.model.BusInfo"%>
<%@page import="org.school.admin.model.BusStop"%>
<%@page import="org.school.admin.model.Route"%>
<%@page import="org.school.admin.model.BusRouteStop"%>
<%@page import="org.school.admin.model.BusRoute"%>
<%@page import="org.school.admin.dao.TransportImpl"%>

<%@page import="java.util.List"%>
<%
	int route_id = 0;
	int route_size = 0;
	String str_selected = null;
	List<BusRouteStop> stop_list = null;
	List<Route> route_list = null;
	List<BusStop> stop_list_all = null;
	int stop_size = 0;
	int stop_size_all = 0;
	stop_list_all = new TransportImpl().getStopList();
	stop_size_all = stop_list_all.size(); 
	System.out.println("stop size="+stop_size_all);
  if (request.getParameterMap().containsKey("route_id")) {
 		route_id = Integer.parseInt(request.getParameter("route_id"));
 		if (route_id > 0) {
 			stop_list = new TransportImpl().getStopByRouteId(route_id);
 			stop_size = stop_list.size(); 
 		}
 	}

	route_list = new TransportImpl().getRoute();
	route_size = route_list.size(); 
	System.out.println("stop count for route_id="+route_id + " IS = " +stop_list);
	
	
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Route Stop</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                        	
                            <div class="contacts-list">
                             <h2>List Route Stop</h2>
                                <p>Here you can add or deactivate route stop. You can define route stop details, .</p>
                            <div class="form-group">
				                <label class="col-sm-2 control-label">Select Route</label>
				                <div class="col-sm-8">
					                <select name="schrouteId" id="schrouteId" class="form-control">
					                    <option value="">Select Route item</option>
					                    <%
					                    String search_route_sel = ""; 					                	
						                    for(int i=0; i < route_size; i++){
						                    	if(route_list.get(i).getId() == route_id){
						                    		search_route_sel = "selected";
						                    	}else{
						                    		search_route_sel = "";
						                    	}
						                    	out.print("<option value='"+route_list.get(i).getId()+"' "+search_route_sel+">"+route_list.get(i).getName()+"</option>");
						                    }
					                
					                    %>
					                </select>
				                </div>
			                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Bus Stop</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Stop Name</th>
                                            <th>Stop No</th>
                                            <th>Latitude</th>
                                            <th>Longitude</th>
                                            <th>Pickup Time</th>
                                            <th>Drop Time</th>
                                            
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                    
                                    	if(stop_size>0)
	                                        for(int i=0; i < stop_size; i++){
	                                        %>
	                                        <tr>
	                                        <td><% out.print(stop_list.get(i).getBusStop().getName()); %></td>	 
	                                            <td><% out.print(stop_list.get(i).getBusStopNo()); %></td>	   
	                                            <td><% out.print(stop_list.get(i).getBusStop().getPickupLatitude()); %>,<% out.print(stop_list.get(i).getBusStop().getPickupLongitude()); %> </td>	
	                                             <td><% out.print(stop_list.get(i).getBusStop().getDropLatitude()); %>,<% out.print(stop_list.get(i).getBusStop().getDropLongitude()); %></td>
	                                            <td><% out.print(stop_list.get(i).getBusPickTime()) ; %></td>
	                                             <td><% out.print(stop_list.get(i).getBusDropTime()); %></td>
	                                            <td class="alignRight">
	                                            	<a href="javascript:editrouteStop(<% out.print(stop_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
	                                            </td>
	                                        </tr>
	                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Bus Stop</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Stop Info</h2>
 								<div id="aerror" class="bg-danger" ></div>

                                
                                <div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Route</label>
	                                <div class="col-sm-2">
	                                    <select name="routeId" id="routeId" class="form-control">
	                                        <option value="">Select Route</option>
	                                        <%
	                                        for(int i=0; i < route_size; i++){
	                                        	if(route_list.get(i).getId()==  route_id )
	                                        			str_selected = "SELECTED";
	                                        	else
	                                        		str_selected = "";
	                                        	
	                                        	out.print("<option "+ str_selected + " value='"+route_list.get(i).getId()+"'>"+route_list.get(i).getName()+"</option>");
	                                         }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Category of Item.
                                            </div>
                                        </div>
                                    </div>
	                            </div>
                                
                                 <div class="form-group">
                                    <label class="col-sm-2 control-label">Stop</label>
                                    <div class="col-sm-2">
                                        <select name="stopId" id="stopId" class="form-control">
	                                        <option value="">Select Bus Stop</option>
	                                        <%
	                                        for(int i=0; i < stop_size_all; i++){
	                                        	if(stop_list_all.get(i).getId()==  route_id )
	                                        			str_selected = "SELECTED";
	                                        	else
	                                        		str_selected = "";
	                                        	
	                                        	out.print("<option "+ str_selected + " value='"+stop_list_all.get(i).getId()+"'>"+stop_list_all.get(i).getName()+"</option>");
	                                         }
	                                        %>
	                                    </select>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the bus stop
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Stop No</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="stopno" id="stopno" placeholder="04">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the stop no in order of pick up and drop
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-sm-2 control-label">Pickup Time</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="pickuptime" id="pickuptime"  placeholder="hh:mm:ss">
                                    </div>
  
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Pick up time at bus stop
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Drop Time</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="droptime" id="droptime" placeholder="hh:mm:ss">
                                    </div>
  
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Drop time at bus stop
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                        
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveRouteStop();">Save</button>
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
function saveRouteStop(){
	var stopno = $.trim($("#stopno").val());
	var pickuptime = $("#pickuptime").val(); 
	var droptime = $("#droptime").val();
	var routeId = $("#routeId").val();
	var stopId = $("#stopId").val();
	if (stopno == "" ) {
		$("#aerror").html("Bus stop no cannot be empty");
		$("#stopno").addClass('has-error');
	} else if ($("#routeId").val() == "" ) {
			$("#aerror").html("Route not selected");
			$("#routeId").addClass('has-error'); 	
	} else if ($("#stopId").val() == "" ) {
		$("#aerror").html("Stop not selected");
		$("#stopId").addClass('has-error'); 	
		
	} else { 
		$.post("../webapi/transport/routestop/save", {stopno: $("#stopno").val(),
		routeId: $("#routeId").val(),stopId: $("#stopId").val(), pickuptime: $("#pickuptime").val(), 
		droptime: $("#droptime").val(), }, function(data){
		if($("#routeId").val() > 0){
			if(data.status == 1)
				window.location.href = "${baseUrl}/transport/route-stop.jsp?route_id="+$("#routeId").val();
			else
				$("#aerror").html(data.message);
		}else{
			$("#aerror").html('Select Category');
		}
	});
	}
}

function editrouteStop(id){
	window.location.href = "${baseUrl}/transport/editroute-stop.jsp?routestop_id="+id;
}

$("#schrouteId").change(function(){
	if($(this).val() > 0){
		window.location.href = "${baseUrl}/transport/route-stop.jsp?route_id="+$(this).val();
	} else {
		//alert("select Category");
		$("#aerror").html('Select Category');
	}
});
$(function() {
	$('#droptime').timepicker({
        minuteStep: 1,
        secondStep: 5,
        showSeconds: true,
        showMeridian: false
    });
	$('#pickuptime').timepicker({
        minuteStep: 1,
        secondStep: 5,
        showSeconds: true,
        showMeridian: false
    });
	
  });
	
</script>