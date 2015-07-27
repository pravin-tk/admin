package org.school.admin.data;

import java.util.List;

public class SearchFilter {
	private String categoryName;
	private List<MainFilter> filter;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<MainFilter> getFilter() {
		return filter;
	}
	public void setFilter(List<MainFilter> filter) {
		this.filter = filter;
	}
	
}
