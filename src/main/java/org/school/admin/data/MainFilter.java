package org.school.admin.data;

public class MainFilter {
	private int id;
	private String name;
	private boolean isFiltered;
	private String paramName;
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
	public boolean isFiltered() {
		return isFiltered;
	}
	public void setFiltered(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}
