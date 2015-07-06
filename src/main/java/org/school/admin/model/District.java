package org.school.admin.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District implements java.io.Serializable {
	
	private Integer id;
	private State state;
	private String name;
	private byte status;
	private Integer sortOrder;
	
	private Set<Tehsil> tehsils = new HashSet<Tehsil>(0);
	
	public District() {
	}

	public District(State state, String name, Integer sortOrder,
			byte status, Set<Tehsil> tehsils) {
		this.state = state;
		this.name = name;
		this.sortOrder = sortOrder;
		this.status = status;
		this.tehsils = tehsils;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	@Column(name = "name", length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
	@Column(name = "sort_order")
	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Tehsil> getTehsils() {
		return tehsils;
	}

	public void setTehsils(Set<Tehsil> tehsils) {
		this.tehsils = tehsils;
	}
	
	
	
}
