<%@page import="org.school.admin.model.ActivityCategoryItem"%>
<%@page import="org.school.admin.model.ActivityCategory"%>
<%@page import="org.school.admin.dao.FacilityImpl"%>

<%@page import="java.util.List"%>
<%
	int category_id = 0;
	int cat_size = 0;
	String str_selected = null;
	List<ActivityCategoryItem> item_list = null;
	List<ActivityCategory> cat_list = null;
	int item_size = 0;
	if (request.getParameterMap().containsKey("scat_id")) {
		category_id = Integer.parseInt(request.getParameter("scat_id"));
		
		if (category_id > 0) {
	item_list = new FacilityImpl().getActivityItemByCategoryId(category_id);
	item_size = item_list.size(); 
		}
	}
 
	cat_list = new FacilityImpl().getActivityCategory();
	cat_size = cat_list.size();
	System.out.println("Item SIZe for cat_id="+category_id + " IS = " +item_size);
%>
<%@ include file="../header.jsp"%>
<%@ include file="../LeftNav.jsp"%>
<!-- Right main content -->
<div class="col-sm-12 col-md-12  main">
	<ol class="breadcrumb">
		<li><a href="#">General</a></li>
		<li class="active">Category Item</li>
	</ol>

	<form method="post" action="#" class="form-horizontal" id="activitycatform"
		novalidate="novalidate" enctype="multipart/form-data">
		<div id="myTabContent" class="tab-content">
			<!--Contacts tab starts-->
			<div class="tab-pane fade active in" id="contacts"
				aria-labelledby="contacts-tab">

				<div class="contacts-list">
					<h2>List Activity Category Item</h2>
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
					<div id="aierror" class="bg-danger"></div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Name</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="tname" id="tname"
								placeholder="FootBall">
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">This is the name of the
									activity category item.</div>
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

					<div class="form-group" id="activity_image_panel">
						<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
							data-placement="bottom" title="activity Image">Image</label>
						<div class="col-sm-4">
							<input data-brackets-id="3402" class="form-control" type="file"
								name="activity-image" />
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
								 id="saveacitivityitem">Save</button>
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
// 			$("#aierror").html("Category item name cannot be empty");
// 			$("#tname").addClass('has-error');
// 		} else if ($("#catId").val() == "") {
// 			$("#aierror").html("Category not selected");
// 			$("#catId").addClass('has-error');
// 		} else {
// 			$
// 					.post(
// 							"../webapi/facility/activitycatitem/save",
// 							{
// 								name : $("#tname").val(),
// 								categoryId : $("#catId").val(),
// 								desc : $("#desc").val(),
// 								status : $('input[name=status]:checked').val()
// 							},
// 							function(data) {
// 								if ($("#catId").val() > 0) {
// 									if (data.status == 1)
// 										window.location.href = "${baseUrl}/facility/activity-cat-item.jsp?scat_id="
// 												+ $("#catId").val();
// 									else
// 										$("#aierror").html(data.message);
// 								} else {

// 									$("#aierror").html("Select Category");
// 								}
// 							});
// 		}
// 	}

	function editCatItem(id) {
		window.location.href = "${baseUrl}/facility/editactivity-cat-item.jsp?scatitem_id="
				+ id;
	}
	$("#saveacitivityitem").click(function(){
		//alert("Hi");
		var options = {
	 			beforeSubmit : showActivityCatRequest, // pre-submit callback 
	 			success :  showActivityCatResponse,
	 			url : '../webapi/facility/activitycatitem/save',
	 			semantic : true,
	 			dataType : 'json'
	 		};
			$('#activitycatform').ajaxSubmit(options);
	});

	function showActivityCatRequest(formData, jqForm, options){
		var strcat = $.trim($("#tname").val());
		if (strcat == "") {
			$("#aierror").html("Category item name cannot be empty");
			$("#tname").addClass('has-error');
		} else if ($("#catId").val() == "") {
			$("#aierror").html("Category not selected");
			$("#catId").addClass('has-error');
		}
		return true;
	}
	function  showActivityCatResponse(responseText, statusText, xhr, $form){
		if ($("#catId").val() > 0) {
			if (responseText.status == 1)
				window.location.href = "${baseUrl}/facility/activity-cat-item.jsp?scat_id="
						+ $("#catId").val();
			else
				$("#aierror").html(responseText.message);
		} else {

			$("#aierror").html("Select Category");
		}
	}
	
	$("#searchcatId")
			.change(
					function() {
						if ($(this).val() > 0) {
							window.location.href = "${baseUrl}/facility/activity-cat-item.jsp?scat_id="
									+ $(this).val();
						} else {
							alert("select Category");
						}
					});
</script>