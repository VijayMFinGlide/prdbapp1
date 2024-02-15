package com.dfwpts.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	private String line1;
	private String city;
	private String state;	
	private String zip;
	private String reason;
	private int verified;
	
	public String getLine1() {
		return line1;
	}
	
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getVerified() {
		return verified;
	}
	
	public void setVerified(int verified) {
		this.verified = verified;
	}
}
