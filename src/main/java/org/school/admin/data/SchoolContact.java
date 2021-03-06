package org.school.admin.data;

import java.util.List;

import org.school.admin.model.ContactInfo;

public class SchoolContact {
	private int id;
	private String name;
	private String address;
	private String latitude;
	private String longitude;
	private List<ContactInfo> contacts;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<ContactInfo> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactInfo> contacts) {
		this.contacts = contacts;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
