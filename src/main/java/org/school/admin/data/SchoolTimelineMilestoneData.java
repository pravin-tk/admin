package org.school.admin.data;

import org.school.admin.model.SchoolTimeline;

public class SchoolTimelineMilestoneData {
	private Integer id;
	private SchoolTimeline schoolTimeline;
	private String title;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SchoolTimeline getSchoolTimeline() {
		return schoolTimeline;
	}
	public void setSchoolTimeline(SchoolTimeline schoolTimeline) {
		this.schoolTimeline = schoolTimeline;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
