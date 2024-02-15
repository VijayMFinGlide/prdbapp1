package com.dfwpts.resources;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Therapist;
import com.dfwpts.model.Patient;
import com.dfwpts.model.PreemptiveVisit;
import com.dfwpts.model.Mileage;
import com.dfwpts.model.Rate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

@Singleton
@Path("/therapists")
public class TherapistResource {
	String withRootUrl = "jdbc:google:mysql://preferred-database:ptsdb/ptsdb?user=root";
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Therapist> getTherapists(@QueryParam("apiKey") String key) {
		List<Therapist> therapistList = new ArrayList<Therapist>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
			PreparedStatement stmtEmail = null;
	    	ResultSet rs = null, rsEmail = null;

	    	String therapists = "select * from therapist";
	    	String therapistEmail = "select * from users where username=?";
	    	
			try {				
				conn = DBUtility.getConnectionPts();
				stmt = conn.createStatement();
				
				stmtEmail = conn.prepareStatement(therapistEmail);
				
				rs = stmt.executeQuery(therapists);
				
				while(rs.next()) {
					stmtEmail.setString(1, rs.getString("username"));

					Therapist t = new Therapist();
					t.setTid(rs.getInt("tid"));
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setUsername(rs.getString("username"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setType(rs.getInt("type"));
					t.setSex(rs.getString("sex"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					if(rs.getString("license_num") != null) {
						t.setLicense(rs.getString("license_num"));
					}
					
					rsEmail = stmtEmail.executeQuery();
					stmtEmail.setString(1, t.getUsername());
					
					if(rsEmail.next()) {
						t.setEmail(rsEmail.getString("email"));
					}

					therapistList.add(t);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rsEmail != null) {
					try { rsEmail.close(); } catch (Exception ignore) {}
				}
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmtEmail != null) {
					try { stmtEmail.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return therapistList;
	}
	
	@GET
	@Path("/supervisors")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Therapist> getSupervisors(@QueryParam("apiKey") String key) {
		List<Therapist> therapistList = new ArrayList<Therapist>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
	    	ResultSet rs = null;

	    	String therapists = "select * from therapist where tid=supervisor_id";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.createStatement();
							
				rs = stmt.executeQuery(therapists);
				
				while(rs.next()) {
					Therapist t = new Therapist();

					t.setTid(rs.getInt("tid"));
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setUsername(rs.getString("username"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setType(rs.getInt("type"));
					t.setSex(rs.getString("sex"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					if(rs.getString("license_num") != null) {
						t.setLicense(rs.getString("license_num"));
					}
					t.setIntern(rs.getInt("intern"));
					t.setCotaId(rs.getInt("cota_id"));
					
					therapistList.add(t);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/supervisors GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return therapistList;
	}

	
	@GET
	@Path("/{tid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Therapist getTherapistById(@QueryParam("apiKey") String key, @PathParam("tid") int id) {
		Therapist t = new Therapist();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, stmtEmail = null;
	    	ResultSet rs = null, rsEmail = null;

	    	String therapistById = "select * from therapist where tid=?";
	    	String therapistEmail = "select * from users where username=?";
	    	String code = "select * from codes where tid=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapistById);
				stmtEmail = conn.prepareStatement(therapistEmail);
				
				t.setTid(id);
				stmt.setInt(1, id);

				rs = stmt.executeQuery();
				
				if(rs.next()) {
					stmtEmail.setString(1, rs.getString("username"));
					
					t.setUsername(rs.getString("username"));
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setType(rs.getInt("type"));
					t.setSex(rs.getString("sex"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setAdminFee(rs.getInt("admin_fee"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					
					
					
					t.setIntern(rs.getInt("intern"));
					t.setCotaId(rs.getInt("cota_id"));
					if ( rs.getString("npi") != null) {						
						t.setNpi(rs.getString("npi"));
					} else {
						t.setNpi("");
					}
					
					if ( rs.getString("license_num") != null) {						
						t.setLicense(rs.getString("license_num"));
					} else {
						t.setLicense("");
					}
					
					t.setMakeupAllowed(rs.getBoolean("makeup_allowed"));
					if (rs.getDate("makeup_visit") != null) {
						t.setMakeupVisit(rs.getDate("makeup_visit"));
					}
				}
				
				rsEmail = stmtEmail.executeQuery();
				
				if(rsEmail.next()) {
					t.setEmail(rsEmail.getString("email"));
				}
				
				rs.close();
				stmt.close();
				stmt = conn.prepareStatement(code);
				stmt.setInt(1, id);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					t.setCode(rs.getString("code"));
					t.setCodeExp(rs.getDate("expiration"));
				}

	    	} catch(Exception e) {
	    		System.out.println("ERROR /therapists/"+id+" GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rsEmail != null) {
					try { rsEmail.close(); } catch (Exception ignore) {}
				}
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmtEmail != null) {
					try { stmtEmail.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return t;
	}
	
	@GET
	@Path("/{tid}/patients")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getNewPatients(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, iStmt = null;
	    	ResultSet rs = null, iRs = null;
	    	
	    	String query = "select * from patient where (assigned_tid=? or assigned_tid2=? or tid1_1=? or tid1_2=? or tid1_3=? or tid1_4=? or tid2_1=? or tid2_2=? or tid2_3=? or evaluator=?)";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();

				stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, tid);
				stmt.setInt(2, tid);
				stmt.setInt(3, tid);
				stmt.setInt(4, tid);
				stmt.setInt(5, tid);
				stmt.setInt(6, tid);
				stmt.setInt(7, tid);
				stmt.setInt(8, tid);
				stmt.setInt(9, tid);
				stmt.setInt(10, tid);

				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Patient p = new Patient();
					int pid = rs.getInt("pid");
					
					p.setPid(pid);
					p.setMRNum(rs.getString("mrnum"));
					p.setFirstName(rs.getString("fname"));
					p.setLastName(rs.getString("lname"));
					p.setInactive(rs.getInt("inactive"));
					if(rs.getDate("inactive_date")!=null) {
						p.setInactiveDate(rs.getDate("inactive_date"));
					}
					if(rs.getDate("orders_in")!=null) {
						p.setOrders(rs.getDate("orders_in"));
					}
					if(rs.getDate("reevalorder_in")!=null) {
						p.setReevalOrders(rs.getDate("reevalorder_in"));
					}
					if(rs.getDate("complete_by")!=null) {
						p.setCompleteBy(rs.getDate("complete_by"));
					}
					if(rs.getDate("doc_appointment")!=null) {
						p.setDocAppt(rs.getDate("doc_appointment"));
					}
					if(rs.getDate("f_doc_app")!=null) {
						p.setFdocAppt(rs.getDate("f_doc_app"));
					}
					p.setSex(rs.getString("sex"));
					p.setBirthDate(rs.getDate("birth"));
					p.setAddress(rs.getString("address_line1"));
					p.setCity(rs.getString("city"));
					p.setState(rs.getString("state"));
					p.setZip(rs.getString("zip"));
					p.setAltAddress(rs.getString("alt_address_line1"));
					p.setAltCity(rs.getString("alt_city"));
					p.setAltZip(rs.getString("alt_zip"));
					p.setParent(rs.getString("parent_name"));
					p.setPhone(rs.getString("phone"));
					p.setAltPhone(rs.getString("alt_phone"));
					p.setEmail(rs.getString("email"));
					p.setDocId(rs.getInt("docid"));
					if(rs.getInt("eval_auth")==1) {
						p.setEvalAuth(true);
					} else {
						p.setEvalAuth(false);
					}
					p.setVisits(rs.getInt("visits_auth"));
					if(rs.getInt("admitted")==1) {
						p.setAdmitted(true);
					} else {
						p.setAdmitted(false);
					}
					if(p.getAdmitted()) {
						p.setAdmissionDate(rs.getDate("admission_date"));
					}
					if(rs.getDate("onset_date")!=null) {
						p.setOnsetDate(rs.getDate("onset_date"));
					}
					if(rs.getDate("ninety_day_date")!=null) {
						p.setNinetyDayDate(rs.getDate("ninety_day_date"));						
					}
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setFreq2(rs.getInt("freq2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setFrequency(rs.getInt("frequency"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					p.setOnHoldDate(rs.getDate("onhold_date"));
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					p.setDischargeDate(rs.getDate("discharged_date"));
					p.setDischargeReason(rs.getInt("discharged_reason"));
					
					int evaluator = rs.getInt("evaluator");
					int efreq = rs.getInt("efreq");
					int tid1_1 = rs.getInt("tid1_1");
					int tid1_2 = rs.getInt("tid1_2");
					int tid1_3 = rs.getInt("tid1_3");
					int tid1_4 = rs.getInt("tid1_4");
					int tid2_1 = rs.getInt("tid2_1");
					int tid2_2 = rs.getInt("tid2_2");
					int tid2_3 = rs.getInt("tid2_3");
					
					p.setTid1_1(rs.getInt("tid1_1"));
					p.setTid1_2(rs.getInt("tid1_2"));
					p.setTid1_3(rs.getInt("tid1_3"));
					p.setTid1_4(rs.getInt("tid1_4"));
					p.setTid2_1(rs.getInt("tid2_1"));
					p.setTid2_2(rs.getInt("tid2_2"));
					p.setTid2_3(rs.getInt("tid2_3"));
					p.setFreq1_1(rs.getInt("freq1_1"));
					p.setFreq1_2(rs.getInt("freq1_2"));
					p.setFreq1_3(rs.getInt("freq1_3"));
					p.setFreq1_4(rs.getInt("freq1_4"));
					p.setFreq2_1(rs.getInt("freq2_1"));
					p.setFreq2_3(rs.getInt("freq2_2"));
					p.setFreq2_3(rs.getInt("freq2_3"));

					if(evaluator != 9) {
						if(tid1_1 == 9) {
							p.setTid1_1(evaluator);
							p.setFreq1_1(efreq);
						} else if(tid1_2 == 9) {
							p.setTid1_2(evaluator);
							p.setFreq1_2(efreq);
						} else if(tid1_3 == 9) {
							p.setTid1_3(evaluator);
							p.setFreq1_3(efreq);
						} else if(tid1_4 == 9) {
							p.setTid1_4(evaluator);
							p.setFreq1_4(efreq);
						} else if(tid2_1 == 9) {
							p.setTid2_1(evaluator);
							p.setFreq2_1(efreq);
						} else if(tid2_2 == 9) {
							p.setTid2_2(evaluator);
							p.setFreq2_2(efreq);
						} else if(tid2_3 == 9) {
							p.setTid2_3(evaluator);
							p.setFreq2_3(efreq);
						}	
					}
					
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));
					p.setIntakePacket(rs.getInt("intake_packet"));
					p.setPrevId(rs.getInt("prev_id"));
					
					String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";

					iStmt = conn.prepareStatement(insuranceByPid);
					
					iStmt.setInt(1, pid);
					iStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
					iStmt.setInt(3, 1);
					
					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setVisits(iRs.getInt("visits"));
						p.setMonthlyFreq(iRs.getInt("monthly"));
						if(iRs.getInt("eval") == 1) {
							p.setEvalAuth(true);
						} else {
							p.setEvalAuth(false);
						}
					}
					
					if(iRs != null) {
						try { iRs.close(); } catch (Exception ignore) {}
					}
					if(iStmt != null) {
						try { iStmt.close(); } catch (Exception ignore) {}
					}
					
					iStmt = conn.prepareStatement(insuranceByPid);
					
					iStmt.setInt(1, pid);
					iStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
					iStmt.setInt(3, 0);
					
					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setVisits(iRs.getInt("visits"));
						p.setMonthlyFreq(iRs.getInt("monthly"));
						if(iRs.getInt("eval") == 1) {
							p.setEvalAuth(true);
						} else {
							p.setEvalAuth(false);
						}
					}					

					if(iRs != null) {
						try { iRs.close(); } catch (Exception ignore) {}
					}
					if(iStmt != null) {
						try { iStmt.close(); } catch (Exception ignore) {}
					}
					
					String phone = "select * from phones where pid = ? and pr = ?";
					iStmt = conn.prepareStatement(phone);
					
					iStmt.setInt(1, pid);
					iStmt.setInt(2, 1);

					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setPhone(iRs.getString("phone"));
					} else {
						p.setPhone("");
					}
					
					patientList.add(p);
				}

	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/"+tid+"/patients GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}

		return patientList;
	}

	@GET
	@Path("/{tid}/rates")
	public List<Rate> getRates(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		
		List<Rate> rates = new ArrayList<Rate>();

		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String select = "select * from rates where tid = ? order by start";
			
	    	try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(select);

				stmt.setInt(1, tid);

				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Rate r = new Rate();
					
					Date start = rs.getDate("start");
					Date end = rs.getDate("end");
					BigDecimal perVisit = rs.getBigDecimal("per_visit");
					BigDecimal eval = rs.getBigDecimal("eval");
					BigDecimal reeval = rs.getBigDecimal("reeval");
					BigDecimal supervisory = rs.getBigDecimal("supervisory");
					BigDecimal amerigroup = rs.getBigDecimal("amerigroup");
					BigDecimal amerigroupeval = rs.getBigDecimal("amerigroupeval");
					BigDecimal amerigroupreeval = rs.getBigDecimal("amerigroupreeval");
					BigDecimal sixtyDays = rs.getBigDecimal("sixty_days");
					BigDecimal aetna = rs.getBigDecimal("aetna");
					BigDecimal aetnaeval = rs.getBigDecimal("aetnaeval");
					BigDecimal aetnareeval = rs.getBigDecimal("aetnareeval");
					BigDecimal hourly = rs.getBigDecimal("hourly");
					BigDecimal extra = rs.getBigDecimal("extra");

					r.setTid(tid);
					r.setStartDate(start);
					r.setEndDate(end);
					r.setPerVisit(perVisit);
					r.setEval(eval);
					r.setReeval(reeval);
					r.setSupervisory(supervisory);
					r.setAmerigroup(amerigroup);
					r.setAmerigroupeval(amerigroupeval);
					r.setAmerigroupreeval(amerigroupreeval);
					r.setAetna(aetna);
					r.setAetnaeval(aetnaeval);
					r.setAetnareeval(aetnareeval);
					r.setSixtyDays(sixtyDays);
					r.sethourly(hourly);
					r.setextra(extra);
					
					rates.add(r);
				}
				
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/"+tid+"/rates GET");
	    		System.out.println(e.getMessage());
	    		
			} finally {
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
	    	}
		}
		return rates;
	}

	
	@GET
	@Path("/{tid}/patients1")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getNewPatients1(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, iStmt = null;
	    	ResultSet rs = null, iRs = null;
	    	
	    	String query = "select * from patient where (assigned_tid=? or assigned_tid2=? or tid1_1=? or tid1_2=? or tid1_3=? or tid1_4=? or tid2_1=? or tid2_2=? or tid2_3=? or evaluator=?)";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();

				stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, tid);
				stmt.setInt(2, tid);
				stmt.setInt(3, tid);
				stmt.setInt(4, tid);
				stmt.setInt(5, tid);
				stmt.setInt(6, tid);
				stmt.setInt(7, tid);
				stmt.setInt(8, tid);
				stmt.setInt(9, tid);
				stmt.setInt(10, tid);

				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Patient p = new Patient();
					int pid = rs.getInt("pid");
					
					p.setPid(pid);
					p.setMRNum(rs.getString("mrnum"));
					p.setFirstName(rs.getString("fname"));
					p.setLastName(rs.getString("lname"));
					p.setInactive(rs.getInt("inactive"));
					if(rs.getDate("inactive_date")!=null) {
						p.setInactiveDate(rs.getDate("inactive_date"));
					}
					if(rs.getDate("orders_in")!=null) {
						p.setOrders(rs.getDate("orders_in"));
					}
					if(rs.getDate("reevalorder_in")!=null) {
						p.setReevalOrders(rs.getDate("reevalorder_in"));
					}
					if(rs.getDate("complete_by")!=null) {
						p.setCompleteBy(rs.getDate("complete_by"));
					}
					if(rs.getDate("complete_by")!=null) {
						p.setCompleteBy(rs.getDate("complete_by"));
					}
					if(rs.getDate("doc_appointment")!=null) {
						p.setDocAppt(rs.getDate("doc_appointment"));
					}
					if(rs.getDate("f_doc_app")!=null) {
						p.setFdocAppt(rs.getDate("f_doc_app"));
					}
					p.setSex(rs.getString("sex"));
					p.setBirthDate(rs.getDate("birth"));
					p.setAddress(rs.getString("address_line1"));
					p.setCity(rs.getString("city"));
					p.setState(rs.getString("state"));
					p.setZip(rs.getString("zip"));
					p.setAltAddress(rs.getString("alt_address_line1"));
					p.setAltCity(rs.getString("alt_city"));
					p.setAltZip(rs.getString("alt_zip"));
					p.setParent(rs.getString("parent_name"));
					p.setPhone(rs.getString("phone"));
					p.setAltPhone(rs.getString("alt_phone"));
					p.setEmail(rs.getString("email"));
					p.setDocId(rs.getInt("docid"));
					if(rs.getInt("eval_auth")==1) {
						p.setEvalAuth(true);
					} else {
						p.setEvalAuth(false);
					}
					p.setVisits(rs.getInt("visits_auth"));
					if(rs.getInt("admitted")==1) {
						p.setAdmitted(true);
					} else {
						p.setAdmitted(false);
					}
					if(p.getAdmitted()) {
						p.setAdmissionDate(rs.getDate("admission_date"));
					}
					if(rs.getDate("onset_date")!=null) {
						p.setOnsetDate(rs.getDate("onset_date"));
					}
					if(rs.getDate("ninety_day_date")!=null) {
						p.setNinetyDayDate(rs.getDate("ninety_day_date"));						
					}
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setFreq2(rs.getInt("freq2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setFrequency(rs.getInt("frequency"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					p.setOnHoldDate(rs.getDate("onhold_date"));
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					p.setDischargeDate(rs.getDate("discharged_date"));
					p.setDischargeReason(rs.getInt("discharged_reason"));
					
					int evaluator = rs.getInt("evaluator");
					int efreq = rs.getInt("efreq");
					int tid1_1 = rs.getInt("tid1_1");
					int tid1_2 = rs.getInt("tid1_2");
					int tid1_3 = rs.getInt("tid1_3");
					int tid1_4 = rs.getInt("tid1_4");
					int tid2_1 = rs.getInt("tid2_1");
					int tid2_2 = rs.getInt("tid2_2");
					int tid2_3 = rs.getInt("tid2_3");
					
					if(evaluator != 9) {
						if(tid1_1 == 9) {
							p.setTid1_1(evaluator);
							p.setFreq1_1(efreq);
							p.setTid1_2(rs.getInt("tid1_2"));
							p.setTid1_3(rs.getInt("tid1_3"));
							p.setTid1_4(rs.getInt("tid1_4"));
							p.setTid2_1(rs.getInt("tid2_1"));
							p.setTid2_2(rs.getInt("tid2_2"));
							p.setTid2_3(rs.getInt("tid2_3"));
							p.setFreq1_2(rs.getInt("freq1_2"));
							p.setFreq1_3(rs.getInt("freq1_3"));
							p.setFreq1_4(rs.getInt("freq1_4"));
							p.setFreq2_1(rs.getInt("freq2_1"));
							p.setFreq2_3(rs.getInt("freq2_2"));
							p.setFreq2_3(rs.getInt("freq2_3"));
						} else if(tid1_2 == 9) {
							p.setTid1_2(evaluator);
							p.setFreq1_2(efreq);
							p.setTid1_1(rs.getInt("tid1_1"));
							p.setTid1_3(rs.getInt("tid1_3"));
							p.setTid1_4(rs.getInt("tid1_4"));
							p.setTid2_1(rs.getInt("tid2_1"));
							p.setTid2_2(rs.getInt("tid2_2"));
							p.setTid2_3(rs.getInt("tid2_3"));
							p.setFreq1_1(rs.getInt("freq1_1"));
							p.setFreq1_3(rs.getInt("freq1_3"));
							p.setFreq1_4(rs.getInt("freq1_4"));
							p.setFreq2_1(rs.getInt("freq2_1"));
							p.setFreq2_3(rs.getInt("freq2_2"));
							p.setFreq2_3(rs.getInt("freq2_3"));
						} else if(tid1_3 == 9) {
							p.setTid1_3(evaluator);
							p.setFreq1_3(efreq);
							p.setTid1_1(rs.getInt("tid1_1"));
							p.setTid1_2(rs.getInt("tid1_2"));
							p.setTid1_4(rs.getInt("tid1_4"));
							p.setTid2_1(rs.getInt("tid2_1"));
							p.setTid2_2(rs.getInt("tid2_2"));
							p.setTid2_3(rs.getInt("tid2_3"));
							p.setFreq1_1(rs.getInt("freq1_1"));
							p.setFreq1_2(rs.getInt("freq1_2"));
							p.setFreq1_4(rs.getInt("freq1_4"));
							p.setFreq2_1(rs.getInt("freq2_1"));
							p.setFreq2_3(rs.getInt("freq2_2"));
							p.setFreq2_3(rs.getInt("freq2_3"));
						} else if(tid1_4 == 9) {
							p.setTid1_4(evaluator);
							p.setFreq1_4(efreq);
							p.setTid1_1(rs.getInt("tid1_1"));
							p.setTid1_2(rs.getInt("tid1_2"));
							p.setTid1_3(rs.getInt("tid1_3"));
							p.setTid2_1(rs.getInt("tid2_1"));
							p.setTid2_2(rs.getInt("tid2_2"));
							p.setTid2_3(rs.getInt("tid2_3"));
							p.setFreq1_1(rs.getInt("freq1_1"));
							p.setFreq1_2(rs.getInt("freq1_2"));
							p.setFreq1_3(rs.getInt("freq1_3"));
							p.setFreq2_1(rs.getInt("freq2_1"));
							p.setFreq2_3(rs.getInt("freq2_2"));
							p.setFreq2_3(rs.getInt("freq2_3"));
						} else if(tid2_1 == 9) {
							p.setTid2_1(evaluator);
							p.setFreq2_1(efreq);
							p.setTid1_1(rs.getInt("tid1_1"));
							p.setTid1_2(rs.getInt("tid1_2"));
							p.setTid1_3(rs.getInt("tid1_3"));
							p.setTid1_4(rs.getInt("tid1_4"));
							p.setTid2_2(rs.getInt("tid2_2"));
							p.setTid2_3(rs.getInt("tid2_3"));
							p.setFreq1_1(rs.getInt("freq1_1"));
							p.setFreq1_2(rs.getInt("freq1_2"));
							p.setFreq1_3(rs.getInt("freq1_3"));
							p.setFreq1_4(rs.getInt("freq1_4"));
							p.setFreq2_3(rs.getInt("freq2_2"));
							p.setFreq2_3(rs.getInt("freq2_3"));
						} else if(tid2_2 == 9) {
							p.setTid2_2(evaluator);
							p.setFreq2_2(efreq);
							p.setTid1_1(rs.getInt("tid1_1"));
							p.setTid1_2(rs.getInt("tid1_2"));
							p.setTid1_3(rs.getInt("tid1_3"));
							p.setTid1_4(rs.getInt("tid1_4"));
							p.setTid2_1(rs.getInt("tid2_1"));
							p.setTid2_3(rs.getInt("tid2_3"));
							p.setFreq1_1(rs.getInt("freq1_1"));
							p.setFreq1_2(rs.getInt("freq1_2"));
							p.setFreq1_3(rs.getInt("freq1_3"));
							p.setFreq1_4(rs.getInt("freq1_4"));
							p.setFreq2_1(rs.getInt("freq2_1"));
							p.setFreq2_3(rs.getInt("freq2_3"));

						} else if(tid2_3 == 9) {
							p.setTid2_3(evaluator);
							p.setFreq2_3(efreq);
							p.setTid1_1(rs.getInt("tid1_1"));
							p.setTid1_2(rs.getInt("tid1_2"));
							p.setTid1_3(rs.getInt("tid1_3"));
							p.setTid1_4(rs.getInt("tid1_4"));
							p.setTid2_1(rs.getInt("tid2_1"));
							p.setTid2_2(rs.getInt("tid2_2"));
							p.setFreq1_1(rs.getInt("freq1_1"));
							p.setFreq1_2(rs.getInt("freq1_2"));
							p.setFreq1_3(rs.getInt("freq1_3"));
							p.setFreq1_4(rs.getInt("freq1_4"));
							p.setFreq2_1(rs.getInt("freq2_1"));
							p.setFreq2_3(rs.getInt("freq2_2"));
						}
						
					} else {
						p.setTid1_1(rs.getInt("tid1_1"));
						p.setTid1_2(rs.getInt("tid1_2"));
						p.setTid1_3(rs.getInt("tid1_3"));
						p.setTid1_4(rs.getInt("tid1_4"));
						p.setTid2_1(rs.getInt("tid2_1"));
						p.setTid2_2(rs.getInt("tid2_2"));
						p.setTid2_3(rs.getInt("tid2_3"));
						p.setFreq1_1(rs.getInt("freq1_1"));
						p.setFreq1_2(rs.getInt("freq1_2"));
						p.setFreq1_3(rs.getInt("freq1_3"));
						p.setFreq1_4(rs.getInt("freq1_4"));
						p.setFreq2_1(rs.getInt("freq2_1"));
						p.setFreq2_3(rs.getInt("freq2_2"));
						p.setFreq2_3(rs.getInt("freq2_3"));
					}
					
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));
					p.setIntakePacket(rs.getInt("intake_packet"));
					p.setPrevId(rs.getInt("prev_id"));
					p.setCoverSheet(rs.getInt("coversheet"));
					
					String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";

					iStmt = conn.prepareStatement(insuranceByPid);
					
					iStmt.setInt(1, pid);
					iStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
					iStmt.setInt(3, 1);
					
					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setVisits(iRs.getInt("visits"));
						p.setMonthlyFreq(iRs.getInt("monthly"));
						if(iRs.getInt("eval") == 1) {
							p.setEvalAuth(true);
						} else {
							p.setEvalAuth(false);
						}
					}
					
					if(iRs != null) {
						try { iRs.close(); } catch (Exception ignore) {}
					}
					if(iStmt != null) {
						try { iStmt.close(); } catch (Exception ignore) {}
					}
					
					iStmt = conn.prepareStatement(insuranceByPid);
					
					iStmt.setInt(1, pid);
					iStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
					iStmt.setInt(3, 0);
					
					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setVisits(iRs.getInt("visits"));
						p.setMonthlyFreq(iRs.getInt("monthly"));
						if(iRs.getInt("eval") == 1) {
							p.setEvalAuth(true);
						} else {
							p.setEvalAuth(false);
						}
					}					

					if(iRs != null) {
						try { iRs.close(); } catch (Exception ignore) {}
					}
					if(iStmt != null) {
						try { iStmt.close(); } catch (Exception ignore) {}
					}
					
					String phone = "select * from phones where pid = ? and pr = ?";
					iStmt = conn.prepareStatement(phone);
					
					iStmt.setInt(1, pid);
					iStmt.setInt(2, 1);

					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setPhone(iRs.getString("phone"));
					} else {
						p.setPhone("");
					}
					
					patientList.add(p);
				}

	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/"+tid+"/patients GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
	    	
	    	Patient m = new Patient();

	    	m.setFirstName("_Meeting");
	    	m.setLastName("Visit");
	    	m.setAddress("2350 Airport Fwy");
	    	m.setCity("Bedford");
	    	m.setState("TX");
	    	m.setZip("76022");
			m.setVisits(0);
			m.setMonthlyFreq(0);
			m.setEvalAuth(true);
			m.setPid(tid*-1);
			m.setStid(tid*-1);
			m.setPtid(0);
			m.setOtid(0);
			m.setParent("");
			m.setSex("M");
			m.setPhone("8175080030");
			m.setMRNum("MV0000");
			
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			try {
				java.util.Date dob = df.parse("09/25/1988");
				m.setBirthDate(dob);
			} catch (ParseException e) {
			}

	    	patientList.add(m);
		}

		return patientList;
	}

