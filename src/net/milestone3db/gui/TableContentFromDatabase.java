package net.milestone3db.gui;

import java.awt.*;

//import java.sql.*;
//import java.util.*;
import javax.swing.*;

import java.awt.event.*;
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
        table.addMouseListener(new MouseListener() {
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
						editLabel.addMouseListener(new MouseListener() {
							@Override
							public void mouseReleased(MouseEvent e) {
							}
							@Override
							public void mousePressed(MouseEvent e) {
							}
							@Override
							public void mouseExited(MouseEvent e) {
							}
							@Override
							public void mouseEntered(MouseEvent e) {
							}
							@Override
							public void mouseClicked(MouseEvent e) {
								rightFrame.dispose();
								StringBuilder header = new StringBuilder();
								StringBuilder data = new StringBuilder();
								Point p = MouseInfo.getPointerInfo().getLocation();
								
								//Sort the selected lines such that they are conform to the underlying TableModel
								int[] lines = table.getSelectedRows();
								for (int i = 0; i < lines.length; i++) {
									lines[i] = table.convertRowIndexToModel(lines[i]);
								}
								Arrays.sort(lines);
								/*
								 * Add data of selected lines (should only be one with our
								 * configuration) to "data"
								 */
								for(int i=lines.length-1; i>=0; i--) {
									for(int j = 0; j < table.getColumnCount(); j++){
										data.append(table.getValueAt(lines[i], j)+",");
									}
								}
								
								//Add column names to header
								for(int i = 0; i < table.getColumnCount(); i++){
									header.append(table.getColumnName(i)+",");
								}
								EditFrame textFrame = new EditFrame(header.toString(), data.toString(), table.getColumnCount());
								textFrame.setBounds(p.x, p.y, textFrame.getWidth(), textFrame.getHeight());
								textFrame.setVisible(true);
							}
						});
						JLabel insertLabel = new JLabel("Insert");
						insertLabel.setFont(new Font(insertLabel.getName(), Font.PLAIN, 11));
						insertLabel.addMouseListener(new MouseListener() {
							@Override
							public void mouseReleased(MouseEvent e) {
							}
							@Override
							public void mousePressed(MouseEvent e) {
							}
							@Override
							public void mouseExited(MouseEvent e) {
							}
							@Override
							public void mouseEntered(MouseEvent e) {
							}
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
								InsertFrame textFrame = new InsertFrame(header.toString(), header.toString(), table.getColumnCount());
								textFrame.setBounds(p.x, p.y, textFrame.getWidth(), textFrame.getHeight());
								textFrame.setVisible(true);
							}
						});
						JLabel deleteLabel = new JLabel("Delete");
						deleteLabel.setFont(new Font(deleteLabel.getName(), Font.PLAIN, 11));
						deleteLabel.addMouseListener(new MouseListener() {
							@Override
							public void mouseReleased(MouseEvent e) {
							}
							@Override
							public void mousePressed(MouseEvent e) {
							}
							@Override
							public void mouseExited(MouseEvent e) {
							}
							@Override
							public void mouseEntered(MouseEvent e) {
							}
							@Override
							public void mouseClicked(MouseEvent e) {
								rightFrame.dispose();
								StringBuilder header = new StringBuilder();
								StringBuilder data = new StringBuilder();
								Point p = MouseInfo.getPointerInfo().getLocation();
								
								//Sort the selected lines such that they are conform to the underlying TableModel
								int[] lines = table.getSelectedRows();
								for (int i = 0; i < lines.length; i++) {
									lines[i] = table.convertRowIndexToModel(lines[i]);
								}
								Arrays.sort(lines);
								/*
								 * Add data of selected lines (should only be one with our
								 * configuration) to "data"
								 */
								for(int i=lines.length-1; i>=0; i--) {
									for(int j = 0; j < table.getColumnCount(); j++){
										data.append(table.getValueAt(lines[i], j)+",");
									}
								}
								
								//Add column names to header
								for(int i = 0; i < table.getColumnCount(); i++){
									header.append(table.getColumnName(i)+",");
								}
								Delete delete = new Delete(header.toString(), data.toString(), table.getColumnCount());
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
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});	
    }
    
	private class EditFrame extends JDialog {
		public EditFrame(String header, String data, int length) {
			setTitle("EditFrame");
			JPanel mainPanel = new JPanel();
			// TODO listener for the buttons
			JButton save = new JButton("Save");
			JButton cancel = new JButton("Cancel");

			mainPanel.setLayout(new GridLayout(length + 1, 2));
			String headerString[];
			headerString = header.split(",");

			String dataString[];
			dataString = data.split(",");

			JLabel[] labels = new JLabel[length];
			JTextField[] fields = new JTextField[length];
			for (int i = 0; i < length; i++) {
				labels[i] = new JLabel(headerString[i]);
				fields[i] = new JTextField(dataString[i]);
			}

			for (int i = 0; i < length; i++) {
				mainPanel.add(labels[i]);
				mainPanel.add(fields[i]);
			}

			mainPanel.add(save);
			mainPanel.add(cancel);

			getContentPane().add(mainPanel);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});
			setMinimumSize(new Dimension(500, 130));
			setSize(500, 70 * length);
		}
	}
	private class InsertFrame extends JDialog {
		public InsertFrame(String header, String data, int length) {
			setTitle("InsertFrame");
			JPanel mainPanel = new JPanel();
			// TODO listener for the buttons
			JButton insert = new JButton("Insert");
			JButton cancel = new JButton("Cancel");

			mainPanel.setLayout(new GridLayout(length + 1, 2));
			String headerString[];
			headerString = header.split(",");

			String dataString[];
			dataString = data.split(",");

			JLabel[] labels = new JLabel[length];
			JTextField[] fields = new JTextField[length];
			for (int i = 0; i < length; i++) {
				labels[i] = new JLabel(headerString[i]);
				fields[i] = new JTextField(dataString[i]);
			}

			for (int i = 0; i < length; i++) {
				mainPanel.add(labels[i]);
				mainPanel.add(fields[i]);
			}

			mainPanel.add(insert);
			mainPanel.add(cancel);

			getContentPane().add(mainPanel);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});
			setMinimumSize(new Dimension(500, 130));
			setSize(500, 70 * length);
		}
	}
	private class Delete {
		public Delete(String header, String data, int length) {
			System.out.println("Delete: " + tablenameTMP);
			//todo:  delete table where header = data
		}
	}
}
