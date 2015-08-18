<%@page import="org.school.admin.model.SchoolClassificationType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.ServletContext" %>
<%
	int id = Integer.parseInt(request.getParameter("id"));	
	List<SchoolClassificationType> schoolclassification_list = null;
	SchoolClassificationType schoolclassification = null;
	if (id > 0) {
		schoolclassification_list = new SettingsImpl().getSchoolClassificationTypeById(id);
		if(schoolclassification_list.size() > 0)
			schoolclassification = schoolclassification_list.get(0);
	}
	ServletContext context = pageContext.getServletContext();
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Settings</a>
                    </li>
                    <li class="active">School Classification Type Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="edtSchoolClassificationform" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update school classification type</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update School Classification Type</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(schoolclassification.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(schoolclassification.getName()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                School Classification Type. 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                 <div class="form-group">
                                    <label class="col-sm-2 control-label">Image</label>
                                    <div class="col-sm-6">
                                        <input type="file" class="form-control" name="image" id="image" placeholder="">
                                    </div>
                                    <div class="col-sm-2">
						<img src="<% out.print(context.getInitParameter("s3_base_url")+schoolclassification.getImage()); %>" width="90" height="90"/>
					</div><%  %>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                               Change School Classification Type image. 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveSchoolClassification();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showSchoolClassificationList();">Cancel</button>
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
 <script src="${baseUrl}/js/jquery.form.js"></script>
<script type="text/javascript">
	function saveSchoolClassification(){
		
		var options = {
				target : '#error-contact-detail', // target element(s) to be updated with server response 
				beforeSubmit : showEditSchoolClassificationRequest, // pre-submit callback 
				success :  showClassificationResponse,
				url : '../webapi/settings/schoolclassificationtype/update',
				semantic : true,
				dataType : 'json'
			};
		
// 		$.post("../webapi/settings/schoolclassificationtype/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
// 			window.location.href = "${baseUrl}/settings/schoolclassificationtype.jsp";
		//});
		$('#edtSchoolClassificationform').ajaxSubmit(options);
		
	}
	
	
	function showEditSchoolClassificationRequest(formData, jqForm, options) {
		var queryString = $.param(formData);
		$('#error-school-classification').hide();
		if($("#image").val() == "" || $("#image").val() == null){
			alert("Please select image.")
		}
		return true;
	}
	
	function showClassificationResponse(responseText, statusText, xhr, $form) {
	   		window.location.href = "${baseUrl}/settings/schoolclassificationtype.jsp";
		}

	
	
	
	
	
	function showSchoolClassificationList(){
		window.location.href = "${baseUrl}/settings/schoolclassificationtype.jsp";
	}
</script>