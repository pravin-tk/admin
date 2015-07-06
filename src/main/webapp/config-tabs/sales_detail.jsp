<%@page import="org.school.admin.dao.SalesDetailDAOImpl"%>
<%@page import="org.school.admin.model.SalesInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.dao.LoginValidationImp"%>
<%
List <AdminUser> dataCollectors = null;
dataCollectors  = new LoginValidationImp().getDataCollector();

List<AdminUser> salesExecutives = null;
salesExecutives = new LoginValidationImp().getSalesExecutive();
 
 int school_id2 = Integer.parseInt(request.getParameter("school_id"));
 
 SalesDetailDAOImpl salesDetailDAOImpl = new SalesDetailDAOImpl(); 
 List<SalesInfo> salesInfos = salesDetailDAOImpl.getSalesDetail(school_id2);
 
 session = request.getSession(false);
 AdminUser registration2 = new AdminUser();
 int user_id1 = 0;
 	if(session!=null)
 	{
 		System.out.print("session is not null..");
 		if(session.getAttribute("uname") != null)
 		{
 				System.out.print("session attribute is not null..");
 				registration2  = (AdminUser)session.getAttribute("uname");
   				//out.print(registration.getName());
   				System.out.println();
   				System.out.println("user id : "+registration2.getId());
   				user_id1 = registration2.getId();
   				System.out.println();
 		}
    }	


