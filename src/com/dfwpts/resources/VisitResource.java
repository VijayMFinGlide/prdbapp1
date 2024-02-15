package com.dfwpts.resources;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Visit;
import com.dfwpts.model.Visits;

import java.sql.Connection;
import java.sql.Date;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
//import java.util.Date;

@Singleton
@Path("/visits")
public class VisitResource {
	String withRootUrl = "jdbc:google:mysql://preferred-database:ptsdb/ptsdb?user=root";

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Visit addVisit(Visit v) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String insertVisit = "INSERT INTO visit (pid,tid,superid,vdate,vstime,vetime,type,image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,image_url_7,image_url_8,approved,saturday,free,freq,late,procode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			conn = DBUtility.getConnectionPts();
			
			stmt = conn.prepareStatement(insertVisit);
			
			stmt.setInt(1, v.getPid());
			stmt.setInt(2, v.getTid());
			stmt.setInt(3, v.getSuperId());
			stmt.setDate(4, new Date(v.getVDate().getTime()));
			stmt.setTime(5, new java.sql.Time(v.getStartTime().getTime()));
			stmt.setTime(6, new java.sql.Time(v.getEndTime().getTime()));
			stmt.setInt(7, v.getType());
			stmt.setString(8, v.getImageUrl1());
			
			//Type == 4 not set up
			if(v.getType()==0) {
				stmt.setString(9, v.getImageUrl2());
				stmt.setString(10, "");
				stmt.setString(11, "");
				stmt.setString(12, "");
				stmt.setString(13, "");
				stmt.setString(14, "");
			}
			if(v.getType()==1 || v.getType()==3) {
				stmt.setString(9, "");
				stmt.setString(10, "");
				stmt.setString(11, "");
				stmt.setString(12, "");
				stmt.setString(13, "");
				stmt.setString(14, "");
			}
			if(v.getType()==2) {
				stmt.setString(9, v.getImageUrl2());
				stmt.setString(10, v.getImageUrl3());
				stmt.setString(11, v.getImageUrl4());
				stmt.setString(12, v.getImageUrl5());
				stmt.setString(13, v.getImageUrl6());
				stmt.setString(14, v.getImageUrl7());
			}
			if(v.getType()==5) {
				stmt.setString(9, v.getImageUrl2());
				stmt.setString(10, v.getImageUrl3());
				stmt.setString(11, v.getImageUrl4());
				stmt.setString(12, v.getImageUrl5());
				stmt.setString(13, "");
				stmt.setString(14, "");
			}
			
