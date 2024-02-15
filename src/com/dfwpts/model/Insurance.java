package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.Comparator;
import java.io.Serializable;

@XmlRootElement
public class Insurance implements Serializable {
	private static final long serialVersionUID = 1L;

	private String[] companies = {"No Insurance", "Private Pay", "TMHP", "Parkland Community Health Plan", "Parkland CHIP", "Cigna", "BCBS TX", "Superior HealthPlan", "Molina Healthcare", "Amerigroup", "United Healthcare", "Aetna", "Cook Children's Health Plan", "Cook CHIP", "UMR", "Amerigroup CHIP", "TMHP SSI", "TRICARE", "Texas Children's Health Plan", "Amerigroup SSI", "Aetna Medicaid"};
	
	private int iid;
	private String company;
	private String insuredId;
	private String memberId;
	private int pid;
	private int visits;
	private int visit1;
	private int visit2;
	private int eval;
	private String evalAuth;
	private Date evalStartDate;
	private Date evalEndDate;
	private Date startDate;
	private Date endDate;
	private String authorization;
	private int type;
	private int frequency;
	private int monthly;
	private int everyOtherWeek;
	private Date everyOtherWeekStartDate;	
	private int iid1;
	private String insuredId1;
	private String memberId1;
	private String evalAuth1;
	private Date evalStartDate1;
	private Date evalEndDate1;
	private String prior1;
	private Date startDate1;
	private Date endDate1;
	private int iid2;
	private String insuredId2;
	private String memberId2;
	private String evalAuth2;
	private Date evalStartDate2;
	private Date evalEndDate2;
	private String prior2;
	private Date startDate2;
	private Date endDate2;
	private String codes;
	private String proccode;
	private String proccodes;

	public int getIid() {
		return iid;
	}
	
	public void setIid(int iid) {
		this.iid = iid;
		if(iid < companies.length) {
			this.company = companies[iid];
		}
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}
	
	public String getprocCode() {
		return proccode;
	}

	public void setprocCode(String proccode) {
		this.proccode = proccode;
	}
	
	
	public String getprocCodes() {
		return proccodes;
	}

	public void setprocCodes(String proccodes) {
		this.proccodes = proccodes;
	}
	
	public String getInsuredId() {
		return insuredId;
	}
	
	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}
	
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public int getVisit1() {
		return visit1;
	}

	public void setVisit1(int visit1) {
		this.visit1 = visit1;
	}
	
	public int getVisit2() {
		return visit2;
	}

	public void setVisit2(int visit2) {
		this.visit2 = visit2;
	}
	
	public int getEval() {
		return eval;
	}

	public void setEval(int eval) {
		this.eval = eval;
	}
	
	public String getEvalAuth() {
		return evalAuth;
	}
	
	public void setEvalAuth(String evalAuth) {
		this.evalAuth = evalAuth;
	}
	
	public Date getEvalStartDate() {
		return evalStartDate;
	}
	
	public void setEvalStartDate(Date evalStartDate) {
		this.evalStartDate = evalStartDate;
	}
	
	public Date getEvalEndDate() {
		return evalEndDate;
	}
	
	public void setEvalEndDate(Date evalEndDate) {
		this.evalEndDate = evalEndDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getAuthorization() {
		return authorization;
	}
	
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	


public int getFrequency() {
	return frequency;
}

public void setFrequency(int frequency) {
	this.frequency = frequency;
}
	
	public int getMonthly() {
		return monthly;
	}
	
	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}
	
	public int getEveryOtherWeek() {
		return everyOtherWeek;
	}
	
	public void setEveryOtherWeek(int everyOtherWeek) {
		this.everyOtherWeek = everyOtherWeek;
	}
	
	public Date getEveryOtherWeekStartDate() {
		return everyOtherWeekStartDate;
	}
	
	public void setEveryOtherWeekStartDate(Date everyOtherWeekStartDate) {
		this.everyOtherWeekStartDate = everyOtherWeekStartDate;
	}
	
	public String getInsuredId1() {
		return insuredId1;
	}
	
	public void setInsuredId1(String insuredId1) {
		this.insuredId1 = insuredId1;
	}
	
	public int getIid1() {
		return iid1;
	}
	
	public void setIid1(int iid1) {
		this.iid1 = iid1;
	}

	public String getMemberId1() {
		return memberId1;
	}
	
	public void setMemberId1(String memberId1) {
		this.memberId1 = memberId1;
	}
	
	public void setPrior1(String prior1) {
		this.prior1 = prior1;
	}
	
	public String getPrior1() {
		return prior1;
	}

	public String getEvalAuth1() {
		return evalAuth1;
	}
	
	public void setEvalAuth1(String evalAuth1) {
		this.evalAuth1 = evalAuth1;
	}
	
	public Date getEvalStartDate1() {
		return evalStartDate1;
	}
	
	public void setEvalStartDate1(Date evalStartDate1) {
		this.evalStartDate1 = evalStartDate1;
	}
	
	public Date getEvalEndDate1() {
		return evalEndDate1;
	}
	
	public void setEvalEndDate1(Date evalEndDate1) {
		this.evalEndDate1 = evalEndDate1;
	}

	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}
	
	public Date getStartDate1() {
		return startDate1;
	}
	
	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}
	
	public Date getEndDate1() {
		return endDate1;
	}
	
	public int getIid2() {
		return iid2;
	}

	public void setIid2(int iid2) {
		this.iid2 = iid2;
	}
	
	public String getInsuredId2() {
		return insuredId2;
	}
	
	public void setInsuredId2(String insuredId2) {
		this.insuredId2 = insuredId2;
	}
	
	public String getMemberId2() {
		return memberId2;
	}
	
	public void setMemberId2(String memberId2) {
		this.memberId2 = memberId2;
	}
	
	public void setPrior2(String prior2) {
		this.prior2 = prior2;
	}
	
	public String getPrior2() {
		return prior2;
	}
	
	public String getEvalAuth2() {
		return evalAuth2;
	}
	
	public void setEvalAuth2(String evalAuth2) {
		this.evalAuth2 = evalAuth2;
	}
	
	public Date getEvalStartDate2() {
		return evalStartDate2;
	}
	
	public void setEvalStartDate2(Date evalStartDate2) {
		this.evalStartDate2 = evalStartDate2;
	}
	
	public Date getEvalEndDate2() {
		return evalEndDate2;
	}
	
	public void setEvalEndDate2(Date evalEndDate2) {
		this.evalEndDate2 = evalEndDate2;
	}

	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}
	
	public Date getStartDate2() {
		return startDate2;
	}
	
	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}
	
	public Date getEndDate2() {
		return endDate2;
	}

	public static Comparator<Insurance> InsuranceEndDateComparator = new Comparator<Insurance>() {
		public int compare(Insurance ins1, Insurance ins2) {
			Date end1 = ins1.getEndDate();
			Date end2 = ins2.getEndDate();
			
			return end1.compareTo(end2);
		}
	};
}
