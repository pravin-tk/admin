<%@page import="org.school.admin.model.BusInfo"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));	
	List<BusInfo> detail = null;
	BusInfo businfo = null;
	if (id > 0) {
		detail = new SettingsImpl().getBusInfoById(id);
		if(detail.size() > 0)
			businfo = detail.get(0);
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
                    <li class="active">Bus Info</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update bus info</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Bus Info</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(businfo.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(businfo.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the bus title.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								<div class="form-group">
			                         <label class="col-sm-2 control-label">Bus No.</label>
			                         <div class="col-sm-2">
			                             <input type="text" id="bus_number" maxlength="10" name="bus_number" class="form-control" value="<% out.print(businfo.getBusNumber()); %>">
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
			                             <input type="text" id="vehicle_no" maxlength="20" name="vehicle_no" class="form-control" value="<% out.print(businfo.getVehicleNo()); %>">
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
			                             <input type="text" id="driver_name" name="driver_name" class="form-control" value="<% out.print(businfo.getBusDriverName()); %>">
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
			                             <input type="text" id="contact_no" name="contact_no" class="form-control" value="<% out.print(businfo.getContactNo()); %>">
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
			                             <textarea id="description" name="description" class="form-control" placeholder=""><% out.print(businfo.getDescription()); %></textarea>
			                         </div>
			                         <div class="col-sm-6">
			                             <div class="tooltip custom-tool-tip right">
			                                 <div class="tooltip-arrow"></div>
			                                 <div class="tooltip-inner">
			                                     Description. 
			                                 </div>
			                             </div>
			                         </div>
			                    </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveBusInfo();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showBusInfoList();">Cancel</button>
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
	function saveBusInfo(){
		$.post("../webapi/settings/businfo/update", {id: $("#id").val(), title: $("#title").val(), busNumber: $("#bus_number").val(), vehicleNo: $("#vehicle_no").val(), busDriverName: $("#driver_name").val(), contactNo: $("#contact_no").val(), description: $("#description").val()}, function(data){
			window.location.href = "${baseUrl}/transport/businfo.jsp";
		});
	}
	
	function showBusInfoList(){
		window.location.href = "${baseUrl}/transport/businfo.jsp";
	}
</script>