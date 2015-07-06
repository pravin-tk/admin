package org.school.admin.model;

// Generated Jun 15, 2015 2:47:47 PM by Hibernate Tools 4.0.0

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

/**
 * AdminUserRole generated by hbm2java
 */
@Entity
@Table(name = "admin_user_role")
public class AdminUserRole implements java.io.Serializable {

	private Integer id;
	private String roleName;
	private Byte status;
	private Set<AdminUser> adminUsers = new HashSet<AdminUser>(0);

	public AdminUserRole() {
	}

	public AdminUserRole(String roleName, Byte status, Set<AdminUser> adminUsers) {
		this.roleName = roleName;
		this.status = status;
		this.adminUsers = adminUsers;
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

	@Column(name = "role_name", length = 45)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminUserRole")
	public Set<AdminUser> getAdminUsers() {
		return this.adminUsers;
	}

	public void setAdminUsers(Set<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}

}
