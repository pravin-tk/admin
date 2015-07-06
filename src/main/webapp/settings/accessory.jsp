<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.Accessories"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
SettingsImpl settings = new SettingsImpl();
List<Accessories> accessories = settings.getAccessories();
%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">Accessories</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List Accessories</h2>
                     <p>Here you can add or deactivate accessories.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Accessory</a>
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
								int country_size = accessories.size(); 
								for(int i=0; i < country_size; i++){ %>
								 <tr>
								    <td> <%out.print(accessories.get(i).getId());%>   </td>
									<td> <%out.print(accessories.get(i).getName());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editAccessory(<% out.print(accessories.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Accessory</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Accessory</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Name</label>
                         <div class="col-sm-2">
                             <input type="text" id="name" name="name" class="form-control" placeholder="Books">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the name of the Accessory. 
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
	    		$.post('../webapi/settings/accessory/save',{name: $("#name").val()},function(data){
	    			if(data.status == 1)
	    				window.location.href = "${baseUrl}/settings/accessory.jsp";
	    			else
	    				alert(data.message);
	    		},'json');
	    		
	    	});
			function editAccessory(id){
				window.location.href = "${baseUrl}/settings/editaccessory.jsp?id="+id;
			}
  	</script>