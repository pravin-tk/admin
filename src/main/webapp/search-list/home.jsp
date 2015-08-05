<%@page import="org.school.admin.data.ViewContactData"%>
<%@page import="org.school.admin.model.ViewContact"%>
<%@page import="org.school.admin.data.ViewSchoolData"%>
<%@page import="org.school.admin.model.ViewSchool"%>
<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%
System.out.println("SessionData : "+session.getAttribute("cityid"));
	SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
	List<School> school_list = null;
	if (request.getParameterMap().containsKey("school_id")) {
		if(request.getParameter("school_id") != null){
			if(Integer.parseInt(request.getParameter("school_id")) > 0)
			school_list = schoolDAOImp.getSchoolList(Integer.parseInt(request.getParameter("school_id")), (Integer)session.getAttribute("cityid"), 0);
			//System.out.println(school_list.toString());
		}
	}
	List<ViewSchoolData> viewSchoolList = new SchoolDAOImp().getViewSchoolList((Integer)session.getAttribute("cityid"));
	
	List<ViewContactData> viewContactList = new SchoolDAOImp().getViewContactList((Integer)session.getAttribute("cityid"));
	for(int i=0;i<viewContactList.size();i++)
	{
	System.out.println("ContactDetailsList : "+viewContactList.get(i).getContactDetail());
		out.print("<script>"
				+"console.log('"+viewContactList.get(i).getContactDetail()+"');"
				+"</script>");
 
	}
	System.out.print("ViewSize : "+viewContactList.size());
%>
            <!-- Right main content -->
                   <form class="form-horizontal" action="" method="">
                       <div class="form-group">
                             <div class="col-sm-4">
                                   <label for="Schooly">Search by School Name or School Id</label>
                                    <% if(viewSchoolList.size()>0){%>
                                   <select id="school_id" name="sendImages">
                                    <option value="0">Enter School Name or School Id</option>
                                  <% for(int i=0;i<viewSchoolList.size();i++) {
                                         ViewSchoolData viewSchoolData = viewSchoolList.get(i);%>
                                       <option value="<%out.print(viewSchoolData.getSchoolId()); %>"><%out.print(viewSchoolData.getSchoolId()); %>, <%out.print(viewSchoolData.getSchoolFullName());%></option>
                                      <%} %>
                                   </select>
                                   <%} else{
                                	    out.println("<br>No school to search");
                                   }%>
                           </div>
                           <div class="col-sm-2">
                            <div class="col-lg-8" >
                             <span style= "padding-top:5px;align:center;" >OR</span></div>
                             
                           </div>
                           <div class="col-sm-4">
                                   <label for="Schooly">Search by POC Name or POC Contact Number</label>
                                    <% 
                                    System.out.println("ContactListSize : "+viewContactList.size());
                                    if(viewContactList.size()>0) {%>
		                                   <select id="contact_id" name="sendImages">
		                                        <option value="0">Enter POC Name or POC Contact Number</option> 
		                                  <% for(int i=0;i<viewContactList.size();i++) {
		                                	  ViewContactData viewContact = viewContactList.get(i); %>
		                                    		<option value="<%out.print(viewContact.getContactNumber()); %>"><%out.print(viewContact.getContactDetail());%></option>
		                                   <%} %>
		                                   </select>
                                   <%}else{
                                        out.println("<br>No conatct name or number to search");
                                   }
                                	   %>
                                   
                           </div>
                       </div>
                </form>                        
                <!--Commission tab starts-->
                <div class="tab-pane fade in active" id="commission" aria-labelledby="commission-tab">
                    <div class="commission-list">
