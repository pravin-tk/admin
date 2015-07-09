<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.SchoolClassificationType"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
SettingsImpl settings = new SettingsImpl();
List<SchoolClassificationType> schoolclassification = settings.getAllSchoolClassificationType();
%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">School Classification Type</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List School Classification Type</h2>
                     <p>Here you can add school category type.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> School Classification Type</a>
                     <table class="table table-striped table-bordered" id="contacts-table">
                         <thead>
                             <tr>
                                 <th>ID</th>
                                 <th>Name</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
								int country_size = schoolclassification.size(); 
								for(int i=0; i < country_size; i++){ %>
								 <tr>
								    <td> <%out.print(schoolclassification.get(i).getId());%>   </td>
									<td> <%out.print(schoolclassification.get(i).getName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editSchoolClassification(<% out.print(schoolclassification.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> School Classification Type</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New School Classification Type</h4>
                    <div id="error-school-classification"></div>
					<form method="post" action="" class="form-horizontal" id="schoolClassificationform">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Name</label>
                         <div class="col-sm-6">
                             <input type="text" id="name" name="name" class="form-control" placeholder="">
                         </div>
                         <div class="col-sm-4">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     School Classification Type. 
                                 </div>
                             </div>
                         </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Image</label>
                         <div class="col-sm-6">
                             <input type="file" id="image" name="image" class="form-control" placeholder="Select image">
                         </div>
                         <div class="col-sm-4">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                    Select School Classification Type image. 
                                 </div>
                             </div>
                         </div>
                    </div>

                    <div class="form-group">
                    	<div class="col-sm-4">
                            <button type="button" id='save' class="btn btn-success">Save</button>
                            <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                        </div>
                    </div> 
                    </form>
              	</div>
          	</div>
 	</div>
    <%@include file="../footer.jsp" %>
    <script src="${baseUrl}/js/jquery.form.js"></script>
	  <script type="text/javascript">
	    	$('#save').click(function(){   
	    		
	    		var options = {
	    				target : '#error-contact-detail', // target element(s) to be updated with server response 
	    				beforeSubmit : showSchoolClassificationRequest, // pre-submit callback 
	    				success :  showClassificationResponse,
	    				url : '../webapi/settings/schoolclassificationtype/save',
	    				semantic : true,
	    				dataType : 'json'
	    			};
	    			//console.log("test");
	    			//$('#schoolClassificationform').ajaxForm(options);
	    			$('#schoolClassificationform').ajaxSubmit(options);
		    		
	    		
// 	    		$.post('../webapi/settings/schoolclassificationtype/save',{name: $("#name").val()},function(data){
// 	    			if(data.status == 1)
// 	    				window.location.href = "${baseUrl}/settings/schoolclassificationtype.jsp";
// 	    			else
// 	    				alert(data.message);
// 	    		},'json');
	    		
	    	});
	    	
	    	// pre-submit callback 
	    	function showSchoolClassificationRequest(formData, jqForm, options) {
	    		var queryString = $.param(formData);
	    		$('#error-school-classification').hide();
	    		//console.log("193");
	    		return true;
	    	}
	    	
			function editSchoolClassification(id){
				window.location.href = "${baseUrl}/settings/editschoolclassificationtype.jsp?id="+id;
			}
			
			function showClassificationResponse(responseText, statusText, xhr, $form) {
				if(responseText.status == 1){
    		   		alert(responseText.message);
    		   		window.location.href = "${baseUrl}/settings/schoolclassificationtype.jsp";
    			}
    			else{
    			 alert(responseText.message);
    			}
    			console.log("Response : "+responseText.message);	
			}
  	</script>