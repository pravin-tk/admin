package org.school.admin.data;

import java.util.List;

public class FeeDetail {
	private int id;
	private String className;
	private List<SchoolFee> fees;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<SchoolFee> getFees() {
		return fees;
	}
	public void setFees(List<SchoolFee> fees) {
		this.fees = fees;
	}
	
}
