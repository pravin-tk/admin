package org.school.admin.model;

// Generated 29 Jun, 2015 11:26:30 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TabControl generated by hbm2java
 */
@Entity
@Table(name = "tab_control")
public class TabControl implements java.io.Serializable {

	private Integer id;
	private School school;
	private Byte basic;
	private Byte schoolDetail;
	private Byte campusDetail;
	private Byte classDetail;
	private Byte contact;
	private Byte infrastructure;
	private Byte achievements;
	private Byte salesDetails;
	private Byte oldStudentProfile;
	private Byte timeLine;
	private Byte highlights;
	private Byte gallery;

	public TabControl() {
	}

	public TabControl(School school, Byte basic, Byte schoolDetail,
			Byte campusDetail, Byte classDetail, Byte contact,
			Byte infrastructure, Byte achievements, Byte salesDetails,
			Byte oldStudentProfile, Byte timeLine, Byte highlights, Byte gallery) {
		this.school = school;
		this.basic = basic;
		this.schoolDetail = schoolDetail;
		this.campusDetail = campusDetail;
		this.classDetail = classDetail;
		this.contact = contact;
		this.infrastructure = infrastructure;
		this.achievements = achievements;
		this.salesDetails = salesDetails;
		this.oldStudentProfile = oldStudentProfile;
		this.timeLine = timeLine;
		this.highlights = highlights;
		this.gallery = gallery;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Column(name = "basic")
	public Byte getBasic() {
		return this.basic;
	}

	public void setBasic(Byte basic) {
		this.basic = basic;
	}

	@Column(name = "school_detail")
	public Byte getSchoolDetail() {
		return this.schoolDetail;
	}

	public void setSchoolDetail(Byte schoolDetail) {
		this.schoolDetail = schoolDetail;
	}

	@Column(name = "campus_detail")
	public Byte getCampusDetail() {
		return this.campusDetail;
	}

	public void setCampusDetail(Byte campusDetail) {
		this.campusDetail = campusDetail;
	}

	@Column(name = "class_detail")
	public Byte getClassDetail() {
		return this.classDetail;
	}

	public void setClassDetail(Byte classDetail) {
		this.classDetail = classDetail;
	}

	@Column(name = "contact")
	public Byte getContact() {
		return this.contact;
	}

	public void setContact(Byte contact) {
		this.contact = contact;
	}

	@Column(name = "infrastructure")
	public Byte getInfrastructure() {
		return this.infrastructure;
	}

	public void setInfrastructure(Byte infrastructure) {
		this.infrastructure = infrastructure;
	}

	@Column(name = "achievements")
	public Byte getAchievements() {
		return this.achievements;
	}

	public void setAchievements(Byte achievements) {
		this.achievements = achievements;
	}

	@Column(name = "sales_details")
	public Byte getSalesDetails() {
		return this.salesDetails;
	}

	public void setSalesDetails(Byte salesDetails) {
		this.salesDetails = salesDetails;
	}

	@Column(name = "old_student_profile")
	public Byte getOldStudentProfile() {
		return this.oldStudentProfile;
	}

	public void setOldStudentProfile(Byte oldStudentProfile) {
		this.oldStudentProfile = oldStudentProfile;
	}

	@Column(name = "time_line")
	public Byte getTimeLine() {
		return this.timeLine;
	}

	public void setTimeLine(Byte timeLine) {
		this.timeLine = timeLine;
	}

	@Column(name = "highlights")
	public Byte getHighlights() {
		return this.highlights;
	}

	public void setHighlights(Byte highlights) {
		this.highlights = highlights;
	}

	@Column(name = "gallery")
	public Byte getGallery() {
		return this.gallery;
	}

	public void setGallery(Byte gallery) {
		this.gallery = gallery;
	}

}
