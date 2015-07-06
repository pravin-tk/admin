<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.FacilityImpl"%>
<%@page import="org.school.admin.model.InfrastructureCategory"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
FacilityImpl facility = new FacilityImpl();
List<InfrastructureCategory> listInfraCategory =null;
listInfraCategory = facility.getInfrastructureCategory();
int infracat_size = listInfraCategory.size(); 
%>               
            <div class="col-sm-12 col-md-12  main">
                
               <ol class="breadcrumb">
                    <li><a href="#">General</a>
                    </li>
                    <li><a href="#">Infrastructure Category</a>
                    </li>
                    <li class="active">Add</li>
                </ol> 
                    <div id="myTabContent" class="tab-content">
                     
                        <!--Contacts tab starts-->
                      <!--   <div class="tab-pane fade" id="country" aria-labelledby="contacts-tab"> -->
                            <div class="contacts-list">
                                <h2>List infrastructure category</h2>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Category</a>
                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <% 	
                                         if(infracat_size > 0){
										for(int i=0; i < infracat_size; i++){ %>
											 <tr>
                                            	<td> <%out.print(listInfraCategory.get(i).getId());%>   </td>
												<td> <%out.print(listInfraCategory.get(i).getName());%>    </td>
											  	<td class="alignRight">
                             						<a href="javascript:editCategory(<% out.print(listInfraCategory.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            	</td>
                                            </tr>
										
                                     <%}} %>
                                    </tbody>
                                </table>

                                  <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Category</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h4>Add New Activity Category</h4>
                                <div id="aerror" class="bg-danger" ></div>
     						<form method="post" action="" class="form-horizontal" id="submitForm">     						
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" id="txtname" class="form-control" placeholder="High Tech Infa">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the category. 
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" id='btnsave_activity' class="btn btn-success">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                                    </div>
                                </div> </form>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                       
                 <!--    </div> -->

               
            </div>
       
     <script type="text/javascript">
    	$('#btnsave_activity').click(function(){ 
    		var strcat=$.trim($("#txtname").val());
    		if (strcat == "")
    			{
    			$("#aerror").html("Category name cannot be empty");
    			$("#txtname").addClass('has-error');    			
    		}else {
	    		$.post('../webapi/facility/infracat/save',{name: $("#txtname").val()},function(data){
	    			if(data.status == 1){
	    				window.location.href = "${baseUrl}/facility/infra-cat.jsp";
	    			}else
	    				$("#aerror").html(data.message);
	    				
	    		},'json');
    		}
    		
    	});
    
	function editCategory(id){
		window.location.href = "${baseUrl}/facility/editinfra-cat.jsp?scat_id="+id;
	}
    	</script>
    <%@include file="../footer.jsp" %>
