<%@page import="org.school.admin.model.ActivityCategory"%>
<%@page import="org.school.admin.model.ActivityCategoryItem"%>
<%@page import="org.school.admin.model.SchoolActivityCatItem"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%
	 
	 SchoolDAOImp schoolDAOImp4  = new SchoolDAOImp();
	 List<ActivityCategory> activityCategories = schoolDAOImp4.getActivityCategory();
	 List<SchoolActivityCatItem> schoolActivityCatItems = schoolDAOImp4.getSchoolActivityCatItem(Integer.parseInt(request.getParameter("school_id")));
 
%>
<div class="panel panel-info">
<div class="panel-heading">
  <h3 class="panel-title" id="myModalLabel">School Activities</h3>
  </div>
 <div class="panel-body">
<%
		ActivityCategory activityCategory =null;
		List<ActivityCategoryItem> activityCategoryItems2 = null;
      	for(int k=0;k<activityCategories.size();k++)
       	{
      		activityCategory = activityCategories.get(k);
      		activityCategoryItems2 = schoolDAOImp4.getActivityCategoryItem(activityCategory.getId());
   			if(activityCategoryItems2.size() > 0) {%>
 			<div class="form-group">
   				<label class="col-sm-2 control-label"><%out.print(activityCategory.getName()); %></label>
 			<div class="col-sm-10">
       		 <div class="inline-checkboxes-holder">
   			<%	
              for(int i = 0; i < activityCategoryItems2.size(); i++) {                                       			 
                  	ActivityCategoryItem activityCategoryItem = activityCategoryItems2.get(i);   
                  	String checked = "";
                  	if(schoolActivityCatItems.size() > 0){
                  		for(int n=0; n < schoolActivityCatItems.size(); n++){
                  			if(schoolActivityCatItems.get(n).getActivityCategoryItem().getId() == activityCategoryItem.getId()){
                  				checked = "checked";
                  			}
                  		}
                  	}
                  	
            %>	<div class="col-sm-2">
	                 <label class="checkbox">
	                      	 <input type="checkbox" id="activity<%out.print(i); %>" name="activity[]" value="<%out.print(activityCategoryItem.getId());%>" <% out.print(checked); %>><% out.print(activityCategoryItem.getName()); %>
	                </label>											
			  	</div>
                                       		    	
             <%}%>
              </div><!-- inline-checkboxes-holder -->
             </div><!-- col-sm-6 -->
           </div><!-- form group -->
	<%
		}
}
		
%>
</div>
</div>