package net.milestone3db.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		listPanel.setLayout(new FlowLayout());
		listPanel.setBackground(Color.red);
		listPanel.setPreferredSize(new Dimension(400, 800));
		
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		righttopPanel = new JPanel();
		righttopPanel.setLayout(new FlowLayout());
		righttopPanel.setBackground(Color.blue);
		righttopPanel.setPreferredSize(new Dimension(1200, 500));
		rightbottomPanel = new JPanel();
		rightbottomPanel.setLayout(new BorderLayout());
		rightbottomPanel.setBackground(Color.green);
		rightbottomPanel.setPreferredSize(new Dimension(1200, 300));
				
		contentPanel.add(righttopPanel, BorderLayout.NORTH);
		contentPanel.add(rightbottomPanel, BorderLayout.SOUTH);
		
		
		mainPanel.add(listPanel, BorderLayout.WEST);
		mainPanel.add(contentPanel, BorderLayout.EAST);
		getContentPane().add(mainPanel);
		
		setResizable(false);
		setVisible(true);
		pack();
	}
}
