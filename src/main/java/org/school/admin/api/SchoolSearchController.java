package org.school.admin.api;

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

import org.school.admin.dao.ClassificationDAOImpl;
import org.school.admin.dao.ContactDetaillDAO;
import org.school.admin.dao.SchoolDAOImp;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.dao.StandardTypeDAO;
import org.school.admin.data.Facility;
import org.school.admin.data.GalleryData;
import org.school.admin.data.NameImageList;
import org.school.admin.data.NameList;
import org.school.admin.data.SchoolCompleteDetail;
import org.school.admin.data.SchoolContact;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolListingRequest;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.data.SchoolTimelineData;
import org.school.admin.data.SearchRequest;
import org.school.admin.service.SearchFilterService;

import ch.qos.logback.classic.gaffer.GafferConfigurator;

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
		return schoolSearchImpl.fetchSchoolListByLattitudeByLongitude(searchRequest);
	}
	
	@GET
	@Path("/schoolfilter.json")
	@Produces(MediaType.APPLICATION_JSON)
	public SchoolSearchResult SchoolFilterList(@Context UriInfo uriInfo)
	{
		SearchFilterService sfService = new SearchFilterService();
		SearchRequest searchRequest = sfService.getSearchRequest(uriInfo);
		SchoolSearchResult result = new SchoolSearchResult();
		result.setActivityFilter(sfService.getUserActivityFilter(searchRequest.getActivityId()));
		result.setSafetyFilter(sfService.getUserSafetyFilter(searchRequest.getSafetyId()));
		result.setInfraFilter(sfService.getUserInfraFilter(searchRequest.getInfraId()));
		result.setBoardFilter(sfService.getUserBoardFilter(searchRequest.getBoardId()));
		result.setMediumFilter(sfService.getUserMediumFilter(searchRequest.getMediumId()));
		result.setTypeFilter(sfService.getUserSchoolTypeFilter(searchRequest.getTypeId()));
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
		result.setHighlights(schoolDAOImp.getSchoolHighlightList(id));
		result.setReviews(schoolSearchImpl.getSchoolReviews(id));
		result.setContacts(contactDetaillDAO.getExternalConatctDetail(id));
		result.setSchoolTimelineData(timelines);
		result.setImages(galleryDatas);
		result.setFees(schoolSearchImpl.getClassFeeDetails(id,standardId));
		Facility facility = new Facility();
		facility.setActivity(schoolSearchImpl.getSchoolActivity(id));
		facility.setSafety(schoolSearchImpl.getSchoolSafety(id));
		facility.setInfra(schoolSearchImpl.getSchoolInfra(id));
		result.setFacility(facility);
		return result;
	}
	
	
}
