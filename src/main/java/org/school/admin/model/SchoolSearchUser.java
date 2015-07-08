package org.school.admin.model;

// Generated Jul 1, 2015 3:26:07 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

/**
 * SchoolSearchUser generated by hbm2java
 */
@Entity
@Table(name = "school_search_user")
public class SchoolSearchUser implements java.io.Serializable {

	private Integer id;
	
	@NotNull(message="First name can not be empty.")
	private String firstName;
	
	@NotNull(message="Last name can not be empty.")
	private String lastName;
	
	@NotNull(message="Email can not be empty.")
	@Email(message="Email is not valid.")
	private String email;
	
	@NotNull(message="Mobile number can not be empty.")
	@Pattern(regexp="[\\d]{10}",message="Mobile number should be valid 10 digit number.")
	private String mobile;
	private String password;
	private Byte status;

	public SchoolSearchUser() {
	}

	public SchoolSearchUser(String firstName, String email) {
		this.firstName = firstName;
		this.email = email;
	}

	public SchoolSearchUser(String firstName, String lastName, String email,
			String mobile, String password, Byte status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
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

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", nullable = false, length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", length = 10)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "password", length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
