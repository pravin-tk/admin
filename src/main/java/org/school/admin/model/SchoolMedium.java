package org.school.admin.model;

// Generated 30-May-2015 14:03:54 by Hibernate Tools 4.0.0

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
 * SchoolMedium generated by hbm2java
 */
@Entity
@Table(name = "school_medium")
public class SchoolMedium implements java.io.Serializable {

	private Integer id;
	private MediumType mediumType;
	private School school;
	private Date lastUpdatedOn;
	private int lastUpdatedBy;

	public SchoolMedium() {
	}

	public SchoolMedium(MediumType mediumType, School school,
			Date lastUpdatedOn, int lastUpdatedBy) {
		this.mediumType = mediumType;
		this.school = school;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer schoolMediumId) {
		this.id = schoolMediumId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medium_id", nullable = false)
	public MediumType getMediumType() {
		return this.mediumType;
	}

	public void setMediumType(MediumType mediumType) {
		this.mediumType = mediumType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id", nullable = false)
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", nullable = false, length = 19)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Column(name = "last_updated_by", nullable = false)
	public int getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}