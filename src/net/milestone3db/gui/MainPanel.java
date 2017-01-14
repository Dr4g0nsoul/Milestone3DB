package net.milestone3db.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.milestone3db.jdbc.Utility;

@SuppressWarnings("serial")
public class MainPanel extends JFrame{
	public static JPanel mainPanel;
	public static JPanel listPanel;
	public static JPanel contentPanel;
	public static JPanel righttopPanel;
	public static JPanel rightbottomPanel;
	
	public MainPanel()  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50, 50);
		setPreferredSize(new Dimension(1600, 800));
		setTitle("ProjectDBMilestone3");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		//listPanel.setBackground(Color.red);
		listPanel.setPreferredSize(new Dimension(400, 800));
		listPanel.add(new JLabel("DB-Tables"));
		for(String tableName : Utility.getTableNames()) {
			JLabel currentLabel = new JLabel(tableName);
			currentLabel.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					changeContentPanel(((JLabel)e.getSource()).getText(),(JLabel)e.getSource());
				}
			});
			listPanel.add(currentLabel);
		}
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		righttopPanel = new JPanel();
		righttopPanel.setLayout(new FlowLayout());
		righttopPanel.setBackground(Color.blue);
		righttopPanel.setPreferredSize(new Dimension(1200, 100));
		rightbottomPanel = new JPanel();
		rightbottomPanel.setLayout(new BorderLayout());
		rightbottomPanel.setBackground(Color.green);
		rightbottomPanel.setPreferredSize(new Dimension(1200, 700));
				
		contentPanel.add(righttopPanel, BorderLayout.NORTH);
		contentPanel.add(rightbottomPanel, BorderLayout.SOUTH);
		
		
		mainPanel.add(listPanel, BorderLayout.WEST);
		mainPanel.add(contentPanel, BorderLayout.EAST);
		getContentPane().add(mainPanel);
		
		setResizable(false);
		setVisible(true);
		pack();
	}
	
	public void changeContentPanel(String tableName, JLabel selected) {
		
		for(int i = 0; i<listPanel.getComponentCount(); i++) {
			if(((JLabel)listPanel.getComponent(i))==selected) {
				selected.setForeground(Color.pink);
			} else
				((JLabel)listPanel.getComponent(i)).setForeground(Color.black);
		}
		
		contentPanel.removeAll();
		contentPanel.add(new TableContentFromDatabase(tableName), BorderLayout.SOUTH);
		contentPanel.add(new Searchbar(tableName), BorderLayout.NORTH);
		contentPanel.revalidate();
		
	}
}
