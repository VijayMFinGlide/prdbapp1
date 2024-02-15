package com.dfwpts.resources;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Insurance;

import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

@Singleton
@Path("/insurances")
public class InsuranceResource {

	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getAllInsurances(@QueryParam("apiKey") String key) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
	    	ResultSet rs = null;
	    	
			String insurances = "select * from insurance";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(insurances);
				
				while(rs.next()) {
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(rs.getInt("pid"));
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					
					if(rs.getString("eval_auth") != null) {
						insurance.setEvalAuth(rs.getString("eval_auth"));
					}
					if(rs.getDate("eval_start") != null) {
						insurance.setEvalStartDate(new Date(rs.getDate("eval_start").getTime()));
					}
					if(rs.getDate("eval_end") != null) {
						insurance.setEvalEndDate(new Date(rs.getDate("eval_end").getTime()));
					}
					if(rs.getString("memberid") != null) {
						insurance.setMemberId(rs.getString("memberid"));
					}
					
					insuranceList.add(insurance);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/ GET");
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
		return insuranceList;
	}

	@GET
	@Path("/old/{pid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getInsuranceByPatientId(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;
	    	
			String insuranceByPid = "select * from insurance where pid = ?";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					
					if(rs.getString("eval_auth") != null) {
						insurance.setEvalAuth(rs.getString("eval_auth"));
					}
					if(rs.getDate("eval_start") != null) {
						insurance.setEvalStartDate(new Date(rs.getDate("eval_start").getTime()));
					}
					if(rs.getDate("eval_end") != null) {
						insurance.setEvalEndDate(new Date(rs.getDate("eval_end").getTime()));
					}
					if(rs.getString("memberid") != null) {
						insurance.setMemberId(rs.getString("memberid"));
					}
					
					insurance.setIid1(rs.getInt("iid1"));
					insurance.setPrior1(rs.getString("prior1"));
					if(rs.getString("eval_auth1") != null) {
						insurance.setEvalAuth1(rs.getString("eval_auth1"));
					}
					if(rs.getDate("eval_start1") != null) {
						insurance.setEvalStartDate1(new Date(rs.getDate("eval_start1").getTime()));
					}
					if(rs.getDate("eval_end1") != null) {
						insurance.setEvalEndDate1(new Date(rs.getDate("eval_end1").getTime()));
					}
					insurance.setStartDate1(rs.getDate("startdate1"));
					insurance.setEndDate1(rs.getDate("enddate1"));
					insurance.setInsuredId1(rs.getString("insuredid1"));
					if(rs.getString("memberid1") != null) {
						insurance.setMemberId1(rs.getString("memberid1"));
					}

					insurance.setIid2(rs.getInt("iid2"));
					insurance.setPrior2(rs.getString("prior2"));
					if(rs.getString("eval_auth2") != null) {
						insurance.setEvalAuth1(rs.getString("eval_auth2"));
					}
					if(rs.getDate("eval_start2") != null) {
						insurance.setEvalStartDate1(new Date(rs.getDate("eval_start2").getTime()));
					}
					if(rs.getDate("eval_end2") != null) {
						insurance.setEvalEndDate1(new Date(rs.getDate("eval_end2").getTime()));
					}
					insurance.setStartDate2(rs.getDate("startdate2"));
					insurance.setEndDate2(rs.getDate("enddate2"));
					insurance.setInsuredId2(rs.getString("insuredid2"));
					if(rs.getString("memberid2") != null) {
						insurance.setMemberId2(rs.getString("memberid2"));
					}

					insuranceList.add(insurance);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/"+pid+" GET");
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
		return insuranceList;
	}

