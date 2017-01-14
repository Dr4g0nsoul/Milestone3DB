package net.milestone3db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class Utility {
	public static boolean search(String s, String table){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCConnector.getInstance();
			stmt = con.createStatement();
			rs = stmt.executeQuery(s);
			while(rs.next()){
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
		System.out.println();
		} catch (SQLException e) {
			System.out.println("SQLException in Utility.search");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {;}
		}
		return false;
	}
	
	public static boolean insert (String q) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = JDBCConnector.getInstance();
			stmt = con.createStatement();
			int addedRows = stmt.executeUpdate(q);
			System.out.println(addedRows+" rows changed");
			System.out.println(q);
		} catch (SQLException e) {
		System.out.println("SQLException in Utility.search");
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {;}
		}
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
	
	public static CustomTableModel dbtabletotable(String table, String filter){
		CustomTableModel ret = new CustomTableModel();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCConnector.getInstance();
			stmt = con.createStatement();
			if(filter != null && filter.length() > 0){
				rs = stmt.executeQuery("SELECT * FROM "+table+" "+filter);
			}else
				rs = stmt.executeQuery("SELECT * FROM "+table);
			
			//Prepare stuff for the TableModel
			ResultSetMetaData rsmd = rs.getMetaData();
			int colc = rsmd.getColumnCount();
			Vector<Vector<?>> rows = new Vector<>();
			Vector<String> columns = new Vector<>();
			Vector<Object> fields = new Vector<>();
			
			//Get column names and prepare them for the TableModel
			for(int i = 1; i <= colc; i++){
				columns.add(rsmd.getColumnTypeName(i));
			}
			
			//Get data and prepare it for the TableModel
			while(rs.next()){
				fields = new Vector<>();
				for(int i = 1; i <= colc; i++){
					fields.add(rs.getString(i));
				}
				rows.add(fields);
			}
			
			//Add data to TableModel
			ret.setDataVector(rows, columns);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
			try{con.close();}catch(Exception e){}
		}
		return ret;
	}
	
}
