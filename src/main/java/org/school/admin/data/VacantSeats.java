package org.school.admin.data;

public class VacantSeats {
	private Integer schoolId;
	private Short standardId;
	private Short vacantSeat;
	
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Short getStandardId() {
		return standardId;
	}
	public void setStandardId(Short standardId) {
		this.standardId = standardId;
	}
	public Short getVacantSeat() {
		return vacantSeat;
	}
	public void setVacantSeat(Short vacantSeat) {
		this.vacantSeat = vacantSeat;
	}
}