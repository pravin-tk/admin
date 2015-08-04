package org.school.admin.data;

import java.util.Date;

public class ClassFeeInfo {
	private int id;
	private String name;
	private int feeId;
	private String feeName;
	private Double amount;
	private String eligibilityCriteria;
	private String admissionProcess;
	private String howToApply;
	private String feesPaymentTerm;
	private Date admissionFrom;
	private Date admissionDeadline;
	private Double totalFee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
