package com.dfwpts.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;
	private int docid;
	private String fname;
	private String lname;
	private String group;
	private String npi;
	private String phone;
	private String fax;
	private String address;
	private String city;
	private String zip;
	private String email;
	
	public int getDocID() {
		return docid;
	}

	public void setDocID(int docid) {
		this.docid = docid;
	}

	public String getFName() {
		return fname;
	}
	
	public void setFName(String fname) {
		this.fname = fname;
	}
	
	public String getLName() {
		return lname;
	}
	
	public void setLName(String lname) {
		this.lname = lname;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getNPI() {
		return npi;
	}
	
	public void setNPI(String npi) {
		this.npi = npi;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
