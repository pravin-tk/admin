package org.school.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.school.admin.dao.FacilityImpl;
import org.school.admin.dao.SettingsImpl;
import org.school.admin.data.MainFilter;
import org.school.admin.data.SearchFilter;
import org.school.admin.data.SearchRequest;
import org.school.admin.data.SearchSort;
import org.school.admin.model.ActivityCategoryItem;
import org.school.admin.model.BoardType;
import org.school.admin.model.InfrastructureCategoryItem;
import org.school.admin.model.MediumType;
import org.school.admin.model.SafetyCategoryItem;
import org.school.admin.model.SchoolCategoryType;
import org.school.admin.model.SchoolClassificationType;
import org.school.admin.model.SchoolType;

public class SearchFilterService {
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
		searchSort1.setDisplayName("Fee");
		searchSort1.setSortOrder("random");
		searchSorts.add(searchSort1);
		SearchSort searchSort2 = new SearchSort();
		searchSort2.setName("rating");
		searchSort2.setDisplayName("Rating");
		searchSort2.setSortOrder("random");
		searchSorts.add(searchSort2);
		SearchSort searchSort3 = new SearchSort();
		searchSort3.setName("distance");
		searchSort3.setDisplayName("Distance");
		searchSort3.setSortOrder("random");
		searchSorts.add(searchSort3);
		SearchSort searchSort4 = new SearchSort();
		searchSort4.setName("seats");
		searchSort4.setDisplayName("Seats");
		searchSort4.setSortOrder("random");
		searchSorts.add(searchSort4);
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
			searchSort.setDisplayName(allSortFields.get(i).getDisplayName());
			searchSort.setSortOrder(allSortFields.get(i).getSortOrder());
			for(int j = 0; j < req_sort_count; j++){
				if(requestSortFields.get(j).getName().equalsIgnoreCase(allSortFields.get(i).getName())){
					searchSort.setSortOrder(requestSortFields.get(j).getSortOrder());
					searchSort.setDisplayName(requestSortFields.get(j).getDisplayName());
				}
			}
			sortFields.add(searchSort);
		}
		return sortFields;
	}
	
	public List<SearchSort> getUserSortFields(String fee, String rating, String distance, String seats)
	{
		List<SearchSort> searchSorts = new ArrayList<SearchSort>();
		SearchSort searchSort1 = new SearchSort();
		searchSort1.setName("classFee");
		searchSort1.setDisplayName("Fee");
		searchSort1.setSortOrder("random");
		if(fee.equalsIgnoreCase("ASC") || fee.equalsIgnoreCase("DESC")){
			searchSort1.setSortOrder(fee);
		}
		searchSorts.add(searchSort1);
		SearchSort searchSort2 = new SearchSort();
		searchSort2.setName("rating");
		searchSort2.setDisplayName("Rating");
		searchSort2.setSortOrder("random");
		if(rating.equalsIgnoreCase("ASC") || rating.equalsIgnoreCase("DESC")){
			searchSort2.setSortOrder(rating);
		}
		searchSorts.add(searchSort2);
		SearchSort searchSort3 = new SearchSort();
		searchSort3.setName("distance");
		searchSort3.setDisplayName("Distance");
		searchSort3.setSortOrder("random");
		if(distance.equalsIgnoreCase("ASC") || distance.equalsIgnoreCase("DESC")){
			searchSort3.setSortOrder(distance);
		}
		searchSorts.add(searchSort3);
		SearchSort searchSort4 = new SearchSort();
		searchSort4.setName("seats");
		searchSort4.setDisplayName("Seats");
		searchSort4.setSortOrder("random");
		if(seats.equalsIgnoreCase("ASC") || seats.equalsIgnoreCase("DESC")){
			searchSort4.setSortOrder(seats);
		}
		searchSorts.add(searchSort4);
		return searchSorts;
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
		int cat_id = 0;
		String cat_name = "";
		List<MainFilter> filter = new ArrayList<MainFilter>();
		SearchFilter searchFilter = new SearchFilter();
		for(int i=0; i<activity_count; i++){
			if(cat_id != 0 && cat_id != activityCategoryItems.get(i).getActivityCategory().getId()){
				searchFilter.setCategoryName(cat_name);
				searchFilter.setFilter(filter);
				activityFilter.add(searchFilter);
				searchFilter = new SearchFilter();
				filter = new ArrayList<MainFilter>();
			}
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(activityCategoryItems.get(i).getId());
			mainFilter.setName(activityCategoryItems.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("activityId");
			if(req_act_count > 0){
				for(int j = 0; j < req_act_count; j++){
					if(requestActivityFielter.get(i).getFilter().get(j).getId() == activityCategoryItems.get(i).getId()){
						mainFilter.setFiltered(true);
					}
				}
			}
			filter.add(mainFilter);
			cat_id = activityCategoryItems.get(i).getActivityCategory().getId();
			cat_name = activityCategoryItems.get(i).getActivityCategory().getName();
		}
		searchFilter.setCategoryName(cat_name);
		searchFilter.setFilter(filter);
		activityFilter.add(searchFilter);
		return activityFilter;
	}
	
	public List<SearchFilter> getUserActivityFilter(String activityId)
	{
		FacilityImpl facilityImpl = new FacilityImpl();
		List<ActivityCategoryItem> activityCategoryItems = facilityImpl.getactivityCategoryItemList();
		int activity_count = activityCategoryItems.size();
		List<SearchFilter> activityFilter = new ArrayList<SearchFilter>();
		int req_act_count = 0;
		String[] requestActivityFielter = activityId.split(",");
		try {
			req_act_count = requestActivityFielter.length;
		} catch(NullPointerException e) {
			//activity filters not found
		}
		int cat_id = 0;
		String cat_name = "";
		List<MainFilter> filter = new ArrayList<MainFilter>();
		SearchFilter searchFilter = new SearchFilter();
		for(int i=0; i<activity_count; i++){
			if(cat_id != 0 && cat_id != activityCategoryItems.get(i).getActivityCategory().getId()){
				searchFilter.setCategoryName(cat_name);
				searchFilter.setFilter(filter);
				activityFilter.add(searchFilter);
				searchFilter = new SearchFilter();
				filter = new ArrayList<MainFilter>();
			}
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(activityCategoryItems.get(i).getId());
			mainFilter.setName(activityCategoryItems.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("activityId");
			if(req_act_count > 0){
				for(int j = 0; j < req_act_count; j++){
					if(Integer.parseInt(requestActivityFielter[j]) == activityCategoryItems.get(i).getId()){
						mainFilter.setFiltered(true);
					}
				}
			}
			filter.add(mainFilter);
			cat_id = activityCategoryItems.get(i).getActivityCategory().getId();
			cat_name = activityCategoryItems.get(i).getActivityCategory().getName();
		}
		searchFilter.setCategoryName(cat_name);
		searchFilter.setFilter(filter);
		activityFilter.add(searchFilter);
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
		int cat_id = 0;
		String cat_name = "";
		List<MainFilter> filter = new ArrayList<MainFilter>();
		SearchFilter searchFilter = new SearchFilter();
		for(int i=0; i<safety_count; i++){
			if(cat_id != 0 && cat_id != safetyCategoryItems.get(i).getSafetyCategory().getId()){
				searchFilter.setCategoryName(cat_name);
				searchFilter.setFilter(filter);
				safetyFilter.add(searchFilter);
				searchFilter = new SearchFilter();
				filter = new ArrayList<MainFilter>();
			}
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(safetyCategoryItems.get(i).getId());
			mainFilter.setName(safetyCategoryItems.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("safetyId");
			if(req_safety_count > 0){
				for(int j = 0; j < req_safety_count; j++){
					if(requestSafetyFilter.get(i).getFilter().get(j).getId() == safetyCategoryItems.get(i).getId()){
						mainFilter.setFiltered(true);
					}
				}
			}
			filter.add(mainFilter);
			cat_id = safetyCategoryItems.get(i).getSafetyCategory().getId();
			cat_name = safetyCategoryItems.get(i).getSafetyCategory().getName();
		}
		searchFilter.setCategoryName(cat_name);
		searchFilter.setFilter(filter);
		safetyFilter.add(searchFilter);
		return safetyFilter;
	}
	
	public List<SearchFilter> getUserSafetyFilter(String safetyId)
	{
		FacilityImpl facilityImpl = new FacilityImpl();
		List<SafetyCategoryItem> safetyCategoryItems = facilityImpl.getSafetyCategoryItemList();
		int safety_count = safetyCategoryItems.size();
		List<SearchFilter> safetyFilter = new ArrayList<SearchFilter>();
		String[] requestSafetyFilter = safetyId.split(",");
		int req_safety_count = 0;
		try {
			req_safety_count = requestSafetyFilter.length;
		} catch(NullPointerException e) {
			//safety filters not found
		}
		int cat_id = 0;
		String cat_name = "";
		List<MainFilter> filter = new ArrayList<MainFilter>();
		SearchFilter searchFilter = new SearchFilter();
		for(int i=0; i<safety_count; i++){
			if(cat_id != 0 && cat_id != safetyCategoryItems.get(i).getSafetyCategory().getId()){
				searchFilter.setCategoryName(cat_name);
				searchFilter.setFilter(filter);
				safetyFilter.add(searchFilter);
				searchFilter = new SearchFilter();
				filter = new ArrayList<MainFilter>();
			}
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(safetyCategoryItems.get(i).getId());
			mainFilter.setName(safetyCategoryItems.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("safetyId");
			if(req_safety_count > 0){
				for(int j = 0; j < req_safety_count; j++){
					if(Integer.parseInt(requestSafetyFilter[j]) == safetyCategoryItems.get(i).getId()){
						mainFilter.setFiltered(true);
					}
				}
			}
			filter.add(mainFilter);
			cat_id = safetyCategoryItems.get(i).getSafetyCategory().getId();
			cat_name = safetyCategoryItems.get(i).getSafetyCategory().getName();
		}
		searchFilter.setCategoryName(cat_name);
		searchFilter.setFilter(filter);
		safetyFilter.add(searchFilter);
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
		int cat_id = 0;
		String cat_name = "";
		List<MainFilter> filter = new ArrayList<MainFilter>();
		SearchFilter searchFilter = new SearchFilter();
		for(int i=0; i<infra_count; i++){
			if(cat_id != 0 && cat_id != infrastructureCategoryItems.get(i).getInfrastructureCategory().getId()){
				searchFilter.setCategoryName(cat_name);
				searchFilter.setFilter(filter);
				infraFilter.add(searchFilter);
				searchFilter = new SearchFilter();
				filter = new ArrayList<MainFilter>();
			}
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(infrastructureCategoryItems.get(i).getId());
			mainFilter.setName(infrastructureCategoryItems.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("infraId");
			if (req_infra_count > 0) {
				for (int j = 0; j < req_infra_count; j++) {
					if (requestInfraFilter.get(i).getFilter().get(j).getId() == infrastructureCategoryItems.get(i).getId()) {
						mainFilter.setFiltered(true);
					}
				}
			}
			filter.add(mainFilter);
			cat_id = infrastructureCategoryItems.get(i).getInfrastructureCategory().getId();
			cat_name = infrastructureCategoryItems.get(i).getInfrastructureCategory().getName();
		}
		searchFilter.setCategoryName(cat_name);
		searchFilter.setFilter(filter);
		infraFilter.add(searchFilter);
		return infraFilter;
	}
	
	public List<SearchFilter> getUserInfraFilter(String infraId) 
	{
		FacilityImpl facilityImpl = new FacilityImpl();
		List<InfrastructureCategoryItem> infrastructureCategoryItems = facilityImpl.getInfrastructureCategoryItemList();
		int infra_count = infrastructureCategoryItems.size();
		List<SearchFilter> infraFilter = new ArrayList<SearchFilter>();
		String[] requestInfraFilter = infraId.split(",");
		int req_infra_count = 0;
		try {
			req_infra_count = requestInfraFilter.length;
		} catch(NullPointerException e) {
			//Infra filters not found
		}
		int cat_id = 0;
		String cat_name = "";
		List<MainFilter> filter = new ArrayList<MainFilter>();
		SearchFilter searchFilter = new SearchFilter();
		for(int i=0; i<infra_count; i++){
			if(cat_id != 0 && cat_id != infrastructureCategoryItems.get(i).getInfrastructureCategory().getId()){
				searchFilter.setCategoryName(cat_name);
				searchFilter.setFilter(filter);
				infraFilter.add(searchFilter);
				searchFilter = new SearchFilter();
				filter = new ArrayList<MainFilter>();
			}
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(infrastructureCategoryItems.get(i).getId());
			mainFilter.setName(infrastructureCategoryItems.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("infraId");
			if (req_infra_count > 0) {
				for (int j = 0; j < req_infra_count; j++) {
					if (Integer.parseInt(requestInfraFilter[j]) == infrastructureCategoryItems.get(i).getId()) {
						mainFilter.setFiltered(true);
					}
				}
			}
			filter.add(mainFilter);
			cat_id = infrastructureCategoryItems.get(i).getInfrastructureCategory().getId();
			cat_name = infrastructureCategoryItems.get(i).getInfrastructureCategory().getName();
		}
		searchFilter.setCategoryName(cat_name);
		searchFilter.setFilter(filter);
		infraFilter.add(searchFilter);
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
			mainFilter.setParamName("boardId");
			for (int j = 0; j < req_board_count; j++) {
				if (requestBoardFilter.get(j).getId() == boardTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			boardFilter.add(mainFilter);
		}
		return boardFilter;
	}
	
	public List<MainFilter> getUserBoardFilter(String boardId) 
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<BoardType> boardTypes = settingsImpl.getBoardTypes();
		int board_count = boardTypes.size();
		List<MainFilter> boardFilter = new ArrayList<MainFilter>();
		String[] requestBoardFilter = boardId.split(",");
		int req_board_count = 0;
		try {
			req_board_count = requestBoardFilter.length;
		} catch(NullPointerException e) {
			//Board filters not found
		}
		for (int i = 0; i < board_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(boardTypes.get(i).getId());
			mainFilter.setName(boardTypes.get(i).getBoardName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("boardId");
			for (int j = 0; j < req_board_count; j++) {
				if (Integer.parseInt(requestBoardFilter[j]) == boardTypes.get(i).getId()) {
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
			mainFilter.setParamName("typeId");
			for (int j = 0; j < req_type_count; j++) {
				if (requestTypeFilter.get(j).getId() == schoolTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			typeFilter.add(mainFilter);
		}
		return typeFilter;
	}
	
	public List<MainFilter> getUserSchoolTypeFilter(String typeId)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<SchoolType> schoolTypes = settingsImpl.getSchoolType();
		int type_count = schoolTypes.size();
		List<MainFilter> typeFilter = new ArrayList<MainFilter>();
		String[] requestTypeFilter = typeId.split(",");
		int req_type_count = 0;
		try {
			req_type_count = requestTypeFilter.length;
		} catch(NullPointerException e) {
			//Type filters not found
		}
		for (int i = 0; i < type_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(schoolTypes.get(i).getId());
			mainFilter.setName(schoolTypes.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("typeId");
			for (int j = 0; j < req_type_count; j++) {
				if (Integer.parseInt(requestTypeFilter[j]) == schoolTypes.get(i).getId()) {
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
			mainFilter.setParamName("categoryId");
			for (int j = 0; j < req_category_count; j++) {
				if (requestCategoryFilter.get(j).getId() == schoolCategoryTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			categoryFilter.add(mainFilter);
		}
		return categoryFilter;
	}
	
	public List<MainFilter> getUserSchoolCategoryFilter(String categoryId)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<SchoolCategoryType> schoolCategoryTypes = settingsImpl.getAllSchoolCategoryType();
		int category_count = schoolCategoryTypes.size();
		List<MainFilter> categoryFilter = new ArrayList<MainFilter>();
		String[] requestCategoryFilter = categoryId.split(",");
		int req_category_count = 0;
		try {
			req_category_count = requestCategoryFilter.length;
		} catch(NullPointerException e) {
			//Category filters not found
		}
		for (int i = 0; i < category_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(schoolCategoryTypes.get(i).getId());
			mainFilter.setName(schoolCategoryTypes.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("categoryId");
			for (int j = 0; j < req_category_count; j++) {
				if (Integer.parseInt(requestCategoryFilter[j]) == schoolCategoryTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			categoryFilter.add(mainFilter);
		}
		return categoryFilter;
	}
	
	/**
	 * Get user school classification filter
	 * @param requestClassificationFilter
	 * @return List<MainFilter>
	 */
	public List<MainFilter> getUserSchoolClassificationFilter(List<MainFilter> requestClassificationFilter)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<SchoolClassificationType> schoolClassificationTypes = settingsImpl.getAllSchoolClassificationType();
		int category_count = schoolClassificationTypes.size();
		List<MainFilter> classificationFilter = new ArrayList<MainFilter>();
		int req_category_count = 0;
		try {
			req_category_count = requestClassificationFilter.size();
		} catch(NullPointerException e) {
			//Category filters not found
		}
		for (int i = 0; i < category_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(schoolClassificationTypes.get(i).getId());
			mainFilter.setName(schoolClassificationTypes.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("classificationId");
			for (int j = 0; j < req_category_count; j++) {
				if (requestClassificationFilter.get(j).getId() == schoolClassificationTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			classificationFilter.add(mainFilter);
		}
		return classificationFilter;
	}
	
	public List<MainFilter> getUserSchoolClassificationFilter(String classificationId)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<SchoolClassificationType> schoolClassificationTypes = settingsImpl.getAllSchoolClassificationType();
		int category_count = schoolClassificationTypes.size();
		List<MainFilter> classificationFilter = new ArrayList<MainFilter>();
		String[] requestClassificationFilter = classificationId.split(",");
		int req_category_count = 0;
		try {
			req_category_count = requestClassificationFilter.length;
		} catch(NullPointerException e) {
			//Category filters not found
		}
		for (int i = 0; i < category_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(schoolClassificationTypes.get(i).getId());
			mainFilter.setName(schoolClassificationTypes.get(i).getName());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("classificationId");
			for (int j = 0; j < req_category_count; j++) {
				if (Integer.parseInt(requestClassificationFilter[j]) == schoolClassificationTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			classificationFilter.add(mainFilter);
		}
		return classificationFilter;
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
			mainFilter.setParamName("mediumId");
			for (int j = 0; j < req_medium_count; j++) {
				if (requestMediumFilter.get(j).getId() == mediumTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			mediumFilter.add(mainFilter);
		}
		return mediumFilter;
	}
	
	public List<MainFilter> getUserMediumFilter(String mediumId)
	{
		SettingsImpl settingsImpl = new SettingsImpl();
		List<MediumType> mediumTypes = settingsImpl.getAllMediumType();
		int medium_count = mediumTypes.size();
		List<MainFilter> mediumFilter = new ArrayList<MainFilter>();
		String[] requestMediumFilter = mediumId.split(",");
		int req_medium_count = 0;
		try {
			req_medium_count = requestMediumFilter.length;
		} catch(NullPointerException e) {
			//Medium filters not found
		}
		for (int i = 0; i < medium_count; i++) {
			MainFilter mainFilter = new MainFilter();
			mainFilter.setId(mediumTypes.get(i).getId());
			mainFilter.setName(mediumTypes.get(i).getTitle());
			mainFilter.setFiltered(false);
			mainFilter.setParamName("mediumId");
			for (int j = 0; j < req_medium_count; j++) {
				if (Integer.parseInt(requestMediumFilter[j]) == mediumTypes.get(i).getId()) {
					mainFilter.setFiltered(true);
				}
			}
			mediumFilter.add(mainFilter);
		}
		return mediumFilter;
	}
	
	public SearchRequest getSearchRequest(UriInfo uriInfo){
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		SearchRequest searchRequest = new SearchRequest();
		Short std = 0;
		searchRequest.setLatitude("");
		searchRequest.setLongitude("");
		searchRequest.setStandardId(std);
		searchRequest.setActivityId("0");
		searchRequest.setBoardId("0");
		searchRequest.setCategoryId("0");
		searchRequest.setDistance("random");
		searchRequest.setFee("random");
		searchRequest.setSeats("random");
		searchRequest.setInfraId("0");
		searchRequest.setMediumId("0");
		searchRequest.setRating("random");
		searchRequest.setSafetyId("0");
		searchRequest.setTypeId("0");
		searchRequest.setClassificationId("0");
		try {
			for (String param: params.keySet()) {
				//System.out.println(param);
		        if (param.equals("latitude")){
		        	searchRequest.setLatitude(params.getFirst("latitude"));
		        } else if(param.equals("longitude")) {
		        	searchRequest.setLongitude(params.getFirst("longitude"));
		        } else if(param.equals("standardId")) {
					searchRequest.setStandardId(Short.parseShort(params.getFirst("standardId")));
		        } else if(param.equals("activityId")) {
		        	searchRequest.setActivityId(params.getFirst("activityId"));
		        } else if(param.equals("boardId")) {
		        	searchRequest.setBoardId(params.getFirst("boardId"));
		        } else if(param.equals("categoryId")) {
		        	searchRequest.setCategoryId(params.getFirst("categoryId"));
		        } else if(param.equals("distance")) {
		        	searchRequest.setDistance(params.getFirst("distance"));
		        } else if(param.equals("seats")) {
		        	searchRequest.setSeats(params.getFirst("seats"));
		        } else if(param.equals("classFee")) {
		        	searchRequest.setFee(params.getFirst("classFee"));
		        } else if(param.equals("infraId")) {
		        	searchRequest.setInfraId(params.getFirst("infraId"));
		        } else if(param.equals("mediumId")) {
		        	searchRequest.setMediumId(params.getFirst("mediumId"));
		        } else if(param.equals("rating")) {
		        	searchRequest.setRating(params.getFirst("rating"));
		        } else if(param.equals("safetyId")) {
		        	searchRequest.setSafetyId(params.getFirst("safetyId"));
		        } else if(param.equals("typeId")) {
		        	searchRequest.setTypeId(params.getFirst("typeId"));
		        } else if(param.equals("classificationId")) {
		        	searchRequest.setClassificationId(params.getFirst("classificationId"));
		        }
		        
		    }
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return searchRequest;
	}
}