	@GET
	@Path("/{tid}/patients/old")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getPatients(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, iStmt = null;
	    	ResultSet rs = null, iRs = null;

	    	String[] patientsById = {"select * from patient where assigned_tid=?",
	    			"select * from patient where assigned_tid2=?",
	    			"select * from patient where tid1_1=?",
	    			"select * from patient where tid1_2=?",
	    			"select * from patient where tid1_3=?",
	    			"select * from patient where tid1_4=?",
	    			"select * from patient where tid2_1=?",
	    			"select * from patient where tid2_2=?",
	    			"select * from patient where tid2_3=?"};
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				for(int i=0; i<patientsById.length; i++) {
					stmt = conn.prepareStatement(patientsById[i]);
					
					stmt.setInt(1, tid);
					rs = stmt.executeQuery();
					
					while(rs.next()) {
						Patient p = new Patient();
						
						p.setPid(rs.getInt("pid"));
						p.setMRNum(rs.getString("mrnum"));
						p.setFirstName(rs.getString("fname"));
						p.setLastName(rs.getString("lname"));
						p.setInactive(rs.getInt("inactive"));
						if(rs.getDate("inactive_date")!=null) {
							p.setInactiveDate(rs.getDate("inactive_date"));
						}
						p.setSex(rs.getString("sex"));
						p.setBirthDate(rs.getDate("birth"));
						p.setAddress(rs.getString("address_line1"));
						p.setCity(rs.getString("city"));
						p.setState(rs.getString("state"));
						p.setZip(rs.getString("zip"));
						p.setAltAddress(rs.getString("alt_address_line1"));
						p.setAltCity(rs.getString("alt_city"));
						p.setAltZip(rs.getString("alt_zip"));
						p.setParent(rs.getString("parent_name"));
						p.setPhone(rs.getString("phone"));
						p.setAltPhone(rs.getString("alt_phone"));
						p.setEmail(rs.getString("email"));
						p.setDocId(rs.getInt("docid"));
						if(rs.getInt("eval_auth")==1) {
							p.setEvalAuth(true);
						} else {
							p.setEvalAuth(false);
						}
						p.setVisits(rs.getInt("visits_auth"));
						if(rs.getInt("admitted")==1) {
							p.setAdmitted(true);
						} else {
							p.setAdmitted(false);
						}
						if(p.getAdmitted()) {
							p.setAdmissionDate(rs.getDate("admission_date"));
						}
						if(rs.getDate("onset_date")!=null) {
							p.setOnsetDate(rs.getDate("onset_date"));
						}
						if(rs.getDate("ninety_day_date")!=null) {
							p.setNinetyDayDate(rs.getDate("ninety_day_date"));						
						}
						p.setTherapistId(rs.getInt("assigned_tid"));
						p.setAssignedTid2(rs.getInt("assigned_tid2"));
						p.setFreq2(rs.getInt("freq2"));
						p.setSubId(rs.getInt("sub_tid"));
						p.setFrequency(rs.getInt("frequency"));
						p.setOnHold(rs.getInt("onhold"));
						p.setOnHoldReason(rs.getInt("onhold_reason"));
						p.setOnHoldDate(rs.getDate("onhold_date"));
						p.setNonAdmitted(rs.getInt("non_admitted"));
						p.setDischarged(rs.getInt("discharged"));
						p.setDischargeDate(rs.getDate("discharged_date"));
						p.setDischargeReason(rs.getInt("discharged_reason"));
						p.setTid1_1(rs.getInt("tid1_1"));
						p.setTid1_2(rs.getInt("tid1_2"));
						p.setTid1_3(rs.getInt("tid1_3"));
						p.setTid1_4(rs.getInt("tid1_4"));
						p.setTid2_1(rs.getInt("tid2_1"));
						p.setTid2_2(rs.getInt("tid2_2"));
						p.setTid2_3(rs.getInt("tid2_3"));
						p.setFreq1_1(rs.getInt("freq1_1"));
						p.setFreq1_2(rs.getInt("freq1_2"));
						p.setFreq1_3(rs.getInt("freq1_3"));
						p.setFreq1_4(rs.getInt("freq1_4"));
						p.setFreq2_1(rs.getInt("freq2_1"));
						p.setFreq2_3(rs.getInt("freq2_2"));
						p.setFreq2_3(rs.getInt("freq2_3"));
						if(rs.getDate("entry_date")!=null) {
							p.setEntryDate(rs.getDate("entry_date"));
						}
						p.setMonthlyFreq(rs.getInt("monthly_freq"));
						p.setPrevFreq(rs.getInt("prevFreq"));
						p.setStid(rs.getInt("stid"));
						p.setPtid(rs.getInt("ptid"));
						p.setOtid(rs.getInt("otid"));
						p.setIntakePacket(rs.getInt("intake_packet"));
						p.setPrevId(rs.getInt("prev_id"));
						
						String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";

						iStmt = conn.prepareStatement(insuranceByPid);
						
						iStmt.setInt(1, rs.getInt("pid"));
						iStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						iStmt.setInt(3, 1);
						
						iRs = iStmt.executeQuery();
						
						if(iRs.next()) {
							p.setVisits(iRs.getInt("visits"));
							p.setMonthlyFreq(iRs.getInt("monthly"));
							if(iRs.getInt("eval") == 1) {
								p.setEvalAuth(true);
							} else {
								p.setEvalAuth(false);
							}
						}
						
						if(iRs != null) {
							try { iRs.close(); } catch (Exception ignore) {}
						}
						if(iStmt != null) {
							try { iStmt.close(); } catch (Exception ignore) {}
						}
						
						iStmt = conn.prepareStatement(insuranceByPid);
						
						iStmt.setInt(1, rs.getInt("pid"));
						iStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						iStmt.setInt(3, 0);
						
						iRs = iStmt.executeQuery();
						
						if(iRs.next()) {
							p.setVisits(iRs.getInt("visits"));
							p.setMonthlyFreq(iRs.getInt("monthly"));
							if(iRs.getInt("eval") == 1) {
								p.setEvalAuth(true);
							} else {
								p.setEvalAuth(false);
							}
						}					

						
						patientList.add(p);
					}
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/"+tid+"/patients GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}

		return patientList;
	}

