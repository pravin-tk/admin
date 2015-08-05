package org.school.admin.data;

import java.util.List;
import java.util.Set;

import org.school.admin.model.AdminUser;
import org.school.admin.model.ClassAccessories;
import org.school.admin.model.ClassFee;
import org.school.admin.model.ClassInfo;
import org.school.admin.model.ClassSection;
import org.school.admin.model.ClassSubjects;

public class ClassDetail {
	private ClassInfo classInfo;
	private ClassSection classSection;
	//private String strReason;
	//private AdminUser adminUser;
	private Set<ClassFee> classFee;
	private Set<ClassAccessories> classAccessories;
	private Set<ClassSubjects> classSubjects;
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}
	public ClassSection getClassSection() {
		return classSection;
	}
	public void setClassSection(ClassSection classSection) {
		this.classSection = classSection;
	}

	public Set<ClassAccessories> getClassAccessories() {
		return classAccessories;
	}
	public void setClassAccessories(Set<ClassAccessories> classAccessories) {
		this.classAccessories = classAccessories;
	}
	public Set<ClassSubjects> getClassSubjects() {
		return classSubjects;
	}
	public void setClassSubjects(Set<ClassSubjects> classSubjects) {
		this.classSubjects = classSubjects;
	}
	public Set<ClassFee> getClassFee() {
		return classFee;
	}
	public void setClassFee(Set<ClassFee> classFee) {
		this.classFee = classFee;
	}
//	public String getStrReason() {
//		return strReason;
//	}
//	public void setStrReason(String strReason) {
//		this.strReason = strReason;
//	}
//	public AdminUser getAdminUser() {
//		return adminUser;
//	}
//	public void setAdminUser(AdminUser adminUser) {
//		this.adminUser = adminUser;
//	}
}
