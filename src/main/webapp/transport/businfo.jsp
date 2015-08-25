<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.BusInfo"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%

	SettingsImpl settings = new SettingsImpl();
	List<BusInfo> businfo = settings.getAllBusInfo();

%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Transport</a>
         	</li>
         	<li><a href="#">Bus Info</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List Buses</h2>
                     <p>Here you can add buses.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Bus Info</a>
                     <table class="table table-striped table-bordered" id="contacts-table">
                         <thead>
                             <tr>
                                 <th>Title</th>
                                 <th>Bus No.</th>
                                 <th>Vehicle No.</th>
                                 <th>Driver Name</th>
                                 <th>Contact</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
								int country_size = businfo.size(); 
								for(int i=0; i < country_size; i++){ %>
								 <tr>
									<td> <%out.print(businfo.get(i).getTitle());%>    </td>
									<td> <%out.print(businfo.get(i).getBusNumber());%>    </td>
									<td> <%out.print(businfo.get(i).getVehicleNo());%>    </td>
									<td> <%out.print(businfo.get(i).getBusDriverName());%>    </td>
									<td> <%out.print(businfo.get(i).getContactNo());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editBusInfo(<% out.print(businfo.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Bus Info</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Bus Info</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Title</label>
                         <div class="col-sm-2">
                             <input type="text" id="title" name="title" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the title of bus. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Bus No.</label>
                         <div class="col-sm-2">
                             <input type="text" id="bus_number" name="bus_number" maxlength="10" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     Bus Number. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Vehicle No.</label>
                         <div class="col-sm-2">
                             <input type="text" id="vehicle_no" name="vehicle_no" maxlength="20" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     Vehicle No. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Driver Name</label>
                         <div class="col-sm-4">
                             <input type="text" id="driver_name" name="driver_name" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-6">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     driver name. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Driver Contact</label>
                         <div class="col-sm-2">
                             <input type="text" id="contact_no" maxlength="10" onKeyPress="return checkNumber(event)" name="contact_no" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     Contact Number. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Description</label>
                         <div class="col-sm-4">
                             <textarea id="description" name="description" class="form-control" placeholder=""></textarea>
                         </div>
                         <div class="col-sm-6">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     Contact Number. 
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
	  function checkNumber(evt)
	  {
		  evt = (evt) ? evt : window.event
		  var charCode = (evt.which) ? evt.which :evt.keyCode
		   if(charCode > 31 && (charCode>57  ||   charCode < 48)){
			   return false;
		   }		  
		  return true;
	  }
	    	$('#save').click(function(){    		
	    		$.post('../webapi/settings/businfo/save',{title: $("#title").val(), busNumber: $("#bus_number").val(), vehicleNo: $("#vehicle_no").val(), busDriverName: $("#driver_name").val(), contactNo: $("#contact_no").val(), description: $("#description").val()},function(data){
	    			if(data.status == 1)
	    				window.location.href = "${baseUrl}/transport/businfo.jsp";
	    			else
	    				alert(data.message);
	    		},'json');
	    		
	    	});
			function editBusInfo(id){
				window.location.href = "${baseUrl}/transport/editbusinfo.jsp?id="+id;
			}
  	</script>