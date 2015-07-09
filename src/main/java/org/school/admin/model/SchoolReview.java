package org.school.admin.model;

// Generated Jul 8, 2015 4:14:43 PM by Hibernate Tools 4.0.0

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
 * SchoolReview generated by hbm2java
 */
@Entity
@Table(name = "school_review")
public class SchoolReview implements java.io.Serializable {

	private Integer id;
	private School school;
	private String title;
	private String review;
	private Integer userid;
	private Date date;
	private Date time;
	private Byte status;

	public SchoolReview() {
	}

	public SchoolReview(School school, String title, String review,
			Integer userid, Date date, Date time, Byte status) {
		this.school = school;
		this.title = title;
		this.review = review;
		this.userid = userid;
		this.date = date;
		this.time = time;
		this.status = status;
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

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "review", length = 65535)
	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "time", length = 8)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}