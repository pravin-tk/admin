package org.school.admin.data;

public class Rating {
	private Short catid;
	private String name;
	private double rating;
	public Short getCatid() {
		return catid;
	}
	public void setCatid(Short catid) {
		this.catid = catid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
