
<%@page import="org.school.admin.dao.SchoolDetailDAOImpl"%>
<%@page import="org.school.admin.service.BoardService"%>
<%@page import="org.school.admin.model.BoardType"%>
<%@page import="org.school.admin.model.SchoolBoard"%>
<%@page import="org.school.admin.model.Locality"%>
<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.service.LocalityService"%>
<%@page import="org.school.admin.dao.CityNamesImp"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.Calendar" %>

<%@page import="org.school.admin.service.SchoolService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.school.admin.model.School" %>

<%@ include file="header.jsp" %>
	<%@ include file="LeftNav.jsp" %>  

<%
	int school_id = Integer.parseInt(request.getParameter("school_id"));


	List<SchoolBoard> schoolBoard = null;
	schoolBoard =  new SchoolDetailDAOImpl().getSchoolBoard(school_id);


	List<BoardType> boardTypeList = null;
	boardTypeList = new BoardService().getBoardList();
	SchoolService schoolService = new SchoolService();
	List<School> schoolList = schoolService.getSchoolDetails(school_id);
	School School = schoolList.get(0);
	CityNamesService cityService = new CityNamesService();
	List<City> city_list = cityService.getAllCityNames();
	LocalityService localityService = new LocalityService();
	List<Locality> locality_list = localityService.getLocalityName(School.getLocality().getCity().getId());
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date trial_StartDate = School.getTrialStartDate();
	Date trial_EndDate = School.getTrialEndDate();
	String trialStartDate = null,trialEndDate = null;
	if(trial_StartDate != null)
	trialStartDate = format.format(trial_StartDate);
	if(trialStartDate != null)
	trialEndDate = format.format(trial_EndDate);
