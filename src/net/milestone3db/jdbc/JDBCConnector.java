package net.milestone3db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
	
	private static final String url = "jdbc:postgresql://localhost:5432/steam";
	private static final String user = "postgres";
	private static final String password = "masterkey";
	private static Connection connection = null;
	
	private JDBCConnector() {
		
	}
	
	public static Connection getInstance() throws SQLException  {
		if(connection==null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}
}
