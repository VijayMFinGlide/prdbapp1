package com.dfwpts.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mileage implements Serializable {

	private static final long serialVersionUID = -9155892411575332898L;
	
	private int tid;
	private Double mile;
	private Date vdate;
	private String response;

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTid() {
		return this.tid;
	}

	public void setMile(Double mile) {
		this.mile = mile;
	}

	public Double getMile() {
		return this.mile;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getVdate() {
		return this.vdate;
	}
	
	
	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return this.response;
	}
	
}
