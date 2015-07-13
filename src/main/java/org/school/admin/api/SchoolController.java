package org.school.admin.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.data.SchoolCompleteDetail;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.School;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolTimeline;
import org.school.admin.model.SchoolTimelineMilestone;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.service.SchoolService;

@Path("schoolDetails/")
public class SchoolController {
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public  SchoolCompleteDetail fetchSchoolCompleteDetails(@PathParam("id") int id) {
		SchoolService schoolHome = new SchoolService();
		return schoolHome.getSchoolCompleteDetails(id);
	}
	@POST
	@Path("review/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage getSchoolReview(SchoolReview schoolReview)
//	public ResponseMessage getSchoolReview(@QueryParam("schoolId") String strSchoolId,
//			@QueryParam("userId") String strUserId,
//			@QueryParam("title") String title,
//			@QueryParam("review") String review
//			)
	{
		//MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		
		
		//String strSchoolId = params.getFirst("schoolId");
	//	String strUserId = params.getFirst("userId");
		//String title = params.getFirst("title");
		//String review = params.getFirst("review");
		//System.out.println("SchoolId : "+strSchoolId+" userId : "+strUserId+" title : "+title+" review : "+review);
	
		       
				    Byte reviewStatus =0;
//					SchoolReview schoolReview = new SchoolReview();
//					School school = new School();
//					
					schoolReview.setDate(new Date());
					schoolReview.setTime(new Date());
//					
//					try
//					{
//						
//						school.setId(Integer.parseInt(strSchoolId));
//					}
//					 catch(NumberFormatException e)
//			        {
//						 schoolReview.setReview(review);
//							schoolReview.setTitle(title);
//							schoolReview.setStatus(reviewStatus);
//							return new SchoolDAOImp().saveSchoolReview(schoolReview);
//
//			        }
//					schoolReview.setSchool(school);
//					try
//					{
//						UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
//						userRegistrationInfo.setId(Integer.parseInt(strUserId));
//						schoolReview.setUserRegistrationInfo(userRegistrationInfo);
//						
//					}
//					catch(NumberFormatException e)
//					{
//						schoolReview.setReview(review);
//						schoolReview.setTitle(title);
//						schoolReview.setStatus(reviewStatus);
//						return new SchoolDAOImp().saveSchoolReview(schoolReview);
//					}
//					schoolReview.setReview(review);
//					schoolReview.setTitle(title);
					schoolReview.setStatus(reviewStatus);
					return new SchoolDAOImp().saveSchoolReview(schoolReview);
	}
}
