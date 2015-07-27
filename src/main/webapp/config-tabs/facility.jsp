
<%@page import="org.school.admin.model.SafetyCategory"%>
<%@page import="org.school.admin.model.ActivityCategory"%>
<%@page import="org.school.admin.model.ActivityCategoryItem"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.AdminUser"%>

<%
	int school_id7 = Integer.parseInt(request.getParameter("school_id"));
 	 session = request.getSession(false);
	 AdminUser adminuser7 = new AdminUser();
 	int user_id7 = 0;
 	if(session!=null)
 	{
 		if(session.getAttribute("uname") != null)
 		{
 			adminuser7  = (AdminUser)session.getAttribute("uname");
   				System.out.println();
   				System.out.println("user id : "+adminuser7.getId());
   				user_id7 = adminuser7.getId();
   				System.out.println();
 		}
    }	
%>  
<%@include file="facility/infrastructure.jsp" %>
<%@include file="facility/activity.jsp" %>
<%@include file="facility/safety.jsp" %>

<form method="post" action="" class="form-horizontal" id="facility_detail">    
<div class="form-group">
   	<div class="col-sm-4">
       	<button type="button" id='saveinfra' class="btn btn-success">Save</button>
       	<button class="btn btn-default list-id list-contact" id="cancel-contact-detail" type="reset">Cancel</button>
   	</div>
</div>
</form>
<script type="text/javascript">
$("#saveinfra").click(function(){
	var user_id = <%out.print(user_id7);%>
	var school_id = <%out.print(school_id7); %>
	var activity_data = [];
	var school_data = [];
	var school_item = {id:school_id};
	school_data.push(school_item);
	var activity = null; 
	 $('input[name="activity[]"]:checked').each(function() { 
		 if(activity != null) 
			 activity = activity+","+$(this).val(); 
		 else 
			 activity = $(this).val(); 
		 var activity_item = {activityCategoryItem:{id:$(this).val()},school:{id:school_id}};
		 activity_data.push(activity_item);
	});
	 var safety_data = [];
	var safety = null; 
	 $('input[name="safety[]"]:checked').each(function() { 
		 if(safety != null) 
			 safety = safety+","+$(this).val(); 
		 else 
			 safety = $(this).val(); 
		 var safety_item = {safetyCategoryItem:{id:$(this).val()},school:{id:school_id}};
		 safety_data.push(safety_item);
	});
	 var infra_data = [];
	$('input[name="infra_cat_id[]"]:checked').each(function() { 
		 var infra_item = {school:{id:school_id},infrastructureCategoryItem:{id:$(this).val()},
				 isOptional:$('input[name=rdochoice_'+$(this).val()+']:checked').val(),
				 countItemValue:$('#noOfItems_'+$(this).val()).val(),description:$('#desc_'+$(this).val()).val(),charges:$('#noOfCharges_'+$(this).val()).val()};
		 infra_data.push(infra_item);
	});
	var final_infra = {schoolActivityCatItems:activity_data,safetyCatItems:safety_data,schoolInfrastructureCatItems:infra_data,school : school_data}
	
	 $.ajax({
		    url: 'webapi/school/saveinfrastructure',
		    type: 'POST',
		    data: JSON.stringify(final_infra),
		    contentType: 'application/json; charset=utf-8',
		    dataType: 'json',
		    async: false,
		    success: function(msg) {
		        alert(msg.message);
		        updateProgress($('#school_id').val());
		    }
		});

});

function showOption(chkid) {
	
	var divoption = "divoption_"+chkid;
	var divdesc = "divdesc_"+chkid;
	var chkname = "chkinfra_"+chkid;
	var divcatitem = "divcatitem_"+chkid;
	console.log("CHK="+$('#'+chkname).is(':checked'));
	console.log("DIVOPTION="+divoption);
	
	if ($('#'+chkname).is(':checked')){
		if($("#optional"+chkid).val() == 1){
			$("#"+divoption).show();
			$("#"+divcatitem).show();
		}else{
			$("#"+divoption).hide();
			$("#"+divoption).hide();
		}
	}else{
		$("#"+divoption).hide();
	}
}

function showItem(chkid,option){
	var divcatitem = "divcatitem_"+chkid;
	var rdo = "rdoOption_"+chkid;
	if ($('#'+rdo).is(':checked') && option == 1){
		$("#"+divcatitem).show();
		console.log("DIV ITEM="+ divcatitem);
	}else{
		$("#"+divcatitem).hide();
		console.log("DIV ITEM="+ divcatitem);
	}
}

$("#chkinfra").click(function(){
	
	alert('hey');
});



</script>
