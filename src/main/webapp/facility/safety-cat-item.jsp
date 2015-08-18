<%@page import="org.school.admin.model.SafetyCategoryItem"%>
<%@page import="org.school.admin.model.SafetyCategory"%>
<%@page import="org.school.admin.dao.FacilityImpl"%>

<%@page import="java.util.List"%>
<%
	int category_id = 0;
	int cat_size = 0;
	String str_selected = null;
	List<SafetyCategoryItem> item_list = null;
	List<SafetyCategory> cat_list = null;
	int item_size = 0;
	if (request.getParameterMap().containsKey("scat_id")) {
		category_id = Integer.parseInt(request.getParameter("scat_id"));
		
		if (category_id > 0) {
	item_list = new FacilityImpl().getItemByCategoryId(category_id);
	item_size = item_list.size(); 
		}
	}
 
	cat_list = new FacilityImpl().getSafetyCategory();
	cat_size = cat_list.size();
%>
<%@ include file="../header.jsp"%>
<%@ include file="../LeftNav.jsp"%>
<!-- Right main content -->
<div class="col-sm-12 col-md-12  main">
	<ol class="breadcrumb">
		<li><a href="#">General</a></li>
		<li class="active">Category Item</li>
	</ol>

	<form method="post" action="#" class="form-horizontal" id="safetycatform"
		novalidate="novalidate" enctype="multipart/form-data">
		<div id="myTabContent" class="tab-content">
			<!--Contacts tab starts-->
			<div class="tab-pane fade active in" id="contacts"
				aria-labelledby="contacts-tab">

				<div class="contacts-list">
					<h2>List Safety Category Item</h2>
					<p>Here you can add or deactivate category item. You can define
						category item details, .</p>
					<div class="form-group">
						<label class="col-sm-2 control-label">Select Category item</label>
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
					<a href="#" class="btn btn-primary view-contacts bottom-margin"><i
						class="fa fa-plus"></i> Category Item</a>

					<table class="table table-striped table-bordered"
						id="contacts-table">
						<thead>
							<tr>
								<th>Name</th>
								<th>Status</th>
								<th class="alignRight">Actions</th>
							</tr>
						</thead>
						<tbody>
							<%
								if(item_size>0)
								                                        for(int i=0; i < item_size; i++){
							%>
							<tr>
								<td>
									<%
										out.print(item_list.get(i).getName());
									%>
								</td>
								<td>
									<%
										if(item_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); }
									%>
								</td>
								<td class="alignRight"><a
									href="javascript:editCatItem(<%out.print(item_list.get(i).getId());%>);"
									class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>

					<a href="#" class="btn btn-primary view-contacts"><i
						class="fa fa-plus"></i> Category Item</a>

				</div>
				<div class="contacts-new" style="display: none;">
					<h2>Add New Category Item</h2>
					<div id="aerror" class="bg-danger"></div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Name</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="safetycatitemname" id="safetycatitemname"
								placeholder="eg. Identity Card, Lab Coat">
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">This is the name of the safety
									category item.</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
							data-placement="bottom">Category</label>
						<div class="col-sm-2">
							<select name="catId" id="catId" class="form-control">
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
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">Category of Item.</div>
							</div>
						</div>
					</div>

					<div class="form-group" id="safety_image_panel">
						<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
							data-placement="bottom" title="activity Image">Image</label>
						<div class="col-sm-4">
							<input data-brackets-id="3402" class="form-control" type="file"
								name="safety-image" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Status</label>
						<div class="col-sm-2">
							<label class="radio-inline"> <input type="radio"
								name="status" id="status" value="1" checked="checked">Active
							</label> <label class="radio-inline"> <input type="radio"
								name="status" id="status" value="0">Inactive
							</label>
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">Active/Inactive item.</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2">
							<button type="button" class="btn btn-success"
								id="save-safety-cat-item">Save</button>
							<button class="btn btn-default list-id list-contacts"
								type="reset">Cancel</button>
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
// 	function saveCatItem() {
// 		var strcat = $.trim($("#tname").val());
// 		if (strcat == "") {
// 			$("#aerror").html("Item name cannot be empty");
// 			$("#tname").addClass('has-error');
// 		} else if ($("#catId").val() == "") {
// 			$("#aerror").html("Category not selected");
// 			$("#catId").addClass('has-error');
// 		} else {
// 			$
// 					.post(
// 							"../webapi/facility/safetycatitem/save",
// 							{
// 								name : $("#tname").val(),
// 								categoryId : $("#catId").val(),
// 								desc : $("#desc").val(),
// 								status : $('input[name=status]:checked').val()
// 							},
// 							function(data) {
// 								if ($("#catId").val() > 0) {
// 									if (data.status == 1)
// 										window.location.href = "${baseUrl}/facility/safety-cat-item.jsp?scat_id="
// 												+ $("#catId").val();
// 									else
// 										alert(data.message);
// 								} else {
// 									alert("Select Category");
// 								}
// 							});
// 		}
// 	}
	
	$("#save-safety-cat-item").click(function(){
		var options = {
	 			beforeSubmit : showSafetyCatRequest, // pre-submit callback 
	 			success :  showSafetyCatResponse,
	 			url : '../webapi/facility/safetycatitem/save',
	 			semantic : true,
	 			dataType : 'json'
	 		};
			$('#safetycatform').ajaxSubmit(options);
	});
    function showSafetyCatRequest(formData, jqForm, options){
		var strcat = $.trim($("#safetycatitemname").val());
		if (strcat == "") {
			$("#aierror").html("Category item name cannot be empty");
			$("#safetycatitemname").addClass('has-error');
		} else if ($("#catId").val() == "") {
			$("#aierror").html("Category not selected");
			$("#catId").addClass('has-error');
		}
		return true;
    }
    
    function showSafetyCatResponse(responseText, statusText, xhr, $form){
		if ($("#catId").val() > 0) {
			if (responseText.status == 1)
				window.location.href = "${baseUrl}/facility/safety-cat-item.jsp?scat_id="
					+ $("#catId").val();
			else
				$("#aierror").html(responseText.message);
		} else {

			$("#aierror").html("Select Category");
		}
    }
	function editCatItem(id) {
		window.location.href = "${baseUrl}/facility/editsafety-cat-item.jsp?scatitem_id="
				+ id;
	}

	$("#searchcatId")
			.change(
					function() {
						if ($(this).val() > 0) {
							window.location.href = "${baseUrl}/facility/safety-cat-item.jsp?scat_id="
									+ $(this).val();
						} else {
							alert("select Category");
						}
					});
</script>