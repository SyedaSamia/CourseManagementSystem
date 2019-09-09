import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class StudentDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetails frame = new StudentDetails();
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
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public JTextField showId;
	public JTextField showName;
	public JTextField showPhoneNo;
	public JTextField showCredit;
	public StudentDetails() {
		super("Student Course Details");
		setResizable(false);
		conn = DatabseConnection.connection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false); //this frame will be invisible will go to home frame
				home obj = new home();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(button);
		
		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login obj = new login();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(button_1);
		
		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student obj = new Student();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(button_2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(52, 60, 28, 14);
		contentPane.add(label);
		
		showId = new JTextField();
		showId.setColumns(10);
		showId.setBounds(115, 57, 202, 20);
		contentPane.add(showId);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(52, 123, 34, 14);
		contentPane.add(label_1);
		
		showName = new JTextField();
		showName.setColumns(10);
		showName.setBounds(117, 120, 200, 20);
		contentPane.add(showName);
		
		JButton updateName = new JButton("Update");
		updateName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					String name = showName.getText();
					int id = Integer.parseInt(showId.getText());
					String sql = "UPDATE STUDENT SET NAME = '"+name+"' WHERE ID = '"+id+"' ";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Name Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		updateName.setBounds(327, 119, 89, 23);
		contentPane.add(updateName);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setBounds(52, 184, 55, 14);
		contentPane.add(lblPhoneNo);
		
		showPhoneNo = new JTextField();
		showPhoneNo.setColumns(10);
		showPhoneNo.setBounds(117, 181, 200, 20);
		contentPane.add(showPhoneNo);
		
		JButton updatePhoneNo = new JButton("Update");
		updatePhoneNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					String phone = showPhoneNo.getText();
					int id = Integer.parseInt(showId.getText());
					String sql = "UPDATE STUDENT SET PHONE_NO = '"+phone+"' WHERE ID = '"+id+"' ";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Phone No Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		updatePhoneNo.setBounds(327, 180, 89, 23);
		contentPane.add(updatePhoneNo);
		
		JLabel lblStudentDetails = new JLabel("Student Details");
		lblStudentDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStudentDetails.setBounds(144, 18, 143, 28);
		contentPane.add(lblStudentDetails);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setBounds(52, 242, 55, 14);
		contentPane.add(lblCredits);
		
		showCredit = new JTextField();
		showCredit.setColumns(10);
		showCredit.setBounds(115, 239, 202, 20);
		contentPane.add(showCredit);
		
		JButton updateCredits = new JButton("Update");
		updateCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					int credits = Integer.parseInt(showCredit.getText());
					int id = Integer.parseInt(showId.getText());
					String sql = "UPDATE STUDENT SET TOTAL_CREDITS = '"+credits+"' WHERE ID = '"+id+"' ";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Total Credits Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		updateCredits.setBounds(327, 238, 89, 23);
		contentPane.add(updateCredits);
		
	/*	JLabel lblCourseTaken = new JLabel("Course Taken");
		lblCourseTaken.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCourseTaken.setBounds(151, 11, 129, 20);
		contentPane.add(lblCourseTaken);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 32, 390, 171);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			stmt =  (Statement) conn.createStatement();			
			String sql = "SELECT * FROM COURSE_OFFERED_S";
			PreparedStatement pst = conn.prepareStatement(sql);
			rs =  pst.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));			
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}*/
		
	}
}
