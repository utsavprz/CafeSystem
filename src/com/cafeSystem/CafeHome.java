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
import javax.swing.border.MatteBorder;

public class CafeHome {

	private JFrame frame;
	private JPanel panel_1;
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
	
	int cBurrito = 180;
	int sBurrito = 220;
	int pBurrito = 200;
	
	int mPizza = 360;
	int hPizza = 450;
	int bPizza = 550;
	int mxPizza = 690;
	
	int sWings = 240;
	int kMeal = 160;
	int cNugget = 220;
	
	int c = 185;
	int a = 150;
	int es = 115;
	int l = 195;
	int i = 190;
	int m = 250;
	int f = 195;
	
	double tax_rate = 15;
	
	double iTotal, iChange, iCost, iTax, iSubTotal, iMeal, iDrink;
	
	double[] itemcost = new double[21]; //array for calculation
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField txtYourOrderHas;
	private JTextField textField_24;
	

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
                
                
                 preparedStmt.execute();
               
                
//        		txtYourOrderHas.setText("Dear,"+ " " + textField_22.getText());
//
//        		
//                textField_24.setText("Your order has been placed for" + " " + textField_23.getText());

                connection.close();
                

                
    
            } catch (Exception x) {
                x.printStackTrace();
            }
	}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setBounds(200, 100, 1178, 711);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Cafe System");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 58, 561, 601);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
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
		textField_11.setBounds(465, 19, 86, 32);
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
		textField_12.setBounds(465, 73, 86, 32);
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
		textField_13.setBounds(465, 126, 86, 32);
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
		chckbx.setBounds(10, 14, 166, 32);
		chckbx.setBackground(Color.ORANGE);
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
		chckbx_2.setBounds(10, 66, 166, 32);
		chckbx_2.setBackground(Color.ORANGE);
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
		chckbx_3.setBounds(10, 119, 166, 32);
		chckbx_3.setBackground(Color.ORANGE);
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
		chckbx_5.setBounds(10, 171, 166, 32);
		chckbx_5.setBackground(Color.ORANGE);
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
		chckbx_4.setBounds(10, 330, 166, 32);
		chckbx_4.setBackground(Color.ORANGE);
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
		chckbx_7.setBounds(10, 275, 166, 32);
		chckbx_7.setBackground(Color.ORANGE);
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
		chckbx_6.setBounds(10, 224, 166, 32);
		chckbx_6.setBackground(Color.ORANGE);
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
		chckbx_8.setBounds(10, 384, 166, 32);
		chckbx_8.setBackground(Color.ORANGE);
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
		chckbx_9.setBounds(10, 436, 166, 32);
		chckbx_9.setBackground(Color.ORANGE);
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
		chckbx_10.setBounds(10, 486, 166, 32);
		chckbx_10.setBackground(Color.ORANGE);
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
		chckbx_11.setBounds(10, 538, 166, 32);
		chckbx_11.setBackground(Color.ORANGE);
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
		chckbx_12.setBounds(293, 19, 166, 32);
		chckbx_12.setBackground(Color.ORANGE);
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
		chckbx_13.setBounds(293, 70, 166, 32);
		chckbx_13.setBackground(Color.ORANGE);
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
		chckbx_14.setBounds(293, 125, 166, 32);
		chckbx_14.setBackground(Color.ORANGE);
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
		chckbx_15.setBounds(293, 224, 166, 32);
		chckbx_15.setBackground(Color.ORANGE);
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
		chckbx_16.setBounds(293, 276, 166, 32);
		chckbx_16.setBackground(Color.ORANGE);
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
		chckbx_17.setBounds(293, 329, 166, 32);
		chckbx_17.setBackground(Color.ORANGE);
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
		chckbx_18.setBounds(293, 381, 166, 32);
		chckbx_18.setBackground(Color.ORANGE);
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
		chckbx_19.setBounds(293, 434, 166, 32);
		chckbx_19.setBackground(Color.ORANGE);
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
		chckbx_20.setBounds(293, 485, 166, 32);
		chckbx_20.setBackground(Color.ORANGE);
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
		chckbx_21.setBounds(293, 540, 166, 32);
		chckbx_21.setBackground(Color.ORANGE);
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
		textField_14.setBounds(465, 232, 86, 32);
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
		textField_15.setBounds(465, 286, 86, 32);
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
		textField_16.setBounds(465, 339, 86, 32);
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
		textField_17.setBounds(465, 392, 86, 32);
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
		textField_18.setBounds(465, 445, 86, 32);
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
		textField_19.setBounds(465, 498, 86, 32);
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
		textField_20.setBounds(465, 551, 86, 32);
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
		panel_6.setBounds(290, 179, 261, 32);
		panel_6.setBackground(Color.DARK_GRAY);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblDrinks = new JLabel("Drinks");
		lblDrinks.setBounds(0, 0, 261, 32);
		panel_6.add(lblDrinks);
		lblDrinks.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrinks.setForeground(Color.WHITE);
		lblDrinks.setFont(new Font("Montserrat", Font.BOLD, 18));
		
		JLabel CB = new JLabel("Rs" + " " + cBurger);
		CB.setBounds(33, 45, 60, 16);
		CB.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(CB);
		
		JLabel HB = new JLabel("Rs" + " " + hBurger);
		HB.setBounds(33, 98, 60, 14);
		HB.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(HB);
		
		JLabel CSB = new JLabel("Rs" + " " + csBurger);
		CSB.setBounds(33, 150, 60, 14);
		CSB.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(CSB);
		
		JLabel SB = new JLabel("Rs" + " " + sBurger);
		SB.setBounds(33, 203, 60, 14);
		SB.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(SB);
		
		JLabel CBR = new JLabel("Rs" + " " + cBurrito);
		CBR.setBounds(33, 257, 60, 14);
		CBR.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(CBR);
		
		JLabel SBR = new JLabel("Rs" + " " + sBurrito);
		SBR.setBounds(33, 309, 60, 14);
		SBR.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(SBR);
		
		JLabel PBR = new JLabel("Rs" + " " + pBurrito);
		PBR.setBounds(33, 362, 60, 14);
		PBR.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(PBR);
		
		JLabel MP = new JLabel("Rs" + " " + mPizza);
		MP.setBounds(33, 415, 60, 14);
		MP.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(MP);
		
		JLabel HP = new JLabel("Rs" + " " + hPizza);
		HP.setBounds(33, 465, 60, 14);
		HP.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(HP);
		
		JLabel BBP = new JLabel("Rs" + " " + bPizza);
		BBP.setBounds(33, 517, 60, 14);
		BBP.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(BBP);
		
		JLabel MXP = new JLabel("Rs" + " " + mxPizza);
		MXP.setBounds(33, 562, 60, 24);
		MXP.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(MXP);
		
		JLabel SW = new JLabel("Rs" + " " + sWings);
		SW.setBounds(318, 49, 60, 16);
		SW.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(SW);
		
		JLabel KM = new JLabel("Rs" + " " + kMeal);
		KM.setBounds(318, 102, 60, 14);
		KM.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(KM);
		
		JLabel CN = new JLabel("Rs" + " " + cNugget);
		CN.setBounds(318, 154, 60, 14);
		CN.setFont(new Font("Verdana", Font.BOLD, 13));
		panel.add(CN);
		
		JLabel DC = new JLabel("Rs" + " " + c);
		DC.setFont(new Font("Verdana", Font.BOLD, 13));
		DC.setBounds(318, 255, 60, 14);
		panel.add(DC);
		
		JLabel DA = new JLabel("Rs" + " " + a);
		DA.setFont(new Font("Verdana", Font.BOLD, 13));
		DA.setBounds(318, 306, 60, 14);
		panel.add(DA);
		
		JLabel DE = new JLabel("Rs" + " " + es);
		DE.setFont(new Font("Verdana", Font.BOLD, 13));
		DE.setBounds(318, 359, 60, 14);
		panel.add(DE);
		
		JLabel DL = new JLabel("Rs" + " " + l);
		DL.setFont(new Font("Verdana", Font.BOLD, 13));
		DL.setBounds(318, 412, 60, 14);
		panel.add(DL);
		
		JLabel DIL = new JLabel("Rs" + " " + i);
		DIL.setFont(new Font("Verdana", Font.BOLD, 13));
		DIL.setBounds(318, 462, 60, 14);
		panel.add(DIL);
		
		JLabel DM = new JLabel("Rs" + " " + m);
		DM.setFont(new Font("Verdana", Font.BOLD, 13));
		DM.setBounds(318, 514, 60, 14);
		panel.add(DM);
		
		JLabel DFW = new JLabel("Rs" + " " + f);
		DFW.setFont(new Font("Verdana", Font.BOLD, 13));
		DFW.setBounds(318, 562, 60, 28);
		panel.add(DFW);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(581, 57, 571, 249);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtYourOrderHas = new JTextField();
		txtYourOrderHas.setBorder(null);
		txtYourOrderHas.setText("");
		txtYourOrderHas.setBackground(SystemColor.inactiveCaptionBorder);
		txtYourOrderHas.setFont(new Font("Montserrat", Font.BOLD, 24));
		txtYourOrderHas.setBounds(20, 57, 494, 57);
		txtYourOrderHas.setColumns(10);
		panel_1.add(txtYourOrderHas);
		
		textField_24 = new JTextField();
		textField_24.setBorder(null);
		textField_24.setText("");
		textField_24.setBackground(SystemColor.inactiveCaptionBorder);
		textField_24.setFont(new Font("Montserrat", Font.BOLD, 24));
		textField_24.setColumns(10);
		textField_24.setBounds(20, 111, 541, 64);
		panel_1.add(textField_24);
		
		JLabel lblNewLabel_2 = new JLabel("Please use the system only to order food");
		lblNewLabel_2.setBounds(321, 11, 240, 14);
		panel_1.add(lblNewLabel_2);

		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(Color.ORANGE);
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
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
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBorder(null);
		panel_2_1.setBounds(581, 600, 571, 59);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.ORANGE);
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		panel_4.setBounds(581, 536, 571, 51);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel jblTotal = new JLabel("");
		jblTotal.setBounds(438, 12, 123, 31);
		panel_4.add(jblTotal);
		jblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jblTotal.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblTotal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblTotal.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(849, 389, 303, 146);
		frame.getContentPane().add(panel_2);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		panel_2.setLayout(null);
		
		JLabel jblCostOfMeal = new JLabel("");
		jblCostOfMeal.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblCostOfMeal.setBackground(Color.WHITE);
		jblCostOfMeal.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblCostOfMeal.setHorizontalAlignment(SwingConstants.CENTER);
		jblCostOfMeal.setBounds(187, 53, 106, 31);
		panel_2.add(jblCostOfMeal);
		
		JLabel lblNewLabel_1_9_1 = new JLabel("Cost of Drinks");
		lblNewLabel_1_9_1.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblNewLabel_1_9_1.setBounds(32, 11, 150, 31);
		panel_2.add(lblNewLabel_1_9_1);
		
		JLabel jblCostOfDrinks = new JLabel("");
		jblCostOfDrinks.setHorizontalAlignment(SwingConstants.CENTER);
		jblCostOfDrinks.setFont(new Font("Montserrat", Font.PLAIN, 17));
		jblCostOfDrinks.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.GRAY));
		jblCostOfDrinks.setBackground(Color.WHITE);
		jblCostOfDrinks.setBounds(187, 11, 106, 31);
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
				txtYourOrderHas.setText(null);
				textField_24.setText(null);
				
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
		btnReset.setBounds(290, 11, 103, 33);
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
		lblNewLabel_1_9_1_1.setBounds(313, 12, 69, 31);
		panel_4.add(lblNewLabel_1_9_1_1);
		lblNewLabel_1_9_1_1.setFont(new Font("Montserrat", Font.BOLD, 16));
		

		
		JLabel lblNewLabel_1_9_1_1_1 = new JLabel("Sub Total");
		lblNewLabel_1_9_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_9_1_1_1.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblNewLabel_1_9_1_1_1.setBounds(10, 11, 94, 31);
		panel_4.add(lblNewLabel_1_9_1_1_1);
		
		JPanel panel_7_2_1 = new JPanel();
		panel_7_2_1.setBackground(Color.WHITE);
		panel_7_2_1.setBounds(150, 11, 123, 32);
		panel_4.add(panel_7_2_1);
		
		JPanel panel_7_2_1_1 = new JPanel();
		panel_7_2_1_1.setBackground(Color.WHITE);
		panel_7_2_1_1.setBounds(438, 11, 123, 32);
		panel_4.add(panel_7_2_1_1);
		
		JLabel lblNewLabel_1_9_1_2 = new JLabel("Tax");
		lblNewLabel_1_9_1_2.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblNewLabel_1_9_1_2.setBounds(10, 55, 40, 31);
		panel_2_2.add(lblNewLabel_1_9_1_2);
		
		JLabel lblNewLabel_1_9_1_2_1 = new JLabel("Change");
		lblNewLabel_1_9_1_2_1.setFont(new Font("Montserrat", Font.BOLD, 16));
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
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(154, 11, 106, 32);
		panel_2_2.add(panel_7);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setBackground(Color.WHITE);
		panel_7_1.setBounds(154, 54, 106, 32);
		panel_2_2.add(panel_7_1);
		

		
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
		
		btnNewButton.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(128, 128, 128), new Color(0, 255, 0)));
		btnNewButton.setBackground(new Color(153, 255, 102));
		btnNewButton.setBounds(166, 11, 103, 33);
		panel_2_1.add(btnNewButton);
		

		
		JLabel lblNewLabel_1_9 = new JLabel("Cost of Meal");
		lblNewLabel_1_9.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblNewLabel_1_9.setBounds(32, 53, 150, 31);
		panel_2.add(lblNewLabel_1_9);
		
		JPanel panel_7_2 = new JPanel();
		panel_7_2.setBackground(Color.WHITE);
		panel_7_2.setBounds(187, 11, 106, 32);
		panel_2.add(panel_7_2);
		
		JPanel panel_7_3 = new JPanel();
		panel_7_3.setBackground(Color.WHITE);
		panel_7_3.setBounds(187, 53, 106, 32);
		panel_2.add(panel_7_3);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(165, 95, 103, 33);
