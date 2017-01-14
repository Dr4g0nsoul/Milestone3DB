package net.milestone3db.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.milestone3db.jdbc.CustomTableModel;

@SuppressWarnings("serial")
public class Searchbar extends JPanel {
	public static JTextField searchField;
	private static TableRowSorter<CustomTableModel> rowSorter;
	public Searchbar(String tablename) {
		setLayout(new FlowLayout());
    	setPreferredSize(new Dimension(1200, 70));
		//setBackground(Color.black);
		
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(700, 30));
		JButton searchButton = new JButton("Search");
		
		add(searchField);
		add(searchButton);
		
		//rowSorter not working; todo ^								////////////
		//rowSorter = new TableRowSorter<CustomTableModel>();
		TableContentFromDatabase.table.setRowSorter(rowSorter);
		
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
