package org.school.admin.model;

// Generated Jun 22, 2015 5:13:56 PM by Hibernate Tools 4.0.0

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

/**
 * SchoolTimeline generated by hbm2java
 */
@Entity
@Table(name = "school_timeline")
public class SchoolTimeline implements java.io.Serializable {

	private Integer id;
	private School school;
	private Short year;
	private String title;
	private String image;
	private String classesUpto;
	private Integer lastUpdatedBy;
	private Date lastUpdatedOn;
	private Set<SchoolTimelineMilestone> schoolTimelineMilestones = new HashSet<SchoolTimelineMilestone>(
			0);

	public SchoolTimeline() {
	}

	public SchoolTimeline(School school, Short year, String title,
			String image, String classesUpto, Integer lastUpdatedBy,
			Date lastUpdatedOn,
			Set<SchoolTimelineMilestone> schoolTimelineMilestones) {
		this.school = school;
		this.year = year;
		this.title = title;
		this.image = image;
		this.classesUpto = classesUpto;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedOn = lastUpdatedOn;
		this.schoolTimelineMilestones = schoolTimelineMilestones;
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

	@Column(name = "year")
	public Short getYear() {
		return this.year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "image", length = 200)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "classes_upto", length = 16777215)
	public String getClassesUpto() {
		return this.classesUpto;
	}

	public void setClassesUpto(String classesUpto) {
		this.classesUpto = classesUpto;
	}

	@Column(name = "last_updated_by")
	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", length = 19)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolTimeline")
	public Set<SchoolTimelineMilestone> getSchoolTimelineMilestones() {
		return this.schoolTimelineMilestones;
	}

	public void setSchoolTimelineMilestones(
			Set<SchoolTimelineMilestone> schoolTimelineMilestones) {
		this.schoolTimelineMilestones = schoolTimelineMilestones;
	}

}