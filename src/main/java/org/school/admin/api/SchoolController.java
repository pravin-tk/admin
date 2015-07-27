package org.school.admin.api;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.school.admin.dao.ClassDetailDAO;
import org.school.admin.dao.ContactDetaillDAO;
import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.data.ClassInfoData;
import org.school.admin.data.Facility;
import org.school.admin.data.FeeDetail;
import org.school.admin.data.GalleryData;
import org.school.admin.data.InfraCategory;
import org.school.admin.data.NameList;
import org.school.admin.data.Rating;
import org.school.admin.data.RatingData;
import org.school.admin.data.SchoolAddress;
import org.school.admin.data.SchoolContact;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolSuggestion;
import org.school.admin.service.SchoolSuggestionService;

@Path("api1.0/school/")
public class SchoolController extends ResourceConfig {
	@Context ServletContext context;
	public SchoolController() {
		register(MultiPartFeature.class);
    }
	String img_path;
	
	@GET
	@Path("classInfo.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClassInfoData> getClassInfoDetails(@Context UriInfo uriInfo)
	{
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String schoolId = params.getFirst("schoolId");
		String stdId = params.getFirst("stdId");
		try
		{
					if(schoolId !=null && stdId !=null){
				return new ClassDetailDAO().getClassDetail(Integer.parseInt(schoolId),Short.parseShort(stdId));
				}else if(schoolId !=null)
					return new ClassDetailDAO().getClassDetail(Integer.parseInt(schoolId));
				else
					return null;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	@GET
	@Path("basic.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolAddress getBasicInfo(@PathParam("id") int id)
	{
		img_path = this.context.getInitParameter("s3_base_url");
		List<SchoolAddress> basic = new ClassDetailDAO().getSchoolBasicInfo(id);
		if(basic.size() > 0){
			if(basic.get(0).getHomeImage() != null)
				basic.get(0).setHomeImage(img_path+basic.get(0).getHomeImage());
			else
				basic.get(0).setHomeImage("");
			if(basic.get(0).getLogo() != null)
				basic.get(0).setLogo(img_path+basic.get(0).getLogo());
			else
				basic.get(0).setLogo("");
			return basic.get(0);
		}else{
			return null;
		}
	}
	
	@GET
	@Path("contact.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolContact getSchoolContact(@PathParam("id") int id){
		ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
		return contactDetaillDAO.getExternalConatctDetail(id);
	}
	
	@GET
	@Path("gallery.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GalleryData> getImageGallary(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<GalleryData> galleryDatas = schoolSearchImpl.getImageGallary(id);
		img_path = this.context.getInitParameter("s3_base_url");
		for(int i = 0; i < galleryDatas.size(); i++){
			galleryDatas.get(i).setImageUrl(img_path+galleryDatas.get(i).getImageUrl());
		}
		return galleryDatas;
	}
	
	@GET
	@Path("highlight.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getSchoolHighlightInfo(@PathParam("id") int id)
	{
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		return schoolDAOImp.getSchoolHighlightList(id);
	}
	
	@GET
	@Path("fee.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FeeDetail> getClassFeeDetails(
			@PathParam("id") int id,
			@DefaultValue("0") @QueryParam("standardId") Short standardId
	){
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getClassFeeDetails(id,standardId);
	}
	
	@GET
	@Path("timeline.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolTimelineData> getSchoolTimeLine(@PathParam("id") int id){
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		List<SchoolTimelineData> timelines = schoolDAOImp.getSchoolTimelineDetails(id);
		for(int i = 0; i < timelines.size(); i++){
			timelines.get(i).setImage(img_path+timelines.get(i).getImage());
		}
		return timelines;
	}
	
	@GET
	@Path("reviews.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolReview> getSchoolReviews(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getSchoolReviews(id);
	}
	
	@GET
	@Path("facility.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Facility getSchoolFacility(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> activity = schoolSearchImpl.getSchoolActivity(id);
		List<InfraCategory> safety = schoolSearchImpl.getSchoolSafety(id);
		List<InfraCategory> infra = schoolSearchImpl.getSchoolInfra(id);
		Facility facility = new Facility();
		facility.setActivity(activity);
		facility.setSafety(safety);
		facility.setInfra(infra);
		return facility;
	}
	
	@GET
	@Path("activity.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InfraCategory> getSchoolActivity(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> activity = schoolSearchImpl.getSchoolActivity(id);
		return activity;
	}
	
	@GET
	@Path("safety.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InfraCategory> getSchoolSafety(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> safety = schoolSearchImpl.getSchoolSafety(id);
		return safety;
	}
	
	@GET
	@Path("infra.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InfraCategory> getSchoolInfra(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> infra = schoolSearchImpl.getSchoolInfra(id);
		return infra;
	}
	
	@POST
	@Path("suggest.json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addSuggestion(SchoolSuggestion schoolSuggestion) {
		SchoolSuggestionService schoolSuggestionService = new SchoolSuggestionService();
		ResponseMessage responseMessage = schoolSuggestionService.addSchoolSuggestion(schoolSuggestion);
		return responseMessage;
	}
	
	@GET
	@Path("rating.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rating> getSchoolRating(@PathParam("id") int id){
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<Rating> ratings = schoolSearchImpl.getSchoolRating(id);
		for(int i=0; i<ratings.size(); i++){
			ratings.get(i).setImage(img_path+ratings.get(i).getImage());
		}
		return ratings;
	}
	
	@POST
	@Path("rate.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMessage addSchoolRating(RatingData ratingData){
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.addSchoolRating(ratingData);
	}
	
	@POST
	@Path("review.json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage getSchoolReview(SchoolReview schoolReview)
	{
	    Byte reviewStatus =0;
		schoolReview.setDate(new Date());
		schoolReview.setTime(new Date());
		schoolReview.setStatus(reviewStatus);
		return new SchoolDAOImp().saveSchoolReview(schoolReview);
	}
	
	
}
