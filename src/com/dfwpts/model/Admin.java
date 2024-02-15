package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.*;

@XmlRootElement
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	private int aid;
	private String username;
	private String firstName;
	private String lastName;
	private String sex;
	private Date birthDate;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	private String ssn;
	private String password;
	
	public int getAid() {
		return aid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getSex() {
		return sex;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
