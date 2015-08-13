<%@page import="org.school.admin.model.ActivityCategory"%>
<%@page import="org.school.admin.model.ActivityCategoryItem"%>
<%@page import="org.school.admin.dao.FacilityImpl"%>
<%@page import="java.util.List"%>
<%
	int item_id = Integer.parseInt(request.getParameter("scatitem_id"));
	List<ActivityCategory> category_list = new FacilityImpl().getActivityCategory();
	List<ActivityCategoryItem> item_detail = null;
	ActivityCategoryItem rowCatItem = null;
	int  category_size = category_list.size();	 
	if (item_id > 0) {
		item_detail = new FacilityImpl().getActivityCategoryItemById(item_id);
		if(item_detail.size() > 0)
		rowCatItem = item_detail.get(0);
		System.out.println("JJ="+rowCatItem.getId());
	}
%>
<%@ include file="../header.jsp"%>
<%@ include file="../LeftNav.jsp"%>
<!-- Right main content -->
<div class="col-sm-12 col-md-12  main">
	<ol class="breadcrumb">
		<li><a href="#">General</a></li>
		<li class="active">Activity Category Update</li>
	</ol>
	<form method="post" action="#" class="form-horizontal"
		id="updateactivityitemform" novalidate="novalidate"
		enctype="multipart/form-data">
		<div id="myTabContent" class="tab-content">
			<!--Contacts tab starts-->
			<div class="tab-pane fade active in" id="contacts"
				aria-labelledby="contacts-tab">
				<div class="contacts-list">
					<p>Here you can update activity category item</p>

				</div>
				<div class="contacts-new">
					<h2>Update Category Item</h2>
					<div id="aierror" class="bg-danger"></div>
					<input type="hidden" name="id" id="id"
						value="<%out.print(rowCatItem.getId());%>" />
					<div class="form-group">
						<label class="col-sm-2 control-label">Name</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="tname" id="tname"
								value="<%out.print(rowCatItem.getName());%>"
								placeholder="Cricket">
						</div>
						<div class="col-sm-8">
							<div class="tooltip custom-tool-tip right">
								<div class="tooltip-arrow"></div>
								<div class="tooltip-inner">This is the name of the
									category item.</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
							data-placement="bottom">Category</label>
						<div class="col-sm-2">
							<select name="categoryId" id="categoryId" class="form-control">
								<option value="">Select Category</option>
								<%
									for(int i=0; i < category_size; i++){
									                                        	String selected = "";
									                                        	if(rowCatItem.getActivityCategory().getId() == category_list.get(i).getId()){
									                                        		selected = "selected";
									                                        	}
									                                        	out.print("<option value='"+category_list.get(i).getId()+"' "+selected+">"+category_list.get(i).getName()+"</option>");
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
						<div class="col-sm-4">
							<img id='timeline_img' src='${baseUrl}/images/<%out.print(rowCatItem.getImage()); %>' width=90 height=90/>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Status</label>
						<div class="col-sm-2">
							<label class="radio-inline"> <input type="radio"
								name="status" id="status" value="1"
								<%if(rowCatItem.getStatus() == 1){%> checked="checked" <%}%>>Active
							</label> <label class="radio-inline"> <input type="radio"
								name="status" id="status" value="0"
								<%if(rowCatItem.getStatus() == 0){%> checked="checked" <%}%>>Inactive
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
								id="update-activity-item" >Update</button>
							<button class="btn btn-default list-id list-contacts"
								type="button"
								onclick="showItemList(<%out.print(rowCatItem.getActivityCategory().getId());%>);">Cancel</button>
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
<script src="${baseUrl}/js/jquery.form.js"></script>

<%@ include file="../footer.jsp"%>
<script type="text/javascript">
// function saveItem(){
// 	var strcat=$.trim($("#tname").val());
// 	if (strcat == "" ) {
// 		$("#aierror").html("Category item name cannot be empty");
// 		$("#tname").addClass('has-error');
// 	}else if($("#catId").val()==""){
// 		$("#aierror").html("Category not selected");
// 		$("#catId").addClass('has-error');  
// 	} else {
// 		$.post("../webapi/facility/activitycatitem/update", {id: $("#id").val(), name: $("#tname").val(), 
// 			categoryId: $("#categoryId").val(), desc: $("#desc").val(),
//  			status: $('input[name=status]:checked').val()}, function(data){
// 			window.location.href = "${baseUrl}/facility/activity-cat-item.jsp?scat_id="+$("#categoryId").val();
// 		});
// 	}
// }
$("#update-activity-item").click(function(){
	//alert("hi ");
	var options = {
 			beforeSubmit : showUpdateActivityCatRequest, // pre-submit callback 
 			success :  showUpdateActivityCatResponse,
 			url : '../webapi/facility/activitycatitem/update',
 			semantic : true,
 			dataType : 'json'
 		};
	$("#updateactivityitemform").ajaxSubmit(options);;
});

function showItemList(id){
	window.location.href = "${baseUrl}/facility/activity-cat-item.jsp?scat_id="+id;
}
function showUpdateActivityCatRequest(formData, jqForm, options){
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
	
	function showUpdateActivityCatResponse(responseText, statusText, xhr, $form){
		if ($("#categoryId").val() > 0) {
			if (responseText.status == 1){
				alert(responseText.message);
				window.location.href = "${baseUrl}/facility/activity-cat-item.jsp?scat_id="+$("#categoryId").val();
			}
			else
				$("#aierror").html(responseText.message);
		} else {

			$("#aierror").html("Select Category");
		}
	}
</script>