package net.milestone3db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utility {
	public static boolean search(String s, String table) throws SQLException{
		Connection con = JDBCConnector.getInstance();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		while(rs.next()){
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
		}
		System.out.println();
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
	
	public static ArrayList<String> getTableNames(){
		ArrayList<String> ret = new ArrayList<>();
		try{
		Connection con = JDBCConnector.getInstance();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT table_name"
				+ " FROM information_schema.tables"
				+ " WHERE table_schema='public'"
				+ " AND table_type='BASE TABLE';");
		while(rs.next()){
			ret.add(rs.getString(1));
		}
		} catch (SQLException e) {;}
		return ret;
	}
	
}
