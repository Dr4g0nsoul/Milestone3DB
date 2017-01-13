package net.milestone3db.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DefaultPanel extends JFrame{
	public static JPanel mainPanel;
	public static JPanel leftPanel;
	public static JPanel rightPanel;
	public static JPanel righttopPanel;
	public static JPanel rightbottomPanel;
	public DefaultPanel()  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50, 50);
		setPreferredSize(new Dimension(1600, 800));
		setTitle("ProjectDBMilestone3");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout());
		leftPanel.setBackground(Color.red);
		leftPanel.setPreferredSize(new Dimension(400, 800));
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		righttopPanel = new JPanel();
		righttopPanel.setLayout(new FlowLayout());
		righttopPanel.setBackground(Color.blue);
		righttopPanel.setPreferredSize(new Dimension(1200, 500));
		rightbottomPanel = new JPanel();
		rightbottomPanel.setLayout(new BorderLayout());
		rightbottomPanel.setBackground(Color.green);
		rightbottomPanel.setPreferredSize(new Dimension(1200, 300));
				
		rightPanel.add(righttopPanel, BorderLayout.NORTH);
		rightPanel.add(rightbottomPanel, BorderLayout.SOUTH);
		
		
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		getContentPane().add(mainPanel);
		
		setResizable(false);
		setVisible(true);
		pack();
	}
}
