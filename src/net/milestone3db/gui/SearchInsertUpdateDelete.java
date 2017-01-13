package net.milestone3db.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SearchInsertUpdateDelete extends JPanel {
	public static JPanel leftPanel;
	public static JPanel rightPanel;
	
	public static JButton searchButton;
	public static JButton insertButton;
	public static JButton updateButton;
	public static JButton deleteButton;
	
	public SearchInsertUpdateDelete(String tablename) {
		setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(1200, 300));
    	//setBackground(Color.red);
    	
    	leftPanel = new JPanel();
    	leftPanel.setPreferredSize(new Dimension(850, 300));
    	leftPanel.setBackground(Color.green);
		leftPanel.setLayout(new GridLayout());
		
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(350, 300));
		rightPanel.setBackground(Color.blue);
		rightPanel.setLayout(new GridLayout(4, 1));
    	
    	//create field for search, insert, update, delete
    	//for each column name 1 label(name) + textfield 
		//
		//todo
		//
		
		searchButton = new JButton("Search");
		insertButton = new JButton("Insert");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		
		rightPanel.add(searchButton);
		rightPanel.add(insertButton);
		rightPanel.add(updateButton);
		rightPanel.add(deleteButton);
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		
		revalidate();
		repaint();
		setVisible(true);
	}
}
