package com.cafeSystem;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#3C3D3C"));
		frame.setBounds(100, 100, 820, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.decode("#F2F2F3"));
		panel.setBounds(226, 83, 351, 346);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Username", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField.setBackground(Color.decode("#F2F2F3"));
		textField.setBounds(33, 143, 290, 45);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CAFE");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblNewLabel_1.setBounds(85, 56, 76, 37);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("SYSTEM");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(171, 52, 129, 45);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String userName = textField.getText();
                String password = passwordField.getText();
                
            	String url = "jdbc:mysql://127.0.0.1:3306/cafe_system";
            	String user = "root";
            	String pass = "madridista00";
            	
                try {
                    Connection connection = (Connection) DriverManager.getConnection(url, user, pass);

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select user_name, password from account where user_name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                    	JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    	frame.dispose();
                    	Reception call = new Reception();   
                    	call.main(null);
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.decode("#FFAB00"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(33, 272, 290, 37);
		panel.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		passwordField.setBackground(Color.decode("#F2F2F3"));
		passwordField.setBounds(33, 199, 290, 45);
		panel.add(passwordField);
	}
}
