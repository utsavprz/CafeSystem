package com.cafeSystem;

import java.awt.EventQueue;

import java.util.stream.*;
import java.lang.Double;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import java.util.Arrays;

import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CafeHome {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	
	private JButton btnAdd;
	
	String item;
	int price;
	int tot;
	
	
	
	int cBurger = 150;
	int hBurger = 180;
	int csBurger = 130;
	int sBurger = 230;
	
	double cBurrito = 180;
	double sBurrito = 220;
	double pBurrito = 200;
	
	double mPizza = 360;
	double hPizza = 450;
	double bPizza = 550;
	double mxPizza = 690;
	
	double sWings = 240;
	double kMeal = 160;
	double cNugget = 220;
	
	double c = 185;
	double a = 150;
	double es = 115;
	double l = 195;
	double i = 190;
	double m = 250;
	double f = 195;
	
	double tax_rate = 15;
	
	double iTotal, iChange, iCost, iTax, iSubTotal, iMeal, iDrink;
	
	double[] itemcost = new double[21]; //array for calculation
	private JTextField textField_21;
	private JTable table;
	private JTextField textField_22;
	private JTextField textField_23;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeHome window = new CafeHome();
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
	public CafeHome() {
		initialize();
	}

	public void add(String cst, String tbn, String itm, int prc, int qtn, int tot, String dt, String tm) {
        String customer = cst;
        String table = tbn;
		String itemName = itm;
        int itemPrice = prc;
        int itemQty = qtn;
        int itemTot = tot;
        String curDate = dt;
        String curTime = tm;
        
		
        
    	String url = "jdbc:mysql://127.0.0.1:3306/cafe_system";
    	String user = "root";
    	String pass = "madridista00";

        
        if (customer.isEmpty() || table.isEmpty() || itemName.isEmpty() ) {
        	JOptionPane.showMessageDialog(btnAdd, "Enter valid details");
        }else {
        	try {
                Connection connection = DriverManager.getConnection(url,user,pass);

                String query = " insert into order_detail (custName, tabNo, item, price, qty, total, date, time)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?)";

                Statement sta = connection.createStatement();
                
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString (1, customer);
                preparedStmt.setString (2, table);
                preparedStmt.setString (3, itemName);
                preparedStmt.setInt (4, itemPrice);
                preparedStmt.setInt (5, itemQty);
                preparedStmt.setInt (6, itemTot);
                preparedStmt.setString (7, curDate);
                preparedStmt.setString (8, curTime);
                
                
                boolean x = preparedStmt.execute();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
	}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(200, 100, 1180, 711);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Cafe System");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 70, 561, 589);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		frame.getContentPane().add(panel);
		
		
		
		textField = new JTextField();
		textField.setBounds(182, 19, 86, 32);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setText("0");
		textField.setEnabled(false);
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		panel.setLayout(null);
		
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 73, 86, 32);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setText("0");
		textField_1.setEnabled(false);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(182, 126, 86, 32);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setText("0");
		textField_2.setEnabled(false);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(182, 285, 86, 32);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setText("0");
		textField_3.setEnabled(false);
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(182, 232, 86, 32);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setText("0");
		textField_4.setEnabled(false);
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(182, 179, 86, 32);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setText("0");
		textField_5.setEnabled(false);
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setColumns(10);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(182, 338, 86, 32);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_6.setText("0");
		textField_6.setEnabled(false);
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setColumns(10);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(182, 390, 86, 32);
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setText("0");
		textField_7.setEnabled(false);
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setColumns(10);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setBounds(182, 442, 86, 32);
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setText("0");
		textField_8.setEnabled(false);
		textField_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setColumns(10);
		panel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setBounds(182, 492, 86, 32);
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setText("0");
		textField_9.setEnabled(false);
		textField_9.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setColumns(10);
		panel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setBounds(182, 544, 86, 32);
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_10.setText("0");
		textField_10.setEnabled(false);
		textField_10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setColumns(10);
		panel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(465, 27, 86, 32);
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_11.setText("0");
		textField_11.setEnabled(false);
		textField_11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setColumns(10);
		panel.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setBounds(465, 80, 86, 32);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_12.setText("0");
		textField_12.setEnabled(false);
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setColumns(10);
		panel.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setBounds(465, 133, 86, 32);
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_13.setText("0");
		textField_13.setEnabled(false);
		textField_13.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setColumns(10);
		panel.add(textField_13);
		
		JCheckBox chckbx = new JCheckBox("Chicken Burger");
		chckbx.setBounds(10, 14, 166, 42);
		chckbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx.isSelected()) {
					textField.setEnabled(true);
					textField.setText("");
					
				}else {
					textField.setEnabled(false);
					textField.setText("0");
				}
				
			}
		});
		chckbx.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx);
		
		JCheckBox chckbx_2 = new JCheckBox("Ham Burger");
		chckbx_2.setBounds(10, 66, 166, 42);
		chckbx_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbx_2.isSelected()) {
					textField_1.setEnabled(true);
					textField_1.setText("");
				}else {
					textField_1.setEnabled(false);
					textField_1.setText("0");
				}
			}
		});
		chckbx_2.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_2);
		
		JCheckBox chckbx_3 = new JCheckBox("Chesse Burger");
		chckbx_3.setBounds(10, 119, 166, 42);
		chckbx_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_3.isSelected()) {
					textField_2.setEnabled(true);
					textField_2.setText("");
				}else {
					textField_2.setEnabled(false);
					textField_2.setText("0");
				}
				
			}
		});
		chckbx_3.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_3);
		
		JCheckBox chckbx_5 = new JCheckBox("Steak Burger");
		chckbx_5.setBounds(10, 171, 166, 42);
		chckbx_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_5.isSelected()) {
					textField_5.setEnabled(true);
					textField_5.setText("");
				}else {
					textField_5.setEnabled(false);
					textField_5.setText("0");
				}
				
			}
		});
		chckbx_5.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_5);
		
		JCheckBox chckbx_4 = new JCheckBox("Pork Burrito");
		chckbx_4.setBounds(10, 330, 166, 42);
		chckbx_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_4.isSelected()) {
					textField_6.setEnabled(true);
					textField_6.setText("");
				}else {
					textField_6.setEnabled(false);
					textField_6.setText("0");
				}
				
			}
		});
		chckbx_4.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_4);
		
		JCheckBox chckbx_7 = new JCheckBox("Shrimp Burrito");
		chckbx_7.setBounds(10, 275, 166, 42);
		chckbx_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_7.isSelected()) {
					textField_3.setEnabled(true);
					textField_3.setText("");
				}else {
					textField_3.setEnabled(false);
					textField_3.setText("0");
				}
				
			}
		});
		chckbx_7.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_7);
		
		JCheckBox chckbx_6 = new JCheckBox("Chicken Burrito");
		chckbx_6.setBounds(10, 224, 166, 42);
		chckbx_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_6.isSelected()) {
					textField_4.setEnabled(true);
					textField_4.setText("");
				}else {
					textField_4.setEnabled(false);
					textField_4.setText("0");
				}
				
			}
		});
		chckbx_6.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_6);
		
		JCheckBox chckbx_8 = new JCheckBox("Margherita Pizza");
		chckbx_8.setBounds(10, 384, 166, 42);
		chckbx_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_8.isSelected()) {
					textField_7.setEnabled(true);
					textField_7.setText("");
				}else {
					textField_7.setEnabled(false);
					textField_7.setText("0");
				}
				
			}
		});
		chckbx_8.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_8);
		
		JCheckBox chckbx_9 = new JCheckBox("Hawaiian Pizza");
		chckbx_9.setBounds(10, 436, 166, 42);
		chckbx_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_9.isSelected()) {
					textField_8.setEnabled(true);
					textField_8.setText("");
				}else {
					textField_8.setEnabled(false);
					textField_8.setText("0");
				}
				
			}
		});
		chckbx_9.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_9);
		
		JCheckBox chckbx_10 = new JCheckBox("BBQ Bacon Pizza");
		chckbx_10.setBounds(10, 486, 166, 42);
		chckbx_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_10.isSelected()) {
					textField_9.setEnabled(true);
					textField_9.setText("");
				}else {
					textField_9.setEnabled(false);
					textField_9.setText("0");
				}
				
			}
		});
		chckbx_10.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_10);
		
		JCheckBox chckbx_11 = new JCheckBox("Mix Pizza");
		chckbx_11.setBounds(10, 538, 166, 42);
		chckbx_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_11.isSelected()) {
					textField_10.setEnabled(true);
					textField_10.setText("");
				}else {
					textField_10.setEnabled(false);
					textField_10.setText("0");
				}
				
			}
		});
		chckbx_11.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_11);
		
		JCheckBox chckbx_12 = new JCheckBox("Saucy Wings");
		chckbx_12.setBounds(290, 19, 166, 42);
		chckbx_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_12.isSelected()) {
					textField_11.setEnabled(true);
					textField_11.setText("");
				}else {
					textField_11.setEnabled(false);
					textField_11.setText("0");
				}
				
			}
		});
		chckbx_12.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_12);
		
		JCheckBox chckbx_13 = new JCheckBox("Kids Meal");
		chckbx_13.setBounds(290, 70, 166, 42);
		chckbx_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_13.isSelected()) {
					textField_12.setEnabled(true);
					textField_12.setText("");
				}else {
					textField_12.setEnabled(false);
					textField_12.setText("0");
				}
				
			}
		});
		chckbx_13.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_13);
		
		JCheckBox chckbx_14 = new JCheckBox("Chicken Nugget");
		chckbx_14.setBounds(290, 125, 166, 42);
		chckbx_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_14.isSelected()) {
					textField_13.setEnabled(true);
					textField_13.setText("");
				}else {
					textField_13.setEnabled(false);
					textField_13.setText("0");
				}
				
			}
		});
		chckbx_14.setFont(new Font("Montserrat", Font.PLAIN, 16));
		panel.add(chckbx_14);
		
		JCheckBox chckbx_15 = new JCheckBox("Cappuccino");
		chckbx_15.setBounds(290, 218, 166, 42);
		panel.add(chckbx_15);
		chckbx_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_15.isSelected()) {
					textField_14.setEnabled(true);
					textField_14.setText("");
				}else {
					textField_14.setEnabled(false);
					textField_14.setText("0");
				}
				
			}
		});
		chckbx_15.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		JCheckBox chckbx_16 = new JCheckBox("Americano");
		chckbx_16.setBounds(290, 270, 166, 42);
		panel.add(chckbx_16);
		chckbx_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_16.isSelected()) {
					textField_15.setEnabled(true);
					textField_15.setText("");
				}else {
					textField_15.setEnabled(false);
					textField_15.setText("0");
				}
				
			}
		});
		chckbx_16.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		JCheckBox chckbx_17 = new JCheckBox("Espresso");
		chckbx_17.setBounds(290, 323, 166, 42);
		panel.add(chckbx_17);
		chckbx_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_17.isSelected()) {
					textField_16.setEnabled(true);
					textField_16.setText("");
				}else {
					textField_16.setEnabled(false);
					textField_16.setText("0");
				}
				
			}
		});
		chckbx_17.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		JCheckBox chckbx_18 = new JCheckBox("Latte");
		chckbx_18.setBounds(290, 375, 166, 42);
		panel.add(chckbx_18);
		chckbx_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_18.isSelected()) {
					textField_17.setEnabled(true);
					textField_17.setText("");
				}else {
					textField_17.setEnabled(false);
					textField_17.setText("0");
				}
				
			}
		});
		chckbx_18.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		JCheckBox chckbx_19 = new JCheckBox("Ice Latte");
		chckbx_19.setBounds(290, 428, 166, 42);
		panel.add(chckbx_19);
		chckbx_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_19.isSelected()) {
					textField_18.setEnabled(true);
					textField_18.setText("");
				}else {
					textField_18.setEnabled(false);
					textField_18.setText("0");
				}
				
			}
		});
		chckbx_19.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		JCheckBox chckbx_20 = new JCheckBox("Mocha");
		chckbx_20.setBounds(290, 479, 166, 42);
		panel.add(chckbx_20);
		chckbx_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_20.isSelected()) {
					textField_19.setEnabled(true);
					textField_19.setText("");
				}else {
					textField_19.setEnabled(false);
					textField_19.setText("0");
				}
				
			}
		});
		chckbx_20.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		JCheckBox chckbx_21 = new JCheckBox("Flat White");
		chckbx_21.setBounds(290, 534, 166, 42);
		panel.add(chckbx_21);
		chckbx_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx_21.isSelected()) {
					textField_20.setEnabled(true);
					textField_20.setText("");
				}else {
					textField_20.setEnabled(false);
					textField_20.setText("0");
				}
				
			}
		});
		chckbx_21.setFont(new Font("Montserrat", Font.PLAIN, 16));
		
		textField_14 = new JTextField();
		textField_14.setBounds(465, 224, 86, 32);
		panel.add(textField_14);
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_14.setText("0");
		textField_14.setEnabled(false);
		textField_14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(465, 278, 86, 32);
		panel.add(textField_15);
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_15.setText("0");
		textField_15.setEnabled(false);
		textField_15.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(465, 331, 86, 32);
		panel.add(textField_16);
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_16.setText("0");
		textField_16.setEnabled(false);
		textField_16.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(465, 384, 86, 32);
		panel.add(textField_17);
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_17.setText("0");
		textField_17.setEnabled(false);
		textField_17.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setColumns(10);
		
		textField_18 = new JTextField();
		textField_18.setBounds(465, 437, 86, 32);
		panel.add(textField_18);
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_18.setText("0");
		textField_18.setEnabled(false);
		textField_18.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setBounds(465, 490, 86, 32);
		panel.add(textField_19);
		textField_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_19.setText("0");
		textField_19.setEnabled(false);
		textField_19.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setColumns(10);
		
		textField_20 = new JTextField();
		textField_20.setBounds(465, 543, 86, 32);
		panel.add(textField_20);
		textField_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_20.setText("0");
		textField_20.setEnabled(false);
		textField_20.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char iNumber = e.getKeyChar();
				if (!(Character.isDigit(iNumber))
					||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
			e.consume();
		}
			}
		});
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBounds(290, 179, 261, 32);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblDrinks = new JLabel("Drinks");
		lblDrinks.setBounds(0, 0, 261, 32);
		panel_6.add(lblDrinks);
		lblDrinks.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrinks.setForeground(Color.WHITE);
		lblDrinks.setFont(new Font("Montserrat", Font.BOLD, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(581, 11, 571, 295);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 551, 271);
		panel_1.add(table);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_2_2.setBounds(581, 389, 270, 146);
		frame.getContentPane().add(panel_2_2);
		
		JLabel jblTax = new JLabel("");
		jblTax.setHorizontalAlignment(SwingConstants.CENTER);
		jblTax.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblTax.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblTax.setBackground(Color.WHITE);
		jblTax.setBounds(154, 54, 106, 32);
		panel_2_2.add(jblTax);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_2_1.setBounds(861, 389, 291, 146);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_4.setBounds(581, 608, 561, 51);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel jblTotal = new JLabel("");
		jblTotal.setBounds(428, 11, 123, 31);
		panel_4.add(jblTotal);
		jblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jblTotal.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblTotal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblTotal.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(581, 546, 561, 51);
		frame.getContentPane().add(panel_2);
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_2.setLayout(null);
		
		JLabel jblCostOfMeal = new JLabel("");
		jblCostOfMeal.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblCostOfMeal.setBackground(Color.WHITE);
		jblCostOfMeal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblCostOfMeal.setHorizontalAlignment(SwingConstants.CENTER);
		jblCostOfMeal.setBounds(445, 11, 106, 31);
		panel_2.add(jblCostOfMeal);
		
		JLabel lblNewLabel_1_9_1 = new JLabel("Cost of Drinks");
		lblNewLabel_1_9_1.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_1_9_1.setBounds(10, 11, 150, 31);
		panel_2.add(lblNewLabel_1_9_1);
		
		JLabel jblCostOfDrinks = new JLabel("");
		jblCostOfDrinks.setHorizontalAlignment(SwingConstants.CENTER);
		jblCostOfDrinks.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblCostOfDrinks.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblCostOfDrinks.setBackground(Color.WHITE);
		jblCostOfDrinks.setBounds(165, 11, 106, 31);
		panel_2.add(jblCostOfDrinks);
		
		JLabel jblChange = new JLabel("");
		jblChange.setHorizontalAlignment(SwingConstants.CENTER);
		jblChange.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblChange.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(128, 128, 128)));
		jblChange.setBackground(Color.WHITE);
		jblChange.setBounds(154, 11, 106, 32);
		panel_2_2.add(jblChange);
		
		JLabel jblSubTotal = new JLabel("");
		jblSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jblSubTotal.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblSubTotal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblSubTotal.setBackground(Color.WHITE);
		jblSubTotal.setBounds(150, 11, 123, 31);
		panel_4.add(jblSubTotal);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				chckbx.setSelected(false);
				chckbx_2.setSelected(false);
				chckbx_3.setSelected(false);
				chckbx_4.setSelected(false);
				chckbx_5.setSelected(false);
				chckbx_6.setSelected(false);
				chckbx_7.setSelected(false);
				chckbx_8.setSelected(false);
				chckbx_9.setSelected(false);
				chckbx_10.setSelected(false);
				chckbx_11.setSelected(false);
				chckbx_12.setSelected(false);
				chckbx_13.setSelected(false);
				chckbx_14.setSelected(false);
				chckbx_15.setSelected(false);
				chckbx_16.setSelected(false);
				chckbx_17.setSelected(false);
				chckbx_18.setSelected(false);
				chckbx_19.setSelected(false);
				chckbx_20.setSelected(false);
				chckbx_21.setSelected(false);
				

				textField.setEnabled(false);
				textField_1.setEnabled(false);
				textField_2.setEnabled(false);;
				textField_3.setEnabled(false);;
				textField_4.setEnabled(false);;
				textField_5.setEnabled(false);;
				textField_6.setEnabled(false);
				textField_7.setEnabled(false);
				textField_8.setEnabled(false);
				textField_9.setEnabled(false);
				textField_10.setEnabled(false);
				textField_11.setEnabled(false);
				textField_12.setEnabled(false);
				textField_13.setEnabled(false);
				textField_14.setEnabled(false);
				textField_15.setEnabled(false);
				textField_16.setEnabled(false);
				textField_17.setEnabled(false);
				textField_18.setEnabled(false);
				textField_19.setEnabled(false);
				textField_20.setEnabled(false);
				
				textField.setText("0");
				textField_1.setText("0");
				textField_2.setText("0");
				textField_3.setText("0");
				textField_4.setText("0");
				textField_5.setText("0");
				textField_6.setText("0");
				textField_7.setText("0");
				textField_8.setText("0");
				textField_9.setText("0");
				textField_10.setText("0");
				textField_11.setText("0");
				textField_12.setText("0");
				textField_13.setText("0");
				textField_14.setText("0");
				textField_15.setText("0");
				textField_16.setText("0");
				textField_17.setText("0");
				textField_18.setText("0");
				textField_19.setText("0");
				textField_20.setText("0");
				textField_21.setText(null);
				textField_22.setText(null);
				textField_23.setText(null);
				
				jblCostOfMeal.setText(null);
				jblCostOfDrinks.setText(null);
				jblTax.setText(null);
				jblTotal.setText(null);
				jblSubTotal.setText(null);
				jblChange.setText(null);
				

				
				
				
				
				
			}
		});
		btnReset.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnReset.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReset.setBackground(new Color(153, 255, 102));
		btnReset.setBounds(165, 55, 103, 33);
		panel_2_1.add(btnReset);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 571, 59);
		panel_3.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Meals");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Montserrat", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 0, 561, 59);
		panel_3.add(lblNewLabel);
		

		
		JLabel lblNewLabel_1_9_1_1 = new JLabel("Total");
		lblNewLabel_1_9_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_9_1_1.setBounds(303, 11, 69, 31);
		panel_4.add(lblNewLabel_1_9_1_1);
		lblNewLabel_1_9_1_1.setFont(new Font("Montserrat", Font.BOLD, 18));
		

		
		JLabel lblNewLabel_1_9_1_1_1 = new JLabel("Sub Total");
		lblNewLabel_1_9_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_9_1_1_1.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_1_9_1_1_1.setBounds(10, 11, 94, 31);
		panel_4.add(lblNewLabel_1_9_1_1_1);
		
		JLabel lblNewLabel_1_9_1_2 = new JLabel("Tax");
		lblNewLabel_1_9_1_2.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_1_9_1_2.setBounds(10, 55, 40, 31);
		panel_2_2.add(lblNewLabel_1_9_1_2);
		
		JLabel lblNewLabel_1_9_1_2_1 = new JLabel("Change");
		lblNewLabel_1_9_1_2_1.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_1_9_1_2_1.setBounds(10, 13, 150, 31);
		panel_2_2.add(lblNewLabel_1_9_1_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Paid Amount");
		lblNewLabel_1.setBounds(10, 106, 111, 23);
		panel_2_2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Montserrat", Font.BOLD, 16));
		
		textField_21 = new JTextField();
		textField_21.setBounds(154, 97, 106, 32);
		panel_2_2.add(textField_21);
		textField_21.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.GRAY));
		textField_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setColumns(10);
		

		
		JButton btnNewButton = new JButton("Total");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				itemcost[0] = Double.parseDouble(textField.getText()) * cBurger;
				itemcost[1] = Double.parseDouble(textField_1.getText()) * hBurger;
				itemcost[2] = Double.parseDouble(textField_2.getText()) * csBurger;
				itemcost[3] = Double.parseDouble(textField_3.getText()) * sBurrito;
				itemcost[4] = Double.parseDouble(textField_4.getText()) * cBurrito;
				itemcost[5] = Double.parseDouble(textField_5.getText()) * sBurger;
				itemcost[6] = Double.parseDouble(textField_6.getText()) * pBurrito;
				itemcost[7] = Double.parseDouble(textField_7.getText()) * mPizza;
				itemcost[8] = Double.parseDouble(textField_8.getText()) * hPizza;
				itemcost[9] = Double.parseDouble(textField_9.getText()) * bPizza;
				itemcost[10] = Double.parseDouble(textField_10.getText()) * mxPizza;
				itemcost[11] = Double.parseDouble(textField_11.getText()) * sWings;
				itemcost[12] = Double.parseDouble(textField_12.getText()) * kMeal;
				itemcost[13] = Double.parseDouble(textField_13.getText()) * cNugget;
				itemcost[14] = Double.parseDouble(textField_14.getText()) * c;
				itemcost[15] = Double.parseDouble(textField_15.getText()) * a;
				itemcost[16] = Double.parseDouble(textField_16.getText()) * es;
				itemcost[17] = Double.parseDouble(textField_17.getText()) * l;
				itemcost[18] = Double.parseDouble(textField_18.getText()) * i;
				itemcost[19] = Double.parseDouble(textField_19.getText()) * m;
				itemcost[20] = Double.parseDouble(textField_20.getText()) * f;
				
				iMeal = itemcost[0] + itemcost[1] + itemcost[2] + itemcost[3] + itemcost[4] + itemcost[5] + itemcost[6] + itemcost[7]
						+ itemcost[8] + itemcost[9] + itemcost[10] + itemcost[11]+ itemcost[12] + itemcost[13];
				
				iDrink = itemcost[14] + itemcost[15]
						+ itemcost[16] + itemcost[17] + itemcost[18] + itemcost[19] + itemcost[20];
				
				
				iSubTotal = itemcost[0] + itemcost[1] + itemcost[2] + itemcost[3] + itemcost[4] + itemcost[5] + itemcost[6] + itemcost[7]
						+ itemcost[8] + itemcost[9] + itemcost[10] + itemcost[11]+ itemcost[12] + itemcost[13] + itemcost[14] + itemcost[15]
						+ itemcost[16] + itemcost[17] + itemcost[18] + itemcost[19] + itemcost[20];
				
				iTax = (iSubTotal * tax_rate) / 100;
				
				String meal = String.format("Rs %.2f",iMeal);				
				jblCostOfMeal.setText(meal);
				
				String drinks = String.format("Rs %.2f",iDrink);				
				jblCostOfDrinks.setText(drinks);
				
				String cTax = String.format("Rs %.2f",iTax);
				jblTax.setText(cTax);
				
				
				String iAmount = String.format("Rs %.2f",iSubTotal);				
				jblSubTotal.setText(iAmount);
				
				String cTotal = String.format("Rs %.2f",iSubTotal + iTax);				
				jblTotal.setText(cTotal);
					
				
				
			}


		});
		
		JButton getChange = new JButton("Get Change");
		getChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double paidAmt = Double.parseDouble(textField_21.getText());
				
				double ttlAmt = iSubTotal + iTax;
				
				double changeAmt = paidAmt - ttlAmt;
				
				
				
				String change = String.format("Rs %.2f",changeAmt);				
				jblChange.setText(change);
				
				
				
			}
		});
		getChange.setFont(new Font("Montserrat", Font.BOLD, 14));
		getChange.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getChange.setBackground(new Color(153, 255, 102));
		getChange.setBounds(23, 102, 103, 33);
		panel_2_1.add(getChange);
		
		btnNewButton.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(128, 128, 128), new Color(0, 255, 0)));
		btnNewButton.setBackground(new Color(153, 255, 102));
		btnNewButton.setBounds(165, 11, 103, 33);
		panel_2_1.add(btnNewButton);
		

		
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.setBounds(23, 55, 103, 33);
		panel_2_1.add(btnInvoice);
		btnInvoice.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnInvoice.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnInvoice.setBackground(new Color(153, 255, 102));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(165, 102, 103, 33);
		panel_2_1.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "CafeSystem",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
		});
		btnExit.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnExit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExit.setBackground(new Color(255, 51, 51));
		

		
		JLabel lblNewLabel_1_9 = new JLabel("Cost of Meal");
		lblNewLabel_1_9.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_1_9.setBounds(290, 11, 150, 31);
		panel_2.add(lblNewLabel_1_9);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.window);
		panel_5.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_5.setBounds(581, 317, 571, 64);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		textField_22 = new JTextField();
		textField_22.setBackground(SystemColor.window);
		textField_22.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Customer Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_22.setBounds(36, 11, 230, 42);
		panel_5.add(textField_22);
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		textField_23.setBackground(SystemColor.window);
		textField_23.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Table no.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_23.setBounds(296, 11, 230, 42);
		panel_5.add(textField_23);
		textField_23.setColumns(10);
		
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
	      LocalDate localDate = LocalDate.now();
	      String dateCur = dtf.format(localDate);
	      
		
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        String timeCur = dtfa.format(localTime);
		
		
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbx.isSelected()) {
					 int totalPric = Integer.parseInt(textField.getText()) * cBurger;
					 int totalqtn = Integer.parseInt(textField.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Chicken Burger", cBurger, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_2.isSelected()) {
					 int totalPric = Integer.parseInt(textField_1.getText()) * hBurger;
					 int totalqtn = Integer.parseInt(textField_1.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Ham Burger", hBurger, totalqtn, totalPric, dateCur, timeCur);
				}
				
			}
		});
		btnAdd.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnAdd.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(128, 128, 128), new Color(0, 255, 0)));
		btnAdd.setBackground(new Color(153, 255, 102));
		btnAdd.setBounds(23, 11, 103, 33);
		panel_2_1.add(btnAdd);
		

		

		

		

		

	}
}
