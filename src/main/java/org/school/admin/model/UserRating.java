package org.school.admin.model;

// Generated Jul 10, 2015 5:21:17 PM by Hibernate Tools 4.0.0

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
 * UserRating generated by hbm2java
 */
@Entity
@Table(name = "user_rating")
public class UserRating implements java.io.Serializable {

	private Integer id;
	private UserRegistrationInfo userRegistrationInfo;
	private RatingCategoryType ratingCategoryType;
	private School school;
	private Float rating;
	private Date addedDate;

	public UserRating() {
	}

	public UserRating(UserRegistrationInfo userRegistrationInfo,
			RatingCategoryType ratingCategoryType, School school, Float rating,
			Date addedDate) {
		this.userRegistrationInfo = userRegistrationInfo;
		this.ratingCategoryType = ratingCategoryType;
		this.school = school;
		this.rating = rating;
		this.addedDate = addedDate;
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
	@JoinColumn(name = "user_id")
	public UserRegistrationInfo getUserRegistrationInfo() {
		return this.userRegistrationInfo;
	}

	public void setUserRegistrationInfo(
			UserRegistrationInfo userRegistrationInfo) {
		this.userRegistrationInfo = userRegistrationInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rating_category_type_id")
	public RatingCategoryType getRatingCategoryType() {
		return this.ratingCategoryType;
	}

	public void setRatingCategoryType(RatingCategoryType ratingCategoryType) {
		this.ratingCategoryType = ratingCategoryType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Column(name = "rating", precision = 9, scale = 3)
	public Float getRating() {
		return this.rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "added_date", length = 10)
	public Date getAddedDate() {
		return this.addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

}