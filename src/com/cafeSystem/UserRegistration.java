package com.cafeSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.border.Border;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton;


    public void register() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserRegistration frame = new UserRegistration();
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

    @SuppressWarnings("serial")
	public UserRegistration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground (Color.decode("#37807F"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Create an account");
        lblNewUserRegister.setForeground(Color.white);
        lblNewUserRegister.setFont(new Font("monsterrat", Font.BOLD, 35));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First name");
        lblName.setForeground(Color.white);
        lblName.setFont(new Font("monsterrat", Font.BOLD, 15));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setFont(new Font("monsterrat", Font.BOLD, 15));
        lblNewLabel.setBounds(58, 243, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setForeground(Color.white);
        lblEmailAddress.setFont(new Font("monsterrat", Font.BOLD, 15));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmailAddress);

        firstname = new JTextField();
		firstname.setMargin(new Insets(0, 15, 0, 0));
        firstname.setFont(new Font("monsterrat", Font.PLAIN, 15));
        firstname.setBounds(214, 151, 228, 40);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setMargin(new Insets(0, 15, 0, 0));
        lastname.setFont(new Font("monsterrat", Font.PLAIN, 15));
        lastname.setBounds(214, 235, 228, 40);
        contentPane.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();
		email.setMargin(new Insets(0, 15, 0, 0));
        email.setFont(new Font("monsterrat", Font.PLAIN, 15));
        email.setBounds(214, 320, 228, 40);
        contentPane.add(email);
        email.setColumns(10);

        username = new JTextField();
		username.setMargin(new Insets(0, 15, 0, 0));
        username.setFont(new Font("monsterrat", Font.PLAIN, 15));
        username.setBounds(707, 151, 228, 40);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.white);
        lblUsername.setFont(new Font("monsterrat", Font.BOLD, 15));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.white);
        lblPassword.setFont(new Font("monsterrat", Font.BOLD, 15));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile number");
        lblMobileNumber.setForeground(Color.white);
        lblMobileNumber.setFont(new Font("monsterrat", Font.BOLD, 15));
        lblMobileNumber.setBounds(542, 329, 139, 26);
        contentPane.add(lblMobileNumber);

        mob = new JTextField();
		mob.setMargin(new Insets(0, 15, 0, 0));
        mob.setFont(new Font("monsterrat", Font.PLAIN, 15));
        mob.setBounds(707, 320, 228, 40);
        contentPane.add(mob);
        mob.setColumns(10);

        passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(0, 15, 0, 0));
        passwordField.setFont(new Font("monsterrat", Font.PLAIN, 15));
        passwordField.setBounds(707, 235, 228, 40);
        contentPane.add(passwordField);

        btnNewButton = new JButton("Register")  {  	    
        		@Override public void setBorder(Border border) {
	        // No!
	    }
	};
	btnNewButton.setBackground(Color.decode("#F58901"));
	btnNewButton.setForeground(Color.decode("#FFFFFF"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String emailId = email.getText();
                String userName = username.getText();
                String mobileNumber = mob.getText();
                int len = mobileNumber.length();
				String password = passwordField.getText();
                
            	String url = "jdbc:mysql://127.0.0.1:3306/cafe_system";
            	String user = "root";
            	String pass = "madridista00";

                String greet = "" + firstName;
                greet += " \n";
                
                if (firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || userName.isEmpty() || mobileNumber.isEmpty() || password.isEmpty() && len!= 10) {
                	JOptionPane.showMessageDialog(btnNewButton, "Enter valid details");
                }else {
                	try {
                        Connection connection = DriverManager.getConnection(url,user,pass);

                        String query = " insert into account (first_name, last_name, user_name, password, email_id, mobile_number)"
                                + " values (?, ?, ?, ?, ?, ?)";

                        Statement sta = connection.createStatement();
                        
                        PreparedStatement preparedStmt = connection.prepareStatement(query);
                        preparedStmt.setString (1, firstName);
                        preparedStmt.setString (2, lastName);
                        preparedStmt.setString (3, userName);
                        preparedStmt.setString (4, password);
                        preparedStmt.setString (5, emailId);
                        preparedStmt.setString (6, mobileNumber);
                        
                        
                        boolean x = preparedStmt.execute();
                        if (x == true) {
                            JOptionPane.showMessageDialog(btnNewButton, "Account not created");
                            CafeSystem call = new CafeSystem();
                            call.main(null);
                        } else {
                            JOptionPane.showMessageDialog(btnNewButton,
                                "Welcome, " + greet + "Your account is sucessfully created");
                        }
                        connection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
	

            }
        });
        
        btnNewButton.setFont(new Font("montserrat", Font.BOLD, 18));
        btnNewButton.setBounds(58, 440, 880, 50);
        contentPane.add(btnNewButton);
    }
}
