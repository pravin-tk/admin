<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.BusStop"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%

	SettingsImpl settings = new SettingsImpl();
	List<BusStop> busstop = settings.getAllBusStop();

%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">Bus Stop</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List Bus Stops</h2>
                     <p>Here you can add bus Stops.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Bus Stop</a>
                     <table class="table table-striped table-bordered" id="contacts-table">
                         <thead>
                             <tr>
                                 <th>Name</th>
                                 <th>Pickup Point</th>
                                 <th>Drop Point</th>
                                 <th>Status</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
								int country_size = busstop.size(); 
								for(int i=0; i < country_size; i++){ %>
								 <tr>
									<td> <%out.print(busstop.get(i).getName());%>    </td>
									<td> <%out.print(busstop.get(i).getPickupLatitude());%>, <%out.print(busstop.get(i).getPickupLongitude());%>    </td>
									<td> <%out.print(busstop.get(i).getDropLatitude());%>, <%out.print(busstop.get(i).getDropLongitude());%>    </td>
									<td><% if(busstop.get(i).getStatus() == true) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
								  	<td class="alignRight">
                  						<a href="javascript:editBusStop(<% out.print(busstop.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Bus Stop</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Bus Stop</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Name</label>
                         <div class="col-sm-4">
                             <input type="text" id="name" name="name" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-6">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the name of bus stop. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Pickup Latitude</label>
                         <div class="col-sm-2">
                             <input type="text" id="pickup_latitude" name="pickup_latitude" class="form-control" placeholder="">
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
                             <input type="text" id="pickup_longitude" name="pickup_longitude" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     Pickup Longitude. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Drop Latitude</label>
                         <div class="col-sm-2">
                             <input type="text" id="drop_latitude" name="drop_latitude" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     pickup_longitude. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Drop Longitude</label>
                         <div class="col-sm-2">
                             <input type="text" id="drop_longitude" name="drop_longitude" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     Drop Longitude. 
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
                                    Active/Inactive.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                    	<div class="col-sm-2">
                            <button type="button" id='save' class="btn btn-success">Save</button>
                            <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                        </div>
                    </div> 
                    </form>
              	</div>
          	</div>
 	</div>
    <%@include file="../footer.jsp" %>
	  <script type="text/javascript">
	    	$('#save').click(function(){    		
	    		$.post('../webapi/settings/busstop/save',{name: $("#name").val(), pickupLatitude: $("#pickup_latitude").val(), pickupLongitude: $("#pickup_longitude").val(), dropLatitude: $("#drop_latitude").val(), dropLongitude: $("#drop_longitude").val(), status: $('input[name=status]:checked').val()},function(data){
	    			if(data.status == 1)
	    				window.location.href = "${baseUrl}/settings/busstop.jsp";
	    			else
	    				alert(data.message);
	    		},'json');
	    		
	    	});
			function editBusStop(id){
				window.location.href = "${baseUrl}/settings/editbusstop.jsp?id="+id;
			}
  	</script>