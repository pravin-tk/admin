<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.service.CityNamesService"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%@ include file="header.jsp" %>
<%
	SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
	List<School> school_list = null;
	if (request.getParameterMap().containsKey("school_id")) {
		if(request.getParameter("school_id") != null){
			if(Integer.parseInt(request.getParameter("school_id")) > 0)
			school_list = schoolDAOImp.getSchoolList(Integer.parseInt(request.getParameter("school_id")), 0, 0);
			System.out.println(school_list.toString());
		}
	}
	List<City> city_list = new CityNamesService().getAllCityNames();
	System.out.println(city_list.toString());
%>

	<%@ include file="LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <h1 class="page-header">School List</h1>
                   <form class="form-horizontal" action="" method="">
                       <div class="form-group">
                           <div class="col-sm-3">
                               <div class="col-lg-10">
                                   <label for="comp-city">City</label>
                                   <br>
                                   <select id="city_id" name="sendImages" class="form-control">
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
                           <div class="col-sm-3">
                               <div class="col-lg-10">
                                   <label for="locality">Locality</label>
                                   <br>
                                   <select id="locality_id" name="sendImages" class="form-control">
                                       <option value="">Select Locality</option>
                                       
                                   </select>
                               </div>
                           </div>
                           <div class="col-sm-3">
                               <div class="col-lg-10">
                                   <label for="Schooly">School</label>
                                   <br>
                                   <select id="school_id" name="sendImages" class="form-control">
                                       <option value=""> Select School</option>
                                      
                                   </select>
                               </div>
                           </div>
                            <div class="form-group">
                     
                          	<div class="col-sm-3">
                          	 	<div class="col-lg-12">
                          	 	<br>
                              	<input type="button" value="Search" id="search" class="btn btn-success">
                          	</div>
                      	</div>
                  	</div>
                       </div>
                  
                </form>                        
                <!--Commission tab starts-->
                <div class="tab-pane fade in active" id="commission" aria-labelledby="commission-tab">
                    <div class="commission-list">
                        <a class="btn btn-primary addschool bottom-margin" id="addschool"><i class="fa fa-plus"></i> School</a>

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
                                   </td>
                               	</tr>
                               	<% } 
                               	}%>
                            </tbody>
                        </table>

                        <a class="btn btn-primary addschool" id="addschool"><i class="fa fa-plus"></i> School</a>
                    </div>
                         
            </div>
        </div>
    
    
    
    
    <!-- /Right main content -->
    <%@ include file="footer.jsp" %>
    
     <script type="text/javascript">
    	
    $("#city_id").change(function(){
    	changeCity(this);
    });
    function changeCity(city)
    {
    	var city_id = $(city).val();
    	if(city_id != 0 || city_id != ""){
	    	$.get("webapi/general/locality/"+city_id,function(data){
	    		var dropdown = "<option value=''>Select Locality</option>";
	    		for(var i=0;i<data.length;i++){
	    			dropdown += "<option value="+data[i].id+">"+data[i].name+"</option>"; 
	    		}
			  	$("#locality_id").html(dropdown);
	    	});
	    	$.get("webapi/school/namelist/0/"+city_id+"/0",function(data){
	    		var dropdown = "<option value=''>Select School</option>";
	    		for(var i=0;i<data.length;i++){
	    			dropdown += "<option value="+data[i].school_id+">"+data[i].school_name+"</option>"; 
	    		}
			  		$("#school_id").html(dropdown);
	    	});
    	}
    	else{
    		 var dropdownLocality = "<option value=''>Select Locality</option>";
    		 var dropdownSchool = "<option value=''>Select School</option>";
    		$("#locality_id").html(dropdownLocality);
    		$("#school_id").html(dropdownSchool);
    	}
    }
    $("#locality_id").change(function(){
    	var locality_id = $(this).val();
    	if(locality_id != 0 || locality_id != ""){
	    	
	    	$.get("webapi/school/namelist/0/0/"+locality_id,function(data){
	    		var dropdown = "<option value=''>Select School</option>";
	    		for(var i=0;i<data.length;i++){
	    			dropdown += "<option value="+data[i].school_id+">"+data[i].school_name+"</option>"; 
	    		}
			  	$("#school_id").html(dropdown);
	    	});
    	}
    	else
    	{
    		changeCity(this);	
    	}
    });
    </script>
    <script type="text/javascript" >
    $('.addschool').click(function (){
    	window.location.href="addschool.jsp";
    });
    $("#city_id").keydown(function (e) {
		  if (e.keyCode == 13) {
			  alert("Please select city");
		  }
	});
    function search()
    {
    	var locality_id = $("#locality_id").val() > 0? $("#locality_id").val():0;
    	var city_id = $("#city_id").val() > 0?$("#city_id").val():0;
    	var school_id = $("#school_id").val() > 0?$("#school_id").val():0;
    	if(city_id == 0)
    	{
			alert("Please Select city");    		
    	}
    	
    	if(locality_id > 0 || city_id > 0 || school_id > 0){
	    	
	    	$.get("webapi/school/htmllist/"+school_id+"/"+city_id+"/"+locality_id,function(data){
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
			    	row.push(
			    			"<a href='javascript:editSchool("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-pencil'></i></a> "
			    			+"<a href='javascript:updateConfig("+data[index].id+");' class='btn btn-success icon-btn new-commission'><i class='fa fa-cog'></i></a>"
			    			);
			    	oTable.fnAddData(row);
			    });
	    	});
    	}
    }
    $("#search").click(function(){
    	 search();
    	
    });
  function  editSchool(school_id)
  {
	  window.location.href = "editschool.jsp?school_id="+school_id;
  }
  function updateConfig(school_id)
  {
	  window.location.href="schoolconfig.jsp?school_id="+school_id;
  }
    </script>
</body>

</html>