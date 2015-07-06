<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.Subject"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
SettingsImpl settings = new SettingsImpl();
List<Subject> subject = settings.getSubject();
int subjectsize = subject.size(); 
%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">Subject</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List Subject Type</h2>
                     <p>Here you can add or deactivate subject.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Subject</a>
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
								
								for(int i=0; i < subjectsize; i++){ %>
								 <tr>
								    <td> <%out.print(subject.get(i).getId());%>   </td>
									<td> <%out.print(subject.get(i).getName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editSubject(<% out.print(subject.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Subject</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Subject</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Name</label>
                         <div class="col-sm-2">
                             <input type="text" id="name" name="name" class="form-control" placeholder="English">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the name of the subject. 
                                 </div>
                             </div>
                         </div>
                    </div>

                    <div class="form-group">
                    	<div class="col-sm-2">
                            <button type="button" id='save' class="btn btn-success">Save</button>
                            <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
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
			    		$('#error').html('Please enter subject name')	;
			    		$('#name').addClass('has-error');
	    		}else{
	    			$.post('../webapi/settings/subject/save',{name: $("#name").val()},function(data){
			    			if(data.status == 1)
			    				window.location.href = "${baseUrl}/settings/subject.jsp";
			    			else
			    				$('#error').html(data.message)	;
	    			},'json');
	    		}
	    	});
			function editSubject(id){
				window.location.href = "${baseUrl}/settings/editsubject.jsp?id="+id;
			}
  	</script>