<%@page import="org.school.admin.model.Subject"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Short.parseShort(request.getParameter("id"));	
	List<Subject> detail = null;
	Subject subject = null;
	if (id > 0) {
		detail = new SettingsImpl().getSubjectById(id);
		if(detail.size() > 0)
			subject = detail.get(0);
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
                    <li class="active">Subject Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update Subject detail</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Subject</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(subject.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(subject.getName()); %>" placeholder="English">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Title of the subject.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveSubject();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showSubjectList();">Cancel</button>
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
	function saveSubject(){
		$.post("../webapi/settings/subject/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
			window.location.href = "${baseUrl}/settings/subject.jsp";
		});
	}	
	function showSubjectList(){
		window.location.href = "${baseUrl}/settings/subject.jsp";
	}
</script>