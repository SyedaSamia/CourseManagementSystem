import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class FacultyDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyDetails frame = new FacultyDetails();
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
	public JTextField showMail;
	
	public FacultyDetails() {
		//super("Faculty Course Details");
		super("Faculty Details");
		setResizable(false);
		conn = DatabseConnection.connection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false); //this frame will be invisible will go to home frame
				home obj = new home();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(btnHome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login obj = new login();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(btnLogout);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	setVisible(false); //this frame will be invisible will go to home frame
				Faculty obj = new Faculty();
				obj.setVisible(true);
				dispose();
			}
		});
		menuBar.add(btnBack);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 64, 390, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		*/
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(53, 107, 28, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(53, 170, 34, 14);
		contentPane.add(lblName);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(53, 231, 28, 14);
		contentPane.add(lblMail);
		JButton btnUpdateName = new JButton("Update");
		btnUpdateName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					String name = showName.getText();
					int id = Integer.parseInt(showId.getText());
					String sql = "UPDATE FACULTY_MEMBERS SET NAME = '"+name+"' WHERE ID = '"+id+"' ";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Name Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnUpdateName.setBounds(303, 166, 89, 23);
		contentPane.add(btnUpdateName);
		
		showId = new JTextField();
		showId.setBounds(91, 104, 202, 20);
		contentPane.add(showId);
		showId.setColumns(10);
		
		showName = new JTextField();
		showName.setColumns(10);
		showName.setBounds(93, 167, 200, 20);
		contentPane.add(showName);
		
		showMail = new JTextField();
		showMail.setColumns(10);
		showMail.setBounds(93, 228, 200, 20);
		contentPane.add(showMail);
		
		JLabel lblFacultyDetails = new JLabel("Faculty Details");
		lblFacultyDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFacultyDetails.setBounds(163, 28, 143, 28);
		contentPane.add(lblFacultyDetails);
		
		JButton buttonUpdateMail = new JButton("Update");
		buttonUpdateMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmt =  (Statement) conn.createStatement();
					String mail = showMail.getText();
					int id = Integer.parseInt(showId.getText());
					String sql = "UPDATE FACULTY_MEMBERS SET MAIL = '"+mail+"' WHERE ID = '"+id+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.execute(sql);
					JOptionPane.showMessageDialog(null, "Mail Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		buttonUpdateMail.setBounds(303, 227, 89, 23);
		contentPane.add(buttonUpdateMail);
		
		/*try{
			
			stmt =  (Statement) conn.createStatement();
			
			String sql = "SELECT * FROM COURSE_OFFERED_F";
			PreparedStatement pst = conn.prepareStatement(sql);
			rs =  pst.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));			
			
			
			
			JButton btnUpdate = new JButton("Update");
			btnUpdate.setBounds(326, 303, 89, 23);
			contentPane.add(btnUpdate);
			
			showId = new JTextField();
			showId.setBounds(63, 8, 89, 20);
			contentPane.add(showId);
			showId.setColumns(10);
			
			showName = new JTextField();
			showName.setColumns(10);
			showName.setBounds(265, 8, 150, 20);
			contentPane.add(showName);
			
			showMail = new JTextField();
			showMail.setColumns(10);
			showMail.setBounds(265, 33, 150, 20);
			contentPane.add(showMail);
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}*/
	}
}
