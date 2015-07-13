<%@page import="org.school.admin.model.SafetyCategoryItem"%>
<%@page import="org.school.admin.model.SafetyCategory"%>
<%@page import="org.school.admin.model.SchoolSafetyCatItem"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%
	 SchoolDAOImp schoolDAOImp1  = new SchoolDAOImp();
	 List<SafetyCategory> safetyCategories = schoolDAOImp1.getSafetyCategory();
	 List<SchoolSafetyCatItem> schoolSafetyCatItems = schoolDAOImp1.getSchoolSafetyCatItem(Integer.parseInt(request.getParameter("school_id")));
 
%>
<div class="panel panel-info">
<div class="panel-heading">
  <h3 class="panel-title" id="myModalLabel">Safety features</h3>
  </div>
 <div class="panel-body">
<%
      	for(int k=0;k<safetyCategories.size();k++)
       	{
   			SafetyCategory safetyCategory = safetyCategories.get(k);
   			List<SafetyCategoryItem> safetyCategoryItems2 = schoolDAOImp1.getSafetyCategoryItem(safetyCategory.getId());
   			if(safetyCategoryItems2.size() > 0) {%>
 			<div class="form-group">
   				<label class="col-sm-2 control-label"><%out.print(safetyCategory.getName()); %></label>
 			<div class="col-sm-10">
       		 <div class="inline-checkboxes-holder">
   			<%	
              for(int i = 0; i < safetyCategoryItems2.size(); i++) {                                       			 
                  	SafetyCategoryItem safetyCategoryItem = safetyCategoryItems2.get(i);
                  	String checked = "";
                  	if(schoolSafetyCatItems.size() > 0){
                  		for(int n=0; n < schoolSafetyCatItems.size(); n++){
                  			if(schoolSafetyCatItems.get(n).getSafetyCategoryItem().getId() == safetyCategoryItem.getId()){
                  				checked = "checked";
                  			}
                  		}
                  	}
             %> 	
             	<div class="col-sm-4">
		                <label class="checkbox">
		                      	 <input type="checkbox" id="safety<%out.print(safetyCategoryItem.getId()); %>" name="safety[]" value="<%out.print(safetyCategoryItem.getId());%>" <% out.print(checked); %>><% out.print(safetyCategoryItem.getName()); %>
		                </label>											
			  	</div><!-- end col-sm-3 -->
                                       		    	
             <%
              }
              %>
              </div><!-- inline-checkboxes-holder -->
             </div><!-- col-sm-6 -->
           </div><!-- form group -->
	<%
		}
}
		
%>
</div>
</div>
