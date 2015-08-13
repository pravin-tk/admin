<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.AdminUserRole"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.BusInfo"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
 <%
 session = request.getSession(false);
 AdminUser adminuser5 = new AdminUser();
 if(session!=null)
 {
 	if(session.getAttribute("uname") != null)
 	{
 			adminuser5 = (AdminUser)session.getAttribute("uname");
 				System.out.println();
 				System.out.println("user id : "+adminuser5.getId());
 	}
 }
      SchoolDAOImp adminUserRoleDAO = new SchoolDAOImp();
 	  List<AdminUserRole> adminUserRoles =   adminUserRoleDAO.getAdminUserRole();   	
  %>         
 	<div class="col-sm-12 col-md-12  main">
     
    	  <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>Admin User Role</h2>
                     <p>Here you can add admin user role.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>Admin User Role</a>
                     <table class="table table-striped table-bordered" id="contacts-table">
                         <thead>
                             <tr>
                                 <th>Role Id</th>
                                 <th>Admin User Role</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
								int country_size = adminUserRoles.size(); 
								for(int i=0; i < country_size; i++){ %>
								 <tr>
									<td> <%out.print(adminUserRoles.get(i).getId());%>    </td>
									<td> <%out.print(adminUserRoles.get(i).getRoleName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editAdminUserRole(<% out.print(adminUserRoles.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<% } %> 
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i>Admin User Role</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Admin User Role</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Admin User Role</label>
                         <div class="col-sm-2">
                             <input type="text" id="adminuserrole" name="title" class="form-control" placeholder="Sales Exexctive">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the title of Admin User Role. 
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
	  <script type="text/javascript">
	    	$('#save').click(function(){    
	    	
	    		$.post('../webapi/settings/adminuserrole_save',{adminuserrole: $("#adminuserrole").val()},function(data){
	    			if(data.status == 1)
	    				{
	    					window.location.href = "${baseUrl}/settings/adminuserrole.jsp";
	    					$("#adminuserrole").val("");
	    				}
	    			else
	    				alert(data.message);
	    		},'json');
	    		
	    	});
			function editAdminUserRole(id){
				window.location.href = "${baseUrl}/settings/edit-admin-user-role.jsp?id="+id;
			}
  	</script>