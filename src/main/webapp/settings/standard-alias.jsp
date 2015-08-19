<%@page import="org.school.admin.model.StandardType"%>
<%@page import="org.school.admin.model.StandardAlias"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%@include file="../header.jsp"%>
<%@include file="../LeftNav.jsp"%>
<%
	SettingsImpl settings = new SettingsImpl();
List<StandardAlias> standardAilasList = settings.getStandardAlias();
List<StandardType> standardTypeList = settings.getStandardType();
%>
<div class="col-sm-12 col-md-12  main">

	<ol class="breadcrumb">
		<li><a href="#">Settings</a></li>
		<li><a href="#">Standard Alias</a></li>
		<li class="active">Add</li>
	</ol>

	<div id="myTabContent" class="tab-content">
		<div class="contacts-list">
			<h2>List Standard Alias</h2>
			<p>Here you can add or deactivate standard alias.</p>
			<a href="#" class="btn btn-primary view-contacts bottom-margin"><i
				class="fa fa-plus"></i>Standard Alias </a>
			<table class="table table-striped table-bordered" id="contacts-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th class="alignRight">Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
						int standard_alias_size = standardAilasList.size(); 
											for(int i=0; i < standard_alias_size; i++){
					%>
					<tr>
						<td>
							<%
								out.print(standardAilasList.get(i).getId());
							%>
						</td>
						<td>
							<%
								out.print(standardAilasList.get(i).getName());
							%>
						</td>
						<td class="alignRight"><a
							href="javascript:editstandardalias(<%out.print(standardAilasList.get(i).getId());%>);"
							class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
						</td>
					</tr>

					<%
						}
					%>
				</tbody>
			</table>
			<a href="#" class="btn btn-primary view-contacts bottom-margin"><i
				class="fa fa-plus"></i> Standard Alias</a>
		</div>
		<div class="contacts-new" style="display: none;">
			<h4>Add New Standard Alias</h4>
			<div id="error"></div>
			<form method="post" action="" class="form-horizontal" id="submitForm">
				<div class="form-group">
					<label class="col-sm-2 control-label">Standard *</label>
					<div class="col-sm-4">
						<select id="standard" name="standard" class="form-control">
							<option value="0">-- Select standard --</option>
							<%
								if(standardTypeList.size() > 0){
											for (int i = 0; i < standardTypeList.size(); i++) {

												StandardType standardType = standardTypeList.get(i);
							%>
							<option value="<%out.print(standardType.getId());%>">
								<%
									out.print(standardType.getName());
								%>
							</option>

							<%
								}
										}
							%>

						</select>
					</div>
					<div class="col-sm-6">
						<div class="tooltip custom-tool-tip right">
							<div class="tooltip-arrow"></div>
							<div class="tooltip-inner">This is the name of the class.
								It might be used in the template of the email/SMS we send, so be
								careful.</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Standard Alias Name</label>
					<div class="col-sm-2">
						<input type="text" id="name" name="name" class="form-control"
							placeholder="Standard Ailas eg:-Red for Playfgroup">
					</div>
					<div class="col-sm-8">
						<div class="tooltip custom-tool-tip right">
							<div class="tooltip-arrow"></div>
							<div class="tooltip-inner">This is the name of the Standard
								Alias.</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<button type="button" id='save' class="btn btn-success">Save</button>
						<button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript">
	    	$('#save').click(function(){   
	    		
	    		if($("#name").val() == ""){
	    			alert("Please enter the standard ailas name");
	    		}
	    		if($("#standard").val()==0){
	    			alert("Please select Standard");
	    		}else{
	    			$.post('../webapi/settings/standard-alias/save',{standard:$("#standard").val(),name: $("#name").val()},function(data){
		    			if(data.status == 1)
		    				window.location.href = "${baseUrl}/settings/standard-alias.jsp";
		    			else
		    				alert(data.message);
		    		},'json');
	    		}
	    	});
			function editstandardalias(id){
				window.location.href = "${baseUrl}/settings/editstandard-alias.jsp?id="+id;
			}
  	</script>