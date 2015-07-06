<%@page import="org.school.admin.model.StreamType"%>
<%@page import="org.school.admin.model.MediumType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<StreamType> stream_detail = null;
	StreamType stream = null;
	if (id > 0) {
		stream_detail = new SettingsImpl().getStreamTypeById(id);
		if(stream_detail.size() > 0)
			stream = stream_detail.get(0);
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
                    <li class="active">Stream Type Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update Stream Type</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Stream Type</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(stream.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(stream.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the stream type.
                                            </div>
                                        </div>
                                    </div>
                                </div>

								<div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"><% out.print(stream.getDescription()); %></textarea>
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
                                        <button type="button" class="btn btn-success" onclick="saveStreamType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showStreamList();">Cancel</button>
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
function saveStreamType(){
	$.post("../webapi/settings/streamtype/update", {id: $("#id").val(), title: $("#title").val(), description: $("#description").val()}, function(data){
		window.location.href = "${baseUrl}/settings/streamtype.jsp";
	});
}

function showStreamList(){
	window.location.href = "${baseUrl}/settings/streamtype.jsp";
}
	
</script>