<%@page import="org.school.admin.model.InfrastructureCategoryItem"%>
<%@page import="org.school.admin.model.SchoolInfrastructureCatItem"%>
<%@page import="org.school.admin.dao.SchoolDAOImp"%>
<%@page import="java.util.List"%>
<%
	 SchoolDAOImp schoolDAOImp2  = new SchoolDAOImp();
	 List<InfrastructureCategoryItem> infrastructureCategories = schoolDAOImp2.getInfrastructureCategoryItem();
	 List<SchoolInfrastructureCatItem> schoolInfrastructureCatItems = schoolDAOImp2.getSchoolInfrastructureCatItem(Integer.parseInt(request.getParameter("school_id")));
 
%>
<div class="panel panel-info">
<div class="panel-heading">
  <h3 class="panel-title" id="myModalLabel">Infrastructure</h3>
</div>
 <div class="panel-body">
 <div class ="form_group ">
 <div class = "alert alert-warning" style="padding-bottom:35px;" >
 <div class="col-sm-2">Facility</div>
 <div class="col-sm-1">Select</div>
 <div class="col-sm-2">is optional?</div>
 <div class="col-sm-2">No Of Items</div>
 <div class="col-sm-3">Charges</div>
 <div class="col-sm-2">Comment</div>
 </div>
 </div>
<%		int main_cat_id = 0;
      	for(int k=0;k<infrastructureCategories.size();k++) 	{ // for
      		InfrastructureCategoryItem infrastructureCategory = infrastructureCategories.get(k);
   				
   				String checked = "";
   				Byte isOptional = 0;
   				Integer countItemValue = 0;
   				String description = "";
   				Double charges = 0.0;
              	if(schoolInfrastructureCatItems.size() > 0){
              		for(int n=0; n < schoolInfrastructureCatItems.size(); n++){
              			if(schoolInfrastructureCatItems.get(n).getInfrastructureCategoryItem().getId() == infrastructureCategory.getId()){
              				checked = "checked";
              				isOptional = schoolInfrastructureCatItems.get(n).getIsOptional();
              				countItemValue = schoolInfrastructureCatItems.get(n).getCountItemValue();
              				description = schoolInfrastructureCatItems.get(n).getDescription();
              				charges = schoolInfrastructureCatItems.get(n).getCharges();
              			}
              		}
              	}
   			%>
   			<% 
	   			if(main_cat_id != infrastructureCategory.getInfrastructureCategory().getId()) {
	   				out.print("<span class='alert alert-info col-sm-12'>"+infrastructureCategory.getInfrastructureCategory().getName()+"</span>");
	   			}
   				main_cat_id = infrastructureCategory.getInfrastructureCategory().getId();
   			%>
   			<input type = "hidden" id = "optional<%out.print(infrastructureCategory.getId()); %>" name = "optional<%out.print(infrastructureCategory.getId()); %>" value = "<%out.print(infrastructureCategory.getIsOptional()); %>"/>
 			<div class="form-group">
       		 			<div class="col-sm-2"><%out.print(infrastructureCategory.getName()); %></div>
       		 			<div class="col-sm-1">
       		 			<input type="checkbox" name="infra_cat_id[]" id="chkinfra_<%out.print(infrastructureCategory.getId()); %>"
       		 			 value="<%out.print(infrastructureCategory.getId()); %>" 
       		 			 onclick="showOption('<%out.print(infrastructureCategory.getId()); %>')" <%out.print(checked); %>>   
       		 			
       		 			</div>
					    <div class="col-sm-2" >
					    	<% if(infrastructureCategory.getIsOptional() == 1){ %>
					    	
					      		<input type="radio" name="rdochoice_<%out.print(infrastructureCategory.getId()); %>" id="rdoOption_<%out.print(infrastructureCategory.getId()); %>" value="0" <% if(isOptional == 0){ %>checked<%} %>/>Optional
					       		<input type="radio" name="rdochoice_<%out.print(infrastructureCategory.getId()); %>" id="rdoOption_<%out.print(infrastructureCategory.getId()); %>" value="1" <% if(isOptional == 1){ %>checked<%} %>/>Compulsory
					       	<% }else{ %>
					       		<input type="radio" name="rdochoice_<%out.print(infrastructureCategory.getId()); %>" id="rdoOption_<%out.print(infrastructureCategory.getId()); %>" value="0" checked disabled/>Optional
					       		<input type="radio" name="rdochoice_<%out.print(infrastructureCategory.getId()); %>" id="rdoOption_<%out.print(infrastructureCategory.getId()); %>" value="1" disabled/>Compulsory
					       	<% } %>	
					     </div>
					    <div class="col-sm-2" >
            	 		 	<input type="text" name="noOfItems_<%out.print(infrastructureCategory.getId()); %>" id="noOfItems_<%out.print(infrastructureCategory.getId()); %>" value="<% if(countItemValue > 0){out.print(countItemValue);} %>" placeholder="<%out.print(infrastructureCategory.getCountItemName()); %>" size="15" maxlength="5"/>
            	 		</div>
       		 			<div class="col-sm-2" >
       		 				<% if(infrastructureCategory.getIsOptional() == 1){ %>
            	 		 		<input type="text" name="noOfCharges_<%out.print(infrastructureCategory.getId()); %>" id="noOfCharges_<%out.print(infrastructureCategory.getId()); %>" value="<% out.print(charges); %>" placeholder="Charges per month" size="15" maxlength="10"/>
            	 		 	<% } else { %>
            	 		 		<input type="hidden" name="noOfCharges_<%out.print(infrastructureCategory.getId()); %>" id="noOfCharges_<%out.print(infrastructureCategory.getId()); %>" value="0"/>0
            	 		 	<%} %>
            	 		</div>
       		 			
         				<div class="col-sm-2" id="divdesc_<%out.print(infrastructureCategory.getId()); %>" style="display:block">
         					<textarea id="desc_<%out.print(infrastructureCategory.getId()); %>"><% out.print(description); %></textarea> 
         				</div>
		</div> <!-- form-group -->
		<%
       } //end for		main cat
		%>
					
</div><!-- panel -body -->
</div><!-- panel info -->
