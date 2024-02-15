package com.dfwpts.resources;

import com.dfwpts.DBUtility;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import javax.inject.Singleton;

@Singleton
@Path("/constants")
public class ConstantResource {
	String withRootUrl = "jdbc:google:mysql://preferred-database:ptsdb/ptsdb?user=root";

	@GET
	@Path("/MRNum")
	public int getMRNum(@QueryParam("apiKey") String key) {
		int MRNum = 0;
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String mr = "select field_value from constants where form_num=? and field_name=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(mr);
				
				stmt.setInt(1, 9);
				stmt.setString(2, "MRNum");
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					String MRNumber = rs.getString("field_value");
					MRNum = Integer.parseInt(MRNumber);
				}
			} catch(Exception e) {
	    		System.out.println("ERROR: constants/MRNum GET");
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
		return MRNum;
	}

	@PUT
	@Path("/MRNum")
	public void setMRNum(@QueryParam("apiKey") String key, int mrnum) {
		if(key.equals("j8DP61HkQLLZD9puLNGJ")) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			String mr = "update constants set field_value=? where form_num=? and field_name=?";
			
			try {
				conn = DBUtility.getConnectionPts();
				
				stmt = conn.prepareStatement(mr);
				
				stmt.setInt(1, mrnum);
				stmt.setInt(2, 9);
				stmt.setString(3, "MRNum");
				
				stmt.executeUpdate();
			} catch(Exception e) {
	    		System.out.println("ERROR: constants/MRNum GET");
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
}
