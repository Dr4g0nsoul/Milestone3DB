package net.milestone3db.gui;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

import net.milestone3db.jdbc.JDBCConnector;

public class InsertUpdateDialog extends JDialog{
	

	public InsertUpdateDialog(String tableName, ArrayList<String> data, boolean inserting) {
		
		JLabel lblInsertData = new JLabel("Insert data");
		lblInsertData.setFont(new Font("Tahoma", Font.PLAIN, 22));
		if(!inserting)
			lblInsertData.setText("Update data");
		lblInsertData.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblInsertData, BorderLayout.NORTH);
		
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		
		//START CREATING panelContent
		ArrayList<String> names = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCConnector.getInstance();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from "+tableName);
			int numberOfColumns = rs.getMetaData().getColumnCount();
			names = new ArrayList<>();
			for(int i = 0; i<numberOfColumns; i++) {
				names.add(rs.getMetaData().getColumnLabel(i+1));
			}
			
		}catch (SQLException e) {
			System.out.println("SQLError in InsertUpdateDialog");
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {;}
		}
		
		if(names!=null){
			for(int i = 0; i<names.size(); i++) {
				panelContent.add(new ItemPanel(names.get(i), data.get(i)));
			}
		}
		getContentPane().add(panelContent, BorderLayout.CENTER);
		//END CREATING panelContent
		
		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		//TODO: Add listener for insert and update
		
		
		
		panelButtons.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		
		setVisible(true);
	}
	
	private class ItemPanel extends JPanel
	{
		
		public ItemPanel(String name, String value) {
			setLayout(new FlowLayout());
			
			add(new JLabel(name+": "));
			add(new TextField(value));
			
			setVisible(true);
		}
	}
}
