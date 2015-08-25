<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="java.util.List"%>

<%
if(session !=null){
	if(session.getAttribute("cityid") == null){
		StringBuffer header_url = request.getRequestURL();
		if(response.containsHeader(header_url.toString())){
			response.setHeader(header_url.toString(), header_url.toString()+"/index.jsp");
		}
	}else{
		
	
List<School> schoolPendingList = new SchoolDAOImp().getSchoolPendingList((Integer)session.getAttribute("cityid"));

   if(schoolPendingList.size()>0)
   {
%>
 <table class="table table-striped table-bordered"
		id="pending-tab-table">
		<thead>
			<tr>
				<th>School Name</th>
				<th>Address</th>
				<th class="alignRight">Actions</th>
			</tr>
		</thead>
		<tbody>
			<%for(int i=0;i<schoolPendingList.size();i++){%>
			<tr>
				<td>
					<% out.print(schoolPendingList.get(i).getName()); %>
				</td>
				<td>
					<% out.print(schoolPendingList.get(i).getAlias()+","+schoolPendingList.get(i).getPlotNo() 
							+","+schoolPendingList.get(i).getStreetName()
							+","+schoolPendingList.get(i).getLocality().getName()
							+","+schoolPendingList.get(i).getLocality().getCity().getName()); 
 					 %> 
				</td>
				
				<td class="alignRight"><a
					href=""
					class="btn btn-success icon-btn"><i class="fa fa-font"></i></a> <a
					href=""
					class="btn btn-danger icon-btn"><i class="fa fa-star-half-o"></i></a> <a
					href=""
					class="btn btn-danger icon-btn"><i class="fa fa-ban"></i></a></td>
			</tr>
			<%}
 			}
 			}
 			}%> 
		</tbody>
	</table>
	
<!-- <!-- 	<script type="text/javascript"> --> 
<!-- // var cityId = $("#change_city").val(); -->
<!-- // $.get("webapi/school/pending/"+cityId,{},function(data){ -->
<!-- // 	var oTable = $("#pending-tab-table").dataTable(); -->
<!-- // 	    oTable.fnClearTable(); -->
	    
<!-- // 	    for(var key in data) { -->
<!-- // 	        console.log(data[key].name); -->
<!-- // 	        html = "<a href='' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>" -->
<!-- //              +" <a href='' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>"; -->
<!-- // 	    	var row = []; -->
<!-- // 	    	 row.push(data[key].name); -->
<!-- // 	    	 row.push(data[key].alias+","+data[key].plotNo+","+data[key].streetName+","+data[key].locality.name -->
<!-- // 	    			 +","+data[key].locality.city.name+","+data[key].pincode); -->
<!-- // 		    	 row.push(html); -->
<!-- // 	    	oTable.fnAddData(row); -->
<!-- // 	   }  -->
<!-- // }); -->
<!-- <!-- </script> --> 
