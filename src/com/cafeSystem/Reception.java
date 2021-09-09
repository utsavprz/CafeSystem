package com.cafeSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class Reception {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reception window = new Reception();
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
	public Reception() {
		initialize();
		connectDB();
		display();
	}
	
	Connection connection;
	String url = "jdbc:mysql://127.0.0.1:3306/cafe_system";
	String user = "root";
	String pass = "madridista00";
	
	public void connectDB() {
		try {

            connection = DriverManager.getConnection(url,user,pass);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
	}
	
	public void display() {
		
			
			try {
				connection = DriverManager.getConnection(url,user,pass);
				
	            String query = "select * from order_detail";
	            
	            PreparedStatement pst = connection.prepareStatement(query);
	            
	            ResultSet rs = pst.executeQuery();
	            table.setModel(DbUtils.resultSetToTableModel(rs));

	            
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
			
		}
	
	
	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1216, 666);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 817, 247);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(837, 60, 353, 556);
		frame.getContentPane().add(panel);
		
		JButton btnDisplay = new JButton("Refresh");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				display();
				
			}
		});
		btnDisplay.setBounds(696, 319, 131, 23);
		frame.getContentPane().add(btnDisplay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 350, 817, 142);
		frame.getContentPane().add(panel_1);
	}

}
