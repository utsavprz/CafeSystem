package com.cafeSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    public void login() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground (Color.decode("#37807F"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setFont(new Font("monsterrat", Font.BOLD, 35));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setMargin(new Insets(0, 15, 0, 0));
        textField.setFont(new Font("monsterrat", Font.PLAIN, 15));
        textField.setBounds(450, 170, 281, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setMargin(new Insets(0, 15, 0, 0));
        passwordField.setFont(new Font("monsterrat", Font.PLAIN, 15));
        passwordField.setBounds(450, 286, 281, 40);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.white);
        lblUsername.setFont(new Font("monsterrat", Font.BOLD,15));
        lblUsername.setBounds(290, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.white);
        lblPassword.setFont(new Font("monsterrat", Font.BOLD,15));
        lblPassword.setBounds(290, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login"){  	    
    		@Override public void setBorder(Border border) {
        // No!
    		}
        };

    	btnNewButton.setBackground(Color.decode("#F58901"));
    	btnNewButton.setForeground(Color.decode("#FFFFFF"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(58, 400, 880, 50);
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
                    	dispose();
                    	UserHome callHome = new UserHome();
                        callHome.home();              
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);
        
        

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}