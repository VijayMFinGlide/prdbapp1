package com.dfwpts;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

public class DBUtility {
	private static final String withRootUrl = "jdbc:google:mysql://preferred-database:ptsdb/portaldb?user=root";
	private static final String withRootUrlPts = "jdbc:google:mysql://preferred-database:ptsdb/ptsdb?user=root";
	private static final String localUrl = "jdbc:mysql://173.194.83.155/portaldb";
	private static final String localUrlPts = "jdbc:mysql://173.194.83.155/ptsdb";
	private static final String user = "root";
	private static final String pass = "root@pts";
	
	public static final Connection getConnection() throws Exception {
		Connection conn = null;
		if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        conn = DriverManager.getConnection(withRootUrl);
		} else {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(localUrl, user, pass);
		}		
		return conn;
	}

	public static final Connection getConnectionPts() throws Exception {
		Connection conn = null;
		if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        conn = DriverManager.getConnection(withRootUrlPts);
		} else {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(localUrlPts, user, pass);
		}		
		return conn;
	}
}
