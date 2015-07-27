<%@page import="org.school.admin.model.Subject"%>
<%@page import="org.school.admin.dao.SubjectDAO"%>
<%@page import="org.school.admin.dao.SectionTypeDAO"%>
<%@page import="org.school.admin.model.SectionType"%>
<%@page import="org.school.admin.dao.StandardTypeDAO"%>
<%@page import="org.school.admin.model.StandardType"%>
<%@page import="org.school.admin.model.TeachingApproachType"%>
<%@page import="org.school.admin.dao.TeachingApproachTypeDAO"%>
<%@page import="org.school.admin.dao.AccessoryDAO"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.FeeType"%>
<%@page import="org.school.admin.model.StreamType"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.Accessories"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.model.ClassSection"%>
<%@page import="org.school.admin.model.ClassInfo"%>
<%
	int school_id6 = Integer.parseInt(request.getParameter("school_id"));
 
	AccessoryDAO accessoryDAO = new AccessoryDAO();
 	List<Accessories> accessories = null;
 	accessories = accessoryDAO.getAccessory();
 	
 	TeachingApproachTypeDAO teachingApproachTypeDAO = new TeachingApproachTypeDAO();
 	List<TeachingApproachType> teachingApproachTypes = null;
 	teachingApproachTypes = teachingApproachTypeDAO.getTeachingApproachType();
 	
 	StandardTypeDAO standardTypeDAO = new StandardTypeDAO();
 	List<StandardType> standardTypes = null;
 	standardTypes = standardTypeDAO.getStandardType();
 	
 	SettingsImpl streamTypeDAO = new SettingsImpl();
 	List<StreamType> streamTypes = null;
 	streamTypes = streamTypeDAO.getStreamTypes();
 	
 	SubjectDAO subjectDAO = new SubjectDAO();
 	List<Subject> subjects = null;
 	subjects = subjectDAO.getSubjects();
 	
 	SettingsImpl feetypeDAO = new SettingsImpl();
 	List<FeeType> feetypes = null;
 	feetypes = feetypeDAO.getAllFeeType();
 	
 	 session = request.getSession(false);
	 AdminUser adminuser6 = new AdminUser();
 	int user_id6 = 0;
 	if(session!=null)
 	{
 		if(session.getAttribute("uname") != null)
 		{
 			adminuser6  = (AdminUser)session.getAttribute("uname");
   				System.out.println();
   				System.out.println("user id : "+adminuser6.getId());
   				user_id6 = adminuser6.getId();
   				System.out.println();
 		}
    }
 	List<ClassInfo> classInfo = null;
 	SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
 	classInfo = schoolDAOImp.getClassInfoBySchId(school_id6);
	System.out.println("listsize" +classInfo.size());
%>

