package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;



import java.io.Serializable;
import java.util.Date;


@XmlRootElement
public class PatientAction implements Serializable {

	private static final long serialVersionUID = 1L;
	private int actionid;
	private int pid;
	private String createdBy;
	private Date createdAt;
	private String actionRequired;
	private String actionTaken;
	private int completed;
	private Date completedOn;

	public void setActionid(int actionid) {
		this.actionid = actionid;
	}

	public int getActionid() {
		return this.actionid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return this.pid;
	}
	
	public void setCreatedby(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedby() {
		return this.createdBy;
	}

	public void setCreatedat(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCreatedat() {
		return this.createdAt;
	}

	public void setActionRequired(String actionRequired) {
		this.actionRequired = actionRequired;
	}

	public String getActionRequired() {
		return this.actionRequired;
	}
	
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getActionTaken() {
		return this.actionTaken;
	}
	
	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getCompleted() {
		return this.completed;
	}
	
	
	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	public Date getCompletedOn() {
		return this.completedOn;
	}
	
	
}
