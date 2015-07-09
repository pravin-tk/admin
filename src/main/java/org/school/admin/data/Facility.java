package org.school.admin.data;

import java.util.List;

public class Facility {
	private List<InfraCategory> activity;
	private List<InfraCategory> safety;
	private List<InfraCategory> infra;
	public List<InfraCategory> getActivity() {
		return activity;
	}
	public void setActivity(List<InfraCategory> activity) {
		this.activity = activity;
	}
	public List<InfraCategory> getSafety() {
		return safety;
	}
	public void setSafety(List<InfraCategory> safety) {
		this.safety = safety;
	}
	public List<InfraCategory> getInfra() {
		return infra;
	}
	public void setInfra(List<InfraCategory> infra) {
		this.infra = infra;
	}
	
}
