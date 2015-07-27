<%@page import="org.school.admin.model.Route"%>
<%@page import="org.school.admin.dao.TransportImpl"%>
<%@page import="java.util.List"%>
<%
	int routeid = Integer.parseInt(request.getParameter("routeid"));	
	List<Route> routeDetail = null;
	Route rowroute = null;
	if (routeid > 0) {
		routeDetail = new TransportImpl().getRouteById(routeid);
		if(routeDetail.size() > 0)
			rowroute = routeDetail.get(0);
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
                    <li class="active">Route Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update route detail</p>

                            </div>
                            <div class="contacts-new">
                                <h4>Update Route</h4>
								<div id="aerror" class="bg-danger" ></div>
								<input type="hidden" name="id" id="id" value="<% out.print(rowroute.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtname" id="txtname" value="<% out.print(rowroute.getName()); %>" placeholder="outdoor activity">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the route.
                                            </div>
                                        </div>
                                    </div>
                                </div>
								
                               

<!--                                 <div class="form-group"> -->
<!--                                     <label class="col-sm-2 control-label">Description</label> -->
<!--                                     <div class="col-sm-2"> -->
<%--                                         <textarea class="form-control" name="desc" id="desc" placeholder="Description..."><% //out.print(rowroute.); %></textarea> --%>
<!--                                     </div> -->
<!--                                     <div class="col-sm-8"> -->
<!--                                         <div class="tooltip custom-tool-tip right"> -->
<!--                                             <div class="tooltip-arrow"></div> -->
<!--                                             <div class="tooltip-inner"> -->
<!--                                                description if any -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="1" <%if(rowroute.getStatus() == 1){ %>checked="checked"<%} %> >Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" id="status" value="0" <%if(rowroute.getStatus() == 0){ %>checked="checked"<%} %> >Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Active/Inactive route.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="saveRoute();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button"
                                         onclick="showRouteList();">Cancel</button>
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
function saveRoute(){
	var strcat=$.trim($("#txtname").val());
	if (strcat == "") {
		$("#aerror").html("Route name cannot be empty");
		$("#txtname").addClass('has-error');  
	}else {	
		$.post("../webapi/transport/route/update", {id: $("#id").val(), name: $("#txtname").val(), desc: $("#desc").val(), status: $('input[name=status]:checked').val()}, function(data){
		window.location.href = "${baseUrl}/transport/route.jsp";
		});
	}
}

function showRouteList(){
	window.location.href = "${baseUrl}/transport/route.jsp";
}
	
</script>