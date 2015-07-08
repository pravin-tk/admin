<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="java.util.List"%>
<%
	List<City> city_list = new CityNamesService().getAllCityNames();
	System.out.println(city_list.toString());
%>
	<%@ include file="header.jsp" %>
	<%@ include file="LeftNav.jsp" %>            
	<!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <h1 class="page-header">School</h1>
                <ol class="breadcrumb">
                    <li>
                        <a href="#">school</a>
                    </li>
                    <li class="active">add</li>
                </ol>
                <!-- <h2 class="sub-header">Section title</h2> -->
                <form method="post" action=""  class="form-horizontal" id="schoolAddForm">
					
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#basic" data-toggle="tab">Basic</a>
                        </li>
                        
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="basic" aria-labelledby="basic-tab">
                            <h2>Basic Information</h2>
                            <div class="alert alert-danger" role="alert" style="display:none;" id="message_body">
							  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							  	<span class="sr-only">Error:</span>
							  	<span id="output"></span>
							</div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="School Name">Name</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="school_name" id="school_name" placeholder="School Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Plot No.">Plot No.</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="plot_no" id="plot_no" placeholder="Plot No.">
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
                                        	out.print("<option value='"+city_list.get(i).getId()+"'>"+city_list.get(i).getName()+"</option>");
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
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Street Name">Street Name</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="street_name" id="street_name" placeholder="Street Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Landmark">Landmark</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="landmark" id="landmark" placeholder="Landmark">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Pincode">Pincode</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="pincode" id="pincode" placeholder="Pincode" maxlength="6">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Latitude">Latitude</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="latitude" id="latitude" placeholder="Latitude">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Longitude">Longitude</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="longitude" id="longitude" placeholder="Longitude">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Alias">Alias Name</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="alias" id="alias" placeholder="Alias Name of school">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tag Line">Tag Line</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" name="tag_line" id="tag_line" placeholder="Tag Line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="About School">About School</label>
                                <div class="col-sm-6">
                                    <textarea class="form-control" rows="4" name="about_school" id="about_school" placeholder="About School"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Establishment">Establishment</label>
                                <div class="col-sm-6">
                                	<select name="establishment_type" id="establishment_type" class="form-control">
                                        <option value="0">Old</option>
                                        <option value="1">New</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                    <label class="col-sm-2 control-label">Is Free Listing</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="isFreeListing" id="isFreeListing" value="1" onClick="openTrialDate();">Yes
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isFreeListing" id="isFreeListing" value="0" checked="checked" onClick="closeTrialDate();">No
                                        </label>
                                    </div>
                            </div>
                            <div class="form-group" id="trial_s_date" style="display:none;">
								<label class="col-sm-2 control-label">Trial Start Date</label>
								<div class="col-sm-6">
									<input type="date" class="form-control"
										placeholder="trial start date" name="trialStartDate" id="trialStartDate">
										
									
								</div>
							</div>
							
<!-- 								<input type="text" class="time ui-timepicker-input" id="timeformatExample1" autocomplete="off"> -->
							<div class="form-group" id="trial_e_date" style="display:none;">
								<label class="col-sm-2 control-label">Trial End Date</label>
								<div class="col-sm-6">
									<input type="date" class="form-control"
										placeholder="trial end date" name="trialEndDate" id="trialEndDate">
								</div>
							</div>
                            <input type="hidden" value="<%out.print(registration.getId()); %>" name="registration_id"/>
                            
                            <div class="form-group">
                                <div class="col-sm-2 col-sm-offset-2">
                                    <input type="submit" class="btn btn-success" value="Save">
                                </div>
                            </div>
                        </div>



                        <div class="tab-pane fade" id="contact" aria-labelledby="contact-tab">
                            <h2>Contact Information</h2>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom">Time Zone</label>
                                <div class="col-sm-4">
                                    <select name="" id="" class="form-control">
                                        
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2 col-sm-offset-2">
                                    <button type="submit" class="btn btn-success">Save</button>
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
    <script type="text/javascript">
    	$("#trialStartDate").datepicker({
   		 format: 'yyyy-mm-dd',
		    startDate: '2010-01-01'
	});
    	$("#trialEndDate").datepicker({
      		 format: 'yyyy-mm-dd',
   		    startDate: '2010-01-01'
   	});
    	//$('#timeformatExample1').timepicker({ 'timeFormat': 'H:i:s' });
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
    
    $(document).ready(function() { 
        var options = { 
            target:        '#output',   // target element(s) to be updated with server response 
            beforeSubmit:  showRequest,  // pre-submit callback 
            success:       showResponse,
            url: '${baseUrl}/webapi/school/addschool',
     		dataType: 'json'
        }; 
         $("#trialStartDate").datepicker('setDate',new Date());
         $("#trialEndDate").datepicker('setDate',new Date(new Date().setMonth(new Date().getMonth()+6)));
       
         
         console.log("New date  : "+ $("#trialEndDate").val());
         
       //  alert("Trial end date : "+$("#trialEndDate").val());
        $('#schoolAddForm').ajaxForm(options); 
    }); 
     
    // pre-submit callback 
    function showRequest(formData, jqForm, options) { 
        var queryString = $.param(formData); 
        $('#message_body').hide();
        return true; 
    } 
     
    // post-submit callback 
    function showResponse(responseText, statusText, xhr, $form)  { 
    	if(responseText.status == 0){
   			$('#message_body').show();
       		$('#output').html(responseText.message)
    	}else{
    		$('#message_body').hide();
    		window.location.href = "home.jsp?school_id="+responseText.status;
    	}
    } 
    
    function openTrialDate(){
    	$("#trial_s_date").show();
    	$("#trial_e_date").show();
    }
    
    function closeTrialDate(){
    	$("#trial_s_date").hide();
    	$("#trial_e_date").hide();
    }
    </script>