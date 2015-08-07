<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.School"%>
<%@page import="java.util.List"%>

<%List<School> schoolIncompleteList = new SchoolDAOImp().getSchoolIncompleteList((Integer)session.getAttribute("cityid"));
   if(schoolIncompleteList.size()>0)
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
			<%for(int i=0;i<schoolIncompleteList.size();i++){%>
			<tr>
				<td>
					<% out.print(schoolIncompleteList.get(i).getName()); %>
				</td>
				<td>
					<% out.print(schoolIncompleteList.get(i).getAlias()+","+schoolIncompleteList.get(i).getPlotNo() 
							+","+schoolIncompleteList.get(i).getStreetName()
							+","+schoolIncompleteList.get(i).getLocality().getName()
							+","+schoolIncompleteList.get(i).getLocality().getCity().getName()); 
 					 %> 
				</td>
				
				<td class="alignRight"><a
					href=""
					class="btn btn-success icon-btn"><i class="fa fa-font"></i></a> <a
					href="#incompltepending"
					class=" open-pending btn btn-info icon-btn" data-id="<%out.print(schoolIncompleteList.get(i).getId()); %>" title="pending" alt="pending" data-toggle='modal' data-taget="#incompltepending"><i class="fa fa-rub"></i></a>
					  <a
					href=""
					class="btn btn-danger icon-btn"><i class="fa fa-ban"></i></a></td>
			</tr>
			<%}
 			}%> 
		</tbody>
	</table>
			<div class="modal fade" id="incompltepending" tabindex="-1" role="dialog" aria-hidden="true" >
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
                                      <textarea id="incompletependingReason" rows ="4" class="form-control" style="width:350px;margin-left:20px;height:120px"></textarea>
                                  </div>
                              </div>
                              <div class="modal-footer">
                                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                  <button type="button" id="btn-incompletepending" class="btn btn-danger">Pending</button>
                              </div>
                          </div>
                      </div>
               </div>
               
    <script type="text/javascript"> 
 $(document).on('click', 'a.open-pending', function(){
		console.log("Hi11 "+$(this).data('id'));
		$("#hdnid").val($(this).data('id'));
	});
 $("#btn-incompletepending").click(function(){
	if($("#incompletependingReason").val() != ""){
	    pending
		$("#incompletependingReason").val("");
	}else{
		alert("Please enter the reason for pending");
	}
 });
 </script>            