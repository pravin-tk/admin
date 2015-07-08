package org.school.admin.data;

import java.util.List;
import java.util.Set;

import org.school.admin.model.SchoolHighlight;

public class SchoolCompleteDetail {
	private List<SchoolTimelineData> schoolTimelineData;
	private List<SchoolFacilityData> schoolFacilityData;

	public List<SchoolTimelineData> getSchoolTimelineData() {
		return schoolTimelineData;
	}

	public void setSchoolTimelineData(List<SchoolTimelineData> schoolTimelineData) {
		this.schoolTimelineData = schoolTimelineData;
	}
}
