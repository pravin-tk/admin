package org.school.admin.data;

import java.util.List;
import java.util.Set;

import org.school.admin.model.SchoolHighlight;
import org.school.admin.model.SchoolReview;
import org.school.admin.model.SchoolTimeline;

public class SchoolCompleteDetail {
	List<NameList> highlights;
	List<SchoolReview> reviews;
	SchoolContact contacts;
	private List<SchoolTimelineData> schoolTimelineData;
	private List<SchoolFacilityData> schoolFacilityData;

	public List<SchoolTimelineData> getSchoolTimelineData() {
		return schoolTimelineData;
	}

	public void setSchoolTimelineData(List<SchoolTimelineData> schoolTimelineData) {
		this.schoolTimelineData = schoolTimelineData;
	}

	public List<NameList> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<NameList> highlights) {
		this.highlights = highlights;
	}

	public List<SchoolReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<SchoolReview> reviews) {
		this.reviews = reviews;
	}

	public SchoolContact getContacts() {
		return contacts;
	}

	public void setContacts(SchoolContact contacts) {
		this.contacts = contacts;
	}

	public List<SchoolFacilityData> getSchoolFacilityData() {
		return schoolFacilityData;
	}

	public void setSchoolFacilityData(List<SchoolFacilityData> schoolFacilityData) {
		this.schoolFacilityData = schoolFacilityData;
	}
	
}
