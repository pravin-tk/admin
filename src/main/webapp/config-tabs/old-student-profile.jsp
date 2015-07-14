<%@page import="org.school.admin.data.PrevStudentProfileList"%>
<%@page import="org.school.admin.dao.PrevStudentProfileDAO"%>
<%@page import="org.school.admin.model.ContactInfo"%>
<%@page import="org.school.admin.dao.ContactDetaillDAO"%>
<%@page import="org.school.admin.dao.SalesDetailDAOImpl"%>
<%@page import="org.school.admin.model.SalesInfo"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%
	
 
 	int school_id5 = Integer.parseInt(request.getParameter("school_id"));
 
	PrevStudentProfileDAO prevStudentProfileDAO = new PrevStudentProfileDAO();
	List<PrevStudentProfileList> prevStudentProfiles = prevStudentProfileDAO.getPrevStudentProfile(school_id5);
	 session = request.getSession(false);
	 AdminUser registration5 = new AdminUser();
 	int user_id5 = 0;
 	if(session!=null)
 	{
 		if(session.getAttribute("uname") != null)
 		{
 				registration5  = (AdminUser)session.getAttribute("uname");
   				System.out.println();
   				System.out.println("user id : "+registration5.getId());
   				user_id5 = registration5.getId();
   				System.out.println();
 		}
    }	


%>

               


        <!-- <div id="myTabContent" class="tab-content"> -->
