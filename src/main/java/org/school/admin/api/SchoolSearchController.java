package org.school.admin.api;

import java.util.ArrayList;
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
import javax.ws.rs.core.UriInfo;

import org.school.admin.dao.CityNamesImp;
import org.school.admin.dao.ClassificationDAOImpl;
import org.school.admin.dao.ContactDetaillDAO;
import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.dao.StandardTypeDAO;
import org.school.admin.data.Facility;
import org.school.admin.data.GalleryData;
import org.school.admin.data.InfraCategory;
import org.school.admin.data.NameImageList;
import org.school.admin.data.NameList;
import org.school.admin.data.NearbySchoolList;
import org.school.admin.data.Rating;
import org.school.admin.data.RatingsReviewsData;
import org.school.admin.data.SchoolCompleteDetail;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolListingRequest;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.data.SearchRequest;
import org.school.admin.model.SchoolPanoramicImage;
import org.school.admin.model.SchoolReview;
import org.school.admin.exception.ResponseMessage;
import org.school.admin.service.SearchFilterService;
@Path("api1.0")
public class SchoolSearchController {
	@Context ServletContext context;
	
	@POST
	@Path("/schoollist.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SchoolSearchResult SearchSchool(SchoolListingRequest request)
	{
		SearchFilterService sfService = new SearchFilterService();
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		SchoolSearchResult result = schoolSearchImpl.getSearchList(request);
		result.setActivityFilter(sfService.getUserActivityFilter(request.getActivityFilter()));
		result.setSafetyFilter(sfService.getUserSafetyFilter(request.getSafetyFilter()));
		result.setInfraFilter(sfService.getUserInfraFilter(request.getInfraFilter()));
		result.setBoardFilter(sfService.getUserBoardFilter(request.getBoardFilter()));
		result.setMediumFilter(sfService.getUserMediumFilter(request.getMediumFilter()));
		result.setTypeFilter(sfService.getUserSchoolTypeFilter(request.getTypeFilter()));
		result.setCategoryFilter(sfService.getUserSchoolCategoryFilter(request.getCategoryFilter()));
		result.setSortFields(sfService.getUserSortFields(request.getSortFields()));
		result.setClassificationFilter(sfService.getUserSchoolClassificationFilter(request.getClassificationFilter()));
		return result;
	}
	
	@GET
	@Path("/schoollist.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolList> SchoolSearchList(@Context UriInfo uriInfo)
	{
		SearchFilterService sfService = new SearchFilterService();
		SearchRequest searchRequest = sfService.getSearchRequest(uriInfo);
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<SchoolList> schoolLists = schoolSearchImpl.fetchSchoolListByLattitudeByLongitude(searchRequest);
		String img_path = this.context.getInitParameter("s3_base_url");
		for(int i=0; i< schoolLists.size(); i++){
			if(schoolLists.get(i).getLogo() != "")
				schoolLists.get(i).setLogo(img_path+schoolLists.get(i).getLogo());
			if(schoolLists.get(i).getHomeImage() != "")
				schoolLists.get(i).setHomeImage(img_path+schoolLists.get(i).getHomeImage());
		}
		return schoolLists;
	}
	
	@GET
	@Path("/schoolfilter.json")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolSearchResult SchoolFilterList(@Context UriInfo uriInfo)
	{
		SearchFilterService sfService = new SearchFilterService();
		SearchRequest searchRequest = sfService.getSearchRequest(uriInfo);
		SchoolSearchResult result = new SchoolSearchResult();
//		result.setActivityFilter(sfService.getUserActivityFilter(searchRequest.getActivityId()));
//		result.setSafetyFilter(sfService.getUserSafetyFilter(searchRequest.getSafetyId()));
//		result.setInfraFilter(sfService.getUserInfraFilter(searchRequest.getInfraId()));
		result.setBoardFilter(sfService.getUserBoardFilter(searchRequest.getBoardId()));
		result.setTaFilter(sfService.getUserTAFilter(searchRequest.getTaId()));
		result.setMediumFilter(sfService.getUserMediumFilter(searchRequest.getMediumId()));
//		result.setTypeFilter(sfService.getUserSchoolTypeFilter(searchRequest.getTypeId()));
		result.setCategoryFilter(sfService.getUserSchoolCategoryFilter(searchRequest.getCategoryId()));
		result.setClassificationFilter(sfService.getUserSchoolClassificationFilter(searchRequest.getClassificationId()));
		result.setSortFields
			(
				sfService.getUserSortFields
				(
					searchRequest.getFee(),
					searchRequest.getRating(), 
					searchRequest.getDistance(),
					searchRequest.getSeats()
				)
			);
		return result;
	}
	
	@GET
	@Path("/standardlist.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getStandardList()
	{
		StandardTypeDAO standardTypeDAO = new StandardTypeDAO();
		return standardTypeDAO.getStandardList();
	}
	
	@GET
	@Path("/classificationlist.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameImageList> getClassificationList()
	{
		ClassificationDAOImpl classificationDAOImpl = new ClassificationDAOImpl();
		return classificationDAOImpl.getClassifications();
	}
	
	@GET
	@Path("/school.json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolCompleteDetail getSchoolInfo(
			@PathParam("id") int id,
			@DefaultValue("0") @QueryParam("standardId") Short standardId
	){
		String img_path = this.context.getInitParameter("s3_base_url");
		SchoolCompleteDetail result = new SchoolCompleteDetail();
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		SchoolDAOImp schoolDAOImp = new SchoolDAOImp();
		ContactDetaillDAO contactDetaillDAO = new ContactDetaillDAO();
		List<SchoolTimelineData> timelines = schoolDAOImp.getSchoolTimelineDetails(id);
		for(int i = 0; i < timelines.size(); i++){
			timelines.get(i).setImage(img_path+timelines.get(i).getImage());
		}
		List<GalleryData> galleryDatas = schoolSearchImpl.getImageGallary(id);
		for(int i = 0; i < galleryDatas.size(); i++){
			galleryDatas.get(i).setImageUrl(img_path+galleryDatas.get(i).getImageUrl());
		}
		List<SchoolPanoramicImage> schoolPanoramicImages = schoolSearchImpl.getSchoolPanorama(id);
		img_path = this.context.getInitParameter("s3_base_url");
		for(int i = 0; i < schoolPanoramicImages.size(); i++){
			schoolPanoramicImages.get(i).setPanoImage(img_path+schoolPanoramicImages.get(i).getPanoImage());
		}
		List<SchoolReview> reviews = schoolSearchImpl.getSchoolReviews(id);
		for(int i = 0; i < reviews.size(); i++){
			reviews.get(i).getUserRegistrationInfo().setImage(img_path+reviews.get(i).getUserRegistrationInfo().getImage());
		}
		result.setHighlights(schoolDAOImp.getSchoolHighlightList(id));
		//result.setReviews(reviews);
		result.setContacts(contactDetaillDAO.getExternalConatctDetail(id));
		result.setSchoolTimelineData(timelines);
		result.setImages(galleryDatas);
		result.setPanorama(schoolPanoramicImages);
		result.setFees(schoolSearchImpl.getClassFeeDetails(id,standardId));
		Facility facility = new Facility();
		List<InfraCategory> schoolActivity = schoolSearchImpl.getSchoolActivity(id);
		for(int i=0; i<schoolActivity.size(); i++){
			for(int j=0; j<schoolActivity.get(i).getItems().size(); j++){
				schoolActivity.get(i).getItems().get(j).setImage(img_path+schoolActivity.get(i).getItems().get(j).getImage());
			}
		}
		List<InfraCategory> schoolSafety = schoolSearchImpl.getSchoolSafety(id);
		for(int i=0; i<schoolSafety.size(); i++){
			for(int j=0; j<schoolSafety.get(i).getItems().size(); j++){
				schoolSafety.get(i).getItems().get(j).setImage(img_path+schoolSafety.get(i).getItems().get(j).getImage());
			}
		}
		List<InfraCategory> schoolInfra = schoolSearchImpl.getSchoolInfra(id);
		for(int i=0; i<schoolInfra.size(); i++){
			for(int j=0; j<schoolInfra.get(i).getItems().size(); j++){
				schoolInfra.get(i).getItems().get(j).setImage(img_path+schoolInfra.get(i).getItems().get(j).getImage());
			}
		}
		facility.setActivity(schoolActivity);
		facility.setSafety(schoolSafety);
		facility.setInfra(schoolInfra);
		result.setFacility(facility);
		
		List<Rating> ratings = schoolSearchImpl.getSchoolRating(id);
		for(int i=0; i<ratings.size(); i++){
			ratings.get(i).setImage(img_path+ratings.get(i).getImage());
		}
		result.setRating(ratings);
		result.setSchoolAchievements(schoolSearchImpl.getSchoolAchievmentsBySchoolId(id));
		
		List<RatingsReviewsData> ratingsReviewsData = schoolSearchImpl.getSchoolRatingsAndReviews(id);
		for(int i = 0; i < ratingsReviewsData.size(); i++){
			ratingsReviewsData.get(i).setImage(img_path+ratingsReviewsData.get(i).getImage());
		}
		result.setRatingsAndReviews(ratingsReviewsData);
		
		return result;
	}
	
	@GET
	@Path("/bloodgroup.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getBloodGroup(){
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getBloodGroupList();
	}
	
	@GET
	@Path("/castelist.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getCasteList(){
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getCastList();
	}
	
	@GET
	@Path("/nearbyschools.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NearbySchoolList> getNearbySchools(@Context UriInfo uriInfo)
	{
		SearchFilterService sfService = new SearchFilterService();
		SearchRequest searchRequest = sfService.getSearchRequest(uriInfo);
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<NearbySchoolList> nearbySchoolLists = schoolSearchImpl.getNearbySchoolByLatitudeByLogitude(searchRequest);
		String img_path = this.context.getInitParameter("s3_base_url");
		for(int i = 0; i < nearbySchoolLists.size(); i++){
			nearbySchoolLists.get(i).setHomeImage(img_path+nearbySchoolLists.get(i).getHomeImage());
		}
		return nearbySchoolLists;
	}
	
	@GET
	@Path("/topschools.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolList> getTopSchools()
	{
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<SchoolList> schoolLists =  schoolSearchImpl.getTopSchools();
		String img_path = this.context.getInitParameter("s3_base_url");
		for(int i = 0; i < schoolLists.size(); i++){
			schoolLists.get(i).setHomeImage(img_path+schoolLists.get(i).getHomeImage());
		}
		return schoolLists;
	}
	
	@GET
	@Path("/geturi.json/{latitude}/{longitude}/{standardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMessage getUri(
			@PathParam("latitude") String latitude,
			@PathParam("longitude") String longitude,
			@PathParam("standardId") Short standardId) {
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		return schoolSearchImpl.getUriByLatitudeLongitudeByStandard(latitude, longitude, standardId);
	}
	
	@GET
	@Path("/cities.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getCityList()
	{
		CityNamesImp cityDao = new CityNamesImp();
		return cityDao.getCityList();
	}
	
	@GET
	@Path("/compareschools.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SchoolList> compareSchools(
			@QueryParam("schoolIds") String schoolIds,
			@QueryParam("latitude") @DefaultValue("") String latitude,
			@QueryParam("longitude") @DefaultValue("") String longitude
	) {
		String img_path = this.context.getInitParameter("s3_base_url");
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		List<Integer> integerSchoolIds = new ArrayList<Integer>();
		try{
			for(String s : schoolIds.split(",")) integerSchoolIds.add(Integer.valueOf(s.trim()));
			if(integerSchoolIds.size() < 2 || integerSchoolIds.size() > 4 ) {
				return null;
			}
		} catch(NumberFormatException e) {
			return null;
		}
		List<SchoolList> schoolLists = schoolSearchImpl.compareSchools(integerSchoolIds, latitude, longitude);
		for(int i=0; i< schoolLists.size(); i++){
			if(schoolLists.get(i).getLogo() != "")
				schoolLists.get(i).setLogo(img_path+schoolLists.get(i).getLogo());
			if(schoolLists.get(i).getHomeImage() != "")
				schoolLists.get(i).setHomeImage(img_path+schoolLists.get(i).getHomeImage());
		}
		return schoolLists;
	}
	
}
