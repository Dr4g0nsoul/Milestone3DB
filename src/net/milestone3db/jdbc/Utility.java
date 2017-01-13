package net.milestone3db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utility {
	public static boolean search(String s, String table) throws SQLException{
		Connection con = JDBCConnector.getInstance();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		System.out.println(s);
		return false;
	}
	
	public static boolean insert (String q) throws SQLException{
		Connection con = JDBCConnector.getInstance();
		Statement stmt = con.createStatement();
		int addedRows = stmt.executeUpdate(q);
		System.out.println(q);
		return false;
	}
	
	public static boolean delete (String q){
		System.out.println(q);
		return false;
	}
	
	public static boolean update (String q){
		System.out.println(q);
		return false;
	}
	
}
