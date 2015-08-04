package org.school.admin.data;

import java.util.Date;
import java.util.List;

public class FeeDetail {
	private int id;
	private String className;
	private String eligibilityCriteria;
	private String admissionProcess;
	private String howToApply;
	private String feesPaymentTerm;
	private Date admissionFrom;
	private Date admissionDeadline;
	private Double totalFee;
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
	public Date getAdmissionFrom() {
		return admissionFrom;
	}
	public void setAdmissionFrom(Date admissionFrom) {
		this.admissionFrom = admissionFrom;
	}
	public Date getAdmissionDeadline() {
		return admissionDeadline;
	}
	public void setAdmissionDeadline(Date admissionDeadline) {
		this.admissionDeadline = admissionDeadline;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	
}
