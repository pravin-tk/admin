package org.school.admin.data;

public class SearchFilter {
	private int id;
	private String itemName;
	private String categoryName;
	private boolean isFiltered;
	private String paramName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
