<%@page import="org.school.admin.model.Cast"%>
<%@page import="org.school.admin.model.Accessories"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));	
	List<Cast> castDetail = null;
	Cast cast = null;
	if (id > 0) {
		castDetail = new SettingsImpl().getCastById(id);
		if(castDetail.size() > 0)
			cast = castDetail.get(0);
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
                    <li class="active">Cast Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="cast" aria-labelledby="contacts-tab">
                            <div class="cast-list">
                                <p>Here you can update cast detail</p>

                            </div>
                            <div class="cast-new">
                                <h2>Update Cast</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(cast.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(cast.getName()); %>" placeholder="ST">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the cast.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveCast();">Update</button>
                                        <button class="btn btn-default list-id list-cast" type="button" onclick="showCastList();">Cancel</button>
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
	function saveCast(){
		$.post("../webapi/settings/cast/update", {id: $("#id").val(), name: $("#name").val()}, function(data){
			window.location.href = "${baseUrl}/settings/cast.jsp";
		});
	}
	
	function showCastList(){
		window.location.href = "${baseUrl}/settings/cast.jsp";
	}
</script>