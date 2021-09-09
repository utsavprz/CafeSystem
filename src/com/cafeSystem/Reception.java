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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private JLabel lbl_subTotal;
	private JLabel lblTotal;
	private JLabel lblChange;
	
	
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    LocalDate localDate = LocalDate.now();
    String dateCur = dtf.format(localDate);
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTable table_1;
    
    DefaultTableModel df;
    private JTextField textField_9;
    
	double total, sum, tax;
	private JTextField textField_3;
    
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
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1216, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 817, 227);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				
				textField_5.setText(model.getValueAt(i, 2).toString());
				textField_6.setText(model.getValueAt(i, 3).toString());
				textField_7.setText(model.getValueAt(i, 4).toString());
				textField_8.setText(model.getValueAt(i, 5).toString());
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(837, 60, 353, 442);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		
		JButton btnDisplay = new JButton("Refresh");
		btnDisplay.setBounds(696, 290, 131, 23);
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				display();
				
			}
		});
		frame.getContentPane().add(btnDisplay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 324, 817, 176);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(10, 45, 46, 14);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText(dateCur);
		textField.setBounds(102, 42, 110, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(102, 73, 110, 20);
		panel_1.add(textField_1);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(10, 76, 89, 14);
		panel_1.add(lblCustomerName);
		
		JLabel lblNewLabel_1 = new JLabel("Table No.");
		lblNewLabel_1.setBounds(10, 106, 60, 14);
		panel_1.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(102, 103, 110, 20);
		panel_1.add(textField_2);
		
		JButton btnNewButton = new JButton("Show Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					connection = DriverManager.getConnection(url,user,pass);
					
		            String query = "select * from order_detail WHERE date = ? and custName = ? and tabNo = ?";
		            
		            PreparedStatement pst = connection.prepareStatement(query);
		            pst.setString(1,textField.getText());
		            pst.setString(2,textField_1.getText());
		            pst.setString(3,textField_2.getText());
		            
		            ResultSet rs = pst.executeQuery();
		            
		            table.setModel(DbUtils.resultSetToTableModel(rs));

		            
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
				
			}
		});
		btnNewButton.setBounds(10, 142, 103, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Search Order");
		lblNewLabel_2.setFont(new Font("Montserrat", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(10, 11, 131, 14);
		panel_1.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 138, 2);
		panel_1.add(separator);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_5.setText(null);
				textField_6.setText(null);
				textField_7.setText(null);
				textField_8.setText(null);
				textField_9.setText(null);
				
				lbl_subTotal.setText(null);
				lblTotal.setText(null);

				
			}
		});
		btnNewButton_1.setBounds(123, 142, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Item Name");
		lblNewLabel_1_1_1.setBounds(253, 45, 76, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(358, 45, 110, 20);
		panel_1.add(textField_5);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1_1.setBounds(253, 76, 46, 14);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_1_1.setBounds(253, 106, 60, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Item Total");
		lblNewLabel_1_1_1_1_1_1.setBounds(495, 48, 60, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(358, 73, 110, 20);
		panel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(358, 103, 110, 20);
		panel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(561, 45, 110, 20);
		panel_1.add(textField_8);
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				df = (DefaultTableModel)table_1.getModel();
				df.addRow(new Object[]
						{
								
								textField_5.getText(),
								textField_6.getText(),
								textField_7.getText(),
								textField_8.getText()
								
						});
				
				sum = 0;
				
				
				for (int i=0; i<table_1.getRowCount(); i++) {
					
					sum = sum + Integer.parseInt(table_1.getValueAt(i, 3).toString());
					
				}
				
				tax = (sum * 10) / 100;
						
				total = sum + tax;

				lbl_subTotal.setText(String.valueOf("Rs " + sum));
				lblTotal.setText(String.valueOf("Rs " + total));
				
			
				
			}
		});
		btnNewButton_2.setBounds(703, 44, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sub Total");
		lblNewLabel_3.setBounds(495, 79, 60, 14);
		panel_1.add(lblNewLabel_3);
		
		lbl_subTotal = new JLabel("");
		lbl_subTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_subTotal.setBounds(561, 77, 110, 18);
		panel_1.add(lbl_subTotal);
		
		JLabel lblNewLabel_5 = new JLabel("Total");
		lblNewLabel_5.setBounds(495, 109, 46, 14);
		panel_1.add(lblNewLabel_5);
		
		lblTotal = new JLabel("");
		lblTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTotal.setBounds(561, 106, 110, 18);
		panel_1.add(lblTotal);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Paid Amount");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(253, 146, 89, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1_1_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(358, 143, 110, 20);
		panel_1.add(textField_9);
		
		JButton btnNewButton_2_1 = new JButton("Get Change");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double paidAmt = Double.parseDouble(textField_9.getText());
				
				
				double changeAmt = paidAmt - total;
				
				
						
				textField_3.setText(String.valueOf(changeAmt));
				
			}
		});
		btnNewButton_2_1.setBounds(703, 142, 104, 23);
		panel_1.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("Change");
		lblNewLabel_5_1.setBounds(495, 146, 60, 14);
		panel_1.add(lblNewLabel_5_1);
		
		textField_3 = new JTextField();
		textField_3.setBorder(new LineBorder(Color.BLACK));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(561, 143, 110, 20);
		panel_1.add(textField_3);
		
		JButton btnNewButton_3 = new JButton("Invoice");
		btnNewButton_3.setBounds(703, 85, 89, 23);
		panel_1.add(btnNewButton_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(253, 32, 138, 2);
		panel_1.add(separator_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Add to Invoice");
		lblNewLabel_2_1.setFont(new Font("Montserrat", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(253, 11, 163, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 511, 817, 142);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Price", "Quantity", "Item Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
	}
}
