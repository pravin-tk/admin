<%@page import="org.school.admin.model.InfrastructureCategory"%>
<%@page import="org.school.admin.model.InfrastructureCategoryItem"%>
<%@page import="org.school.admin.dao.FacilityImpl"%>
<%@page import="java.util.List"%>
<%
	int item_id = Integer.parseInt(request.getParameter("scat_id"));
	List<InfrastructureCategory> category_list = new FacilityImpl().getInfrastructureCategory();
	List<InfrastructureCategoryItem> item_detail = null;
	InfrastructureCategoryItem rowinfracat = null;
	int  category_size = category_list.size();	 
	if (item_id > 0) {
		item_detail = new FacilityImpl().getInfrastructureCategoryItemById(item_id);
		if(item_detail.size() > 0)
	rowinfracat = item_detail.get(0);
	}
%>
<%@ include file="../header.jsp"%>
<%@ include file="../LeftNav.jsp"%>
<!-- Right main content -->
<div class="col-sm-12 col-md-12  main">
	<ol class="breadcrumb">
		<li><a href="#">General</a></li>
		<li class="active">Infrastructure Category Update</li>
	</ol>
	<form method="post" action="#" class="form-horizontal" id="updateinfracatform"
		novalidate="novalidate"
		enctype="multipart/form-data">
		<div id="myTabContent" class="tab-content">
			<!--Contacts tab starts-->
			<div class="tab-pane fade active in" id="contacts"
				aria-labelledby="contacts-tab">
				<div class="contacts-list">
					<p>Here you can update infrastructure category detail</p>

				</div>
				<div class="contacts-new">
					<h4>Update Infrastructure Category</h4>
					<div id="aierror" class="bg-danger"></div>
					<input type="hidden" name="id" id="id"
						value="<%out.print(rowinfracat.getId());%>" />
					<div class="form-group">
						<label class="col-sm-2 control-label">Name</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="infracatitemname" id="infracatitemname"
								value="<%out.print(rowinfracat.getName());%>"
								placeholder="Library">
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">This is the name of the
									category.</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
							data-placement="bottom">Category</label>
						<div class="col-sm-4">
							<select name="infrcatid" id="infrcatid" class="form-control">
								<option value="">Select Category</option>
								<%
									for(int i=0; i < category_size; i++){
									                                        	String str_selected = "";
									                                        	if(category_list.get(i).getId() ==  rowinfracat.getInfrastructureCategory().getId() )
									                                        			str_selected = "SELECTED";
									                                        	else
									                                        		str_selected = "";
									                                        	
									                                        	out.print("<option "+ str_selected + " value='"+category_list.get(i).getId()+"'>"+category_list.get(i).getName()+"</option>");
									                                         }
								%>
							</select>
						</div>
						<div class="col-sm-6">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">Category of Item.</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Is Optional</label>
						<div class="col-sm-2">
							<label class="radio-inline"> <input type="radio"
								name="isOptional" value="1"
								<%if(rowinfracat.getIsOptional() == 1){%> checked="checked"
								<%}%>>Yes
							</label> <label class="radio-inline"> <input type="radio"
								name="isOptional" value="0"
								<%if(rowinfracat.getIsOptional() == 0){%> checked="checked"
								<%}%>>No
							</label>
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">If Optional category.</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Count Item Name</label>
						<div class="col-sm-4">
							<input type="text" name="updatecountItemName" id="updatecountItemName" class="form-control"
								placeholder="No Of Books"
								value="<%out.print(rowinfracat.getCountItemName());%>">
						</div>
						<div class="col-sm-6">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">Name of item count.</div>
							</div>
						</div>
					</div>

					<!-- Image part -->
					<div class="form-group" id="infra_image_panel">
						<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
							data-placement="bottom" title="infra Image">Image</label>
						<div class="col-sm-4">
							<input data-brackets-id="3402" class="form-control" type="file"
								name="infra-image" />
						</div>
						<div class="col-sm-4">
							<img id='timeline_img'
								src='${baseUrl}/images/<%out.print(rowinfracat.getImage()); %>'
								width=90 height=90 />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Status</label>
						<div class="col-sm-2">
							<label class="radio-inline"> <input type="radio"
								name="status" id="status" value="1"
								<%if(rowinfracat.getStatus() == 1){%> checked="checked" <%}%>>Active
							</label> <label class="radio-inline"> <input type="radio"
								name="status" id="status" value="0"
								<%if(rowinfracat.getStatus() == 0){%> checked="checked" <%}%>>Inactive
							</label>
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">Active/Inactive infra category.
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-4">
							<button type="button" class="btn btn-success"
								id="btn-updateinfracatitem">Update</button>
							<button class="btn btn-default list-id list-contacts"
								type="button"
								onclick="showInfraCatList(<%out.print(rowinfracat.getInfrastructureCategory().getId());%>);">Cancel</button>
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
<%@ include file="../footer.jsp"%>
<script src="${baseUrl}/js/jquery.form.js"></script>

<script type="text/javascript">
// function saveInfraCat(){
// 	var strcat=$.trim($("#infracatitemname").val());
// 	if (strcat == "" ) {
// 		$("#aierror").html("Category name cannot be empty");
// 		$("#infracatitemname").addClass('has-error');    			
// 	}else {
// 		$.post("../webapi/facility/infracatitem/update", {id: $("#id").val(),
// 			name: $("#infracatitemname").val(), desc: $("#desc").val(),
// 			isOptional: $('input[name=isOptional]:checked').val(),
// 			countItemName: $("#countItemName").val(),
// 			status: $('input[name=status]:checked').val(),infra_cat_id:$("#catId").val()}, function(data){
// 			window.location.href = "${baseUrl}/facility/infra-category-item.jsp?scat_id="+$("#catId").val();
// 		});
// 	}
// }


$("#btn-updateinfracatitem").click(function(){
	var options = {
 			beforeSubmit : showUpdateInfraCatRequest, // pre-submit callback 
 			success :  showUpdateInfraCatResponse,
 			url : '../webapi/facility/infracatitem/update',
 			semantic : true,
 			dataType : 'json'
 		};
	$("#updateinfracatform").ajaxSubmit(options);
});
function showUpdateInfraCatRequest(formData, jqForm, options){
	var strcat=$.trim($("#infracatitemname").val());
	if (strcat == "" ) {
		$("#aierror").html("Category name cannot be empty");
		$("#infracatitemname").addClass('has-error');    			
	}
}
function  showUpdateInfraCatResponse(responseText, statusText, xhr, $form){
	if(responseText.status == 1)
			window.location.href = "${baseUrl}/facility/infra-category-item.jsp?scat_id="+$("#infrcatid").val();
		else
			alert(responseText.message);
}
function showInfraCatList(id){
	window.location.href = "${baseUrl}/facility/infra-category-item.jsp?scat_id="+id;
}
	
</script>