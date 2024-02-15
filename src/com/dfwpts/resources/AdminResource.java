package com.dfwpts.resources;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Admin;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.DriverManager;
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
@Path("/admin")
public class AdminResource {

	String withRootUrl = "jdbc:google:mysql://preferred-database:ptsdb/ptsdb?user=root";

	@GET
	@Path("/{aid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Admin getAdminById(@QueryParam("apiKey") String key, @PathParam("aid") int id) {
		Admin a = new Admin();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, stmtEmail = null;
	    	ResultSet rs = null, rsEmail = null;

	    	String adminById = "select * from admin where aid=?";
	    	String adminEmail = "select * from users where username=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(adminById);
				stmtEmail = conn.prepareStatement(adminEmail);
				
				a.setAid(id);
				stmt.setInt(1, id);

				rs = stmt.executeQuery();
				
				if(rs.next()) {
					stmtEmail.setString(1, rs.getString("username"));
					a.setUsername(rs.getString("username"));
					a.setFirstName(rs.getString("fname"));
					a.setLastName(rs.getString("lname"));
					a.setSex(rs.getString("sex"));
					a.setBirthDate(rs.getDate("birth"));
					a.setAddress(rs.getString("address_line1"));
					a.setCity(rs.getString("city"));
					a.setState(rs.getString("state"));
					a.setZip(rs.getString("zip"));
					a.setPhone(rs.getString("phone"));
					a.setSsn(rs.getString("ssn"));
				}
				rsEmail = stmtEmail.executeQuery();
				
				if(rsEmail.next()) {
					a.setEmail(rsEmail.getString("email"));
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /admin/"+id+" GET");
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
		return a;
	}

	@GET
	@Path("/username/{username}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Admin getAdminByUsername(@QueryParam("apiKey") String key, @PathParam("username") String username) {
		Admin a = new Admin();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null, stmtEmail = null;
	    	ResultSet rs = null, rsEmail = null;

	    	String adminByUsername = "select * from admin where username=?";
	    	String adminEmail = "select * from users where username=?";
	    	
	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(adminByUsername);
				stmtEmail = conn.prepareStatement(adminEmail);
				
				a.setUsername(username);
				stmt.setString(1, username);
				stmtEmail.setString(1, username);
				rsEmail = stmtEmail.executeQuery();
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					a.setAid(rs.getInt("aid"));
					a.setFirstName(rs.getString("fname"));
					a.setLastName(rs.getString("lname"));
					a.setSex(rs.getString("sex"));
					a.setBirthDate(rs.getDate("birth"));
					a.setAddress(rs.getString("address_line1"));
					a.setCity(rs.getString("city"));
					a.setState(rs.getString("state"));
					a.setZip(rs.getString("zip"));
					a.setPhone(rs.getString("phone"));
					a.setSsn(rs.getString("ssn"));
				}
				
				if(rsEmail.next()) {
					a.setEmail(rsEmail.getString("email"));
					a.setPassword(rsEmail.getString("password"));
				}
	    	} catch(Exception e) {
				System.out.println("ERROR /admin/username/"+username+" GET");
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
		return a;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addAdmin(Admin a) {
		Connection conn = null;
		PreparedStatement stmt = null;
    	ResultSet rs = null;

    	String insertAdmin = "insert into admin (username, fname, lname, sex, birth, address_line1, city, state, zip, phone, ssn) values (?,?,?,?,?,?,?,?,?,?,?)";
    	
    	try {
			conn = DBUtility.getConnectionPts();
			stmt = conn.prepareStatement(insertAdmin);

			stmt.setString(1, a.getUsername());
			stmt.setString(2, a.getFirstName());
			stmt.setString(3, a.getLastName());
			stmt.setString(4, a.getSex());
			stmt.setDate(5, (Date) a.getBirthDate());
			stmt.setString(6, a.getAddress());
			stmt.setString(7, a.getCity());
			stmt.setString(8, a.getState());
			stmt.setString(9, a.getZip());
			stmt.setString(10, a.getPhone());
			stmt.setString(11, a.getSsn());
			
			stmt.executeUpdate();
			
    	} catch(Exception e) {
			System.out.println("ERROR admin/ POST");			
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
	@Path("/{aid}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void updateAdmin(@PathParam("aid") int aid, Admin a) {
		if(a.getAid() == aid) {
			Connection conn = null;
			PreparedStatement stmt = null, emailStmt = null;
	    	ResultSet rs = null;

	    	String updateAdmin = "UPDATE admin SET address_line1=?, city=?, zip=?, phone=? WHERE aid=?";
	    	String updateEmail = "UPDATE users SET email=? where username=?";

	    	try {
				conn = DBUtility.getConnectionPts();
				stmt = conn.prepareStatement(updateAdmin);
				
				stmt.setString(1, a.getAddress());
				stmt.setString(2, a.getCity());
				stmt.setString(3, a.getZip());
				stmt.setString(4, a.getPhone());
				stmt.setInt(5, aid);
								
				stmt.executeUpdate();
				
				emailStmt = conn.prepareStatement(updateEmail);
				
				emailStmt.setString(1, a.getEmail());
				emailStmt.setString(2, a.getUsername());
				
				emailStmt.executeUpdate();
				
	    	} catch(Exception e) {
				System.out.println("ERROR /admin/"+aid+" PUT");
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
}