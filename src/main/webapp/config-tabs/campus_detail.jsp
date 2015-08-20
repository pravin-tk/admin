<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.model.AreaUnit"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.CampusInfo"%>
<%@page import="org.school.admin.dao.CampusDAOImpl"%>
<%@page import="java.util.List"%>
<% 
	SettingsImpl settingsImpl = new SettingsImpl();
	List<AreaUnit> areaUnit = settingsImpl.getAreaUnits();
	int unitSize = areaUnit.size();
	int school_id1 = Integer.parseInt(request.getParameter("school_id"));
	CampusDAOImpl campusDAOImpl = new CampusDAOImpl();
	CampusInfo campusInfo = new CampusInfo();
	List<CampusInfo> campusInfoList = null;
	campusInfoList = campusDAOImpl.getCampusInfo(school_id1);
	if(campusInfoList.size() > 0){
		campusInfo = campusInfoList.get(0);
	}
%> 
<form method="post" action="" class="form-horizontal" id="campus_detail">
	<div class="form-group">
	  <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Campus size</label>
	  <div class="col-sm-6">
	      <input data-brackets-id="3402" type="text" class="form-control" id="txt_name" placeholder="enter campus size" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getCampusSize());}%>">
	  </div>
	</div>
	<div class="form-group">
	    <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Unit of campus size</label>
	    <div class="col-sm-6">
	       	<select name="area_unit" id="area_unit" class="form-control">
	       		<option value="0">Select Area Unit</option>
	       	<%  if(unitSize > 0){
	       	for(int i = 0; i < unitSize; i++){ 
	       		String selected = "";
	       		if(campusInfoList.size() > 0){
		       		if(areaUnit.get(i).getId() == campusInfo.getAreaUnit().getId()){
		       			selected = "selected";
		       		} else {
		       			selected = "";
		       		}
	       		}
	       	%>
	       		<option value="<% out.print(areaUnit.get(i).getId()); %>" <%out.print(selected); %>><% out.print(areaUnit.get(i).getName()); %></option>
	       	<%} 
	       	}%>
	       	</select>
	    </div>
	</div>
	<div class="form-group">
	  <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">No of building </label>
	  <div class="col-sm-6">
	      <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_bldng" placeholder="enter no of buildings" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalBuildings());}%>">
	  </div>
	</div>
	                              
	<div class="form-group">
	  <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">No of playgrounds </label>
	  <div class="col-sm-6">
	      <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_playground" onKeyPress="return checkNumber(event)" placeholder="enter no of playgrounds" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalPlaygrounds());}%>">
	  </div>
	</div>
	                              
	
	                              
	<div class="form-group">
	  <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">No of students (boys) </label>
	  <div class="col-sm-6">
	      <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_student_boys" onKeyPress="return checkNumber(event)"
	       placeholder="enter no of students boys" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalBoys());}%>">
	  </div>
	</div>
	                              
	<div class="form-group">
	    <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">No of students (girls) </label>
	    <div class="col-sm-6">
	        <input data-brackets-id="3402" type="text" onkeyup="sum();" class="form-control" id="txt_no_of_student_girls" onKeyPress="return checkNumber(event)"
	         placeholder="enter no of students girls" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalGirls()); }%>">
	    </div>
	</div>
	 	
	<div class="form-group">
	  <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">No of students </label>
	  <div class="col-sm-6">
	      <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_student"   placeholder="enter no of students" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalStudents()); }%>" disabled>
	  </div>
	</div>	 	
	 	
	<div class="form-group">
	    <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">  No of teaching staff(Male) </label>
	    <div class="col-sm-6">
	        <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_male_teaching_staff" onKeyPress="return checkNumber(event)"
	        placeholder="enter no of  male teaching staff" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalMaleTeacher());} %>">
	    </div>
	</div>
	<div class="form-group">
	    <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">  No of teaching staff(Female) </label>
	    <div class="col-sm-6">
	        <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_female_teaching_staff" onKeyPress="return checkNumber(event)"
	        placeholder="enter no of female teaching staff " value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getTotalFemaleTeacher()); }%>">
	    </div>
	</div>
	   <div class="form-group">
	    <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">  No of supporting staff(Male) </label>
	    <div class="col-sm-6">
	        <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_male_supporting_staff" onKeyPress="return checkNumber(event)"
	        placeholder="enter no of supporting staff male" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getMaleSupportingStaff()); }%>">
	    </div>
	</div>
	<div class="form-group">
	    <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip..."> 
	     No of supporting staff(Female) *</label>
	    <div class="col-sm-6">
	        <input data-brackets-id="3402" type="text" class="form-control" id="txt_no_of_female_supporting_staff" onKeyPress="return checkNumber(event)"
	        placeholder="enter no of supporting staff female" value="<% if(campusInfoList.size() > 0){ out.print(campusInfo.getFemaleSupportingStaff()); }%>">
	    </div>
	</div>
	
	<div class="form-group">
	    <div class="col-sm-2 col-sm-offset-2">
	    <% if(campusInfo.getId() > 0) {%>
	    	<button type="button" id = "updatecampusdetails"  data-toggle="modal" data-target="#updateCampusDetail" class="btn btn-success">Update</button>
	    <% } else { %>
	        <button type="button" id = "savecampusdetails" class="btn btn-success">Save</button>
	    <% } %>
	    </div>
	</div>
	 <div class="modal fade" id="updateCampusDetail" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width:450px;">
                 <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                       </button>
                           <h4 class="modal-title" id="myModalLabel">Reason for update</h4>
                 </div>
                  <div class="modal-body">
                       <div class="input-group margin-bottom-sm col-sm-6">
                            <textarea id="updateCampusReason"  style="width:350px;margin-left:20px;height:120px"></textarea>
                        </div>
                  </div>
                   <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button type="button" id="updatecampusdetail" class="btn btn-success">Update</button>
                   </div>
              </div>
        </div>
     </div>
