<%@page import="org.school.admin.service.CategoryService"%>
<%@page import="org.school.admin.model.SchoolCategoryType"%>
<%@page import="org.school.admin.service.ClassificationService"%>
<%@page import="org.school.admin.model.SchoolClassificationType"%>
<%@page import="org.school.admin.service.MediumService"%>
<%@page import="org.school.admin.model.MediumType"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="org.school.admin.service.BoardService"%>
<%@page import="org.school.admin.model.BoardType"%>
<%@page import="org.school.admin.service.SchoolTypeService"%>
<%@page import="java.util.List"%>
<%@page import="org.school.admin.model.SchoolType"%>
<%@page import="org.school.admin.model.SchoolInfo"%>
<%@page import="org.school.admin.model.SchoolBoard"%>
<%@page import="org.school.admin.model.SchoolMedium"%>
<%@page import="org.school.admin.dao.SchoolDetailDAOImpl"%>
<%@page import="org.school.admin.model.AdminUser"%>

<%
	int school_id = Integer.parseInt(request.getParameter("school_id"));
	SchoolDetailDAOImpl schoolDetailDAOImpl = new SchoolDetailDAOImpl();
	List<SchoolInfo> info_list =null;
	 info_list = schoolDetailDAOImpl.getSchoolInfo(school_id);
	SchoolInfo schoolInfo = null;
	List<SchoolBoard> schoolBoard = null;
	List<SchoolMedium> schoolMedium = null;
	List<SchoolType> schoolTypeList = null;
	List<BoardType> boardTypeList = null;
	List<MediumType> mediumTypeList = null;
	List<SchoolClassificationType> classificationList = null;
	List<SchoolCategoryType> categoryList = null;
	if(info_list.size() > 0){
		schoolInfo = info_list.get(0);
		schoolBoard = schoolDetailDAOImpl.getSchoolBoard(school_id);
		schoolMedium = schoolDetailDAOImpl.getSchoolMedium(school_id);
	}
	
		
	schoolTypeList = new SchoolTypeService().getSchoolTypeList();
	boardTypeList = new BoardService().getBoardList();
	mediumTypeList = new MediumService().getMediumList();
	classificationList = new ClassificationService().getClassificationList();
	categoryList = new CategoryService().getCategoryList();
