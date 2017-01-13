package net.milestone3db.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class DefaultPanelDB extends JPanel {

	/**
	 * Create the panel
	 */
	public DefaultPanelDB() {
		setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(593, 401, 89, 23);
		add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(494, 401, 89, 23);
		add(btnUpdate);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(395, 401, 89, 23);
		add(btnInsert);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 0, 727, 316);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTableName = new JLabel("Defaultname");
		lblTableName.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTableName.setBounds(0, 0, 142, 26);
		panel.add(lblTableName);

	}
}
