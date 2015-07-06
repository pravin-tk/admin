<%@page import="org.school.admin.model.RatingCategoryType"%>
<%@page import="org.school.admin.dao.SettingsImpl"%>
<%@page import="java.util.List"%>
<%
	Short id = Short.parseShort(request.getParameter("id"));
	List<RatingCategoryType> ratingcategory_detail = null;
	RatingCategoryType ratingcategory = null;
	if (id > 0) {
		ratingcategory_detail = new SettingsImpl().getRatingCategoryTypeById(id);
		if(ratingcategory_detail.size() > 0)
			ratingcategory = ratingcategory_detail.get(0);
	}
%>
 <%@ include file="../header.jsp" %>
	<%@ include file="../LeftNav.jsp" %>   
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                <ol class="breadcrumb">
                    <li>
                        <a href="#">General</a>
                    </li>
                    <li class="active">Rating Category Update</li>
                </ol>
                <form method="post" action="#" class="form-horizontal" id="submitForm" novalidate="novalidate">	
					<div id="myTabContent" class="tab-content">
                        <!--Contacts tab starts-->
                        <div class="tab-pane fade active in" id="contacts" aria-labelledby="contacts-tab">
                            <div class="contacts-list">
                                <p>Here you can update rating category</p>

                            </div>
                            <div class="contacts-new">
                                <h2>Update Rating Category</h2>

								<input type="hidden" name="id" id="id" value="<% out.print(ratingcategory.getId()); %>"/>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Category Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="categoryName" id="categoryName" value="<% out.print(ratingcategory.getCategoryName()); %>" placeholder="">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Rating category name.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Weightage</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="weightage" id="weightage" value="<% out.print(ratingcategory.getWeightage()); %>">
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
                                        <button type="button" class="btn btn-success" onclick="saveRatingCategory();">Update</button>
                                        <button class="btn btn-default list-id list-contacts" type="button" onclick="showRatingCategoryList();">Cancel</button>
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
	$.post("../webapi/settings/ratingcategorytype/update", {id: $("#id").val(), categoryName: $("#categoryName").val(), weightage: $("#weightage").val()}, function(data){
		window.location.href = "${baseUrl}/settings/ratingcategorytype.jsp";
	});
}

function showRatingCategoryList(){
	window.location.href = "${baseUrl}/settings/ratingcategorytype.jsp";
}
	
</script>