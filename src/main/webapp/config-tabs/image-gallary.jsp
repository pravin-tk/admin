<%@page import="java.awt.Image"%>
<%@page import="org.school.admin.model.SchoolImageGallery"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.ServletContext" %>
<%
 	int school_id111 = Integer.parseInt(request.getParameter("school_id"));
	SchoolDAOImp schoolDAOImp111 = new SchoolDAOImp();
	List<SchoolImageGallery> schoolImageGalleries = schoolDAOImp111.getSchoolImageGallery(school_id111);
	List<School> schools = schoolDAOImp111.getSchoolDetails(school_id111);
	session = request.getSession(false);
	ServletContext context = pageContext.getServletContext();
%>
<form action="${baseUrl}/webapi/school/saveimagegallary" method="post" id="school_imgallery_form" class="form-horizontal" enctype="multipart/form-data">
   <div class="contact-new1" id="image_info_add">
        <h4>Add images of school</h4>
		<div id="error-contact-detail" class="has-error bg-danger nopadding"></div>
		<input type="hidden" name="id" id="timeline_id" value="" /> <input
			type="hidden" name="school_id" id="school_id"
			value="<%out.print(school_id111);%>" /> 
		
		<div class="form-group" id="image_panel">
			<label for="" class="col-sm-2 control-label" data-toggle="select school logo's image only"
				data-placement="bottom" title="School Image">School Logo</label>
			<div class="col-sm-4">
				<input data-brackets-id="3402" class="form-control" type="file" name="logo_image" />
			</div>
			<% if(schoolImageGalleries.size() > 0) { %>
			<div class="col-sm-4">
				<img src="<% out.print(context.getInitParameter("s3_base_url")+schools.get(0).getLogo()); %>" width="90" height="90"/>
			</div>
			<% } %>
		</div>
		<div class="form-group" id="image_panel">
			<label for="" class="col-sm-2 control-label" data-toggle="select school home image only"
				data-placement="bottom" title="School Image">School Home Image</label>
			<div class="col-sm-4">
				<input data-brackets-id="3402" class="form-control" type="file"
					name="home_image" />
			</div>
			<% if(schoolImageGalleries.size() > 0) { %>
			<div class="col-sm-4">
				<img src="<% out.print(context.getInitParameter("s3_base_url")+schools.get(0).getHomeImage()); %>" width="120" height="90"/>
			</div>
			<% } %>
		</div>
		
		
		<input type="hidden" name="imgcount" id="imgcount" value="3" />
		<div id="image-gallery">
			<% if(schoolImageGalleries.size() > 0) { %>
			<% for(int i = 0; i < schoolImageGalleries.size(); i++) { %>
			<div id="image-gallery_<% out.print(i+1);%>">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imageTitle<% out.print(schoolImageGalleries.get(i).getId());%>" id="imageTitle<% out.print(schoolImageGalleries.get(i).getId());%>" value="<% out.print(schoolImageGalleries.get(i).getTitle());%>">
					</div>
					<div class='col-sm-1'>
						<a href='javascript:updateImageTitle(<% out.print(i+1);%>,<% out.print(schoolImageGalleries.get(i).getId());%>);' 
							class='btn btn-success icon-btn'><i class='fa fa-edit'></i></a>
					</div>
					<div class="col-sm-2">
						<img src="<% out.print(context.getInitParameter("s3_base_url")+schoolImageGalleries.get(i).getImage()); %>" width="90" height="90"/>
					</div>
					<div class='col-sm-1'>
						<a href='javascript:removeImgPermanently(<% out.print(i+1);%>,<% out.print(schoolImageGalleries.get(i).getId());%>);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a>
					</div>
				</div>
				</div>
				<% } %>
				<% } else { %>
				<div id="image-gallery_1">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imageTitle[]" id="imageTitle[]" placeholder="about image">
					</div>
					<div class="form-group" id="image_panel">
						<label for="" class="col-sm-1 control-label" data-toggle=""
							data-placement="bottom" title="School Image">Image</label>
						<div class="col-sm-2">
							<input data-brackets-id="3402" class="form-control" type="file" name="ga_image[]" />
						</div>
					</div>
					
				</div>
				</div>
				<div id="image-gallery_2">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imageTitle[]" id="imageTitle[]" placeholder="about image">
					</div>
					<label for="" class="col-sm-1 control-label" data-toggle=""
						data-placement="bottom" title="School Image">Image</label>
					<div class="col-sm-2">
						<input data-brackets-id="3402" class="form-control" type="file" name="ga_image[]" />
					</div>
					<div class='col-sm-1'>
						<a href='javascript:removeImg(2);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a>
					</div>
				</div>
				</div>
				<div id="image-gallery_3">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imageTitle[]" id="imageTitle[]" placeholder="about image">
					</div>
					<label for="" class="col-sm-1 control-label" data-toggle=""
						data-placement="bottom" title="School Image">Image</label>
					<div class="col-sm-2">
						<input data-brackets-id="3402" class="form-control" type="file" name="ga_image[]" />
					</div>
					<div class='col-sm-1'>
						<a href='javascript:removeImg(3);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a>
					</div>
				</div>
				</div>
				<% } %>
		</div>
		<div>
			<a href="javascript:addMoreImg();"
				class="btn btn-success view-contact bottom-margin"> Add More </a>
		</div>
		<div class="form-group">
			<div class="col-sm-4">
				<button type="submit" id='saveimagegallery' class="btn btn-success">Save</button>
			</div>
		</div>
	</div>
