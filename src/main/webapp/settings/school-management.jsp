<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.SchoolType"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
SettingsImpl settings = new SettingsImpl();
List<SchoolType> schoolType = settings.getSchoolType();
int country_size = schoolType.size(); 
%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">School Management</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="school-management-list">
                     <h2>List School Management</h2>
                     <p>Here you can add or deactivate school management.</p>
                     <a href="#" class="btn btn-primary view-school-management bottom-margin"><i class="fa fa-plus"></i> School Management</a>
                     <table class="table table-striped table-bordered" id="school-management-table">
                         <thead>
                             <tr>
                                 <th>ID</th>
                                 <th>Name</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
								
								for(int i=0; i < country_size; i++){ %>
								 <tr>
								    <td> <%out.print((Short)schoolType.get(i).getId());%>   </td>
									<td> <%out.print(schoolType.get(i).getName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editSchoolType(<% out.print((Short)schoolType.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-school-management bottom-margin"><i class="fa fa-plus"></i> School Management</a>
                 </div>
                 <div class="school-management-new" style="display:none;">
                 	<h4>Add New School Management</h4>
                    <div id="error" class="has-error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Name</label>
                         <div class="col-sm-2">
                             <input type="text" id="name" name="name" class="form-control" placeholder="Government.">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the name of the school management. 
                                 </div>
                             </div>
                         </div>
                    </div>

                    <div class="form-group">
                    	<div class="col-sm-2">
                            <button type="button" id='save' class="btn btn-success">Save</button>
                            <button class="btn btn-default list-id list-school-management" type="reset">Cancel</button>
                        </div>
                    </div> 
                    </form>
              	</div>
          	</div>
 	</div>
    <%@include file="../footer.jsp" %>
	  <script type="text/javascript">
	    	$('#save').click(function(){
	    		if($('#name').val()==""){
	    			$("#error").html('Please enter school type');
	    			$('#name').addClass('has-error');
	    		} else {		    		
		    		$.post('../webapi/settings/schtype/save',{name: $("#name").val()},function(data){
		    			if(data.status == 1)
		    				window.location.href = "${baseUrl}/settings/school-management.jsp";
		    			else
		    				$('#error').html(data.message)	;
		    		},'json');
	    		}
	    		
	    	});
			function editSchoolType(id){
				window.location.href = "${baseUrl}/settings/editschool-management.jsp?id="+id;
			}
  	</script>