	@GET
	@Path("/{pid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getInsuranceByPatientIdNew(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;
//	    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	    	Date today = new Date();
	    	
			String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));
				stmt.setInt(3, 1);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {					
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("codes") != null) {
						insurance.setCodes(rs.getString("codes"));
					}
					insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
					insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));

					insuranceList.add(insurance);
				}
				
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));
				stmt.setInt(3, 0);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {					
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("codes") != null) {
						insurance.setCodes(rs.getString("codes"));
					}
					insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
					insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));

					insuranceList.add(insurance);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/"+pid+" GET");
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
		return insuranceList;
	}
	
	@GET
	@Path("/{pid}/new")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getInsuranceByPatientIdN(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;
	    	Date today = new Date();
	    	
			String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));
				stmt.setInt(3, 1);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {					
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setEval(rs.getInt("eval"));
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setFrequency(rs.getInt("frequency"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("codes") != null) {
						insurance.setCodes(rs.getString("codes"));
					}
					insurance.setMonthly(rs.getInt("monthly"));
					insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
					insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));
					insurance.setVisits(rs.getInt("visits"));
					insurance.setVisit1(rs.getInt("visit1"));
					insurance.setVisit2(rs.getInt("visit1"));
					insurance.setprocCode(rs.getString("proc_code"));
					if(rs.getString("proc_codes") != null) {
					insurance.setprocCodes(rs.getString("proc_codes"));
					}
					insuranceList.add(insurance);
				}
				
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));
				stmt.setInt(3, 0);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {					
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setEval(rs.getInt("eval"));
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setFrequency(rs.getInt("frequency"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("codes") != null) {
						insurance.setCodes(rs.getString("codes"));
					}
					insurance.setMonthly(rs.getInt("monthly"));
					insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
					insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));
					insurance.setVisits(rs.getInt("visits"));
					insurance.setVisit1(rs.getInt("visit1"));
					insurance.setVisit2(rs.getInt("visit1"));
					insurance.setprocCode(rs.getString("proc_code"));
					if(rs.getString("proc_codes") != null) {
					insurance.setprocCodes(rs.getString("proc_codes"));
					}

					insuranceList.add(insurance);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/"+pid+" GET");
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
		return insuranceList;
	}

	@GET
	@Path("/test/{pid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getInsuranceByPatientIdNewTesting(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();

		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
	    	if(pid < 0) {
	    		try {
					Date start = new Date();
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(start);

					cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
					start = cal.getTime();

					cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 91);
					Date end = cal.getTime();

					Insurance insurance = new Insurance();
					
					insurance.setIid(1);
					insurance.setPid(pid);
					insurance.setAuthorization("No Auth Req");
					insurance.setType(1);
					insurance.setStartDate(start);
					insurance.setEndDate(end);
					insurance.setInsuredId("Medicaid ID");
					insurance.setCodes("");
					insurance.setEveryOtherWeek(0);
					insurance.setEveryOtherWeekStartDate(null);
					
					insuranceList.add(insurance);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
	    	} else {
				Connection conn = null;
				PreparedStatement stmt = null;
		    	ResultSet rs = null;
		    	Date today = new Date();
		    	
				String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and type = ? order by startdate desc";
				String futureInsuranceByPid = "select * from insurances where pid = ? and startdate >= ? and type = ? order by startdate desc";
				
				try {
					conn = DBUtility.getConnectionPts();
					stmt = conn.prepareStatement(insuranceByPid);
					
					stmt.setInt(1, pid);
					stmt.setDate(2, new java.sql.Date(today.getTime()));
					stmt.setInt(3, 1);
					
					rs = stmt.executeQuery();
					
					if(rs.next()) {					
						Insurance insurance = new Insurance();
						
						insurance.setIid(rs.getInt("iid"));
						insurance.setPid(pid);
						insurance.setAuthorization(rs.getString("authorization"));
						insurance.setType(rs.getInt("type"));
						insurance.setStartDate(rs.getDate("startdate"));
						insurance.setEndDate(rs.getDate("enddate"));
						insurance.setInsuredId(rs.getString("insuredid"));
						if(rs.getString("codes") != null) {
							insurance.setCodes(rs.getString("codes"));
						}
						insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
						insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));
						
						insuranceList.add(insurance);
					}
					
					if(rs != null) {
						try { rs.close(); } catch (Exception ignore) {}
					}
					if(stmt != null) {
						try { stmt.close(); } catch (Exception ignore) {}
					}
					
					stmt = conn.prepareStatement(futureInsuranceByPid);
					
					stmt.setInt(1, pid);
					stmt.setDate(2, new java.sql.Date(today.getTime()));
					stmt.setInt(3, 1);
					
					rs = stmt.executeQuery();
					
					if(rs.next()) {					
						Insurance insurance = new Insurance();
						
						insurance.setIid(rs.getInt("iid"));
						insurance.setPid(pid);
						insurance.setAuthorization(rs.getString("authorization"));
						insurance.setType(rs.getInt("type"));
						insurance.setStartDate(rs.getDate("startdate"));
						insurance.setEndDate(rs.getDate("enddate"));
						insurance.setInsuredId(rs.getString("insuredid"));
						if(rs.getString("codes") != null) {
							insurance.setCodes(rs.getString("codes"));
						}
						insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
						insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));

						insuranceList.add(insurance);
					}
					
					if(rs != null) {
						try { rs.close(); } catch (Exception ignore) {}
					}
					if(stmt != null) {
						try { stmt.close(); } catch (Exception ignore) {}
					}
					
					stmt = conn.prepareStatement(insuranceByPid);
					
					stmt.setInt(1, pid);
					stmt.setDate(2, new java.sql.Date(today.getTime()));
					stmt.setInt(3, 0);
					
					rs = stmt.executeQuery();
					
					if(rs.next()) {					
						Insurance insurance = new Insurance();
						
						int iid = rs.getInt("iid");
						if(iid != 0) {
							insurance.setIid(rs.getInt("iid"));
							insurance.setPid(pid);
							insurance.setAuthorization(rs.getString("authorization"));
							insurance.setType(rs.getInt("type"));
							insurance.setStartDate(rs.getDate("startdate"));
							insurance.setEndDate(rs.getDate("enddate"));
							insurance.setInsuredId(rs.getString("insuredid"));
							if(rs.getString("codes") != null) {
								insurance.setCodes(rs.getString("codes"));
							}
							insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
							insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));

							insuranceList.add(insurance);
						}
					}

					if(rs != null) {
						try { rs.close(); } catch (Exception ignore) {}
					}
					if(stmt != null) {
						try { stmt.close(); } catch (Exception ignore) {}
					}
					
					stmt = conn.prepareStatement(futureInsuranceByPid);
					
					stmt.setInt(1, pid);
					stmt.setDate(2, new java.sql.Date(today.getTime()));
					stmt.setInt(3, 0);
					
					rs = stmt.executeQuery();
					
					if(rs.next()) {					
						Insurance insurance = new Insurance();

						int iid = rs.getInt("iid");
						if(iid != 0) {
							insurance.setIid(rs.getInt("iid"));
							insurance.setPid(pid);
							insurance.setAuthorization(rs.getString("authorization"));
							insurance.setType(rs.getInt("type"));
							insurance.setStartDate(rs.getDate("startdate"));
							insurance.setEndDate(rs.getDate("enddate"));
							insurance.setInsuredId(rs.getString("insuredid"));
							if(rs.getString("codes") != null) {
								insurance.setCodes(rs.getString("codes"));
							}
							insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
							insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));
							
							insuranceList.add(insurance);
						}
					}

		    	} catch(Exception e) {
					System.out.println("ERROR /insurances/"+pid+" GET");
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
		return insuranceList;
	}

	@GET
	@Path("/eval/{pid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getEvalInsuranceByPatientIdNew(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;
	    	Date today = new Date();
	    	
			String insuranceByPid = "select * from insurances where pid = ? and startdate <= ? and enddate >= ? and type = ? and eval=? order by startdate desc";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));
				stmt.setDate(3, new java.sql.Date(today.getTime()));
				stmt.setInt(4, 1);
				stmt.setInt(5, 1);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {					
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("codes") != null) {
						insurance.setCodes(rs.getString("codes"));
					}

					insuranceList.add(insurance);
				}
				
				if(rs != null) {
					try { rs.close(); } catch (Exception ignore) {}
				}
				if(stmt != null) {
					try { stmt.close(); } catch (Exception ignore) {}
				}
				
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));
				stmt.setDate(3, new java.sql.Date(today.getTime()));
				stmt.setInt(4, 0);
				stmt.setInt(5, 1);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {					
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					if(rs.getString("codes") != null) {
						insurance.setCodes(rs.getString("codes"));
					}

					insuranceList.add(insurance);
				}
				
				
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/eval/"+pid+" GET");
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
		return insuranceList;
	}
	
	@GET
	@Path("/{pid}/pending")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getPendingInsuranceByPatientId(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;
	    	Date today = new Date();

			String insuranceByPid = "select * from insurances where pid=? and startdate >= ?";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insuranceByPid);
				
				stmt.setInt(1, pid);
				stmt.setDate(2, new java.sql.Date(today.getTime()));

				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Insurance insurance = new Insurance();
					
					insurance.setIid(rs.getInt("iid"));
					insurance.setPid(pid);
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setInsuredId(rs.getString("insuredid"));
					
					if(rs.getString("memberid") != null) {
						insurance.setMemberId(rs.getString("memberid"));
					}
					insurance.setEveryOtherWeek(rs.getInt("everyotherweek"));
					insurance.setEveryOtherWeekStartDate(rs.getDate("eow_startdate"));

					insuranceList.add(insurance);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/"+pid+"/pending GET");
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
		return insuranceList;
	}

	@GET
	@Path("/name/{lastname}-{firstname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Insurance> getInsuranceByName(@QueryParam("apiKey") String key, @PathParam("lastname") String lastName, @PathParam("firstname") String firstName) {
		List<Insurance> insuranceList = new ArrayList<Insurance>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String insuranceByNames = "select * from insurance where pid = (select pid from patient where lname=? and fname=?);";
			
			try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insuranceByNames);
				
				stmt.setString(1, lastName);
				stmt.setString(2, firstName);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Insurance insurance = new Insurance();

					insurance.setPid(rs.getInt("pid"));
					insurance.setInsuredId(rs.getString("insuredid"));
					insurance.setAuthorization(rs.getString("authorization"));
					insurance.setType(rs.getInt("type"));
					insurance.setStartDate(rs.getDate("startdate"));
					insurance.setEndDate(rs.getDate("enddate"));
					insurance.setIid(rs.getInt("iid"));
					if(rs.getString("memberid") != null) {
						insurance.setMemberId(rs.getString("memberid"));
					}
					
					if(rs.getInt("iid1")!=0) {
						insurance.setIid1(rs.getInt("iid1"));
						insurance.setStartDate1(rs.getDate("startdate1"));
						insurance.setEndDate1(rs.getDate("enddate1"));
						insurance.setPrior1(rs.getString("prior1"));
						if(rs.getString("memberid1") != null) {
							insurance.setMemberId1(rs.getString("memberid1"));
						}
					}
					if(rs.getInt("iid2")!=0) {
						insurance.setIid2(rs.getInt("iid2"));
						insurance.setStartDate2(rs.getDate("startdate2"));
						insurance.setEndDate2(rs.getDate("enddate2"));
						insurance.setPrior2(rs.getString("prior2"));
						if(rs.getString("memberid2") != null) {
							insurance.setMemberId2(rs.getString("memberid2"));
						}
					}

					insuranceList.add(insurance);
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /insurances/name/{lastname}-{firstname} GET");
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
		return insuranceList;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void postInsurance(@QueryParam("apiKey") String key, Insurance ins) {
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	String insertInsurance = "insert into insurance (iid,pid,insuredid,startdate,enddate,authorization,type,memberid) values (?,?,?,?,?,?,?,?)";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insertInsurance);
				
				stmt.setInt(1, ins.getIid());
				stmt.setInt(2, ins.getPid());
				stmt.setString(3, ins.getInsuredId());
				stmt.setDate(4, new java.sql.Date(ins.getStartDate().getTime()));
				stmt.setDate(5, new java.sql.Date(ins.getEndDate().getTime()));
				stmt.setString(6, ins.getAuthorization());
				stmt.setInt(7, ins.getType());
				stmt.setString(8, ins.getMemberId());
				
				stmt.executeUpdate();
			}  catch(Exception e) {
				System.out.println("ERROR /insurances/ POST");
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

	@POST
	@Path("/parkland/")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void postInsuranceForParkland(@QueryParam("apiKey") String key, Insurance ins) {
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	String insertInsurance = "insert into insurance (iid,pid,insuredid,eval_auth,eval_start,eval_end,startdate,enddate,authorization,type,memberid) values (?,?,?,?,?,?,?,?,?,?,?)";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(insertInsurance);
				
				stmt.setInt(1, ins.getIid());
				stmt.setInt(2, ins.getPid());
				stmt.setString(3, ins.getInsuredId());
				stmt.setString(4, ins.getEvalAuth());
				stmt.setDate(5, new java.sql.Date(ins.getEvalStartDate().getTime()));
				stmt.setDate(6, new java.sql.Date(ins.getEvalEndDate().getTime()));
				stmt.setDate(7, new java.sql.Date(ins.getStartDate().getTime()));
				stmt.setDate(8, new java.sql.Date(ins.getEndDate().getTime()));
				stmt.setString(9, ins.getAuthorization());
				stmt.setInt(10, ins.getType());
				stmt.setString(11, ins.getMemberId());
				
				stmt.executeUpdate();
			}  catch(Exception e) {
				System.out.println("ERROR /insurances/parkland POST");
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
	@Path("/{pid}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Insurance putInsurance(Insurance insurance) {
		Connection conn = null;
		PreparedStatement stmt = null;

    	String updateInsurance = "update insurance set iid=?, insuredid=?, startdate=?, enddate=?, authorization=?, iid1=?, prior1=?, startdate1=?, enddate1=?, iid2=?, prior2=?, startdate2=?, enddate2=? where pid=? and type=?";

    	try {
			conn = DBUtility.getConnectionPts();
			stmt = conn.prepareStatement(updateInsurance);

			stmt.setInt(1, insurance.getIid());
			stmt.setString(2, insurance.getInsuredId());
			stmt.setDate(3, new java.sql.Date(insurance.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(insurance.getEndDate().getTime()));
			stmt.setString(5, insurance.getAuthorization());
			stmt.setInt(6, insurance.getIid1());
			stmt.setString(7, insurance.getPrior1());
			stmt.setDate(8, new java.sql.Date(insurance.getStartDate1().getTime()));
			stmt.setDate(9, new java.sql.Date(insurance.getEndDate1().getTime()));
			stmt.setInt(10, insurance.getIid2());
			stmt.setString(11, insurance.getPrior2());
			stmt.setDate(12, new java.sql.Date(insurance.getStartDate().getTime()));
			stmt.setDate(13, new java.sql.Date(insurance.getEndDate().getTime()));
			stmt.setInt(14, insurance.getPid());
			stmt.setInt(15, insurance.getType());
			
			stmt.executeUpdate();
		}  catch(Exception e) {
			System.out.println("ERROR /insurances/{pid} PUT");
    		System.out.println(e.getMessage());
		} finally {
			if(stmt != null) {
				try { stmt.close(); } catch (Exception ignore) {}
			}
			if(conn != null) {
				try { conn.close(); } catch (Exception ignore) {}
			}
		}
    	return insurance;
	}
}
