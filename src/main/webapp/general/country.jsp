<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="org.school.admin.dao.CountryDAOImp"%>
<%@page import="org.school.admin.model.Country"%>
<%@page import="java.util.List"%> 
<%@include file="../header.jsp" %>
<%@include file="../LeftNav.jsp" %>    
<%
CountryDAOImp countryService = new CountryDAOImp();
List<Country> listCountry = countryService.getCountryList();
%>               
            <div class="col-sm-12 col-md-12  main">
                
               <ol class="breadcrumb">
                    <li><a href="#">General</a>
                    </li>
                    <li><a href="#">Country</a>
                    </li>
                    <li class="active">Add</li>
                </ol> 
                <!-- <h2 class="sub-header">Section title</h2> -->
           

                 <!--    <ul id="myTab" class="nav nav-tabs">
                        <li><a href="#country" data-toggle="tab">Country</a>
                        </li>   </ul> -->

                    <div id="myTabContent" class="tab-content">
                     
                        <!--Contacts tab starts-->
                      <!--   <div class="tab-pane fade" id="country" aria-labelledby="contacts-tab"> -->
                            <div class="contacts-list">
                                <h2>List country</h2>
                                <p>Here you can add or deactivate countries. You can define country details, .</p>
                                <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Country</a>



                                <table class="table table-striped table-bordered" id="contacts-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Status</th>                                           
                                            <th class="alignRight">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <% 					
										int country_size = listCountry.size(); 
										for(int i=0; i < country_size; i++){ %>
											 <tr>
                                            	<td> <%out.print(listCountry.get(i).getId());%>   </td>
												<td> <%out.print(listCountry.get(i).getName());%>    </td>
												<td> 
												<%	if(listCountry.get(i).getStatus()== 1){
	                                        			out.print("<span class='label label-success'>Active</span>");
	                                        		} else {
												    	out.print("<span class='label label-warning'>Inactive</span>");
													} 
												%>  
											  </d>
											  	<td class="alignRight">
                             						<a href="javascript:editCountry(<% out.print(listCountry.get(i).getId()); %>);" class="btn btn-success icon-btn"><i class="fa fa-pencil"></i></a>
                                            	</td>
                                            </tr>
										
                                     <%} %>
                                    </tbody>
                                </table>

                                  <a href="#" class="btn btn-primary view-contacts bottom-margin"><i class="fa fa-plus"></i> Country</a>

                            </div>
                            <div class="contacts-new" style="display:none;">
                                <h4>Add New Country</h4>
                                <div id="error"></div>
     						<form method="post" action="" class="form-horizontal" id="submitForm">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Name</label>
                                    <div class="col-sm-2">
                                        <input type="text" id="txtname" class="form-control" placeholder="Croatia">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                This is the name of the country. 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Status</label>
                                    <div class="col-sm-2">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="1" checked="">Active
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="0">Inactive
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                If inactive country name will not appear in dropdowns
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Sort Order</label>
                                    <div class="col-sm-2">
                                        <input type="text" id="txtSort"  class="form-control" placeholder="1">
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="tooltip custom-tool-tip right">
                                            <div class="tooltip-arrow"></div>
                                            <div class="tooltip-inner">
                                                Sort order in list
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <button type="button" id='btnsave' class="btn btn-success">Save</button>
                                        <button class="btn btn-default list-id list-contacts" type="reset">Cancel</button>
                                    </div>
                                </div> </form>
                            </div>

                        </div>
                        <!--Contacts tab ends-->

                       
                 <!--    </div> -->

               
            </div>
       
     <script type="text/javascript">
    	$('#btnsave').click(function(){    		
    		$.post('../webapi/general/country/save',{name: $("#txtname").val(), status: $('input[name=status]:checked').val(), sortOrder: $("#txtSort").val()},function(data){
    			if(data.status == 1)
    				window.location.href = "${baseUrl}/general/country.jsp";
    			else
    				alert(data.message);
    		},'json');
    		
    	});
    	/* if(data.status == 1)
			window.location.href = "${baseUrl}/general/city.jsp?tehsil_id="+$("#tehsilId").val();
		else
			alert(data.message); */
	function editCountry(id){
		window.location.href = "${baseUrl}/general/editcountry.jsp?country_id="+id;
	}
    	</script>
    <%@include file="../footer.jsp" %>
   

 