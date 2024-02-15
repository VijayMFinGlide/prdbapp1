package com.dfwpts.resources;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Address;
import com.dfwpts.model.Address2;
import com.dfwpts.model.Discharge;
import com.dfwpts.model.Hold;
import com.dfwpts.model.Insurance;
import com.dfwpts.model.MakeupVisit;
import com.dfwpts.model.Patient;
import com.dfwpts.model.PatientAction;

import java.util.*;
import java.sql.Connection;
import java.sql.Date;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

@Singleton
@Path("/patients")
public class PatientResource {

	String withRootUrl = "jdbc:google:mysql://preferred-database:ptsdb/ptsdb?user=root";

	@GET
	@Path("/{pid}/addresses")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Address> getPatientsAddress(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
		List<Address> list = new ArrayList<Address>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String addresses = "select * from update_address where pid = ? and verified = ?";

	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(addresses);
				
				stmt.setInt(1, id);
				stmt.setString(2, "Y");
				
	    		rs = stmt.executeQuery();
	    		
	    		while(rs.next()) {
	    			String line1 = rs.getString("address_line1");
	    			String city = rs.getString("city");
	    			String state = rs.getString("state");
	    			String zip = rs.getString("zip");
	    			String reason = rs.getString("reason");
	    			String verified = rs.getString("verified");
	    			
	    			Address a = new Address();
	    		
	    			
	    			a.setLine1(line1);
	    			a.setCity(city);
	    			a.setState(state);
	    			a.setZip(zip);
	    			a.setReason(reason);
	    			
	    			if("Y".equals(verified)) {
	    				a.setVerified(1);
	    			} else {
	    				a.setVerified(0);
	    			}
	    			
	    			list.add(a);
	    		}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/" + id + "/addresses GET");
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
	@Path("/{pid}/addresses2")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Address2> getPatientsAddress2(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
		List<Address2> list = new ArrayList<Address2>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String addresses = "select * from address_history where pid = ?";

	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(addresses);
				
				stmt.setInt(1, id);
				
	    		rs = stmt.executeQuery();
	    		
	    		while(rs.next()) {
	    			String line1 = rs.getString("address_line1");
	    			String city = rs.getString("city");
	    			String state = rs.getString("state");
	    			String zip = rs.getString("zip");
	    			String lat = rs.getString("lat");
	    			String longs = rs.getString("long");
	    			Date start = rs.getDate("start");
	    			Date end = rs.getDate("end");
	    			int tp = rs.getInt("tp");
	    			/*if (rs.getString("lat") == null) {
	    				lat = "";
	    			} else   {
	    				lat = rs.getString("lat");
	    			}
    	         if (rs.getString("long") == null) {
	    				longs = "";
	    			} else   {
	    				longs = rs.getString("long");
	    			}*/
	    			
	    			Address2 a = new Address2();
	    			
	    			a.setLine1(line1);
	    			a.setCity(city);
	    			a.setState(state);
	    			a.setZip(zip);
	    	         if (rs.getBoolean("home")) {
	    				a.setType("home");
	    			} else if (rs.getBoolean("billing")) {
	    				a.setType("billing");
	    			} else if (rs.getBoolean("therapy")) {
	    				a.setType("therapy");
	    			}
	    	         
	    	         
	    	        a.setLat(lat);
			    	a.setLongs(longs);
			    	a.setStart(start);
			    	a.setEnd(end);
			    	a.setTp(tp);
	    			list.add(a);
	    		}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/" + id + "/addresses2 GET");
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
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getPatients(@QueryParam("apiKey") String key) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
	    	ResultSet rs = null;

	    	String patients = "select * from patient";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(patients);

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
					p.setCoverSheet(rs.getInt("coversheet"));
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
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setNotes(rs.getString("notes"));
					p.setFrequency(rs.getInt("frequency"));
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					if(rs.getDate("onhold_date") != null) {
						p.setOnHoldDate(rs.getDate("onhold_date"));
					}
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					if(rs.getDate("discharged_date") != null) {
						p.setDischargeDate(rs.getDate("discharged_date"));
					}					
					p.setDischargeReason(rs.getInt("discharged_reason"));
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setTid1_1(rs.getInt("tid1_1"));
					p.setTid1_2(rs.getInt("tid1_2"));
					p.setTid1_3(rs.getInt("tid1_3"));
					p.setTid1_4(rs.getInt("tid1_4"));
					p.setTid2_1(rs.getInt("tid2_1"));
					p.setTid2_2(rs.getInt("tid2_2"));
					p.setTid2_3(rs.getInt("tid2_3"));
					p.setFreq2(rs.getInt("freq2"));
					p.setFreq1_1(rs.getInt("freq1_1"));
					p.setFreq1_2(rs.getInt("freq1_2"));
					p.setFreq1_3(rs.getInt("freq1_3"));
					p.setFreq1_4(rs.getInt("freq1_4"));
					p.setFreq2_1(rs.getInt("freq2_1"));
					p.setFreq2_3(rs.getInt("freq2_2"));
					p.setFreq2_3(rs.getInt("freq2_3"));
					p.setBlobKey(rs.getString("blob_key"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));
					
					patientList.add(p);
				}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/ GET");
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
	@Path("/{pid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Patient getPatientById(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
		Patient p = new Patient();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, iStmt = null;
	    	ResultSet rs = null, iRs = null;

	    	String patientById = "select * from patient where pid=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(patientById);

				p.setPid(id);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					
					p.setPid(rs.getInt("pid"));
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
					p.setSubId(rs.getInt("sub_tid"));
					p.setNotes(rs.getString("notes"));
					p.setFrequency(rs.getInt("frequency"));
					p.setFreq2(rs.getInt("freq2"));
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					if(rs.getDate("onhold_date") != null) {
						p.setOnHoldDate(rs.getDate("onhold_date"));
					}
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					if(rs.getDate("discharged_date") != null) {
						p.setDischargeDate(rs.getDate("discharged_date"));
					}					
					p.setDischargeReason(rs.getInt("discharged_reason"));
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					
					int evaluator = rs.getInt("evaluator");
					int efreq = rs.getInt("efreq");
					
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
						if(rs.getInt("tid1_1") == 9) {
							p.setTid1_1(evaluator);
							p.setFreq1_1(efreq);
						} else if(rs.getInt("tid1_2") == 9) {
							p.setTid1_2(evaluator);
							p.setFreq1_2(efreq);
						} else if(rs.getInt("tid1_3") == 9) {
							p.setTid1_3(evaluator);
							p.setFreq1_3(efreq);
						} else if(rs.getInt("tid1_4") == 9) {
							p.setTid1_4(evaluator);
							p.setFreq1_4(efreq);
						} else if(rs.getInt("tid2_1") == 9) {
							p.setTid2_1(evaluator);
							p.setFreq2_1(efreq);
						} else if(rs.getInt("tid2_2") == 9) {
							p.setTid2_2(evaluator);
							p.setFreq2_2(efreq);
						} else if(rs.getInt("tid2_3") == 9) {
							p.setTid2_3(evaluator);
							p.setFreq2_3(efreq);
						}
					}

					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setBlobKey(rs.getString("blob_key"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));
					p.setIntakePacket(rs.getInt("intake_packet"));
					p.setPrevId(rs.getInt("prev_id"));
					p.setCoverSheet(rs.getInt("coversheet"));
								    	
					String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";

					iStmt = conn.prepareStatement(insuranceByPid);
					
					iStmt.setInt(1, id);
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
					
					iStmt.setInt(1, id);
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
					
					iStmt.setInt(1, id);
					iStmt.setInt(2, 1);

					iRs = iStmt.executeQuery();
					
					if(iRs.next()) {
						p.setPhone(iRs.getString("phone"));
					} else {
						p.setPhone("");
					}
					
				}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+id+" GET");
	    		System.out.println(e.getMessage());
			} finally {
				if(iRs != null) {
					try { iRs.close(); } catch (Exception ignore) {}
				}
				if(iStmt != null) {
					try { iStmt.close(); } catch (Exception ignore) {}
				}
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
		return p;
	}
	
	@GET
	@Path("/{pid}/dischargeinfo")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Discharge getDischargeInfo(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
		Discharge d = new Discharge();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String infoByPid = "select * from discharges where pid=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(infoByPid);

				stmt.setInt(1, id);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					d.setPid(id);
					d.setDate(rs.getDate("date"));
					d.setNotes(rs.getString("notes"));
					d.setReasonCode(rs.getInt("reason"));
				}
				
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+id+"/dischargeinfo GET");
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
		
		return d;
	}

	@GET
	@Path("/{pid}/insurances")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getInsuranceByPid(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
		List<Insurance> insList = new ArrayList<Insurance>();
		Insurance ins = new Insurance();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String insByPid = "select * from insurance where pid=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insByPid);

				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					ins.setPid(rs.getInt("pid"));
					ins.setIid(rs.getInt("iid"));
					ins.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("memberid") != null) {
						ins.setMemberId(rs.getString("memberid"));
					}
					if(rs.getString("eval_auth") != null) {
						ins.setEvalAuth(rs.getString("eval_auth"));
					}
					if(rs.getString("eval_start") != null) {
						ins.setEvalStartDate(new Date(rs.getDate("eval_start").getTime()));
					}
					if(rs.getString("eval_end") != null) {
						ins.setEvalEndDate(new Date(rs.getDate("eval_end").getTime()));
					}						
					ins.setStartDate(new Date(rs.getDate("startdate").getTime()));
					ins.setEndDate(new Date(rs.getDate("enddate").getTime()));
					ins.setAuthorization(rs.getString("authorization"));
					ins.setType(rs.getInt("type"));
					
				}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+id+"/insurances GET");
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
		return insList;
	}
	
	@GET
	@Path("/MRNum/{mrnum}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Patient getPatientByMRNum(@QueryParam("apiKey") String key, @PathParam("mrnum") String mrnum) {
		Patient p = new Patient();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String patientById = "select * from patient where mrnum=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(patientById);

				stmt.setString(1, mrnum);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					
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
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setNotes(rs.getString("notes"));
					p.setFrequency(rs.getInt("frequency"));
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					if(rs.getDate("onhold_date") != null) {
						p.setOnHoldDate(rs.getDate("onhold_date"));
					}
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					if(rs.getDate("discharged_date") != null) {
						p.setDischargeDate(rs.getDate("discharged_date"));
					}					
					p.setDischargeReason(rs.getInt("discharged_reason"));
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setTid1_1(rs.getInt("tid1_1"));
					p.setTid1_2(rs.getInt("tid1_2"));
					p.setTid1_3(rs.getInt("tid1_3"));
					p.setTid1_4(rs.getInt("tid1_4"));
					p.setTid2_1(rs.getInt("tid2_1"));
					p.setTid2_2(rs.getInt("tid2_2"));
					p.setTid2_3(rs.getInt("tid2_3"));
					p.setFreq2(rs.getInt("freq2"));
					p.setFreq1_1(rs.getInt("freq1_1"));
					p.setFreq1_2(rs.getInt("freq1_2"));
					p.setFreq1_3(rs.getInt("freq1_3"));
					p.setFreq1_4(rs.getInt("freq1_4"));
					p.setFreq2_1(rs.getInt("freq2_1"));
					p.setFreq2_3(rs.getInt("freq2_2"));
					p.setFreq2_3(rs.getInt("freq2_3"));
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setBlobKey(rs.getString("blob_key"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));
					
				}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/mrnum/"+mrnum+" GET");
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
		return p;
	}

	@GET
	@Path("/name/{lastname}_{firstname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getPatientByName(@QueryParam("apiKey") String key, @PathParam("lastname") String lastName, @PathParam("firstname") String firstName) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String patientByName = "select * from patient where lname=? and fname=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(patientByName);

				stmt.setString(1, lastName);
				stmt.setString(2, firstName);
				
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
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setNotes(rs.getString("notes"));
					p.setFrequency(rs.getInt("frequency"));
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					if(rs.getDate("onhold_date") != null) {
						p.setOnHoldDate(rs.getDate("onhold_date"));
					}
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					if(rs.getDate("discharged_date") != null) {
						p.setDischargeDate(rs.getDate("discharged_date"));
					}					
					p.setDischargeReason(rs.getInt("discharged_reason"));
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setTid1_1(rs.getInt("tid1_1"));
					p.setTid1_2(rs.getInt("tid1_2"));
					p.setTid1_3(rs.getInt("tid1_3"));
					p.setTid1_4(rs.getInt("tid1_4"));
					p.setTid2_1(rs.getInt("tid2_1"));
					p.setTid2_2(rs.getInt("tid2_2"));
					p.setTid2_3(rs.getInt("tid2_3"));
					p.setFreq2(rs.getInt("freq2"));
					p.setFreq1_1(rs.getInt("freq1_1"));
					p.setFreq1_2(rs.getInt("freq1_2"));
					p.setFreq1_3(rs.getInt("freq1_3"));
					p.setFreq1_4(rs.getInt("freq1_4"));
					p.setFreq2_1(rs.getInt("freq2_1"));
					p.setFreq2_3(rs.getInt("freq2_2"));
					p.setFreq2_3(rs.getInt("freq2_3"));
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setBlobKey(rs.getString("blob_key"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));

					patientList.add(p);
				}

	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/name/"+lastName+"-"+firstName+" GET");
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
	@Path("/fname/{firstname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getPatientByFirstName(@QueryParam("apiKey") String key, @PathParam("firstname") String firstName) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String patientByName = "select * from patient where fname=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(patientByName);

				stmt.setString(1, firstName);
				
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
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setNotes(rs.getString("notes"));
					p.setFrequency(rs.getInt("frequency"));
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					if(rs.getDate("onhold_date") != null) {
						p.setOnHoldDate(rs.getDate("onhold_date"));
					}
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					if(rs.getDate("discharged_date") != null) {
						p.setDischargeDate(rs.getDate("discharged_date"));
					}					
					p.setDischargeReason(rs.getInt("discharged_reason"));
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setTid1_1(rs.getInt("tid1_1"));
					p.setTid1_2(rs.getInt("tid1_2"));
					p.setTid1_3(rs.getInt("tid1_3"));
					p.setTid1_4(rs.getInt("tid1_4"));
					p.setTid2_1(rs.getInt("tid2_1"));
					p.setTid2_2(rs.getInt("tid2_2"));
					p.setTid2_3(rs.getInt("tid2_3"));
					p.setFreq2(rs.getInt("freq2"));
					p.setFreq1_1(rs.getInt("freq1_1"));
					p.setFreq1_2(rs.getInt("freq1_2"));
					p.setFreq1_3(rs.getInt("freq1_3"));
					p.setFreq1_4(rs.getInt("freq1_4"));
					p.setFreq2_1(rs.getInt("freq2_1"));
					p.setFreq2_3(rs.getInt("freq2_2"));
					p.setFreq2_3(rs.getInt("freq2_3"));
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setBlobKey(rs.getString("blob_key"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));

					patientList.add(p);
				}

	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/fname"+firstName+" GET");
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
	@Path("/lname/{lastname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Patient> getPatientByLastName(@QueryParam("apiKey") String key, @PathParam("lastname") String lastName) {
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String patientByName = "select * from patient where lname=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(patientByName);

				stmt.setString(1, lastName);
				
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
					p.setTherapistId(rs.getInt("assigned_tid"));
					p.setAssignedTid2(rs.getInt("assigned_tid2"));
					p.setSubId(rs.getInt("sub_tid"));
					p.setNotes(rs.getString("notes"));
					p.setFrequency(rs.getInt("frequency"));
					p.setMonthlyFreq(rs.getInt("monthly_freq"));
					p.setOnHold(rs.getInt("onhold"));
					p.setOnHoldReason(rs.getInt("onhold_reason"));
					if(rs.getDate("onhold_date") != null) {
						p.setOnHoldDate(rs.getDate("onhold_date"));
					}
					p.setNonAdmitted(rs.getInt("non_admitted"));
					p.setDischarged(rs.getInt("discharged"));
					if(rs.getDate("discharged_date") != null) {
						p.setDischargeDate(rs.getDate("discharged_date"));
					}					
					p.setDischargeReason(rs.getInt("discharged_reason"));
					if(rs.getDate("entry_date")!=null) {
						p.setEntryDate(rs.getDate("entry_date"));
					}
					p.setTid1_1(rs.getInt("tid1_1"));
					p.setTid1_2(rs.getInt("tid1_2"));
					p.setTid1_3(rs.getInt("tid1_3"));
					p.setTid1_4(rs.getInt("tid1_4"));
					p.setTid2_1(rs.getInt("tid2_1"));
					p.setTid2_2(rs.getInt("tid2_2"));
					p.setTid2_3(rs.getInt("tid2_3"));
					p.setFreq2(rs.getInt("freq2"));
					p.setFreq1_1(rs.getInt("freq1_1"));
					p.setFreq1_2(rs.getInt("freq1_2"));
					p.setFreq1_3(rs.getInt("freq1_3"));
					p.setFreq1_4(rs.getInt("freq1_4"));
					p.setFreq2_1(rs.getInt("freq2_1"));
					p.setFreq2_3(rs.getInt("freq2_2"));
					p.setFreq2_3(rs.getInt("freq2_3"));
					p.setPrevFreq(rs.getInt("prevFreq"));
					p.setBlobKey(rs.getString("blob_key"));
					p.setStid(rs.getInt("stid"));
					p.setPtid(rs.getInt("ptid"));
					p.setOtid(rs.getInt("otid"));

					patientList.add(p);
				}

	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/fname"+lastName+" GET");
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
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addPatient(Patient patient) {
		Connection conn = null;
		PreparedStatement stmt = null;
    	ResultSet rs = null;

    	String insertPatient = "insert into patient (fname, lname, sex, birth, address_line1, city, state, zip, alt_address_line1, alt_city, alt_zip, parent_name, phone, email, docid, assigned_tid, alt_phone, mrnum, notes, entry_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	try {
			conn = DBUtility.getConnectionPts();
			stmt = conn.prepareStatement(insertPatient);

			stmt.setString(1, patient.getFirstName());
			stmt.setString(2, patient.getLastName());
			stmt.setString(3, patient.getSex());
			stmt.setDate(4, new Date(patient.getBirthDate().getTime()));
			stmt.setString(5, patient.getAddress());
			stmt.setString(6, patient.getCity());
			stmt.setString(7, patient.getState());
			stmt.setString(8, patient.getZip());
			stmt.setString(9, patient.getAltAddress());
			stmt.setString(10, patient.getAltCity());
			stmt.setString(11, patient.getAltZip());
			stmt.setString(12, patient.getParent());
			stmt.setString(13, patient.getPhone());
			stmt.setString(14, patient.getEmail());
			stmt.setInt(15, patient.getDocId());
			stmt.setInt(16, patient.getTherapistId());
			stmt.setString(17, patient.getAltPhone());
			stmt.setString(18, patient.getMRNum());
			stmt.setString(19, patient.getNotes());
			stmt.setDate(20, new Date(patient.getEntryDate().getTime()));
			
			stmt.executeUpdate();
			
    	} catch(Exception e) {
    		System.out.println("ERROR: patients/ POST");
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
	@Path("/{pid}/admitted")
	public void updateAdmissionDetails(@QueryParam("apiKey") String key, @PathParam("pid") int pid, Patient p) {
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
	    	String updatePatient = "UPDATE patient SET admitted=?, admission_date=?, eval_auth=?, visits_auth=?, frequency=?, monthly_freq=? WHERE pid=?";
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(updatePatient);
				
				stmt.setInt(1, 1);
				stmt.setDate(2, new Date(p.getAdmissionDate().getTime()));
				stmt.setInt(3, 1);
				stmt.setInt(4, p.getVisits());
				stmt.setInt(5, p.getFrequency());
				stmt.setInt(6, p.getMonthlyFreq());
				stmt.setInt(7, pid);

				stmt.executeUpdate();
				
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+pid+"/admitted PUT");
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
	}	
	
	@PUT
	@Path("/{pid}/info")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updatePatientInfo(@PathParam("pid") int pid, Patient patient) {
		if(patient.getPid() == pid) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String updatePatient = "UPDATE patient SET address_line1=?, city=?, zip=?, phone=?, email=?, assigned_tid=?, notes=?, assigned_tid2=?, sub_tid=?, fname=?, lname=?, birth=?, frequency=?, visits_auth=? WHERE pid=?";

	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(updatePatient);
				
				stmt.setString(1, patient.getAddress());
				stmt.setString(2, patient.getCity());
				stmt.setString(3, patient.getZip());
				stmt.setString(4, patient.getPhone());
				stmt.setString(5, patient.getEmail());
				stmt.setInt(6, patient.getTherapistId());
				stmt.setString(7, patient.getNotes());
				stmt.setInt(8, patient.getAssignedTid2());
				stmt.setInt(9, patient.getSubId());
				stmt.setString(10, patient.getFirstName());
				stmt.setString(11, patient.getLastName());
				stmt.setDate(12, new Date(patient.getBirthDate().getTime()));
				stmt.setInt(13, patient.getFrequency());
				stmt.setInt(14, patient.getVisits());
				stmt.setInt(15, pid);

				stmt.executeUpdate();
				
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+pid+"/info PUT");
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
	
	@PUT
	@Path("/{pid}/auth")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updatePatientAuth(@PathParam("pid") int pid, Patient p) {
		if(pid == p.getPid()) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String updatePatient = "UPDATE patient SET eval_auth=?, visits_auth=? WHERE pid=?";

	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(updatePatient);
				
				if(p.getEvalAuth()) {
					stmt.setInt(1, 1);
				} else {
					stmt.setInt(1, 0);
				}
				stmt.setInt(2, p.getVisits());
				stmt.setInt(3, pid);
				
				stmt.executeUpdate();
				
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+pid+"/auth PUT");
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
	@Path("/{pid}/makeupvisits")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<MakeupVisit> getMakeupVisitsByPid(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
		List<MakeupVisit> makeupVisitList = new ArrayList<MakeupVisit>();
		MakeupVisit makeupVisit = new MakeupVisit();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

	    	String makeupVisitsByPid = "select * from makeup_visit where pid = ? order by start_date";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(makeupVisitsByPid);

				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					
					
					
					makeupVisit.setPid(rs.getInt("pid"));
					makeupVisit.setStartDate(rs.getDate("start_date"));
					makeupVisit.setEndDate(rs.getDate("end_date"));					
					makeupVisitList.add(makeupVisit);
				}
	    	} catch(Exception e) {
	    		System.out.println("ERROR: patients/"+id+"/makeupvisits GET");
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
		return makeupVisitList;
	}


