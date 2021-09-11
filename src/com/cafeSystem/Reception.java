package com.cafeSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javax.swing.table.DefaultTableCellRenderer;
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

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;


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
	private JLabel lblTotal_1;
	private JLabel lbl_subTotal_1;
	private JLabel lblChange1;
	
	JPanel panel;
	JScrollPane scrollPane_1;

	DefaultTableModel df;
	private JTextField textField_9;

	double total, sum, tax;
	private JTextField textField_4;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

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
			
			if (table.getRowCount() != 0) {
				
				DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
				centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
				
				table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer1 );
				table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer1 );
//				table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer1 );
				table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer1 );
				table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer1 );
				table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer1 );
				table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer1 );
				table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer1 );
				
				table.getTableHeader().setBorder(new LineBorder(Color.white));
			}

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
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.decode("#272836"));
		frame.setBounds(100, 100, 1160, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 817, 227);
		scrollPane.getViewport().setBackground(Color.decode("#262837"));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setBackground(new Color(38,40,55));
		table.setForeground(new Color(255, 255, 255));
		table.setGridColor(Color.WHITE);
		table.setRowHeight(25);
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
		
		
		table.getTableHeader().setBackground(Color.decode("#262837"));
		table.getTableHeader().setForeground(Color.decode("#ffffff"));
		

		
		scrollPane.setViewportView(table);
		


		JButton btnDisplay = new JButton("Refresh");
		btnDisplay.setBackground(Color.WHITE);
		btnDisplay.setFont(new Font("Lato", Font.BOLD, 11));
		btnDisplay.setBounds(707, 299, 120, 30);
		btnDisplay.setBorder(null);
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				display();

			}
		});
		frame.getContentPane().add(btnDisplay);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(Color.decode("#1E1C2C"));
		panel_1.setBounds(10, 346, 817, 215);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 50, 46, 14);
		panel_1.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(Color.decode("#2C303E"));
		textField.setForeground(new Color(255, 255, 255));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBorder(null);
		textField.setText(dateCur);
		textField.setBounds(102, 42, 125, 30);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.decode("#2C303E"));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBorder(null);
		textField_1.setColumns(10);
		textField_1.setBounds(102, 83, 125, 30);
		panel_1.add(textField_1);

		JLabel lblCustomerName = new JLabel("Name");
		lblCustomerName.setForeground(Color.WHITE);
		lblCustomerName.setFont(new Font("Lato", Font.BOLD, 12));
		lblCustomerName.setBounds(10, 91, 67, 14);
		panel_1.add(lblCustomerName);

		JLabel lblNewLabel_1 = new JLabel("Table No.");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 132, 60, 14);
		panel_1.add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBackground(Color.decode("#2C303E"));
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBorder(null);
		textField_2.setColumns(10);
		textField_2.setBounds(102, 124, 125, 30);
		panel_1.add(textField_2);

		JButton btnNewButton = new JButton("Show Order");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Lato", Font.BOLD, 11));
		btnNewButton.setBackground(Color.decode("#2299DD"));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String query = "select * from order_detail WHERE date = ? and custName = ? and tabNo = ?";

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
		btnNewButton.setBounds(10, 173, 103, 30);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Search Order");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 6, 131, 25);
		panel_1.add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 138, 2);
		panel_1.add(separator);

		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Lato", Font.BOLD, 11));
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				display();
				
				textField_1.setText(null);
				textField_2.setText(null);
				lblChange1.setText(null);
				textField_5.setText(null);
				textField_6.setText(null);
				textField_7.setText(null);
				textField_8.setText(null);
				textField_9.setText(null);

				lbl_subTotal.setText(null);
				lblTotal.setText(null);
				
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				
				model.setRowCount(0);
				
				scrollPane_1.setVisible(false);
				
				
				textField_4.setText("");
				textField_10.setText("");

				
				textField_11.setText("");
				textField_12.setText("");
				
				lbl_subTotal_1.setText(null);
				lblTotal_1.setText(null);
				
				
				

			}
		});
		btnNewButton_1.setBounds(126, 173, 89, 30);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Item Name");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(314, 50, 76, 14);
		panel_1.add(lblNewLabel_1_1_1);

		textField_5 = new JTextField();
		textField_5.setForeground(new Color(255, 255, 255));
		textField_5.setBackground(Color.decode("#2C303E"));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBorder(null);
		textField_5.setColumns(10);
		textField_5.setBounds(419, 45, 110, 30);
		panel_1.add(textField_5);

	
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(314, 91, 46, 14);
		panel_1.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1.setBounds(314, 132, 60, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Item Total");
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(572, 50, 60, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);

		textField_6 = new JTextField();
		textField_6.setForeground(new Color(255, 255, 255));
		textField_6.setBackground(Color.decode("#2C303E"));
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setBorder(new LineBorder(new Color(46, 49, 62)));
		textField_6.setColumns(10);
		textField_6.setBounds(419, 83, 110, 30);
		panel_1.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setForeground(new Color(255, 255, 255));
		textField_7.setBackground(Color.decode("#2C303E"));
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setBorder(new LineBorder(new Color(46, 49, 62)));
		textField_7.setColumns(10);
		textField_7.setBounds(419, 124, 110, 30);
		panel_1.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setForeground(new Color(255, 255, 255));
		textField_8.setBackground(Color.decode("#2C303E"));
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setBorder(null);
		textField_8.setColumns(10);
		textField_8.setBounds(660, 42, 110, 30);
		panel_1.add(textField_8);

		JLabel lblNewLabel_3 = new JLabel("Sub Total");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_3.setBounds(572, 91, 60, 14);
		panel_1.add(lblNewLabel_3);

		lbl_subTotal = new JLabel("");
		lbl_subTotal.setForeground(new Color(255, 255, 255));
		lbl_subTotal.setBackground(Color.LIGHT_GRAY);
		lbl_subTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(204, 204, 204), new Color(204, 204, 204), null));
		lbl_subTotal.setBounds(660, 80, 110, 30);
		panel_1.add(lbl_subTotal);

		JLabel lblNewLabel_5 = new JLabel("Total");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_5.setBounds(572, 132, 46, 14);
		panel_1.add(lblNewLabel_5);

		lblTotal = new JLabel("");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setBackground(Color.LIGHT_GRAY);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(204, 204, 204), new Color(204, 204, 204), null));
		lblTotal.setBounds(660, 121, 110, 30);
		panel_1.add(lblTotal);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Paid Amount");
		lblNewLabel_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(314, 173, 89, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1_1_1);

		textField_9 = new JTextField();
		textField_9.setForeground(new Color(255, 255, 255));
		textField_9.setBackground(Color.decode("#2C303E"));
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setBorder(new LineBorder(new Color(46, 49, 62)));
		textField_9.setColumns(10);
		textField_9.setBounds(419, 165, 110, 30);
		panel_1.add(textField_9);

		JLabel lblNewLabel_5_1 = new JLabel("Change");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Lato", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(572, 165, 60, 30);
		panel_1.add(lblNewLabel_5_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(314, 32, 138, 2);
		panel_1.add(separator_1);

		JLabel lblNewLabel_2_1 = new JLabel("Add to Invoice");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(314, 9, 163, 19);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlShadow);
		panel_2.setBounds(265, 42, 1, 145);
		panel_1.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.decode("#2E313E"));
		panel_3.setBounds(660, 80, 110, 31);
		panel_1.add(panel_3);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(Color.decode("#2E313E"));
		panel_3_1.setBounds(660, 121, 110, 31);
		panel_1.add(panel_3_1);
		
		lblChange1 = new JLabel("");
		lblChange1.setForeground(new Color(255, 255, 255));
		lblChange1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(204, 204, 204), new Color(204, 204, 204), null));
		lblChange1.setBackground(Color.LIGHT_GRAY);
		lblChange1.setBounds(660, 162, 110, 30);
		panel_1.add(lblChange1);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBackground(new Color(46, 49, 62));
		panel_3_1_1.setBounds(660, 162, 110, 31);
		panel_1.add(panel_3_1_1);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(837, 61, 297, 500);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBorder(null);
				scrollPane_1.getViewport().setBackground(Color.white);
				scrollPane_1.setBounds(0, 121, 297, 246);
				scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
				
				
				
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
						
						table_1.getTableHeader().setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(102, 102, 102)));
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
						
						DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
						centerRenderer.setHorizontalAlignment( JLabel.CENTER );
						
						table_1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
						table_1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
						table_1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
						table_1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
						
						scrollPane_1.setViewportView(table_1);
						
						textField_4 = new JTextField();
						textField_4.setBorder(null);
						textField_4.setBackground(new Color(255, 255, 255));
						textField_4.setEditable(false);
						textField_4.setHorizontalAlignment(SwingConstants.CENTER);
						textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
						textField_4.setBounds(0, 40, 297, 20);
						textField_4.setColumns(10);
						panel.add(textField_4);
						
						textField_10 = new JTextField();
						textField_10.setBackground(new Color(255, 255, 255));
						textField_10.setBorder(null);
						textField_10.setBounds(10, 76, 86, 20);
						textField_10.setColumns(10);
						panel.add(textField_10);
						

						lblTotal_1 = new JLabel("");
						lblTotal_1.setBackground(new Color(255, 255, 255));
						lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblTotal_1.setBorder(null);
						lblTotal_1.setBounds(203, 411, 86, 18);
						
						
						
						lbl_subTotal_1 = new JLabel("");
						lbl_subTotal_1.setBackground(new Color(255, 255, 255));
						lbl_subTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
						lbl_subTotal_1.setBorder(null);
						lbl_subTotal_1.setBounds(203, 378, 84, 18);
						
						
						textField_11 = new JTextField();
						textField_11.setBorder(null);
						textField_11.setFont(new Font("Tahoma", Font.BOLD, 11));
						textField_11.setBounds(133, 378, 76, 20);
						panel.add(textField_11);
						textField_11.setColumns(10);
						
						textField_12 = new JTextField();
						textField_12.setBorder(null);
						textField_12.setFont(new Font("Tahoma", Font.BOLD, 11));
						textField_12.setColumns(10);
						textField_12.setBounds(133, 409, 76, 20);
						panel.add(textField_12);
						
						JLabel lblNewLabel_4 = new JLabel("Receipt");
						lblNewLabel_4.setForeground(new Color(255, 255, 255));
						lblNewLabel_4.setFont(new Font("Lato", Font.BOLD, 25));
						lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_4.setBounds(837, 22, 297, 28);
						frame.getContentPane().add(lblNewLabel_4);
						
						JLabel lblNewLabel_4_1 = new JLabel("Order List");
						lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_4_1.setForeground(Color.WHITE);
						lblNewLabel_4_1.setFont(new Font("Lato", Font.BOLD, 25));
						lblNewLabel_4_1.setBounds(10, 22, 817, 28);
						frame.getContentPane().add(lblNewLabel_4_1);
						
								JButton btnNewButton_2 = new JButton("Add");
								btnNewButton_2.setBounds(20, 299, 89, 30);
								frame.getContentPane().add(btnNewButton_2);
								btnNewButton_2.setForeground(Color.white);
								btnNewButton_2.setFont(new Font("Lato", Font.BOLD, 11));
								btnNewButton_2.setBackground(new Color(255, 102, 51));
								btnNewButton_2.setBorder(null);
								
										JButton btnNewButton_3 = new JButton("Print");
										btnNewButton_3.setBounds(589, 299, 89, 30);
										frame.getContentPane().add(btnNewButton_3);
										btnNewButton_3.setForeground(Color.WHITE);
										btnNewButton_3.setFont(new Font("Lato", Font.BOLD, 11));
										btnNewButton_3.setBackground(Color.decode("#4447F5"));
										btnNewButton_3.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {

												PrinterJob printerJob = PrinterJob.getPrinterJob();
												printerJob.setJobName("Print Bill");
												
										        printerJob.setPrintable(new Printable() {
										            @Override
										            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
										                // Check If No Printable Content
										                if(pageIndex > 0){
										                    return Printable.NO_SUCH_PAGE;
										                }
										                
										                // Make 2D Graphics to map content
										                Graphics2D graphics2D = (Graphics2D)graphics;
										                
										                // Set Graphics Translations
										                // A Little Correction here Multiplication was not working so I replaced with addition
										                graphics2D.translate(pageFormat.getImageableX()+10, pageFormat.getImageableY()+10);
										                
										                // This is a page scale. Default should be 0.3 I am using 0.5
										                graphics2D.scale(0.3, 0.3);
										                
										                // Now paint panel as graphics2D
										                panel.paint(graphics2D);
										                
										                // return if page exists
										                return Printable.PAGE_EXISTS;
										            }
										        });
										        // Store printerDialog as boolean
										        boolean returningResult = printerJob.printDialog();
										        // check if dilog is showing
										        if(returningResult){
										            // Use try catch exeption for failure
										            try{
										                // Now call print method inside printerJob to print
										                printerJob.print();
										            }catch (PrinterException printerException){
										                JOptionPane.showMessageDialog(null, this, "Print Error: " + printerException.getMessage(), 0);
										            }
												

											}
											}
										});
										btnNewButton_3.setBorder(null);
										
												JButton btnNewButton_2_1 = new JButton("Get Change");
												btnNewButton_2_1.setBounds(138, 299, 96, 30);
												frame.getContentPane().add(btnNewButton_2_1);
												btnNewButton_2_1.setForeground(Color.WHITE);
												btnNewButton_2_1.setFont(new Font("Lato", Font.BOLD, 11));
												btnNewButton_2_1.setBackground(new Color(0, 153, 102));
												btnNewButton_2_1.setBorder(null);
														
												btnNewButton_2_1.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {

														double paidAmt = Double.parseDouble(textField_9.getText());

														double changeAmt = paidAmt - total;

														lblChange1.setText(String.valueOf(changeAmt));

													}
												});
								btnNewButton_2.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										

										textField_4.setText("CAFE SYSTEM");
										textField_10.setText(dateCur);
										
										panel.add(scrollPane_1);
										scrollPane_1.setVisible(true);
										
										textField_11.setText("Sub Total");
										textField_12.setText("Total");
										
										panel.add(lbl_subTotal_1);
										panel.add(lblTotal_1);
										



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
	}
}