</form>
<script src="${baseUrl}/js/jquery.form.js"></script>
<script type="text/javascript">
	function checkChar(evt) {
		evt = (evt) ? evt : window.event
		var charCode = (evt.which) ? evt.which : evt.keyCode
		//var validChar = /^[a-zA-Z]+$/;
		if (validName(charCode)) {
			return true;
		}
		return false;
	}
	function validName(name) {
		var expr = /^[a-zA-Z]+$/;
		return expr.test(name);
	}
	function checkNumber(evt) {
		evt = (evt) ? evt : window.event
		var charCode = (evt.which) ? evt.which : evt.keyCode
		if (charCode > 31 && (charCode > 57 || charCode < 48)) {
			return false;
		}
		return true;
	}
	function ValidateEmail(email) {
		var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		return expr.test(email);
	};

	$("#saveimagegallery").click(function() {
		var options = {
			target : '#error-contact-detail', // target element(s) to be updated with server response 
			beforeSubmit : showGalleryRequest, // pre-submit callback 
			success : showGalleryResponse,
			url : '${baseUrl}/webapi/school/saveimagegallery',
			semantic : true,
			dataType : 'json'
		};
		
		$('#school_imgallery_form').ajaxForm(options);
	});

	// pre-submit callback 
	function showGalleryRequest(formData, jqForm, options) {
		var queryString = $.param(formData);
		$('#error-contact-detail').hide();
		return true;
	}

	// post-submit callback 
	function showGalleryResponse(responseText, statusText, xhr, $form) {
		if (responseText.status == 1) {
			updateProgress($('#school_id').val());
			alert(responseText.message);
			$("#saveimagegallery").hide();
		}
	}

	
	function addMoreImg() {
		var imgcount = parseInt($("#imgcount").val()) + 1;
		var html = "<div id='image-gallery_"+imgcount+"'>"
			+"<div class='form-group'>"
			+"<label for='' class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Image Title'>Image Title</label>"
			+"<div class='col-sm-3'>"
			+"<input data-brackets-id='3402' type='text' class='form-control' name='imageTitle[]' id='imageTitle' placeholder='about image'>"
			+"</div>"
			+"<div id='image_panel'>"
			+"<label for='' class='col-sm-1 control-label' data-toggle=''	data-placement='bottom' title='School Image'>Image</label>"
			+"<div class='col-sm-3'>"
			+"<input data-brackets-id='3402' class='form-control' type='file' name='ga_image[]' />"
			+"</div>"
			+"</div>"
			+"<div class='col-sm-1'>"
			+"	<a href='javascript:removeImg("+imgcount+");' class='btn btn-danger icon-btn'><i"
			+"		class='fa fa-remove'></i></a>"
			+"</div>"
		+"</div>"
	+"</div>";
		$("#image-gallery").append(html);
		$("#imgcount").val(mcount);
	}

	function removeImg(id) {
		var imgcount = parseInt($("#imgcount").val()) - 1;
		$("#image-gallery_" + id).remove();
		$("#imgcount").val(imgcount);
	}
	
	function removeImgPermanently(id, img_id){
		if(confirm("Do you really want to remove this image ?")){
			var imgcount = parseInt($("#imgcount").val()) - 1;
			$("#image-gallery_" + id).remove();
			$("#imgcount").val(imgcount);
			$.get("${baseUrl}/webapi/school/deletegalleryimage/"+img_id,{},function(data){
				alert(data.message);
			});
		}
	}
	
	function updateImageTitle(id, img_id){
		$.post("${baseUrl}/webapi/school/updateimagetitle",{id: img_id, title: $("#imageTitle"+img_id).val()},function(data){
			alert(data.message);
		});
	}
	
</script>