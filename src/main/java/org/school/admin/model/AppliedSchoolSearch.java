package org.school.admin.model;

// Generated Jun 29, 2015 3:32:41 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * SchoolSearchId generated by hbm2java
 */
@Entity
@Table(name="applied_school_search")
public class AppliedSchoolSearch implements java.io.Serializable {

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
	private short boardId;
	private String mediums;
	private String schoolCategory;
	private short categoryId;
	private String schoolClassification;
	private short classificationId;
	private double rating;
	private long galeryImages;
	private long reviews;
	private String schoolType;
	private short schoolTypeId;
	private Integer userId;
	private short standardId;

	public AppliedSchoolSearch() {
	}

	public AppliedSchoolSearch(int schoolId, String alias, String tagLine,
			String aboutSchool, String logo, String homeImage,
			String establishmentType, String boardName, short boardId,
			short categoryId, short classificationId, double rating,
			long galeryImages, long reviews, short schoolTypeId, Integer userId, short standardId) {
		this.schoolId = schoolId;
		this.alias = alias;
		this.tagLine = tagLine;
		this.aboutSchool = aboutSchool;
		this.logo = logo;
		this.homeImage = homeImage;
		this.establishmentType = establishmentType;
		this.boardName = boardName;
		this.boardId = boardId;
		this.categoryId = categoryId;
		this.classificationId = classificationId;
		this.rating = rating;
		this.galeryImages = galeryImages;
		this.reviews = reviews;
		this.schoolTypeId = schoolTypeId;
		this.userId = userId;
		this.standardId = standardId;
	}

	public AppliedSchoolSearch(int schoolId, String name, String alias,
			String latitude, String longitude, String tagLine,
			String aboutSchool, String logo, String homeImage,
			String establishmentType, String streetName, String pincode,
			String localityName, String cityName, String boardName,
			short boardId, String mediums, String schoolCategory,
			short categoryId, String schoolClassification,
			short classificationId, double rating, long galeryImages,
			long reviews, String schoolType, short schoolTypeId, Integer userId, short standardId) {
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
		this.boardId = boardId;
		this.mediums = mediums;
		this.schoolCategory = schoolCategory;
		this.categoryId = categoryId;
		this.schoolClassification = schoolClassification;
		this.classificationId = classificationId;
		this.rating = rating;
		this.galeryImages = galeryImages;
		this.reviews = reviews;
		this.schoolType = schoolType;
		this.schoolTypeId = schoolTypeId;
		this.userId = userId;
		this.standardId = standardId;
	}
	
	@Id
	@Column(name = "school_id", unique=true, nullable = false)
	public int getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "alias", nullable = false, length = 200)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "latitude", length = 15)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", length = 15)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "tag_line", nullable = false, length = 200)
	public String getTagLine() {
		return this.tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	@Column(name = "about_school", nullable = false, length = 65535)
	public String getAboutSchool() {
		return this.aboutSchool;
	}

	public void setAboutSchool(String aboutSchool) {
		this.aboutSchool = aboutSchool;
	}

	@Column(name = "logo", nullable = false, length = 200)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "home_image", nullable = false, length = 200)
	public String getHomeImage() {
		return this.homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

	@Column(name = "establishment_type", nullable = false, length = 3)
	public String getEstablishmentType() {
		return this.establishmentType;
	}

	public void setEstablishmentType(String establishmentType) {
		this.establishmentType = establishmentType;
	}

	@Column(name = "street_name", length = 500)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "pincode", length = 100)
	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Column(name = "locality_name", length = 200)
	public String getLocalityName() {
		return this.localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	@Column(name = "city_name", length = 200)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "board_name", nullable = false, length = 45)
	public String getBoardName() {
		return this.boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	@Column(name = "board_id", nullable = false)
	public short getBoardId() {
		return this.boardId;
	}

	public void setBoardId(short boardId) {
		this.boardId = boardId;
	}

	@Column(name = "mediums", length = 65535)
	public String getMediums() {
		return this.mediums;
	}

	public void setMediums(String mediums) {
		this.mediums = mediums;
	}

	@Column(name = "school_category", length = 200)
	public String getSchoolCategory() {
		return this.schoolCategory;
	}

	public void setSchoolCategory(String schoolCategory) {
		this.schoolCategory = schoolCategory;
	}

	@Column(name = "category_id", nullable = false)
	public short getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(short categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "school_classification", length = 200)
	public String getSchoolClassification() {
		return this.schoolClassification;
	}

	public void setSchoolClassification(String schoolClassification) {
		this.schoolClassification = schoolClassification;
	}

	@Column(name = "classification_id", nullable = false)
	public short getClassificationId() {
		return this.classificationId;
	}

	public void setClassificationId(short classificationId) {
		this.classificationId = classificationId;
	}

	@Column(name = "rating", nullable = false, precision = 9, scale = 3)
	public double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Column(name = "galery_images", nullable = false)
	public long getGaleryImages() {
		return this.galeryImages;
	}

	public void setGaleryImages(long galeryImages) {
		this.galeryImages = galeryImages;
	}

	@Column(name = "reviews", nullable = false)
	public long getReviews() {
		return this.reviews;
	}

	public void setReviews(long reviews) {
		this.reviews = reviews;
	}

	@Column(name = "school_type", length = 200)
	public String getSchoolType() {
		return this.schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	@Column(name = "school_type_id", nullable = false)
	public short getSchoolTypeId() {
		return this.schoolTypeId;
	}

	public void setSchoolTypeId(short schoolTypeId) {
		this.schoolTypeId = schoolTypeId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "standard_id", nullable = false)
	public short getStandardId() {
		return standardId;
	}

	public void setStandardId(short standardId) {
		this.standardId = standardId;
	}

}