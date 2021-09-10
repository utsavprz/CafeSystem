package com.cafeSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import javax.swing.DebugGraphics;
import javax.swing.SwingConstants;


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
	JLabel lblTotal_1;
	JLabel lbl_subTotal_1;

	DefaultTableModel df;
	private JTextField textField_9;

	double total, sum, tax;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_10;

	public void connectDB() {
		try {

			connection = DriverManager.getConnection(url, user, pass);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void display() {

		try {

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
		frame.setBounds(100, 100, 1160, 562);
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

		JButton btnDisplay = new JButton("Refresh");
		btnDisplay.setBounds(696, 290, 131, 23);
		btnDisplay.setBorder(null);
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
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String query = "select * from order_detail WHERE date = ? and custName = ? or tabNo = ?";

					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());

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
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBorder(null);
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
		btnNewButton_2.setBackground(Color.ORANGE);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				if (textField_4.getText().isEmpty() && textField_10.getText().isEmpty()) {
					textField_4.setText("CAFE SYSTEM");
					textField_10.setText(dateCur);
					
				}

				String itemName = textField_5.getText();
				int itemPrice = Integer.parseInt(textField_6.getText());
				int itemQty = Integer.parseInt(textField_7.getText());
				int itemTot = Integer.parseInt(textField_8.getText());

				String url = "jdbc:mysql://127.0.0.1:3306/cafe_system";
				String user = "root";
				String pass = "madridista00";

				if (itemName.isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton_2, "Enter valid details");
				} else {
					try {
						Connection connection = DriverManager.getConnection(url, user, pass);

						String query = " insert into invoiceTemp (item, price, qty, total)" + " values (?, ?, ?, ?)";

						Statement sta = connection.createStatement();

						PreparedStatement preparedStmt = connection.prepareStatement(query);
						preparedStmt.setString(1, itemName);
						preparedStmt.setInt(2, itemPrice);
						preparedStmt.setInt(3, itemQty);
						preparedStmt.setInt(4, itemTot);

						preparedStmt.execute();

						connection.close();

					} catch (Exception x) {
						x.printStackTrace();
					}
				}

				df = (DefaultTableModel) table_1.getModel();
				df.addRow(new Object[] {

						textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText()

				});

				sum = 0;

				for (int i = 0; i < table_1.getRowCount(); i++) {

					sum = sum + Integer.parseInt(table_1.getValueAt(i, 3).toString());

				}

				tax = (sum * 10) / 100;

				total = sum + tax;

				lbl_subTotal.setText(String.valueOf("Rs " + sum));
				lbl_subTotal_1.setText(String.valueOf("Rs " + sum));
				lblTotal.setText(String.valueOf("Rs " + total));
				lblTotal_1.setText(String.valueOf("Rs " + total));

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
		btnNewButton_2_1.setBackground(Color.GREEN);
		btnNewButton_2_1.setBorder(null);
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
		btnNewButton_3.setBackground(Color.ORANGE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				//				HashMap<String, Object> a = new HashMap<String, Object>();
//
//				try {
//					System.out.println("loading");
//					JasperDesign jdesign = JRXmlLoader
//							.load("C:\\Users\\utsav\\eclipse-workspace\\Gui\\src\\com\\cafeSystem\\inv.jrxml");
//					System.out.println("got design");
//
//					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//
//					System.out.println("Compiling");
//
//					JasperPrint jprint = JasperFillManager.fillReport(jreport, a, connection);
//					System.out.println("Printing");
//
//					JasperViewer.viewReport(jprint);
//
//				} catch (JRException e1) {
//					// TODO Auto-generated catch block
//					System.out.println(e1);
//
//				}
				


			}
		});
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBounds(703, 85, 89, 23);
		panel_1.add(btnNewButton_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(253, 32, 138, 2);
		panel_1.add(separator_1);

		JLabel lblNewLabel_2_1 = new JLabel("Add to Invoice");
		lblNewLabel_2_1.setFont(new Font("Montserrat", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(253, 11, 163, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(837, 61, 297, 440);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.getViewport().setBackground(Color.white);
				scrollPane_1.setBounds(0, 121, 297, 246);
				panel.add(scrollPane_1);
				
						table_1 = new JTable();
						table_1.setGridColor(new Color(255, 255, 255));
						table_1.setRowSelectionAllowed(false);
						table_1.setShowGrid(false);
						table_1.setShowHorizontalLines(false);
						table_1.setShowVerticalLines(false);
						table_1.setRowHeight(30);
						table_1.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Item", "Price", "Qty", "Total"
							}
						) {
							Class[] columnTypes = new Class[] {
								String.class, String.class, String.class, String.class
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
						table_1.getTableHeader().setBackground(Color.white);
						table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
						table_1.getColumnModel().getColumn(0).setMinWidth(140);
						table_1.getColumnModel().getColumn(0).setMaxWidth(140);
						
						table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
						table_1.getColumnModel().getColumn(1).setMinWidth(60);
						table_1.getColumnModel().getColumn(1).setMaxWidth(60);
						
						table_1.getColumnModel().getColumn(2).setPreferredWidth(45);
						table_1.getColumnModel().getColumn(2).setMinWidth(45);
						table_1.getColumnModel().getColumn(2).setMaxWidth(45);
						
						table_1.getColumnModel().getColumn(3).setPreferredWidth(60);
						table_1.getColumnModel().getColumn(3).setMinWidth(60);
						table_1.getColumnModel().getColumn(3).setMaxWidth(60);
						
						scrollPane_1.setViewportView(table_1);
						
						textField_4 = new JTextField();
						textField_4.setBorder(null);
						textField_4.setBackground(new Color(255, 255, 255));
						textField_4.setEditable(false);
						textField_4.setHorizontalAlignment(SwingConstants.CENTER);
						textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
						textField_4.setBounds(0, 40, 297, 20);
						panel.add(textField_4);
						textField_4.setColumns(10);
						
						textField_10 = new JTextField();
						textField_10.setBackground(new Color(255, 255, 255));
						textField_10.setBorder(null);
						textField_10.setBounds(10, 76, 86, 20);
						panel.add(textField_10);
						textField_10.setColumns(10);
						
						lblTotal_1 = new JLabel("");
						lblTotal_1.setBackground(new Color(255, 255, 255));
						lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblTotal_1.setBorder(null);
						lblTotal_1.setBounds(203, 411, 86, 18);
						panel.add(lblTotal_1);
						
						JLabel lblNewLabel_5_2 = new JLabel("Total");
						lblNewLabel_5_2.setBounds(147, 411, 46, 14);
						panel.add(lblNewLabel_5_2);
						
						lbl_subTotal_1 = new JLabel("");
						lbl_subTotal_1.setBackground(new Color(255, 255, 255));
						lbl_subTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
						lbl_subTotal_1.setBorder(null);
						lbl_subTotal_1.setBounds(203, 378, 84, 18);
						panel.add(lbl_subTotal_1);
						
						JLabel lblNewLabel_3_1 = new JLabel("Sub Total");
						lblNewLabel_3_1.setBounds(133, 378, 60, 14);
						panel.add(lblNewLabel_3_1);
	}
}
