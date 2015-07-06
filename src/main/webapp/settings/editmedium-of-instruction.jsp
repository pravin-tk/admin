<%@page import="org.school.admin.model.MediumType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<MediumType> medium_detail = null;
	MediumType medium = null;
	if (id > 0) {
		medium_detail = new SettingsImpl().getMediumTypeById(id);
		if(medium_detail.size() > 0)
			medium = medium_detail.get(0);
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
                    <li class="active">Medium of Instruction Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update medium of instruction</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Medium of Instruction</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(medium.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(medium.getTitle()); %>" placeholder="English">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the medium of instruction.
                                            </div>
                                        </div>
                                    </div>
                                </div>

								<div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"><% out.print(medium.getDescription()); %></textarea>
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
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveMediumType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showMediumList();">Cancel</button>
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
function saveMediumType(){
	$.post("../webapi/settings/mediumtype/update", {id: $("#id").val(), title: $("#title").val(), description: $("#description").val()}, function(data){
		window.location.href = "${baseUrl}/settings/medium-of-instruction.jsp";
	});
}

function showMediumList(){
	window.location.href = "${baseUrl}/settings/medium-of-instruction.jsp";
}
	
</script>