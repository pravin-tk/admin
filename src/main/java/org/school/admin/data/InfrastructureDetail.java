package org.school.admin.data;

import java.util.Set;

import org.school.admin.model.School;
import org.school.admin.model.SchoolActivityCatItem;
import org.school.admin.model.SchoolInfrastructureCatItem;
import org.school.admin.model.SchoolSafetyCatItem;

public class InfrastructureDetail {
	private Set<SchoolActivityCatItem> schoolActivityCatItems;
	private Set<SchoolSafetyCatItem> safetyCatItems;
	private Set<SchoolInfrastructureCatItem> schoolInfrastructureCatItems;
	private School school;
	public Set<SchoolActivityCatItem> getSchoolActivityCatItems() {
		return schoolActivityCatItems;
	}
	public void setSchoolActivityCatItems(
			Set<SchoolActivityCatItem> schoolActivityCatItems) {
		this.schoolActivityCatItems = schoolActivityCatItems;
	}
	public Set<SchoolSafetyCatItem> getSafetyCatItems() {
		return safetyCatItems;
	}
	public void setSafetyCatItems(Set<SchoolSafetyCatItem> safetyCatItems) {
		this.safetyCatItems = safetyCatItems;
	}
	public Set<SchoolInfrastructureCatItem> getSchoolInfrastructureCatItems() {
		return schoolInfrastructureCatItems;
	}
	public void setSchoolInfrastructureCatItems(
			Set<SchoolInfrastructureCatItem> schoolInfrastructureCatItems) {
		this.schoolInfrastructureCatItems = schoolInfrastructureCatItems;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	
	

}