</form>
<script type="text/javascript">
  		 function sum()
 		 {
  			
  			if( $("#txt_no_of_student_boys").val() != "" && $('#txt_no_of_student_girls').val() !=""){
  				var sum = 0;
	   			sum = parseInt( $("#txt_no_of_student_boys").val())+parseInt( $('#txt_no_of_student_girls').val());
	   			$('#txt_no_of_student').val(sum);
  			}else{
  				$('#txt_no_of_student').val("0");
  			}
 		 }
  		 
         function checkNumber(evt)
        	{
        		evt = (evt) ? evt : window.event
        		var charCode = (evt.which) ? evt.which :evt.keyCode
        		if(charCode > 31 && (charCode>57  ||   charCode < 48)){
        			return false;
        		}		  
        		return true;
        	}
 		$('#savecampusdetails').click(function()
 			{
 			 var msg="";
  			 if($("#txt_no_of_student_boys").val() == "" || $("#txt_no_of_student_boys").val() == null){
	   			if(msg != "") msg+=", Please enter number of boys"; else msg="Please enter number of boys";
  			 }
  			 if( $('#txt_no_of_student_girls').val() == ""){
  				if(msg != "") msg+=", Please enter number of girls"; else msg="Please enter number of girls";
  			 }
  			 if(msg != ""){
  				 alert(msg);
  			 }else{
  				 
  			 
	 			var school_id = $('#school_id').val();
	 			var updated_by = $('#updated_by').val();
	 			var campus_size = $('#txt_name').val();
	 			var area_unit = $('#area_unit').val();
	 			var no_of_building = $('#txt_no_of_bldng').val();
	 			var no_of_playgrounds = $('#txt_no_of_playground').val();
	 			var no_of_total_student = $('#txt_no_of_student').val();
	 			var txt_no_of_student_boys = $('#txt_no_of_student_boys').val();
	 			var txt_no_of_student_girls = $('#txt_no_of_student_girls').val();
	 			var txt_no_of_male_teaching_staff = $('#txt_no_of_male_teaching_staff').val();
	 			var txt_no_of_female_teaching_staff = $('#txt_no_of_female_teaching_staff').val();
	 			var txt_no_of_male_supporting_staff = $('#txt_no_of_male_supporting_staff').val();
	 			var txt_no_of_female_supporting_staff = $('#txt_no_of_female_supporting_staff').val();
	 			
	//  			if(no_of_building == "" 
	//  					|| no_of_playgrounds == "" 
	//  					||campus_size == "" 
	//  					|| no_of_total_student == "" 
	//  					|| txt_no_of_student_boys == ""
	//  					|| txt_no_of_student_girls == ""
	//  					|| txt_no_of_male_teaching_staff == ""
	//  					|| txt_no_of_female_teaching_staff == ""
	//  					|| txt_no_of_male_supporting_staff == ""
	//  					|| txt_no_of_female_supporting_staff == "")
	//  				{
	//  				  alert("All fields are mendatory");
	//  				}
	//  			else{
	 			$.ajax({
	 				
	 				url : 'webapi/campus/savecampus',
	 				type:'POST',
	 				data : {
	 					school_id : school_id,
	 					updated_by : updated_by,
	 					campus_size : campus_size,
	 					area_unit : area_unit,
	 					no_of_building : no_of_building,
	 					no_of_playground : no_of_playgrounds,
	 					no_of_total_student : no_of_total_student,
	 					no_of_student_boys : txt_no_of_student_boys,
	 					no_of_student_girls : txt_no_of_student_girls,
	 					no_of_male_teaching_staff : txt_no_of_male_teaching_staff,
	 					no_of_female_teaching_staff : txt_no_of_female_teaching_staff,
	 					no_of_male_supporting_staff : txt_no_of_male_supporting_staff,
	 					no_of_female_supporting_staff  : txt_no_of_female_supporting_staff
	 				},
	 				success : function(data)
	 				{
	 						
	 					if(data.status == 1){
	 						alert(data.message);	
	 						updateProgress($('#school_id').val());
	//  						$('#txt_name').val("");
	//  			 			$('#area_unit').val("");
	//  			 			$('#txt_no_of_bldng').val("");
	//  			 			$('#txt_no_of_playground').val("");
	//  			 			$('#txt_no_of_student').val("");
	//  			 			$('#txt_no_of_student_boys').val("");
	//  			 			$('#txt_no_of_student_girls').val("");
	//  			 			$('#txt_no_of_male_teaching_staff').val("");
	//  			 			$('#txt_no_of_female_teaching_staff').val("");
	//  			 			$('#txt_no_of_male_supporting_staff').val("");
	//  			 			$('#txt_no_of_female_supporting_staff').val("");
	 					}
	 					else
	 						alert(data.message);
	 					
	 			 			
	 						
	 				},
	 				error : function(data)
	 				{
	 					alert("Fail to save campus detail");
	 				}
	 			
	 			});
	 			}
 			//alert("campus details");
 		});
 		function updateCampus(strReason)
 		{
 			var school_id = $('#school_id').val();
 			var updated_by = $('#updated_by').val();
 			var campus_size = $('#txt_name').val();
 			var area_unit = $('#area_unit').val();
 			var no_of_building = $('#txt_no_of_bldng').val();
 			var no_of_playgrounds = $('#txt_no_of_playground').val();
 			var no_of_total_student = $('#txt_no_of_student').val();
 			var txt_no_of_student_boys = $('#txt_no_of_student_boys').val();
 			var txt_no_of_student_girls = $('#txt_no_of_student_girls').val();
 			var txt_no_of_male_teaching_staff = $('#txt_no_of_male_teaching_staff').val();
 			var txt_no_of_female_teaching_staff = $('#txt_no_of_female_teaching_staff').val();
 			var txt_no_of_male_supporting_staff = $('#txt_no_of_male_supporting_staff').val();
 			var txt_no_of_female_supporting_staff = $('#txt_no_of_female_supporting_staff').val();
 			
//  			if(no_of_building == "" 
//  					|| no_of_playgrounds == "" 
//  					||campus_size == "" 
//  					|| no_of_total_student == "" 
//  					|| txt_no_of_student_boys == ""
//  					|| txt_no_of_student_girls == ""
//  					|| txt_no_of_male_teaching_staff == ""
//  					|| txt_no_of_female_teaching_staff == ""
//  					|| txt_no_of_male_supporting_staff == ""
//  					|| txt_no_of_female_supporting_staff == "")
//  				{
//  				  alert("All fields are mendatory");
//  				}
//  			else{
 			$.ajax({
 				
 				url : 'webapi/campus/savecampus',
 				type:'POST',
 				data : {
 					school_id : school_id,
 					updated_by : updated_by,
 					campus_size : campus_size,
 					area_unit : area_unit,
 					no_of_building : no_of_building,
 					no_of_playground : no_of_playgrounds,
 					no_of_total_student : no_of_total_student,
 					no_of_student_boys : txt_no_of_student_boys,
 					no_of_student_girls : txt_no_of_student_girls,
 					no_of_male_teaching_staff : txt_no_of_male_teaching_staff,
 					no_of_female_teaching_staff : txt_no_of_female_teaching_staff,
 					no_of_male_supporting_staff : txt_no_of_male_supporting_staff,
 					no_of_female_supporting_staff  : txt_no_of_female_supporting_staff,
 					strReason : strReason
 				},
 				success : function(data)
 				{
 						
 					if(data.status == 1){
 						alert(data.message);	
 						updateProgress($('#school_id').val());
 						$("#updateCampusReason").val("");
//  						$('#txt_name').val("");
//  			 			$('#area_unit').val("");
//  			 			$('#txt_no_of_bldng').val("");
//  			 			$('#txt_no_of_playground').val("");
//  			 			$('#txt_no_of_student').val("");
//  			 			$('#txt_no_of_student_boys').val("");
//  			 			$('#txt_no_of_student_girls').val("");
//  			 			$('#txt_no_of_male_teaching_staff').val("");
//  			 			$('#txt_no_of_female_teaching_staff').val("");
//  			 			$('#txt_no_of_male_supporting_staff').val("");
//  			 			$('#txt_no_of_female_supporting_staff').val("");
 					}
 					else
 						alert(data.message);
 					
 			 			
 						
 				},
 				error : function(data)
 				{
 					alert("Fail to save campus detail");
 				}
 			
 			});
 			
 		}
 		$("#updatecampusdetail").click(function(){
 			if($("#updateCampusReason").val() != ""){
 				updateCampus($("#updateCampusReason").val());
 				$("#updateCampusDetail").modal('hide');
 			}else{
 				alert("Please enter the reason for update");
 			}
 			
 		});
    
</script>            
                            
