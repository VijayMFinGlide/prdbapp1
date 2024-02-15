package com.dfwpts.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Discharge implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int pid;
	private int reasonCode;
	private String notes;
	private Date date;

	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getReasonCode() {
		return reasonCode;
	}
	
	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
