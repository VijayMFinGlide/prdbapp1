package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vid;
	private int pid;
	private int freq;
	private int tid;
	private int superId;
	private Date vdate;
	private Date startTime;
	private Date endTime;
	private int approved;
	private int billed;
	private int type;
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private String imageUrl4;
	private String imageUrl5;
	private String imageUrl6;
	private String imageUrl7;
	private String imageUrl8;
	private int saturday;
	private int free;
	private int late;
	private String proCode;
	
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

	public int getFreq() {
		return freq;
	}
	
	public void setFreq(int freq) {
		this.freq = freq;
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

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		if(approved == 0 || approved ==1) {
			this.approved = approved;
		}
	}
	
	public int getBilled() {
		return billed;
	}

	public void setBilled(int billed) {
		if(billed == 0 || billed == 1) {
			this.billed = billed;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getImageUrl1() {
		return imageUrl1;
	}
	
	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}
	
	public String getImageUrl2() {
		return imageUrl2;
	}
	
	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}
	
	public String getImageUrl3() {
		return imageUrl3;
	}
	
	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}

	public String getImageUrl4() {
		return imageUrl4;
	}
	
	public void setImageUrl4(String imageUrl4) {
		this.imageUrl4 = imageUrl4;
	}
	
	public String getImageUrl5() {
		return imageUrl5;
	}
	
	public void setImageUrl5(String imageUrl5) {
		this.imageUrl5 = imageUrl5;
	}

	public String getImageUrl6() {
		return imageUrl6;
	}
	
	public void setImageUrl6(String imageUrl6) {
		this.imageUrl6 = imageUrl6;
	}

	public String getImageUrl7() {
		return imageUrl7;
	}
	
	public void setImageUrl7(String imageUrl7) {
		this.imageUrl7 = imageUrl7;
	}

	public String getImageUrl8() {
		return imageUrl8;
	}
	
	public void setImageUrl8(String imageUrl8) {
		this.imageUrl8 = imageUrl8;
	}

	public int getSaturday() {
		return saturday;
	}
	
	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}
	
	public int getFree() {
		return free;
	}
	
	public void setFree(int free) {
		this.free = free;
	}

	public int getLate() {
		return late;
	}
	
	public void setLate(int late) {
		this.late = late;
	}
	public String getproCode() {
		return proCode;
	}
	
	public void setproCode(String proCode) {
		this.proCode = proCode;
	}
	

	public int getVisitTime() {
		return (int)((endTime.getTime() - startTime.getTime()) / (60 * 1000) % 60);
	}
}
