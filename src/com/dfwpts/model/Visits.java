
package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class Visits implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vid;
	private int pid;
	private int tid;
	private int superId;
	private Date vdate;
	private Date startTime;
	private Date endTime;
	private int type;
	
	
	
	
	public int getVid() {
		return vid;
	}
	
	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}

	

	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getSuperId() {
		return superId;
	}
	
	public void setSuperId(int superId) {
		this.superId = superId;
	}
	
	public Date getVDate() {
		return vdate;
	}
	
	public void setVDate(Date vdate) {
		this.vdate = vdate;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	

	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	
	

	public int getVisitTime() {
		return (int)((endTime.getTime() - startTime.getTime()) / (60 * 1000) % 60);
	}
}