//		panel_2.add(btnExit);
		btnExit.setEnabled(true);
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.inactiveCaptionBorder);
		panel_5.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_5.setBounds(581, 317, 571, 64);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		textField_22 = new JTextField();
		textField_22.setBackground(SystemColor.inactiveCaptionBorder);
		textField_22.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Customer Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_22.setBounds(36, 11, 230, 42);
		panel_5.add(textField_22);
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		textField_23.setBackground(SystemColor.inactiveCaptionBorder);
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
		
		
		
		btnAdd = new JButton("Order");
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
				
				if (chckbx_3.isSelected()) {
					 int totalPric = Integer.parseInt(textField_2.getText()) * csBurger;
					 int totalqtn = Integer.parseInt(textField_2.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Cheese Burger", csBurger, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_5.isSelected()) {
					 int totalPric = Integer.parseInt(textField_5.getText()) * sBurger;
					 int totalqtn = Integer.parseInt(textField_5.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Steak Burger", sBurger, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_6.isSelected()) {
					 int totalPric = Integer.parseInt(textField_4.getText()) * cBurrito;
					 int totalqtn = Integer.parseInt(textField_4.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Chicken Burrito", cBurrito, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_7.isSelected()) {
					 int totalPric = Integer.parseInt(textField_3.getText()) * sBurrito;
					 int totalqtn = Integer.parseInt(textField_3.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Shrimp Burrito", sBurrito, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_4.isSelected()) {
					 int totalPric = Integer.parseInt(textField_6.getText()) * pBurrito;
					 int totalqtn = Integer.parseInt(textField_6.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Pork Burrito", pBurrito, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_8.isSelected()) {
					 int totalPric = Integer.parseInt(textField_7.getText()) * mPizza;
					 int totalqtn = Integer.parseInt(textField_7.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Margherita Pizza", mPizza, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_9.isSelected()) {
					 int totalPric = Integer.parseInt(textField_8.getText()) * hPizza;
					 int totalqtn = Integer.parseInt(textField_8.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Hawaiian Pizza", hPizza, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_10.isSelected()) {
					 int totalPric = Integer.parseInt(textField_9.getText()) * bPizza;
					 int totalqtn = Integer.parseInt(textField_9.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "BBQ Bacon Pizza", bPizza, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_11.isSelected()) {
					 int totalPric = Integer.parseInt(textField_10.getText()) * mxPizza;
					 int totalqtn = Integer.parseInt(textField_10.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Mix Pizza", mxPizza, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_12.isSelected()) {
					 int totalPric = Integer.parseInt(textField_11.getText()) * sWings;
					 int totalqtn = Integer.parseInt(textField_11.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Saucy Wings", sWings, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_13.isSelected()) {
					 int totalPric = Integer.parseInt(textField_12.getText()) * kMeal;
					 int totalqtn = Integer.parseInt(textField_12.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Kids Meal", kMeal, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_14.isSelected()) {
					 int totalPric = Integer.parseInt(textField_13.getText()) * cNugget;
					 int totalqtn = Integer.parseInt(textField_13.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Chicken Nugget", cNugget, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_15.isSelected()) {
					 int totalPric = Integer.parseInt(textField_14.getText()) * c;
					 int totalqtn = Integer.parseInt(textField_14.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Cappuccino", c, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_16.isSelected()) {
					 int totalPric = Integer.parseInt(textField_15.getText()) * a;
					 int totalqtn = Integer.parseInt(textField_15.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Americano", a, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_17.isSelected()) {
					 int totalPric = Integer.parseInt(textField_16.getText()) * es;
					 int totalqtn = Integer.parseInt(textField_16.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Espresso", es, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_18.isSelected()) {
					 int totalPric = Integer.parseInt(textField_17.getText()) * l;
					 int totalqtn = Integer.parseInt(textField_17.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Latte", l, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_19.isSelected()) {
					 int totalPric = Integer.parseInt(textField_18.getText()) * i;
					 int totalqtn = Integer.parseInt(textField_18.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Ice Latte", i, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_20.isSelected()) {
					 int totalPric = Integer.parseInt(textField_19.getText()) * m;
					 int totalqtn = Integer.parseInt(textField_19.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Mocha", m, totalqtn, totalPric, dateCur, timeCur);
				}
				
				if (chckbx_21.isSelected()) {
					 int totalPric = Integer.parseInt(textField_20.getText()) * f;
					 int totalqtn = Integer.parseInt(textField_20.getText());
					 
					CafeHome callAdd = new CafeHome();
					callAdd.add(textField_22.getText(), textField_23.getText(), "Flat White", f, totalqtn, totalPric, dateCur, timeCur);
				}
				
                String cName = textField_22.getText();
                String Ctb = textField_23.getText();
                
        		txtYourOrderHas.setText("Hello, " + cName);		
        		textField_24.setText("Your order has been placed for table " + Ctb);
				
			}
		});
		btnAdd.setFont(new Font("Montserrat", Font.BOLD, 14));
		btnAdd.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(128, 128, 128), new Color(0, 255, 0)));
		btnAdd.setBackground(new Color(153, 255, 102));
		btnAdd.setBounds(42, 11, 103, 33);
		panel_2_1.add(btnAdd);
		
		JButton getChange = new JButton("Get Change");
		getChange.setBounds(414, 11, 103, 33);
		panel_2_1.add(getChange);
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
		

		

		

		

		

	}
}
