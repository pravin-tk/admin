<%@page import="org.school.admin.model.StreamType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="org.school.admin.model.BoardType"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
SettingsImpl settings = new SettingsImpl();
List<StreamType> streamtypes = settings.getAllStreamType();
%>               
 	<div class="col-sm-12 col-md-12  main">
     
    	<ol class="breadcrumb">
         	<li><a href="#">Settings</a>
         	</li>
         	<li><a href="#">Stream Type</a>
         	</li>
        	<li class="active">Add</li>
     	</ol> 

        <div id="myTabContent" class="tab-content">
                 <div class="contacts-list">
                     <h2>List Stream Type</h2>
                     <p>Here you can add or deactivate Stream Types.</p>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Stream Type</a>
                     <table class="table table-striped table-bordered" id="contacts-table">
                         <thead>
                             <tr>
                                 <th>ID</th>
                                 <th>Title</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                         </thead>
                         <tbody>
                        	<% 					
								int country_size = streamtypes.size(); 
								for(int i=0; i < country_size; i++){ %>
								 <tr>
								    <td> <%out.print(streamtypes.get(i).getId());%>   </td>
									<td> <%out.print(streamtypes.get(i).getTitle());%>    </td>
								  	<td class="alignRight">
                  						<a href="javascript:editStreamType(<% out.print(streamtypes.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                 	</td>
                                 </tr>

                          	<%} %>
                         </tbody>
                     </table>
                     <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Stream Type</a>
                 </div>
                 <div class="contacts-new" style="display:none;">
                 	<h4>Add New Stream Type</h4>
                    <div id="error"></div>
					<form method="post" action="" class="form-horizontal" id="submitForm">
                    <div class="form-group">
                         <label class="col-sm-2 control-label">Title</label>
                         <div class="col-sm-2">
                             <input type="text" id="stream-title" name="stream-title" class="form-control" placeholder="Science">
                         </div>
                         <div class="col-sm-8">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the name of the Stream Type.Eg- Science,Arts. 
                                 </div>
                             </div>
                         </div>
                    </div>
					<div class="form-group">
                       <label class="col-sm-2 control-label">Description</label>
                         <div class="col-sm-6">
                              <textarea class="form-control" name="stream-description" id="stream-description"></textarea>                         </div>
                         <div class="col-sm-4">
                             <div class="tooltip custom-tool-tip right">
                                 <div class="tooltip-arrow"></div>
                                 <div class="tooltip-inner">
                                     This is the description of the Stream Type. 
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
	    		$.post('../webapi/settings/streamtype/save',{title : $("#stream-title").val(),description : $("#stream-description").val()},function(data){
	    			if(data.status == 1)
	    				window.location.href = "${baseUrl}/settings/streamtype.jsp";
	    			else
	    				alert(data.message);
	    		},'json');
	    		
	    	});
			function editStreamType(id){
				window.location.href = "${baseUrl}/settings/editstreamtype.jsp?id="+id;
			}
  	</script>