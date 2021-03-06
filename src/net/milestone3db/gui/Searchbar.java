package net.milestone3db.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.milestone3db.jdbc.CustomTableModel;

@SuppressWarnings("serial")
public class Searchbar extends JPanel {
	public static JTextField searchField;
	private TableRowSorter<TableModel> rowSorter;
	
	public Searchbar(JTable table) {
		GroupLayout gpl = new GroupLayout(this);
		gpl.setAutoCreateContainerGaps(true);
		gpl.setAutoCreateGaps(true);
		
		searchField = new JTextField();
//		searchField.setPreferredSize(new Dimension(700, 30));
		JButton searchButton = new JButton("Search");
		JButton resetButton = new JButton("Reset");
		
		gpl.setHorizontalGroup(gpl.createSequentialGroup()
				.addComponent(searchField)
				.addComponent(searchButton)
				.addComponent(resetButton));
		gpl.setVerticalGroup(gpl.createSequentialGroup()
				.addGroup(gpl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(searchField)
				.addComponent(searchButton)
				.addComponent(resetButton)));

		add(searchField);
		add(searchButton);
		add(resetButton);
		
		rowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(rowSorter);
		
		setLayout(gpl);
//    	setPreferredSize(new Dimension(1200, 70));
        setBorder(BorderFactory.createMatteBorder(1,0,0,1, Color.black));
        //setBackground(Color.black);
        
		//Listener
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
				rowSorter.setRowFilter(null);
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchField.getText();
              if (text.trim().length() == 0) {
                  rowSorter.setRowFilter(null);
              } else {
                  rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
              }
			}
		});
	}
}
