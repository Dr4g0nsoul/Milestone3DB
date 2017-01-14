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
	
    public JTable getTable() {
		return table;
	}

	public TableContentFromDatabase(String tablename)
    {
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
        
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
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
					TextFrame textFrame = new TextFrame(header.toString(), data.toString(), table.getColumnCount());
					textFrame.setBounds(p.x, p.y, textFrame.getWidth(), textFrame.getHeight());
					textFrame.setVisible(true);
				}
			}
		});
    }
    
	private class TextFrame extends JDialog {
		public TextFrame(String header, String data, int length) {
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
}
