<%@page import="org.school.admin.model.BoardType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));	
	List<BoardType> detail = null;
	BoardType boardtype = null;
	if (id > 0) {
		detail = new SettingsImpl().getBoardTypeById(id);
		if(detail.size() > 0)
			boardtype = detail.get(0);
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
                    <li class="active">Area Board Type</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update Board Type detail</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Board Type</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(boardtype.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(boardtype.getBoardName()); %>" placeholder="C.B.S.E.">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the board type.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveBoardType();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showBoardTypeList();">Cancel</button>
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
<script type="text/javascript">
	function saveBoardType(){
		$.post("../webapi/settings/boardtype/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
			if(data.status == 1)
				window.location.href = "${baseUrl}/settings/boardtype.jsp";
			else
				alert(data.message);
		});
	}
	
	function showBoardTypeList(){
		window.location.href = "${baseUrl}/settings/boardtype.jsp";
	}
</script>