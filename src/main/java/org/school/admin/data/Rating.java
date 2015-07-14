package org.school.admin.data;

public class Rating {
	private Short catid;
	private String name;
	private double rating;
	private String image;
	private Long ratingCount;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(Long ratingCount) {
		this.ratingCount = ratingCount;
	}
	
}
