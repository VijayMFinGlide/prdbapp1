package com.dfwpts.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PreemptiveVisit implements Serializable {

	private static final long serialVersionUID = -9155892411575332898L;
	
	private int tid;
	private Date startDate;
	private Date endDate;
	private String holiday;
	

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTid() {
		return this.tid;
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
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getHoliday() {
		return this.holiday;
	}
	
}