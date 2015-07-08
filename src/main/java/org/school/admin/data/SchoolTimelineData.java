package org.school.admin.data;

import java.util.List;
import java.util.Set;

import org.school.admin.model.SchoolTimelineMilestone;

public class SchoolTimelineData {
	private Integer id;
	private int year;
	private String title;
	private String image;
	
	private List<SchoolTimelineMilestone> milestones;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<SchoolTimelineMilestone> getMilestones() {
		return milestones;
	}
	public void setMilestones(List<SchoolTimelineMilestone> milestones) {
		this.milestones = milestones;
	}
	
}
