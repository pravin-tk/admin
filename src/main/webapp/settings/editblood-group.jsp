<%@page import="org.school.admin.model.BloodGroup"%>
<%@page import="org.school.admin.model.Accessories"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));	
	List<BloodGroup> bloodGroupDetail = null;
	BloodGroup bloodGroup = null;
	if (id > 0) {
		bloodGroupDetail = new SettingsImpl().getBloodGroupById(id);
		if(bloodGroupDetail.size() > 0)
			bloodGroup = bloodGroupDetail.get(0);
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
                    <li class="active">Blood Group Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="blood-group" aria-labelledby="contacts-tab">
                            <div class="blood-group-list">
                                <p>Here you can update blood group detail</p>

                            </div>
                            <div class="blood-group-new">
                                <h2>Update Blood Group</h2>

								<input type="hidden" name="id" id="id" value="<% out.print((Short)bloodGroup.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(bloodGroup.getName()); %>" placeholder="Books">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the blood group.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveBloodGroup();">Update</button>
                                        <button class="btn btn-default list-id list-blood-group" type="button" onclick="showAccessoryList();">Cancel</button>
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
	function saveBloodGroup(){
		$.post("../webapi/settings/bloodgroup/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
			if(data.status == 1)
				window.location.href = "${baseUrl}/settings/blood-group.jsp";
			else
				alert(data.message);
		});
	}
	
	function showAccessoryList(){
		window.location.href = "${baseUrl}/settings/blood-group.jsp";
	}
</script>
