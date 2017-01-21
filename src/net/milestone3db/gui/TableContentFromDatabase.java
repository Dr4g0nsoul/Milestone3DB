package net.milestone3db.gui;

import java.awt.*;

//import java.sql.*;
//import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


//import net.milestone3db.jdbc.CustomTableModel;
//import net.milestone3db.jdbc.JDBCConnector;
import net.milestone3db.jdbc.Utility;

@SuppressWarnings("serial")
public class TableContentFromDatabase extends JPanel
{
	private JTable table;
	private boolean check = true;
	private int x = 0;
	private int y = 0;
	private String tablenameTMP;
	
    public JTable getTable() {
		return table;
	}
    //table(edit update etc) buggy after searchrequest.
	public TableContentFromDatabase(String tablename)
    {
		tablenameTMP = tablename;
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(1200, 700));
    	setBackground(Color.red);
    	setBorder(BorderFactory.createMatteBorder(0,1,1,1, Color.black));
    		
        //  Create table with database data  
        table = new JTable(Utility.dbtabletotable(tablename, ""));
        //Settings for the table and create the RowSorter
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

        add( new JScrollPane( table ), BorderLayout.CENTER );
        
        //Listener
        //click table to choose elements then right click to get menu
        table.addMouseListener(new MouseAdapter() {
			JFrame rightFrame;
			@Override
			public void mouseReleased(MouseEvent event) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				if(SwingUtilities.isRightMouseButton(event)){
					if (check == false) {
						rightFrame.dispose();
						check = true;
					} else {
						check = false;
						rightFrame = new JFrame();
						rightFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						rightFrame.setUndecorated(true);
						
						x= p.x;
						y= p.y;
						
						rightFrame.setLocation(x, y);
						rightFrame.setPreferredSize(new Dimension(60, 113));
						
						JPanel mainPanel = new JPanel();
						mainPanel.setLayout(new FlowLayout());
						mainPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
						
						JLabel titleLabel = new JLabel("Edit");
						titleLabel.setFont(new Font(titleLabel.getName(), Font.BOLD, 16));
						titleLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
						
						JLabel editLabel = new JLabel("Edit/Update");
						editLabel.setFont(new Font(editLabel.getName(), Font.PLAIN, 11));
						
						JLabel insertLabel = new JLabel("Insert");
						insertLabel.setFont(new Font(insertLabel.getName(), Font.PLAIN, 11));
						
						JLabel deleteLabel = new JLabel("Delete");
						deleteLabel.setFont(new Font(deleteLabel.getName(), Font.PLAIN, 11));
						
						//Listener
						
						//edit/update MouseListener
						editLabel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								rightFrame.dispose();
								if(table.getSelectedColumn() != -1){
									StringBuilder header = new StringBuilder();
									StringBuilder data = new StringBuilder();
									Point p = MouseInfo.getPointerInfo().getLocation();

									// Sort the selected lines such that they
									// are conform to the underlying TableModel
									int[] lines = table.getSelectedRows();
									for (int i = 0; i < lines.length; i++) {
										lines[i] = table.convertRowIndexToModel(lines[i]);
									}
									Arrays.sort(lines);
									/*
									 * Add data of selected lines (should only
									 * be one with our configuration) to "data"
									 */
									for (int i = lines.length - 1; i >= 0; i--) {
										for (int j = 0; j < table.getColumnCount(); j++) {
											data.append(table.getModel().getValueAt(lines[i], j) + ",");
										}
									}

									// Add column names to header
									for (int i = 0; i < table.getColumnCount(); i++) {
										header.append(table.getColumnName(i) + ",");
									}
									System.out.println(tablename);
									System.out.println(data);
									// Replacing editframe
									/*
									 * EditFrame textFrame = new
									 * EditFrame(header.toString(),
									 * data.toString(), table.getColumnCount());
									 * textFrame.setBounds(p.x, p.y,
									 * textFrame.getWidth(),
									 * textFrame.getHeight());
									 * textFrame.setVisible(true);
									 */

									ArrayList<String> dataList = new ArrayList<>(
											Arrays.asList(data.toString().split(",")));
									new InsertUpdateDialog(tablename, dataList, false, table);
									// End replacing editframe
								}else{
									JOptionPane.showMessageDialog(TableContentFromDatabase.this, "No column to edit selected", "Message", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						});
						
						//insert MouseListener
						insertLabel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								rightFrame.dispose();
								StringBuilder header = new StringBuilder();
								Point p = MouseInfo.getPointerInfo().getLocation();
								
								//Sort the selected lines such that they are conform to the underlying TableModel
								int[] lines = table.getSelectedRows();
								for (int i = 0; i < lines.length; i++) {
									lines[i] = table.convertRowIndexToModel(lines[i]);
								}
								Arrays.sort(lines);
								//Add column names to header
								for(int i = 0; i < table.getColumnCount(); i++){
									header.append(table.getColumnName(i)+",");
								}
								
								//Replacing InsertFrame
								/*
								InsertFrame textFrame = new InsertFrame(header.toString(), header.toString(), table.getColumnCount());
								textFrame.setBounds(p.x, p.y, textFrame.getWidth(), textFrame.getHeight());
								textFrame.setVisible(true);
								*/
								new InsertUpdateDialog(tablename, null, true, table);
								//End replacing InsertFrame
							}
						});
						
						//delete MouseListener
						deleteLabel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								rightFrame.dispose();
								if(table.getSelectedColumn() != -1){
									StringBuilder header = new StringBuilder();
									StringBuilder data = new StringBuilder();
									Point p = MouseInfo.getPointerInfo().getLocation();

									// Sort the selected lines such that they
									// are conform to the underlying TableModel
									int[] lines = table.getSelectedRows();
									for (int i = 0; i < lines.length; i++) {
										lines[i] = table.convertRowIndexToModel(lines[i]);
									}
									Arrays.sort(lines);
									/*
									 * Add data of selected lines (should only
									 * be one with our configuration) to "data"
									 */
									for (int i = lines.length - 1; i >= 0; i--) {
										for (int j = 0; j < table.getColumnCount(); j++) {
											data.append(table.getModel().getValueAt(lines[i], j) + ",");
										}
									}

									// Add column names to header
									for (int i = 0; i < table.getColumnCount(); i++) {
										header.append(table.getColumnName(i) + ",");
									}
									
									//Delete selected row from database and view
									if (Utility.insert("DELETE FROM " + tablename + " WHERE " + header.toString().split(",")[0] + "=" + data.toString().split(",")[0])) {
										for (int i = 0; i < lines.length; i++) {
											((DefaultTableModel) table.getModel()).removeRow(lines[i]);
										}
									}else{
										JOptionPane.showMessageDialog(TableContentFromDatabase.this, "Error during deletion of data", "Message", JOptionPane.INFORMATION_MESSAGE);
									}
								}else{
									JOptionPane.showMessageDialog(TableContentFromDatabase.this, "No column to delete selected", "Message", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						});
						
						mainPanel.add(titleLabel);
						mainPanel.add(editLabel);
						mainPanel.add(insertLabel);
						mainPanel.add(deleteLabel);
						
						rightFrame.getContentPane().add(mainPanel);
						rightFrame.pack();
						rightFrame.setVisible(true);
					}
				}
			}
		});	
    }
	
}
