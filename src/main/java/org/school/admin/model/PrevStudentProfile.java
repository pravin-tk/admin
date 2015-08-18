package org.school.admin.model;
// Generated 9 Jun, 2015 12:51:03 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PrevStudentProfile generated by hbm2java
 */
@Entity
@Table(name = "prev_student_profile")
public class PrevStudentProfile implements java.io.Serializable {

	private Integer id;
	private School school;
	private String name;
	private String email;
	private String mobile;
	private String batch;
	private String achievements;
	private Date lastUpdatedOn;
	private Integer lastUpdatedBy;
	private String image;

	public PrevStudentProfile() {
	}

	public PrevStudentProfile(School school, String name, String batch,
			String achievements,String image) {
		this.school = school;
		this.name = name;
		this.batch = batch;
		this.achievements = achievements;
		this.image = image;
	}

	public PrevStudentProfile(School school, String name, String email,
			String mobile, String batch, String achievements,
			String image,Date lastUpdatedOn, Integer lastUpdatedBy) {
		this.school = school;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.batch = batch;
		this.achievements = achievements;
		this.image = image;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
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
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "batch", nullable = false, length = 45)
	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Column(name = "achievements", nullable = false, length = 65535)
	public String getAchievements() {
		return this.achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
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

	@Column(name = "image", length = 200)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "PrevStudentProfile [id=" + id + ", schoolId=" + school.getId()
				+ ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", batch=" + batch + ", achievements=" + achievements
				+ ", lastUpdatedOn=" + lastUpdatedOn + ", lastUpdatedBy="
				+ lastUpdatedBy + "]";
	}

}