<!--                         <a class="btn btn-primary addschool bottom-margin" id="addschool"><i class="fa fa-plus"></i> School</a> -->

                        <table class="table table-striped table-bordered" id="commission-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Locality</th>
                                    <th>Street</th>
                                    <th>Lat/Long</th>
                                    <th>Pincode</th>
                                    <th>Establishment</th>
                                    <th>Status</th>
                                    <th class="alignRight">Actions</th>
                                </tr>
                            </thead>
                            <tbody id="school_list">
                           	<% if(school_list != null){
                               for(int i=0; i < school_list.size(); i++){
                               	%>
                               	<tr>
                                   <td><% out.print(school_list.get(i).getName()); %></td>
                                   <td><% out.print(school_list.get(i).getLocality().getName()); %></td>
                                   <td><% out.print(school_list.get(i).getStreetName()); %></td>
                                   <td><% out.print(school_list.get(i).getLatitude()+","+school_list.get(i).getLongitude()); %></td>
                                   <td><% out.print(school_list.get(i).getPincode()); %></td>
                                   <td><% if(school_list.get(i).isEstablishmentType() == true) { out.print("New"); } else { out.print("Old"); } %></td>
                                   <td><% if(school_list.get(i).getStatus() == 1) { out.print("<span class='label label-success'>Active</span>"); } else { out.print("<span class='label label-warning'>Inactive</span>"); } %></td>
                                   <td class="alignRight">
                                   		<a href='javascript:editSchool(<% out.print(school_list.get(i).getId()); %>);' class='btn btn-success icon-btn new-commission'><i class='fa fa-pencil'></i></a>
			    						<a href='javascript:updateConfig(<% out.print(school_list.get(i).getId()); %>);' class='btn btn-success icon-btn new-commission'><i class='fa fa-cog'></i></a>
<%-- 			    						<% if(school_list.get(i).getPromote() == 0){%> --%>
<%-- 			    						<a href='javascript:updatePromote(<% out.print(school_list.get(i).getId()); %>,<% out.print(school_list.get(i).getPromote()); %>);' class='btn btn-success icon-btn new-commission'><i class='fa fa-star-o'></i></a> --%>
<%-- 			    						<%}else{ %> --%>
<%-- 			    						<a href='javascript:depromote(<% out.print(school_list.get(i).getId()); %>,<% out.print(school_list.get(i).getPromote()); %>);' class='btn btn-success icon-btn new-commission'><i class='fa fa-star'></i></a> --%>
<%-- 			    						<%} %> --%>
                                   </td>
                               	</tr>
                               	<% } 
                               	}%>
                            </tbody>
                        </table>

                        <a class="btn btn-primary addschool" id="addschool"><i class="fa fa-plus"></i> School</a>
                    </div>
                         
            </div>
    
    
    
    
    <!-- /Right main content -->
    <link rel="stylesheet" type="text/css" href="${baseUrl}/css/selectize.css" />
