package org.school.admin.data;

import org.hibernate.annotations.Type;

public class SchoolAddress implements java.io.Serializable {
	private int schoolId;
	private String name;
	private String alias;
	private String latitude;
	private String longitude;
	private String tagLine;
	private String aboutSchool;
	private String logo;
	private String homeImage;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private String establishmentType;
	private String streetName;
	private String pincode;
	private String localityName;
	private String cityName;
	private String landmark;
	
   
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
	
	public String isEstablishmentType() {
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
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getEstablishmentType() {
		return establishmentType;
	}
	
}
