package com.dfwpts.resources;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Doctor;

import java.util.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/doctors")
public class DoctorResource {
	
		
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addDoctor(Doctor d) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String insertDoctor = "insert into doctor (fname,lname,npi_num,phone,address,city,zip,email,doc_group,fax_num) values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = DBUtility.getConnectionPts();

			stmt = conn.prepareStatement(insertDoctor);
			
			stmt.setString(1, d.getFName());
			stmt.setString(2, d.getLName());
			stmt.setString(3, d.getNPI());
			stmt.setString(4, d.getPhone());
			stmt.setString(5, d.getAddress());
			stmt.setString(6, d.getCity());
			stmt.setString(7, d.getZip());
			stmt.setString(8, d.getEmail());
			stmt.setString(9, d.getGroup());
			stmt.setString(10, d.getFax());
			
			stmt.executeUpdate();
		} catch(Exception e) {
    		System.out.println("ERROR: doctors/ POST");
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
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Doctor> getAllDoctors(@QueryParam("apiKey") String key) {
		List<Doctor> dList = new ArrayList<Doctor>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select * from doctor order by fname, lname";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(doctorStmt);
				
				while(rs.next()) {
					Doctor d = new Doctor();

					d.setDocID(rs.getInt("docid"));
					d.setFName(rs.getString("fname"));
					d.setLName(rs.getString("lname"));
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setGroup(rs.getString("doc_group"));
					d.setFax(rs.getString("fax_num"));
					
					dList.add(d);
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/ GET");
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
		return dList;
	}
	
	@GET
	@Path("/active")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Doctor> getActiveDoctors(@QueryParam("apiKey") String key) {
		List<Doctor> dLists = new ArrayList<Doctor>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select distinct  p.docid, d.fname,d.lname,d.address,d.city,d.zip,d.phone,d.fax_num,d.npi_num,d.doc_group,d.email from patient p join doctor d on p.docid = d.docid  union " + 
					"select  distinct r.docid, d.fname,d.lname,d.address,d.city,d.zip,d.phone,d.fax_num,d.npi_num,d.doc_group,d.email from referrals r join doctor d on r.docid = d.docid  ;";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(doctorStmt);
				
				while(rs.next()) {
					Doctor d = new Doctor();

					d.setDocID(rs.getInt("docid"));
					d.setFName(rs.getString("fname"));
					d.setLName(rs.getString("lname"));
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setGroup(rs.getString("doc_group"));
					d.setFax(rs.getString("fax_num"));
					
					dLists.add(d);
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/ GET");
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
		return dLists;
	}


	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Doctor getDoctorUsingID(@QueryParam("apiKey") String key, @PathParam("id") int id) {
		Doctor d = new Doctor();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select * from doctor where docid=?";

			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(doctorStmt);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {

					d.setDocID(id);
					d.setFName(rs.getString("fname"));
					d.setLName(rs.getString("lname"));
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setGroup(rs.getString("doc_group"));
					d.setFax(rs.getString("fax_num"));
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/"+id+" GET");
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
	@Path("/{fname}_{lname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Doctor getDoctorUsingNames(@QueryParam("apiKey") String key, @PathParam("fname") String fname, @PathParam("lname") String lname) {
		Doctor d = new Doctor();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select * from doctor where fname=? and lname=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(doctorStmt);
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				rs = stmt.executeQuery();
				
				if(rs.next()) {

					d.setDocID(rs.getInt("docid"));
					d.setFName(fname);
					d.setLName(lname);
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setFax(rs.getString("fax_num"));
					d.setGroup(rs.getString("doc_group"));
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/"+fname+"-"+lname+" PUT");
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
	@Path("/fname/{fname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Doctor> getDoctorUsingFirstName(@QueryParam("apiKey") String key, @PathParam("fname") String fname) {
		List<Doctor> list = new ArrayList<Doctor>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select * from doctor where fname=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(doctorStmt);
				stmt.setString(1, fname);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Doctor d = new Doctor();

					d.setDocID(rs.getInt("docid"));
					d.setFName(rs.getString("fname"));
					d.setLName(rs.getString("lname"));
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setFax(rs.getString("fax_num"));
					d.setGroup(rs.getString("doc_group"));
					
					list.add(d);
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/fname/"+fname+" PUT");
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
	@Path("/lname/{lname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Doctor> getDoctorUsingLastName(@QueryParam("apiKey") String key, @PathParam("lname") String lname) {
		List<Doctor> list = new ArrayList<Doctor>();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select * from doctor where lname=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(doctorStmt);
				stmt.setString(1, lname);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Doctor d = new Doctor();

					d.setDocID(rs.getInt("docid"));
					d.setFName(rs.getString("fname"));
					d.setLName(rs.getString("lname"));
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setFax(rs.getString("fax_num"));
					d.setGroup(rs.getString("doc_group"));
					
					list.add(d);
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/lname/"+lname+" PUT");
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
	@Path("/npi/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Doctor getDoctorUsingNPI(@QueryParam("apiKey") String key, @PathParam("id") int id) {
		Doctor d = new Doctor();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String doctorStmt = "select * from doctor where npi_num=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(doctorStmt);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {

					d.setDocID(rs.getInt("docid"));
					d.setFName(rs.getString("fname"));
					d.setLName(rs.getString("lname"));
					d.setNPI(rs.getString("npi_num"));
					d.setPhone(rs.getString("phone"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));
					d.setZip(rs.getString("zip"));
					d.setEmail(rs.getString("email"));
					d.setGroup(rs.getString("doc_group"));
					d.setFax(rs.getString("fax_num"));
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: doctors/npi/"+id+" PUT");
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

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updateDoctor(@PathParam("id") int id, Doctor d) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String updateDoc = "update doctor set address=?, city=?, zip=?, phone=?, email=?, fax_num=? where docid=?";
		
		try {
			conn = DBUtility.getConnectionPts();
			
			stmt = conn.prepareStatement(updateDoc);
			
			stmt.setString(1, d.getAddress());
			stmt.setString(2, d.getCity());
			stmt.setString(3, d.getZip());
			stmt.setString(4, d.getPhone());
			stmt.setString(5, d.getEmail());
			stmt.setString(6, d.getFax());
			stmt.setInt(7, id);
			
			stmt.executeUpdate();
			
		} catch(Exception e) {
    		System.out.println("ERROR: doctors/"+id+" PUT");
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