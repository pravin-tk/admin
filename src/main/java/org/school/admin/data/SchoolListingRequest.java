package org.school.admin.data;

import java.util.List;

public class SchoolListingRequest {
	private String longitude;
	private String latitude;
	private Short standardId;
	private List<SearchFilter> safetyFilter;
	private List<SearchFilter> infraFilter;
	private List<SearchFilter> activityFilter;
	private List<MainFilter> boardFilter;
	private List<MainFilter> mediumFilter;
	private List<MainFilter> typeFilter;
	private List<MainFilter> categoryFilter;
	private List<MainFilter> classificationFilter;
	private List<SearchSort> sortFields;

	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Short getStandardId() {
		return standardId;
	}
	public void setStandardId(Short standardId) {
		this.standardId = standardId;
	}
	
	public List<SearchFilter> getSafetyFilter() {
		return safetyFilter;
	}
	public void setSafetyFilter(List<SearchFilter> safetyFilter) {
		this.safetyFilter = safetyFilter;
	}
	public List<SearchFilter> getInfraFilter() {
		return infraFilter;
	}
	public void setInfraFilter(List<SearchFilter> infraFilter) {
		this.infraFilter = infraFilter;
	}
	public List<SearchFilter> getActivityFilter() {
		return activityFilter;
	}
	public void setActivityFilter(List<SearchFilter> activityFilter) {
		this.activityFilter = activityFilter;
	}
	public List<SearchSort> getSortFields() {
		return sortFields;
	}
	public void setSortFields(List<SearchSort> sortFields) {
		this.sortFields = sortFields;
	}
	public List<MainFilter> getBoardFilter() {
		return boardFilter;
	}
	public void setBoardFilter(List<MainFilter> boardFilter) {
		this.boardFilter = boardFilter;
	}
	public List<MainFilter> getMediumFilter() {
		return mediumFilter;
	}
	public void setMediumFilter(List<MainFilter> mediumFilter) {
		this.mediumFilter = mediumFilter;
	}
	public List<MainFilter> getTypeFilter() {
		return typeFilter;
	}
	public void setTypeFilter(List<MainFilter> typeFilter) {
		this.typeFilter = typeFilter;
	}
	public List<MainFilter> getCategoryFilter() {
		return categoryFilter;
	}
	public void setCategoryFilter(List<MainFilter> categoryFilter) {
		this.categoryFilter = categoryFilter;
	}
	public List<MainFilter> getClassificationFilter() {
		return classificationFilter;
	}
	public void setClassificationFilter(List<MainFilter> classificationFilter) {
		this.classificationFilter = classificationFilter;
	}
	
}