<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.model.City"%>
<%@page import="org.school.admin.model.Locality"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="java.util.List"%>
<%	
	List<School> schoolActiveList = new SchoolDAOImp().getSchoolActiveList((Integer)session.getAttribute("cityid"));
	if(schoolActiveList.size()>0){
%>
 <table class="table table-striped table-bordered"
		id="active-tab-table">
		<thead>
			<tr>
				<th>School Name</th>
				<th>Address</th>
				<th class="alignRight">Actions</th>
			</tr>
		</thead>
		<tbody>
			<%for(int i=0;i<schoolActiveList.size();i++){%>
			<tr>
				<td>
					<% out.print(schoolActiveList.get(i).getName()); %>
				</td>
				<td>
					<% out.print(schoolActiveList.get(i).getAlias()+","+schoolActiveList.get(i).getPlotNo() 
							+","+schoolActiveList.get(i).getStreetName()
							+","+schoolActiveList.get(i).getLocality().getName()
							+","+schoolActiveList.get(i).getLocality().getCity().getName()
							); %> 
				</td>
				<td class="alignRight"><a
					href="#chgpending"
					class=" open-PendingDialog btn btn-info icon-btn" data-id="<%out.print(schoolActiveList.get(i).getLocality().getCity().getId()); %>" title="pending" alt="pending" data-toggle='modal' data-taget="#pendingreason"><i class="fa fa-rub"></i></a>
					 <a	href="#chgincomplete" class="open-incompletedialog btn btn-warning icon-btn" title="incomplete" alt="incomplete" data-id="<%out.print(schoolActiveList.get(i).getLocality().getCity().getId()); %>" data-toggle='modal' data-taget="#incompletereason" ><i class="fa fa-shield"></i></a> <a
					href=""
					class="btn btn-danger icon-btn" title="reject" alt="reject"><i class="fa fa-ban"></i></a></td>
			</tr>
			<%}
 			}%> 
		</tbody>
	</table>
	<div class="modal fade" id="pendingreason" tabindex="-1" role="dialog" aria-hidden="true" >
                      <div class="modal-dialog">
                          <div class="modal-content" style="width:450px;">
                              <div class="modal-header">
                              <input type="hidden" id="delreason" name="delreason" value="" />  
							  <input type="hidden" id="studentId" value="" />
                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                                  </button>
                                  <h4 class="modal-title" id="myModalLabel">Reason for pending</h4>
                              </div>
                              <div class="modal-body">
                                  <div class="input-group margin-bottom-sm col-sm-6">
                                      <textarea id="txtpendingReason" rows ="4" class="form-control" style="width:350px;margin-left:20px;height:120px"></textarea>
                                  </div>
                              </div>
                              <div class="modal-footer">
                                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                  <button type="button" id="deleteProfileDetail" class="btn btn-danger">Pending</button>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  <div class="modal fade" id="incompletereason" tabindex="-1" role="dialog" aria-hidden="true" >
                      <div class="modal-dialog">
                          <div class="modal-content" style="width:450px;">
                              <div class="modal-header">
                              <input type="hidden" id="delreason" name="delreason" value="" />  
							  <input type="hidden" id="studentId" value="" />
                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                                  </button>
                                  <h4 class="modal-title" id="myModalLabel">Reason for incomplete</h4>
                              </div>
                              <div class="modal-body">
                                  <div class="input-group margin-bottom-sm col-sm-6">
                                      <textarea id="txtincompleteReason" rows ="4" class="form-control" style="width:350px;margin-left:20px;height:120px"></textarea>
                                  </div>
                              </div>
                              <div class="modal-footer">
                                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                  <button type="button" id="deleteProfileDetail" class="btn btn-danger">Go to incomplete</button>
                              </div>
                          </div>
                      </div>
                  </div>
 <script type="text/javascript"> 
 $(document).on('click', 'a.open-PendingDialog', function(){
		console.log("Hi11 "+$(this).data('id'));
		$("#hdnid").val($(this).data('id'));
	});
 </script>
<!-- // var cityId = $("#change_city").val(); -->
<!-- // $.get("webapi/school/active/"+cityId,{},function(data){ -->
<!-- // 	var oTable = $("#active-tab-table").dataTable(); -->
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
