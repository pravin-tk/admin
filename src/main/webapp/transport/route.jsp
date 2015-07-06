<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.TransportImpl"%>
<%@page import="org.school.admin.model.Route"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
TransportImpl transport = new TransportImpl();
List<Route> listRoute = transport.getRoute();
int route_size = listRoute.size(); 
%>               
            <div class="col-sm-12 col-md-12  main">
                
               <ol class="breadcrumb">
                    <li><a href="#">General</a>
                    </li>
                    <li><a href="#">Route</a>
                    </li>
                    <li class="active">Add</li>
                </ol> 
                    <div id="myTabContent" class="tab-content">
                     
                        <!--Contacts tab starts-->
                      <!--   <div class="tab-pane fade" id="country" aria-labelledby="contacts-tab"> -->
                            <div class="contacts-list">
                                <h2>List route</h2>
                                <p>Here you can add or deactivate route. You can define route details, .</p>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Route</a>



                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Status</th>                                           
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <% 	
										for(int i=0; i < route_size; i++){ %>
											 <tr>
                                            	<td> <%out.print(listRoute.get(i).getId());%>   </td>
												<td> <%out.print(listRoute.get(i).getName());%>    </td>
												<td> 
												<%	if(listRoute.get(i).getStatus()== 1){
	                                        			out.print("<span class='label label-success'>Active</span>");
	                                        		} else {
												    	out.print("<span class='label label-warning'>Inactive</span>");
													} 
												%>  
											  </d>
											  	<td class="alignRight">
                             						<a href="javascript:editRoute(<% out.print(listRoute.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            	</td>
                                            </tr>
										
                                     <%} %>
                                    </tbody>
                                </table>

                                  <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Category</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h4>Add New Route</h4>
                                <div id="aerror" class="bg-danger" ></div>
     						<form method="post" action="" class="form-horizontal" id="submitForm">     						
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" id="txtname" class="form-control" placeholder="Yerwada to Kalyani nagar">
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
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="1" checked="">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="0">Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                If inactive route  name will not appear in dropdowns
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Description</label>
                                    <div class="col-sm-2">
                                        <textarea class="form-control" name="desc" id="desc" placeholder="Description..."></textarea>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Description
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" id='btnsave_route' class="btn btn-success">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                                    </div>
                                </div> </form>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                       
                 <!--    </div> -->

               
            </div>
       
     <script type="text/javascript">
    	$('#btnsave_route').click(function(){ 
    		var strcat=$.trim($("#txtname").val());
    		if (strcat == "")
    			{
    			$("#aerror").html("Route name cannot be empty");
    			$("#txtname").addClass('has-error');    			
    		}else {
	    		$.post('../webapi/transport/route/save',{name: $("#txtname").val(),
	    			status: $('input[name=status]:checked').val(),
	    			desc: $("#desc").val()},function(data){
	    			if(data.status == 1){
	    				window.location.href = "${baseUrl}/transport/route.jsp";
	    			}else
	    				$("#aerror").html(data.message);
	    				
	    		},'json');
    		}
    		
    	});
    
	function editRoute(id){
		window.location.href = "${baseUrl}/transport/editroute.jsp?routeid="+id;
	}
    	</script>
    <%@include file="../footer.jsp" %>
   

 