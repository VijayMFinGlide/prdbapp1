package com.dfwpts.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int tid;
	private Date startDate;
	private Date endDate;
	private BigDecimal perVisit;
	private BigDecimal eval;
	private BigDecimal reeval;
	private BigDecimal supervisory;
	private BigDecimal amerigroup;
	private BigDecimal amerigroupeval;
	private BigDecimal amerigroupreeval;
	private BigDecimal aetna;
	private BigDecimal aetnaeval;
	private BigDecimal aetnareeval;
	private BigDecimal sixtyDays;
	private BigDecimal hourly;
	private BigDecimal extra;

	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public BigDecimal getPerVisit() {
		return perVisit;
	}

	public void setPerVisit(BigDecimal perVisit) {
		this.perVisit = perVisit;
	}

	public BigDecimal getEval() {
		return eval;
	}

	public void setEval(BigDecimal eval) {
		this.eval = eval;
	}

	public BigDecimal getReeval() {
		return reeval;
	}

	public void setReeval(BigDecimal reeval) {
		this.reeval = reeval;
	}

	public BigDecimal getSupervisory() {
		return supervisory;
	}

	public void setSupervisory(BigDecimal supervisory) {
		this.supervisory = supervisory;
	}

	public BigDecimal getAmerigroup() {
		return amerigroup;
	}

	public void setAmerigroup(BigDecimal amerigroup) {
		this.amerigroup = amerigroup;
	}

	public BigDecimal getAmerigroupeval() {
		return amerigroupeval;
	}
	
	public void setAmerigroupeval(BigDecimal amerigroupeval) {
		this.amerigroupeval = amerigroupeval;
	}
	
	public BigDecimal getAmerigroupreeval() {
		return amerigroupreeval;
	}
	
	public void setAmerigroupreeval(BigDecimal amerigroupreeval) {
		this.amerigroupreeval = amerigroupreeval;
	}
	
	
	
	
	public BigDecimal getAetna() {
		return aetna;
	}

	public void setAetna(BigDecimal aetna) {
		this.aetna = aetna;
	}

	public BigDecimal getAetnaeval() {
		return aetnaeval;
	}
	
	public void setAetnaeval(BigDecimal aetnaeval) {
		this.aetnaeval = aetnaeval;
	}
	
	public BigDecimal getAetnareeval() {
		return aetnareeval;
	}
	
	public void setAetnareeval(BigDecimal aetnareeval) {
		this.aetnareeval = aetnareeval;
	}
	
	public BigDecimal getSixtyDays() {
		return sixtyDays;
	}

	public void setSixtyDays(BigDecimal sixtyDays) {
		this.sixtyDays = sixtyDays;
	}
	public BigDecimal gethourly() {
		return hourly;
	}

	public void sethourly(BigDecimal hourly) {
		this.hourly = hourly;
	}
	
	public BigDecimal getextra() {
		return extra;
	}

	public void setextra(BigDecimal extra) {
		this.extra = extra;
	}

}
