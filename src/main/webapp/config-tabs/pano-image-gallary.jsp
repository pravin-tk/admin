<%@page import="org.school.admin.model.SchoolPanoramicImage"%>
<%@page import="java.awt.Image"%>
<%@page import="org.school.admin.model.SchoolImageGallery"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.ServletContext" %>
<%
	List<SchoolPanoramicImage> SchoolPanoramicImage = new SchoolDAOImp().getSchoolPanoImageGallery( Integer.parseInt(request.getParameter("school_id")));
	//List<School> schools = new SchoolDAOImp().getSchoolDetails( Integer.parseInt(request.getParameter("school_id")));
	session = request.getSession(false);
	ServletContext context1 = pageContext.getServletContext();
%>
<form action="${baseUrl}/webapi/school/saveimagepanogallery" method="post" id="school_pano_imgallery_form" class="form-horizontal" enctype="multipart/form-data">
   <div class="contact-new1" id="image_info_add">
        <h4>Add Pano images of school</h4>
		<div id="error-contact-detail" class="has-error bg-danger nopadding"></div>
		<input type="hidden" name="id" id="timeline_id" value="" /> <input
			type="hidden" name="school_id" id="school_id"
			value="<%out.print( Integer.parseInt(request.getParameter("school_id")));%>" /> 
		<input type="hidden" name="panopanoimgcount" id="panopanoimgcount" value="3" />
		<div id="image-pano-gallery">
			<% if(SchoolPanoramicImage.size() > 0) { %>
			<% for(int i = 0; i < SchoolPanoramicImage.size(); i++) { %>
			<div id="image-pano-gallery_<% out.print(i+1);%>">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imagePanoTitle<% out.print(SchoolPanoramicImage.get(i).getId());%>" id="imagePanoTitle<% out.print(SchoolPanoramicImage.get(i).getId());%>" value="<% out.print(SchoolPanoramicImage.get(i).getTitle());%>">
					</div>
					<div class='col-sm-1'>
						<a href='javascript:updateimagePanoTitle(<% out.print(i+1);%>,<% out.print(SchoolPanoramicImage.get(i).getId());%>);' 
							class='btn btn-success icon-btn'><i class='fa fa-edit'></i></a>
					</div>
					<div class="col-sm-2">
						<img src="<% out.print(context1.getInitParameter("s3_base_url")+SchoolPanoramicImage.get(i).getPanoImage()); %>" width="90" height="90"/>
					</div>
					<div class='col-sm-1'>
						<a href='javascript:removePanoImgPermanently(<% out.print(i+1);%>,<% out.print(SchoolPanoramicImage.get(i).getId());%>);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a>
					</div>
				</div>
				</div>
				<% } %>
				<% } else { %>
				<div id="image-pano-gallery_1">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imagePanoTitle[]" id="imagePanoTitle[]" placeholder="about image">
					</div>
					<div class="form-group" id="image_panel">
						<label for="" class="col-sm-2 control-label" data-toggle=""
							data-placement="bottom" title="School Pano Image">Pano Image</label>
						<div class="col-sm-2">
							<input data-brackets-id="3402" class="form-control" type="file" name="ga_pano_image[]" />
						</div>
					</div>
					
				</div>
				</div>
				<div id="image-pano-gallery_2">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imagePanoTitle[]" id="imagePanoTitle[]" placeholder="about image">
					</div>
					<label for="" class="col-sm-2 control-label" data-toggle=""
						data-placement="bottom" title="School Pano Image">Pano Image</label>
					<div class="col-sm-2">
						<input data-brackets-id="3402" class="form-control" type="file" name="ga_pano_image[]" />
					</div>
					<div class='col-sm-1'>
						<a href='javascript:removePanoImg(2);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a>
					</div>
				</div>
				</div>
				<div id="image-pano-gallery_3">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="Short description about image"
						data-placement="bottom" title="Image Title">Image Title</label>
					<div class="col-sm-4">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="imagePanoTitle[]" id="imagePanoTitle[]" placeholder="about image">
					</div>
					<label for="" class="col-sm-2 control-label" data-toggle=""
						data-placement="bottom" title="School Pano Image">Pano Image</label>
					<div class="col-sm-2">
						<input data-brackets-id="3402" class="form-control" type="file" name="ga_pano_image[]" />
					</div>
					<div class='col-sm-1'>
						<a href='javascript:removePanoImg(3);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a>
					</div>
				</div>
				</div>
				<% } %>
		</div>
		<div>
			<a href="javascript:addMorePanoImg();"
				class="btn btn-success view-contact bottom-margin"> Add More </a>
		</div>
		<div class="form-group">
			<div class="col-sm-4">
				<button type="submit" id='savepanoimagegallery' class="btn btn-success">Save</button>
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

	$("#savepanoimagegallery").click(function() {
		var options = {
			target : '#error-contact-detail', // target element(s) to be updated with server response 
			beforeSubmit : showGalleryRequest, // pre-submit callback 
			success : showGalleryResponse,
			url : '${baseUrl}/webapi/school/saveimagepanogallery',
			semantic : true,
			dataType : 'json'
		};
		
		$('#school_pano_imgallery_form').ajaxForm(options);
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
			//$("#saveimagegallery").hide();
		}
		else
			alert(responseText.message);
	}

	
	function addMorePanoImg() {
		var panoimgcount = parseInt($("#panoimgcount").val()) + 1;
		var html = "<div id='image-pano-gallery_"+panoimgcount+"'>"
			+"<div class='form-group'>"
			+"<label for='' class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Image Title'>Image Title</label>"
			+"<div class='col-sm-3'>"
			+"<input data-brackets-id='3402' type='text' class='form-control' name='imagePanoTitle[]' id='imagePanoTitle' placeholder='about image'>"
			+"</div>"
			+"<div id='image_panel'>"
			+"<label for='' class='col-sm-2 control-label' data-toggle=''	data-placement='bottom' title='School Pano Image'>Pano Image</label>"
			+"<div class='col-sm-3'>"
			+"<input data-brackets-id='3402' class='form-control' type='file' name='ga_pano_image[]' />"
			+"</div>"
			+"</div>"
			+"<div class='col-sm-1'>"
			+"	<a href='javascript:removePanoImg("+panoimgcount+");' class='btn btn-danger icon-btn'><i"
			+"		class='fa fa-remove'></i></a>"
			+"</div>"
		+"</div>"
	+"</div>";
		$("#image-pano-gallery").append(html);
		$("#panoimgcount").val(mcount);
	}

	function removePanoImg(id) {
		var panoimgcount = parseInt($("#panoimgcount").val()) - 1;
		$("#image-pano-gallery_" + id).remove();
		$("#panoimgcount").val(panoimgcount);
	}
	
	function removePanoImgPermanently(id, img_id){
		if(confirm("Do you really want to remove this image ?")){
			var panoimgcount = parseInt($("#panoimgcount").val()) - 1;
			$("#image-pano-gallery_" + id).remove();
			$("#panoimgcount").val(panoimgcount);
			$.get("${baseUrl}/webapi/school/deletepanogalleryimage/"+img_id,{},function(data){
				alert(data.message);
			});
		}
	}
	
	function updateimagePanoTitle(id, img_id){
		$.post("${baseUrl}/webapi/school/updatepanoimagePanoTitle",{id: img_id, title: $("#imagePanoTitle"+img_id).val()},function(data){
			alert(data.message);
		});
	}
	
</script>