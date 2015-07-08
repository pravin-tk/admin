
<%@page import="org.school.admin.model.SchoolInfo"%>
<%@page import="org.school.admin.dao.SchoolDetailDAOImpl"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="java.util.List"%>
<%
	int school_id4 = Integer.parseInt(request.getParameter("school_id"));
	SchoolDetailDAOImpl schoolDetailDAOImpl1 = new SchoolDetailDAOImpl();
	List<SchoolInfo> info_list1 = null;
	info_list1 = schoolDetailDAOImpl1.getSchoolInfo(school_id4);
 
 session = request.getSession(false);
 AdminUser registration4 = new AdminUser();
 int user_id4 = 0;
 	if(session!=null)
 	{
 		System.out.print("session is not null..");
 		if(session.getAttribute("uname") != null)
 		{
 				System.out.print("session attribute is not null..");
 				registration4  = (AdminUser)session.getAttribute("uname");
   				//out.print(registration.getName());
   				System.out.println();
   				System.out.println("user id : "+registration4.getId());
   				user_id4 = registration4.getId();
   				System.out.println();
 		}
    }	


%>
<form action="" method="post" id="school_achievement" class="form-horizontal">
	<div class="form-group">
	      <label for="" class="col-sm-2 control-label" data-toggle="tooltip"
	       data-placement="bottom" title="Tooltip...">Awards</label>
	      <div class="col-sm-6">
	       
	            <textarea class="form-control" rows="4"  placeholder="Please enter name" id='award_name' ><% 	if(info_list1.size() > 0){
	            	if(info_list1.get(0).getTieUpDesc() != null)
            			out.print(info_list1.get(0).getAwardDesc()); 
	            	} %></textarea>
	      </div>
	      <div class="col-sm-4">
	              <div class="tooltip custom-tool-tip right">
	                  <div class="tooltip-arrow"></div>
	                  <div class="tooltip-inner">
	                (Describe the name and year)
	                  </div>
	              </div>
	          </div>
	  </div>
	  <div class="form-group">
	      <label for="" class="col-sm-2 control-label" data-toggle="tooltip"
	       data-placement="bottom" title="Tooltip...">School Associations/Tie Up/Affiliation</label>
	      <div class="col-sm-6">
	            <textarea class="form-control" rows="4"  placeholder="Please enter School Associations/Tie Up/Affiliation" id='school_tieup' ><% if(info_list1.size() > 0){
	            		if(info_list1.get(0).getTieUpDesc() != null)
	            			out.print(info_list1.get(0).getTieUpDesc()); 
	            	}%></textarea>
	      </div>
	        <div class="col-sm-4">
	              <div class="tooltip custom-tool-tip right">
	                  <div class="tooltip-arrow"></div>
	                  <div class="tooltip-inner">
	               (List down the name)
	                  </div>
	              </div>
	          </div>
	  </div>
	  <div class="form-group">
	      <label for="" class="col-sm-2 control-label" data-toggle="tooltip" 
	      data-placement="bottom" title="Tooltip...">Approvals</label>
	      <div class="col-sm-6">                                       
	           <textarea class="form-control" rows="4"  placeholder="Please enter approval" id='approval' ><%if(info_list1.size() > 0){
	           		if(info_list1.get(0).getApprovalDesc() != null)
	           			out.print(info_list1.get(0).getApprovalDesc()); 
	           	}%></textarea>
	      </div>
	       <div class="col-sm-4">
	              <div class="tooltip custom-tool-tip right">
	                  <div class="tooltip-arrow"></div>
	                  <div class="tooltip-inner">
	              
	                  </div>
	              </div>
	          </div>
	  </div>
	 <input type="hidden" value="<%out.print(school_id4); %>" id="school_id" />
	 <input type="hidden" value="<%out.print(user_id4); %>" id="user_id"/>
	 <div class="form-group">
	     <div class="col-sm-2 col-sm-offset-2">
	         <button type="button"  id="saveschoolacheiviment" class="btn btn-success">Save</button>
	     </div>
	 </div>
</form>                            
<script type="text/javascript">
 	$("#saveschoolacheiviment").click(function(){
	   	$.post("webapi/school/schoolachievement",{school_id : $('#school_id').val(),user_id : $('#user_id').val(),award_name : $('#award_name').val(),school_tieup : $('#school_tieup').val(), approval : $('#approval').val()},function(data){
	   		alert(data.message);
// 	   		$('#award_name').val("");
// 	   		$('#school_tieup').val("");
// 	   		$('#approval').val("");
	   		updateProgress($('#school_id').val());
	   	});
 	});
</script>
                       