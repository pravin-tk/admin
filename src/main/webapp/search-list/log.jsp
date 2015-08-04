<%@page import="org.school.admin.model.Locality"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.SchoolLog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<SchoolLog> schoolLogList = new SchoolDAOImp().getSchoolLog();
  if(schoolLogList.size()>0)
  {
%>
 <table class="table table-striped table-bordered"
		id="log-tab-table" style="font-size:12px;">
		<thead>
			<tr>
				<th>School Name</th>
				<th>User</th>
				<th>Reason</th>
				<th>log</th>
				<th>date</th>
				<th>time</th>
				<th class="alignRight">Actions</th>
			</tr>
		</thead>
		<tbody>
			<%for(int i=0;i<schoolLogList.size();i++){%>
			<tr>
				<td>
					<%
					out.print(schoolLogList.get(i).getSchool().getName()+", "
							+schoolLogList.get(i).getSchool().getLocality().getName()+", "
							+schoolLogList.get(i).getSchool().getLocality().getCity().getName());
					
					%>
				</td>
				<td>
 					<% out.print(schoolLogList.get(i).getAdminUser().getName()); %> 
				</td>
				<td>
 					<% out.print(schoolLogList.get(i).getReason()); %> 
				</td>
				<td>
 					<% out.print(schoolLogList.get(i).getLog()); %> 
				</td>
				<td>
 					<% out.print(schoolLogList.get(i).getLogDate()); %> 
				</td>
				<td>
 					<% out.print(schoolLogList.get(i).getLogTime()); %> 
				</td>
				<td class="alignRight"><a
					href=""
					class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a> <a
					href=""
					class="btn btn-danger icon-btn"><i class="fa fa-trash"></i></a></td>
			</tr>
			<%}
 			}%> 
		</tbody>
	</table>
</body>
<script type="text/javascript">
$("#showLog").click(function(){
// 	$.get("webapi/school/log",{},function(data){
// 		var oTable = $("#log-tab-table").dataTable();
// 		    oTable.fnClearTable();
		    
// 		    for(var key in data) {
// 		        console.log(data[key].log);
// 		        html = "<a href='' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"
// 	             +" <a href='' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>";
// 		    	var row = [];
// 		    	 row.push(data[key].log);
// //	 	    	 row.push(data[key].alias+","+data[key].plotNo+","+data[key].streetName+","+data[key].locality.name
// //	 	    			 +","+data[key].locality.city.name+","+data[key].pincode);
// 			    	 row.push(html);
// 		    	oTable.fnAddData(row);
// 		   } 
// 	});	
});

</script>
</html>