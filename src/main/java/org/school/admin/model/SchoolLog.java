package org.school.admin.model;

// Generated 26 Jul, 2015 5:06:14 PM by Hibernate Tools 4.3.1

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
 * SchoolLog generated by hbm2java
 */
@Entity
@Table(name = "school_log")
public class SchoolLog implements java.io.Serializable {

	private Integer id;
	private AdminUser adminUser;
	private School school;
	private String reason;
	private String log;
	private Date logDate;
	private Date logTime;

	public SchoolLog() {
	}

	public SchoolLog(AdminUser adminUser, School school, String reason,
			String log, Date logDate, Date logTime) {
		this.adminUser = adminUser;
		this.school = school;
		this.reason = reason;
		this.log = log;
		this.logDate = logDate;
		this.logTime = logTime;
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
	@JoinColumn(name = "user_id")
	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Column(name = "reason", length = 65535)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "log", length = 45)
	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "log_date", length = 10)
	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "log_time", length = 8)
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}