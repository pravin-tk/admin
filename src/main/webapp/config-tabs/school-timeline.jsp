<%@page import="org.school.admin.model.SchoolTimelineMilestone"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%
	
 
 	int school_id110 = Integer.parseInt(request.getParameter("school_id"));
	SchoolDAOImp schoolDAOImp110 = new SchoolDAOImp();
	List<SchoolTimelineMilestone> schoolTimelineMilestones = schoolDAOImp110.getSchoolTimelineMilestones(school_id110);
	session = request.getSession(false);
	AdminUser registration110 = new AdminUser();
	int user_id110 = 0;
	if(session!=null)
	{
		if(session.getAttribute("uname") != null)
		{
				registration110  = (AdminUser)session.getAttribute("uname");
  				System.out.println();
  				System.out.println("user id : "+registration110.getId());
  				user_id110 = registration110.getId();
  				System.out.println();
		}
   }	

%>

               

   <div class="school-timeline-list" id="timeline-info-list">
       <p>Here you can add/update school timeline.</p>
       <a href="#" class="btn btn-primary view-school-timeline bottom-margin"><i class="fa fa-plus"></i> School Timeline</a>
       <table class="table table-striped table-bordered" id="school-timeline-table">
           <thead>
               <tr>
                   <th>Year</th>
                   <th>Title</th>
                   <th>Image Title</th>
                   <th>Classes Up To</th>
                   <th class="alignRight">Actions</th>
               </tr>
           </thead>
           <tbody>
           <%
           	try
              {
           		for(int i = 0; i < schoolTimelineMilestones.size(); i++)
           		{
           			
           			out.print("<tr><td>"+schoolTimelineMilestones.get(i).getSchoolTimeline().getYear()+"</td>");
           			out.print("<td>"+schoolTimelineMilestones.get(i).getTitle()+"</td>");
           			out.print("<td>"+schoolTimelineMilestones.get(i).getSchoolTimeline().getTitle()+"</td>");
           			out.print("<td>"+schoolTimelineMilestones.get(i).getSchoolTimeline().getClassesUpto()+"</td>");
           			out.print("<td><a href='javascript:editTimeLine("+schoolTimelineMilestones.get(i).getSchoolTimeline().getId()+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a></td></tr>");
           		}
              }
              catch(Exception e)
              {
           	   System.err.print("POCInternalError : "+e);
              }
            %> 
           </tbody>
       </table>
       <a href="#" class="btn btn-primary view-school-timeline bottom-margin"><i class="fa fa-plus"></i> School Timeline</a>
   </div>
   <div class="school-timeline-new" style="display:none;" id="timeline_info_add">
   <form action="" method="post" id="school_timeline_form" class="form-horizontal" enctype="multipart/form-data">
        <h4>Add New Time Line</h4>
		<div id="error-contact-detail" class="has-error bg-danger nopadding"></div>
		<input type="hidden" name="id" id="timeline_id" value="" /> 
		<input type="hidden" name="school_id" id="school_id" value="<%out.print(school_id110);%>" /> 
		<input type="hidden" name="user_id" id="user_id" value="<%out.print(user_id110);%>" />
		<div class="form-group">
			<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
				data-placement="bottom" title="Year of Milestones">Year</label>
			<div class="col-sm-4">
				<input data-brackets-id="3402" type="text" class="form-control"
					id="year" name="year" placeholder="enter year">
			</div>
		</div>
		<div class="form-group" id="image_panel">
			<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
				data-placement="bottom" title="School Image">Image</label>
			<div class="col-sm-4">
				<input data-brackets-id="3402" class="form-control" type="file"
					name="image" />
			</div>
		</div>
		<div class="form-group">
			<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
				data-placement="bottom" title="Image Title">Image Title</label>
			<div class="col-sm-4">
				<input data-brackets-id="3402" type="text" class="form-control"
					name="title" id="title" placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
				data-placement="bottom" title="Image Title">Classes Up To</label>
			<div class="col-sm-6">
				<input data-brackets-id="3402" type="text" class="form-control"
					name="classes_upto" id="classes_upto" placeholder="">
			</div>
		</div>
		<input type="hidden" name="mcount" id="mcount" value="1" />
		<div id="milestones">
			<div id="milestone_1">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
						data-placement="bottom" title="Image Title">Milestone
						Title</label>
					<div class="col-sm-3">
						<input data-brackets-id="3402" type="text" class="form-control"
							name="milestoneTitle[]" id="milestoneTitle" placeholder="">
					</div>
					<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
						data-placement="bottom" title="Description">Milestone
						Description</label>
					<div class="col-sm-4">
						<textarea class="form-control" name="milestoneDesc[]"
							id="milestoneDesc" placeholder="Description..."></textarea>
					</div>
					<div class="col-sm-1">
						<a href='javascript:remove(1);' class='btn btn-danger icon-btn'><i
							class='fa fa-remove'></i></a>
					</div>
				</div>
			</div>
		</div>
		<div>
			<a href="javascript:addMore();"
				class="btn btn-success view-school-timeline bottom-margin"> Add More </a>
		</div>
		<div class="form-group">
			<div class="col-sm-4">
				<button type="submit" id='savetimeline' class="btn btn-success">Save</button>
				<button type="submit" id='updatetimeline' class="btn btn-success"
					style="display: none;">Update</button>
				<button class="btn btn-default list-id list-school-timeline"
					id="cancel-timeline" type="reset">Cancel</button>
			</div>
		</div>
		</form>
	</div>

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

	$("#savetimeline").click(function() {
		var options = {
			target : '#error-contact-detail', // target element(s) to be updated with server response 
			beforeSubmit : showTimeLineRequest, // pre-submit callback 
			success : showTimeLineResponse,
			url : '${baseUrl}/webapi/school/savetimeline',
			semantic : true,
			dataType : 'json'
		};

		$('#school_timeline_form').ajaxForm(options);
	});

	// pre-submit callback 
	function showTimeLineRequest(formData, jqForm, options) {
		var queryString = $.param(formData);
		$('#error-contact-detail').hide();
		return true;
	}

	// post-submit callback 
	function showTimeLineResponse(responseText, statusText, xhr, $form) {
		if(responseText.status == 1){
		$(".school-timeline-new").hide();
		$(".school-timeline-list").show();
		$('#error-contact-detail').html("");
		$('#timeline_id, #year, #title, #classes_upto')
				.removeClass('has-error');
		$("#timeline_img").remove();
		var html = "<div id='milestone_1'>"
				+ "<div class='form-group'>"
				+ "<label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Image Title'>Milestone Title</label>"
				+ "<div class='col-sm-3'>"
				+ "<input data-brackets-id='3402' type='text' class='form-control' name='milestoneTitle[]' id='milestoneTitle'>"
				+ "</div><label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Description'>Milestone Description</label>"
				+ "<div class='col-sm-4'>"
				+ "<textarea class='form-control' name='milestoneDesc[]' id='milestoneDesc' placeholder='Description...'></textarea>"
				+ "</div><div class='col-sm-1'><a href='javascript:remove(1);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a></div>"
				+ "</div></div>";
		document.getElementById("milestones").innerHTML = "";
		$("#milestones").html(html);
		$("#mcount").val(1);
		$("#savetimeline").show();
		$("#updatetimeline").hide();
		updateProgress($('#school_id').val());
		}
		alert(responseText.message);
	}

	$("#cancel-timeline")
			.click(
					function() {
						$('#error-contact-detail').html("");
						$('#timeline_id, #year, #title, #classes_upto')
								.removeClass('has-error');
						$("#timeline_img").remove();
						var html = "<div id='milestone_1'>"
								+ "<div class='form-group'>"
								+ "<label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Image Title'>Milestone Title</label>"
								+ "<div class='col-sm-3'>"
								+ "<input data-brackets-id='3402' type='text' class='form-control' name='milestoneTitle[]' id='milestoneTitle'>"
								+ "</div><label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Description'>Milestone Description</label>"
								+ "<div class='col-sm-4'>"
								+ "<textarea class='form-control' name='milestoneDesc[]' id='milestoneDesc' placeholder='Description...'></textarea>"
								+ "</div><div class='col-sm-1'><a href='javascript:remove(1);' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a></div>"
								+ "</div></div>";
						document.getElementById("milestones").innerHTML = "";
						$("#milestones").html(html);
						$("#mcount").val(1);
						$("#savetimeline").show();
						$("#updatetimeline").hide();
					});

	function editTimeLine(id) {
		$.get('webapi/school/timeline_detail/' + id, {}, function(data) {
			$("#timeline_info_add").show();
			$("#timeline-info-list").hide();
			$("#savetimeline").hide();
			$("#updatetimeline").show();
			$('#timeline_id').val(data[0].schoolTimeline.id);
			$('#year').val(data[0].schoolTimeline.year);
			$('#title').val(data[0].schoolTimeline.title);
			$('#classes_upto').val(
					data[0].schoolTimeline.classesUpto);
			$("#image_panel")
					.append(
							"<img id='timeline_img' src='${baseUrl}/images/"+data[0].schoolTimeline.image+"' width=90 height=90/>");
			var mcount = 0;
			$("#milestone_1").remove();
			$(data)
					.each(
							function(index) {
								var html = "<div id='milestone_"
										+ (index + 1)
										+ "'>"
										+ "<div class='form-group'>"
										+ "<label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Image Title'>Milestone Title</label>"
										+ "<div class='col-sm-3'>"
										+ "<input data-brackets-id='3402' type='text' class='form-control' name='milestoneTitle[]' id='milestoneTitle' value='"+data[index].title+"'>"
										+ "</div><label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Description'>Milestone Description</label>"
										+ "<div class='col-sm-4'>"
										+ "<textarea class='form-control' name='milestoneDesc[]' id='milestoneDesc' placeholder='Description...'>"
										+ data[index].milestoneDesc
										+ "</textarea>"
										+ "</div><div class='col-sm-1'><a href='javascript:remove("
										+ (index + 1)
										+ ");' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a></div>"
										+ "</div></div>";
								$("#milestones").append(html);
								mcount++;
							});
			$("#mcount").val(mcount);

		});
	}

	function addMore() {
		var mcount = parseInt($("#mcount").val()) + 1;
		var html = "<div id='milestone_"+mcount+"'>"
				+ "<div class='form-group'>"
				+ "<label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Image Title'>Milestone Title</label>"
				+ "<div class='col-sm-3'>"
				+ "<input data-brackets-id='3402' type='text' class='form-control' name='milestoneTitle[]' id='milestoneTitle'>"
				+ "</div><label class='col-sm-2 control-label' data-toggle='tooltip' data-placement='bottom' title='Description'>Milestone Description</label>"
				+ "<div class='col-sm-4'>"
				+ "<textarea class='form-control' name='milestoneDesc[]' id='milestoneDesc' placeholder='Description...'></textarea>"
				+ "</div><div class='col-sm-1'><a href='javascript:remove("
				+ mcount
				+ ");' class='btn btn-danger icon-btn'><i class='fa fa-remove'></i></a></div>"
				+ "</div></div>";
		$("#milestones").append(html);
		$("#mcount").val(mcount);
	}

	function remove(id) {
		var mcount = parseInt($("#mcount").val()) - 1;
		$("#milestone_" + id).remove();
		$("#mcount").val(mcount);
	}

	$('#updatetimeline').click(function() {
		var options = {
			target : '#error-contact-detail',
			beforeSubmit : showTimeLineRequest,
			success : showTimeLineResponse,
			url : '${baseUrl}/webapi/school/updatetimeline',
			semantic : true,
			dataType : 'json'
		};
		$('#school_timeline_form').ajaxForm(options);
		//updateProgress($('#school_id').val());
	});
</script>