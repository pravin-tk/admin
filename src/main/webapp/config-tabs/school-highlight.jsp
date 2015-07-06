<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="org.school.admin.model.SchoolHighlight"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.AdminUser"%>
<%@page import="org.school.admin.dao.LoginValidationImp"%>
<%
	
 int school_id112 = Integer.parseInt(request.getParameter("school_id"));
 
 SchoolDAOImp schoolDAOImp112 = new SchoolDAOImp(); 
 List<SchoolHighlight> highlights = schoolDAOImp112.getSchoolHighlights(school_id112);
 
 session = request.getSession(false);
 AdminUser registration112 = new AdminUser();
 int user_id112 = 0;
 	if(session!=null)
 	{
 		System.out.print("session is not null..");
 		if(session.getAttribute("uname") != null)
 		{
 				System.out.print("session attribute is not null..");
 				registration112  = (AdminUser)session.getAttribute("uname");
   				//out.print(registration.getName());
   				System.out.println();
   				System.out.println("user id : "+registration112.getId());
   				user_id112 = registration112.getId();
   				System.out.println();
 		}
    }	


%>

<!--sales detail tab starts-->
<form action="" method="post" id="sales_detail" class="form-horizontal">
	<div class="sales-list" id="highlight-table">
		<p>Here you can add school highlights.</p>
		<a href="#" class="btn btn-primary view-sales bottom-margin"><i class="fa fa-plus"></i> Highlight</a>
		<table class="table table-striped table-bordered" id="highlight-table-data">
			<thead>
				<tr>
					<th>Highlight</th>
					<th class="alignRight">Actions</th>
				</tr>
			</thead>
			<%
				for(int i = 0; i < highlights.size(); i++)
          		{
          			out.print("<td>"+highlights.get(i).getName()+"</td>");
          			out.print("<td><a href='javascript:editHighlight("+highlights.get(i).getId()+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a></td></tr>");
          		}
			%>

		</table>
		<a href="#" class="btn btn-primary view-sales"><i class="fa fa-plus"></i> Highlight</a>
	</div>
	<div class="sales-new" style="display: none" id="highlight-save">
		<h2>Add New Highlight</h2>
		<input type="hidden" id="highlight_id" name="highlight_id" value=""/>
		<div class="form-group">
			<label for="" class="col-sm-2 control-label" data-toggle="tooltip"
				data-placement="bottom" title="Tooltip...">Highlight *</label>
			<div class="col-sm-6">
				<input data-brackets-id="3402" type="text" class="form-control" id="highlight_name" name="highlight_name" placeholder="Name of highlight"/>
			</div>
			<div class="col-sm-4">
				<div class="tooltip custom-tool-tip right">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">(Name of school highlight)</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="school_id" value="<%out.print(school_id112);%>"> 
		<input type="hidden" id="user_id" value="<%out.print(user_id112);%>">
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-2">
				<button type="button" id="savehighlight" class="btn btn-success" onclick="saveHighlight();">Save</button>
				<button type="button" id="updatehighlight" class="btn btn-success" style="display:none;" onclick="updateHighlight();">Update</button>
				<button type="reset" id="highlightcancel" class="btn list-id list-sales">Cancel</button>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
function saveHighlight(){
	if($("#highlight_name").val() != "") {
		$.post("${baseUrl}/webapi/school/savehighlight",{name: $("#highlight_name").val(), school_id: $("#school_id").val()},function(data){
			var oTable = $("#highlight-table-data").dataTable();
			    oTable.fnClearTable();
			$(data).each(function(index){
		    	html = "<a href='javascript:editHighlight("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>";
		    	var row = [];
		    	row.push(data[index].name);
			   	row.push(html);
		    	oTable.fnAddData(row);
		    });
			$("#highlight-save").hide();
			$("#highlight-table").show();
			$("#updatehighlight").hide();
			$("#savehighlight").show();
			$("#highlight_name").val("");
			updateProgress($('#school_id').val());
		});
	} else {
		alert("Please enter highlight title.");
		$("#highlight-table").hide();
		$("#highlight-save").show();
	}
	
}

function editHighlight(id) {
	$.get('webapi/school/highlight_detail/' + id, {}, function(data) {
		$("#highlight-save").show();
		$("#highlight-table").hide();
		$("#updatehighlight").show();
		$("#savehighlight").hide();
		$("#highlight_id").val(data.id);
		$("#highlight_name").val(data.name);
	});
}

function updateHighlight(){
	$.post("${baseUrl}/webapi/school/updatehighlight",{id: $("#highlight_id").val(), name: $("#highlight_name").val(), school_id: $("#school_id").val()},function(data){
		var oTable = $("#highlight-table-data").dataTable();
		    oTable.fnClearTable();
		$(data).each(function(index){
	    	html = "<a href='javascript:editHighlight("+data[index].id+");' class='btn btn-success icon-btn'><i class='fa fa-pencil'></i></a>";
	    	var row = [];
	    	row.push(data[index].name);
		   	row.push(html);
	    	oTable.fnAddData(row);
	    });
		updateProgress($('#school_id').val());
		$("#highlight-save").hide();
		$("#highlight-table").show();
		$("#updatehighlight").hide();
		$("#savehighlight").show();
		$("#highlight_name").val("");
	});
}
$("#highlightcancel").click(function(){
	$("#highlight-save").hide();
	$("#highlight-table").show();
	$("#updatehighlight").hide();
	$("#savehighlight").show();
	$("#highlight_name").val("");
});
</script>