@GET
@Path("/{pid}/holdinfo")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public List<Hold> getholdInfo(@QueryParam("apiKey") String key, @PathParam("pid") int id) {
	List<Hold> HoldList = new ArrayList<Hold>();
	
	
	if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
		Connection conn = null;
		PreparedStatement stmt = null;
    	ResultSet rs = null;

    	String infoByPid = "select * from holds where pid=?";
    	
    	try {
			conn = DBUtility.getConnectionPts();
			
			stmt = conn.prepareStatement(infoByPid);

			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Date holddate = rs.getDate("holddate");
    			Date removedate = rs.getDate("removedate");
    			int  reason = rs.getInt("reason");
    			
				Hold hold = new Hold();
				hold.setPid(id);
				hold.setHoldDate(holddate);
				hold.setRemoveDate(removedate);
				hold.setReasonCode(reason);
				
				HoldList.add(hold);
				
			}
			
    	} catch(Exception e) {
    		System.out.println("ERROR: patients/"+id+"/holdinfo GET");
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
	
	return HoldList;
}
@GET
@Path("/actions")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public List<PatientAction> getAction(@QueryParam("apiKey") String key) {
	List<PatientAction> actionList = new ArrayList<PatientAction>();
	
	if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
		Connection conn = null;
		PreparedStatement stmt = null;
    	ResultSet rs = null;

    	String action = "select * from actions where parent = ? and synced = ? ";
    	
    	try {
			conn = DBUtility.getConnectionPts();
			
			stmt = conn.prepareStatement(action);
			stmt.setInt(1, 1);
			stmt.setInt(2, 0);
			rs = stmt.executeQuery();
			
			

			while(rs.next()) {
				PatientAction a = new PatientAction();
				a.setActionid(rs.getInt("action_id"));
				a.setPid(rs.getInt("pid"));
				a.setCreatedby(rs.getString("created_by"));
				a.setCreatedat(rs.getDate("created_at"));
				a.setActionRequired(rs.getString("action_required"));
				if(rs.getString("action_taken")!=null) {
					a.setActionTaken(rs.getString("action_taken"));
				}
				a.setCompleted(rs.getInt("completed"));
				if(rs.getDate("completed_on")!=null) {
					a.setCompletedOn(rs.getDate("completed_on"));
				}
				
				
				
				actionList.add(a);
			}
    	} catch(Exception e) {
    		System.out.println("ERROR: action/ GET");
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
	return actionList;
}


}