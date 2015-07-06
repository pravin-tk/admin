package org.school.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;
@Entity
@XmlRootElement
@Table(name="school")
public class SchoolCreation {

	@Id
	@GeneratedValue
	@Column(name="school_id")
	private int school_id;
	private String school_name, alias, tag_line, about_school, logo;
	private String plot_no;
	private String street_name;
	@ManyToOne
	 @JoinColumn(name = "locality_id")
	private Locality locality;
	private int created_by, last_updated_by;
	private String landmark;
	private String pincode;
	private float latitude, longitude;
	private Timestamp live_date, last_updated_on;
	private byte status, establishment_type;
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTag_line() {
		return tag_line;
	}
	public void setTag_line(String tag_line) {
		this.tag_line = tag_line;
	}
	public String getAbout_school() {
		return about_school;
	}
	public void setAbout_school(String about_school) {
		this.about_school = about_school;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPlot_no() {
		return plot_no;
	}
	public void setPlot_no(String plot_no) {
		this.plot_no = plot_no;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	
	public Locality getLocality() {
		return locality;
	}
	public void setLocality(Locality locality) {
		this.locality = locality;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public int getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(int last_updated_by) {
		this.last_updated_by = last_updated_by;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public Timestamp getLive_date() {
		return live_date;
	}
	public void setLive_date(Timestamp live_date) {
		this.live_date = live_date;
	}
	public Timestamp getLast_updated_on() {
		return last_updated_on;
	}
	public void setLast_updated_on(Timestamp last_updated_on) {
		this.last_updated_on = last_updated_on;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public byte getEstablishment_type() {
		return establishment_type;
	}
	public void setEstablishment_type(byte establishment_type) {
		this.establishment_type = establishment_type;
	}
	
	

}
