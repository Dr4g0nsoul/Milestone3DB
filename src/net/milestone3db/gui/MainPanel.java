package net.milestone3db.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
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
	private TableContentFromDatabase tcfd;
	
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
		listPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		tcfd = new TableContentFromDatabase("publisher");
		((JLabel)listPanel.getComponent(2)).setForeground(Color.pink);
		contentPanel.add(tcfd, BorderLayout.SOUTH);
		contentPanel.add(new Searchbar(tcfd.getTable()), BorderLayout.NORTH);
		
		mainPanel.add(listPanel, BorderLayout.WEST);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
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
		tcfd = new TableContentFromDatabase(tableName);
		
		contentPanel.removeAll();
		contentPanel.add(tcfd, BorderLayout.SOUTH);
		contentPanel.add(new Searchbar(tcfd.getTable()), BorderLayout.NORTH);
		contentPanel.revalidate();
		
	}
}
