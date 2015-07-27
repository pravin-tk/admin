package org.school.admin.model;

// Generated 30-May-2015 14:03:54 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * School generated by hbm2java
 */
@Entity
@Table(name = "school")
public class School implements java.io.Serializable {

	private Integer id;
	private Locality locality;
	private String name;
	private String plotNo;
	private String streetName;
	private String landmark;
	private String pincode;
	private String longitude;
	private String latitude;
	private String alias;
	private String tagLine;
	private String aboutSchool;
	private String logo;
	private Byte promote;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean establishmentType;
	private Byte status;
	private Byte isFreelisting;
	private Date trialStartDate;
	private Date trialEndDate;
	private Date liveDate;
	private Integer createdBy;
	private Date lastUpdatedOn;
	private Integer lastUpdatedBy;
	private String homeImage;
	private Set<ClassInfo> classInfos = new HashSet<ClassInfo>(0);
	private Set<SchoolMedium> schoolMediums = new HashSet<SchoolMedium>(0);
	private Set<SchoolTimeline> schoolTimelines = new HashSet<SchoolTimeline>(0);
	private Set<SchoolActivityCatItem> schoolActivityCatItems = new HashSet<SchoolActivityCatItem>(0);
	private Set<SchoolSafetyCatItem> schoolSafetyCatItems = new HashSet<SchoolSafetyCatItem>(0);
	private Set<SchoolInfrastructureCatItem> schoolInfrastructureCatItems = new HashSet<SchoolInfrastructureCatItem>(0);
	private Set<AppliedSchool> appliedSchools = new HashSet<AppliedSchool>(0);
	private Set<ShortListedSchool> shortListedSchools = new HashSet<ShortListedSchool>(0);
	
	public School() {
	}

	public School(Locality locality, String name, String plotNo,
			String streetName, String landmark, String pincode,
			String longitude, String latitude, String alias, String tagLine,
			String aboutSchool, boolean establishmentType,Byte promote) {
		this.locality = locality;
		this.name = name;
		this.plotNo = plotNo;
		this.streetName = streetName;
		this.landmark = landmark;
		this.pincode = pincode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.alias = alias;
		this.tagLine = tagLine;
		this.aboutSchool = aboutSchool;
		this.establishmentType = establishmentType;
		this.promote = promote;
	}

	public School(Locality locality, String name, String plotNo,
			String streetName, String landmark, String pincode,
			String longitude, String latitude, String alias, String tagLine,
			String aboutSchool, String logo, boolean establishmentType,
			byte status, Date liveDate, Integer createdBy,
			Date lastUpdatedOn, Integer lastUpdatedBy,Byte promote,
			Set<ClassInfo> classInfos,
			Set<SchoolMedium> schoolMediums, Set<SchoolTimeline> schoolTimelines, Set<AppliedSchool> appliedSchools, Set<ShortListedSchool> shortListedSchools) {
		this.locality = locality;
		this.name = name;
		this.plotNo = plotNo;
		this.streetName = streetName;
		this.landmark = landmark;
		this.pincode = pincode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.alias = alias;
		this.tagLine = tagLine;
		this.aboutSchool = aboutSchool;
		this.logo = logo;
		this.establishmentType = establishmentType;
		this.status = status;
		this.liveDate = liveDate;
		this.createdBy = createdBy;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
		this.promote = promote;
		this.classInfos = classInfos;
		this.schoolMediums = schoolMediums;
		this.schoolTimelines = schoolTimelines;
		this.appliedSchools = appliedSchools;
		this.shortListedSchools = shortListedSchools;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locality_id", nullable = false)
	public Locality getLocality() {
		return this.locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "plot_no", nullable = false, length = 200)
	public String getPlotNo() {
		return this.plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	@Column(name = "street_name", nullable = false, length = 200)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "landmark", nullable = false, length = 100)
	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@Column(name = "pincode", nullable = false, length = 100)
	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Column(name = "longitude", nullable = false, length = 15)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", nullable = false, length = 15)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "alias", nullable = false, length = 200)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	@Column(name = "logo", length = 200)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "establishment_type", nullable = false)
	public boolean isEstablishmentType() {
		return this.establishmentType;
	}

	public void setEstablishmentType(boolean establishmentType) {
		this.establishmentType = establishmentType;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	@Column(name = "is_free_listing")
	public Byte getIsFreelisting() {
		return isFreelisting;
	}

	public void setIsFreelisting(Byte isFreelisting) {
		this.isFreelisting = isFreelisting;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "trial_start_date", length = 10)
	public Date getTrialStartDate() {
		return trialStartDate;
	}

	public void setTrialStartDate(Date trialStartDate) {
		this.trialStartDate = trialStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "trial_end_date", length = 10)
	public Date getTrialEndDate() {
		return trialEndDate;
	}

	public void setTrialEndDate(Date trialEndDate) {
		this.trialEndDate = trialEndDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "live_date", length = 19)
	public Date getLiveDate() {
		return this.liveDate;
	}

	public void setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", length = 19)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Column(name = "last_updated_by")
	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "promote")
	public Byte getPromote() {
		return this.promote;
	}

	public void setPromote(Byte promote) {
		this.promote = promote;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<SchoolMedium> getSchoolMediums() {
		return this.schoolMediums;
	}
	
	public void setSchoolMediums(Set<SchoolMedium> schoolMediums) {
		this.schoolMediums = schoolMediums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<ClassInfo> getClassInfos() {
		return classInfos;
	}

	public void setClassInfos(Set<ClassInfo> classInfos) {
		this.classInfos = classInfos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<SchoolActivityCatItem> getSchoolActivityCatItems() {
		return schoolActivityCatItems;
	}

	public void setSchoolActivityCatItems(
			Set<SchoolActivityCatItem> schoolActivityCatItems) {
		this.schoolActivityCatItems = schoolActivityCatItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<SchoolSafetyCatItem> getSchoolSafetyCatItems() {
		return schoolSafetyCatItems;
	}

	public void setSchoolSafetyCatItems(
			Set<SchoolSafetyCatItem> schoolSafetyCatItems) {
		this.schoolSafetyCatItems = schoolSafetyCatItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<SchoolInfrastructureCatItem> getSchoolInfrastructureCatItems() {
		return schoolInfrastructureCatItems;
	}

	public void setSchoolInfrastructureCatItems(
			Set<SchoolInfrastructureCatItem> schoolInfrastructureCatItems) {
		this.schoolInfrastructureCatItems = schoolInfrastructureCatItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<SchoolTimeline> getSchoolTimelines() {
		return this.schoolTimelines;
	}

	public void setSchoolTimelines(Set<SchoolTimeline> schoolTimelines) {
		this.schoolTimelines = schoolTimelines;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<AppliedSchool> getAppliedSchools() {
		return appliedSchools;
	}

	public void setAppliedSchools(Set<AppliedSchool> appliedSchools) {
		this.appliedSchools = appliedSchools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	public Set<ShortListedSchool> getShortListedSchools() {
		return shortListedSchools;
	}

	public void setShortListedSchools(Set<ShortListedSchool> shortListedSchools) {
		this.shortListedSchools = shortListedSchools;
	}

	@Column(name = "home_image", length = 200)
	public String getHomeImage() {
		return this.homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

}
