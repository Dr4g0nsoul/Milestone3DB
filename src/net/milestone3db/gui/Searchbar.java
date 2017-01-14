package net.milestone3db.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class Searchbar extends JPanel {
	public static JTextField searchField;
	public Searchbar(String tablename) {
		setLayout(new FlowLayout());
    	setPreferredSize(new Dimension(1200, 70));
		//setBackground(Color.black);
		
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(700, 30));
		JButton searchButton = new JButton("Search");
		
		add(searchField);
		add(searchButton);
	}
}
