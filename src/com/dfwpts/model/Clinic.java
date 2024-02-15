package com.dfwpts.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Clinic implements Serializable {
	private static final long serialVersionUID = 1L;
	private int mlid;
	private String name;
	private String address;
	private String city;
	private String zip;
	private String contact1;
	private String title1;
	private String contact2;
	private String title2;
	private String phone;
	private String fax;
	private String email;
	private String website;

	public int getMLID() {
		return mlid;
	}

	public void setMLID(int mlid) {
		this.mlid = mlid;
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

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getContact1() {
		return contact1;
	}
	
	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getTitle1() {
		return title1;
	}
	
	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getContact2() {
		return contact2;
	}
	
	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getTitle2() {
		return title2;
	}
	
	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}

}
