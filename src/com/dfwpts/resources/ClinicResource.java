package com.dfwpts.resources;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dfwpts.DBUtility;
import com.dfwpts.model.Clinic;

@Singleton
@Path("/clinics")
public class ClinicResource {
	

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Clinic> getAllDoctors(@QueryParam("apiKey") String key) {
		
		List<Clinic> cList = new ArrayList<Clinic>();

		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			String stmts = "select * from marketing_list order by name";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(stmts);

				while(rs.next()) {
					Clinic c = new Clinic();
					
					c.setMLID(rs.getInt("mlid"));
					c.setName(rs.getString("name"));
					c.setAddress(rs.getString("address_line"));
					c.setCity(rs.getString("city"));
					c.setZip(rs.getString("zip"));
					
					if(rs.getString("phone") != null) {
						c.setPhone(rs.getString("phone"));
					}
					if(rs.getString("email") != null) {
						c.setEmail(rs.getString("email"));
					}
					if(rs.getString("fax") != null) {
						c.setFax(rs.getString("fax"));
					}
					if(rs.getString("website") != null) {
						c.setWebsite(rs.getString("website"));
					}
					
					String contact1 = "", contact2 = "";
					if(rs.getString("contact_fname") != null) {
						contact1 = rs.getString("contact_fname");
					}
					if(rs.getString("contact_lname") != null) {
						contact1 += " " + rs.getString("contact_lname");
					}
					if(rs.getString("contact_fname1") != null) {
						contact2 = rs.getString("contact_fname1");
					}
					if(rs.getString("contact_lname1") != null) {
						contact2 += " " + rs.getString("contact_lname1");
					}
					c.setContact1(contact1);
					c.setContact2(contact2);
					
					if(rs.getString("title") != null) {
						c.setTitle1(rs.getString("title"));
					}
					if(rs.getString("title1") != null) {
						c.setTitle2(rs.getString("title1"));
					}
					
					cList.add(c);					
				}
				
			} catch(Exception e) {
	    		System.out.println("ERROR: clinics/ GET");
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
		
		return cList;
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Clinic getDoctorUsingID(@QueryParam("apiKey") String key, @PathParam("id") int id) {
		Clinic c = new Clinic();
		
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String stmts = "select * from marketing_list where mlid=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(stmts);
				
				stmt.setInt(1, id);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					c.setMLID(rs.getInt("mlid"));
					c.setName(rs.getString("name"));
					c.setAddress(rs.getString("address_line"));
					c.setCity(rs.getString("city"));
					c.setZip(rs.getString("zip"));
					
					if(rs.getString("phone") != null) {
						c.setPhone(rs.getString("phone"));
					}
					if(rs.getString("email") != null) {
						c.setEmail(rs.getString("email"));
					}
					if(rs.getString("fax") != null) {
						c.setFax(rs.getString("fax"));
					}
					if(rs.getString("website") != null) {
						c.setWebsite(rs.getString("website"));
					}
					
					String contact1 = "", contact2 = "";
					if(rs.getString("contact_fname") != null) {
						contact1 = rs.getString("contact_fname");
					}
					if(rs.getString("contact_lname") != null) {
						contact1 += " " + rs.getString("contact_lname");
					}
					if(rs.getString("contact_fname1") != null) {
						contact2 = rs.getString("contact_fname1");
					}
					if(rs.getString("contact_lname1") != null) {
						contact2 += " " + rs.getString("contact_lname1");
					}
					c.setContact1(contact1);
					c.setContact2(contact2);
					
					if(rs.getString("title") != null) {
						c.setTitle1(rs.getString("title"));
					}
					if(rs.getString("title1") != null) {
						c.setTitle2(rs.getString("title1"));
					}
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: clinics/"+id+" GET");
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
		return c;
	}	
}
