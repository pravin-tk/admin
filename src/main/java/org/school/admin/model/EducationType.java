package org.school.admin.model;

// Generated Jun 10, 2015 6:01:42 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EducationType generated by hbm2java
 */
@Entity
@Table(name = "education_type")
public class EducationType implements java.io.Serializable {

	private Short id;
	private String title;
	private String description;
	private Date lastUpdatedOn;
	private Integer lastUpdatedBy;
	private String shortTitle;
	private Byte sortOrder;

	public EducationType() {
	}

	public EducationType(String title, String description, Date lastUpdatedOn,
			Integer lastUpdatedBy, String shortTitle, Byte sortOrder) {
		this.title = title;
		this.description = description;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
		this.shortTitle = shortTitle;
		this.sortOrder = sortOrder;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 400)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "short_title", length = 200)
	public String getShortTitle() {
		return this.shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	@Column(name = "sort_order")
	public Byte getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Byte sortOrder) {
		this.sortOrder = sortOrder;
	}

}
