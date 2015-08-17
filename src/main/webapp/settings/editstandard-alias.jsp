<%@page import="org.school.admin.model.StandardType"%>
<%@page import="org.school.admin.model.StandardAlias"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));	
	List<StandardAlias> detail = null;
	List<StandardType> standardTypeList = null;
	standardTypeList = new SettingsImpl().getStandardType();
	StandardAlias standardAlias = null;
	int standard_size = standardTypeList.size();
	if (id > 0) {
		detail = new SettingsImpl().getStandardAliasById(id);
		if(detail.size() > 0)
			standardAlias = detail.get(0);
	}
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">Settings</a>
                    </li>
                    <li class="active">Update Standard Alias</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update standard alias detail</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Standard Alias</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(standardAlias.getId()); %>"/>
								<div class="form-group">
					<label class="col-sm-2 control-label">Standard *</label>
					<div class="col-sm-4">
						<select id="standard" name="standard" class="form-control">
							<option value="0">-- Select standard --</option>
							<%
									for(int i=0; i < standard_size; i++){
									                                        	String str_selected = "";
									                                        	if(standardTypeList.get(i).getId() ==  standardAlias.getStandardType().getId() )
									                                        			str_selected = "SELECTED";
									                                        	else
									                                        		str_selected = "";
									                                        	
									                                        	out.print("<option "+ str_selected + " value='"+standardTypeList.get(i).getId()+"'>"+standardTypeList.get(i).getName()+"</option>");
									                                     }
								%>

						</select>
					</div>
				</div>				
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Standard Alias Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="name" id="name" value="<% out.print(standardAlias.getName()); %>" placeholder="Books">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the standard alias.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-success" onclick="updateStandardAlias();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showStandardAliasList();">Cancel</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                    </div>
               </form>
 		</div>
    
    <!-- /Right main content -->
<%@ include file="../footer.jsp" %>
<script type="text/javascript">
	function updateStandardAlias(){
		if($("#standard").val()==0){
			alert("Please select standard");
		}
		if($("#name").val() == ""){
			alert("Please enter the standard alias name");
		}
		else{
			$.post("../webapi/settings/standard-alias/update", {id: $("#id").val(),standard:$("#standard").val(), name: $("#name").val()}, function(data){
				if(data.status == 1)
					window.location.href = "${baseUrl}/settings/standard-alias.jsp";
				else
					alert(data.message);
			});
		}
	}
	
	function showStandardAliasList(){
		window.location.href = "${baseUrl}/settings/standard-alias.jsp";
	}
</script>