package net.milestone3db.gui;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

import net.milestone3db.jdbc.JDBCConnector;
import net.milestone3db.jdbc.Utility;

public class InsertUpdateDialog extends JDialog{
	ArrayList<String> names = null;
	ArrayList<Integer> types = null;
	
	public ArrayList<ItemPanel> items = null;
	
	/**
	 * A dialog that manages inserting and updating data
	 * @param tableName name of the table to work with
	 * @param data ArrayList of strings that has the data in it
	 * @param types ArrayList of strings that has the data-types in it (number,string,default)
	 * @param inserting true if you want to insert, false if you want to update
	 */
	public InsertUpdateDialog(String tableName, ArrayList<String> data, boolean inserting) {
		
		setBounds(20, 20, 800, 500);
		
		JLabel lblInsertData = new JLabel("Insert data");
		lblInsertData.setFont(new Font("Tahoma", Font.PLAIN, 22));
		if(!inserting)
			lblInsertData.setText("Update data");
		lblInsertData.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblInsertData, BorderLayout.NORTH);
		
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		
		//START CREATING panelContent
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCConnector.getInstance();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from "+tableName);
			int numberOfColumns = rs.getMetaData().getColumnCount();
			names = new ArrayList<>();
			types = new ArrayList<>();
			System.out.println("InsertUpdateDialog: "+tableName);
			System.out.println("Varchar = "+Types.VARCHAR);
			for(int i = 0; i<numberOfColumns; i++) {
				names.add(rs.getMetaData().getColumnLabel(i+1));
				types.add(rs.getMetaData().getColumnType(i+1));
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
			items = new ArrayList<>();
			for(int i = 0; i<names.size(); i++) {
				ItemPanel currentIPanel = new ItemPanel(names.get(i), data.get(i));
				items.add(currentIPanel);
				panelContent.add(currentIPanel);
			}
		}
		getContentPane().add(panelContent, BorderLayout.CENTER);
		//END CREATING panelContent
		
		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		//TODO: Add listener for insert and update
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inserting) {
					String insertString = "insert into "+tableName+" values (";
					for(int i = 0;i<items.size();i++) {
						if(types.get(i)==Types.VARCHAR)
							insertString+="'"+items.get(i).getItemValue()+"',";
						else
							insertString+=items.get(i).getItemValue()+",";
					}
					insertString+="\b";
					insertString+=");";
					Utility.insert(insertString);
				} else {
					String updateString = "update "+tableName+" set ";
					for(int i = 0;i<items.size();i++) {
						if(types.get(i)==Types.VARCHAR)
							updateString+=names.get(i)+"='"+items.get(i).getItemValue()+"',";
						else
							updateString+=names.get(i)+"="+items.get(i).getItemValue()+",";
					}
					updateString+="\b";
					//------------Not sure if it is right
					updateString+="where "+names.get(0)+"="+data.get(0);
					//------------
					Utility.update(updateString);
				}
				setVisible(false);
				dispose();
				
			}
		});
		
		
		panelButtons.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panelButtons.add(btnCancel);
		
		setModal(true);
		setVisible(true);
	}
	
	private class ItemPanel extends JPanel
	{
		private String itemName;
		private String itemValue;
		
		public ItemPanel(String name, String value) {
			setLayout(new FlowLayout());
			
			add(new JLabel(name+": "));
			add(new TextField(value));
			itemName = name;
			itemValue = value;
			
			setVisible(true);
		}
		
		public String getItemName() {
			return itemName;
		}
		
		public String getItemValue() {
			return itemValue;
		}
	}
}
