package org.school.admin.data;

public class SearchRequest {
	private String longitude;
	private String latitude;
	private Short standardId;
	private String boardId;
	private String taId;
	private String mediumId;
	private String typeId;
	private String categoryId;
	private String classificationId;
	private String safetyId;
	private String activityId;
	private String infraId;
	private String fee;
	private String rating;
	private String distance;
	private String seats;
	private String userId;
	private String minFee;
	private String maxFee;
	
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
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getTaId() {
		return taId;
	}
	public void setTaId(String taId) {
		this.taId = taId;
	}
	public String getMediumId() {
		return mediumId;
	}
	public void setMediumId(String mediumId) {
		this.mediumId = mediumId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSafetyId() {
		return safetyId;
	}
	public void setSafetyId(String safetyId) {
		this.safetyId = safetyId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getInfraId() {
		return infraId;
	}
	public void setInfraId(String infraId) {
		this.infraId = infraId;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	public String getClassificationId() {
		return classificationId;
	}
	public void setClassificationId(String classificationId) {
		this.classificationId = classificationId;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMinFee() {
		return minFee;
	}
	public void setMinFee(String minFee) {
		this.minFee = minFee;
	}
	public String getMaxFee() {
		return maxFee;
	}
	public void setMaxFee(String maxFee) {
		this.maxFee = maxFee;
	}
	@Override
	public String toString() {
		return "SearchRequest [longitude=" + longitude + ", latitude="
				+ latitude + ", standardId=" + standardId + ", boardId="
				+ boardId + ", taId=" + taId + ", mediumId=" + mediumId
				+ ", typeId=" + typeId + ", categoryId=" + categoryId
				+ ", classificationId=" + classificationId + ", safetyId="
				+ safetyId + ", activityId=" + activityId + ", infraId="
				+ infraId + ", fee=" + fee + ", rating=" + rating
				+ ", distance=" + distance + ", seats=" + seats + ", userId="
				+ userId + ", minFee=" + minFee + ", maxFee=" + maxFee + "]";
	}
	
	
}
