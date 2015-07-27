package org.school.admin.data;

import java.sql.Time;
import java.util.Date;

import org.school.admin.model.School;
import org.school.admin.model.SchoolNameList;
import org.school.admin.model.StandardType;
import org.school.admin.model.StreamType;
import org.school.admin.model.TeachingApproachType;

public class ClassInfoData {
	private StandardTypeData standardTypeData;
	private StreamTypeData streamTypeData;
	private TeachingApproachTypeData teachingApproachTypeData;
	private String eligibilityCriteria;
	private String admissionProcess;
	private String howToApply;
	private String feesPaymentTerm;
	private String specialization;
	private Short totalSeat;
	private Short vacantSeat;
	private Date admissionFrom;
	private Date admissionTo;
	private Date admissionDeadline;
	private Time morningTimeFrom;
	private Time morningTimeTo;
	private Time afternoonTimeFrom;
	private Time afternoonTimeTo;
	private Double totalFee;
	
	
	public StandardTypeData getStandardTypeData() {
		return standardTypeData;
	}
	public void setStandardTypeData(StandardTypeData standardTypeData) {
		this.standardTypeData = standardTypeData;
	}
	
	public StreamTypeData getStreamTypeData() {
		return streamTypeData;
	}
	public void setStreamTypeData(StreamTypeData streamTypeData) {
		this.streamTypeData = streamTypeData;
	}
	
	public TeachingApproachTypeData getTeachingApproachTypeData() {
		return teachingApproachTypeData;
	}
	public void setTeachingApproachTypeData(
			TeachingApproachTypeData teachingApproachTypeData) {
		this.teachingApproachTypeData = teachingApproachTypeData;
	}
	public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}
	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}
	public String getAdmissionProcess() {
		return admissionProcess;
	}
	public void setAdmissionProcess(String admissionProcess) {
		this.admissionProcess = admissionProcess;
	}
	public String getHowToApply() {
		return howToApply;
	}
	public void setHowToApply(String howToApply) {
		this.howToApply = howToApply;
	}
	public String getFeesPaymentTerm() {
		return feesPaymentTerm;
	}
	public void setFeesPaymentTerm(String feesPaymentTerm) {
		this.feesPaymentTerm = feesPaymentTerm;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public Short getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(Short totalSeat) {
		this.totalSeat = totalSeat;
	}
	public Short getVacantSeat() {
		return vacantSeat;
	}
	public void setVacantSeat(Short vacantSeat) {
		this.vacantSeat = vacantSeat;
	}
	public Date getAdmissionFrom() {
		return admissionFrom;
	}
	public void setAdmissionFrom(Date admissionFrom) {
		this.admissionFrom = admissionFrom;
	}
	public Date getAdmissionTo() {
		return admissionTo;
	}
	public void setAdmissionTo(Date admissionTo) {
		this.admissionTo = admissionTo;
	}
	public Date getAdmissionDeadline() {
		return admissionDeadline;
	}
	public void setAdmissionDeadline(Date admissionDeadline) {
		this.admissionDeadline = admissionDeadline;
	}
	public Time getMorningTimeFrom() {
		return morningTimeFrom;
	}
	public void setMorningTimeFrom(Time morningTimeFrom) {
		this.morningTimeFrom = morningTimeFrom;
	}
	public Time getMorningTimeTo() {
		return morningTimeTo;
	}
	public void setMorningTimeTo(Time morningTimeTo) {
		this.morningTimeTo = morningTimeTo;
	}
	public Time getAfternoonTimeFrom() {
		return afternoonTimeFrom;
	}
	public void setAfternoonTimeFrom(Time afternoonTimeFrom) {
		this.afternoonTimeFrom = afternoonTimeFrom;
	}
	public Time getAfternoonTimeTo() {
		return afternoonTimeTo;
	}
	public void setAfternoonTimeTo(Time afternoonTimeTo) {
		this.afternoonTimeTo = afternoonTimeTo;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

}
