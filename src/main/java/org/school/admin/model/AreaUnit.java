package org.school.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
@Table(name="area_unit")
public class AreaUnit {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "name", length = 200)
	private String name;
	@Column(name = "last_updated_by")
	private int last_updated_by;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_on", length = 19)
	private Date last_updated_on;
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
	public int getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(int last_updated_by) {
		this.last_updated_by = last_updated_by;
	}
	public Date getLast_updated_on() {
		return last_updated_on;
	}
	public void setLast_updated_on(Date last_updated_on) {
		this.last_updated_on = last_updated_on;
	}
	
}
