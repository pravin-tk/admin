package org.school.admin.data;

import java.util.List;

public class InfraCategory {
	private int id;
	private String name;
	private String description;
	private List<InfraItem> items;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<InfraItem> getItems() {
		return items;
	}
	public void setItems(List<InfraItem> items) {
		this.items = items;
	}

}