<form action="" method="post" id="old_student_profile" class="form-horizontal">
       <div class="prevStudent-list" id="pre_student_list">
           <p>Here you can add or deactivate school type.</p>
           <a href="#" class="btn btn-primary view-prevStudent bottom-margin" id="addoldStudentProfile"><i class="fa fa-plus"></i> old student profile</a>
           <table class="table table-striped table-bordered" id="prevStudent-table">
               <thead>
                   <tr>
                       <th>Name</th>
                       <th>Batch</th>
                       <th>Achievements</th>
                       <th class="alignRight">Actions</th>
                   </tr>
               </thead>
               <tbody>
               <%
               	try{
                   	for(int i = 0; i < prevStudentProfiles.size(); i++)
                   	{
                    	PrevStudentProfileList contactDetail2 = prevStudentProfiles.get(i);
                    	out.print("<tr><td>"+contactDetail2.getName()+"</td>");
                    	out.print("<td>"+contactDetail2.getBatch()+"</td>");
                    	out.print("<td>"+contactDetail2.getAchievements()+"</td>");
                    	out.print("<td><a href='javascript:editProfile("+contactDetail2.getId()+")' class='btn btn-success icon-btn'>"
                    				+"<i class='fa fa-pencil'></i></a>"
                                    +" <a href='javascript:deleteProfile("+contactDetail2.getId()+")' class='btn btn-danger icon-btn'>"
                                    +"<i class='fa fa-trash'></i></a></td></tr>");
                    }
                }
                catch(Exception e)
                {
             	   System.err.print("PreviousStudentProfileError : "+e);
                }
          	%> 
      		</tbody>
       	</table>
   		<a href="#" class="btn btn-primary view-prevStudent bottom-margin" id="addoldStudentProfile1"><i class="fa fa-plus"></i> old Student Profile</a>
    </div>
 	<div class="prevStudent" style="display:none;" id="pre_student_add">
 	<h4>Add old Student Profile</h4>
    	<div id="error-prevStudent" class="has-error bg-danger nopadding"></div>
    	<input type="hidden" name="osId" id="osId" value=""/>
        <div class="form-group">
            <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Name</label>
            <div class="col-sm-4">
              	<input data-brackets-id="3402" type="text" class="form-control" id="osname" placeholder="enter old student name">
          	</div>
       </div>
       <div class="form-group">
          	<label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">email</label>
            <div class="col-sm-4">
              	<input data-brackets-id="3402" type="text" class="form-control" id="osemail" placeholder="enter old student email id">
            </div>
       </div>
       <div class="form-group">
           	<label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Mobile No</label>
            <div class="col-sm-2">
              	<input data-brackets-id="3402" type="text" class="form-control" id="osmobile_no" onKeyPress="return checkNumber(event)" placeholder="enter old student mobile number" maxlength="10">
            </div>
       	</div>
              
        <div class="form-group">
                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Batch</label>
                <div class="col-sm-6">
                    <input data-brackets-id="3402" type="text" class="form-control" id="osbatch" placeholder="enter old student batch">
                </div>
        </div>
        <div class="form-group">
                <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Achievements</label>
                <div class="col-sm-6">
                    <input data-brackets-id="3402" type="text" class="form-control" id="osachievements" placeholder="enter old student achievements">
                </div>
        </div>
        <div class="form-group">
    		<div class="col-sm-4" id="button_list">
            	<button type="button" id='saveprevStudent' class="btn btn-success ">Save</button>
            	<button type="button" id="updatePrevStudent" class="btn btn-success " style="display:none;">Update</button>
            	<button class="btn btn-default list-id list-prevStudent" id="cancel-old-student" type="reset">Cancel</button>
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
   	$("#addoldStudentProfile1").click(function()
   	{
   		$('#saveprevStudent').show();
   		$("#updatePrevStudent").hide();
   		
   	});
   	
   	$("#addoldStudentProfile").click(function()
   	{
   		$('#saveprevStudent').show();
   		$("#updatePrevStudent").hide();
   	});
   	
   	$('#saveprevStudent').click(function(){
   		var school_id = <%out.print(school_id5);%>
  			var user_id = <%out.print(user_id5);%>
   			if($("#osname").val().length ==0 )
  			{
   				alert("Please enter previous student name");
//   				$("#error-prevStudent").html('Please enter your name, email id and mobile number');
//   				$('#email, #mobile_no, #name, #achievements, #batch ').addClass('has-error');
  				
//   			} else if (!ValidateEmail($('#osemail').val()))
//   			{
//   				$('#error-prevStudent').html("Please enter your valid email id");
//  					$('#email').addClass('has-error');
//   			} 
   			}
		else {		
   			
    		$.post('webapi/school/prestudent',{school_id : school_id, user_id : user_id,name: $("#osname").val(), email : $("#osemail").val(), mobile : $("#osmobile_no").val(), batch : $("#osbatch").val(),
    			achievements : $("#osachievements").val()},function(data){
    			
    			$('#osemail, #osmobile_no, #osname').removeClass('has-error');
    			$('#error-prevStudent').html("");
    			var oTable = $("#prevStudent-table").dataTable();
 			    oTable.fnClearTable();
 			    $("#osname").val(""); $("#osemail").val(""); $("#osmobile_no").val("");
 			    $("#osachievements").val("");
 			    $("#osbatch").val("");
 			    
 			   alert("Save successfully..");
 			    $("#pre_student_add").hide();
 			    $("#pre_student_list").show();
 			    $("#saveprevStudent").hide();
 			    $("#updatePrevStudent").show();
 			   updateProgress($('#school_id').val());
 			    $(data).each(function(index){
 			    	html = "<a href='javascript:editProfile("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"
                          +" <a href='deleteProfile("+data[index].id+");' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>";
 			    	var row = [];
 			    	 row.push(data[index].name);
 			    	 row.push(data[index].batch);
 			    	 row.push(data[index].achievements);
	 			    	 row.push(html);
 			    	oTable.fnAddData(row);
 			    });
 			  
    		},'json');
   		}
   		
   	});
	    	
   	$("#cancel-old-student").click(function(){
   		$('#error-prevStudent').html("");
   		$('#osemail, #osmobile_no, #osname, #osachievements, #osbatch ').removeClass('has-error');
   		$("#osname").val(""); $("#osemail").val(""); $("#osmobile_no").val("");
		    $("#osachievements").val("");
		    $("#osbatch").val("");
   	});
   	
   	function editProfile(id){
   		$.get('webapi/school/prestudent_detail/'+id,{},function(data){
   			$("#pre_student_add").show();
			$("#pre_student_list").hide();
			$("#saveprevStudent").hide();
			$("#updatePrevStudent").show();
			$('#osId').val(data.id);
			$('#osemail').val(data.email);
			$('#osmobile_no').val(data.mobile);
			$('#osname').val(data.name);
			$('#osachievements').val(data.achievements);
			$('#osbatch').val(data.batch);
		});
   	}
   	
   	$('#updatePrevStudent').click(function(){
   		var school_id = <%out.print(school_id5);%>
  			var user_id = <%out.print(user_id5);%>
   			if($("#osname").val().length ==0)
  			{
   				alert("Please enter previous student name");
//   				$("#error-prevStudent").html('Please enter your name, email id and mobile number');
//   				$('#email, #mobile_no, #name, #achievements, #batch ').addClass('has-error');
  				
//   			} else if (!ValidateEmail($('#osemail').val()))
//   			{
//   				$('#error-prevStudent').html("Please enter your valid email id");
//  					$('#email').addClass('has-error');
//   			} 
  		 	}
			else {		
   			
    		$.post('webapi/school/prestudent_update',{id: $("#osId").val(), school_id : school_id, user_id : user_id,name: $("#osname").val(), email : $("#osemail").val(), mobile : $("#osmobile_no").val(), batch : $("#osbatch").val(),
    			achievements : $("#osachievements").val()},function(data){
    			
    			$('#osemail, #osmobile_no, #osname').removeClass('has-error');
    			$('#error-prevStudent').html("");
    			var oTable = $("#prevStudent-table").dataTable();
 			    oTable.fnClearTable();
 			    $("#osname").val(""); $("#osemail").val(""); $("#osmobile_no").val("");
 			    $("#osachievements").val("");
 			    $("#osbatch").val("");
 			   alert("Updated successfully..");
 			    $("#pre_student_add").hide();
 			    $("#pre_student_list").show();
 			    $("#saveprevStudent").show();
 				$("#updatePrevStudent").hide();
 				updateProgress($('#school_id').val());
 			    $(data).each(function(index){
 			    	html = "<a href='javascript:editProfile("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>"
                          +" <a href='deleteProfile("+data[index].id+");' class='btn btn-danger icon-btn'><i class='fa fa-trash'></i></a>";
 			    	var row = [];
 			    	 row.push(data[index].name);
 			    	 row.push(data[index].batch);
 			    	 row.push(data[index].achievements);
	 			    	 row.push(html);
 			    	oTable.fnAddData(row);
 			    });
 			  
    		},'json');
   		}
   		
   	});
	    	
			
  	</script>