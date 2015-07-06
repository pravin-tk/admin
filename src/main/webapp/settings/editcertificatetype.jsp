<%@page import="org.school.admin.model.CertificateType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	List<CertificateType> certificate_detail = null;
	CertificateType certificate = null;
	if (id > 0) {
		certificate_detail = new SettingsImpl().getCertificateTypeById(id);
		if(certificate_detail.size() > 0)
			certificate = certificate_detail.get(0);
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
                    <li class="active">Certificate Type Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update certificate Type</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Certificate Type</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(certificate.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="title" id="title" value="<% out.print(certificate.getTitle()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the certificate.
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Display Order</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<% out.print(certificate.getSortOrder()); %>" placeholder="1">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Display order of certificate type.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(certificate.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(certificate.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive certificate type.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveCertificate();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showCertificateList();">Cancel</button>
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
function saveCertificate(){
	$.post("../webapi/settings/certificatetype/update", {id: $("#id").val(), title: $("#title").val(), sortOrder: $("#sortOrder").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/settings/certificatetype.jsp";
	});
}

function showCertificateList(){
	window.location.href = "${baseUrl}/settings/certificatetype.jsp";
}
	
</script>