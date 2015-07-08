package org.school.admin.data;

import java.util.List;

import org.school.admin.model.ActivityCategory;
import org.school.admin.model.InfrastructureCategory;
import org.school.admin.model.SafetyCategory;

public class SchoolFacilityData {
	private List<ActivityCategory> activityCategory;
	private List<SafetyCategory> safetyCategory;
	private List<InfrastructureCategory> infrastructureCategory;
	
	public List<ActivityCategory> getActivityCategory() {
		return activityCategory;
	}
	public void setActivityCategory(List<ActivityCategory> activityCategory) {
		this.activityCategory = activityCategory;
	}
	public List<SafetyCategory> getSafetyCategory() {
		return safetyCategory;
	}
	public void setSafetyCategory(List<SafetyCategory> safetyCategory) {
		this.safetyCategory = safetyCategory;
	}
	public List<InfrastructureCategory> getInfrastructureCategory() {
		return infrastructureCategory;
	}
	public void setInfrastructureCategory(
			List<InfrastructureCategory> infrastructureCategory) {
		this.infrastructureCategory = infrastructureCategory;
	}
	
	
}
