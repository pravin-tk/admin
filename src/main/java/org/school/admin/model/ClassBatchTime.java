package org.school.admin.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "class_batch_time")
public class ClassBatchTime implements java.io.Serializable{

	private int id;
	private ClassInfo classInfo;
	private Time batchTimeFrom;
	private Time batchTimeTo;
	
	public ClassBatchTime() {
		
	}
	public ClassBatchTime(int id, ClassInfo classInfo, Time batchTimeFrom,
			Time batchTimeTo) {
		super();
		this.id = id;
		this.classInfo = classInfo;
		this.batchTimeFrom = batchTimeFrom;
		this.batchTimeTo = batchTimeTo;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "class_id")
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}
	
	@Column(name = "batch_time_from", length = 8)
	public Time getBatchTimeFrom() {
		return batchTimeFrom;
	}
	public void setBatchTimeFrom(Time batchTimeFrom) {
		this.batchTimeFrom = batchTimeFrom;
	}
	
	@Column(name = "batch_time_to", length = 8)
	public Time getBatchTimeTo() {
		return batchTimeTo;
	}
	public void setBatchTimeTo(Time batchTimeTo) {
		this.batchTimeTo = batchTimeTo;
	}
}