<div class="class-detail-list">
	<p>Here you can add or deactivate class detail.</p>
	<a href="#" class="btn btn-primary view-class-detail bottom-margin"
		id="addClassDetail"><i class="fa fa-plus"></i> Class Detail</a>
	<table class="table table-striped table-bordered"
		id="class-detail-table">
		<thead>
			<tr>
				<th>Standard</th>
				<th>Teaching approach</th>
				<th>Stream</th>
				<th>Total Seats</th>
				<th>Vacant Seats</th>

				<th class="alignRight">Actions</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i=0; i< classInfo.size(); i++){ %>
			<tr>
				<td>
					<% out.print(classInfo.get(i).getStandardType().getName()); %>
				</td>
				<td>
					<% out.print(classInfo.get(i).getStreamType().getTitle()); %>
				</td>
				<td>
					<% out.print(classInfo.get(i).getTeachingApproachType().getName()); %>
				</td>
				<td>
					<% out.print(classInfo.get(i).getTotalSeat()); %>
				</td>
				<td>
					<% out.print(classInfo.get(i).getVacantSeat()); %>
				</td>

				<td class="alignRight"><a
					href="javascript:editClassInfo(<% out.print(classInfo.get(i).getId()); %>);"
					class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a> <a
					href="javascript:deleteClassInfo(<% out.print(classInfo.get(i).getId()); %>);"
					class="btn btn-danger icon-btn"><i class="fa fa-trash"></i></a></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<a href="#" class="btn btn-primary view-class-detail bottom-margin" id="addClassDetail1"><i
		class="fa fa-plus"></i> Class Detail</a>
</div>
<div class="class-detail-new" style="display: none;">
	<h4>Add New Class Detail</h4>
	<div id="error-class-detail" class="has-error"></div>
	<form method="post" action="" class="form-horizontal" id="submitForm">
		<input type="hidden" name="class_id" id="class_id" value="" />
		<input type="hidden" name="feeItemCnt" id="feeItemCnt" value="1" />
		<input type="hidden" name="oldfeeCnt" id="oldfeeCnt" value="1" />
		<div class="form-group">
			<label class="col-sm-2 control-label">Standard *</label>
			<div class="col-sm-4">
				<select id="standard" name="standard" class="form-control">
					<option value="0">-- Select standard --</option>
					<%
					if(standardTypes.size() > 0){
						for (int i = 0; i < standardTypes.size(); i++) {

							StandardType standardType = standardTypes.get(i);
					%>
					<option value="<%out.print(standardType.getId());%>">
						<%
							out.print(standardType.getName());
						%>
					</option>

					<%
						}
					}
					%>

				</select>
			</div>
			<div class="col-sm-6">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">This is the name of the class. It
						might be used in the template of the email/SMS we send, so be
						careful.</div>
				</div>
			</div>
		</div>


		 <div class="form-group">
			<label class="col-sm-2 control-label">Stream *</label>
			<div class="col-sm-6">
				<select id="stream" name="stream" class="form-control">
					<option value="0">-- Select --</option>
					<%
					    if(streamTypes.size() > 0){
						for (int j = 0; j < streamTypes.size(); j++) {
							StreamType streamType = streamTypes.get(j);
					%>
					<option value="<%out.print(streamType.getId());%>">
						<%
							out.print(streamType.getTitle());
						%>
					</option>
					<%
						}
					    }
					%>
				</select>
			</div>
			<div class="col-sm-4">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">Stream of class </div>
				</div>
			</div>
		</div> 


		<div class="form-group">
			<label class="col-sm-2 control-label">Subject Taught *</label>
			<div class="col-sm-6">
				<div class="inline-checkboxes-holder" id="subjects">

					<%
						if (subjects.size() > 0) {
							for (int i = 0, j = 0; i < subjects.size(); i++) {

								Subject subject = subjects.get(i);
								
					%><div class="col-sm-3">
						
						<label class="checkbox"> <input type="checkbox"
							id="subject" name="subject[]"
							value="<%out.print(subject.getId());%>"> <%
								out.print(subject.getName());
							%>
						</label>
				     </div>
					<%
							}
						}//end of if
					%>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Teaching approach *</label>
			<div class="col-sm-4">
				<select id="teaching_approach" class="form-control"
					name="teaching_approach">
					<option value="0">-- Select --</option>
					<%
					if(teachingApproachTypes.size() > 0)
					{
						for (int j = 0; j < teachingApproachTypes.size(); j++) {
							TeachingApproachType teachingApproachType = teachingApproachTypes
									.get(j);
					%>
					<option value="<%out.print(teachingApproachType.getId());%>">
						<%
							out.print(teachingApproachType.getName());
						%>
					</option>
					<%
						}
					}
					%>
				</select>
			</div>
			<div class="col-sm-6">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">This is the role of the class
						detail just to keep track of it.</div>
				</div>
			</div>
		</div>

		<h4>ADMISSION DETAILS</h4>
		(Eligibility criteria)

		<div class="form-group">
			<label class="col-sm-2 control-label">Total Seats </label>
			<div class="col-sm-2">
				<input type="text" class="form-control" placeholder="No of seats"
					id="total_seats">
			</div>
			<div class="col-sm-8">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(No. of seats in standard or
						available on request)</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Seats Vacant </label>
			<div class="col-sm-2">
				<input type="text" class="form-control"
					placeholder="no of seats vacant" id="vacant_seats">
			</div>
			<div class="col-sm-8">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(No. of vacant seats in Standard
						or available on request)</div>
				</div>
			</div>
		</div>

<div class="form-group">
			<label class="col-sm-2 control-label">Morning timing from</label>
			<div class="col-sm-2">
				<input type="text" class="form-control"
					placeholder="hh:mm:ss" id="morning_time_from">
			</div>
			<div class="col-sm-4">
				<label class="col-sm-2 control-label">to</label>
				<div class="col-sm-4">
					<input type="text" class="form-control"
						placeholder="hh:mm:ss" id="morning_time_to">
				</div>
			</div>
		
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label">Evening timing from</label>
			<div class="col-sm-2">
				<input type="text" class="form-control"
					placeholder="hh:mm:ss" id="evening_time_from">
			</div>
			<div class="col-sm-4">
				<label class="col-sm-2 control-label">to</label>
				<div class="col-sm-4">
					<input type="text" class="form-control"
						placeholder="hh:mm:ss" id="evening_time_to">
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Admission deadline </label>
			<div class="col-sm-4">
				<input type="text" class="form-control"
					placeholder="admission deadline" id="admission_deadline">
			</div>
			<div class="col-sm-6">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(yyyy-mm-dd)</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Admission from </label>
			<div class="col-sm-4">
				<input type="text" class="form-control" placeholder="admission from"
					id="admission_from">
			</div>
			<div class="col-sm-6">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(yyyy-mm-dd)
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Admission to </label>
			<div class="col-sm-4">
				<input type="text" class="form-control" placeholder="admission to"
					id="admission_to">
			</div>
			<div class="col-sm-6">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(yyyy-mm-dd)</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Eligibility Criteria</label>
			<div class="col-sm-6">
				<textarea class="form-control" rows="4"
					placeholder="Enter eligibility criteria" id='ecriteria'></textarea>
			</div>
			<div class="col-sm-4">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(Require any specialization in
						student for admission)</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Specialization (if any)</label>
			<div class="col-sm-6">
				<textarea class="form-control" rows="4"
					placeholder="Enter specialization" id='specialization'></textarea>
			</div>
			<div class="col-sm-4">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(Require any specialization in
						student for admission)</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Admission
				Procedure(List of required documents)</label>
			<div class="col-sm-6">
				<textarea class="form-control" rows="4"
					placeholder="Enter admission procedure" id='admission_procedure'></textarea>
			</div>
			<div class="col-sm-4">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(Require any specialization in
						student for admission)</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">How to Apply</label>
			<div class="col-sm-6">
				<textarea class="form-control" rows="4"
					placeholder="Enter how to apply details" id='how_to_apply'></textarea>
			</div>
			<div class="col-sm-4">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(Information regarding form
						filling, Online or Offline, Exclusive through us etc.)</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Accessories Provided *</label>
			<div class="col-sm-8">
				<div class="inline-checkboxes-holder">
					<%
                      if(accessories.size() > 0)
                      {
               		    for(int i = 0; i < accessories.size(); i++)
            			{
               			Accessories accessories2 = accessories.get(i);   
               			%>
               			<div class="col-sm-3">
						
						<label class="checkbox"> <input type="checkbox"
							id="accessories" name="accessories[]"
							value="<%out.print(accessories2.getId());%>"> <% out.print(accessories2.getName()); %>
						</label>
						
					</div>
					<%
                 		}
                   	}
				%>
				</div>
			</div>
		</div>

		<h4>Fee Details</h4>
		(Fee, Fee Payment terms etc.)
			<div class="form-group">
				<label class="col-sm-2 control-label">Fee payment term</label>
				<div class="col-sm-6">
					<textarea class="form-control" rows="4"
						placeholder="Enter fee payment terms" id='fee_pay_term'></textarea>
				</div>
				<div class="col-sm-4">
					<div class="tooltip custom-tool-tip right">
						<div class="tooltip-arrow"></div>
						<div class="tooltip-inner">(Require any specialization in
							student for admission)</div>
					</div>
				</div>
			</div>
		<div class="form-group">
			<label class="col-sm-6 control-label">Fee Type *</label> <label
				class="col-sm-2 control-label">Amount *</label>
		</div>

		<div class="form-group">

			<div class="col-sm-6">
				<select  name="fee_desc[]" class="fee_desc1" id="cb1"   multiple>
					<option value="0">-- Select fee type --</option>
					<%
						for (int i = 0; i < feetypes.size(); i++) {
							FeeType feeType = feetypes.get(i);
					%>
					<option value="<%out.print(feeType.getTitle());%>">
						<%
							out.print(feeType.getTitle());
						%>
					</option>

					<%
						}
					%>
				</select>
			</div>
			<div class="col-sm-2">
				<input type="text" maxlength="7" style="margin: 4px 5px 0 5px;" placeholder="0" id= 'txt1'
					 name="fee_amount[]" value = "" >

			</div>
			<div class="col-sm-2">
				<div class="tooltip custom-tool-tip right">

					<div class="tooltip-inner" id ="divaddmore">
						<span
							style="font: normal 12px agency, arial; color: blue; text-decoration: underline; cursor: pointer;"
							onclick="addMoreRows(this.form);"> Add More </span>
					</div>
				</div>
			</div>


		</div>
		<div id="addedRows">
		<!-- xxxxxxx -->
		
		
		<!--xxx  -->
		</div>

		<div class="form-group">
			<div class="col-sm-4">
				<button type="button" id='saveclassdetail' class="btn btn-success">Save</button>
				<button type="button" id='updateclassdetail' class="btn btn-success"
					style="display: none;">Update</button>
				<button class="btn btn-default list-id cancel-class-detail" onclick="removeRow();"
					type="reset">Cancel</button>
			</div>
		</div>
	</form>
</div>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/selectize.css" />
<script type="text/javascript" src="${baseUrl}/js/selectize.min.js"></script>
<!-- datepicker Js -->
<script src="${baseUrl}/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
$(function() {
	$('#morning_time_to').timepicker({
        minuteStep: 1,
        secondStep: 5,
        showSeconds: true,
        showMeridian: false,
       // defaultTime:'00:00:00'
    });
	$('#morning_time_from').timepicker({
        minuteStep: 1,
        secondStep: 5,
        showSeconds: true,
        showMeridian: false,
       // defaultTime:'00:00:00'
    });
	$('#evening_time_to').timepicker({
        minuteStep: 1,
        secondStep: 5,
        showSeconds: true,
        showMeridian: false,
       // defaultTime:'00:00:00'
    });
	$('#evening_time_from').timepicker({
        minuteStep: 1,
        secondStep: 5,
        showSeconds: true,
        showMeridian: false,
    });
	
  });
$("#admission_deadline").datepicker('setDate',new Date());
$("#admission_from").datepicker('setDate',new Date());
$("#admission_to").datepicker('setDate',new Date());
var arr_feetype_perm = [];
var feetype_perm_json = [];
var arr_feetype_temp = [];
var arr_feetype_sel = [];
var arr_feetype_del = [];
var rowCount = 1;
<%
for (int i = 0; i < feetypes.size(); i++) {
%>
	arr_feetype_perm.push('<%out.print(feetypes.get(i).getTitle());%>');
	arr_feetype_temp = arr_feetype_perm.slice();
	

	var jsonArg2 = new Object();
	jsonArg2.text = '<%out.print(feetypes.get(i).getTitle());%>';
	jsonArg2.value = '<%out.print(feetypes.get(i).getTitle());%>';
	feetype_perm_json.push(jsonArg2);
<% } 
%>
	
	var $select_fee,select_fee;
	$('#admission_deadline').datepicker({
		 format: 'yyyy-mm-dd',
		    startDate: '2010-01-01'
	});
	$('#admission_from').datepicker({ format: 'yyyy-mm-dd',
	startDate: '2010-01-01'});
	$('#admission_to').datepicker({ format: 'yyyy-mm-dd',
	startDate: '2010-01-01'});
	$select_fee = $('.fee_desc1').selectize({
			plugins: ['remove_button'],
			delimiter: '+',
			persist: false,
			create: function(input) {
			   return {
			       value: input,
			       text: input
			   }
			},
			onChange: function(value) {		    	
		         arr_feetype_sel = SplitTheString(value);
		         removeArrayItem (arr_feetype_sel);
		     },
			  onItemRemove : function(value) {
				  addArrayItem(value)
			  }
	});
	select_fee  = $select_fee[0].selectize;



	
	function SplitTheString(CommaSepStr) {
		 var ResultArray = []; 
	
	       if (CommaSepStr!= null) {
	           var SplitChars = '+';
	           if (CommaSepStr.indexOf(SplitChars) >= 0) {
	               ResultArray = CommaSepStr.split(SplitChars);
	
	           }else {
	           	ResultArray [0] =CommaSepStr;
	           }
	       }
	      return ResultArray[0] ;
	   }

$('#updateclassdetail').click(function() {
	var school_id = <%out.print(school_id6);%>
	var user_id = <%out.print(user_id6);%>
	var subject = null;
 	//if($("#cb1").val() != "" && $("#txt1").val() != "")
	fee_data = getFeeData();
	subject_data = getSubjectData();
	accessory_data = getAccessoryData();
	class_info_data = getClassInfoData();
   
	var msg = "";

	if ($('#standard').val() == 0) {
		if(msg != "") msg = msg+",Please select standard"; else  msg = "Please select standard";
	}
	if ($("#section").val() == 0) {
		if(msg != "") msg = msg+",Please select section";  else msg = "Please select section";
	} 
	if ($("#stream").val() == 0) {
		if(msg != "") msg = msg+",Please select stream"; else msg = "Please select stream";
	}
	if($("#vacant_seats").val() != "" && $("#total_seats").val() != "")	{
	if(parseInt($("#vacant_seats").val()) > parseInt($("#total_seats").val())){
			if(msg != "") msg = msg+",Vacant seats should not exceed total seat"; else msg = "Vacant seats should not exceed total seat";
		}
	}
	 	  if($("#morning_time_from").val() == ""){
				 $("#morning_time_from").val("00:00:00");
			} 
			if($("#morning_time_to").val() == ""){
			 $("#morning_time_to").val("00:00:00");
			}
			if($("#evening_time_from").val() == "")
			{
				$("#evening_time_from").val("00:00:00");
			}
			if($("#evening_time_to").val() == "")
			{
				$("#evening_time_to").val("00:00:00");
			}
// 	if($('#admission_deadline').val() < $("#admission_to").val()){
// 		if(msg != "") msg = msg+",Admission deadline date should be greater than admission to"; else msg = "Please select admission deadline";
// 	}
// 	 if($("#admission_to").val() > $("#admission_from").val()){
// 		if(msg != "") msg = msg+",Admission to date should not greater than admission from date"; else msg = "Admission to(date) should not greater than admission from(date)";
// 	} 
// 	 if($("#admission_from").val() == ""){
// 		if(msg != "") msg = msg+",Please select admission from"; else msg = "Please select admission from";
// 	}
//     if($("#admission_to").val() == ""){
//     	if(msg != "") msg = msg+",Please select admission to"; else msg = "Please select admission to";
// 	}
//    if($("#admission_deadline").val() == ""){
// 	   if(msg != "") msg = msg+",Please select admission deadline"; else msg = "Please select admission deadline";
// 	}
//    if(($("#morning_time_to").val() == "" || $("#morning_time_from").val()	== "") && ($("#evening_time_to" ).val() == "" || $("#evening_time_from").val() == "")){
// 	 if(msg != "") msg = msg+",Please select morning shift time or evening shift time"; else msg = "Please select morning shift time or evening shift time";
//    }
   if(msg != "") {
	       alert(msg);
   }else{
	  
			fee_data = getFeeData();
		subject_data = getSubjectData();
		accessory_data = getAccessoryData();
		class_info_data = getClassInfoData();
		var final_data = [];
		final_data = { classInfo : class_info_data,classFee:fee_data,classSubjects:subject_data,classAccessories: accessory_data };
		$.ajax({
		    url: 'webapi/school/updateclassdetail',
		    type: 'POST',
		    data: JSON.stringify(final_data),
		    contentType: 'application/json; charset=utf-8',
		    dataType: 'json',
		    async: false,
		    success: function(data) {
		    	
		    	arr_feetype_temp = arr_feetype_perm.slice();
		    	$(".cancel-class-detail").click();
				if (data.status == 0) {
					alert(data.message);
				} else {
					 alert(data.message);
					 getClassList(school_id);
					 clearAllFields();
		 			 updateProgress(school_id);
		 			$("#saveclassdetail").show();
		 			$("#updateclassdetail").hide();
					
				}
			},
			error : function(data)
			{
				alert("Fail to save data"+JSON.stringify(data,null,2));
			}
			
		});
	}
});

$("#addClassDetail").click(function(){
	$("#saveclassdetail").show();
	$("#updateclassdetail").hide();
});

$("#addClassDetail1").click(function(){
	$("#saveclassdetail").show();
	$("#updateclassdetail").hide();
});

</script>
<script type="text/javascript">

	
	function getFeeCategory(){
		$('input[name="fee_desc[]"]:selected').each(function() { 
			for(var i in ary){
			    if(fee_cat[i]==$(this).val()){
			    	fee_cat.splice(i,1);
			        break;
			        }
			}
    	});
		return fee_cat;
	}
	
	function addMoreRows(frm) {
	  var blncheck =false;
	  blncheck = checkEmptyFields();
	  if (blncheck || arr_feetype_temp.length<1 ){ return false;}
	  
	  rowCount = rowCount +1;
	  console.log('#751');
	
		var cmb = '#cb'+rowCount; 
		var recRow = '<div class="form-group" id="rowCount'+rowCount+'">';
		recRow = recRow + '<div class="col-sm-6"><select id="cb'+rowCount+'" name="fee_desc[]" class="fee_desc'+rowCount+'" multiple></select></div>';
		recRow = recRow + '<div class="col-sm-2"><input id="txt'+rowCount+'" name="fee_amount[]" id = "txt'+rowCount+'" type="text"  maxlength="120" style="margin: 4px 5px 0 5px;" value ="0"/></div>';
		recRow = recRow + '<div class="col-sm-2"><a href="javascript:void(0);" onclick="removeRow('+rowCount+');">Delete</a></div></div>';
		// the div id, where new fields are to be added
	    
		jQuery('#addedRows').append(recRow);
		var select_cbo, $select_cbo;
		
 		var $select = $(cmb).selectize({
 			  plugins: ['remove_button'],
			 // on change of city dropdown populate zone dropdown			 
		  onChange: function(value) {		    	
		         //alert(value)
		         arr_feetype_sel = SplitTheString(value);
		         removeArrayItem (arr_feetype_sel);
		     },
		  onItemRemove : function(value) {
			  addArrayItem(value)
		  }
		 });
		
		// fetch the instance
		var selectize = $select[0].selectize;
		for(var i = 0; i < arr_feetype_temp.length; i++) {
		    $(cmb)[0].selectize.addOption({
				  value:arr_feetype_temp[i],
				  text:arr_feetype_temp[i]
				}); 
		}
	}
	
	function removeArrayItem(selvalArr){
		var found =-1;
		for(var i = 0; i < arr_feetype_temp.length; i++) {
			found =  $.inArray( arr_feetype_temp[i], selvalArr );
			if(found > -1){
				arr_feetype_temp.splice(i,1);
			}
		}
		
		 if( arr_feetype_temp.length <1) {$("#divaddmore").hide(); }else{ $("#divaddmore").show(); } 
	}
	
	function addArrayItem(arrItem){
		arr_feetype_temp.push(arrItem);
	}
	
	
	function checkEmptyFields() {
		
		var selval ="";
		var selamt = 0;
		var i = 0;
		var cb = "#cb"+rowCount;
		var txt = "#txt"+rowCount;
		var blnError =false;
		if( $(cb).length )         // use this if you are using id to check
		{
		     	// it exists
				$(cb).each(function(i, selectedElement) {
					selval = $(selectedElement).val() ;
				 });
				$(txt).each(function(i, selectedElement) {
					selamt = $(selectedElement).val() ;
				 });
			    if(selval == null ) {
			    	$(cb).addClass('has-error');
			    	blnError = true;
			    }else if(selamt == 0) {
			    	$(txt).addClass('has-error');
			    	blnError = true;
			    }else
			    	$(txt).removeClass('has-error');
				 	return blnError;
		}else
			return false;
	}
	
	// Get the selectize control instance
	function removeRow(removeNum) {
		var divname = "#rowCount"+removeNum;
		var cb = "#cb"+removeNum;
		var arr_feetype_sel1 = []; 
		
		if( $(cb).length )         // use this if you are using id to check
		{
			$(cb).each(function(i, selectedElement) {
				selval = $(selectedElement).val() ;
			 });
			 if( selval != null){
				 arr_feetype_sel1 = SplitTheString(selval);
			 }
			 for (j=0;j<= arr_feetype_sel1.length; j++){
				 addArrayItem (arr_feetype_sel1[j]);
			 }
		}
		if(arr_feetype_temp.length >0)
			 $("#divaddmore").show(); 
		$( "div" ).remove(divname);
		
	}
	$(".cancel-class-detail").click(function(){
		
		$("#addedRows").html("");
		$("#txt1").val("");
		$("#cb1").val("");
		select_fee.clearOptions();
		for(var i = 0; i < arr_feetype_perm.length; i++) {
			select_fee.addOption({
				  value:arr_feetype_perm[i],
				  text:arr_feetype_perm[i]
				}); 
		}
		arr_feetype_temp = arr_feetype_perm.slice();
		if(arr_feetype_temp.length >0)
			 $("#divaddmore").show();
	});
	function getFeeData(){
		var fee_data = [];
		var fee_item;
		var totalcnt =rowCount;
		console.log("row count : "+rowCount);
		var str_val ="";
		for (i = 1; i <= rowCount; i++) {
			var sep = ",";
			var rep = "+";
			var item_name = '#cb'+i;
			var item_amt = '#txt'+i;
			if($(item_name).val() != 'undefined' && $(item_name).val() != null )
				str_val = $(item_name).val().toString();
			
			//console.log("before replace 2 =  "+str_val);
			//if(str_val !="" && str_val != 'undefined')	
				var str_val = str_val.replace(/,/gi, "+"); 
			//console.log("after replace =  "+str_val);
			if(str_val != "" && $(item_amt).val() >0  ){
				if($("#class_id").val() >0)
					fee_item = { feeDesc:str_val ,amount:$(item_amt).val(),classInfo : {id :$("#class_id").val()} };
				else
					fee_item = { feeDesc:str_val ,amount:$(item_amt).val() };
				//alert(str_val);
				fee_data.push(fee_item);
			}
			
		}
		//console.log('selected fee at '+fee_data);
		return fee_data;
	}




	function getSubjectData() {
		var subject = null;
		var subjectItem =null;
		var school_id = <%out.print(school_id6);%>;
		var subject_data = [];
		$('input[name="subject[]"]:checked').each(function() {
			if($("#class_id").val() >0)
				subjectItem = {subject:{id:$(this).val()},classInfo:{id : $("#class_id").val()}, school:{id:school_id}};
			else
				subjectItem = {subject:{id:$(this).val()}};
			subject_data.push(subjectItem);
		});
		return subject_data;
	}

	function getAccessoryData() {
		var accessories = null;
		var accessoryItem =null;
		var accessory_data = [];
		$('input[name="accessories[]"]:checked').each(function() {
				if($("#class_id").val() >0)
					accessoryItem = {accessories:{id:$(this).val()},classInfo:{id : $("#class_id").val()}};
				else
					accessoryItem = {accessories:{id:$(this).val()}};
				accessory_data.push(accessoryItem);
		});
		return accessory_data;
	}

	function getClassInfoData () {
		var school_id = <%out.print(school_id6);%>
		if($("#class_id").val() >0) {
			var classInfo = {
					school:{id:school_id},
					id : $("#class_id").val(),
					standardType:{id:$("#standard").val()},
					streamType:{id:$("#stream").val()},
					teachingApproachType :{id : $("#teaching_approach").val()} ,
					vacantSeat : $("#vacant_seats").val(),
					totalSeat : $("#total_seats").val(),
					eligibilityCriteria : $("#ecriteria").val(),
					specialization : $("#specialization").val(),
					admissionProcess :  $("#admission_procedure").val(),
					howToApply : $("#how_to_apply").val(),
					admissionDeadline : new Date($("#admission_deadline").val()),
					admissionFrom : new Date($("#admission_from").val()),
					admissionTo : new Date($("#admission_to").val()),
					feesPaymentTerm : $("#fee_pay_term").val(),
					eligibilityCriteria : $("#ecriteria").val(),
					morningTimeFrom : $("#morning_time_from").val(),
					morningTimeTo   : $("#morning_time_to").val(),
					afternoonTimeFrom : $("#evening_time_from").val(),
					afternoonTimeTo   : $("#evening_time_to").val()
					};
		}else {
			var classInfo = {school:{id:school_id},
					standardType:{id:$("#standard").val()},
					streamType:{id:$("#stream").val()},
					teachingApproachType :{id : $("#teaching_approach").val()} ,
					vacantSeat : $("#vacant_seats").val(),
					totalSeat : $("#total_seats").val(),
					eligibilityCriteria : $("#ecriteria").val(),
					specialization : $("#specialization").val(),
					admissionProcess :  $("#admission_procedure").val(),
					howToApply : $("#how_to_apply").val(),
					admissionDeadline : new Date($("#admission_deadline").val()),
					admissionFrom : new Date($("#admission_from").val()),
					admissionTo : new Date($("#admission_to").val()),
					feesPaymentTerm : $("#fee_pay_term").val(),
					eligibilityCriteria : $("#ecriteria").val(),
					morningTimeFrom : $("#morning_time_from").val(),
					morningTimeTo   : $("#morning_time_to").val(),
					afternoonTimeFrom : $("#evening_time_from").val(),
					afternoonTimeTo   : $("#evening_time_to").val()
					};
		}
		return classInfo;
	}

	$('#saveclassdetail').click(function() {
		var school_id = <%out.print(school_id6);%>
		var user_id = <%out.print(user_id6);%>
		var subject = "";
		var fee_data = "";
		var msg = "";

		if ($('#standard').val() == 0) {
			if(msg != "") msg = msg+",Please select standard"; else  msg = "Please select standard";
		}
		if ($("#section").val() == 0) {
			if(msg != "") msg = msg+",Please select section";  else msg = "Please select section";
		} 
		if ($("#stream").val() == 0) {
			if(msg != "") msg = msg+",Please select stream"; else msg = "Please select stream";
		}
		if($("#vacant_seats").val() != "" && $("#total_seats").val() != ""){
		if(parseInt($("#vacant_seats").val()) > parseInt($("#total_seats").val())){
			if(msg != "") msg = msg+",Vacant seats should not exceed total seat"; else msg = "Vacant seats should not exceed total seat";
			}
		}
	 	  if($("#morning_time_from").val() == ""){
				 $("#morning_time_from").val("00:00:00");
			} 
			if($("#morning_time_to").val() == ""){
			 $("#morning_time_to").val("00:00:00");
			}
			if($("#evening_time_from").val() == "")
			{
				$("#evening_time_from").val("00:00:00");
			}
			if($("#evening_time_to").val() == "")
			{
				$("#evening_time_to").val("00:00:00");
			}
	   if(msg != "") {
		       alert(msg);
	   }else{
		  // if($("#cb1").val() != "" && $("#txt1").val() != "")
			fee_data = getFeeData();
			subject_data = getSubjectData();
			accessory_data = getAccessoryData();
			class_info_data = getClassInfoData();
			var final_data = [];
			final_data = { classInfo : class_info_data,classFee:fee_data,classSubjects:subject_data,classAccessories: accessory_data };
			$.ajax({
			    url: 'webapi/school/saveclassdetail',
			    type: 'POST',
			    data: JSON.stringify(final_data),
			    contentType: 'application/json; charset=utf-8',
			    dataType: 'json',
			    async: false,
			    success: function(data) {
					if (data.status == 0) {
						alert(data.message);
						
					} else {
						alert(data.message);
						clearAllFields();
						getClassList(school_id);
						
			 			 updateProgress(school_id);
					}
				},
				error : function(data)
				{
					alert("Fail to save data"+JSON.stringify(data,null,2));
				}
				
			});
			
		}
	});
	function clearAllFields()
	{
		$("#standard").val(0);
		//$("#stream").val(0);
		 $("#teaching_approach").val(0);
		 $("#vacant_seats").val("");
		 $("#total_seats").val("");
		 $("#ecriteria").val("");
		 $("#specialization").val("");
		$("#admission_procedure").val("");
		$("#how_to_apply").val("");
		$("#admission_deadline").val("");
		$("#admission_from").val("");
		$("#admission_to").val("");
		$("#fee_pay_term").val("");
		$( "input[type='checkbox']" ).prop( "checked", false);
		for(var i = 1;i<=rowCount;i++)
			{
				$("#txt"+i).val("");
			}
		$(".cancel-class-detail").click();
	}
	function getClassList(school_id)
	{
		$.get("webapi/school/getclasslist/"+school_id,{},function(newdata){
			var oTable = $("#class-detail-table").dataTable();
			    oTable.fnClearTable();
			$(newdata).each(function(index){
			    	html = "<a href='javascript:editClassInfo("+newdata[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"
                      +" <a href='deleteClassInfo("+newdata[index].id+");' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>";
			    	var row = [];
			    	 row.push(newdata[index].standardType.name);
			    	// row.push(newdata[index].sectionType.name);
			    	 row.push(newdata[index].teachingApproachType.name);
			    	 row.push(newdata[index].streamType.title);
			    	 row.push(newdata[index].totalSeat);
			    	 row.push(newdata[index].vacantSeat);
 			     row.push(html);
			    	oTable.fnAddData(row);
			    });
			$(".class-detail-new").hide();
			$(".class-detail-list").show();
		});
		
	}
	function editClassInfo(id){
		$.get('webapi/school/class_info/'+id,{},function(data){
			$(".class-detail-new").show();
		$(".class-detail-list").hide();
		$("#saveclassdetail").hide();
		$("#updateclassdetail").show();
		$('[name=standard]').val(data.classInfo.standardType.id);
		$('[name=stream]').val(data.classInfo.streamType.id);
		$('input[name="subject[]"]').each(function() {
			for(var i = 0; i < data.classSubjects.length; i++){
				if($(this).val() == data.classSubjects[i].subject.id){
		    		$(this).prop('checked', true);
				}
			}
	    });
		$('[name=teaching_approach]').val(data.classInfo.teachingApproachType.id);
		$("#total_seats").val(data.classInfo.totalSeat);
		$("#vacant_seats").val(data.classInfo.vacantSeat);
		$("#morning_time_from").val(data.classInfo.morningTimeFrom);
        $("#morning_time_to").val(data.classInfo.morningTimeTo);
        $("#evening_time_from").val(data.classInfo.afternoonTimeFrom);
        $("#evening_time_to").val(data.classInfo.afternoonTimeTo);
		$("#specialization").val(data.classInfo.specialization);
		$("#ecriteria").val(data.classInfo.eligibilityCriteria);
		$("#admission_procedure").val(data.classInfo.admissionProcess);
		$("#how_to_apply").val(data.classInfo.howToApply);
		if (typeof data.classInfo.admissionDeadline === 'undefined') {
			$("#admission_deadline").val("");
		}else{
			ddate = new Date(data.classInfo.admissionDeadline);
			$("#admission_deadline").val(ddate.getFullYear()+"-"+("0" + (ddate.getMonth()+1)).slice(-2)+"-"+("0" + ddate.getDate()).slice(-2));
		}
		
		if (typeof data.classInfo.admissionFrom === 'undefined') {
			$("#admission_from").val("");
		}
		else{
		tdate = new Date(data.classInfo.admissionFrom);
		$("#admission_from").val(tdate.getFullYear()+"-"+("0" + (tdate.getMonth()+1)).slice(-2)+"-"+("0" + tdate.getDate()).slice(-2));
		}
		
		if (typeof data.classInfo.admissionTo === 'undefined') {
			$("#admission_to").val("");
		}else{
			fdate = new Date(data.classInfo.admissionTo);
			$("#admission_to").val(fdate.getFullYear()+"-"+("0" + (fdate.getMonth()+1)).slice(-2)+"-"+("0" + fdate.getDate()).slice(-2));
		}
		$("#fee_pay_term").val(data.classInfo.feesPaymentTerm);
		$('input[name="accessories[]"]').each(function() {
			for(var i = 0; i < data.classAccessories.length; i++){
				if($(this).val() == data.classAccessories[i].accessories.id){
		    		$(this).prop('checked', true);
				}
			}
	    });
			var str1 = "";
			var arr = [];
			
			var txt = "#txt";
			
			if(data.classFee.length>0)
			for(var i = 0; i < data.classFee.length; i++){
				if(data.classFee[i].feeDesc != undefined && data.classFee[i].feeDesc != ""){
					str1 = data.classFee[i].feeDesc;
					if (str1.indexOf("+") >= 0 ) {
						arr = str1.split("+");
			        }else {
			        	 
			        	   arr [0] =str1;
			        }
				}
				if(i>=1 && i <= data.classFee.length-1) {
					addEditRows(i+1);
				}
				var cmb ="#cb"+(i+1);
				 for (j=0; j < arr.length; j++) {
					if(arr[j]) {
						$(cmb)[0].selectize.addItem(arr[j]);
						removeArrayItem(arr[j]);
						//console.log('#760+adding sel items at '+cmb+"with"+arr[j]);
					}
				}
				//show fee amount
				$(txt+(i+1)).val(data.classFee[i].amount ); 
				
			}
		$('#feeItemCnt').val(data.classFee.length);
		$('#oldfeeCnt').val(data.classFee.length);
		$("#class_id").val(data.classInfo.id);
		if(data.classFee.length >0)
			rowCount = data.classFee.length;
		console.log('#1191');
		
	});
	}
	
	function addEditRows(rowCount) {
		   var blncheck =false;
		   var cmb = '#cb'+rowCount; 
		   var recRow = '<div class="form-group" id="rowCount'+rowCount+'">';
			recRow = recRow + '<div class="col-sm-6"><select id="cb'+rowCount+'" name="fee_desc[]" class="fee_desc'+rowCount+'" multiple></select></div>';
			recRow = recRow + '<div class="col-sm-2"><input id="txt'+rowCount+'" name="fee_amount[]" id = "txt'+rowCount+'" type="text"  maxlength="120" style="margin: 4px 5px 0 5px;" value ="0"/></div>';
			recRow = recRow + '<div class="col-sm-2"><a href="javascript:void(0);" onclick="removeRow('+rowCount+');">Delete</a></div></div>';
			// the div id, where new fields are to be added
		    
			jQuery('#addedRows').append(recRow);
			var select_cbo, $select_cbo;
			
	 		var $select = $(cmb).selectize({
	 			  plugins: ['remove_button'],
				 // on change of city dropdown populate zone dropdown			 
			  onChange: function(value) {		    	
			         //alert(value)
			         arr_feetype_sel = SplitTheString(value);
			         removeArrayItem (arr_feetype_sel);
			     },
			  onItemRemove : function(value) {
				  addArrayItem(value)
			  }
			 });
			
			// fetch the instance
			var selectize = $select[0].selectize;
			for(var i = 0; i < arr_feetype_temp.length; i++) {
			    $(cmb)[0].selectize.addOption({
					  value:arr_feetype_temp[i],
					  text:arr_feetype_temp[i]
					}); 
			}
		}
		
</script>