%>


  <!--sales detail tab starts-->
                  <form action="" method="post" id="sales_detail" class="form-horizontal">  
                  <input type="hidden" name="sdId" id="sdId" value=""/>                        
                       <div class="sales-list" id="sales-detail-list">
                                
                                <p>Here you can add or deactivate sales detail. You can define sales details, contact methods etc.</p>
                                <a href="#" class="btn btn-primary view-sales bottom-margin"><i class="fa fa-plus"></i> Sales Detail</a>


                                <table class="table table-striped table-bordered" id="sales-table">
                                    <thead>
                                        <tr>
                                     		<th>Contact Person Name</th>
                                     		<th>Contact Person Number</th>
                                     		<th>Contact Person Email</th>
                                            <th>Designation</th>                           
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <%
                                    		for(int i = 0; i < salesInfos.size(); i++)
                                    		{
                                    			SalesInfo salesInfo = salesInfos.get(i);
                                    			out.print("<td>"+salesInfo.getInfoProviderName()+"</td>");
                                    			out.print("<td>"+salesInfo.getInfoProviderContactNo()+"</td>");
                                    			out.print("<td>"+salesInfo.getInfoProviderEmail()+"</td>");
                                    			out.print("<td>"+salesInfo.getInfoProviderDesignation()+"</td>");
                                    			out.print("<td><a href='javascript:editSalesInfo("+salesInfo.getId()+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a></td></tr>");
                                    		}
                                    
                                    
                                     %> 
                                    
                                </table>

                                <a href="#" class="btn btn-primary view-sales"><i class="fa fa-plus"></i> Sales Detail</a>

                            </div>
                            <div class="sales-new" style="display:none" id="sales-detail-add">
                                <h2>Add New Sales Detail</h2>


                                                              <div class="form-group" >
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Sales executive name*</label>
                                <div class="col-sm-6">
                                    <select name="" id="sales_executive" class="form-control">
                                  <option value='0'>Select sales executive name</option>
                                  <%
                            
                            	for(int i=0; i < salesExecutives.size();i++)
                            	{
                            		AdminUser salesExecutive = salesExecutives.get(i);
                            		out.print("<option value='"+salesExecutive.getId()+"' >"+salesExecutive.getName()+"</option>");
                            	}
                            
                            %>
                             
                     </select>  
                                </div>
                            </div>
                            <div class="form-group datacollector">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Data Collector Name*</label>
                                <div class="col-sm-6">
                                   <select name="" id="datacollector" class="form-control">
                                  <option value='0'>Select data collector name</option>
                                  <%
                            
                            	for(int i=0; i < dataCollectors.size();i++)
                            	{
                            		AdminUser dataCollector = dataCollectors.get(i);
                            		out.print("<option value='"+dataCollector.getId()+"' >"+dataCollector.getName()+"</option>");
                            	}
                            
                         	   %>
                             
                     </select>  
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Contact Person Name *</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="contact_person_name" placeholder="Name of contact person">
                                </div>
                                 <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                             (Name of school authorised person)
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Contact person number">Contact Person Number *</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="contact_person_no" onKeyPress="return checkNumber(event)" maxlength="10" placeholder="Contact number">
                                </div>
                                      <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                             (Contact no. of school authorised person)
                                            </div>
                                        </div>
                                    </div>
                            </div>
                              <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Contact person email">Contact Person email *</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="contact_person_email" placeholder="Contact email">
                                </div>
                                      <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                             (Contact email. of school authorised person)
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            
                              <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Designation *</label>
                                <div class="col-sm-6">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="designation" placeholder="Designation">
                                </div>
                                      <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                          (Designation of school authorised person)
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <input type="hidden"  id="school_id" value="<%out.print(school_id2);%>">
                            
                            <input type="hidden"  id="user_id" value="<%out.print(user_id1);%>">
                            <div class="form-group">
                                <div class="col-sm-2 col-sm-offset-2">
                                    <button type="button" id="savesalesdetail"  class="btn btn-success">Save</button>
                                    <button type="button" id="updatesalesdetail" class="btn btn-success " style="display:none;">Update</button>
                                    <button type="reset" id="cancelsales"  class="btn list-id list-sales">Cancel</button>
                                </div>
                            </div>
                            </div>
                      </form>      
 <script type="text/javascript">
 function ValidateEmail(email) {
     var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
     return expr.test(email);
 };
	function checkNumber(evt)
	{
	  	evt = (evt) ? evt : window.event
	  	var charCode = (evt.which) ? evt.which :evt.keyCode
	   	if(charCode > 31 && (charCode>57  ||   charCode < 48)){
		   return false;
	   	}		  
	  	return true;
	}
 $("#savesalesdetail").click(function(){
	 var blnError =false;
	 if($("#sales_executive").val() == 0)
		 {
		  alert("Please select sales executive name");
		  blnError =true;
		 }
	 else if($("#datacollector").val()==0)
		 {
		   alert("Please select data collector name");
		   blnError =true;
		 }
	 else if($.trim($("#contact_person_name").val()).length == 0 || $("#contact_person_name").val() == "")
		  {
		    alert("Please enter the contact person name");
		    blnError =true;
		  }
 else if (!ValidateEmail($('#contact_person_email').val()))
		{
		  alert("Enter valid email id of contact person");
//			$('#error-prevStudent').html("Please enter your valid email id");
//				$('#email').addClass('has-error');
		  blnError =true;
		}
 else if($("#designation").val() == "")
	 {
	   alert("Please enter the designation of the contact person");
	   blnError =true;
	 }
	 if(!blnError){
		  console.log('228');
	    	$.post("webapi/salesdetail/save",{school_id : $('#school_id').val(),
	    		user_id : $('#user_id').val(),
	    		sales_executive : $('#sales_executive').val(),
	    		datacollector : $('#datacollector').val(),
	    		contact_person_name : $('#contact_person_name').val(),
	    		contact_person_email: $('#contact_person_email').val(),
	    		contact_person_no : $('#contact_person_no').val(),
	    		designation : $('#designation').val()},function(data){
	    		
	    		//alert("Hi");
	    		alert("Saved Successfully");
	    		$(".sales-new").hide();
	    		$(".sales-list").show();
			  //	$("#school_list").html(data);
 			    var oTable = $("#sales-table").dataTable();
			    oTable.fnClearTable();
			    $(".sales-new").hide();
	    		$(".sales-list").show();
			    updateProgress($('#school_id').val());
			    $(data).each(function(index){
			    	var row = [];
			    	row.push(data[index].infoProviderName);						//contact person name
			    	row.push(data[index].infoProviderContactNo);	
			    	row.push(data[index].infoProviderEmail);	
			    	row.push(data[index].infoProviderDesignation);	
			    	row.push("<a href='javascript:editSalesInfo("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>");
			    	oTable.fnAddData(row);
			   });
	    	});
 		}else return false;
 	
 });
 
 
	function editSalesInfo(id){
   		$.get('webapi/school/sales_detail/'+id,{},function(data){
   			$("#sales-detail-add").show();
			$("#sales-detail-list").hide();
			$("#savesalesdetail").hide();
			$("#updatesalesdetail").show();
			$('#sdId').val(data.id);
		//	alert("Sales executive name : "+data.adminUserBySalesExecutiveId.name);
			$("#sales_executive").val(data.adminUserBySalesExecutiveId.id);
			//$("div.datacollector select").val("Rahul");
			//$("#sales_executive option[value = '"+data.adminUserBySalesExecutiveId.id+"']").prop('selected', true);
			$("#datacollector").val(data.adminUserByDataCollectorId.id)
			$('#contact_person_name').val(data.infoProviderName);
			$('#contact_person_email').val(data.infoProviderEmail);
			$("#contact_person_no").val(data.infoProviderContactNo);
			$('#designation').val(data.infoProviderDesignation);
		});
   	}
	
	$("#updatesalesdetail").click(function(){
		 var blnError =false;
		 if($("#sales_executive").val() == 0)
			 {
			  alert("Please select sales executive name");
			  blnError =true;
			 }
		 else if($("#datacollector").val()==0)
			 {
			   alert("Please select data collector name");
			   blnError =true;
			 }
		 else if($.trim($("#contact_person_name").val()).length == 0 || $("#contact_person_name").val() == "")
			  {
			    alert("Please enter the contact person name");
			    blnError =true;
			  }
	 else if (!ValidateEmail($('#contact_person_email').val()))
			{
			  alert("Enter valid email id of contact person");
//				$('#error-prevStudent').html("Please enter your valid email id");
//					$('#email').addClass('has-error');
			  blnError =true;
			}
	 else if($("#designation").val() == "")
		 {
		   alert("Please enter the designation of the contact person");
		   blnError =true;
		 }
		 if(!blnError){
			  console.log('228');
		    	$.post("webapi/salesdetail/update",{school_id : $('#school_id').val(),
		    		id: $("#sdId").val(),
		    		user_id : $('#user_id').val(),
		    		sales_executive : $('#sales_executive').val(),
		    		datacollector : $('#datacollector').val(),
		    		contact_person_name : $('#contact_person_name').val(),
		    		contact_person_email: $('#contact_person_email').val(),
		    		contact_person_no : $('#contact_person_no').val(),
		    		designation : $('#designation').val()},function(data){
		    		
		    		//alert("Hi");
		    		alert("Saved Successfully");
		    		$(".sales-new").hide();
		    		$(".sales-list").show();
				  //	$("#school_list").html(data);
	 			    var oTable = $("#sales-table").dataTable();
				    oTable.fnClearTable();
				    $(".sales-new").hide();
		    		$(".sales-list").show();
				    updateProgress($('#school_id').val());
				    $(data).each(function(index){
				    	var row = [];
				    	row.push(data[index].infoProviderName);						//contact person name
				    	row.push(data[index].infoProviderContactNo);	
				    	row.push(data[index].infoProviderEmail);	
				    	row.push(data[index].infoProviderDesignation);	
				    	row.push("<a href='javascript:editSalesInfo("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>");
				    	oTable.fnAddData(row);
				   });
		    	});
	 		}else return false;
	 	
	 });

</script>
                        
                        <!-- sales detail tab ends-->