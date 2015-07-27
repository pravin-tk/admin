<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>
<%@include file="LeftNav.jsp" %>    
            <!-- Right main content -->
            <div class="col-sm-12 col-md-12  main">
                    <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                            <a href="#home" data-toggle="tab" >search</a>
                        </li>
	                     <li >
	                            <a href="#active" data-toggle="tab">active</a>
	                        </li>
	                     <li>
                            <a href="#pending" data-toggle="tab" >Pending</a>
                        </li>
                         <li>
                            <a href="#rejected" data-toggle="tab">Rejected</a>
                        </li>
                        <li>
                            <a href="#distribution" data-toggle="tab">Distribution</a>
                        </li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                      <!-- first tab content -->
                       <div class="tab-pane fade active in" id="home" aria-labelledby="contact-tab">
                           <h2>Search</h2>
                               <%@include file = "search-list/home.jsp" %>
                        </div>
                                                <!--end first tab content -->
                        <!-- sec tab content -->
                          <div class="tab-pane fade " id="active" aria-labelledby="country-tab">
                            <h2>active</h2>
                             <%@include file = "search-list/active.jsp" %>
                        </div>                    
                        <!-- end sec tab content -->
                        
                        <!-- third tab content -->     
                       <div class="tab-pane fade " id="pending" aria-labelledby="defaults-tab">
                             <h2>pending</h2>
                               <%@include file = "search-list/pending.jsp" %>
                             
                        </div>
                         
                        <!-- third tab content -->
                        
                         <!-- fourth tab content -->                         
                       <div class="tab-pane fade" id="rejected" aria-labelledby="school-acheivement-tab">
                           	<h2>Rejected</h2>
                           	  <%@include file = "search-list/rejected.jsp" %>
                       	</div>
                        <!-- fourth tab content -->
                        
                         <!-- sixth tab content --> 
                         <div class="tab-pane fade" id="distribution" aria-labelledby="country-tab">
                            <h2>Distribution</h2>
                              <%@include file = "search-list/distribution.jsp" %>
                        </div>
                                                
                       
                        <!-- sixth tab content -->
                 </div>
<!--                 </form> -->
            </div>
        
    <!-- /Right main content -->
   
  <%@include file="footer.jsp" %>  
<script type="text/javascript">

</script>