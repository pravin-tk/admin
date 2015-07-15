package org.school.admin.model;

// Generated Jul 10, 2015 5:21:17 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * UserRegistrationInfo generated by hbm2java
 */
@Entity
@Table(name = "user_registration_info")
public class UserRegistrationInfo implements java.io.Serializable {

	private Integer id;
	@NotNull(message="First name can not be empty.")
	private String firstName;
	private String lastName;
	@Pattern(regexp="[\\d]{10}||[\\d]{0}",message="Mobile number should be valid 10 digit number.")
	private String mobile;
	private String password;
	@NotNull(message="Email can not be empty.")
	private String email;
	private Byte status;
	private String memberOtp;
	private Boolean gender;
	private Date dateOfBirth;
	private String image;
	private String bloodGroup;
	private String tempAddr;
	private String permAddr;
	private String landlineNo;
	private Date lastLoggedOn;
	private Integer lastUpdatedBy;
	private Date lastUpdatedOn;
	private Set<UserRating> userRatings = new HashSet<UserRating>(0);
	private Set<SchoolReview> schoolReviews = new HashSet<SchoolReview>(0);

	public UserRegistrationInfo() {
	}

	public UserRegistrationInfo(String firstName, String lastName,
			String mobile, String password, String email, Byte status,
			String memberOtp, Boolean gender, Date dateOfBirth, String image,
			String bloodGroup, String tempAddr, String permAddr,
			String landlineNo, Date lastLoggedOn, Integer lastUpdatedBy,
			Date lastUpdatedOn, Set<UserRating> userRatings,
			Set<SchoolReview> schoolReviews) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.password = password;
		this.email = email;
		this.status = status;
		this.memberOtp = memberOtp;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
		this.bloodGroup = bloodGroup;
		this.tempAddr = tempAddr;
		this.permAddr = permAddr;
		this.landlineNo = landlineNo;
		this.lastLoggedOn = lastLoggedOn;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedOn = lastUpdatedOn;
		this.userRatings = userRatings;
		this.schoolReviews = schoolReviews;
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

	@Column(name = "first_name", length = 200)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 200)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "member_otp", length = 6)
	public String getMemberOtp() {
		return this.memberOtp;
	}

	public void setMemberOtp(String memberOtp) {
		this.memberOtp = memberOtp;
	}

	@Column(name = "gender")
	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", length = 10)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "image", length = 200)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "blood_group", length = 10)
	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Column(name = "temp_addr", length = 200)
	public String getTempAddr() {
		return this.tempAddr;
	}

	public void setTempAddr(String tempAddr) {
		this.tempAddr = tempAddr;
	}

	@Column(name = "perm_addr", length = 200)
	public String getPermAddr() {
		return this.permAddr;
	}

	public void setPermAddr(String permAddr) {
		this.permAddr = permAddr;
	}

	@Column(name = "landline_no", length = 20)
	public String getLandlineNo() {
		return this.landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_logged_on", length = 10)
	public Date getLastLoggedOn() {
		return this.lastLoggedOn;
	}

	public void setLastLoggedOn(Date lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRegistrationInfo")
	public Set<UserRating> getUserRatings() {
		return this.userRatings;
	}

	public void setUserRatings(Set<UserRating> userRatings) {
		this.userRatings = userRatings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRegistrationInfo")
	public Set<SchoolReview> getSchoolReviews() {
		return this.schoolReviews;
	}

	public void setSchoolReviews(Set<SchoolReview> schoolReviews) {
		this.schoolReviews = schoolReviews;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobile=" + mobile + ", email="
				+ email + ", status=" + status + ", image=" + image + "}";
	}
	

}
