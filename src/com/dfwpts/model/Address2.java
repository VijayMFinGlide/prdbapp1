package com.dfwpts.model;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String line1;
	private String city;
	private String state;	
	private String zip;
	private String type;
	private String lat;
	private String longs;
	private Date start;
	private Date end;
	private int tp;
	
	
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLongs() {
		return longs;
	}

	public void setLongs(String longs) {
		this.longs = longs;
	}
	
	
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}
}
