package com.dfwpts.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MakeupVisit implements Serializable {

	private static final long serialVersionUID = -9155892411575332898L;
	
	private int pid;
	private Date startDate;
	private Date endDate;

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return this.pid;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}
}