<%@page import="org.school.admin.model.ContactInfo"%>
<%@page import="org.school.admin.dao.ContactDetaillDAO"%>
<%@page import="org.school.admin.dao.SalesDetailDAOImpl"%>
<%@page import="org.school.admin.model.SalesInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%
	
 
 	int school_id3 = Integer.parseInt(request.getParameter("school_id"));
 
	ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
	List<ContactInfo> contactDetails = contactDetaillDAO.getConatctDetail(school_id3);
	 session = request.getSession(false);
	 AdminUser registration3 = new AdminUser();
 	int user_id3 = 0;
 	if(session!=null)
 	{
 		if(session.getAttribute("uname") != null)
 		{
 				registration3  = (AdminUser)session.getAttribute("uname");
   				System.out.println();
   				System.out.println("user id : "+registration3.getId());
   				user_id3 = registration3.getId();
   				System.out.println();
 		}
    }	


%>

               

			<form action="" method="post" id="contact_detail" class="form-horizontal">
                 <div class="contact-list" id="contact-info-list">
                     <p>Here you can add or deactivate school type.</p>
                     <a href="#" class="btn btn-primary view-contact bottom-margin"><i class="fa fa-plus"></i> Contact Detail(<% out.print(contactDetails.size()); %>)</a>
                     <table class="table table-striped table-bordered" id="contact-detal-table">
                         <thead>
                             <tr>
                                 
                                 <th>Name</th>
                                 <th>Email</th>
                                 <th>Mobile No.</th>
                                 <th>Type</th>
                                 <th class="alignRight">Actions</th>
                             </tr>
                             
                         </thead>
                                       <%
                                       		try
                                       {
                                    		for(int i = 0; i < contactDetails.size(); i++)
                                    		{
                                    			
                                    			ContactInfo contactDetail = contactDetails.get(i);
                                    			out.print("<tr><td>"+contactDetail.getName()+"</td>");
                                    			out.print("<td>"+contactDetail.getEmail()+"</td>");
                                    			out.print("<td>"+contactDetail.getMobileNo()+"</td>");
                                    			System.out.print("Type  : "+contactDetail.getType());
                                    			if(contactDetail.getType() == 0)
                                    			out.print("<td>Internal</td>");
                                    			else
                                    				out.print("<td>External</td>");
                                    			out.print("<td><a href='javascript:editContactInfo("+contactDetail.getId()+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a></td></tr>");
                                    		}
                                       }
                                       catch(Exception e)
                                       {
                                    	   System.err.print("POCInternalError : "+e);
                                       }
                                       
                                    
                                     %> 
                         
                         
                         <tbody>
                                           </tbody>
                     </table>
                     
                     
                    
                     <a href="#" class="btn btn-primary view-contact bottom-margin"><i class="fa fa-plus"></i> Contact Detail</a>
                 </div>
                 <div class="contact-new" style="display:none;" id="contact_info_add">
                 	<h4>Add New Contact Detail</h4>
                    <div id="error-contact-detail" class="has-error bg-danger nopadding"></div>
							<input type="hidden" name="id" id="id" value=""/>
                  			<div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Select type</label>
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" name="usertype" id="usertype" value="0" checked>Internal
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="usertype" id="usertype" value="1">External
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Name</label>
                                <div class="col-sm-4">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="name"   placeholder="enter name">
                                </div>
                              </div>
                               <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Email</label>
                                <div class="col-sm-4">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="email" placeholder="enter email id">
                                </div>
                              </div>
                               <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Mobile No.</label>
                                <div class="col-sm-4">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="mobile_no" onKeyPress="return checkNumber(event)" placeholder="enter mobile number" maxlength="10">
                                </div>
                              </div>
                         <div class="form-group">
                    		<div class="col-sm-4">
                            	<button type="button" id='savecontact' class="btn btn-success">Save</button>
                            	<button type="button" id='updatecontact' class="btn btn-success" style="display:none;">Update</button>
                            	<button class="btn btn-default list-id list-contact" id="cancel-contact-detail" type="reset" >Cancel</button>
                        	</div>
                   		 </div> 
                    </div>
          	 	</form>
<script type="text/javascript">
  function checkChar(evt)
  {
	  evt = (evt) ? evt : window.event
	  var charCode = (evt.which) ? evt.which :evt.keyCode
	  //var validChar = /^[a-zA-Z]+$/;
	   if(validName(charCode)){
		   return true;
	   }		  
	  return false;
  }
  function validName(name)
  {
	  var expr = /^[a-zA-Z]+$/;
	  return expr.test(name);
  }
  function checkNumber(evt)
  {
	  evt = (evt) ? evt : window.event
	  var charCode = (evt.which) ? evt.which :evt.keyCode
	   if(charCode > 31 && (charCode>57  ||   charCode < 48)){
		   return false;
	   }		  
	  return true;
  }
  function ValidateEmail(email) {
        var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return expr.test(email);
    };
    	$('#savecontact').click(function(){
    		
    		var school_id = <%out.print(school_id3);%>
   			var user_id = <%out.print(user_id3);%>
   			
    		var type =  $('input:radio[name=usertype]:checked').val();
    		
    		
    		if($("#name").val().length ==0 && $("#email").val().length == 0 && $("#mobile_no").val().length == 0)
    			{
//     				$("#error-contact-detail").html('Please enter your name, email id and mobile number');
//     				$('#email, #mobile_no, #name').addClass('has-error');
    				
    				alert('Please enter your name, email id and mobile number');
    				
    			}
    		
    		
    		else if(!ValidateEmail($('#email').val()))
    			{
//     				$('#error-contact-detail').html("Please enter your valid email id");
//    					$('#email').addClass('has-error');
   					alert("Please enter your valid email id");
    			}
    		
    		else {		
    			
	    		$.post('webapi/school/savecontact',{school_id : school_id, user_id : user_id,type : type, name: $("#name").val(), email : $("#email").val(), mobile : $("#mobile_no").val()},function(data){
	    			$('#email, #mobile_no, #name').removeClass('has-error');
	    			$('#error-contact-detail').html("");
	    			 var oTable = $("#contact-detal-table").dataTable();
	 			    oTable.fnClearTable();
	 			   $("#name").val(""); $("#email").val(""); $("#mobile_no").val("");
	 			    
	 			   $("#savecontact").addClass("list-contact");
	 			  updateProgress(school_id);
	 			    $(data).each(function(index){
	 			    	var row = [];
	 			    	 row.push(data[index].name); // name of internal or external person
	 			    	 row.push(data[index].email); //sales executive name
	 			    	 row.push(data[index].mobileNo);						//contact person name
		 			    	if(data[index].type == 0){
	 			    	 	row.push("Internal");	
		 			    	} else {
		 			    		row.push("External");
	 			    	}
	 			    	 row.push("<a href='javascript:editContactInfo("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>");
	 			    	oTable.fnAddData(row);
	 			   });
	 			    alert("Contact detail saved successfully");
	 			   $(".contact-new").hide();
	 			   $(".contact-list").show();
	 			   $("#savecontact").show();
	 			   $("#updatecontact").hide();
	 			  
	    		});
    		}
    		
    	});
    	
   	$("#cancel-contact-detail").click(function(){
   		$('#error-contact-detail').html("");
   		$('#email, #mobile_no, #name').removeClass('has-error');
   		$("#savecontact").show();
		$("#updatecontact").hide();
		$("#name").val(""); $("#email").val(""); $("#mobile_no").val("");
   	});
   	
   	function editContactInfo(id){
   		$.get('webapi/school/contact_detail/'+id,{},function(data){
   			$("#contact_info_add").show();
			$("#contact-info-list").hide();
			$("#savecontact").hide();
			$("#updatecontact").show();
			$('#id').val(data.id);
			$('#name').val(data.name);
			$('#email').val(data.email);
			$('#mobile_no').val(data.mobileNo);
			$("input[name=usertype][value=" + data.type + "]").attr('checked', 'checked');
		});
   	}
   	
   	$('#updatecontact').click(function(){
		var school_id = <%out.print(school_id3);%>
		var user_id = <%out.print(user_id3);%>
		var type =  $('input:radio[name=usertype]:checked').val();
		if ($("#name").val().length ==0 && $("#email").val().length == 0 && $("#mobile_no").val().length == 0)
		{
// 			$("#error-contact-detail").html('Please enter your name, email id and mobile number');
// 			$('#email, #mobile_no, #name').addClass('has-error');
			
			alert('Please enter your name, email id and mobile number');
		} else if (!ValidateEmail($('#email').val()))
		{
// 			$('#error-contact-detail').html("Please enter your valid email id");
// 			$('#email').addClass('has-error');
			alert("Please enter your valid email id");
		} else {		
    		$.post('webapi/school/updatecontact',{id : $("#id").val(), school_id : school_id, user_id : user_id,type : type, name: $("#name").val(), email : $("#email").val(), mobile : $("#mobile_no").val()},function(data){
    			
    			$('#email, #mobile_no, #name').removeClass('has-error');
    			$('#error-contact-detail').html("");
    			var oTable = $("#contact-detal-table").dataTable();
 			    oTable.fnClearTable();
 			    $("#name").val(""); $("#email").val(""); $("#mobile_no").val("");
 			    $("#savecontact").addClass("list-contact");
 			  
 			   updateProgress(school_id);
 			    $(data).each(function(index){
 			    	var row = [];
 			    	 row.push(data[index].name);
 			    	 row.push(data[index].email);
 			    	 row.push(data[index].mobileNo);
	 			    	if(data[index].type == 0){
 			    	 		row.push("Internal");	
	 			    	} else {
	 			    		row.push("External");
 			    		}
 			    	 row.push("<a href='javascript:editContactInfo("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>");
 			    	 oTable.fnAddData(row);
 			   });
 			   alert("Updated successfully..");
 			   $(".contact-new").hide();
 			   $(".contact-list").show();
 			   $("#savecontact").show();
 			   $("#updatecontact").hide();
    		});
		}
	});    	
		
</script>