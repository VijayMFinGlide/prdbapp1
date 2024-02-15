package com.dfwpts.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class Therapist implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tid;
	private String username;
	private String passwd;
	private String firstName;
	private String lastName;
	private int type;
	private int active;
	private int past;
	private String sex;
	private Date birthDate;
	private String classification;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String emerContact;
	private String emerContactPhone;
	private String emerContact2;
	private String emerContactPhone2;
	private Date startDate;
	private Date endDate;
	private String email;
	private int superId;
	private String ssn;
	private String title;
	private String jobTitle;
	private String code;
	private Date codeExp;
	private Date stateLicense;
	private Date ashaLicense;
	private Date tbTest;
	private Date cpr;
	private Date driversLicense;
	private Date carInsurance;
	private Date liabilityInsurance;
	private String liabilityInsuranceCompany;
	private int adminFee;
	private int intern;
	private int cotaId;
	private int mentorId;
	private int training;
	private String npi;
	private String license;
	private boolean makeupAllowed;
	private Date makeupVisit;
	
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getMentorId() {
		return mentorId;
	}
	
	public void setMentorId(int mentorId) {
		this.mentorId = mentorId;
	}
	
	public int getTraining() {
		return training;
	}
	
	public void setTraining(int training) {
		this.training = training;
	}
	
	public int getIntern() {
		return intern;
	}
	
	public void setIntern(int intern) {
		this.intern = intern;
	}
	
	public int getCotaId() {
		return cotaId;
	}
	
	public void setCotaId(int cotaId) {
		this.cotaId = cotaId;
	}
	
	public int getAdminFee() {
		return adminFee;
	}
	
	public void setAdminFee(int adminFee) {
		this.adminFee = adminFee;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getActive() {
		return active;
	}
	
	public void setActive(int active) {
		this.active = active;
	}
	
	public int getPast() {
		return past;
	}
	
	public void setPast(int past) {
		this.past = past;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmerContact() {
		return emerContact;
	}
	
	public void setEmerContact(String emerContact) {
		this.emerContact = emerContact;
	}
	
	public String getEmerContactPhone() {
		return emerContactPhone;
	}
	
	public void setEmerContactPhone(String emerContactPhone) {
		this.emerContactPhone = emerContactPhone;
	}
	
	public String getEmerContact2() {
		return emerContact2;
	}
	
	public void setEmerContact2(String emerContact2) {
		this.emerContact2 = emerContact2;
	}
	
	public String getEmerContactPhone2() {
		return emerContactPhone2;
	}
	
	public void setEmerContactPhone2(String emerContactPhone2) {
		this.emerContactPhone2 = emerContactPhone2;
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

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getSuperId() {
		return superId;
	}
	
	public void setSuperId(int superId) {
		this.superId = superId;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getCodeExp() {
		return codeExp;
	}

	public void setCodeExp(Date codeExp) {
		this.codeExp = codeExp;
	}	

	public Date getStateLicense() {
		return stateLicense;
	}

	public void setStateLicense(Date stateLicense) {
		this.stateLicense = stateLicense;
	}	

	public Date getAshaLicense() {
		return ashaLicense;
	}

	public void setAshaLicense(Date ashaLicense) {
		this.ashaLicense = ashaLicense;
	}

	public Date getTbTest() {
		return tbTest;
	}

	public void setTbTest(Date tbTest) {
		this.tbTest = tbTest;
	}

	public Date getCpr() {
		return cpr;
	}

	public void setCpr(Date cpr) {
		this.cpr = cpr;
	}

	public Date getDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(Date driversLicense) {
		this.driversLicense = driversLicense;
	}

	public Date getCarInsurance() {
		return carInsurance;
	}

	public void setCarInsurance(Date carInsurance) {
		this.carInsurance = carInsurance;
	}

	public Date getLiabilityInsurance() {
		return liabilityInsurance;
	}

	public void setLiabilityInsurance(Date liabilityInsurance) {
		this.liabilityInsurance = liabilityInsurance;
	}
	
	public String getLiabilityInsuranceCompany() {
		return liabilityInsuranceCompany;
	}

	public void setLiabilityInsuranceCompany(String liabilityInsuranceCompany) {
		this.liabilityInsuranceCompany = liabilityInsuranceCompany;
	}
	
	public String getNpi() {
		return npi;
	}
	
	public void setNpi(String npi) {
		this.npi = npi;
	}
	
	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}

	public boolean isMakeupAllowed() {
		return makeupAllowed;
	}

	public void setMakeupAllowed(boolean makeupAllowed) {
		this.makeupAllowed = makeupAllowed;
	}

	public Date getMakeupVisit() {
		return makeupVisit;
	}

	public void setMakeupVisit(Date makeupVisit) {
		this.makeupVisit = makeupVisit;
	}

}
