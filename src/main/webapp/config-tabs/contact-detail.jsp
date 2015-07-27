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
	if(contactDetails.size() == 0)
		
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
                     <a href="#" class="btn btn-primary view-contact bottom-margin"><i class="fa fa-plus"></i> Contact Detail</a>
                     <table class="table table-striped table-bordered" id="contact-detail-table">
                         <thead>
                             <tr>
                                 
                                 <th>Name</th>
                                 <th>Email</th>
                                 <th>Mobile No.</th>
                                 <th>Contact No</th>
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
                                    			out.print("<td>"+contactDetail.getContactNo()+"</td>");
                                    			System.out.print("ContactType  : "+contactDetail.getType());
                                    			System.out.print("Contact No : "+contactDetail.getContactNo());
                                    			if(contactDetail.getType() == 0)
                                    			out.print("<td>Internal</td>");
                                    			else
                                    				out.print("<td>External</td>");
                                    			out.print("<td><a href='javascript:editContactInfo("+contactDetail.getId()+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"
                                    					 +" <a href='javascript:deleteContactInfo("+contactDetail.getId()+")' class='btn btn-danger icon-btn'>"
                                                         +"<i class='fa fa-trash'></i></a></td></tr>");
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
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Select type *</label>
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="checkbox" name="usertype[]" id="usertype0" value="0">Internal
                                    </label>
                                    <label class="radio-inline">
                                        <input type="checkbox" name="usertype[]" id="usertype1" value="1">External
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Name*</label>
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
                                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Contact No.</label>
                                <div class="col-sm-4">
                                    <input data-brackets-id="3402" type="text" class="form-control" id="contact_no" onKeyPress="return checkNumber(event)" placeholder="enter contact number" maxlength="12">
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
   			var usertype = [];
    	    $('input[name="usertype[]"]:checked').each(function(){
    			usertype.push($(this).val());
    		});
    		
    		
    		var msg = "";
    		if ($("#name").val().length ==0 && ($("#email").val().length == 0 && $("#mobile_no").val().length == 0 && $("#contact_no").val().length == 0))
    		{
    			if(msg != "") msg = msg+",Please enter your name, email id and mobile number and conatct number"; else  msg ="Please enter your name, email id and mobile number and contact number";
    		}
    		if(($("#email").val() == "" && $("#mobile_no").val().length == 0 && $("#contact_no").val().length == 0)) {
    			if(msg!=("")) msg=msg+",Please enter email id or mobile number or contact number"; else msg="Please enter email id or mobile number or contact number";
    		}
    		if($("#mobile_no").val() != ""){
    			if($("#mobile_no").val().length ==0 || $("#mobile_no").val().length <10) {
    				if(msg!=(""))msg=msg+",Please enter valid mobile number"; else msg="Please enter valid mobile number";
    			}
    		}
    		if($("#contact_no").val() != ""){
    			if($("#contact_no").val().length == 0 || $("#contact_no").val().length < 11){
    				if(msg!=("")) msg=msg+",Please enter valid std code before contact number"; else msg="Please enter valid std code before contact number";
    			}
    		}
    			
    		if($('#email').val() != "") {
    			if (!ValidateEmail($('#email').val()))
    			{
    				if(msg != "") msg=msg+",Please enter your valid email id"; else msg="Please enter your valid email id";
    			}
    		}
    		if(msg != "") {
    			alert(msg);
    		} else {		
// 	    		$.post('webapi/school/savecontact',{school_id : school_id, user_id : user_id,
// 	    			usertype : usertype, name: $("#name").val(), email : $("#email").val(), 
// 	    			mobile : $("#mobile_no").val()},function(data){
// 	    			$('#email, #mobile_no, #name').removeClass('has-error');
// 	    			$('#error-contact-detail').html("");
// 	    			 var oTable = $("#contact-detail-table").dataTable();
// 	 			    oTable.fnClearTable();
// 	 			   $("#name").val(""); $("#email").val(""); $("#mobile_no").val("");
	 			    
// 	 			   $("#savecontact").addClass("list-contact");
// 	 			  updateProgress(school_id);
// 	 			    $(data).each(function(index){
// 	 			    	var row = [];
// 	 			    	 row.push(data[index].name); // name of internal or external person
// 	 			    	 row.push(data[index].email); //sales executive name
// 	 			    	 row.push(data[index].mobileNo);						//contact person name
// 		 			    	if(data[index].type == 0){
// 	 			    	 	row.push("Internal");	
// 		 			    	} else {
// 		 			    		row.push("External");
// 	 			    	}
// 	 			    	 row.push("<a href='javascript:editContactInfo("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>");
// 	 			    	oTable.fnAddData(row);
// 	 			   });
// 	 			    alert("Contact detail saved successfully");
// 	 			   $(".contact-new").hide();
// 	 			   $(".contact-list").show();
// 	 			   $("#savecontact").show();
// 	 			   $("#updatecontact").hide();
	 			  
// 	    		});
	    		
	    		
	    		$.ajax({
	    			url:'webapi/school/savecontact',
	    			data : {school_id : school_id, user_id : user_id,
		    			usertype : usertype.toString(), name: $("#name").val(), email : $("#email").val(), 
		    			mobile : $("#mobile_no").val(),contact : $("#contact_no").val()},
		    		method : 'POST',
		    		success: function(data)
		    		{
		    			$('#email, #mobile_no, #name, #contact_no').removeClass('has-error');
		    			$('#error-contact-detail').html("");
		    			
		 			   $("#name").val(""); $("#email").val(""); $("#mobile_no").val("");$("#contact_no").val('');
		 			  $('input[name="usertype[]"]').prop( "checked", false);
		 			  
		 			   $("#savecontact").addClass("list-contact");
		 			  updateProgress(school_id);
		 			 showContactDetail();
		 			    alert("Contact detail saved successfully");
		 			   $(".contact-new").hide();
		 			   $(".contact-list").show();
		 			   $("#savecontact").show();
		 			   $("#updatecontact").hide();
		    		}
		    		
	    			
	    			
	    		});
	 			   $(".contact-new").hide();
	 			   $(".contact-list").show();
	 			   $("#savecontact").show();
	 			   $("#updatecontact").hide();
	 			  
	    			//});
    		}
    		
    	});
    	function changeCheckbox()
    	{
    		 $('#usertype1').click(function() {
    		        if ($(this).is(':checked') && $("#id").val()>0) {
    		            $("#usertype0").prop('checked',false);
    		        }
    		    });
    		 $('#usertype0').click(function() {
 		        if ($(this).is(':checked') && $("#id").val()>0) {
 		            $("#usertype1").prop('checked',false);
 		        }
 		    });
    	}
   	$("#cancel-contact-detail").click(function(){
   		$('#error-contact-detail').html("");
   		$('#email, #mobile_no, #name, #contact_no').removeClass('has-error');
   		$("#savecontact").show();
		$("#updatecontact").hide();
		$("#name").val(""); $("#email").val(""); $("#mobile_no").val("");$("#contact_no").val("");
		  $('input[name="usertype[]"]').prop( "checked", false);
		  $("#id").val('');
		
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
			$("#contact_no").val(data.contactNo);
			
			$('input[name="usertype[]"]').prop( "checked", false);
			if(data.type == 0){
					$('#usertype0').prop('checked',true);
					//console.log('smthng'+$('#usertype0').prop('checked',true));
			}else if(data.type == 1){
				$('#usertype1').prop('checked',true);
				//console.log('smthng'+$('#usertype1').prop('checked',true));
			}else{
				console.log('nothing'+data.type);
			}
			changeCheckbox();
			//$('input[name="usertype[]"][value=' + data.type + ']').attr('checked', 'checked');
// 			$('input[name="usertype[]"]').each(function() {
// 					if($(this).val() == data.type){
// 			    		$(this).prop('checked', true);
// 					}
// 				});
		});
   	}
	

   	$('#updatecontact').click(function(){
		var school_id = <%out.print(school_id3);%>
		var user_id = <%out.print(user_id3);%>
		
		var usertype = [];
		$('input[name="usertype[]"]:checked').each(function(){
			usertype.push($(this).val());
		});
		var msg = "";
		if ($("#name").val().length ==0 && ($("#email").val().length == 0 && $("#mobile_no").val().length == 0 && $("#contavct_no").val().length == 0))
		{
			if(msg != "") msg = msg+",Please enter your name, email id and mobile number and contact number"; else  msg ="Please enter your name, email id and mobile number and contact number";
		}
		if(($("#email").val() == "" && $("#mobile_no").val().length == 0 && $("#contact_no").val().length == 0)) {
			if(msg!=("")) msg=msg+",Please enter email id or mobile number or contact number"; else msg="Please enter email id or mobile number or contact number";
		}
		if($("#mobile_no").val() != ""){
			if($("#mobile_no").val().length ==0 || $("#mobile_no").val().length <10) {
				if(msg!=(""))msg=msg+",Please enter valid mobile number"; else msg="Please enter valid mobile number";
				}
		}
		if($("#contact_no").val() != ""){
			if($("#contact_no").val().length == 0 || $("#contact_no").val().length < 11){
				if(msg!=("")) msg=msg+",Please enter valid std code before contact number"; else msg="Please enter valid std code before contact number";
			}
		}
		
		if($('#email').val() != "") {
			if (!ValidateEmail($('#email').val()))
			{
				if(msg != "") msg=msg+",Please enter your valid email id"; else msg="Please enter your valid email id";
			}
		}
		
		if(msg != "") {
			alert(msg);
		} else {	
   		
    		$.post('webapi/school/updatecontact',{id : $("#id").val(), 
    			school_id : school_id, 
    			user_id : user_id,
    			usertype : usertype.toString(),
    			name: $("#name").val(), 
    			email : $("#email").val(), 
    			mobile : $("#mobile_no").val(),
    			contact :$("#contact_no").val()},function(data){
    			
    			$('#email, #mobile_no, #name, #contact_no').removeClass('has-error');
    			$('#error-contact-detail').html("");
    			var oTable = $("#contact-detail-table").dataTable();
 			    oTable.fnClearTable();
 			    $("#name").val(""); $("#email").val(""); $("#mobile_no").val("");$("#contact_no").val("");
 			    $("#savecontact").addClass("list-contact");
 			   $('input[name="usertype[]"]').prop( "checked", false);
 			   updateProgress(school_id);
 			  showContactDetail();
 			   alert("Updated successfully..");
 			  $("#id").val('');
 			   $(".contact-new").hide();
 			   $(".contact-list").show();
 			   $("#savecontact").show();
 			   $("#updatecontact").hide();
    		});
		}
	});    	
	function deleteContactInfo(contactId)
	{
		var deleteContact = confirm("Do you want to delete this contact detail ?");
		if(deleteContact){
			$.post("webapi/school/deleteContact",{contactId : contactId},function(data){
				if(data.status == 1){
				 alert(data.message);
				  deleteProgress($("#school_id").val());
				 showContactDetail();
				}
			});
		}
	}
	
	function showContactDetail()
	{
		var schoolId = $("#school_id").val();
		$.get("webapi/school/viewcontact/"+schoolId,{},function(data){
			var oTable = $("#contact-detail-table").dataTable();
		    oTable.fnClearTable();
		 $(data).each(function(index){
		    	var row = [];
		    	 row.push(data[index].name);
		    	 row.push(data[index].email);
		    	 row.push(data[index].mobileNo);
		    	 if(data[index].contactNo != 'undefined')
		    		 row.push(data[index].contactNo);
			    	if(data[index].type == 0){
		    	 		row.push("Internal");	
			    	} else {
			    		row.push("External");
		    		}
		    	 row.push("<a href='javascript:editContactInfo("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"+
		    			 "<a href='javascript:deleteContactInfo("+data[index].id+");' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>");
		    	 oTable.fnAddData(row);
		   });
		});
		
	}
</script>
