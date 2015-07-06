<%@page import="org.school.admin.model.Accessories"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));	
	List<Accessories> detail = null;
	Accessories accessory = null;
	if (id > 0) {
		detail = new SettingsImpl().getAccessoryById(id);
		if(detail.size() > 0)
			accessory = detail.get(0);
	}
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Settings</a>
                    </li>
                    <li class="active">Accessory Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update accessory detail</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Accessory</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(accessory.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(accessory.getName()); %>" placeholder="Books">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the accessory.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveAccessory();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showAccessoryList();">Cancel</button>
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
	function saveAccessory(){
		$.post("../webapi/settings/accessory/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
			window.location.href = "${baseUrl}/settings/accessory.jsp";
		});
	}
	
	function showAccessoryList(){
		window.location.href = "${baseUrl}/settings/accessory.jsp";
	}
</script>