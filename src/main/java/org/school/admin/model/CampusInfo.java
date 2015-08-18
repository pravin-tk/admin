package org.school.admin.model;

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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="campus_info")
public class CampusInfo {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name = "name", length = 100)
	private String name;
	@Column(name = "campus_size")
	private double campusSize;
	@Column(name = "total_buildings")
	private int totalBuildings;
	@Column(name = "total_playgrounds")
	private int totalPlaygrounds;
	@Column(name = "total_boys")
	private int totalBoys;
	@Column(name = "total_girls")
	private int totalGirls;
	@Column(name = "total_male_teacher")
	private int totalMaleTeacher;
	@Column(name = "total_female_teacher")
	private int totalFemaleTeacher;
	@Column(name = "male_supporting_staff")
	private int maleSupportingStaff;
	@Column(name = "female_supporting_staff")
	private int femaleSupportingStaff;
	@Column(name = "total_students")
	private int totalStudents;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", length = 19)
	private Date lastUpdatedOn;
	@Column(name = "last_updated_by")
	private int lastUpdatedBy;
	
	
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "campus_size_unit_id")
	private AreaUnit areaUnit;
	
	
	
	public School getSchool() {
		return school;
	}
	public void setSchool(School School) {
		this.school = School;
	}
	public AreaUnit getAreaUnit() {
		return areaUnit;
	}
	public void setAreaUnit(AreaUnit areaUnit) {
		this.areaUnit = areaUnit;
	}
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
	public double getCampusSize() {
		return campusSize;
	}
	public void setCampusSize(double campusSize) {
		this.campusSize = campusSize;
	}
	public int getTotalBuildings() {
		return totalBuildings;
	}
	public void setTotalBuildings(int totalBuildings) {
		this.totalBuildings = totalBuildings;
	}
	public int getTotalPlaygrounds() {
		return totalPlaygrounds;
	}
	public void setTotalPlaygrounds(int totalPlaygrounds) {
		this.totalPlaygrounds = totalPlaygrounds;
	}
	public int getTotalBoys() {
		return totalBoys;
	}
	public void setTotalBoys(int totalBoys) {
		this.totalBoys = totalBoys;
	}
	public int getTotalGirls() {
		return totalGirls;
	}
	public void setTotalGirls(int totalGirls) {
		this.totalGirls = totalGirls;
	}
	public int getTotalMaleTeacher() {
		return totalMaleTeacher;
	}
	public void setTotalMaleTeacher(int totalMaleTeacher) {
		this.totalMaleTeacher = totalMaleTeacher;
	}
	public int getTotalFemaleTeacher() {
		return totalFemaleTeacher;
	}
	public void setTotalFemaleTeacher(int totalFemaleTeacher) {
		this.totalFemaleTeacher = totalFemaleTeacher;
	}
	public int getMaleSupportingStaff() {
		return maleSupportingStaff;
	}
	public void setMaleSupportingStaff(int maleSupportingStaff) {
		this.maleSupportingStaff = maleSupportingStaff;
	}
	public int getFemaleSupportingStaff() {
		return femaleSupportingStaff;
	}
	public void setFemaleSupportingStaff(int femaleSupportingStaff) {
		this.femaleSupportingStaff = femaleSupportingStaff;
	}
	public int getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public int getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	
}
