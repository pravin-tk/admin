<%@page import="org.school.admin.model.RatingCategoryType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	List<RatingCategoryType> ratingcategory_list = new SettingsImpl().getAllRatingCategoryType();
	int ratingcategory_size = ratingcategory_list.size(); 
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Rating Category Type</li>
                </ol>
                
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Rating Category Type</a>

                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>Category Name</th>
                                            <th>Weightage</th>
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%
                                        for(int i=0; i < ratingcategory_size; i++){
                                        %>
                                        <tr>
                                            <td><% out.print(ratingcategory_list.get(i).getCategoryName()); %></td>
                                            <td><% out.print(ratingcategory_list.get(i).getWeightage() == 1); %></td>
                                            <td class="alignRight">
                                            	<a href="javascript:editRatingCategory(<% out.print(ratingcategory_list.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>

                                <a href="#" class="btn btn-primary view-contacts"><i class="fa fa-plus"></i> Rating Category Type</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h2>Add New Rating Category Type</h2>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Category Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Category Name.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Weightage</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="weightage" id="weightage" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                 Weightage/priority.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-success" onclick="saveRatingCategory();">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                    </div>
               </form>
 		</div>
    </div>
    <!-- /Right main content -->
<%@ include file="../footer.jsp" %>
<script type="text/javascript">
function saveRatingCategory(){
	$.post("../webapi/settings/ratingcategorytype/save", {categoryName: $("#categoryName").val(), weightage: $("#weightage").val()}, function(data){
		if(data.status == 1)
			window.location.href = "${baseUrl}/settings/ratingcategorytype.jsp";
		else
			alert(data.message);
	});
}

function editRatingCategory(id){
	window.location.href = "${baseUrl}/settings/editratingcategorytype.jsp?id="+id;
}	
</script>