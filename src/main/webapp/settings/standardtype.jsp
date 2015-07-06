<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.StandardType"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
SettingsImpl settings = new SettingsImpl();
List<StandardType> standardType = settings.getStandardType();
int stdtypesize = standardType.size(); 
%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">School Type</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List Standard Type</h2>
                     <p>Here you can add or deactivate standard type.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Standard Type</a>
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
								
								for(int i=0; i < stdtypesize; i++){ %>
								 <tr>
								    <td> <%out.print(standardType.get(i).getId());%>   </td>
									<td> <%out.print(standardType.get(i).getName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editStandardType(<% out.print(standardType.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Standard Type</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Standard Type</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Name</label>
                         <div class="col-sm-2">
                             <input type="text" id="name" name="name" class="form-control" placeholder="X Standard">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the name of the standard type. 
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
		    		$('#error').html('Please enter standard type')	;
		    		$('#name').addClass('has-error');
    		}else{
	    		$.post('../webapi/settings/stdtype/save',{name: $("#name").val()},function(data){
	    			if(data.status == 1)
	    				window.location.href = "${baseUrl}/settings/standardtype.jsp";
	    			else
	    				$('#error').html(data.message)	;
	    			},'json');
	    		
	    	
	    	}
	    	});
			function editStandardType(id){
				window.location.href = "${baseUrl}/settings/editstandardtype.jsp?id="+id;
			}
  	</script>