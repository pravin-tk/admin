package org.school.admin.model;

// Generated Jun 20, 2015 5:49:39 PM by Hibernate Tools 4.0.0

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
 * InfrastructureCategory generated by hbm2java
 */
@Entity
@Table(name = "infrastructure_category")
public class InfrastructureCategory implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<InfrastructureCategoryItem> infrastructureCategoryItems = new HashSet<InfrastructureCategoryItem>(
			0);

	public InfrastructureCategory() {
	}

	public InfrastructureCategory(String name,
			Set<InfrastructureCategoryItem> infrastructureCategoryItems) {
		this.name = name;
		this.infrastructureCategoryItems = infrastructureCategoryItems;
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

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "infrastructureCategory")
	public Set<InfrastructureCategoryItem> getInfrastructureCategoryItems() {
		return this.infrastructureCategoryItems;
	}

	public void setInfrastructureCategoryItems(
			Set<InfrastructureCategoryItem> infrastructureCategoryItems) {
		this.infrastructureCategoryItems = infrastructureCategoryItems;
	}

}
