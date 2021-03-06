package org.school.admin.model;

// Generated Jun 11, 2015 12:47:55 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SchoolActivityCatItem generated by hbm2java
 */
@Entity
@Table(name = "school_activity_cat_item")
public class SchoolActivityCatItem implements java.io.Serializable {

	private Integer id;
	private ActivityCategoryItem activityCategoryItem;
	private School school;
	private String description;
	private Byte status;
	private Date lastUpdatedOn;
	private Integer lastUpdatedBy;

	public SchoolActivityCatItem() {
	}

	public SchoolActivityCatItem(ActivityCategoryItem activityCategoryItem,
			School school, String description, Byte status,
			Date lastUpdatedOn, Integer lastUpdatedBy) {
		this.activityCategoryItem = activityCategoryItem;
		this.school = school;
		this.description = description;
		this.status = status;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activity_cat_item_id")
	public ActivityCategoryItem getActivityCategoryItem() {
		return this.activityCategoryItem;
	}

	public void setActivityCategoryItem(
			ActivityCategoryItem activityCategoryItem) {
		this.activityCategoryItem = activityCategoryItem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id",nullable = false)
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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

}
