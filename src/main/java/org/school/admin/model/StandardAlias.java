package org.school.admin.model;

// Generated 14 Jul, 2015 12:36:38 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cast generated by hbm2java
 */
@Entity
@Table(name = "standard_alias")
public class StandardAlias implements java.io.Serializable {

	private Short id;
	private StandardType standardType;
	private String name;

	public StandardAlias() {
	}

	public StandardAlias(String name) {
		this.name = name;
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

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "standard_id")
	public StandardType getStandardType() {
		return standardType;
	}

	public void setStandardType(StandardType standardType) {
		this.standardType = standardType;
	}
	
	

}
