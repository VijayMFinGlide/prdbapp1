package com.dfwpts.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hold implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int pid;
	private int reasonCode;
	
	private Date holddate;
	private Date removedate;

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
	public Date getHoldDate() {
		return holddate;
	}

	public void setHoldDate(Date holddate) {
		this.holddate = holddate;
	}
	
	public Date getRemoveDate() {
		return removedate;
	}

	public void setRemoveDate(Date removedate) {
		this.removedate = removedate;
	}
	
	
}