package org.school.admin.data;

public class SchoolList implements java.io.Serializable {

	private int schoolId;
	private String name;
	private String alias;
	private String latitude;
	private String longitude;
	private String tagLine;
	private String aboutSchool;
	private String logo;
	private String homeImage;
	private String establishmentType;
	private String streetName;
	private String pincode;
	private String localityName;
	private String cityName;
	private String boardName;
	private String mediums;
	private String schoolCategory;
	private String schoolType;
	private String schoolClassification;
	private String teachingApproach;
	private Double rating;
	private long galeryImages;
	private long reviews;
	private Double distance;
	private Double totalFee;
	private int seats;
	private Short standardId;
	private String standardName;
	private String primaryContactNo;
	private boolean isShortlisted;
	private Double campusSize;
	private String unitName;
	private long students;
	private long teachers;

	public SchoolList() {
	}

	public SchoolList(int schoolId, String boardName, long galeryImages,
			long reviews) {
		this.schoolId = schoolId;
		this.boardName = boardName;
		this.galeryImages = galeryImages;
		this.reviews = reviews;
	}

	public SchoolList(int schoolId, String name, String alias,
			String latitude, String longitude, String tagLine,
			String aboutSchool, String logo, String homeImage,
			String establishmentType, String streetName, String pincode,
			String localityName, String cityName, String boardName,
			String mediums, String schoolCategory, String schoolClassification,
			Double rating, long galeryImages, long reviews, Double distance,
			Double totalFee
	) {
		this.schoolId = schoolId;
		this.name = name;
		this.alias = alias;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tagLine = tagLine;
		this.aboutSchool = aboutSchool;
		this.logo = logo;
		this.homeImage = homeImage;
		this.establishmentType = establishmentType;
		this.streetName = streetName;
		this.pincode = pincode;
		this.localityName = localityName;
		this.cityName = cityName;
		this.boardName = boardName;
		this.mediums = mediums;
		this.schoolCategory = schoolCategory;
		this.schoolClassification = schoolClassification;
		this.rating = rating;
		this.galeryImages = galeryImages;
		this.reviews = reviews;
		this.distance = distance;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getAboutSchool() {
		return aboutSchool;
	}

	public void setAboutSchool(String aboutSchool) {
		this.aboutSchool = aboutSchool;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getHomeImage() {
		return homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

	public String getEstablishmentType() {
		return establishmentType;
	}

	public void setEstablishmentType(String establishmentType) {
		this.establishmentType = establishmentType;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getMediums() {
		return mediums;
	}

	public void setMediums(String mediums) {
		this.mediums = mediums;
	}

	public String getSchoolCategory() {
		return schoolCategory;
	}

	public void setSchoolCategory(String schoolCategory) {
		this.schoolCategory = schoolCategory;
	}

	public String getSchoolClassification() {
		return schoolClassification;
	}

	public void setSchoolClassification(String schoolClassification) {
		this.schoolClassification = schoolClassification;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public long getGaleryImages() {
		return galeryImages;
	}

	public void setGaleryImages(long galeryImages) {
		this.galeryImages = galeryImages;
	}

	public long getReviews() {
		return reviews;
	}

	public void setReviews(long reviews) {
		this.reviews = reviews;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Short getStandardId() {
		return standardId;
	}

	public void setStandardId(Short standardId) {
		this.standardId = standardId;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getTeachingApproach() {
		return teachingApproach;
	}

	public void setTeachingApproach(String teachingApproach) {
		this.teachingApproach = teachingApproach;
	}

	public String getPrimaryContactNo() {
		return primaryContactNo;
	}

	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}

	public boolean isShortlisted() {
		return isShortlisted;
	}

	public void setShortlisted(boolean isShortlisted) {
		this.isShortlisted = isShortlisted;
	}

	public Double getCampusSize() {
		return campusSize;
	}

	public void setCampusSize(Double campusSize) {
		this.campusSize = campusSize;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public long getStudents() {
		return students;
	}

	public void setStudents(long students) {
		this.students = students;
	}

	public long getTeachers() {
		return teachers;
	}

	public void setTeachers(long teachers) {
		this.teachers = teachers;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	
}
