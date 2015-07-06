<%@page import="org.school.admin.model.ExamType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<ExamType> exam_detail = null;
	ExamType exam = null;
	if (id > 0) {
		exam_detail = new SettingsImpl().getExamTypeById(id);
		if(exam_detail.size() > 0)
			exam = exam_detail.get(0);
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
                    <li class="active">Exam Type Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update exam Type</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Exam Type</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(exam.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(exam.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the exam type.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" name="description" id="description"><% out.print(exam.getDescription()); %></textarea>
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
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(exam.getSortOrder()); %>" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of exam type.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveExamType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showExamList();">Cancel</button>
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
function saveExamType(){
	$.post("../webapi/settings/examtype/update", {id: $("#id").val(), title: $("#title").val(), description: $("#description").val(), sortOrder: $("#sortOrder").val()}, function(data){
		window.location.href = "${baseUrl}/settings/examtype.jsp";
	});
}

function showExamList(){
	window.location.href = "${baseUrl}/settings/examtype.jsp";
}
	
</script>