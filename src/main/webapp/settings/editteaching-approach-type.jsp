<%@page import="org.school.admin.model.TeachingApproachType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));	
	List<TeachingApproachType> detail = null;
	TeachingApproachType areaunit = null;
	if (id > 0) {
		detail = new SettingsImpl().getTeachingApproachTypeById(id);
		if(detail.size() > 0)
			areaunit = detail.get(0);
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
                    <li class="active">Teaching Approach Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update Teaching Approach detail</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Teaching Approach</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(areaunit.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(areaunit.getName()); %>" placeholder="e.g visual,handcrafts">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the teaching approach.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveAreaUnit();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showAreaUnitList();">Cancel</button>
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
	function saveAreaUnit(){
		$.post("../webapi/settings/ta/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
			window.location.href = "${baseUrl}/settings/teaching-approach-type.jsp";
		});
	}
	
	function showAreaUnitList(){
		window.location.href = "${baseUrl}/settings/teaching-approach-type.jsp";
	}
</script>