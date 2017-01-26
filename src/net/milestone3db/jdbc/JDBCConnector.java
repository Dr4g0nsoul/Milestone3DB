package net.milestone3db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
	
	private static String url = "jdbc:postgresql://localhost:5432/Milestone3DB";
	private static String user = "postgres";
	private static String password = "masterkey";
	private static String originalurl = "jdbc:postgresql://localhost:5432/";
	private static Connection connection = null;
	
	private JDBCConnector() {
		
	}
	
	public static Connection getInstance() throws SQLException  {
		if(connection==null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}
	
	public static void setPassword(String password) {
		JDBCConnector.password = password;
	}
	
	public static void setUser(String user) {
		JDBCConnector.user = user;
	}
	
	public static void setName(String name) {
		JDBCConnector.url = JDBCConnector.originalurl + name;
	}
}
