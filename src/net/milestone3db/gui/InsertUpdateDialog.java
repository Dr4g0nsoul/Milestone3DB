package net.milestone3db.gui;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

public class InsertUpdateDialog extends JDialog{

	public InsertUpdateDialog(String tableName, ArrayList<String> data, boolean inserting) {
		
		JLabel lblInsertData = new JLabel("Insert data");
		lblInsertData.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblInsertData.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblInsertData, BorderLayout.NORTH);
		
		JPanel panelContent = new JPanel();
		getContentPane().add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		
		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		//TODO: Add listener for insert and update
		
		
		
		panelButtons.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		
		setVisible(true);
	}
	
	private class ItemPanel
	{
		
		public ItemPanel(String name, String value) {
			setLayout(new FlowLayout());
			
			add(new JLabel(name+": "));
			add(new TextField(value));
			
			setVisible(true);
		}
	}
}
