<%@page import="org.school.admin.model.OccupationType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<OccupationType> occupation_detail = null;
	OccupationType occupation = null;
	if (id > 0) {
		occupation_detail = new SettingsImpl().getOccupationTypeById(id);
		if(occupation_detail.size() > 0)
			occupation = occupation_detail.get(0);
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
                    <li class="active">Parent Occupation Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update parent occupation</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Parent Occupation</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(occupation.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(occupation.getTitle()); %>" placeholder="Govr. employee">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the parent occupation.
                                            </div>
                                        </div>
                                    </div>
                                </div>

								<div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"><% out.print(occupation.getDescription()); %></textarea>
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
                                        <button type="button" class="btn btn-success" onclick="saveOccupationType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showOccupationList();">Cancel</button>
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
function saveOccupationType(){
	$.post("../webapi/settings/occupationtype/update", {id: $("#id").val(), title: $("#title").val(), description: $("#description").val()}, function(data){
		window.location.href = "${baseUrl}/settings/parent-occupation.jsp";
	});
}

function showOccupationList(){
	window.location.href = "${baseUrl}/settings/parent-occupation.jsp";
}
	
</script>