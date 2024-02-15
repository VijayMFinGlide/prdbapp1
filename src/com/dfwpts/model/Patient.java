package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.dfwpts.model.Insurance;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pid;
	private String mrNum;
	private String firstName;
	private String lastName;
	private int inactive;
	private Date inactiveDate;
	private String sex;
	private Date birthDate;
	private String address;
	private String altAddress;
	private String city;
	private String altCity;
	private String state;
	private String zip;
	private String altZip;
	private String parent;
	private String phone;
	private String altPhone;
	private String email;
	private int docId;
	private boolean evalAuth;
	private int visits;
	private int therapistId;
	private List<Insurance> insurance;
	private boolean admitted;
	private Date admissionDate;
	private Date onsetDate;
	private int assignedTid2;
	private int subId;		//To be removed later
	private String notes;
	private int frequency;
	private int onHold;
	private int onHoldReason;
	private Date onHoldDate;
	private int nonAdmitted;
	private int discharged;
	private int dischargeReason;
	private Date dischargeDate;
	private Date entryDate;
	private Date ninetyDayDate;
	private int freq2;
	private int tid1_1;
	private int freq1_1;
	private int tid1_2;
	private int freq1_2;
	private int tid1_3;
	private int freq1_3;
	private int tid1_4;
	private int freq1_4;
	private int tid2_1;
	private int freq2_1;
	private int tid2_2;
	private int freq2_2;
	private int tid2_3;
	private int freq2_3;
	private int monthlyFreq;
	private int prevFreq;
	private String blobKey;
	private int stid;
	private int ptid;
	private int otid;
	private int intakePacket;
	private int prevId;
	private int coversheet;
	private Date orders;
	private Date reevalOrders;
	private Date completeBy;
	private Date docAppt;
	private Date fdocAppt;

	
	public Date getOrders() {
		return orders;
	}
	
	public void setOrders(Date orders) {
		this.orders = orders;
	}
	
	public Date getReevalOrders() {
		return reevalOrders;
	}
	
     public void setReevalOrders(Date reevalOrders) {
		this.reevalOrders = reevalOrders;
	}
     
     public Date getCompleteBy() {
 		return completeBy;
 	}
 	
      public void setCompleteBy(Date completeBy) {
 		this.completeBy = completeBy;
 	}
		
	public int getPrevId() {
		return prevId;
	}

	public void setPrevId(int prevId) {
		this.prevId = prevId;
	}
	
	public int getCoverSheet() {
		return coversheet;
	}

	public void setCoverSheet(int coversheet) {
		this.coversheet = coversheet;
	}
	
	public int getIntakePacket() {
		return intakePacket;
	}

	public void setIntakePacket(int intakePacket) {
		this.intakePacket = intakePacket;
	}

	public int getPid() {
		return pid;
	}
	
	public int getStid() {
		return stid;
	}

	public int getPtid() {
		return ptid;
	}

	public int getOtid() {
		return otid;
	}

	public String getMRNum() {
		return mrNum;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public int getInactive() {
		return inactive;
	}
	
	public Date getInactiveDate() {
		return inactiveDate;
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
	
	public String getAltAddress() {
		return altAddress;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getAltCity() {
		return altCity;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}

	public String getAltZip() {
		return altZip;
	}

	public String getParent() {
		return parent;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getAltPhone() {
		return altPhone;
	}

	public String getEmail() {
		return email;
	}
	
	public int getDocId() {
		return docId;
	}
	
	public boolean getEvalAuth() {
		return evalAuth;
	}
	
	public int getVisits() {
		return visits;
	}
	
	public int getTherapistId() {
		return therapistId;
	}
	
	public List<Insurance> getInsurance() {
		return insurance;
	}
	
	public boolean getAdmitted() {
		return admitted;
	}
	
	public Date getAdmissionDate() {
		return admissionDate;
	}
	
	public Date getOnsetDate() {
		return onsetDate;
	}
	
	public int getAssignedTid2() {
		return assignedTid2;
	}
	
	public int getSubId() {
		return subId;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public int getOnHold() {
		return onHold;
	}
	
	public int getOnHoldReason() {
		return onHoldReason;
	}
	
	public Date getOnHoldDate() {
		return onHoldDate;
	}
	
	public int getNonAdmitted() {
		return nonAdmitted;
	}
	
	

	public int getDischarged() {
		return discharged;
	}
	
	public int getDischargeReason() {
		return dischargeReason;
	}
	
	public Date getDischargeDate() {
		return dischargeDate;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	
	public Date getNinetyDayDate() {
		return ninetyDayDate;
	}
	
	public int getFreq2() {
		return freq2;
	}
	
	public int getTid1_1() {
		return tid1_1;
	}

	public int getFreq1_1() {
		return freq1_1;
	}
	
	public int getTid1_2() {
		return tid1_2;
	}

	public int getFreq1_2() {
		return freq1_2;
	}
	
	public int getTid1_3() {
		return tid1_3;
	}

	public int getFreq1_3() {
		return freq1_3;
	}

	public int getTid1_4() {
		return tid1_4;
	}

	public int getFreq1_4() {
		return freq1_4;
	}
	
	public int getTid2_1() {
		return tid2_1;
	}

	public int getFreq2_1() {
		return freq2_1;
	}
	
	public int getTid2_2() {
		return tid2_2;
	}

	public int getFreq2_2() {
		return freq2_2;
	}

	public int getTid2_3() {
		return tid2_3;
	}

	public int getFreq2_3() {
		return freq2_3;
	}
	
	public int getMonthlyFreq() {
		return monthlyFreq;
	}
	
	public int getPrevFreq() {
		return prevFreq;
	}
	
	public String getBlobKey() {
		return blobKey;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public void setPtid(int ptid) {
		this.ptid = ptid;
	}
	
	public void setStid(int stid) {
		this.stid = stid;
	}

	public void setOtid(int otid) {
		this.otid = otid;
	}
	
	public void setInactive(int inactive) {
		this.inactive = inactive;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	
	public void setMRNum(String mrNum) {
		this.mrNum = mrNum;
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
	
	public void setAltAddress(String altAddress) {
		this.altAddress = altAddress;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setAltCity(String altCity) {
		this.altCity = altCity;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setAltZip(String altZip) {
		this.altZip = altZip;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setAltPhone(String aPhone) {
		this.altPhone = aPhone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDocId(int docId) {
		this.docId = docId;
	}
	
	public void setEvalAuth(boolean evalAuth) {
		this.evalAuth = evalAuth;
	}
	
	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public void setTherapistId(int therapistId) {
		this.therapistId = therapistId;
	}
	
	public void setInsurance(List<Insurance> insurance) {
		this.insurance = insurance;
	}
	
	public void setAdmitted(boolean admitted) {
		this.admitted = admitted;
	}
	
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	
	public void setOnsetDate(Date onsetDate) {
		this.onsetDate = onsetDate;
	}
	
	public void setAssignedTid2(int assignedTid2) {
		this.assignedTid2 = assignedTid2;
	}
	
	public void setSubId(int subId) {
		this.subId = subId;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public void setOnHold(int onHold) {
		if(onHold==0 || onHold==1) {
			this.onHold = onHold;
		}
	}
	
	public void setOnHoldReason(int onHoldReason) {
		this.onHoldReason = onHoldReason;
	}
	
	public void setOnHoldDate(Date onHoldDate) {
		this.onHoldDate = onHoldDate;
	}
	
	
	public void setNonAdmitted(int nonAdmitted) {
		this.nonAdmitted = nonAdmitted;
	}

	public void setDischarged(int discharged) {
		if(discharged==0 || discharged==1) {
			this.discharged = discharged;
		}
	}

	public void setDischargeReason(int dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public void setNinetyDayDate(Date ninetyDayDate) {
		this.ninetyDayDate = ninetyDayDate;
	}
	
	public void setFreq2(int freq2) {
		this.freq2 = freq2;
	}

	public void setTid1_1(int tid1_1) {
		this.tid1_1 = tid1_1;
	}

	public void setFreq1_1(int freq1_1) {
		this.freq1_1 = freq1_1;
	}

	public void setTid1_2(int tid1_2) {
		this.tid1_2 = tid1_2;
	}
	
	public void setFreq1_2(int freq1_2) {
		this.freq1_2 = freq1_2;
	}

	public void setTid1_3(int tid1_3) {
		this.tid1_3 = tid1_3;
	}
	
	public void setFreq1_3(int freq1_3) {
		this.freq1_3 = freq1_3;
	}

	public void setTid1_4(int tid1_4) {
		this.tid1_4 = tid1_4;
	}
	
	public void setFreq1_4(int freq1_4) {
		this.freq1_4 = freq1_4;
	}
	
	public void setTid2_1(int tid2_1) {
		this.tid2_1 = tid2_1;
	}

	public void setFreq2_1(int freq2_1) {
		this.freq2_1 = freq2_1;
	}

	public void setTid2_2(int tid2_2) {
		this.tid2_2 = tid2_2;
	}
	
	public void setFreq2_2(int freq2_2) {
		this.freq2_2 = freq2_2;
	}

	public void setTid2_3(int tid2_3) {
		this.tid2_3 = tid2_3;
	}

	public void setFreq2_3(int freq2_3) {
		this.freq2_3 = freq2_3;
	}
	
	public void setMonthlyFreq(int monthlyFreq) {
		this.monthlyFreq = monthlyFreq;
	}

	public void setPrevFreq(int prevFreq) {
		this.prevFreq = prevFreq;
	}
	
	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
	
	public Date getDocAppt() {
		return docAppt;
	}
	
     public void setDocAppt(Date docAppt) {
		this.docAppt = docAppt;
	}
     
     public Date getFdocAppt() {
 		return fdocAppt;
 	}
 	
      public void setFdocAppt(Date fdocAppt) {
 		this.fdocAppt = fdocAppt;
 	}
	
}