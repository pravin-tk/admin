<%@page import="org.school.admin.model.PaymentMode"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<PaymentMode> paymentmode_detail = null;
	PaymentMode paymentmode = null;
	if (id > 0) {
		paymentmode_detail = new SettingsImpl().getPaymentModeById(id);
		if(paymentmode_detail.size() > 0)
			paymentmode = paymentmode_detail.get(0);
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
                    <li class="active">Payment Mode Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update payment mode</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Payment Mode</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(paymentmode.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(paymentmode.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the mode of the payment.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"><% out.print(paymentmode.getDescription()); %></textarea>
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
                                            <input type="radio" name="isOnline" id="isOnline" value="1" <%if(paymentmode.getIsOnline() == 1){ %>checked="checked"<%} %> >Online
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isOnline" id="isOnline" value="0" <%if(paymentmode.getIsOnline() == 0){ %>checked="checked"<%} %> >Offline
                                        </label>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Online/Offline.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="isDeleted" id="isDeleted" value="1" <%if(paymentmode.getIsDeleted() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isDeleted" id="isDeleted" value="0" <%if(paymentmode.getIsDeleted() == 0){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="savePaymentMode();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showPaymentList();">Cancel</button>
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
	$.post("../webapi/settings/paymentmode/update", {id: $("#id").val(), title: $("#title").val(), description: $("#description").val(), isOnline: $('input[name=isOnline]:checked').val(), isDeleted: $('input[name=isDeleted]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/settings/paymentmode.jsp";
	});
}

function showPaymentList(){
	window.location.href = "${baseUrl}/settings/paymentmode.jsp";
}
	
</script>