			stmt.setString(15, "");
			stmt.setInt(16, 1);
			stmt.setInt(17, v.getSaturday());
			stmt.setInt(18, v.getFree());
			stmt.setInt(19, v.getFreq());
			stmt.setInt(20, v.getLate());
			stmt.setString(21, v.getproCode());

			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("ERROR visits/ POST");
    		System.out.println(e.getMessage());
    	} finally {
			if(stmt != null) {
				try { stmt.close(); } catch (Exception ignore) {}
			}
			if(conn != null) {
				try { conn.close(); } catch (Exception ignore) {}
			}
		}
		return v;
	}
	
	@GET
	@Path("/notapproved")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastNotApprovedVisits(@QueryParam("apiKey") String key) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where approved=0";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(visit);
				
				while(rs.next()) {				
					if(rs.getInt("approved")==0) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
						
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/notapproved GET");
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
		return visitList;
	}

	@GET
	@Path("/approved")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastApprovedVisits(@QueryParam("apiKey") String key) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where approved=1";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(visit);
				
				while(rs.next()) {
					if(rs.getInt("approved")==1) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
						
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR visits/approved GET");
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
		return visitList;
	}

	@GET
	@Path("/patient/{pid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastVisitsForPatient(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where pid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, pid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Visit v = new Visit();

					v.setVid(rs.getInt("vid"));
					v.setPid(rs.getInt("pid"));
					v.setTid(rs.getInt("tid"));
					v.setVDate(rs.getDate("vdate"));
					v.setStartTime(rs.getTime("vstime"));
					v.setEndTime(rs.getTime("vetime"));
					v.setApproved(rs.getInt("approved"));
					v.setBilled(rs.getInt("billed"));
					v.setType(rs.getInt("type"));
					v.setImageUrl1(rs.getString("image_url_1"));
					if(v.getType()==0 || v.getType()==5) {
						v.setImageUrl2(rs.getString("image_url_2"));
						v.setImageUrl3(" ");
						v.setImageUrl4(" ");
					}
					if(v.getType()==1 || v.getType()==3) {
						v.setImageUrl2(" ");
						v.setImageUrl3(" ");
						v.setImageUrl4(" ");
					}
					if(v.getType()==2) {
						v.setImageUrl2(rs.getString("image_url_2"));
						v.setImageUrl3(rs.getString("image_url_3"));
						v.setImageUrl4(rs.getString("image_url_4"));
					}
					v.setSuperId(rs.getInt("superid"));
					v.setFree(rs.getInt("free"));
					v.setSaturday(rs.getInt("saturday"));

					visitList.add(v);
				}
			} catch(Exception e) {
				System.out.println("ERROR visits/patient/"+pid+" GET");
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
		return visitList;
	}

	@GET
	@Path("/therapist/{tid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastVisitsForTherapist(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Visit> visitList = new ArrayList<Visit>();

		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where tid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, tid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Visit v = new Visit();
					
					v.setVid(rs.getInt("vid"));
					v.setPid(rs.getInt("pid"));
					v.setTid(rs.getInt("tid"));
					v.setVDate(rs.getDate("vdate"));
					v.setStartTime(rs.getTime("vstime"));
					v.setEndTime(rs.getTime("vetime"));
					v.setApproved(rs.getInt("approved"));
					v.setBilled(rs.getInt("billed"));
					v.setType(rs.getInt("type"));
					v.setImageUrl1(rs.getString("image_url_1"));
					if(v.getType()==0 || v.getType()==5) {
						v.setImageUrl2(rs.getString("image_url_2"));
						v.setImageUrl3(" ");
						v.setImageUrl4(" ");
					}
					if(v.getType()==1 || v.getType()==3) {
						v.setImageUrl2(" ");
						v.setImageUrl3(" ");
						v.setImageUrl4(" ");
					}
					if(v.getType()==2) {
						v.setImageUrl2(rs.getString("image_url_2"));
						v.setImageUrl3(rs.getString("image_url_3"));
						v.setImageUrl4(rs.getString("image_url_4"));
					}
					v.setSuperId(rs.getInt("superid"));
					v.setFree(rs.getInt("free"));
					v.setSaturday(rs.getInt("saturday"));
					
					visitList.add(v);
				}
			} catch(Exception e) {
				System.out.println("ERROR visits/therapist/"+tid+" GET");
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
		return visitList;
	}
	
	@GET
	@Path("/patient/{pid}/notapproved")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastNotApprovedVisitsForPatient(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where pid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, pid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {				
					if(rs.getInt("approved")==0) {
						Visit v = new Visit();
						
						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
						
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR visits/patient/"+pid+"/notapproved GET");
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
		return visitList;
	}
	
	@GET
	@Path("/patient/{pid}/approved")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastApprovedVisitsForPatient(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where pid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, pid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("approved")==1) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
						
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/patient/"+pid+"/approved GET");
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
		return visitList;
	}

	@GET
	@Path("/therapist/{tid}/notapproved")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastNotApprovedVisitsForTherapist(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where tid=?";
	    	
			try {			
				conn = DBUtility.getConnectionPts();

				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, tid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {				
					if(rs.getInt("approved")==0) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));

						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/therapist/"+tid+"/notapproved GET");
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
		return visitList;
	}

	@GET
	@Path("/therapist/{tid}/approved")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastApprovedVisitsForTherapist(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where tid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, tid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("approved")==1) {
						Visit v = new Visit();
						
						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));

						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/therapist/"+tid+"/approved GET");
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
		return visitList;
	}

	@GET
	@Path("/patient/{pid}/notbilled")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastNotBilledVisitsForPatient(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where pid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, pid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					if(rs.getInt("approved")==1 && rs.getInt("billed")==0) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
					
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/patient/"+pid+"/notbilled GET");
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
		return visitList;
	}

	@GET
	@Path("/patient/{pid}/billed")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastBilledVisitsForPatient(@QueryParam("apiKey") String key, @PathParam("pid") int pid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where pid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, pid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("billed")==1) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
						
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/patient/"+pid+"/billed GET");
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
		return visitList;
	}
	
	@GET
	@Path("/therapist/{tid}/notbilled")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastNotBilledVisitsForTherapist(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where tid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, tid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("approved")==1 && rs.getInt("billed")==0) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));
						
						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/therapist/"+tid+"/notbilled GET");
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
		return visitList;
	}

	@GET
	@Path("/therapist/{tid}/billed")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visit> getPastBilledVisitsForTherapist(@QueryParam("apiKey") String key, @PathParam("tid") int tid) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where tid=?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setInt(1, tid);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					if(rs.getInt("billed")==1) {
						Visit v = new Visit();

						v.setVid(rs.getInt("vid"));
						v.setPid(rs.getInt("pid"));
						v.setTid(rs.getInt("tid"));
						v.setVDate(rs.getDate("vdate"));
						v.setStartTime(rs.getTime("vstime"));
						v.setEndTime(rs.getTime("vetime"));
						v.setApproved(rs.getInt("approved"));
						v.setBilled(rs.getInt("billed"));
						v.setType(rs.getInt("type"));
						v.setImageUrl1(rs.getString("image_url_1"));
						if(v.getType()==0 || v.getType()==5) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==1 || v.getType()==3) {
							v.setImageUrl2(" ");
							v.setImageUrl3(" ");
							v.setImageUrl4(" ");
						}
						if(v.getType()==2) {
							v.setImageUrl2(rs.getString("image_url_2"));
							v.setImageUrl3(rs.getString("image_url_3"));
							v.setImageUrl4(rs.getString("image_url_4"));
						}
						v.setSuperId(rs.getInt("superid"));
						v.setFree(rs.getInt("free"));
						v.setSaturday(rs.getInt("saturday"));

						visitList.add(v);
					}
				}
			} catch(Exception e) {
				System.out.println("ERROR /visits/therapist/"+tid+"/billed GET");
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
		return visitList;
	}
	
	@GET
	@Path("/{vdate}/")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visits> getPastVisitsForPatients(@QueryParam("apiKey") String key, @PathParam("vdate") String vdate) {
		List<Visits> visitLists = new ArrayList<Visits>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from visit where vdate >= ?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setString(1, vdate);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					
						Visits vs = new Visits();

						vs.setVid(rs.getInt("vid"));
						vs.setPid(rs.getInt("pid"));
						vs.setTid(rs.getInt("tid"));
						vs.setVDate(rs.getDate("vdate"));
						vs.setStartTime(rs.getTime("vstime"));
						vs.setEndTime(rs.getTime("vetime"));
						vs.setType(rs.getInt("type"));
						vs.setSuperId(rs.getInt("superid"));

						visitLists.add(vs);
					}
				
			} catch(Exception e) {
				System.out.println("ERROR /visits/therapist/"+vdate+"/billed GET");
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
		return visitLists;
	}
	
	@GET
	@Path("meeting/{vdate}/")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Visits> getPastVisitsForMeetings(@QueryParam("apiKey") String key, @PathParam("vdate") String vdate) {
		List<Visits> visitLists = new ArrayList<Visits>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
	    	ResultSet rs = null;

			String visit = "select * from meetingvisit where vdate >= ?";
	    	
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(visit);
				stmt.setString(1, vdate);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					
						Visits vs = new Visits();

						vs.setVid(rs.getInt("vid"));
						vs.setPid(rs.getInt("pid"));
						vs.setTid(rs.getInt("tid"));
						vs.setVDate(rs.getDate("vdate"));
						vs.setStartTime(rs.getTime("vstime"));
						vs.setEndTime(rs.getTime("vetime"));
						vs.setType(rs.getInt("type"));
						vs.setSuperId(rs.getInt("superid"));
						

						visitLists.add(vs);
					}
				
			} catch(Exception e) {
				System.out.println("ERROR /visits/therapist/"+vdate+"/billed GET");
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
		return visitLists;
	}
	
}