	@GET
	@Path("/username/{username}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Therapist getTherapistByUsername(@QueryParam("apiKey") String key, @PathParam("username") String username) {
		Therapist t = new Therapist();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, stmtEmail = null;
	    	ResultSet rs = null, rsEmail = null;

	    	String therapistByUsername = "select * from therapist where username=?";
	    	String therapistEmail = "select * from users where username=?";
	    	String code = "select * from codes where tid=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapistByUsername);
				stmtEmail = conn.prepareStatement(therapistEmail);
				int id = 0;
				
				t.setUsername(username);
				stmt.setString(1, username);
				stmtEmail.setString(1, username);
				
				rs = stmt.executeQuery();
				rsEmail = stmtEmail.executeQuery();
				
				if(rs.next()) {
					t.setTid(rs.getInt("tid"));
					id = rs.getInt("tid");
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setSex(rs.getString("sex"));
					t.setType(rs.getInt("type"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					t.setClassification(rs.getString("classification"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					
					t.setIntern(rs.getInt("intern"));
					t.setCotaId(rs.getInt("cota_id"));
				}
				
				if(rsEmail.next()) {
					t.setEmail(rsEmail.getString("email"));
					t.setPasswd(rsEmail.getString("password"));
				}
				
				rs.close();
				stmt.close();
				stmt = conn.prepareStatement(code);
				stmt.setInt(1, id);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					t.setCode(rs.getString("code"));
					t.setCodeExp(rs.getDate("expiration"));
				}

	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/username/"+username+" GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rsEmail != null) {
					try { rsEmail.close(); } catch (Exception ignore) {}
				}
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmtEmail != null) {
					try { stmtEmail.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return t;
	}
	
	@GET
	@Path("/username/{username}/password/{password}")
	public String putPassword(@QueryParam("apiKey") String key, @PathParam("username") String username, @PathParam("password") String password) {
		String returns = "";
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			String update = "update users set password = ? where username = ?";
			
	    	try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(update);

				stmt.setString(1, password);
				stmt.setString(2, username);

				stmt.executeUpdate();
				
				returns = "success";
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/username/"+username+"/password PUT");
	    		System.out.println(e.getMessage());
	    		returns = "fail";
			} finally {
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
	    	}
		}
		return returns;
	}

	@GET
	@Path("/username/{username}/json")
	@Produces({MediaType.APPLICATION_JSON})
	public String getTherapistByUsernameJSON(@QueryParam("apiKey") String key, @PathParam("username") String username) {
		JSONObject th = new JSONObject();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, stmtEmail = null;
	    	ResultSet rs = null, rsEmail = null;

	    	String therapistByUsername = "select * from therapist where username=?";
	    	String therapistEmail = "select * from users where username=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(therapistByUsername);

				stmt.setString(1, username);

				rs = stmt.executeQuery();
				
				if(rs.next()) {
					th.put("username", username);
					th.put("tid", rs.getInt("tid"));
				}
				
				stmtEmail = conn.prepareStatement(therapistEmail);

				stmtEmail.setString(1, username);
				
				rsEmail = stmtEmail.executeQuery();

				if(rsEmail.next()) {
					th.put("password", rsEmail.getString("password"));
				}
				
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/username/"+username+"/json GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rsEmail != null) {
					try { rsEmail.close(); } catch (Exception ignore) {}
				}
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmtEmail != null) {
					try { stmtEmail.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return th.toString();
	}

	@GET
	@Path("/name/{fname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Therapist> getTherapistByName(@QueryParam("apiKey") String key, @PathParam("fname") String fname) {
		List<Therapist> list = new ArrayList<Therapist>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String therapistByUsername = "select * from therapist where fname=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapistByUsername);
				
				stmt.setString(1, fname);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Therapist t = new Therapist();
					
					t.setUsername(rs.getString("username"));
					t.setTid(rs.getInt("tid"));
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setSex(rs.getString("sex"));
					t.setType(rs.getInt("type"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					
					t.setIntern(rs.getInt("intern"));
					t.setCotaId(rs.getInt("cota_id"));

					list.add(t);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/name/"+fname+" GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return list;
	}

	@GET
	@Path("/{tid}/assistants")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Therapist> getAssistants(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Therapist> therapistList = new ArrayList<Therapist>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String therapists = "select * from therapist where supervisor_id=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapists);
				
				stmt.setInt(1, tid);
							
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Therapist t = new Therapist();

					t.setTid(rs.getInt("tid"));
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setUsername(rs.getString("username"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setType(rs.getInt("type"));
					t.setSex(rs.getString("sex"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					
					t.setIntern(rs.getInt("intern"));
					t.setCotaId(rs.getInt("cota_id"));

					therapistList.add(t);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/supervisors GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return therapistList;
	}

	@GET
	@Path("/{tid}/assistantsinterns")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Therapist> getAssistantsInterns(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Therapist> therapistList = new ArrayList<Therapist>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String therapists = "select * from therapist where cota_id=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapists);
				
				stmt.setInt(1, tid);
							
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Therapist t = new Therapist();

					t.setTid(rs.getInt("tid"));
					t.setMentorId(rs.getInt("mentor_id"));
					t.setTraining(rs.getInt("training"));
					t.setUsername(rs.getString("username"));
					t.setFirstName(rs.getString("fname"));
					t.setLastName(rs.getString("lname"));
					t.setType(rs.getInt("type"));
					t.setSex(rs.getString("sex"));
					t.setBirthDate(rs.getDate("birth"));
					t.setAddress(rs.getString("address_line1"));
					t.setCity(rs.getString("city"));
					t.setState(rs.getString("state"));
					t.setZip(rs.getString("zip"));
					t.setPhone(rs.getString("phone"));
					t.setSuperId(rs.getInt("supervisor_id"));
					t.setSsn(rs.getString("ssn"));
					t.setTitle(rs.getString("title"));
					t.setJobTitle(rs.getString("job_title"));
					t.setActive(rs.getInt("active"));
					t.setPast(rs.getInt("past"));
					t.setEmerContact(rs.getString("emergency_contact"));
					t.setEmerContactPhone(rs.getString("emergency_phone"));
					t.setStartDate(rs.getDate("startdate"));
					if(rs.getDate("enddate") != null) {
						t.setEndDate(rs.getDate("enddate"));
					}
					if(rs.getString("emergency_contact_2") != null) {
						t.setEmerContact2(rs.getString("emergency_contact_2"));
					}
					if(rs.getString("emergency_phone_2") != null) {
						t.setEmerContactPhone2(rs.getString("emergency_phone_2"));
					}
					if(rs.getDate("state_license") != null) {
						t.setStateLicense(rs.getDate("state_license"));
					}
					if(rs.getDate("asha_license") != null) {
						t.setAshaLicense(rs.getDate("asha_license"));
					}
					if(rs.getDate("tb_test") != null) {
						t.setTbTest(rs.getDate("tb_test"));
					}
					if(rs.getDate("cpr") != null) {
						t.setCpr(rs.getDate("cpr"));
					}
					if(rs.getDate("drivers_license") != null) {
						t.setDriversLicense(rs.getDate("drivers_license"));
					}
					if(rs.getDate("car_insurance") != null) {
						t.setCarInsurance(rs.getDate("car_insurance"));
					}
					if(rs.getDate("liability_insurance") != null) {
						t.setLiabilityInsurance(rs.getDate("liability_insurance"));
					}
					if(rs.getString("liability_insurance_company") != null) {
						t.setLiabilityInsuranceCompany(rs.getString("liability_insurance_company"));
					}
					
					t.setIntern(rs.getInt("intern"));
					t.setCotaId(rs.getInt("cota_id"));

					therapistList.add(t);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/supervisors GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return therapistList;
	}

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addTherapist(Therapist t) {
		Connection conn = null;
		PreparedStatement stmt = null, userStmt = null;
    	ResultSet rs = null;
    	
    	String insertTherapist = "insert into therapist (username, fname, lname, type, sex, birth, address_line1, city, state, zip, phone, supervisor_id, ssn, title, job_title) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	String inTherapist = "insert into users (username, password, role, email) values (?,?,?,?)";
    	
    	try {
			conn = DBUtility.getConnectionPts();
			stmt = conn.prepareStatement(insertTherapist);
			userStmt = conn.prepareStatement(inTherapist);
			
			userStmt.setString(1, t.getUsername());
			userStmt.setString(2, t.getPasswd());
			userStmt.setString(3, "T");
			userStmt.setString(4, t.getEmail());
			
			userStmt.executeUpdate();

			stmt.setString(1, t.getUsername());
			stmt.setString(2, t.getFirstName());
			stmt.setString(3, t.getLastName());
			stmt.setInt(4, t.getType());
			stmt.setString(4, t.getSex());
			stmt.setDate(5, new Date(t.getBirthDate().getTime()));
			stmt.setString(6, t.getAddress());
			stmt.setString(7, t.getCity());
			stmt.setString(8, t.getState());
			stmt.setString(9, t.getZip());
			stmt.setString(10, t.getPhone());
			stmt.setInt(11, t.getSuperId());
			stmt.setString(12, t.getSsn());
			stmt.setString(13, t.getTitle());
			stmt.setString(14, t.getJobTitle());
			
			stmt.executeUpdate();
			
    	} catch(Exception e) {
			System.out.println("ERROR /therapists POST");
    		System.out.println(e.getMessage());
		} finally {
			if(rs != null) {
				try { rs.close(); } catch (Exception ignore) {}
			}
			if(stmt != null) {
				try { stmt.close(); } catch (Exception ignore) {}
			}
			if(conn != null) {
				try { conn.close(); } catch (Exception ignore) {}
			}
		}
	}
	
	@PUT
	@Path("/{tid}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updateTherapist(@PathParam("tid") int tid, Therapist t) {
		if(t.getTid() == tid) {
			Connection conn = null;
			PreparedStatement stmt = null, emailStmt = null;
	    	ResultSet rs = null;
	    	
	    	String updateTherapist = "UPDATE therapist SET address_line1=?, city=?, zip=?, phone=? WHERE tid=?";
	    	String updateEmail = "UPDATE users SET email=? where username=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(updateTherapist);
				
				stmt.setString(1, t.getAddress());
				stmt.setString(2, t.getCity());
				stmt.setString(3, t.getZip());
				stmt.setString(4, t.getPhone());
				stmt.setInt(5, tid);

				stmt.executeUpdate();
				
				emailStmt = conn.prepareStatement(updateEmail);
				
				emailStmt.setString(1, t.getEmail());
				emailStmt.setString(2, t.getUsername());
				
				emailStmt.executeUpdate();
				
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists PUT");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		
	}
	@GET
	@Path("/{tid}/preemptivevisit")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<PreemptiveVisit> getPreemptiveVisit(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<PreemptiveVisit> visits = new ArrayList<PreemptiveVisit>();
		
	
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String therapists = "select * from preemptive_visit where tid=? order by start_date desc";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapists);
				
				stmt.setInt(1, tid);
							
				rs = stmt.executeQuery();
				
				
				while(rs.next()) {
					PreemptiveVisit  visit = new PreemptiveVisit();
					visit.setTid(tid);
					visit.setStartDate(rs.getDate("start_date"));
					visit.setEndDate(rs.getDate("end_date"));
					visit.setHoliday(rs.getString("holiday"));
					visits.add(visit);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/preemptivevisit GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return visits;
	}

	@GET
	@Path("/{tid}/mileage")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Mileage> getMileage(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Mileage> visitss = new ArrayList<Mileage>();
		
	
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String therapists = " select * from mileage1 where tid= ? and  final <> '' and finalpdf is null order by vdate desc";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapists);
				
				stmt.setInt(1, tid);
							
				rs = stmt.executeQuery();
				
				
				while(rs.next()) {
					Mileage  visit = new Mileage();
					visit.setTid(tid);
					//visit.setMile(rs.getDouble("mile"));
					visit.setResponse(rs.getString("final"));
					visit.setVdate(rs.getDate("vdate"));
					visitss.add(visit);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/mileage GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return visitss;
	}
	
	
	
	@GET
	@Path("/mileages")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Mileage> getMileages(@QueryParam("apiKey") String key) {
		List<Mileage> vis = new ArrayList<Mileage>();
		
	
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String therapists = "select * from mileage1 where final <> '' and finalpdf is null order by vdate desc ";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(therapists);
				rs = stmt.executeQuery();
				
				
				while(rs.next()) {
					Mileage  visit = new Mileage();
					visit.setTid(rs.getInt("tid"));
					visit.setResponse(rs.getString("final"));
					visit.setVdate(rs.getDate("vdate"));
					vis.add(visit);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /therapists/mileage GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				if(conn != null) {
					try { conn.close(); } catch (Exception ignore) {}
				}
			}
		}
		return vis;
	}
	
}