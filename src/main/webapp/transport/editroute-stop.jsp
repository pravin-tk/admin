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
	int stop_size = 0;
	int stop_size_all = 0;
	int routeStopId = Integer.parseInt(request.getParameter("routestop_id"));
	String str_selected = "";
	List<Route> route_list = null;
	List<BusStop> stop_list_all = null;
	List<BusRouteStop> route_stop_detail = null;
	BusRouteStop rowRouteStop = null ;
	stop_list_all = new TransportImpl().getStopList();
	stop_size_all = stop_list_all.size(); 
	route_list = new TransportImpl().getRoute();
	route_size = route_list.size();
	if (routeStopId > 0) {
		route_stop_detail = new TransportImpl().getRouteStopById(routeStopId);
		if(route_stop_detail.size() > 0)
			rowRouteStop = route_stop_detail.get(0);
		System.out.println("JJ="+rowRouteStop.getId());
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
                    <li class="active">Route Stop Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update Stop info for a route</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Route Stop</h2>
								<div id="aierror" class="bg-danger" ></div>
								<input type="hidden" name="id" id="id" value="<% out.print(rowRouteStop.getId()); %>"/>
                                         <div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Route</label>
	                                <div class="col-sm-2">
	                                    <select name="routeId" id="routeId" class="form-control">
	                                        <option value="">Select Route</option>
	                                        <%
	                                        for(int i=0; i < route_size; i++){
	                                        	if(route_list.get(i).getId() ==  rowRouteStop.getRoute().getId() )
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
	                                        	if(stop_list_all.get(i).getId()==  rowRouteStop.getBusStop().getId() )
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
                                        <input type="text" class="form-control" name="stopno" id="stopno" placeholder="04" value="<% out.print(rowRouteStop.getBusStopNo()); %>">
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
                                        <input type="text" class="form-control" name="pickuptime" id="pickuptime"  placeholder="hh:mm am/pm">
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
                                        <input type="text" class="form-control" name="droptime" id="droptime" placeholder="hh:mm am/pm" value = "hours_am_pm('<% rowRouteStop.getBusDropTime(); %>');">
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
                                        <button type="button" class="btn btn-success" onclick="saveRouteStopInfo();">Update</button>
                         <button class="btn btn-default list-id list-contacts" type="button"   
                         onclick="showRouteStopList(<% out.print(rowRouteStop.getBusStop().getId()); %>);">Cancel</button>
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
$("#pickuptime").val(hours_am_pm("<%out.print(rowRouteStop.getBusPickTime());%>")); 
$("#droptime").val(hours_am_pm("<%out.print(rowRouteStop.getBusDropTime()); %>"));
function saveRouteStopInfo(){
	var strcat=$.trim($("#tname").val());
	var stopno = $.trim($("#stopno").val());
	if($("#pickuptime").val() != "")
	var pickuptime = getTime($("#pickuptime").val()); 
	if($("#droptime").val() != "")
	var droptime = getTime($("#droptime").val());
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
		$.post("../webapi/transport/routestop/update", {id: $("#id").val(), stopno: $("#stopno").val(),
			routeId: $("#routeId").val(),stopId: $("#stopId").val(), pickuptime: pickuptime, 
			droptime: droptime}, function(data){
			window.location.href = "${baseUrl}/transport/route-stop.jsp?route_id="+$("#routeId").val();
		});
	}
}

function showRouteStopList(id){
	window.location.href = "${baseUrl}/transport/route-stop.jsp?route_id="+id;
}
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