<script type="text/javascript" src="${baseUrl}/js/selectize.min.js"></script>
    
    <script type="text/javascript" >
    
    $select_school = $('#school_id').selectize({
    	
		persist: false,
		create: function(input) {
		   return {
		       value: input,
		       text: input
		   }
		},
		onChange: function(value) {		    	
	         searchSchool();
	     },
		  onItemRemove : function(value) {
					  }
});
 select_school  = $select_school[0].selectize;
	$select_contact = $('#contact_id').selectize({
	
	persist: false,
	create: function(input) {
	   return {
	       value: input,
	       text: input
	   }
	},
	onChange: function(value) {		    	
            searchContact();
     },
	  onItemRemove : function(value) {
				  }
});
 select_contact  = $select_contact[0].selectize;
    
    
    
    
    $('.addschool').click(function (){
    	window.location.href="addschool.jsp";
    });
    function searchSchool()
    {
    	var school_id = $("#school_id").val() > 0?$("#school_id").val():0;
    	
    	if( school_id > 0){
	    	
	    	$.get("webapi/school/htmllist/"+school_id+"/2/0",function(data){
			  	$("#school_list").html(data);
			    var oTable = $("#commission-table").dataTable();
			    oTable.fnClearTable();
			
			    
			    $(data).each(function(index){
			    	var row = [];
			    	row.push(data[index].name);
			    	row.push(data[index].locality.name);
			    	row.push(data[index].streetName);
			    	row.push(data[index].latitude+","+data[index].longitude);
			    	row.push(data[index].pincode);
			    	if(data[index].establishmentType == true)
			    		row.push("New");
			    	else
			    		row.push("Old");
			    	if(data[index].status == 1)
			    		row.push("<span class='label label-success'>Active</span>");
			    	else
			    		row.push("<span class='label label-warning'>Inactive</span>");
			    	if(data[index].promote == 0){
			    	row.push(
			    			"<a href='javascript:editSchool("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-pencil'></i></a> "
			    			+"<a href='javascript:updateConfig("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-cog'></i></a> "
			    			+"<a href='javascript:updatePromote("+data[index].id+","+data[index].promote+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-star-o'></i></a>"
			    			);
			    	}
			    	else{
			    		row.push(
				    			"<a href='javascript:editSchool("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-pencil'></i></a> "
				    			+"<a href='javascript:updateConfig("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-cog'></i></a> "
				    			+"<a href='javascript:depromote("+data[index].id+","+data[index].promote+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-star'></i></a>"
				    			);
			    		
			    	}
			    	oTable.fnAddData(row);
			    });
	    	});
    	}
    }
    
    function searchContact()
    {
    	var contact_id =  $('#contact_id').val() > 0? $('#contact_id').val():0;
    	var city_id = $('#change_city').val();
    	//alert("City Id  : "+city_id);
    	if(contact_id > 0){
    	   		$.get("webapi/school/schoollist/"+contact_id+"/"+city_id,function(data){
			  	$("#school_list").html(data);
			    var oTable = $("#commission-table").dataTable();
			    oTable.fnClearTable();
			
			    
			    $(data).each(function(index){
			    	var row = [];
			    	row.push(data[index].name);
			    	row.push(data[index].locality.name);
			    	row.push(data[index].streetName);
			    	row.push(data[index].latitude+","+data[index].longitude);
			    	row.push(data[index].pincode);
			    	if(data[index].establishmentType == true)
			    		row.push("New");
			    	else
			    		row.push("Old");
			    	if(data[index].status == 1)
			    		row.push("<span class='label label-success'>Active</span>");
			    	else
			    		row.push("<span class='label label-warning'>Inactive</span>");
			    	if(data[index].promote == 0){
			    	row.push(
			    			"<a href='javascript:editSchool("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-pencil'></i></a> "
			    			+"<a href='javascript:updateConfig("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-cog'></i></a> "
			    			+"<a href='javascript:updatePromote("+data[index].id+","+data[index].promote+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-star-o'></i></a>"
			    			);
			    	}
			    	else{
			    		row.push(
				    			"<a href='javascript:editSchool("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-pencil'></i></a> "
				    			+"<a href='javascript:updateConfig("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-cog'></i></a> "
				    			+"<a href='javascript:depromote("+data[index].id+","+data[index].promote+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-star'></i></a>"
				    			);
			    		
			    	}
			    	oTable.fnAddData(row);
			    });
	    	});
    	}
    	
    }
   
  function  editSchool(school_id)
  {
	  window.location.href = "editschool.jsp?school_id="+school_id;
  }
  function updateConfig(school_id)
  {
	  window.location.href="schoolconfig.jsp?school_id="+school_id;
  }
  function updatePromote(schoolId,promote)
  {
	 var ok = confirm("Do you want to Promoto ?");
	 if(ok){
		$.post("webapi/school/updatepromote",{schoolId : schoolId,promote : promote},function(data)
	  	{
	  			searchSchool();
	  	});
 	 } 
  }
 function depromote(schoolId,promote)
 {
	 var ok = confirm("Do you want to Depromoto ?");
	 if(ok){
			$.post("webapi/school/updatepromote",{schoolId : schoolId,promote : promote},function(data)
		  	{
		  			searchSchool();
		  	});
		 } 
   }
 


 
    </script>
</body>

</html>
