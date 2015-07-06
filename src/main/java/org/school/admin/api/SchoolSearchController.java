package org.school.admin.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.validation.constraints.NotNull;

import org.hibernate.engine.spi.QueryParameters;
import org.school.admin.dao.FacilityImpl;
import org.school.admin.dao.SchoolSearchImpl;
import org.school.admin.dao.SettingsImpl;
import org.school.admin.dao.StandardTypeDAO;
import org.school.admin.data.MainFilter;
import org.school.admin.data.NameList;
import org.school.admin.data.SchoolList;
import org.school.admin.data.SchoolListingRequest;
import org.school.admin.data.SchoolSearchResult;
import org.school.admin.data.SearchFilter;
import org.school.admin.data.SearchRequest;
import org.school.admin.data.SearchSort;
import org.school.admin.model.ActivityCategoryItem;
import org.school.admin.model.BoardType;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.MediumType;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.model.School;
import org.school.admin.model.SchoolCategoryType;
import org.school.admin.model.SchoolSearch;
import org.school.admin.model.SchoolType;

@Path("api1.0")
public class SchoolSearchController {
	
	@POST
	@Path("/schoollist.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SchoolSearchResult SearchSchool(SchoolListingRequest request)
	{
		System.out.println("Request Json:"+request);
		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
		SchoolSearchResult result = schoolSearchImpl.getSearchList(request);
		result.setActivityFilter(getUserActivityFilter(request.getActivityFilter()));
		result.setSafetyFilter(getUserSafetyFilter(request.getSafetyFilter()));
		result.setInfraFilter(getUserInfraFilter(request.getInfraFilter()));
		result.setBoardFilter(getUserBoardFilter(request.getBoardFilter()));
		result.setMediumFilter(getUserMediumFilter(request.getMediumFilter()));
		result.setTypeFilter(getUserSchoolTypeFilter(request.getTypeFilter()));
		result.setCategoryFilter(getUserSchoolCategoryFilter(request.getCategoryFilter()));
		result.setSortFields(getUserSortFields(request.getSortFields()));
		return result;
	}
	
	@GET
	@Path("/schoollist.json")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchRequest SchoolSearchList(@Context UriInfo uriInfo)
	{
		SearchRequest searchRequest = new SearchRequest();
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		try {
			System.out.println("latitude:"+params.getFirst("latitude"));
		} catch(NullPointerException e) {
			//null pointers
		}
//		SchoolSearchImpl schoolSearchImpl = new SchoolSearchImpl();
//		SchoolSearchResult result = schoolSearchImpl.getSearchList(request);
//		result.setActivityFilter(getUserActivityFilter(request.getActivityFilter()));
//		result.setSafetyFilter(getUserSafetyFilter(request.getSafetyFilter()));
//		result.setInfraFilter(getUserInfraFilter(request.getInfraFilter()));
//		result.setBoardFilter(getUserBoardFilter(request.getBoardFilter()));
//		result.setMediumFilter(getUserMediumFilter(request.getMediumFilter()));
//		result.setTypeFilter(getUserSchoolTypeFilter(request.getTypeFilter()));
//		result.setCategoryFilter(getUserSchoolCategoryFilter(request.getCategoryFilter()));
//		result.setSortFields(getUserSortFields(request.getSortFields()));
		return searchRequest;
	}
	
	@GET
	@Path("/standardlist.json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameList> getStandardList()
	{
		StandardTypeDAO standardTypeDAO = new StandardTypeDAO();
		return standardTypeDAO.getStandardList();
	}
	
	/**
	 * Sort Parameters setup
	 * 
	 * @return List<SearchSort>
	 */
	public List<SearchSort> getSortFieldList()
	{
		List<SearchSort> searchSorts = new ArrayList<SearchSort>();
		SearchSort searchSort1 = new SearchSort();
		searchSort1.setName("classFee");
		searchSort1.setSortOrder("random");
		searchSorts.add(searchSort1);
		SearchSort searchSort2 = new SearchSort();
		searchSort2.setName("rating");
		searchSort2.setSortOrder("random");
		searchSorts.add(searchSort2);
		SearchSort searchSort3 = new SearchSort();
		searchSort3.setName("distance");
		searchSort3.setSortOrder("random");
		searchSorts.add(searchSort3);
		return searchSorts;
	}
	
	/**
	 * get User Sort Fields
	 * 
	 * @param requestSortFields
	 * @return List<SearchSort>
	 */
	public List<SearchSort> getUserSortFields(List<SearchSort> requestSortFields)
	{
		List<SearchSort> allSortFields = getSortFieldList();
		int sort_field_count = allSortFields.size();
		List<SearchSort> sortFields = new ArrayList<SearchSort>();
		int req_sort_count = 0;
		try {
			req_sort_count = requestSortFields.size();
		} catch(NullPointerException e) {
			//sort fields not found
		}
		for(int i = 0; i < sort_field_count; i++){
			SearchSort searchSort = new SearchSort();
			searchSort.setName(allSortFields.get(i).getName());
			searchSort.setSortOrder(allSortFields.get(i).getSortOrder());
			for(int j = 0; j < req_sort_count; j++){
				if(requestSortFields.get(j).getName().equalsIgnoreCase(allSortFields.get(i).getName())){
					searchSort.setSortOrder(requestSortFields.get(j).getSortOrder());
				}
			}
			sortFields.add(searchSort);
		}
		return sortFields;
	}
	
	/**
	 * Get user activity filters
	 * @param requestActivityFielter
	 * @return List<SearchFilter>
	 */
	public List<SearchFilter> getUserActivityFilter(List<SearchFilter> requestActivityFielter)
	{
		FacilityImpl facilityImpl = new FacilityImpl();
		List<ActivityCategoryItem> activityCategoryItems = facilityImpl.getactivityCategoryItemList();
		int activity_count = activityCategoryItems.size();
		List<SearchFilter> activityFilter = new ArrayList<SearchFilter>();
		int req_act_count = 0;
		try {
			req_act_count = requestActivityFielter.size();
		} catch(NullPointerException e) {
			//activity filters not found
		}
		for(int i=0; i<activity_count; i++){
			SearchFilter searchFilter = new SearchFilter();
			searchFilter.setId(activityCategoryItems.get(i).getId());
			searchFilter.setItemName(activityCategoryItems.get(i).getName());
			searchFilter.setCategoryName(activityCategoryItems.get(i).getActivityCategory().getName());
			searchFilter.setFiltered(false);
			if(req_act_count > 0){
				for(int j = 0; j < req_act_count; j++){
					if(requestActivityFielter.get(j).getId() == activityCategoryItems.get(i).getId()){
						searchFilter.setFiltered(true);
					}
				}
			}
			activityFilter.add(searchFilter);
		}
		return activityFilter;
	}
	
	/**
	 * Get user safety filters
	 * @param requestSafetyFilter
	 * @return List<SearchFilter>
	 */
	public List<SearchFilter> getUserSafetyFilter(List<SearchFilter> requestSafetyFilter)
	{
		FacilityImpl facilityImpl = new FacilityImpl();
		List<SafetyCategoryItem> safetyCategoryItems = facilityImpl.getSafetyCategoryItemList();
		int safety_count = safetyCategoryItems.size();
		List<SearchFilter> safetyFilter = new ArrayList<SearchFilter>();
		int req_safety_count = 0;
		try {
			req_safety_count = requestSafetyFilter.size();
		} catch(NullPointerException e) {
			//safety filters not found
		}
		for(int i=0; i<safety_count; i++){
			SearchFilter searchFilter = new SearchFilter();
			searchFilter.setId(safetyCategoryItems.get(i).getId());
			searchFilter.setItemName(safetyCategoryItems.get(i).getName());
			searchFilter.setCategoryName(safetyCategoryItems.get(i).getSafetyCategory().getName());
			searchFilter.setFiltered(false);
			if(req_safety_count > 0){
				for(int j = 0; j < req_safety_count; j++){
					if(requestSafetyFilter.get(j).getId() == safetyCategoryItems.get(i).getId()){
						searchFilter.setFiltered(true);
					}
				}
			}
			safetyFilter.add(searchFilter);
		}
		return safetyFilter;
	}
	
	/**
	 * Get User Infra Filters
	 * @param requestInfraFilter
	 * @return List<SearchFilter>
	 */
	public List<SearchFilter> getUserInfraFilter(List<SearchFilter> requestInfraFilter) 
	{
		FacilityImpl facilityImpl = new FacilityImpl();
		List<InfrastructureCategoryItem> infrastructureCategoryItems = facilityImpl.getInfrastructureCategoryItemList();
		int infra_count = infrastructureCategoryItems.size();
		List<SearchFilter> infraFilter = new ArrayList<SearchFilter>();
		int req_infra_count = 0;
		try {
			req_infra_count = requestInfraFilter.size();
		} catch(NullPointerException e) {
			//Infra filters not found
		}
		for(int i=0; i<infra_count; i++){
			SearchFilter searchFilter = new SearchFilter();
			searchFilter.setId(infrastructureCategoryItems.get(i).getId());
			searchFilter.setItemName(infrastructureCategoryItems.get(i).getName());
			searchFilter.setCategoryName(infrastructureCategoryItems.get(i).getInfrastructureCategory().getName());
			searchFilter.setFiltered(false);
			if (req_infra_count > 0) {
				for (int j = 0; j < req_infra_count; j++) {
					if (requestInfraFilter.get(j).getId() == infrastructureCategoryItems.get(i).getId()) {
						searchFilter.setFiltered(true);
					}
				}
			}
			infraFilter.add(searchFilter);
		}
		return infraFilter;
	}
	
	/**
	 * Get user board filters
	 * @param requestBoardFilter
	 * @return List<MainFilter>
	 */
	public List<MainFilter> getUserBoardFilter(List<MainFilter> requestBoardFilter) 
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<BoardType> boardTypes = settingsImpl.getBoardTypes();
		int board_count = boardTypes.size();
		List<MainFilter> boardFilter = new ArrayList<MainFilter>();
		int req_board_count = 0;
		try {
			req_board_count = requestBoardFilter.size();
		} catch(NullPointerException e) {
			//Board filters not found
		}
		for (int i = 0; i < board_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(boardTypes.get(i).getId());
			mainFilter.setName(boardTypes.get(i).getBoardName());
			mainFilter.setFiltered(false);
			for (int j = 0; j < req_board_count; j++) {
				if (requestBoardFilter.get(j).getId() == boardTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			boardFilter.add(mainFilter);
		}
		return boardFilter;
	}
	
	/**
	 * Get user school type filters
	 * @param requestTypeFilter
	 * @return List<MainFilter>
	 */
	public List<MainFilter> getUserSchoolTypeFilter(List<MainFilter> requestTypeFilter)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<SchoolType> schoolTypes = settingsImpl.getSchoolType();
		int type_count = schoolTypes.size();
		List<MainFilter> typeFilter = new ArrayList<MainFilter>();
		int req_type_count = 0;
		try {
			req_type_count = requestTypeFilter.size();
		} catch(NullPointerException e) {
			//Type filters not found
		}
		for (int i = 0; i < type_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(schoolTypes.get(i).getId());
			mainFilter.setName(schoolTypes.get(i).getName());
			mainFilter.setFiltered(false);
			for (int j = 0; j < req_type_count; j++) {
				if (requestTypeFilter.get(j).getId() == schoolTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			typeFilter.add(mainFilter);
		}
		return typeFilter;
	}
	
	/**
	 * Get user school category filter
	 * @param requestCategoryFilter
	 * @return List<MainFilter>
	 */
	public List<MainFilter> getUserSchoolCategoryFilter(List<MainFilter> requestCategoryFilter)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<SchoolCategoryType> schoolCategoryTypes = settingsImpl.getAllSchoolCategoryType();
		int category_count = schoolCategoryTypes.size();
		List<MainFilter> categoryFilter = new ArrayList<MainFilter>();
		int req_category_count = 0;
		try {
			req_category_count = requestCategoryFilter.size();
		} catch(NullPointerException e) {
			//Category filters not found
		}
		for (int i = 0; i < category_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(schoolCategoryTypes.get(i).getId());
			mainFilter.setName(schoolCategoryTypes.get(i).getName());
			mainFilter.setFiltered(false);
			for (int j = 0; j < req_category_count; j++) {
				if (requestCategoryFilter.get(j).getId() == schoolCategoryTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			categoryFilter.add(mainFilter);
		}
		return categoryFilter;
	}
	
	/**
	 * Get user medium filters
	 * @param requestMediumFilter
	 * @return
	 */
	public List<MainFilter> getUserMediumFilter(List<MainFilter> requestMediumFilter)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<MediumType> mediumTypes = settingsImpl.getAllMediumType();
		int medium_count = mediumTypes.size();
		List<MainFilter> mediumFilter = new ArrayList<MainFilter>();
		int req_medium_count = 0;
		try {
			req_medium_count = requestMediumFilter.size();
		} catch(NullPointerException e) {
			//Medium filters not found
		}
		for (int i = 0; i < medium_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(mediumTypes.get(i).getId());
			mainFilter.setName(mediumTypes.get(i).getTitle());
			mainFilter.setFiltered(false);
			for (int j = 0; j < req_medium_count; j++) {
				if (requestMediumFilter.get(j).getId() == mediumTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			mediumFilter.add(mainFilter);
		}
		return mediumFilter;
	}
	
	
	
	
}