%>
<% 
 	System.out.print("School id : "+school_id);
     session = request.getSession(false);
     AdminUser registration1 = new AdminUser();
     int user_id = 0;
     	if(session!=null)
     	{
     		System.out.print("session is not null..");
     		if(session.getAttribute("uname") != null)
     		{
     				System.out.print("session attribute is not null..");
     				registration1  = (AdminUser)session.getAttribute("uname");
       				//out.print(registration.getName());
       				System.out.println();
       				System.out.println("user id : "+registration1.getId());
       				user_id = registration1.getId();
       				System.out.println();
     		}
        }	
 %>
				 <form action="webapi/test/testschool" method="post" id="school-detail" class="form-horizontal"> 
				 <div id="error-school-detail" class="has-error"></div>
					  <div class="form-group">
                          <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="enter school website">School website *</label>
                          <div class="col-sm-6">
                              <input data-brackets-id="3402" type="text" class="form-control" id="school_website" placeholder="school website" value="<%if(info_list.size() > 0){out.print(schoolInfo.getSchoolWebsite());}%>">
                          </div>
                      </div>
                      <div class="form-group">
                          <label for="" class="col-sm-2 control-label" data-toggle="tooltip" data-placement="bottom" title="Tooltip...">Classification *</label>
                          <div class="col-sm-4">
                                <select name="" id="classification" class="form-control">
                                  <option value='0'>Select classification</option>
                                  <%
                                   if(classificationList.size() > 0){
                            	for(int i=0; i < classificationList.size();i++)
                            	{
                            		String selected = "";
                            		if(info_list.size() > 0){
                            			try{
	                            			if(schoolInfo.getSchoolClassificationType().getId() == classificationList.get(i).getId()){
	                            				selected = "selected";
	                            			}else{
	                            				selected = "";
	                            			}
                            			} catch(Exception e) {
                            				
                            			}
                            		}
                            		SchoolClassificationType classification = classificationList.get(i);
                            		out.print("<option value='"+classification.getId()+"' "+selected+">"+classification.getName()+"</option>");
                            	}
                                   }
                            %>
                             
                     </select>     </div> 
                        <div class="col-sm-4">
                        <div class="tooltip custom-tool-tip right">
                            <div class="tooltip-arrow"></div>
                            <div class="tooltip-inner">
                               Eg:- k-12,Pre-school
                            </div>
                        </div>
                    </div>
                    
                </div>
                
                 
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Board Type *</label>
                    <div class="col-sm-6" id="cbk_board">
                    	<%
                    	 if(boardTypeList.size() > 0){
                       	for(int i=0;i<boardTypeList.size();i++){ 
                    	   	BoardType boardType = boardTypeList.get(i);
                    	   	String checked = "";
                    	   	if(info_list.size() > 0){
	                       		for(int j=0; j < schoolBoard.size(); j++){
	                       			if(boardType.getId() == schoolBoard.get(j).getBoardType().getId()){
	                       				checked = "checked";
	                       			}else{
	                       				checked = "";
	                       			}
	                       		}
                    	   	}
							out.print(" <label class='checkbox-inline'> <input type='radio' value='"+boardType.getId()+
										  "' id='board' name='board' "+checked+"> "+boardType.getBoardName()+"</label>"); 
                       	}
                    	 }
						%>
                      </div>
                </div>
                 <div class="form-group">
                    <label class="col-sm-2 control-label">School Management *</label>
                    <div class="col-sm-4">
                        <select id="school_type" class="form-control">
                        <option value='0'>Select school management</option>
                            <%
                                if(schoolTypeList.size()>0){
                            	for(int i=0; i < schoolTypeList.size();i++)
                            	{
                            		String selected = "";
                            		if(info_list.size() > 0){
                            			try{
	                            			if(schoolInfo.getSchoolType().getId() == schoolTypeList.get(i).getId()){
	                            				selected = "selected";
	                            			}else{
	                            				selected = "";
	                            			}
                            			} catch(Exception e){
                            				
                            			}
                            		}
                            		SchoolType schoolType = schoolTypeList.get(i);
                            		out.print("<option value='"+schoolType.getId()+"' "+selected+">"+schoolType.getName()+"</option>");
                            	}
                                }
                            %>
                        </select>
                    </div>
                    <div class="col-sm-4">
                        <div class="tooltip custom-tool-tip right">
                            <div class="tooltip-arrow"></div>
                            <div class="tooltip-inner">
                               Eg:- Private,Govt., Autonomous
                            </div>
                        </div>
                    </div>
                </div>
                
                 <div class="form-group">
                    <label class="col-sm-2 control-label">Type of School *</label>
                    <div class="col-sm-4">
                        <select id="school_category" class="form-control">
                            <option value='0'>Select type of school</option>
                            <%
                                if(categoryList.size() > 0){
                            	for(int i=0; i < categoryList.size();i++)
                            	{
                            		String selected = "";
                            		if(info_list.size() > 0){
                            			try {
	                            			if(schoolInfo.getSchoolCategoryType().getId() == categoryList.get(i).getId()){
	                            				selected = "selected";
	                            			}else{
	                            				selected = "";
	                            			}
                            			} catch(Exception e){
                            				
                            			}
                            		}
                            		SchoolCategoryType category = categoryList.get(i);
                            		out.print("<option value='"+category.getId()+"' "+selected+">"+category.getName()+"</option>");
                            	}
                                }
                            
                            %>
                        </select>
                    </div>
                    <div class="col-sm-4">
                        <div class="tooltip custom-tool-tip right">
                            <div class="tooltip-arrow"></div>
                            <div class="tooltip-inner">
                               Eg:- Boys,Girls,Co-Ed.
                            </div>
                        </div>
                    </div>
                </div>
                
              
                
                  <div class="form-group">
                    <label class="col-sm-2 control-label">Medium of instruction *</label>
                    <div class="col-sm-8" id="cbk_medium">
                    <%
                    if(mediumTypeList.size() > 0){
                       	for(int i=0;i<mediumTypeList.size();i++){ 
                    	   	MediumType mediumType = mediumTypeList.get(i);
                    	   	String checked = "";
                    	   	if(info_list.size() > 0){
	                       		for(int j=0; j < schoolMedium.size(); j++){
	                       			if(mediumType.getId() == schoolMedium.get(j).getMediumType().getId()){
	                       				checked = "checked";
	                       			} 
	                       		}
                    	   	}
							out.print(" <label class='checkbox-inline'> <input type='checkbox' value='"+mediumType.getId()+
									  "' id='medium' name='medium[]' "+checked+"> "+mediumType.getTitle()+"</label>"); 
						}
                    }
					%>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">is residential *</label>
                    <div class="col-sm-6" id="cbk_board">
                    <%
                       if(info_list.size()>0){
                    	if(schoolInfo.getIsResidential() == 1)
                    	{
                    %>
                     <label class='checkbox-inline'> <input type='radio' value='1' id='residential' name='residential' checked>Yes</label> 
                          <label class='checkbox-inline'> <input type='radio' value='0' id='residential' name='residential' >No</label>
                          <%
                    	}
                    	else if(schoolInfo.getIsResidential() == 0)
                    	{
                          %>
                          
                    	 <label class='checkbox-inline'> <input type='radio' value='1' id='residential' name='residential'>Yes</label> 
                          <label class='checkbox-inline'> <input type='radio' value='0' id='residential' name='residential' checked>No</label>
                          <%
                    	}
                       }
                    	else
                    	{
                    		%>
                    	  <label class='checkbox-inline'> <input type='radio' value='1' id='residential' name='residential'>Yes</label> 
                          <label class='checkbox-inline'> <input type='radio' value='0' id='residential' name='residential' checked>No</label>
                    	<%}
                          %>
                      </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">display fee *</label>
                    <div class="col-sm-6" id="cbk_board">
                    <% if(info_list.size() > 0){ %>
                    	 <label class='checkbox-inline'> <input type='radio' value='1' id='display_fee' name='display_fee' <% if( schoolInfo.getDisplayFee() == 1){%>checked<%} %>>Yes</label>
                    	  <label class='checkbox-inline'> <input type='radio' value='0' id='display_fee' name='display_fee' <% if( schoolInfo.getDisplayFee() == 0){%>checked<%} %>>No</label>
                     <% } else { %> 
                    	 <label class='checkbox-inline'> <input type='radio' value='1' id='display_fee' name='display_fee' checked>Yes</label>
                    	 <label class='checkbox-inline'> <input type='radio' value='0' id='display_fee' name='display_fee'>No</label>
                   	 <% } %>
                      </div>
                </div>
                <input type="hidden" name="school_id" id="school_id" value="<%out.print(school_id);%>"/>
                <input type="hidden"  value="<%out.print(user_id); %>" id="updated_by"/>
                <input type ="hidden" value="schoolDetail" id="schoolDetail"/>
                <div class="form-group">
                    <div class="col-sm-2 col-sm-offset-2">
                        <button type="button" id="saveschooldetail" class="btn btn-success">Save</button>
                    </div>
                </div>
               </form>     
 <script type="text/javascript"> 
	$("#saveschooldetail").click(function( 
		){ 
		
		var isBoard = $("#board").is(":checked");
		var boardId = " ";
			//$("input:radio[name=board]").click(function() {
		     boardId = $("input:radio[name=board]:checked").val();
		//});
			if(typeof boardId == "undefined")
				var board = parseInt("0");
		var medium = null; 
     	 $('input[name="medium[]"]:checked').each(function() { 
     		 if(medium != null) 
    		 		medium = medium+","+$(this).val(); 
     		 else 
     			medium = $(this).val(); 
     	}) 
     
    		
     	if(($("#school_website").val() == "" || $.trim($("#school_website").val()).length == 0)
     		&& $("#classification").val() == 0
     		&& $('#school_type').val() == 0
     		&& $('#school_category').val() == 0
     		&& board == 0 
     		&& medium == null
     		)
     		{
     		  alert("All Fields are Mendatory");
     		}
     	else if(($("#school_website").val() == "" || $.trim($("#school_website").val()).length == 0)
         		&& $('#school_type').val() == 0
         		&& $('#school_category').val() == 0
         		&& board == 0 
         		&& medium == null
         		)
         		{
         		 
         		  alert("Please enter school website, select board type, school management, type of school and medium of instruction");
         		}
     	else if(($("#school_website").val() == "" || $.trim($("#school_website").val()).length == 0)
         		&& $("#classification").val() == 0
         		&& $('#school_category').val() == 0
         		&& board == 0 
         		&& medium == null
         		)
         		{
     		       alert("Please enter school website, select classification, board type, type of school and medium of instruction");
         		}
     	else if(($("#school_website").val() == "" || $.trim($("#school_website").val()).length == 0)
         		&& $("#classification").val() == 0
         		&& $('#school_type').val() == 0
         		&& $('#school_category').val() == 0
         		&& board == 0 
         		)
         		{
         		 
     		          alert("Please enter school website, select classification,board type, school management and type of school");
         		}
     	else if($("#classification").val() == 0
         		&& $('#school_type').val() == 0
         		&& $('#school_category').val() == 0
         		&& board == 0 
         		&& medium == null
         		)
         		{
         		 
     				 alert("Please select classification, board type, school management, type of school and medium of instruction");
         		}
     	else if(($("#school_website").val() == "" || $.trim($("#school_website").val()).length == 0))
     		{
     		  alert("Please enter school website");
     		}
     	else if( $("#classification").val() == 0)
     		{
     		  alert("Please select school classification.");
     		}
     	else if(board == 0)
		{
		 alert("Please select board name");
		}
     	else if(medium == null)
		{
		 alert("Please select medium of instruction");
		}
     
     	else if($("#school_type").val() == 0)
     		{
     			alert("Please select school management");
     		}
     	else if($("#school_category").val() == 0)
 		{
 			alert("Please select type of school");
 		}
     	else
     		{
   	 		$.post("webapi/test/testschool",{school_id : $('#school_id').val(),	
   	 			updated_by : $('#updated_by').val(),
   	 			board : boardId,
   				school_website : $('#school_website').val(), 
   				classification : $('#classification').val(), 
   				school_type : $('#school_type').val(),
   				medium : medium, 
   				residential : $('input[name=residential]:checked').val(),
   				display_fee : $('input[name=display_fee]:checked').val(),
   				school_category : $('#school_category').val()},function(data)
  	 			{ 
   					if(data.status ==1)
   					{
   	 					alert(data.message); 
   	 					updateProgress($('#school_id').val());
  	 			
// 			   	 		$("#school_website").val("");
// 			     		 $("#classification").val("");
// 			     		 $('#school_type').val("");
// 			     		 $('#school_category').val("");
			     		
			   	 			
// 			   	 		$('input[name="medium[]"]').prop('checked',false);
// 			   			$('input[name=board]').prop('checked', false);
// 			   			$("input[name=display_fee][value=1]").prop("checked",true);
// 			   			$("input[name=residential][value=0]").prop("checked",true);
  	 				}
   					else{
   						alert(data.message);
   					}
  	 			}
   			); 
     		}
    	}); 
 </script> 