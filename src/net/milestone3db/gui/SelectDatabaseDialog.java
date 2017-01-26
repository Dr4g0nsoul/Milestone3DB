package net.milestone3db.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.milestone3db.jdbc.JDBCConnector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class SelectDatabaseDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfUser;
	private JPasswordField tfPassword;


	/**
	 * Create the dialog.
	 */
	public SelectDatabaseDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDatabaseName = new JLabel("Database Name: ");
		lblDatabaseName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDatabaseName.setBounds(36, 85, 107, 14);
		contentPanel.add(lblDatabaseName);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(36, 121, 108, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setBounds(35, 161, 108, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblDatabaseManager = new JLabel("Database Manager");
		lblDatabaseManager.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblDatabaseManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseManager.setBounds(73, 25, 298, 34);
		contentPanel.add(lblDatabaseManager);
		
		tfName = new JTextField();
		tfName.setBounds(153, 82, 174, 20);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		tfUser = new JTextField();
		tfUser.setBounds(154, 118, 173, 20);
		contentPanel.add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(153, 158, 174, 20);
		contentPanel.add(tfPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDBCConnector.setName(tfName.getText());
//						System.out.println(tfName.getText());
						JDBCConnector.setUser(tfUser.getText());
//						System.out.println(tfUser.getText());
						JDBCConnector.setPassword(tfPassword.getText());
//						System.out.println(Arrays.toString(tfPassword.getPassword()));
						try{
							JDBCConnector.getInstance();
							new MainPanel();
							setVisible(false);
							dispose();
						} catch (SQLException ex) {
							showMessage("Can not connect to Database");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
	
	public void showMessage (String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
