package org.school.admin.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
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
import org.school.admin.data.RatingReviewData;
import org.school.admin.data.RatingsReviewsData;
import org.school.admin.data.SchoolAddress;
import org.school.admin.data.SchoolAnalyticsData;
import org.school.admin.data.SchoolContact;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.data.VacantSeats;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.model.PrevStudentProfile;
import org.school.admin.model.School;
import org.school.admin.model.SchoolPanoramicImage;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolSuggestion;
import org.school.admin.model.UserRegistrationInfo;
import org.school.admin.service.SchoolService;
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
	@Path("basiclist.json/{id}/{standardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolList getBasicInfo(@PathParam("id") int id, @PathParam("standardId") int standardId)
	{
		img_path = this.context.getInitParameter("s3_base_url");
		List<SchoolList> basic = new SchoolSearchImpl().fetchSchoolListById(id,standardId);
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
	@Path("panorama.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolPanoramicImage> getSchoolPanorama(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<SchoolPanoramicImage> schoolPanoramicImages = schoolSearchImpl.getSchoolPanorama(id);
		img_path = this.context.getInitParameter("s3_base_url");
		for(int i = 0; i < schoolPanoramicImages.size(); i++){
			schoolPanoramicImages.get(i).setPanoImage(img_path+schoolPanoramicImages.get(i).getPanoImage());
		}
		return schoolPanoramicImages;
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
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<SchoolReview> reviews = schoolSearchImpl.getSchoolReviews(id);
		for(int i = 0; i < reviews.size(); i++){
			reviews.get(i).getUserRegistrationInfo().setImage(img_path+reviews.get(i).getUserRegistrationInfo().getImage());
		}
		return reviews;
	}
	
	@GET
	@Path("ratingsandreviews.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RatingsReviewsData> getSchoolRatingsAndReviews(@PathParam("id") int id)
	{
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<RatingsReviewsData> reviews = schoolSearchImpl.getSchoolRatingsAndReviews(id);
		for(int i = 0; i < reviews.size(); i++){
			reviews.get(i).setImage(img_path+reviews.get(i).getImage());
		}
		return reviews;
	}
	
	@GET
	@Path("ratingandreview.json/{schoolId}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public RatingReviewData getSchoolRatingAndReviewByUser(
		@PathParam("schoolId") int schoolId,
		@PathParam("userId") int userId
	) {
		img_path = this.context.getInitParameter("s3_base_url");
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		RatingReviewData reviews = schoolSearchImpl.getSchoolRatingAndReviewByUser(schoolId, userId);
		if(reviews != null ) {
			for(int i = 0; i < reviews.getRatings().size(); i++){
				if(reviews.getRatings().get(i).getImage() != null) {
					reviews.getRatings().get(i).setImage(img_path+reviews.getRatings().get(i).getImage());
				} else {
					reviews.getRatings().get(i).setImage("");
				}
			}
		}
		return reviews;
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
		for(int i=0; i<activity.size(); i++){
			for(int j=0; j<activity.get(i).getItems().size(); j++){
				activity.get(i).getItems().get(j).setImage(img_path+activity.get(i).getItems().get(j).getImage());
			}
		}
		for(int i=0; i<safety.size(); i++){
			for(int j=0; j<safety.get(i).getItems().size(); j++){
				safety.get(i).getItems().get(j).setImage(img_path+safety.get(i).getItems().get(j).getImage());
			}
		}
		for(int i=0; i<infra.size(); i++){
			for(int j=0; j<infra.get(i).getItems().size(); j++){
				infra.get(i).getItems().get(j).setImage(img_path+infra.get(i).getItems().get(j).getImage());
			}
		}
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
		for(int i=0; i<activity.size(); i++){
			for(int j=0; j<activity.get(i).getItems().size(); j++){
				activity.get(i).getItems().get(j).setImage(img_path+activity.get(i).getItems().get(j).getImage());
			}
		}
		return activity;
	}
	
	@GET
	@Path("safety.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InfraCategory> getSchoolSafety(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> safety = schoolSearchImpl.getSchoolSafety(id);
		for(int i=0; i<safety.size(); i++){
			for(int j=0; j<safety.get(i).getItems().size(); j++){
				safety.get(i).getItems().get(j).setImage(img_path+safety.get(i).getItems().get(j).getImage());
			}
		}
		return safety;
	}
	
	@GET
	@Path("infra.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InfraCategory> getSchoolInfra(@PathParam("id") int id)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<InfraCategory> infra = schoolSearchImpl.getSchoolInfra(id);
		for(int i=0; i<infra.size(); i++){
			for(int j=0; j<infra.get(i).getItems().size(); j++){
				infra.get(i).getItems().get(j).setImage(img_path+infra.get(i).getItems().get(j).getImage());
			}
		}
		return infra;
	}
	
	@POST
	@Path("suggest.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage addSuggestion(
			@FormParam("personName") String personName,
			@FormParam("mobile") String mobile,
			@FormParam("requirement") String requirement
	) {
		SchoolSuggestionService schoolSuggestionService = new SchoolSuggestionService();
		SchoolSuggestion schoolSuggestion = new SchoolSuggestion(personName, mobile, requirement, 0);
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
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage getSchoolReview(
			@FormParam("schoolId") int schoolId,
			@FormParam("userId") int userId,
			@FormParam("title") String title,
			@FormParam("review") String review,
			@FormParam("id") @DefaultValue("") Integer id
	){
		SchoolReview schoolReview = new SchoolReview();
		School school = new School();
		school.setId(schoolId);
		UserRegistrationInfo userRegistrationInfo = new UserRegistrationInfo();
		userRegistrationInfo.setId(userId);
		schoolReview.setSchool(school);
		schoolReview.setUserRegistrationInfo(userRegistrationInfo);
		schoolReview.setId(id);
		schoolReview.setTitle(title);
		schoolReview.setReview(review);
		Byte reviewStatus =0;
		schoolReview.setDate(new Date());
		schoolReview.setTime(new Date());
		schoolReview.setStatus(reviewStatus);
		return new SchoolDAOImp().saveSchoolReview(schoolReview);
	}
	
	@GET
	@Path("vacantseats.json/{schoolId}/{standardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VacantSeats> getVacantSeats(@PathParam("schoolId") Integer schoolId, @PathParam("standardId") Short standardId)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getVacantSeatsBySchoolIdByStandardId(schoolId, standardId);
	}
	
	@GET
	@Path("contactclicks.json/{schoolId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolAnalyticsData> getContactClicks(@PathParam("schoolId") Integer schoolId)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getContactClicksBySchoolId(schoolId);
	}
	
	@POST
	@Path("contactclicked.json")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage updateContactClicks(@FormParam("schoolId") Integer schoolId)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.updateContactClicksBySchoolId(schoolId);
	}
	
	@GET
	@Path("schoolachievements.json/{schoolId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PrevStudentProfile> getSchoolAchievements(@PathParam("schoolId") Integer schoolId)
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getSchoolAchievmentsBySchoolId(schoolId);
	}
	
	@POST
	@Path("ratereview.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseMessage addOrUpdateSchoolRatingAndReview(RatingReviewData ratingReviewData){
		SchoolService schoolService = new SchoolService();
		return schoolService.addOrUpdateSchoolRatingAndReview(ratingReviewData);
	}
}
