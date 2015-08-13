<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.FacilityImpl"%>
<%@page import="org.school.admin.model.InfrastructureCategory"%>
<%@page import="org.school.admin.model.InfrastructureCategoryItem"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
	int category_id = 0;
	int cat_size = 0;
	String str_selected = null;
	List<InfrastructureCategoryItem> item_list = null;
	List<InfrastructureCategory> cat_list = null;
	int item_size = 0;
	if (request.getParameterMap().containsKey("scat_id")) {
		category_id = Integer.parseInt(request.getParameter("scat_id"));
		
		if (category_id > 0) {
			item_list = new FacilityImpl().getInfrastructureCategoryItem(category_id);
			item_size = item_list.size(); 
		}
	}
	cat_list = new FacilityImpl().getInfrastructureCategory();
	cat_size = cat_list.size();
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
                                <p>Here you can add or deactivate activity. You can define infrastructure category details, .</p>
                                <div class="form-group">
				                	<label class="col-sm-4 control-label">Select Category item</label>
					                <div class="col-sm-8">
						                <select name="searchcatId" id="searchcatId" class="form-control">
						                    <option value="">Select Category item</option>
						                    <%
						                    String search_cat_selected = ""; 					                	
							                    for(int i=0; i < cat_size; i++){
							                    	if(cat_list.get(i).getId() == category_id){
							                    		search_cat_selected = "selected";
							                    	}else{
							                    		search_cat_selected = "";
							                    	}
							                    	out.print("<option value='"+cat_list.get(i).getId()+"' "+search_cat_selected+">"+cat_list.get(i).getName()+"</option>");
							                    }
						                
						                    %>
						                </select>
					                </div>
				                </div>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Category</a>
                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Category</th>
                                            <th>Is optional</th>
                                            <th>Count Item Name</th>    
                                            <th>Status</th>                                       
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <% 	
										for(int i=0; i < item_size; i++){ %>
											 <tr>
                                            	<td> <%out.print(item_list.get(i).getName());%>   </td>
                                            	<td> <%out.print(item_list.get(i).getInfrastructureCategory().getName());%>   </td>
												<td> 
												<% 	
												if (item_list.get(i).getIsOptional()== 1) {
													out.print("Yes");
												} else {
													out.print("No");
												}
												%>
												</td>
												<td> <%out.print(item_list.get(i).getCountItemName());%>   </td>
												<td> 
												<%	if(item_list.get(i).getStatus()== 1){
	                                        			out.print("<span class='label label-success'>Active</span>");
	                                        		} else {
												    	out.print("<span class='label label-warning'>Inactive</span>");
													} 
												%>  
											  </td>
											  	<td class="alignRight">
                             						<a href="javascript:editCategory(<% out.print(item_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            	</td>
                                            </tr>
										
                                     <%} %>
                                    </tbody>
                                </table>

                                  <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Category</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h4>Add New Infrastructure Category</h4>
                                	<div id="aierror" class="bg-danger" ></div>
     						<form method="post" action="" class="form-horizontal" id="infracatitemForm" 
     						  novalidate="novalidate" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="infracatitemname" id="infracatitemname" class="form-control" placeholder="library">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the category. 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
	                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Category</label>
	                                <div class="col-sm-4">
	                                    <select name="infracatid" id="infracatid" class="form-control">
	                                        <option value="">Select Category</option>
	                                        <%
	                                        for(int i=0; i < cat_size; i++){
	                                        	if(cat_list.get(i).getId()==  category_id )
	                                        			str_selected = "SELECTED";
	                                        	else
	                                        		str_selected = "";
	                                        	
	                                        	out.print("<option "+ str_selected + " value='"+cat_list.get(i).getId()+"'>"+cat_list.get(i).getName()+"</option>");
	                                         }
	                                        %>
	                                    </select>
	                                </div>
	                                <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Category of Item.
                                            </div>
                                        </div>
                                    </div>
	                            </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Is Optional</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="isOptional" value="1" checked="">Yes
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isOptional" value="0">No
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                If Optional category.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Count Item Name</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="countItemName" id="countItemName" class="form-control" placeholder="No Of Books">
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Name of item count. 
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
                                                If inactive activity category name will not appear in dropdowns
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Image -->
                                <div class="form-group" id="infra_image_panel">
									<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
										data-placement="bottom" title="infra Image">Image</label>
										<div class="col-sm-4">
											<input data-brackets-id="3402" class="form-control" type="file"
												name="infra-image" />
										</div>
					     	   </div>
                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" id='btnsave' class="btn btn-success">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                                    </div>
                                </div> </form>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                       
                 <!--    </div> -->

               
            </div>
       <script src="${baseUrl}/js/jquery.form.js"></script>
     <script type="text/javascript">
//     	$('#btnsave').click(function(){  
//     		var strcat=$.trim($("#txtname").val());
//     		if (strcat == "" ) {
//     			$("#aierror").html("Category name cannot be empty");
//     			$("#txtname").addClass('has-error');    			
//     		}else {	
// 	    		$.post('../webapi/facility/infracatitem/save',{
// 	    			name: $("#txtname").val(),
// 	    			isOptional: $('input[name=isOptional]:checked').val(),
// 	    			countItemName: $("#countItemName").val(),
// 	    			status: $('input[name=status]:checked').val(),
// 	    			desc: $("#desc").val(),infra_cat_id:$("#catId").val()},function(data){
// 	    			if(data.status == 1)
// 	    				window.location.href = "${baseUrl}/facility/infra-category-item.jsp?scat_id="+$("#catId").val();
// 	    			else
// 	    				alert(data.message);
// 	    		},'json');
//     		}
//     	});
     $("#btnsave").click(function(){
    		var options = {
    	 			beforeSubmit : showInfraCatRequest, // pre-submit callback 
    	 			success :  showInfraCatResponse,
    	 			url : '../webapi/facility/infracatitem/save',
    	 			semantic : true,
    	 			dataType : 'json'
    	 		};
    			$('#infracatitemForm').ajaxSubmit(options); 
     });
    function showInfraCatRequest(formData, jqForm, options){
    	var strcat=$.trim($("#infracatitemname").val());
		if (strcat == "" ) {
			$("#aierror").html("Category name cannot be empty");
			$("#infracatitemname").addClass('has-error');    			
		}
    }
    function  showInfraCatResponse(responseText, statusText, xhr, $form){
    	if(responseText.status == 1)
				window.location.href = "${baseUrl}/facility/infra-category-item.jsp?scat_id="+$("#infracatid").val();
			else
				alert(responseText.message);
    }
	function editCategory(id){
		window.location.href = "${baseUrl}/facility/editinfra-category-item.jsp?scat_id="+id;
	}
	$("#searchcatId").change(function(){
		if($(this).val() > 0){
			window.location.href = "${baseUrl}/facility/infra-category-item.jsp?scat_id="+$(this).val();
		} else {
			alert("select Category");
		}
	});
    </script>
    <%@include file="../footer.jsp" %>
   

 