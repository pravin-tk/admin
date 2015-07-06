<%@page import="org.school.admin.model.PaymentMode"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<PaymentMode> paymentmode_list = new SettingsImpl().getAllPaymentMode();
	int paymentmode_size = paymentmode_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Payment Modes</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Payment Mode</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Is Online</th>
                                            <th>Status</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < paymentmode_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(paymentmode_list.get(i).getTitle()); %></td>
                                            <td><% if(paymentmode_list.get(i).getIsOnline() == 1) { out.print("<span class='label label-success'>Online</span>"); } else { out.print("<span class='label label-warning'>Offline</span>"); } %></td>
                                            <td><% if(paymentmode_list.get(i).getIsDeleted() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editPaymentMode(<% out.print(paymentmode_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Payment Mode</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Payment Mode</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the title of the payment mode.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"></textarea>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Description.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Is Online</label>
                                    <div class="col-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="isOnline" id="isOnline" value="1" checked="checked">Online
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isOnline" id="isOnline" value="0">Offline
                                        </label>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Online/Offline
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="isDeleted" id="isDeleted" value="1" checked="checked">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isDeleted" id="isDeleted" value="0">Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="savePaymentMode();">Save</button>
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
function savePaymentMode(){
	$.post("../webapi/settings/paymentmode/save", {title: $("#title").val(), description: $("#description").val(), isOnline: $('input[name=isOnline]:checked').val(), isDeleted: $('input[name=isDeleted]:checked').val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/paymentmode.jsp";
		else
			alert(data.message);
	});
}

function editPaymentMode(id){
	window.location.href = "${baseUrl}/settings/editpaymentmode.jsp?id="+id;
}	
</script>