%>
<div class="col-sm-12 col-md-12  main">
                <h1 class="page-header">School</h1>
                <ol class="breadcrumb">
                    <li>
                        <a href="#">school</a>
                    </li>
                    <li class="active">edit</li>
                </ol>
                <!-- <h2 class="sub-header">Section title</h2> -->
 				<form id="school_update" method="post" action=""  class="form-horizontal">
					
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#basic" data-toggle="tab">Basic</a>
                        </li>
                        
                        
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="basic" aria-labelledby="basic-tab">
                            <h2>Basic Information</h2>
                            <div id="msg" class="danger-box"></div>
                            <input type="hidden" name="school_id" id="school_id" value="<%out.print(School.getId());%>"/>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="School Name">Name</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="school_name" id="school_name" value="<%out.print(School.getName());%>" placeholder="School Name" >
                                </div>
                            </div>
                             <div class="form-group">
                    <label class="col-sm-2 control-label">Board Type *</label>
                    <div class="col-sm-6" id="cbk_board">
                    	<%
                    	 if(boardTypeList.size() > 0){
                       	for(int i=0;i<boardTypeList.size();i++){ 
                    	   	BoardType boardType = boardTypeList.get(i);
                    	   	String checked = "";
                    	   	if(schoolBoard.size() > 0){
	                       		for(int j=0; j < schoolBoard.size(); j++){
	                       			if(boardType.getId() == schoolBoard.get(j).getBoardType().getId()){
	                       				checked = "checked";
	                       			}else{
	                       				checked = "";
	                       			}
	                       		}
                    	   	}
							out.print(" <label class='checkbox-inline'> <input type='radio' value='"+boardType.getId()+
										  "' id='board' name='board' "+checked+"> "+boardType.getBoardName()+"</label>"); 
                       	}
                    	 }
						%>
                      </div>
                </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Plot No.">Plot No.</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="plot_no" id="plot_no" value="<%out.print(School.getPlotNo());%>" placeholder="Plot No.">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">City</label>
                                <div class="col-sm-4">
                                    <select name="city_id" id="city_id" class="form-control">
                                        <option value="">Select City</option>
                                        <%
                                        int city_size = city_list.size(); 
                                        for(int i=0; i < city_size; i++){
                                        	String selected = "";
                                        	if(School.getLocality().getCity().getId() == city_list.get(i).getId())
                                        		selected = "selected";
                                        	out.print("<option value='"+city_list.get(i).getId()+"' "+selected+">"+city_list.get(i).getName()+"</option>");
                                        }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Locality</label>
                                <div class="col-sm-4">
                                    <select name="locality_id" id="locality_id" class="form-control">
                                        <option value="">Select Locality</option>
                                        <%
                                        int locality_size = locality_list.size(); 
                                        for(int i=0; i < locality_size; i++){
                                        	String selected = "";
                                        	if(School.getLocality().getId() == locality_list.get(i).getId())
                                        		selected = "selected";
                                        	out.print("<option value='"+locality_list.get(i).getId()+"' "+selected+">"+locality_list.get(i).getName()+"</option>");
                                        }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Street Name">Street Name</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="street_name" id="street_name" value="<%out.print(School.getStreetName());%>" placeholder="Street Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Landmark">Landmark</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="landmark" id="landmark" value="<%out.print(School.getLandmark());%>" placeholder="Landmark">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Pincode">Pincode</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="pincode" id="pincode" value="<%out.print(School.getPincode());%>" placeholder="Pincode" maxlength="6">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Latitude">Latitude</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="latitude" id="latitude" value="<%out.print(School.getLatitude().toString());%>" placeholder="Latitude">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Longitude">Longitude</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="longitude" id="longitude" value="<%out.print(School.getLongitude().toString());%>" placeholder="Longitude">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Alias">Alias</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="alias" id="alias" value="<%out.print(School.getAlias());%>" placeholder="Alias">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tag Line">Tag Line</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="tag_line" id="tag_line" value="<%out.print(School.getTagLine());%>" placeholder="Tag Line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="About School">About School</label>
                                <div class="col-sm-6">
                                    <textarea class="form-control" rows="4" name="about_school" id="about_school" placeholder="About School"><%out.print(School.getAboutSchool());%></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                    <label class="col-sm-2 control-label">Is Free Listing</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="isFreeListing" id="isFreeListing" value="1" onClick="openTrialDate();" <%if(School.getIsFreelisting() == 1){ %>checked<%} %>>Yes
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isFreeListing" id="isFreeListing" value="0" onClick="closeTrialDate();" <%if(School.getIsFreelisting() == 0){ %>checked<%} %>>No
                                        </label>
                                    </div>
                            </div>
                            <div class="form-group" id="trial_s_date" style="display:<%if(School.getIsFreelisting() == 1){ %>block;<% }else{ %>none;<%}%>">
								<label class="col-sm-2 control-label">Trial Start Date</label>
								<div class="col-sm-6">
									<input type="date" class="form-control"
										placeholder="trial start date" name="trialStartDate" id="trialStartDate" value="<%if(trialStartDate != null) {out.print(trialStartDate);} else {out.print("");}%>">
								</div>
							</div>
							<div class="form-group" id="trial_e_date" style="display:<%if(School.getIsFreelisting() == 1){ %>block;<% }else{ %>none;<%}%>">
								<label class="col-sm-2 control-label">Trial End Date</label>
								<div class="col-sm-6">
									<input type="date" class="form-control"
										placeholder="trial end date" name="trialEndDate" id="trialEndDate" value="<%if(trialEndDate != null){out.print(trialEndDate);} else {out.print("");}%>">
								</div>
							</div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Establishment">Establishment</label>
                                <div class="col-sm-6">
                                	<select name="establishment_type" id="establishment_type" class="form-control">
                                        <option value="0" <% if(School.isEstablishmentType() == false){out.print("selected");}%>>Old</option>
                                        <option value="1" <% if(School.isEstablishmentType() == true){out.print("selected");}%>>New</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" value="<%out.print(registration.getId()); %>" name="registration_id"/>
                            
                            <div class="form-group">
                                <div class="col-sm-2 col-sm-offset-2">
                                    <input type="button" id="update" class="btn btn-success" value="Save">
                                </div>
                            </div>
                        </div>

		   </div>
           </form>
			</div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
    <script src="${baseUrl}/js/jquery.form.js"></script>
<!--         <script src="http://malsup.github.com/jquery.form.js"></script>  -->
<script type="text/javascript">
$("#trialStartDate").datepicker({
	 format: 'yyyy-mm-dd',
	    startDate: '2010-01-01'
});
$("#trialEndDate").datepicker({
		 format: 'yyyy-mm-dd',
	    startDate: '2010-01-01'
});
$('#update').click(function (){
	var options = { 
					url: 'webapi/school/save',
					type: 'POST',
					target: '#msg',
					dataType: 'json',
				    beforeSubmit: validate, 
					success: showResponse 
				  };
	jQuery("#school_update").ajaxSubmit(options); 
	function validate(){
		//validate
	}
	
	function showResponse(responseText, statusText, xhr, $form)  { 
		if(responseText.status == 0){
   			$('#message_body').show();
       		$('#output').html(responseText.message)
    	}else{
    		$('#message_body').hide();
    		window.location.href = "hometab.jsp?school_id="+$("#school_id").val();
    	}
	}
});

$("#city_id").change(function(){
	var city_id = $(this).val();
	if(city_id != 0 || city_id != ""){
		$.get("webapi/general/locality/"+city_id,function(data){
    		var dropdown = "<option value=''>Select Locality</option>";
    		for(var i=0;i<data.length;i++){
    			dropdown += "<option value="+data[i].id+">"+data[i].name+"</option>"; 
    		}
		  	$("#locality_id").html(dropdown);
    	});
	}
});

function openTrialDate(){
	$("#trial_s_date").show();
	$("#trial_e_date").show();
}

function closeTrialDate(){
	$("#trial_s_date").hide();
	$("#trial_e_date").hide();
}
	
</script>