<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="java.util.List"%>
<%
   
 // List<School> schoolRejectList = new SchoolDAOImp().getSchoolRejectedList(2);
//  if(schoolRejectList.size()>0)
 // {
	  
%>
 <table class="table table-striped table-bordered"
		id="reject-tab-table">
		<thead>
			<tr>
				<th>School Name</th>
				<th>Address</th>
				<th class="alignRight">Actions</th>
			</tr>
		</thead>
<!-- 		<tbody> -->
<%-- 			<%for(int i=0;i<schoolRejectList.size();i++){%> --%>
<!-- 			<tr> -->
<!-- 				<td> -->
<%-- 					<% out.print(schoolRejectList.get(i).getName()); %> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<% out.print(schoolRejectList.get(i).getAlias()+","+schoolRejectList.get(i).getPlotNo() 
// 							+","+schoolRejectList.get(i).getStreetName()
// 							+","+schoolRejectList.get(i).getLocality().getName()
// 							+","+schoolRejectList.get(i).getLocality().getCity().getName()
 							); %> --%>
<!-- 				</td> -->
				
<!-- 				<td class="alignRight"><a -->
<!-- 					href="" -->
<!-- 					class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a> <a -->
<!-- 					href="" -->
<!-- 					class="btn btn-danger icon-btn"><i class="fa fa-trash"></i></a></td> -->
<!-- 			</tr> -->
<%-- 			<%} --%>
<%-- 			}%> --%>
<!-- 		</tbody> -->
	</table>
	
	<script type="text/javascript">
var cityId = $("#change_city").val();
$.get("webapi/school/rejected/"+cityId,{},function(data){
	var oTable = $("#reject-tab-table").dataTable();
	    oTable.fnClearTable();
	    
	    for(var key in data) {
	        console.log(data[key].name);
	        html = "<a href='' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"
             +" <a href='' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>";
	    	var row = [];
	    	 row.push(data[key].name);
	    	 row.push(data[key].alias+","+data[key].plotNo+","+data[key].streetName+","+data[key].locality.name
	    			 +","+data[key].locality.city.name+","+data[key].pincode);
		    	 row.push(html);
	    	oTable.fnAddData(row);
	   } 
});
</script>