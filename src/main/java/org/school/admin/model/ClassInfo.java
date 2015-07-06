package org.school.admin.model;

// Generated 24 Jun, 2015 3:11:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * ClassInfo generated by hbm2java
 */
@Entity
@Table(name = "class_info", uniqueConstraints = @UniqueConstraint(columnNames = {
		"school_id", "standard_id", "ta_id","stream_type_id"}))
public class ClassInfo implements java.io.Serializable {

	private Integer id;
	private School school;
	private StandardType standardType;
	private StreamType streamType;
	private TeachingApproachType teachingApproachType;
	private String eligibilityCriteria;
	private String admissionProcess;
	private String howToApply;
	private String feesPaymentTerm;
	private String specialization;
	private Integer lastUpdatedBy;
	private Date lastUpdatedOn;
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

	public ClassInfo() {
	}

	public ClassInfo(School school, StandardType standardType,
			StreamType streamType, TeachingApproachType teachingApproachType,
			String eligibilityCriteria, String admissionProcess,
			String howToApply, String feesPaymentTerm, String specialization,
			Integer lastUpdatedBy, Date lastUpdatedOn, Short totalSeat,
			Short vacantSeat, Date admissionFrom, Date admissionTo,
			Date admissionDeadline, Time morningTimeFrom, Time morningTimeTo,
			Time afternoonTimeFrom, Time afternoonTimeTo, Double totalFee) {
		this.school = school;
		this.standardType = standardType;
		this.streamType = streamType;
		this.teachingApproachType = teachingApproachType;
		this.eligibilityCriteria = eligibilityCriteria;
		this.admissionProcess = admissionProcess;
		this.howToApply = howToApply;
		this.feesPaymentTerm = feesPaymentTerm;
		this.specialization = specialization;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedOn = lastUpdatedOn;
		this.totalSeat = totalSeat;
		this.vacantSeat = vacantSeat;
		this.admissionFrom = admissionFrom;
		this.admissionTo = admissionTo;
		this.admissionDeadline = admissionDeadline;
		this.morningTimeFrom = morningTimeFrom;
		this.morningTimeTo = morningTimeTo;
		this.afternoonTimeFrom = afternoonTimeFrom;
		this.afternoonTimeTo = afternoonTimeTo;
		this.totalFee = totalFee;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "standard_id")
	public StandardType getStandardType() {
		return this.standardType;
	}

	public void setStandardType(StandardType standardType) {
		this.standardType = standardType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stream_type_id")
	public StreamType getStreamType() {
		return this.streamType;
	}

	public void setStreamType(StreamType streamType) {
		this.streamType = streamType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ta_id")
	public TeachingApproachType getTeachingApproachType() {
		return this.teachingApproachType;
	}

	public void setTeachingApproachType(
			TeachingApproachType teachingApproachType) {
		this.teachingApproachType = teachingApproachType;
	}

	@Column(name = "eligibility_criteria")
	public String getEligibilityCriteria() {
		return this.eligibilityCriteria;
	}

	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	@Column(name = "admission_process")
	public String getAdmissionProcess() {
		return this.admissionProcess;
	}

	public void setAdmissionProcess(String admissionProcess) {
		this.admissionProcess = admissionProcess;
	}

	@Column(name = "how_to_apply", length = 16277215)
	public String getHowToApply() {
		return this.howToApply;
	}

	public void setHowToApply(String howToApply) {
		this.howToApply = howToApply;
	}

	@Column(name = "fees_payment_term", length = 16277215)
	public String getFeesPaymentTerm() {
		return this.feesPaymentTerm;
	}

	public void setFeesPaymentTerm(String feesPaymentTerm) {
		this.feesPaymentTerm = feesPaymentTerm;
	}

	@Column(name = "specialization", length = 250)
	public String getSpecialization() {
		return this.specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Column(name = "last_updated_by")
	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated_on", length = 10)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Column(name = "total_seat")
	public Short getTotalSeat() {
		return this.totalSeat;
	}

	public void setTotalSeat(Short totalSeat) {
		this.totalSeat = totalSeat;
	}

	@Column(name = "vacant_seat")
	public Short getVacantSeat() {
		return this.vacantSeat;
	}

	public void setVacantSeat(Short vacantSeat) {
		this.vacantSeat = vacantSeat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "admission_from", length = 10)
	public Date getAdmissionFrom() {
		return this.admissionFrom;
	}

	public void setAdmissionFrom(Date admissionFrom) {
		this.admissionFrom = admissionFrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "admission_to", length = 10)
	public Date getAdmissionTo() {
		return this.admissionTo;
	}

	public void setAdmissionTo(Date admissionTo) {
		this.admissionTo = admissionTo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "admission_deadline", length = 10)
	public Date getAdmissionDeadline() {
		return this.admissionDeadline;
	}

	public void setAdmissionDeadline(Date admissionDeadline) {
		this.admissionDeadline = admissionDeadline;
	}
	@Column(name = "morning_time_from", length = 8)
	public Time getMorningTimeFrom() {
		return this.morningTimeFrom;
	}

	public void setMorningTimeFrom(Time morningTimeFrom) {
		this.morningTimeFrom = morningTimeFrom;
	}

	@Column(name = "morning_time_to", length = 8)
	public Time getMorningTimeTo() {
		return this.morningTimeTo;
	}

	public void setMorningTimeTo(Time morningTimeTo) {
		this.morningTimeTo = morningTimeTo;
	}

	@Column(name = "afternoon_time_from", length = 8)
	public Time getAfternoonTimeFrom() {
		return this.afternoonTimeFrom;
	}

	public void setAfternoonTimeFrom(Time afternoonTimeFrom) {
		this.afternoonTimeFrom = afternoonTimeFrom;
	}

	@Column(name = "afternoon_time_to", length = 8)
	public Time getAfternoonTimeTo() {
		return this.afternoonTimeTo;
	}

	public void setAfternoonTimeTo(Time afternoonTimeTo) {
		this.afternoonTimeTo = afternoonTimeTo;
	}

	@Column(name= "total_fee")
	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

}
